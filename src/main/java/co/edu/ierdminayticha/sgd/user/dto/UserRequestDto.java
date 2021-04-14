package co.edu.ierdminayticha.sgd.user.dto;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class UserRequestDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private String username;
	private String password;
	private Boolean enabled;
	private String nombre;
	private String apellido;
	private String email;
	private List<RoleInDto> roles;

}
