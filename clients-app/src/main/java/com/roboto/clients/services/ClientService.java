package com.roboto.clients.services;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.roboto.clients.dao.ClientDao;
import com.roboto.clients.models.Client;

@Service
public class ClientService {
	
	@Autowired
	private ClientDao clientDao;
	
	@Transactional(readOnly = true)
	public Client getClientById(Client request) {
		
		Client client = new Client();
		
		try {
			client = clientDao.getClientById(request);
			return client;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return client;
	}
	
	@Transactional(readOnly = true)
	public List<Client> getClients() throws Exception {
		return clientDao.getClients();
	}
	
	@Transactional
	public Client createClient(Client client) {
		return clientDao.createClient(client);
		//return client;
	}
	
	@Transactional
	public Client updateClient(Client client) {		
		return clientDao.updateClient(client);
	}
	
	@Transactional
	public void deleteClientById(Client client) {
		clientDao.deleteClient(client.getId());		
	}

}
