package com.load.balancer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.load.balancer.entities.Worker;

public interface WorkerRepository extends JpaRepository<Worker, Integer> {
    
}
