package co.edu.ierdminayticha.sgd.user.service;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import co.edu.ierdminayticha.sgd.user.dto.UserRequestDto;
import co.edu.ierdminayticha.sgd.user.dto.UserResponseDto;
import co.edu.ierdminayticha.sgd.user.dto.UserResponseListDto;
import co.edu.ierdminayticha.sgd.user.entity.UserEntity;
import co.edu.ierdminayticha.sgd.user.exception.GeneralException;
import co.edu.ierdminayticha.sgd.user.repository.IUserRepository;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class UserServiceImpl implements IUserService {

	private static final String EXISTING_RESOURCE_MESSAGE = "El recurso con id (%s) ya existe ";
	private static final String NO_EXISTEN_RESOURCE_MESSAGE = "No existe el recurso con id (%s) ";
	private static final String NO_EXISTEN_INFO_MESSAGE = "No existe información para mostrar";

	@Autowired
	private IUserRepository repository;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private RestTemplate restTemplate;
	
	@Override
	public UserResponseDto create(UserRequestDto dto) {
		
		log.info("ReferenciaEntityServiceImpl : create - Creando recurso");

		validateExistenceOfTheResource(dto.getId());

		UserEntity entity = toPersist(dto);

		return createSuccessfulResponse(entity);

	}

	@Override
	public UserResponseDto findById(Long id) {
		
		log.info("ReferenciaEntityServiceImpl : findById - Consultando recurso por Id");
		
		UserEntity entity = this.repository.findById(id)
				.orElseThrow(() -> new NoSuchElementException(String.format(NO_EXISTEN_RESOURCE_MESSAGE, id)));
				
		return createSuccessfulResponse(entity);
	}

	@Override
	public List<UserResponseListDto> findAll() {
		
		log.info("ReferenciaEntityServiceImpl : findAll - Consultando lista de  recursos");

		Iterable<UserEntity> entityList = this.repository.findAll();

		if (entityList == null) {
			throw new NoSuchElementException(NO_EXISTEN_INFO_MESSAGE);
		}

		return createSuccessfulResponse(entityList);
	}

	@Override
	public void update(Long id, UserRequestDto dto) {
		
		log.info("ReferenciaEntityServiceImpl : update - Actualizando recurso");

		UserEntity entity = this.repository.findById(id)
				.orElseThrow(() -> new NoSuchElementException(String.format(NO_EXISTEN_RESOURCE_MESSAGE, id)));

		this.modelMapper.map(dto, entity);

		entity.setLastModifiedDate(new Date());

		this.repository.save(entity);

	}

	@Override
	public void delete(Long id) {
		
		log.info("ReferenciaEntityServiceImpl : delete - Eliminando recurso");
		
		UserEntity entity = this.repository.findById(id)
				.orElseThrow(() -> new NoSuchElementException(String.format(NO_EXISTEN_RESOURCE_MESSAGE, id)));
		
		repository.delete(entity);

	}

	private void validateExistenceOfTheResource(Long id) {

		UserEntity entity = this.repository.findById(id).get();

		if (entity != null) {
			
			log.info("ReferenciaEntityServiceImpl : validateExistenceOfTheResource - "
					+ "el recurso con id ({}) ya existe", id);
			
			throw new GeneralException(String.format(EXISTING_RESOURCE_MESSAGE, id));					
		}

	}

	private UserEntity toPersist(UserRequestDto dto) {

		UserEntity entity = this.modelMapper.map(dto, UserEntity.class);

		entity.setCreationDate(new Date());
		
		log.info("ReferenciaEntityServiceImpl : toPersist - "
				+ "recurso a persistir: ", entity);

		entity = this.repository.save(entity);

		return entity;

	}

	private UserResponseDto createSuccessfulResponse(UserEntity entity) {

		UserResponseDto response = this.modelMapper.map(entity, UserResponseDto.class);

		return response;

	}

	private List<UserResponseListDto> createSuccessfulResponse(Iterable<UserEntity> entityList) {

		List<UserResponseListDto> dtoList = modelMapper.map(entityList, new TypeToken<List<UserResponseListDto>>() {
		}.getType());

		return dtoList;

	}

}
