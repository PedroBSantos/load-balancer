package com.load.balancer.usecases.payment.outputs;

public class CalculatePaymentOutput {
    
    private int workerId;
    private double totalPayment;

    public CalculatePaymentOutput(int workerId, double totalPayment) {
        this.workerId = workerId;
        this.totalPayment = totalPayment;
    }

    public int getWorkerId() {
        return workerId;
    }

    public double getTotalPayment() {
        return totalPayment;
    }
}
