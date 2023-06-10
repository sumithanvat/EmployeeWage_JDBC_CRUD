package com.payrollservice;

class EmployeePayroll {
    private int id;
    private String name;
    private double salary;
    private String joiningDate;

    public EmployeePayroll(int id, String name, double salary, String joiningDate) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.joiningDate = joiningDate;
    }

    // Getters and setters for id, name, salary, and joiningDate

    @Override
    public String toString() {
        return "EmployeePayroll [id=" + id + ", name=" + name + ", salary=" + salary + ", joiningDate=" + joiningDate + "]";
    }
}