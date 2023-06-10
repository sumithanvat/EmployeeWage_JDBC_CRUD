package com.payrollservice;

class EmployeePayrollException extends Exception {
    public EmployeePayrollException(String message) {
        super(message, cause);
    }
}