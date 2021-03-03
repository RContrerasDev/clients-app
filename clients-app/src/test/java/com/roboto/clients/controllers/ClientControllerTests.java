package com.roboto.clients.controllers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.roboto.clients.controllers.ClientControllerTests.Config;
import com.roboto.clients.dao.ClientDao;
import com.roboto.clients.models.Client;
import com.roboto.clients.services.ClientService;
import com.roboto.clients.validators.ClientValidator;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = { ClientController.class })
@Import(Config.class)
@WebMvcTest(ClientController.class)
public class ClientControllerTests {

	@MockBean
	private ClientController clientController;

	@MockBean
	private ClientService clientService;

	@MockBean
	private ClientDao clientDao;

	@MockBean
	private JdbcTemplate jdbcTemplate;

	@Autowired
	@MockBean
	private WebApplicationContext webApplicationContext;

	@Autowired
	private MockMvc mockMvc;
	
	private String clientJson;

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		ObjectMapper objectMapper = new ObjectMapper();
        try {
            clientJson = objectMapper.writeValueAsString(getClientForTest());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
	}

	@Test
	public void getClientByIdTest() throws Exception {
		Client client = getClientForTest();
		//when(clientController.getClientById(client)).thenReturn(new ResponseEntity<Client>(HttpStatus.OK));
		when(clientService.getClientById(Mockito.any(Client.class))).thenReturn(client);
		ResponseEntity resp = new ResponseEntity<Client>(client, HttpStatus.OK);
		@SuppressWarnings("deprecation")
		final ResultActions result = this.mockMvc.perform(post("/api/client")
				.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
				.content(clientJson));
		result.andDo(MockMvcResultHandlers.print());
		//result.andExpect(content().contentType("application/json;charset=UTF-8"));
		result.andExpect(status().isOk());
		Client response = clientService.getClientById(client);
		assertEquals(client.getId(), response.getId());
		assertEquals(client.getName(), response.getName());
		assertEquals(client.getLastName(), response.getLastName());
		assertEquals(client.getPosition(), response.getPosition());
		
	}
	
	@Test
	public void getClientById() throws Exception {
		Client client = getClientForTest();
		when(clientService.getClientById(client)).thenReturn(client);
		@SuppressWarnings("deprecation")
		MvcResult result = (MvcResult) this.mockMvc.perform(post("/api/client")
				.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
				.content(clientJson))
		.andDo(print())
		.andExpect(status().isOk())
		.andReturn();
		
		
		Client response = clientService.getClientById(client);
		assertEquals(client.getId(), response.getId());
		assertEquals(client.getName(), response.getName());
		assertEquals(client.getLastName(), response.getLastName());
		assertEquals(client.getPosition(), response.getPosition());
		
	}
	
	@Test
	public void getClientsTest() throws Exception {
		List<Client> clientsList = new ArrayList<Client>();
		clientsList.add(getClientForTest());
		when(clientService.getClients()).thenReturn(clientsList);
		List<Client> response = clientService.getClients();
		final ResultActions result = this.mockMvc.perform(get("/api/list"));
		result.andExpect(status().isOk());
		assertEquals(clientsList.get(0), response.get(0));
		assertEquals(clientsList.get(0).getId(), response.get(0).getId());
		assertEquals(clientsList.get(0).getName(), response.get(0).getName());
		assertEquals(clientsList.get(0).getLastName(), response.get(0).getLastName());
		assertEquals(clientsList.get(0).getPosition(), response.get(0).getPosition());

		
	}
	
	@Test
	public void updateClientTest() throws Exception {
		Client client = getClientForTest();
		when(clientService.updateClient(client)).thenReturn(client);
		final ResultActions result = this.mockMvc.perform(put("/api/update")
				.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
				.content(clientJson));
		result.andReturn();
		result.andDo(print());
		result.andExpect(status().isOk());

		Client response = clientService.updateClient(client);
		assertEquals(client.getId(), response.getId());
		assertEquals(client.getName(), response.getName());
		assertEquals(client.getLastName(), response.getLastName());
		assertEquals(client.getPosition(), response.getPosition());
	}
	
	@Test
	public void deleteClientTest() throws Exception {
		Client client = new Client();
		client.setId(1L);
		final ResultActions result = this.mockMvc.perform(delete("/api/delete")
				.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
				.content(clientJson));
		result.andReturn();
		result.andDo(print());
		result.andExpect(status().isOk());
	}
	
	public Client getClientForTest() {
		
		Client client = new Client();
		client.setId(1L);
		client.setName("Roboto");
		client.setLastName("Contreras");
		client.setPosition("Developer");
		
		return client;
	}

	@TestConfiguration
	protected static class Config {

		@Bean
		public ClientValidator clientValidator() {
			return Mockito.mock(ClientValidator.class);
		}

	}

}
