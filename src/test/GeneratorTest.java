package test;

import main.FileWorker;
import main.Generator;
import main.MarkovChain;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class GeneratorTest {

    @Test
    void generateHeadlines() {
        String trueRes = "Искусственная еда.\nЕда искусственная.\n";
        FileWorker file = new FileWorker("C:\\project\\Short_Text_Generator\\src\\test\\test2.txt");
        file.open();

        OutputStream os = new ByteArrayOutputStream();
        System.setOut(new PrintStream(os));
        MarkovChain chain = new MarkovChain(file.extractText());
        chain.setSeed(42);
        Generator generator = new Generator(chain, 2, 1);
        generator.generateHeadlines();
        chain.setSeed(43);
        generator = new Generator(chain, 2, 1);
        generator.generateHeadlines();

        String actualOutput = os.toString();
        actualOutput = actualOutput.replace("\r\n", "\n");
        actualOutput = actualOutput.replace("\r", "\n");
        assertEquals(trueRes, actualOutput);

    }
}