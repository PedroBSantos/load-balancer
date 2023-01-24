package com.load.balancer.entities;

public class Payment {

    private String workerName;
    private double dailyIncome;
    private int days;

    public Payment(String workerName, double dailyIncome, int days) {
        this.workerName = workerName;
        this.dailyIncome = dailyIncome;
        this.days = days;
    }

    public String getWorkerName() {
        return workerName;
    }

    public double getDailyIncome() {
        return dailyIncome;
    }

    public int getDays() {
        return days;
    }

    public double calculate() {
        return dailyIncome * days;
    }
}
