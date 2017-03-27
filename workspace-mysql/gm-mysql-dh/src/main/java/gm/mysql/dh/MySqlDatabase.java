package gm.mysql.dh;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;

public class MySqlDatabase {
	private String hostname;
	private String port;
	private String dbname;
	private String username;
	private String password;
	private Connection connection;
	
	public MySqlDatabase() {}

	public MySqlDatabase(String hostname, String port, String dbname,
			String username, String password) {
		this.hostname = hostname;
		this.port = port;
		this.dbname = dbname;
		this.username = username;
		this.password = password;
		this.connection = null;
		
		System.out.println ("INFO - in class MySqlDatabase, created the db object");
	}
	
	public void createDb(String rootUsername, String rootPassword) {
		
		System.out.println ("INFO - just entered createDb method in MySqlDatabase class");
		
		// Create connection
		try {
			System.out.println("INFO - trying to connect as root");
			
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://" + hostname	+ ":" + port +
					"/" +  "information_schema", rootUsername, rootPassword);

			System.out.println("INFO - connected as root");
			
		} catch (SQLException e) {
			System.out.println(e.getLocalizedMessage());
		} catch (ClassNotFoundException e) {
			System.out.println(e.getLocalizedMessage());
		}

		// Create database
		Statement statement = null;
		String sqlCreateDB   = "create database if not exists " + dbname;
		String sqlCreateUserLocal = "CREATE USER IF NOT EXISTS '" + username + "'@'localhost' identified by ' " + password + "'";
		String sqlCreateUserRemote = "CREATE USER IF NOT EXISTS '" + username + "'@'%' identified by ' " + password + "'";
		String grantsLocal = "GRANT ALL PRIVILEGES ON " + dbname + ".* to '" + username + "'@'localhost'";
		String grantsRemote = "GRANT ALL PRIVILEGES ON " + dbname + ".* to '" + username + "'@'%'";
		
		try {
			statement = connection.createStatement();
			System.out.println("Statement in create-db status is closed?: " + statement.isClosed());
			System.out.println("Running sqlCreateDB: " + sqlCreateDB);
			statement.executeUpdate(sqlCreateDB);
			System.out.println("Running sqlCreateUserLocal");
			statement.execute(sqlCreateUserLocal);
			System.out.println("Running sqlCreateUserRemote");
			statement.execute(sqlCreateUserRemote);
			System.out.println("Running grantsLocal");
			statement.execute(grantsLocal);
			System.out.println("Running grantsRemote");
			statement.execute(grantsRemote);
			connection.close();
			
		} catch (SQLException e1) {
			System.out.println(e1.getLocalizedMessage());
		}
	}

	public Connection openConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://" + hostname
					+ ":" + port + "/" + dbname, username, password);
		} catch (SQLException e) {
			System.out.println(e.getLocalizedMessage());
		} catch (ClassNotFoundException e) {
			System.out.println(e.getLocalizedMessage());
		}
		return connection;
	}

	public void closeConnection() {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				System.out.println("ERR - Error closing the MySQL Connection!");
				System.out.println(e.getLocalizedMessage());
			}
		}
	}

	public Connection getConnection() {
		return connection;
	}
	
	public String getHostname(){
		return hostname;
	}
	
	public String getPort(){
		return port;
	}
	
	public String getUsername(){
		return username;
	}
	
	public String getPassword(){
		return password;
	}
	
	public String getDbname(){
		return dbname;
	}
	
	public MySqlDataHandler getDataHandler() {
		return new MySqlDataHandler(this);
	}
}
