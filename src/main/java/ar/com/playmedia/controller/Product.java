package ar.com.playmedia.controller;

import java.sql.Connection;
import java.util.ArrayList;
import java.sql.DriverManager;
import java.sql.Statement;

public class Product {
	private Connection dbConnection;
	private Statement query;
    private String url;
	private String username;
	private String password;

    public Product() {
		url = "jdbc:postgresql://127.0.0.1:5432/game_store";
		username = "dba";
		password = "1234";
	}

	public void connect() {	
		try {
			Class.forName("org.postgresql.Driver");
			dbConnection = DriverManager.getConnection(url, username, password);
		} catch(Exception e) {
			System.out.println("ERROR: " + e);
		}
	}


	public void disconnect() {
		try {
			dbConnection.close();
		} catch(Exception e) {
			System.out.println(e);
		}
	}

	public void insert(ar.com.playmedia.model.Product product) {
		String queryString = String.format (
			"SELECT product('%s', %s, %s, %s, %s)",
			product.getDescription(),
			product.getPrice(),
			product.getQuantity(),
			product.getCategory(),
			product.getPlatform()
		);

		try {
			query = dbConnection.createStatement();
			query.execute(queryString);
			query.close();
		} catch (Exception e) {
			System.out.println("ERROR: " + e);
		}
	}
}