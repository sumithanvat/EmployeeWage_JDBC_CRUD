package com.payrollservice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PayrollService {

    public static void main(String[] args) {
        // JDBC driver and database URL
        String jdbcDriver = "com.mysql.jdbc.Driver";
        String databaseUrl = "jdbc:mysql://localhost:3306/payroll_service";

        // Database credentials
        String username = "root";
        String password = "Sumit@123";

        Connection connection = null;

        try {
            // Register the JDBC driver
            Class.forName(jdbcDriver);

            // Establish the connection to the database
            connection = DriverManager.getConnection(databaseUrl, username, password);

            // Check if the database connection is successful
            if (connection != null) {
                System.out.println("Database connection established successfully.");

                // Perform database operations here
                // ...

            } else {
                System.out.println("Failed to connect to the database.");
            }

        } catch (ClassNotFoundException e) {
            System.out.println("JDBC driver not found.");
            e.printStackTrace();

        } catch (SQLException e) {
            System.out.println("Database connection error.");
            e.printStackTrace();

        } finally {
            // Close the database connection
            if (connection != null) {
                try {
                    connection.close();
                    System.out.println("Database connection closed.");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}