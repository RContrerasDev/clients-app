package com.roboto.clients;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

//@EnableAspectJAutoProxy
@SpringBootApplication
public class ClientsAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClientsAppApplication.class, args);
	}

}
