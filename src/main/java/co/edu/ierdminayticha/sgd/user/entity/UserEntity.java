package co.edu.ierdminayticha.sgd.user.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "\"USUARIOS\"")
public class UserEntity {

	@Id
	@SequenceGenerator(name = "\"SEQ_REFERENCIA_ID\"", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "\"SEQ_REFERENCIA_ID\"")
	@Column(name = "\"ID\"")
	private Long id;
	@Column(name = "\"USUARIO\"")
	private String username;
	@Column(name = "\"CONTRASENA\"")
	private String password;
	@Column(name = "\"ES_HABILITADO\"")
	private Boolean enabled;
	@Column(name = "\"NOMBRE\"")
	private String nombre;
	@Column(name = "\"APELLIDO\"")
	private String apellido;
	@Column(name = "\"CORREO\"")
	private String email;
	@Column(name = "\"FECHA_CREACION\"")
	private Date creationDate;
	@Column(name = "\"FECHA_MODIFICACION\"")
	private Date lastModifiedDate;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	private List<UserRoleEntity> listUserRole;


}
