package test;

import main.FileWorker;
import main.MarkovChain;
import main.Vocabulary;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;

class MarkovChainTest {

    @Test
    void generateFirstWord() {
        InputStream inp = null;
        try {
            inp = getClass().getResource("resources/test2.txt").openStream();
        }
        catch (java.io.IOException ex){
            System.out.println("Cant read test file");
        }
        FileWorker file = new FileWorker(inp);
        MarkovChain chain = new MarkovChain(file.extractText());
        chain.setSeed(42);
        String word = chain.generateFirstWord();
        assertEquals(word, "искусственная");
        word = chain.generateNextWord(word);
        assertEquals(word, "еда");
    }

    @Test
    void generateNextWord() {
    }
}