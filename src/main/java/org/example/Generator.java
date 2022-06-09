package org.example;



import java.util.ArrayList;

public class Generator {
    MarkovChain markovChain;
    int len;
    int number;
    Text startHeadlines;
    public Generator(MarkovChain newMarkovChain){
        markovChain = newMarkovChain;
        len = ConsoleWorker.readLen();
        number = ConsoleWorker.readNumber();
        startHeadlines = ConsoleWorker.readStartHeadlines(new Text(number));
    }
    public Generator(MarkovChain newMarkovChain, int newLen, int newNumber){
        markovChain = newMarkovChain;
        len = newLen;
        number = newNumber;
        startHeadlines = new Text(number);
    }

    private Headline generateHeadline(int ind){
        ArrayList<String> body = startHeadlines.getHeadlines().get(ind).getBody();
        if (body.size() == 0) {
            body.add(markovChain.generateFirstWord());
        }
        int start_size = body.size();
        for (int i = start_size; i < len; i++){
            body.add(markovChain.generateNextWord(body.get(i - 1)));
        }
        return new Headline(body);
    }
    public void generateHeadlines(){
        for (int i = 0; i < number; i++){
            Headline headline = generateHeadline(i);
            headline.print();
        }
    }
}
