package com.web.ui.automation.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.web.ui.automation.configurations.DatabaseConfigurations;

public class DatabaseUtil {

/**
 * This method used to load the oracle driver class.
 */
	private static void loadDriverClass() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
/**
 * This method used to get the connection URL to connect to the respective database.
 * @return
 */
	private static String getConnectionURL() {
		StringBuffer sb = new StringBuffer("jdbc:oracle:thin:@");
		sb.append(DatabaseConfigurations.getHostname());
		sb.append(":");
		sb.append(DatabaseConfigurations.getPort());
		sb.append(":");
		sb.append(DatabaseConfigurations.getSID());
		return sb.toString();
	}

	private static Connection getConnection() {
		Connection connection = null;
		try {
			loadDriverClass();
			connection = DriverManager.getConnection(getConnectionURL(), DatabaseConfigurations.getUsername(),
					DatabaseConfigurations.getPassword());
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return connection;
	}
/**
 * This method used to insert the data into the database.
 * @param excelFilePathWithInsertRecords
 */
	public static void insertDataIntoDB(String excelFilePathWithInsertRecords) {
		Connection connection = null;
		try {
			connection = getConnection();
			connection.setAutoCommit(false);
			ExcelUtil excelUtil = new ExcelUtil(excelFilePathWithInsertRecords);
			List<String> insertStatements = excelUtil.getInsertSatementList();
			for (String statement : insertStatements) {
				System.out.print(statement);
				Statement sqlStatement = connection.createStatement();
				sqlStatement.executeQuery(statement);
				System.out.println(" statement executed successfully.");
			}
			connection.commit();
			System.out.println("Committed successfully");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (connection != null)
				try {
					connection.close();
					System.out.println("Connection object released successfully.");
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}

	}
/**
 * This method used to delete the data from the database.
 * @param excelFilePathWithDeletedRecords
 */
	public static void deleteDataFromDB(String excelFilePathWithDeletedRecords) {
		Connection connection = null;
		try {
			connection = getConnection();
			connection.setAutoCommit(false);
			ExcelUtil excelUtilDel = new ExcelUtil(excelFilePathWithDeletedRecords);
			List<String> deleteStatements = excelUtilDel.getDeleteSatementList();
			for (String statement : deleteStatements) {
				System.out.print(statement);
				Statement sqlStatement = connection.createStatement();
				sqlStatement.executeQuery(statement);
				System.out.println(" statement executed successfully.");
			}
			connection.commit();
			System.out.println("Committed successfully");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (connection != null)
				try {
					connection.close();
					System.out.println("Connection object released successfully.");
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}

	}

}
