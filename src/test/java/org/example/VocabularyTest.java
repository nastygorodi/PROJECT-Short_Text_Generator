package org.example;

import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class VocabularyTest {

    @Test
    void print() {
    }

    @Test
    void getAllWords() {
        InputStream inp = null;
        try {
            inp = Thread.currentThread().getContextClassLoader().getResource("test1.txt").openStream();
        }
        catch (java.io.IOException ex){
            System.out.println("Cant read test file");
        }
        FileWorker file = new FileWorker(inp);
        Vocabulary vocab = new Vocabulary(file.extractText());
        ArrayList<String> actualRes = new ArrayList<>(vocab.getAllWords());
        ArrayList<String> trueRes = new ArrayList<>(Arrays.asList("на",
                "дворе", "играла", "кошка", "вдруг", "с", "вышины",
                "бросился", "огромный", "орёл", "схватил", "котенка", "мать", "быстро",
                "вцепилась", "в", "орла", "бросил", "котятами", "стал",
                "бороться", "кошкой"));
        Collections.sort(trueRes);
        Collections.sort(actualRes);
        assertTrue(trueRes.containsAll(vocab.getAllWords()));
        assertTrue(vocab.getAllWords().containsAll(trueRes));

    }

    @Test
    void getFreqWords() {
        InputStream inp = null;
        try {
            inp = Thread.currentThread().getContextClassLoader().getResource("test1.txt").openStream();
        }
        catch (java.io.IOException ex){
            System.out.println("Cant read test file");
        }
        FileWorker file = new FileWorker(inp);
        Vocabulary vocab = new Vocabulary(file.extractText());
        HashMap<String, Integer> actualRes = new HashMap<>(vocab.getFreqWords());
        HashMap<String, Integer> trueRes = new HashMap<>(){{
            put("на", 1);
            put("дворе", 1);
            put("играла", 1);
            put("кошка", 2);
            put("вдруг", 1);
            put("с", 3);
            put("вышины", 1);
            put("бросился", 1);
            put("огромный", 1);
            put("орёл", 3);
            put("схватил", 1);
            put("котенка", 2);
            put("мать", 1);
            put("быстро", 1);
            put("вцепилась", 1);
            put("в", 1);
            put("орла", 1);
            put("бросил", 1);
            put("стал", 1);
            put("бороться", 1);
            put("кошкой", 1);
            put("котятами", 1);
        }};
        assertEquals(trueRes, actualRes);
    }

    @Test
    void getFreqPairs() {
    }

    @Test
    void getAllPairs() {
    }

    @Test
    void getNumberAllWords() {
    }
}