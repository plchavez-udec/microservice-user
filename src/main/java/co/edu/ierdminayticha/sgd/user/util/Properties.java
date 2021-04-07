package co.edu.ierdminayticha.sgd.user.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Properties {
	
	@Value("${url.microservice.trd.find-by-id}")
	private String urlGetTrdById;

	public String getUrlGetTrdById() {
		return urlGetTrdById;
	}

}
