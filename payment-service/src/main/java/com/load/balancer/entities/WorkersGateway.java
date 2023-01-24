package com.load.balancer.entities;

import java.util.List;
import java.util.Optional;

public interface WorkersGateway {

    List<Worker> getAll();

    Optional<Worker> getById(int id);
}
