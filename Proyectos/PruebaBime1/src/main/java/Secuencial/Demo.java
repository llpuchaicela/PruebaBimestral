package Secuencial;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
public class Demo {
    public static void main(String[] args) throws IOException {
//var uris = List.of("http://www.utpl.edu.ec", "http://git.taw.utpl.edu.ec", "http://j4loxa.com");
        List<String> uris;
        try (Stream<String> lines = Files.lines(Paths.get("src/main/resources/urls.csv"))) {
            uris = lines.collect(Collectors.toList());
        }

        HttpClient client = HttpClient
                .newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .followRedirects(HttpClient.Redirect.NORMAL)
                .connectTimeout(Duration.ofSeconds(20))
                .build();
        for(var uriTxt : uris) {
            try {
                HttpRequest request = HttpRequest
                        .newBuilder(new URI(uriTxt))
                        .HEAD()
                        .timeout(Duration.ofSeconds(20))
                        .build();
                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
                System.out.printf("%s (%d)\n",uriTxt, response.statusCode());
            } catch (URISyntaxException | IOException | InterruptedException | IllegalArgumentException exception) {
                System.out.printf("URI-Error: %s(%s)\n", uriTxt, exception.getMessage());
            }
        }
    }
}