package ar.com.playmedia.helper;
import  java.sql.Connection;
import  java.sql.DriverManager;

public class ConnectionManager{
    private static String url = "jdbc:postgresql://127.0.0.1:5432/game_store";
	private static String username = "dba";
	private static String password = "12345678";  
	private static String driver = "org.postgresql.Driver";
    
	public static Connection connect() {	
		Connection dbConnection = null;
		try {
			Class.forName(driver);
			dbConnection = DriverManager.getConnection(url, username, password);
		} catch(Exception e) {
            Console.clean();
            Console.showMessage("Error al conectar con la base de datos. " + e.getMessage());
		}
		return dbConnection;
	}

	public static void disconnect(Connection dbConnection) {
		try {
			dbConnection.close();
		} catch(Exception e) {
			Console.clean();
            Console.showMessage("Error al cerrar la conexión de la base de datos. " + e.getMessage());
		}
	}

}