package ar.com.playmedia.controller;
import ar.com.playmedia.helper.ConnectionManager;
import ar.com.playmedia.helper.Console;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;

public class Platform {   

    public void insert(String platform) {		
		String queryString = String.format (
			"SELECT platform('%s')",
			platform
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
			"select * from search_platform_if_contains('%s')",
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
				System.out.println("No se encontraron plataformas");
			}
			

			 result.close();
			 dbConnection.close();
		} catch (Exception e) { //To TEST
			Console.showMessage(e.getMessage());
		}
	}

	public void delete(Integer id){
		String queryString = String.format (
			"SELECT platform_destroy(%s)",
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
			"SELECT platform_set_description(%s,'%s')",
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
			"SELECT platform_get_description(%s)",
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

}