package com.load.balancer.usecases.payment.inputs;

public class CalculatePaymentInput {
    
    private Integer workerId;
    private int days;

    public CalculatePaymentInput(Integer workerId, int days) {
        this.workerId = workerId;
        this.days = days;
    }

    public int getDays() {
        return days;
    }

    public Integer getWorkerId() {
        return workerId;
    }
}
