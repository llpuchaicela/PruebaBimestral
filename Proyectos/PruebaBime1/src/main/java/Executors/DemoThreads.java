package Executors;

import javax.xml.transform.Result;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.concurrent.Callable;

public class DemoThreads implements Callable<ClaseModelo> {
    private HttpClient client;
    private String url;

    public DemoThreads(HttpClient client, String url) {
        this.client = client;
        this.url = url;
    }

    @Override
    public ClaseModelo call() throws Exception {
        HttpRequest request = HttpRequest
                .newBuilder(new URI(url))
                .HEAD()
                .timeout(Duration.ofSeconds(20))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        ClaseModelo ClaseModelo = new ClaseModelo(url, response.statusCode());
        return ClaseModelo;
    }
}

