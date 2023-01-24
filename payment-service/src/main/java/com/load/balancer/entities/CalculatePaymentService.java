package com.load.balancer.entities;

public class CalculatePaymentService {

    private final WorkersGateway workersGateway;

    public CalculatePaymentService(WorkersGateway workersGateway) {
        this.workersGateway = workersGateway;
    }

    public double calculatePayment(int workerId, int days) {
        var worker = this.workersGateway.getById(workerId);
        if (worker.isEmpty()) {
            throw new RuntimeException("Couldnot found worker with id: " + workerId);
        }
        var payment = new Payment(worker.get().getName(), worker.get().getDailyIncome(), days);
        return payment.calculate();
    }
}
