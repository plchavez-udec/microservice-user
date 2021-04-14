package co.edu.ierdminayticha.sgd.user.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import co.edu.ierdminayticha.sgd.user.api.IUserApi;
import co.edu.ierdminayticha.sgd.user.dto.UserRequestDto;
import co.edu.ierdminayticha.sgd.user.dto.UserResponseDto;
import co.edu.ierdminayticha.sgd.user.dto.UserResponseListDto;
import co.edu.ierdminayticha.sgd.user.service.IUserService;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RefreshScope
@RestController
@RequestMapping(value = "user/v1/user")
public class UserController implements IUserApi {

	@Autowired
	private IUserService service;

	@Override
	public ResponseEntity<UserResponseDto> create(UserRequestDto request) {

		log.info("IUserService : create - Creando recurso {}", request);

		service.create(request);

		log.info("IUserService : create - Transacción exitosa, recurso creado");

		return ResponseEntity.ok().build();
	}

	@Override
	public ResponseEntity<UserResponseDto> findById(Long id) {

		log.info("IUserService : findById - Consultando recurso con id " + "{}", id);

		UserResponseDto response = this.service.findById(id);

		log.info("IUserService : findById - Transacción exitosa, recurso: " + "{}", response);

		return ResponseEntity.ok(response);
	}

	@Override
	public ResponseEntity<List<UserResponseListDto>> findAll() {

		log.info("IUserService : findAll - Consultando todos los registros");

		List<UserResponseListDto> response = service.findAll();

		log.info("IUserService : findAll - Transacción exitosa, registros " + "consultados: ", response);

		return ResponseEntity.ok(response);
	}

	@Override
	public ResponseEntity<?> update(Long id, UserRequestDto request) {

		log.info("IUserService : update - Actualizando recurso con id {}, " + "nuevos valores: {}", id, request);

		service.update(id, request);

		log.info("IUserService : update - Transacción exitosa, registro " + "actualizado");

		return ResponseEntity.noContent().build();
	}

	@Override
	public ResponseEntity<?> delete(Long id) {

		log.info("IUserService : delete - Eliminando recurso con id {}", id);

		this.service.delete(id);

		log.info("IUserService : delete - Transacción exitosa, recurso " + "eliminado");

		return ResponseEntity.ok().build();
	}

	private ResponseEntity<UserResponseDto> buildCreationResponse(UserResponseDto response) {

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{referencia-id}")
				.buildAndExpand(response.getId()).toUri();

		return ResponseEntity.created(uri).body(response);

	}

	@Override
	public ResponseEntity<UserResponseDto> findByUserName(String userName) {
		log.info("IUserService : findByUserName - Consultando recurso con userName " + "{}", userName);

		UserResponseDto response = this.service.findByUserName(userName);

		log.info("IUserService : findByUserName - Transacción exitosa, recurso: {}", response);

		return ResponseEntity.ok(response);
	}

}
