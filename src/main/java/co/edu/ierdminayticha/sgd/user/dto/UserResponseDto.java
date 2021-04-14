package co.edu.ierdminayticha.sgd.user.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class UserResponseDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String username;
	private String password;
	private Boolean enabled;
	private String nombre;
	private String apellido;
	private String email;
	private Date creationDate;
	private Date lastModifiedDate;
	private List<RoleDto> roles;

}
