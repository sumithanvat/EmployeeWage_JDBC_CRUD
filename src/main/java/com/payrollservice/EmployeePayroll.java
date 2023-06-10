package com.payrollservice;

class EmployeePayroll {
    private int id;
    private String name;
    private double salary;

    public EmployeePayroll(int id, String name, double salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    // Getters and setters for id, name, and salary

    @Override
    public String toString() {
        return "EmployeePayroll [id=" + id + ", name=" + name + ", salary=" + salary + "]";
    }
}