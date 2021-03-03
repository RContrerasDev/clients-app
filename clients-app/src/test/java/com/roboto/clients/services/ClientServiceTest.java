package com.roboto.clients.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.roboto.clients.dao.ClientDao;
import com.roboto.clients.models.Client;

public class ClientServiceTest {
	
	@InjectMocks
	ClientService clientService;
	
	@Mock
	ClientDao clientDao;
	
	@Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }
	
	@Test
	public void getClientsTest() throws Exception {
		
		List<Client> clientList = new ArrayList<Client>();
		Client client1 = new Client(1L, "Roberto", "Contreras", "Developer");
		clientList.add(client1);
		
		when(clientDao.getClients()).thenReturn(clientList);		
		List<Client> result = clientService.getClients();		
		assertEquals(1, result.size());
		assertThat(!result.isEmpty());
		assertEquals(result.get(0).getId(), Long.valueOf(1));
		assertEquals(result.get(0).getName(), "Roberto");
		assertEquals(result.get(0).getLastName(), "Contreras");
		assertEquals(result.get(0).getPosition(), "Developer");		
		
	}

}
