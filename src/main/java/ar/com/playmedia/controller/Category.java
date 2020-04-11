package ar.com.playmedia.controller;
import ar.com.playmedia.helper.ConnectionManager;
import ar.com.playmedia.helper.Console;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;

public class Category {   

    public void insert(String category) {		
		String queryString = String.format (
			"SELECT category('%s')",
			category
		);

		try {
            Connection dbConnection = ConnectionManager.connect();
			Statement query = dbConnection.createStatement();
			query.execute(queryString);
			query.close();
		
			dbConnection.close();
		} catch (Exception e) { 
			Console.showMessage(e.getMessage());
		}
	}

	public void search(String word){
		String queryString = String.format (
			"select * from search_category_if_contains('%s')",
			word
		);

		try {
			Connection dbConnection = ConnectionManager.connect();
			Statement query = dbConnection.createStatement();
			query = dbConnection.createStatement();
			ResultSet result = query.executeQuery(queryString);

			if (result != null) 
			{
			 System.out.printf("%-22s %-22s\n", "Id","Descripción");
			

			 while(result.next()){
				System.out.printf("%-22s %-22s\n", result.getString("id"),result.getString("description"));
			 }
			}
			else{
				System.out.println("No se encontraron categorias");
			}
			

			 result.close();
			 dbConnection.close();
		} catch (Exception e) { //To TEST
			Console.showMessage(e.getMessage());
		}
	}

	public void delete(Integer id){
		String queryString = String.format (
			"SELECT category_destroy(%s)",
			id
		);

		try {
            Connection dbConnection = ConnectionManager.connect();
			Statement query = dbConnection.createStatement();
			query.execute(queryString);
			query.close();
		
			dbConnection.close();
		} catch (Exception e) { 
			Console.showMessage(e.getMessage());
		}

	}
	public void modify(Integer id,String description){
		String queryString = String.format (
			"SELECT category_set_description(%s,'%s')",
			id,
			description
		);

		try {
            Connection dbConnection = ConnectionManager.connect();
			Statement query = dbConnection.createStatement();
			query.execute(queryString);
			query.close();
		
			dbConnection.close();
		} catch (Exception e) { 
			Console.showMessage(e.getMessage());
		}

	}

	public boolean exist(Integer id){
		String queryString = String.format (
			"SELECT category_get_description(%s)",
			id
		);
	    boolean exist = false;
		try {
            Connection dbConnection = ConnectionManager.connect();
			Statement query = dbConnection.createStatement();
			ResultSet result = query.executeQuery(queryString);
			 			 
			if ( result.next()) /*return false if there are no rows */
				exist = true;
			
			result.close();		
			dbConnection.close();
			
		} catch (Exception e) { 
			Console.showMessage(e.getMessage());
		}
		return exist;
	}

	public String getCategoryDescription(Integer id){
		String queryString = String.format (
			"SELECT category_get_description(%s)",
			id);
		String description="";
		try {
			Connection dbConnection = ConnectionManager.connect();
			Statement query = dbConnection.createStatement();
			ResultSet result = query.executeQuery(queryString);
			if (result.next()){
				description = result.getString(1);
			}
				query.close();
		
			dbConnection.close();
		} catch (Exception e) { 
			Console.showMessage(e.getMessage());
		}
		return description;
	}

}