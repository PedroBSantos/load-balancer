package com.load.balancer.drivers.load_balancer;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import com.load.balancer.adapters.WorkerService;
import com.load.balancer.entities.Worker;

public class WorkerWebServiceLoadBalancer implements WorkerService {
    
    private WorkersWebServiceDiscovery serviceDiscovery;

    public WorkerWebServiceLoadBalancer(WorkersWebServiceDiscovery serviceDiscovery) {
        this.serviceDiscovery = serviceDiscovery;
    }

    private WorkerWebService getRandomInstance() {
        var instances = this.serviceDiscovery.discovery();
        if (instances.isEmpty()) {
            throw new RuntimeException("No instance found on service registry for 'worker-service'");
        }
        if (instances.size() == 1) {
            return instances.get(0);
        }
        var random = new Random();
        var instanceIndex = random.nextInt(0, instances.size());
        return instances.get(instanceIndex);
    }

    @Override
    public List<Worker> getAllWorkers() {
        var instance = this.getRandomInstance();
        return instance.getAllWorkers();
    }

    @Override
    public Optional<Worker> findById(int id) {
        var instance = this.getRandomInstance();
        return instance.findById(id);
    }
}
