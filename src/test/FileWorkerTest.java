package test;

import main.FileWorker;
import main.Headline;
import main.Text;
import org.junit.jupiter.api.Test;


import java.io.File;
import java.io.FileWriter;

import static org.junit.jupiter.api.Assertions.*;

class FileWorkerTest {

    @Test
    void open() {
        assertTrue(new FileWorker("C:\\project\\Short_Text_Generator\\src\\test\\test1.txt").open());
        assertFalse(new FileWorker("C:\\projecort_Text_Generator\\test1.txt").open());
    }

    @Test
    void extractText() {
        FileWorker file = new FileWorker("C:\\project\\Short_Text_Generator\\src\\test\\test1.txt");
        file.open();
        Text trueRes = new Text(5);
        trueRes.addHeadline(0, new Headline("на дворе играла кошка с котятами"));
        trueRes.addHeadline(1, new Headline("вдруг с вышины бросился огромный орёл"));
        trueRes.addHeadline(2, new Headline("орёл схватил котенка"));
        trueRes.addHeadline(3, new Headline("мать кошка быстро\n" +
                "        вцепилась в орла"));
        trueRes.addHeadline(4, new Headline("орёл бросил котенка стал бороться с кошкой"));
        for (int i = 0; i < 5; i++){
            assertEquals(trueRes.getHeadlines().get(i).getBody(), file.extractText().getHeadlines().get(i).getBody());
        }
    }
}