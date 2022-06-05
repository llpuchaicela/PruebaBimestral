package org.example.SumNumPrimos;

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

public class DemoSec {
    public static void main(String[] args) throws IOException {
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
        HttpClient client = HttpClient
                .newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .followRedirects(HttpClient.Redirect.NORMAL)
                .connectTimeout(Duration.ofSeconds(20))
                .build();
        for (var uriTxt : uris) {
            try {
                HttpRequest request = HttpRequest
                        .newBuilder(new URI(uriTxt))
                        .HEAD()
                        .timeout(Duration.ofSeconds(20))
                        .build();
                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            } catch (URISyntaxException | IOException | InterruptedException | IllegalArgumentException exception) {
            }
        }
    }
}

