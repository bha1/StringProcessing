package org.coenraets.directory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionHelper {
	private String url;
	public static Connection instance;

	private ConnectionHelper() {
		String driver = null;
		try {
			Class.forName("org.sqlite.JDBC");
			instance = DriverManager.getConnection("jdbc:sqlite:test.db");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Connection getConnection() throws SQLException {
		if (instance == null) {
			new ConnectionHelper();
		}
		return instance;
	}

	public static void close(Connection connection) {
		try {
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}