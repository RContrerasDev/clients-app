package com.roboto.clients.constants;

public interface IClientConstants {
	
	public static final String SQL_GET_CLIENT_BY_ID = 
			"SELECT * "
			+ "FROM client "
			+ "WHERE id = ?";
	public static final String SQL_GET_ALL_CLIENTS = 
			"SELECT * "
			+ "FROM client";
	public static final String SQL_CREATE_CLIENT = 
			"INSERT INTO client "
			+ "(id, "
			+ "name, "
			+ "lastName, "
			+ "position) "
			+ "VALUES (?,?,?,?)";
	public static final String SQL_CLIENT_EXISTS = 
			"SELECT COUNT(*) "
			+ "FROM client "
			+ "WHERE id = ?";
	public static final String SQL_UPDATE_CLIENT = 
			"UPDATE client "
			+ "SET name = ?, "
			+ "lastName = ?, "
			+ "position = ? "
			+ "WHERE id = ?";
	public static final String SQL_DELETE_BY_ID = 
			"DELETE "
			+ "FROM client "
			+ "WHERE id = ?";

}
