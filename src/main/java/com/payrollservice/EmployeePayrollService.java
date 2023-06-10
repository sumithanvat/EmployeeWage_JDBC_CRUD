package com.payrollservice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeePayrollService {
    // JDBC driver and database URL
    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/payroll_service";

    // Database credentials
    private static final String USERNAME = "your_username";
    private static final String PASSWORD = "your_password";

    public static void main(String[] args) {
        try {
            // Update the salary of Terisa in the database
            updateEmployeeSalary("Terisa", 3000000.00);

            // Retrieve employee payroll data
            List<EmployeePayroll> employeePayrollList = getEmployeePayrollData();

            // Print the retrieved employee payroll data
            for (EmployeePayroll employeePayroll : employeePayrollList) {
                System.out.println(employeePayroll);
            }

            // Compare Employee Payroll Object with DB to pass the JUnit test
            boolean passed = compareWithDatabase(employeePayrollList, "Terisa", 3000000.00);
            System.out.println("JUnit Test Passed: " + passed);

        } catch (EmployeePayrollException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void updateEmployeeSalary(String employeeName, double newSalary) throws EmployeePayrollException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            // Register the JDBC driver
            Class.forName(JDBC_DRIVER);

            // Establish the connection to the database
            connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);

            // Prepare the SQL statement to update the employee's salary
            String sql = "UPDATE employee_payroll SET salary = ? WHERE name = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setDouble(1, newSalary);
            preparedStatement.setString(2, employeeName);

            // Execute the SQL update statement
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected == 0) {
                throw new EmployeePayrollException("Employee not found  in the database");
            }

        } catch (ClassNotFoundException | SQLException e) {
            throw new EmployeePayrollException("Error updating employee salary.");
        } finally {
            // Close the database resources
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
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
    }

    public static List<EmployeePayroll> getEmployeePayrollData() throws EmployeePayrollException {
        List<EmployeePayroll> employeePayrollList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            // Register the JDBC driver
            Class.forName(JDBC_DRIVER);

            // Establish the connection to the database
            connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);

            // Prepare the SQL statement to retrieve employee payroll data
            String sql = "SELECT * FROM employee_payroll";
            preparedStatement = connection.prepareStatement(sql);

            // Execute the SQL query and retrieve the result set
            resultSet = preparedStatement.executeQuery();

            // Iterate through the result set and populate the employee payroll objects
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                double salary = resultSet.getDouble("salary");

                EmployeePayroll employeePayroll = new EmployeePayroll(id, name, salary);
                employeePayrollList.add(employeePayroll);
            }

        } catch (ClassNotFoundException | SQLException e) {
            throw new EmployeePayrollException("Error retrieving employee payroll data.");
        } finally {
            // Close the database resources
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
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

        return employeePayrollList;
    }

    public static boolean compareWithDatabase(List<EmployeePayroll> employeePayrollList, String employeeName, double salary) {
        for (EmployeePayroll employeePayroll : employeePayrollList) {
            if (employeePayroll.getName().equals(employeeName) && employeePayroll.getSalary() == salary) {
                return true;
            }
        }
        return false;
    }
}
