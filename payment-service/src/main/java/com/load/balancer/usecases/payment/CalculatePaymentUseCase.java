package com.load.balancer.usecases.payment;

import com.load.balancer.entities.CalculatePaymentService;
import com.load.balancer.usecases.payment.inputs.CalculatePaymentInput;
import com.load.balancer.usecases.payment.outputs.CalculatePaymentOutput;

public class CalculatePaymentUseCase {

    private final CalculatePaymentService service;

    public CalculatePaymentUseCase(CalculatePaymentService service) {
        this.service = service;
    }

    public CalculatePaymentOutput execute(CalculatePaymentInput input) {
        var total = this.service.calculatePayment(input.getWorkerId(), input.getDays());
        var payment = new CalculatePaymentOutput(input.getWorkerId(), total);
        return payment;
    }
}
