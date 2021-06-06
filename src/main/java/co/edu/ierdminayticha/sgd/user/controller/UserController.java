package co.edu.ierdminayticha.sgd.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.ierdminayticha.sgd.user.api.IUserApi;
import co.edu.ierdminayticha.sgd.user.dto.RoleDto;
import co.edu.ierdminayticha.sgd.user.dto.UserRequestDto;
import co.edu.ierdminayticha.sgd.user.dto.UserResponseDto;
import co.edu.ierdminayticha.sgd.user.service.IUserService;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RefreshScope
@RestController
@RequestMapping(value = "v1/user")
public class UserController implements IUserApi {

	@Autowired
	private IUserService service;

	@Override
	public ResponseEntity<UserResponseDto> create(UserRequestDto request) {
		log.info("Crear usuario {}", request);
		service.create(request);
		log.info("Transacción exitosa, usuario creado");
		return ResponseEntity.ok().build();
	}

	@Override
	public ResponseEntity<UserResponseDto> findById(Long id) {
		log.info("Consultando recurso con id " + "{}", id);
		UserResponseDto response = this.service.findById(id);
		log.info("Transacción exitosa, recurso: " + "{}", response);
		return ResponseEntity.ok(response);
	}

	@Override
	public ResponseEntity<List<UserResponseDto>> findAll() {
		log.info("Consultando todos los registros");
		List<UserResponseDto> response = service.findAll();
		log.info("Transacción exitosa, registros consultados: ", response);
		return ResponseEntity.ok(response);
	}

	@Override
	public ResponseEntity<?> update(Long id, UserRequestDto request) {
		log.info("Actualizando recurso con id {}, " + "nuevos valores: {}", id, request);
		service.update(id, request);
		log.info("Transacción exitosa, registro " + "actualizado");
		return ResponseEntity.noContent().build();
	}

	@Override
	public ResponseEntity<?> delete(Long id) {
		log.info("Eliminando recurso con id {}", id);
		this.service.delete(id);
		log.info("Transacción exitosa, recurso " + "eliminado");
		return ResponseEntity.ok().build();
	}

	@Override
	public ResponseEntity<UserResponseDto> findByUserName(String userName) {
		log.info("Consultando recurso con userName " + "{}", userName);
		UserResponseDto response = this.service.findByUserName(userName);
		log.info("Transacción exitosa, recurso: {}", response);
		return ResponseEntity.ok(response);
	}

	@Override
	public ResponseEntity<List<RoleDto>> findAllRole() {
		List<RoleDto> response= this.service.findAllListaRoles();
		return ResponseEntity.ok(response);
	}

}
