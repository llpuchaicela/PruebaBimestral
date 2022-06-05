package SecuencialPruebaUnitaria;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class DemoTask {
    private String url;

    public DemoTask(String url) {
        this.url = url;
    }

    public boolean Urls() throws IOException {
        // List<String> uris = List.of("http://www.utpl.edu.ec", "http://git.taw.utpl.edu.ec", "http://j4loxa.com");

        HttpClient client = HttpClient
                .newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .followRedirects(HttpClient.Redirect.NORMAL)
                .connectTimeout(Duration.ofSeconds(20))
                .build();
        try {
            HttpRequest request = HttpRequest
                    .newBuilder(new URI(url))
                    .HEAD()
                    .timeout(Duration.ofSeconds(20))
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.printf("%s (%d)\n", url, response.statusCode());
            if (response.statusCode() == 200) {
                return true;
            }
        } catch (URISyntaxException | IOException | InterruptedException | IllegalArgumentException exception) {
            System.out.printf("URI-Error: %s(%s)\n", url, exception.getMessage());
        }
        return false;
    }
}

