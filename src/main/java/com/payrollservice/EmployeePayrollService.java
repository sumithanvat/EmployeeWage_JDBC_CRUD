package com.payrollservice;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeePayrollService {
    // JDBC driver and database URL
    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/payroll_service";

    // Database credentials
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Sumit@123";

    public static void main(String[] args) {
        try {
            // Define the date range for joining
            String startDate = "2023-01-01";
            String endDate = "2023-12-31";

            // Retrieve employee payroll data within the date range
            List<EmployeePayroll> employeePayrollList = getEmployeePayrollData(startDate, endDate);

            // Print the retrieved employee payroll data
            for (EmployeePayroll employeePayroll : employeePayrollList) {
                System.out.println(employeePayroll);
            }

        } catch (EmployeePayrollException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static List<EmployeePayroll> getEmployeePayrollData(String startDate, String endDate) throws EmployeePayrollException {
        List<EmployeePayroll> employeePayrollList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            // Register the JDBC driver
            Class.forName(JDBC_DRIVER);

            // Establish the connection to the database
            connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);

            // Prepare the SQL statement to retrieve employee payroll data within the date range
            String sql = "SELECT * FROM employee_payroll WHERE joining_date BETWEEN ? AND ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, startDate);
            preparedStatement.setString(2, endDate);

            // Execute the SQL query and retrieve the result set
            resultSet = preparedStatement.executeQuery();

            // Iterate through the result set and populate the employee payroll objects
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                double salary = resultSet.getDouble("salary");
                String joiningDate = resultSet.getString("joining_date");

                EmployeePayroll employeePayroll = new EmployeePayroll(id, name, salary, joiningDate);
                employeePayrollList.add(employeePayroll);
            }

        } catch (ClassNotFoundException | SQLException e) {
            throw new EmployeePayrollException("Error retrieving employee payroll data.", e);
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
}
