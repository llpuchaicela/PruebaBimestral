package Trabajo;

import org.example.Prueba.DemoThreads;
import org.example.Prueba.Results;
import org.openjdk.jmh.annotations.*;

import java.io.IOException;
import java.net.http.HttpClient;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class MyBenchmarkSecuencial {

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public void measureThroughput() throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(10);
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    @Fork(value = 2)
    @Warmup(iterations = 1,time = 1)
    @Measurement(iterations = 1, time = 1)

    public void testMethod() throws IOException {
        List<String> uris = List.of("https://youtu.be/H9WoXa-KELM",
                "https://lnkd.in/dpHrkVr",
                "https://buff.ly/2I1ikpO",
                "https://youtu.be/K-mSfPxfsEg",
                "http://bit.ly/2McqEXR",
                "https://lttr.ai/EQMd",
                "https://www.pie.capital/aff_click?a=a88900924.p10462171",
                "https://lnkd.in/eqkyFx3",
                "https://jef.tips/H9YIg",
                "https://lnkd.in/dukYivf",
                "https://www.crazyaboutstartups.com/where-are-all-the-biotech-startups-raising/",
                "http://bit.ly/31ArgKu",
                "https://www.businessreview.live/post/untitled-1",
                "https://www.instagram.com/p/BzMGVATgxrp/?igshid=cuav9oeqg3ap",
                "https://www.rezgamedev.com/2019/06/life-after-graduation.html",
                "https://www.worldscientific.com/doi/10.1142/S108494671950002X#.XRXfcaENr50.twitter",
                "https://buff.ly/2eBWonq",
                "http://bit.ly/2oTtxOI",
                "https://tcrn.ch/2Zjiqie",
                "http://ow.ly/OeWU30oXbM6",
                "https://rplg.co/f95a1a90",
                "https://buff.ly/2VzpgOt",
                "http://jef.tips/rCXnM",
                "http://vid.staged.com/wlWv",
                "https://m.facebook.com/watchparty/2112970205666767/");
        ExecutorService executor = Executors.newFixedThreadPool(10);
        List<Future<Results>> ClaseModelo = new ArrayList<>();
        HttpClient client = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .followRedirects(HttpClient.Redirect.NORMAL)
                .connectTimeout(Duration.ofSeconds(20))
                .build();
        for (var uriTxt : uris) {
            ClaseModelo.add(executor.submit(new DemoThreads(client, uriTxt)));
        }
        for (var clase : ClaseModelo) {
            try {
                clase.get();
            }catch (InterruptedException | ExecutionException exc) {

            }
        }
        executor.shutdown();
    }
}
