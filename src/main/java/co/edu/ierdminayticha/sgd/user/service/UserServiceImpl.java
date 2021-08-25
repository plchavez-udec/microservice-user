package co.edu.ierdminayticha.sgd.user.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import co.edu.ierdminayticha.sgd.user.dto.RoleDto;
import co.edu.ierdminayticha.sgd.user.dto.RoleInDto;
import co.edu.ierdminayticha.sgd.user.dto.UserRequestDto;
import co.edu.ierdminayticha.sgd.user.dto.UserResponseDto;
import co.edu.ierdminayticha.sgd.user.entity.UserEntity;
import co.edu.ierdminayticha.sgd.user.entity.UserRoleEntity;
import co.edu.ierdminayticha.sgd.user.exception.GeneralException;
import co.edu.ierdminayticha.sgd.user.repository.IRoleRepository;
import co.edu.ierdminayticha.sgd.user.repository.IUserRepository;
import co.edu.ierdminayticha.sgd.user.repository.IUserRoleRepository;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class UserServiceImpl implements IUserService {

	private static final String NO_EXISTEN_RESOURCE_MESSAGE = "No existe el usuario con id (%s) ";
	private static final String NO_EXISTEN_INFO_MESSAGE = "No existe información para mostrar";

	@Autowired
	private IUserRepository repository;
	@Autowired
	private IUserRoleRepository userRoleRepository;
	@Autowired
	private IRoleRepository roleRepository;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Override
	public UserResponseDto create(UserRequestDto request) {
		log.info("Crear usuario {}", request);
		validateExistenceOfResource(request.getUsername());
		UserEntity usertOut = toPersist(request);
		return createSuccessfulResponse(usertOut, "CREAR");
	}

	@Override
	public UserResponseDto findById(Long id) {
		log.info("Consultando usuario por Id {}", id);
		UserEntity entity = this.repository.findById(id)
				.orElseThrow(() -> new NoSuchElementException(String.format(NO_EXISTEN_RESOURCE_MESSAGE, id)));
		return createSuccessfulResponse(entity, "BUSCAR");
	}

	@Override
	public List<UserResponseDto> findAll() {
		log.info("Consultando lista de usuarios");
		Iterable<UserEntity> entityList = this.repository.findAllByEnabled(Boolean.TRUE);
		if (entityList == null) {
			throw new NoSuchElementException(NO_EXISTEN_INFO_MESSAGE);
		}
		return createSuccessfulResponse(entityList);
	}

	@Override
	public void update(Long id, UserRequestDto dto) {
		log.info("UserServiceImpl : update - Actualizando recurso");
		UserEntity entity = this.repository.findById(id)
				.orElseThrow(() -> new NoSuchElementException(String.format(NO_EXISTEN_RESOURCE_MESSAGE, id)));
		this.modelMapper.map(dto, entity);
		entity.setLastModifiedDate(new Date());
		this.repository.save(entity);
	}

	@Override
	public void delete(Long id) {
		log.info("UserServiceImpl : delete - Eliminando usuario: {}", id);
		this.repository.disableUser(id);
	}

	@Override
	public UserResponseDto findByUserName(String userName) {
		log.info("UserServiceImpl : findByUserName - Consultando usuario");
		UserEntity user = this.repository.findByUsernameAndEnabled(userName, true);
		if (user == null) {
			throw new GeneralException(String.format("No existe el usuario %s", userName));
		}
		return createSuccessfulResponse(user, "BUSCAR");
	}

	private void validateExistenceOfResource(String userName) {
		UserEntity user = this.repository.findByUsernameAndEnabled(userName, true);
		if (user != null) {
			throw new GeneralException(String.format("El usuario %s ya existe en el sistema", userName));
		}
	}

	private UserEntity toPersist(UserRequestDto request) {
		UserEntity userOut = null;
		UserEntity user = this.repository.findByUsernameAndEnabled(request.getUsername(), false);
		if (user == null) {
			UserEntity userIn = new UserEntity();
			userIn.setUsername(request.getUsername());
			userIn.setEnabled(true);
			userIn.setNombre(request.getNombre());
			userIn.setApellido(request.getApellido());
			userIn.setEmail(request.getEmail());
			userIn.setCreationUser(request.getCreationUser());
			userIn.setPassword(passwordEncoder.encode(request.getPassword()));
			userIn.setCreationDate(new Date());
			userOut = this.repository.save(userIn);
			for (RoleInDto roleDto : request.getRoles()) {
				UserRoleEntity userRole = new UserRoleEntity();
				userRole.setUser(userOut);
				userRole.setRole(this.roleRepository.findById(roleDto.getId())
						.orElseThrow(() -> new NoSuchElementException("No existe el rol informado")));
				this.userRoleRepository.save(userRole);
			}
		}else {
			user.setEnabled(true);
			userOut = this.repository.save(user);
		}
		return userOut;

	}

	private UserResponseDto createSuccessfulResponse(UserEntity userEntity, String accion) {
		UserResponseDto userdto = new UserResponseDto();
		userdto.setId(userEntity.getId());
		userdto.setNombre(userEntity.getNombre());
		userdto.setApellido(userEntity.getApellido());
		userdto.setEmail(userEntity.getEmail());
		userdto.setUsername(userEntity.getUsername());
		userdto.setPassword(userEntity.getPassword());
		userdto.setEnabled(userEntity.getEnabled());
		userdto.setCreationDate(userEntity.getCreationDate());
		if (userEntity.getLastModifiedDate() != null) {
			userdto.setLastModifiedDate(userEntity.getLastModifiedDate());
		}
		if (accion.equals("BUSCAR")) {
			userdto.setRoles(new ArrayList<>());
			for (UserRoleEntity userRoleEntity : userEntity.getListUserRole()) {
				RoleDto roleDto = new RoleDto();
				roleDto.setId(userRoleEntity.getId());
				roleDto.setName(userRoleEntity.getRole().getNombre());
				userdto.getRoles().add(roleDto);
			}
		}
		return userdto;

	}

	private List<UserResponseDto> createSuccessfulResponse(Iterable<UserEntity> entityList) {
		List<UserResponseDto> dtoList = new ArrayList<>();
		for (UserEntity userEntity : entityList) {
			UserResponseDto userResponseDto = new UserResponseDto();
			userResponseDto = createSuccessfulResponse(userEntity, "BUSCAR");
			dtoList.add(userResponseDto);
		}
		return dtoList;

	}

}
