package com.roboto.clients.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.roboto.clients.models.Client;

@Component
public class ClientValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Client.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		Client client = (Client) target;
		
		if(client.getName().isEmpty()) {
			errors.rejectValue("name", "Name should not be empty");
		}
		
	}

}
