package org.example.Prueba;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.concurrent.Callable;

public class DemoThreads implements Callable<Results> {

    private HttpClient client;
    private String url;

    public DemoThreads(HttpClient client, String url) {
        this.client = client;
        this.url = url;
    }

    @Override
    public Results call() throws Exception {
        HttpRequest request = HttpRequest
                .newBuilder(new URI(url)).
                HEAD()
                .timeout(Duration.ofSeconds(20))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        Results results = new Results(url, response.statusCode());
        return results;
    }
}
