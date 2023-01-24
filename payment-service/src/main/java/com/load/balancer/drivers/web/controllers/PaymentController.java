package com.load.balancer.drivers.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.load.balancer.usecases.payment.CalculatePaymentUseCase;
import com.load.balancer.usecases.payment.inputs.CalculatePaymentInput;
import com.load.balancer.usecases.payment.outputs.CalculatePaymentOutput;

@RestController
@RequestMapping(value = "/payments")
public class PaymentController {
    
    @Autowired
    private CalculatePaymentUseCase useCase;

    @GetMapping
    public ResponseEntity<CalculatePaymentOutput> calculate(@RequestParam Integer workerId, @RequestParam int days) {
        var input = new CalculatePaymentInput(workerId, days);
        var result = this.useCase.execute(input);
        return ResponseEntity.ok(result);
    }
}
