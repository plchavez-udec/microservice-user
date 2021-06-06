package co.edu.ierdminayticha.sgd.user.service;

import java.util.List;

import co.edu.ierdminayticha.sgd.user.dto.RoleDto;
import co.edu.ierdminayticha.sgd.user.dto.UserRequestDto;
import co.edu.ierdminayticha.sgd.user.dto.UserResponseDto;

public interface IUserService {

	UserResponseDto create(UserRequestDto dto);
	UserResponseDto findById(Long id);
	UserResponseDto findByUserName(String userName);
	List<UserResponseDto> findAll();
	void update(Long id, UserRequestDto dto);
	void delete(Long id);
	//crear metodo que no recibe nada y retorna una lista de  roles
	
	List<RoleDto> findAllListaRoles();
}
