package co.edu.ierdminayticha.sgd.user.api;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import co.edu.ierdminayticha.sgd.user.dto.IRequestCreateValidation;
import co.edu.ierdminayticha.sgd.user.dto.IRequestUpdateValidation;
import co.edu.ierdminayticha.sgd.user.dto.UserRequestDto;
import co.edu.ierdminayticha.sgd.user.dto.UserResponseDto;
import co.edu.ierdminayticha.sgd.user.dto.UserResponseListDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "Api Microservicio para la gestion de usuarios", 
     tags = "Api Microservicio para la gestion de usuarios")
public interface IUserApi {
	
	@ApiOperation(value = "Crear usuario",
		          response = UserResponseDto.class)
	@PostMapping(value = "", 
				 consumes = MediaType.APPLICATION_JSON_VALUE, 
				 produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<UserResponseDto> create( @Validated(IRequestCreateValidation.class)
							  @RequestBody 
							  UserRequestDto request);

	@ApiOperation(value = "Obtener usuario por Id",
	              response = UserResponseDto.class)
	@GetMapping(value = "{usuario-id}", 
			    produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<UserResponseDto> findById(@PathVariable("usuario-id") Long id);

	@ApiOperation(value = "Obtener lista de usuarios",
            	  response = UserResponseListDto.class)
	@GetMapping(value = "", 
				produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<List<UserResponseListDto>> findAll();

	@ApiOperation(value = "Actualización parcial del usuario",
      	  		  response = UserRequestDto.class)
	@PatchMapping(value = "{usuario-id}", 
			 	  consumes = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<?> update(
							 @PathVariable("usuario-id")
							 Long id,
							 @Validated(IRequestUpdateValidation.class)
					  		 @RequestBody
							 UserRequestDto request);

	@ApiOperation(value = "Eliminar usuario",
	  		  response = UserRequestDto.class)
	@DeleteMapping(value = "{usuario-id}")
	ResponseEntity<?> delete(Long id);

}
