package co.edu.ierdminayticha.sgd.user.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "\"REFERENCIA\"")
public class UserEntity {

	@Id
	@SequenceGenerator(name = "\"SEQ_REFERENCIA_ID\"", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "\"SEQ_REFERENCIA_ID\"")
	@Column(name = "\"ID\"")
	private Long id;

	/*
	 * Aquí se ubican cada uno de los campos asociados a la tabla
	 */
	
	@Column(name = "\"FECHA_CREACION\"")
	private Date creationDate;
	@Column(name = "\"FECHA_MODIFICACION\"")
	private Date lastModifiedDate;

}
