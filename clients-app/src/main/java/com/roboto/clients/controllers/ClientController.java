package com.roboto.clients.controllers;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.roboto.clients.models.Client;
import com.roboto.clients.services.ClientService;
import com.roboto.clients.validators.ClientValidator;

@RestController
@RequestMapping( value = "/api")
public class ClientController {
	
	@Autowired
	private ClientService clientService;
	
	@Autowired
	@Qualifier("clientValidator")
	private ClientValidator clientValidator;
	
	@InitBinder("client")
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(clientValidator);
	}
	
	private static final Logger log = LoggerFactory.getLogger(ClientController.class);

	@RequestMapping(value = "/client", method = RequestMethod.POST)
	public ResponseEntity<Client> getClientById(@RequestBody Client request) {
		Client client = clientService.getClientById(request);
		log.info(request.getName());
		if(client != null) {
			return new ResponseEntity<Client>(client, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
	}
	
	@GetMapping("/")
	@ResponseStatus(HttpStatus.OK)
	public List<Client> getClients() throws Exception {
		return clientService.getClients();
	}
	
//	@GetMapping("/")
//	public String sayHello() {
//		return "Hello World Spring!";
//	}
	
	
	@PostMapping("/create")
	public ResponseEntity<Client> createClient(@Valid @RequestBody Client client) {
		Client response = clientService.createClient(client);
		return new ResponseEntity<Client>(response, HttpStatus.OK);
	}

}
