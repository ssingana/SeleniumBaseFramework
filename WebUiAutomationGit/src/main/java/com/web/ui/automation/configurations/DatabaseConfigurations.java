package com.web.ui.automation.configurations;

import java.io.IOException;
import java.util.Properties;

public class DatabaseConfigurations {

	public static Properties databaseProperties = new Properties();

	static {
		loadDatabaseProperties();
	}

	private static void loadDatabaseProperties() {
		if (databaseProperties.isEmpty()) {
			try {
				databaseProperties
						.load(DatabaseConfigurations.class.getResourceAsStream("/config/database.properties"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static String getHostname() {
		return databaseProperties.getProperty("hostname");
	}

	public static String getPort() {
		return databaseProperties.getProperty("port");
	}

	public static String getSID() {
		return databaseProperties.getProperty("sid");
	}

	public static String getUsername() {
		return databaseProperties.getProperty("username");
	}

	public static String getPassword() {
		return databaseProperties.getProperty("password");
	}

	public static String getDbname() {
		return databaseProperties.getProperty("dbname");
	}

}
