package com.load.balancer.adapters;

import java.util.List;
import java.util.Optional;

import com.load.balancer.entities.Worker;

public interface WorkerService {
    
    List<Worker> getAllWorkers();

    Optional<Worker> findById(int id);
}
