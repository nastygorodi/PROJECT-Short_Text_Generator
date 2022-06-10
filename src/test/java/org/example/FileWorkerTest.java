package org.example;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class FileWorkerTest {

    @Test
    void open() {
        InputStream inp = null;
        try {
            inp = Thread.currentThread().getContextClassLoader().getResource("test1.txt").openStream();
            assertTrue(new FileWorker(inp).open());
            assertFalse(new FileWorker("../../resourcdses/test1.txt").open());
        }
        catch (java.io.IOException ex){
            System.out.println("Cant read test file");
        }
    }

    @Test
    void extractText() {
        Text trueRes = new Text(5);
        trueRes.addHeadline(0, new Headline("на дворе играла кошка с котятами"));
        trueRes.addHeadline(1, new Headline("вдруг с вышины бросился огромный орёл"));
        trueRes.addHeadline(2, new Headline("орёл схватил котенка"));
        trueRes.addHeadline(3, new Headline("мать кошка быстро\n" +
                "        вцепилась в орла"));
        trueRes.addHeadline(4, new Headline("орёл бросил котенка стал бороться с кошкой"));
        InputStream inp = null;
        try {
            inp = Thread.currentThread().getContextClassLoader().getResource("test1.txt").openStream();
        }
        catch (java.io.IOException ex){
            System.out.println("Cant read test file");
        }
        FileWorker file = new FileWorker(inp);


        ArrayList<Headline> actualRes = file.extractText().getHeadlines();
        for (int i = 0; i < 5; i++){
            assertEquals(trueRes.getHeadlines().get(i).getBody(), actualRes.get(i).getBody());
        }

    }
}