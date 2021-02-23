package com.roboto.clients.dao;

import java.sql.SQLException;
import java.util.List;

import com.roboto.clients.models.Client;

public interface IClientDao {
	
	public Client getClientById(Client request) throws SQLException;
	public List<Client> getClients();
	public Client createClient(Client client);
	public boolean isClientExists(Long id);

}
