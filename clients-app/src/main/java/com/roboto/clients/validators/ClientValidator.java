package com.roboto.clients.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.roboto.clients.models.Client;

@Component
public class ClientValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Client.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		if(errors.hasErrors())
			return;
		
		Client client = (Client) target;
		validateClient(errors, client);
	}
	
	private void validateClient(Errors errors, Client client) {
		
		if(client.getName() == "") {
			errors.reject("name", "name should not be empty");
			return;
		}
		return;
	}

}
