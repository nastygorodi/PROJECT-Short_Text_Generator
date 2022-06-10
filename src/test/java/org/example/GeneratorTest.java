package org.example;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class GeneratorTest {

    @Test
    void generateHeadlines() {
        String trueRes = "Искусственная еда.\nЕда искусственная.\n";
        InputStream inp = null;
        try {
            inp = Thread.currentThread().getContextClassLoader().getResource("test2.txt").openStream();
            FileWorker file = new FileWorker(inp);
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
        catch (java.io.IOException ex){
            System.out.println("Cant read test file");
        }
    }
}