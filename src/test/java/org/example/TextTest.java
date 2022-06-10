package org.example;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class TextTest {

    @Test
    void getHeadlines() {
    }

    @Test
    void addHeadline() {
    }

    @Test
    void print() {
        Headline firstHeadline = new Headline("Первый заголовок");
        Headline secondHeadline = new Headline("Второй заголовок");
        String trueRes = "Первый заголовок." + "\n" + "Второй заголовок." + "\n";
        Text text = new Text(2);
        text.addHeadline(0, firstHeadline);
        text.addHeadline(1, secondHeadline);
        OutputStream os = new ByteArrayOutputStream();
        System.setOut(new PrintStream(os));
        text.print();
        String actualOutput = os.toString();
        actualOutput = actualOutput.replace("\r\n", "\n");
        actualOutput = actualOutput.replace("\r", "\n");
        assertEquals(trueRes, actualOutput);
    }

    @Test
    void testAddHeadline() {
        ArrayList<Headline> trueRes = new ArrayList<>();
        assertEquals(trueRes, new Text().getHeadlines());
        trueRes.add(new Headline());
        assertEquals(trueRes.size(), new Text(1).getHeadlines().size());
    }
}