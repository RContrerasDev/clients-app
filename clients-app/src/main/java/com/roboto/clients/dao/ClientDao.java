package com.roboto.clients.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.roboto.clients.constants.IClientConstants;
import com.roboto.clients.models.Client;

@Component
public class ClientDao implements IClientDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@SuppressWarnings("deprecation")
	@Override
	public Client getClientById(Client request) throws SQLException {
		
		Client client = new Client();
						
			client = jdbcTemplate.queryForObject(
					IClientConstants.SQL_GET_CLIENT_BY_ID, 
					new Object[] {request.getId()}, 
					(rs, rowNum) -> 
						new Client(
							rs.getLong("id"),
							rs.getString("name"),
							rs.getString("lastName"),
							rs.getString("position")
							)
						);
			
		
		return client;
	}

	@Override
	public List<Client> getClients() {
		return jdbcTemplate.query(
				IClientConstants.SQL_GET_ALL_CLIENTS, 
				new ClientRowMapper());
	}

	@Override
	public Client createClient(Client client) {
		
		System.out.println("ROBOTO:: Client name: " + client.getName());
		
		jdbcTemplate.update(
				IClientConstants.SQL_CREATE_CLIENT, 
				new Object[] {
						client.getId(),
						client.getName(),
						client.getLastName(),
						client.getPosition()});
		return client;
	}

	@SuppressWarnings("deprecation")
	@Override
	public boolean isClientExists(Long id) {
		
		Integer count = jdbcTemplate.queryForObject(
				IClientConstants.SQL_CLIENT_EXISTS, 
				new Object[] {id}, Integer.class);
		
		return count > 0 ? Boolean.TRUE : Boolean.FALSE;
		
	}
	
	

}
