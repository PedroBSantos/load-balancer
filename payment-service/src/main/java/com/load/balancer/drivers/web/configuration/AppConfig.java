package com.load.balancer.drivers.web.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.core.env.Environment;

import com.load.balancer.adapters.*;
import com.load.balancer.drivers.load_balancer.*;
import com.load.balancer.entities.*;
import com.load.balancer.usecases.payment.CalculatePaymentUseCase;

@Configuration
public class AppConfig {

    @Autowired
    private Environment env;

    @Bean
    @Scope("singleton")
    WorkersWebServiceDiscovery workersWebServiceDiscovery() {
        var serviceDiscoveryHost = env.getProperty("payment-service.service.discovery.host");
        return new WorkersWebServiceDiscovery(serviceDiscoveryHost);
    }

    @Bean
    @Scope("singleton")
    WorkerService workerWebService(@Autowired WorkersWebServiceDiscovery serviceDiscovery) {
        return new WorkerWebServiceLoadBalancer(serviceDiscovery);
    }

    @Bean
    @Scope("singleton")
    WorkersGateway workersGateway(@Autowired WorkerService workerService) {
        return new WorkerGatewayImpl(workerService);
    }

    @Bean
    @Scope("singleton")
    CalculatePaymentService calculatePaymentService(@Autowired WorkersGateway workersGateway) {
        return new CalculatePaymentService(workersGateway);   
    }

    @Bean
    @Scope("singleton")
    CalculatePaymentUseCase calculatePaymentUseCase(@Autowired CalculatePaymentService service) {
        return new CalculatePaymentUseCase(service);
    }
}
