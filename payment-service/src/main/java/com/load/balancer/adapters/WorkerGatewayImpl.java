package com.load.balancer.adapters;

import java.util.List;
import java.util.Optional;

import com.load.balancer.entities.Worker;
import com.load.balancer.entities.WorkersGateway;

public class WorkerGatewayImpl implements WorkersGateway {

    private final WorkerService service;

    public WorkerGatewayImpl(WorkerService service) {
        this.service = service;
    }

    @Override
    public List<Worker> getAll() {
        return this.service.getAllWorkers();
    }

    @Override
    public Optional<Worker> getById(int id) {
        return this.service.findById(id);
    }
}
