package com.load.balancer.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.load.balancer.entities.Worker;
import com.load.balancer.repositories.WorkerRepository;

@RestController
@RequestMapping(value = "/workers")
public class WorkerController {
    
    @Autowired
    private WorkerRepository repository;
    @Autowired
    private Environment env;
    private static Logger logger = LoggerFactory.getLogger(WorkerController.class);

    @GetMapping
    public ResponseEntity<List<Worker>> getAll() {
        logger.info(env.getProperty("local.server.port"));
        var workers = this.repository.findAll();
        return ResponseEntity.ok(workers);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Worker> getById(@PathVariable Integer id) {
        logger.info(env.getProperty("local.server.port"));
        var worker = this.repository.findById(id);
        return worker.isPresent() ? ResponseEntity.ok(worker.get()) : ResponseEntity.notFound().build();
    }
}
