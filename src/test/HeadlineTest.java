package test;

import main.Headline;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class HeadlineTest {

    @Test
    void getBody() {
        Headline headline = new Headline("ТИ - лучшая специализация в мире ");
        ArrayList<String> trueRes = new ArrayList<>(Arrays.asList("ТИ", "лучшая", "специализация", "в", "мире"));
        assertEquals(trueRes, headline.getBody());
    }

    @Test
    void print() {
        Headline headline = new Headline("Потому что Дора - дура, супер дура, Дора - дура ");
        String trueRes = "Потому что Дора дура супер дура Дора дура.\n";
        OutputStream os = new ByteArrayOutputStream();
        //PrintStream stdout = System.out;
        System.setOut(new PrintStream(os));
        headline.print();
        String actualOutput = os.toString();
        actualOutput = actualOutput.replace("\r\n", "\n");
        actualOutput = actualOutput.replace("\r", "\n");
        //System.setOut(stdout);
        assertEquals(trueRes, actualOutput);
    }

    @Test
    void count_freq_words() {
        Headline headline = new Headline("Потому что Дора - дура, супер дура, Дора - дура ");
        HashMap<String, Integer> trueRes = new HashMap<>();
        trueRes.put("Потому", 1);
        trueRes.put("что", 1);
        trueRes.put("Дора", 2);
        trueRes.put("дура", 3);
        trueRes.put("супер", 1);
        assertEquals(trueRes, headline.count_freq_words());
    }

    @Test
    void count_freq_pairs() {
        Headline headline = new Headline("Потому что Дора - дура, супер дура, Дора - дура ");
        HashMap<String, HashMap<String, Integer>> trueRes = new HashMap<>();
        HashMap<String, Integer> trueValue = new HashMap<>(){{
           put("что", 1);
        }};
        trueRes.put("Потому", trueValue);
        trueValue = new HashMap<>(){{
            put("Дора", 1);
        }};
        trueRes.put("что", trueValue);
        trueValue = new HashMap<>(){{
            put("дура", 2);
        }};
        trueRes.put("Дора", trueValue);
        trueValue = new HashMap<>(){{
            put("супер", 1);
            put("Дора", 1);
        }};
        trueRes.put("дура", trueValue);
        trueValue = new HashMap<>(){{
            put("дура", 1);
        }};
        trueRes.put("супер", trueValue);
        assertEquals(trueRes, headline.count_freq_pairs());
    }
    @Test
    void checkConstructors(){
        ArrayList<String> trueRes = new ArrayList<>();
        assertEquals(trueRes, new Headline().getBody());
        trueRes.add("Abacaba");
        assertEquals(trueRes, new Headline(trueRes).getBody());
    }
}