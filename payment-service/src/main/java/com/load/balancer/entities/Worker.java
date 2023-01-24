package com.load.balancer.entities;

@SuppressWarnings("unused")
public class Worker {
    
    private Integer id;
    private String name;
    private double dailyIncome;

    private Worker() { }
    
    public Worker(Integer id, String name, double dailyIncome) {
        this.id = id;
        this.name = name;
        this.dailyIncome = dailyIncome;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getDailyIncome() {
        return dailyIncome;
    }
}
