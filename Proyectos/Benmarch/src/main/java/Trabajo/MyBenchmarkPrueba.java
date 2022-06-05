/*
 * Copyright (c) 2005, 2014, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.  Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 * or visit www.oracle.com if you need additional information or have any
 * questions.
 */

package Trabajo;

import org.example.Prueba.DemoThreads;
import org.example.Prueba.MainDemo;
import org.example.Prueba.Results;
import org.openjdk.jmh.annotations.*;
import java.io.IOException;
import java.net.http.HttpClient;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class MyBenchmarkPrueba {

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

    public void testMethod() throws IOException{
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
        ExecutorService executor = Executors.newFixedThreadPool(14);
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
