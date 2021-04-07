package co.edu.ierdminayticha.sgd.user.service;

import java.util.List;

import co.edu.ierdminayticha.sgd.user.dto.UserRequestDto;
import co.edu.ierdminayticha.sgd.user.dto.UserResponseDto;
import co.edu.ierdminayticha.sgd.user.dto.UserResponseListDto;

public interface IUserService {

	UserResponseDto create(UserRequestDto dto);

	UserResponseDto findById(Long id);

	List<UserResponseListDto> findAll();

	void update(Long id, UserRequestDto dto);

	void delete(Long id);

}
