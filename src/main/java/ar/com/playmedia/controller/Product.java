package ar.com.playmedia.controller;

import java.sql.Connection;
import java.util.ArrayList;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;

public class Product {
	private Connection dbConnection;
	private Statement query;
	private ResultSet result;
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

	public void delete(Integer id) {
		String queryString = String.format (
			"SELECT  product_destroy(%s);",
			id
		);

		try {
			query = dbConnection.createStatement();
			query.execute(queryString);
			query.close();
		} catch (Exception e) {
			System.out.println("ERROR: " + e);
		}
	}

	public ArrayList<ar.com.playmedia.model.Product> search() {
		ArrayList<ar.com.playmedia.model.Product> productList = 
			new ArrayList<ar.com.playmedia.model.Product>();
		
		String queryString = "SELECT * from product_search()";

		try {
			query = dbConnection.createStatement();
			result = query.executeQuery(queryString);

			while(result.next()) {
				ar.com.playmedia.model.Product product = new ar.com.playmedia.model.Product (
					Integer.parseInt(result.getString(1)),
					result.getString(2),
					Float.parseFloat(result.getString(3)),
					Integer.parseInt(result.getString(4)),
					Integer.parseInt(result.getString(5)),
					Integer.parseInt(result.getString(6))
				);

				productList.add(product);
			}

			query.close();
		} catch (Exception e) {
			System.out.println("ERROR: " + e);
		}

		return productList;
	}

	public ArrayList<ar.com.playmedia.model.Product> search(String filter) {
		ArrayList<ar.com.playmedia.model.Product> productList = 
			new ArrayList<ar.com.playmedia.model.Product>();
		
		String queryString = String.format("SELECT * from product_search('%s')", filter);

		try {
			query = dbConnection.createStatement();
			result = query.executeQuery(queryString);

			while(result.next()) {
				ar.com.playmedia.model.Product product = new ar.com.playmedia.model.Product (
					Integer.parseInt(result.getString(1)),
					result.getString(2),
					Float.parseFloat(result.getString(3)),
					Integer.parseInt(result.getString(4)),
					Integer.parseInt(result.getString(5)),
					Integer.parseInt(result.getString(6))
				);

				productList.add(product);
			}

			query.close();
		} catch (Exception e) {
			System.out.println("ERROR: " + e);
		}

		return productList;
	}
		
		
	public ar.com.playmedia.model.Product identify(Integer id) {
		ar.com.playmedia.model.Product product = null;

		String queryString = String.format("SELECT * FROM product_identify(%s)", id);

		try {
			query = dbConnection.createStatement();
			result = query.executeQuery(queryString);

			while(result.next()) {
				product = new ar.com.playmedia.model.Product (
					Integer.parseInt(result.getString(1)),
					result.getString(2),
					Float.parseFloat(result.getString(3)),
					Integer.parseInt(result.getString(4)),
					Integer.parseInt(result.getString(5)),
					Integer.parseInt(result.getString(6))
				);
			}

			query.close();
		} catch (Exception e) {
			System.out.println("ERROR: " + e);
		}

		return product;
	}

	public ar.com.playmedia.model.Product setDescription (
		ar.com.playmedia.model.Product product, 
		String description
	) {
		String queryString = String.format (
			"SELECT * FROM product_set_description(%s, '%s')", 
			product.getId(),
			description
		);

		try {
			query = dbConnection.createStatement();
			query.execute(queryString);
			query.close();
		} catch (Exception e) {
			System.out.println("ERROR: " + e);
		}

		return identify(product.getId());
	}
	
	public ar.com.playmedia.model.Product setPrice (
		ar.com.playmedia.model.Product product, 
		Float price
	) {
		String queryString = String.format (
			"SELECT * FROM product_set_price(%s, %s)", 
			product.getId(),
			price
		);

		try {
			query = dbConnection.createStatement();
			query.execute(queryString);
			query.close();
		} catch (Exception e) {
			System.out.println("ERROR: " + e);
		}

		return identify(product.getId());
	}


	


	public ar.com.playmedia.model.Product setQuantity (
		ar.com.playmedia.model.Product product, 
		Integer quantity
	) {
		String queryString = String.format (
			"SELECT * FROM product_set_quantity(%s, '%s')", 
			product.getId(),
			quantity
		);

		try {
			query = dbConnection.createStatement();
			query.execute(queryString);
			query.close();
		} catch (Exception e) {
			System.out.println("ERROR: " + e);
		}

		return identify(product.getId());
	}

	public ar.com.playmedia.model.Product setCategory (
		ar.com.playmedia.model.Product product, 
		Integer category
	) {
		String queryString = String.format (
			"SELECT * FROM product_set_category(%s, '%s')", 
			product.getId(),
			category
		);

		try {
			query = dbConnection.createStatement();
			query.execute(queryString);
			query.close();
		} catch (Exception e) {
			System.out.println("ERROR: " + e);
		}

		return identify(product.getId());
	}

	public ar.com.playmedia.model.Product setPlatform (
		ar.com.playmedia.model.Product product, 
		Integer platform
	) {
		String queryString = String.format (
			"SELECT * FROM product_set_platform(%s, '%s')", 
			product.getId(),
			platform
		);

		try {
			query = dbConnection.createStatement();
			query.execute(queryString);
			query.close();
		} catch (Exception e) {
			System.out.println("ERROR: " + e);
		}

		return identify(product.getId());
	}

}