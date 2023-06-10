package com.payrollservice;

public class EmployeeStatistics {
        private double maleSum;
        private double maleAverage;
        private double maleMin;
        private double maleMax;
        private int maleCount;
        private double femaleSum;
        private double femaleAverage;
        private double femaleMin;
        private double femaleMax;
        private int femaleCount;

        // Getters and setters

        public double getMaleSum() {
            return maleSum;
        }

        public void setMaleSum(double maleSum) {
            this.maleSum = maleSum;
        }

        public double getMaleAverage() {
            return maleAverage;
        }

        public void setMaleAverage(double maleAverage) {
            this.maleAverage = maleAverage;
        }

        public double getMaleMin() {
            return maleMin;
        }

        public void setMaleMin(double maleMin) {
            this.maleMin = maleMin;
        }

        public double getMaleMax() {
            return maleMax;
        }

        public void setMaleMax(double maleMax) {
            this.maleMax = maleMax;
        }

        public int getMaleCount() {
            return maleCount;
        }

        public void setMaleCount(int maleCount) {
            this.maleCount = maleCount;
        }

        public double getFemaleSum() {
            return femaleSum;
        }

        public void setFemaleSum(double femaleSum) {
            this.femaleSum = femaleSum;
        }

        public double getFemaleAverage() {
            return femaleAverage;
        }

        public void setFemaleAverage(double femaleAverage) {
            this.femaleAverage = femaleAverage;
        }

        public double getFemaleMin() {
            return femaleMin;
        }

        public void setFemaleMin(double femaleMin) {
            this.femaleMin = femaleMin;
        }

        public double getFemaleMax() {
            return femaleMax;
        }

        public void setFemaleMax(double femaleMax) {
            this.femaleMax = femaleMax;
        }

        public int getFemaleCount() {
            return femaleCount;
        }

        public void setFemaleCount(int femaleCount) {
            this.femaleCount = femaleCount;
        }
    }

    class EmployeePayrollException extends Exception {
        public EmployeePayrollException(String message, Throwable cause) {
            super(message, cause);
        }
    }

