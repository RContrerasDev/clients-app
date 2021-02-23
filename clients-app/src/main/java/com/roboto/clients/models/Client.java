package com.roboto.clients.models;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_EMPTY)
public class Client implements Serializable{
	
	private static final long serialVersionUID = 5117246185518184155L;
	
	private Long id;
	@NotEmpty(message = "Name should not be empty")
	@NotNull
	private String name;
	@NotEmpty(message = "LastName should not be empty")
	@NotNull
	private String lastName;
	@NotEmpty(message = "Position should not be empty")
	@NotNull
	private String position;
	
	public Client() {}
	
	public Client(Long id, String name, String lastName, String position) {
		this.id = id;
		this.name = name;
		this.lastName = lastName;
		this.position = position;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	
	

}
