package co.edu.ierdminayticha.sgd.user.entity;

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
@Table(name = "\"ROLES\"")
public class RoleEntity {

	@Id
	@SequenceGenerator(name = "\"SEQ_ROLES_ID\"", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "\"SEQ_ROLES_ID\"")
	@Column(name = "\"ID\"")
	private Long id;
	@Column(name = "\"NOMBRE\"")
	private String nombre;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "role")
	private List<UserRoleEntity> listUserRole;

}
