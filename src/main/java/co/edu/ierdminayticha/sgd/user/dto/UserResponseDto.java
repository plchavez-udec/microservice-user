package co.edu.ierdminayticha.sgd.user.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class UserResponseDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private Date creationDate;
	private Date lastModifiedDate;

}
