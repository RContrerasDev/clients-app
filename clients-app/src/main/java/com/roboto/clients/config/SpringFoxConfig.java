package com.roboto.clients.config;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SpringFoxConfig {
	
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.roboto.clients"))
				.paths(PathSelectors.any())
				.build()
				.apiInfo(apiInfo());				
	}
	
	private ApiInfo apiInfo() {
	    return new ApiInfo(
	      "Roboto test API", 
	      "This is a test API.", 
	      "API TOS", 
	      "Terms of service", 
	      new Contact("Roberto Contreras", "www.roboto.com", "rcarlos.javadeve@gmail.com"), 
	      "License of API", "API license URL", Collections.EMPTY_LIST);
	}

}
