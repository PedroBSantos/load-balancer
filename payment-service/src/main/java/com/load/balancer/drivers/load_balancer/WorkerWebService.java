package com.load.balancer.drivers.load_balancer;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.google.gson.Gson;
import com.load.balancer.adapters.WorkerService;
import com.load.balancer.entities.Worker;

public class WorkerWebService implements WorkerService {

    private final String host;
    private final Gson gson;

    public WorkerWebService(String host) {
        this.host = host;
        this.gson = new Gson();
    }

    @Override
    public List<Worker> getAllWorkers() {
        try {
            var request = HttpRequest.newBuilder()
                    .uri(new URI("http://" + host + "/workers"))
                    .GET()
                    .build();
            var client = HttpClient.newHttpClient();
            var response = client.send(request, BodyHandlers.ofString());
            var workers = this.gson.fromJson(response.body(), Worker[].class);
            return Arrays.asList(workers);
        } catch (URISyntaxException | InterruptedException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Worker> findById(int id) {
        try {
            var request = HttpRequest.newBuilder()
                    .uri(new URI("http://" + host + "/workers/" + id))
                    .GET()
                    .build();
            var client = HttpClient.newHttpClient();
            var response = client.send(request, BodyHandlers.ofString());
            if (response.statusCode() != 200) {
                return Optional.empty();
            }
            var worker = this.gson.fromJson(response.body(), Worker.class);
            return Optional.of(worker);
        } catch (URISyntaxException | InterruptedException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
