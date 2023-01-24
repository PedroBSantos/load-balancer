package com.load.balancer.drivers.load_balancer;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ecwid.consul.v1.ConsulClient;

public class WorkersWebServiceDiscovery {
    
    private static Logger logger = LoggerFactory.getLogger(WorkersWebServiceDiscovery.class);
    private ConsulClient client;

    public WorkersWebServiceDiscovery(String serviceDiscoveryHost) {
        this.client = new ConsulClient(serviceDiscoveryHost);
    }

    public List<WorkerWebService> discovery() {
        var services = client.getAgentServices();
        var servers = new ArrayList<WorkerWebService>();
        if (services.getValue().isEmpty()) {
            return servers;
        }
        services.getValue().entrySet().forEach(entry -> {
            if (entry.getValue().getService().contains("worker-service")) {
                var serviceAddress = entry.getValue().getAddress();
                var servicePort = entry.getValue().getPort();
                logger.info(serviceAddress + ":" + servicePort);
                servers.add(new WorkerWebService(serviceAddress + ":" + servicePort));
            }
        });
        return servers;
    }
}
