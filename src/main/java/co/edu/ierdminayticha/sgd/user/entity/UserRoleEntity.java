package co.edu.ierdminayticha.sgd.user.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "\"USUARIOS_ROLES\"")
public class UserRoleEntity {

	@Id
	@SequenceGenerator(name = "\"SEQ_USUARIOS_ROLES_ID\"", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "\"SEQ_USUARIOS_ROLES_ID\"")
	@Column(name = "\"ID\"")
	private Long id;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "\"ID_USUARIO\"")
	private UserEntity user;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "\"ID_ROL\"")
	private RoleEntity role;

}
