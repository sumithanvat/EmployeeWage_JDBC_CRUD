package com.payrollservice;
import java.sql.*;

public class EmployeePayrollService {
    // JDBC driver and database URL
    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/payroll_service";

    // Database credentials
    private static final String USERNAME = "your_username";
    private static final String PASSWORD = "your_password";

    public static void main(String[] args) {
        try {
            // Retrieve employee statistics
            EmployeeStatistics employeeStatistics = getEmployeeStatistics();

            // Print the employee statistics
            System.out.println("Male Employees:");
            System.out.println("Sum of Salaries: " + employeeStatistics.getMaleSum());
            System.out.println("Average Salary: " + employeeStatistics.getMaleAverage());
            System.out.println("Minimum Salary: " + employeeStatistics.getMaleMin());
            System.out.println("Maximum Salary: " + employeeStatistics.getMaleMax());
            System.out.println("Count: " + employeeStatistics.getMaleCount());

            System.out.println();

            System.out.println("Female Employees:");
            System.out.println("Sum of Salaries: " + employeeStatistics.getFemaleSum());
            System.out.println("Average Salary: " + employeeStatistics.getFemaleAverage());
            System.out.println("Minimum Salary: " + employeeStatistics.getFemaleMin());
            System.out.println("Maximum Salary: " + employeeStatistics.getFemaleMax());
            System.out.println("Count: " + employeeStatistics.getFemaleCount());

        } catch (EmployeePayrollException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static EmployeeStatistics getEmployeeStatistics() throws EmployeePayrollException {
        EmployeeStatistics employeeStatistics = new EmployeeStatistics();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            // Register the JDBC driver
            Class.forName(JDBC_DRIVER);

            // Establish the connection to the database
            connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);

            // Create a statement
            statement = connection.createStatement();

            // Execute queries for male employees
            String maleQuery = "SELECT SUM(salary), AVG(salary), MIN(salary), MAX(salary), COUNT(*) FROM employee_payroll WHERE gender = 'M' GROUP BY gender";
            resultSet = statement.executeQuery(maleQuery);
            if (resultSet.next()) {
                employeeStatistics.setMaleSum(resultSet.getDouble(1));
                employeeStatistics.setMaleAverage(resultSet.getDouble(2));
                employeeStatistics.setMaleMin(resultSet.getDouble(3));
                employeeStatistics.setMaleMax(resultSet.getDouble(4));
                employeeStatistics.setMaleCount(resultSet.getInt(5));
            }

            // Execute queries for female employees
            String femaleQuery = "SELECT SUM(salary), AVG(salary), MIN(salary), MAX(salary), COUNT(*) FROM employee_payroll WHERE gender = 'F' GROUP BY gender";
            resultSet = statement.executeQuery(femaleQuery);
            if (resultSet.next()) {
                employeeStatistics.setFemaleSum(resultSet.getDouble(1));
                employeeStatistics.setFemaleAverage(resultSet.getDouble(2));
                employeeStatistics.setFemaleMin(resultSet.getDouble(3));
                employeeStatistics.setFemaleMax(resultSet.getDouble(4));
                employeeStatistics.setFemaleCount(resultSet.getInt(5));
            }

        } catch (ClassNotFoundException | SQLException e) {
            throw new EmployeePayrollException("Error retrieving employee statistics.", e);
        } finally {
            // Close the database resources
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return employeeStatistics;
    }
}