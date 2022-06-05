import SecuencialPruebaUnitaria.DemoTask;
import org.junit.jupiter.api.Assertions;

import java.io.IOException;
import java.util.List;

public class DemoTest {
    @org.junit.jupiter.api.Test

    void testDemo() throws IOException {
        List<String> urs=List.of("http://www.utpl.edu.ec", "http://git.taw.utpl.edu.ec", "http://j4loxa.com");
        DemoTask demo = new DemoTask(urs.get(0));
        DemoTask demo1 = new DemoTask(urs.get(1));
        DemoTask demo2 = new DemoTask(urs.get(2));

        Assertions.assertEquals(true,demo.Urls());
        Assertions.assertEquals(false,demo1.Urls());
        Assertions.assertEquals(true,demo2.Urls());
    }
}
