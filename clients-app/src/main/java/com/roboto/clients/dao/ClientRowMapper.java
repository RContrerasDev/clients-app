package com.roboto.clients.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.roboto.clients.models.Client;

public class ClientRowMapper implements RowMapper<Client>{

	@Override
	public Client mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Client client = new Client();
		client.setId(rs.getLong("id"));
		client.setName(rs.getString("name"));
		client.setLastName(rs.getString("lastName"));
		client.setPosition(rs.getString("position"));
		
		return client;
	}

}
