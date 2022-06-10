package org.example;

import org.junit.jupiter.api.Test;

import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

class MarkovChainTest {

    @Test
    void setSeed() {
    }

    @Test
    void print() {
    }

    @Test
    void generateFirstWord() {
        try (InputStream inp = Thread.currentThread().getContextClassLoader().getResource("test2.txt").openStream()){
            FileWorker file = new FileWorker(inp);
            MarkovChain chain = new MarkovChain(file.extractText());
            chain.setSeed(42);
            String word = chain.generateFirstWord();
            assertEquals(word, "искусственная");
            word = chain.generateNextWord(word);
            assertEquals(word, "еда");
        }
        catch (java.io.IOException ex){
            System.out.println("Cant read test file");
        }
    }

    @Test
    void generateNextWord() {
    }
}