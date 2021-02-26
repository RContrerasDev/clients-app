package com.roboto.clients.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

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

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void getClientByIdTest() throws Exception {
		Client client = getClientForTest();
		ObjectMapper mapper = new ObjectMapper();
		when(clientService.getClientById(client)).thenReturn(client);
		@SuppressWarnings("deprecation")
		final ResultActions result = this.mockMvc.perform(post("/api/client")
				.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
				.content(mapper.writeValueAsString(client)));
		result.andReturn();
		result.andDo(print());
		result.andExpect(status().isOk());
		Client response = clientService.getClientById(client);
		assertEquals(client.getId(), response.getId());
		assertEquals(client.getName(), response.getName());
		assertEquals(client.getLastName(), response.getLastName());
		assertEquals(client.getPosition(), response.getPosition());
		
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
