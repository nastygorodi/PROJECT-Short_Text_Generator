package test;

import main.FileWorker;
import main.MarkovChain;
import main.Vocabulary;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MarkovChainTest {

    @Test
    void generateFirstWord() {
        FileWorker file = new FileWorker("C:\\project\\Short_Text_Generator\\src\\test\\test2.txt");
        file.open();
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