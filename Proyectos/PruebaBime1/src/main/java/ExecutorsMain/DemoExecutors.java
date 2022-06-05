package ExecutorsMain;

import java.io.IOException;
import java.net.http.HttpClient;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DemoExecutors {
    public static void main(String[] args) {
        // List<String> uris = List.of("http://www.utpl.edu.ec", "http://git.taw.utpl.edu.ec", "http://j4loxa.com");
        var urlValida = 0;
        var urlNoValida = 0;
        List<String> uris;
        try (Stream<String> lines = Files.lines(Paths.get("src/main/resources/urls.csv"))) {
            uris = lines.collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ExecutorService executor = Executors.newFixedThreadPool(12);
        List<Future<Results>> ClaseModelo = new ArrayList<>();

        HttpClient client = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .followRedirects(HttpClient.Redirect.NORMAL)
                .connectTimeout(Duration.ofSeconds(20))
                .build();
        for (var uriTxt : uris) {
            ClaseModelo.add(executor.submit(new TaskDemoExecutors(client, uriTxt)));
        }
        for (var clase : ClaseModelo) {
            try {
                System.out.printf("%s (%d)\n", clase.get().url(), clase.get().estado());
                if (clase.get().estado() == 200) {
                    urlValida = urlValida + 1;

                } else {
                    urlNoValida = urlNoValida + 1;
                }
            } catch (ExecutionException | InterruptedException exception) {
            }
        }
        System.out.println("Las url que siguen validas son :" + urlValida
                + "\nLas URL que no son Validas son: " + urlNoValida);
        executor.shutdown();

    }
}
