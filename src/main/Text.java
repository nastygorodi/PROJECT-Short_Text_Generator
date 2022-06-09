package main;

import java.util.ArrayList;

public class Text {
    public ArrayList<Headline> listOfHeadlines;
    public Text(){
        listOfHeadlines = new ArrayList<>();
    }
    public Text(int number){
        listOfHeadlines = new ArrayList<>();
        for (int i = 0; i < number; i++){
            listOfHeadlines.add(new Headline());
        }
    }
    public ArrayList<Headline> getHeadlines(){
        return listOfHeadlines;
    }

    public void addHeadline(int ind, Headline new_headline){
        listOfHeadlines.set(ind, new_headline);
    }
    public void print(){
        for (Headline headline : listOfHeadlines){
            headline.print();
        }
    }
    public void addHeadline(Headline headline){
        listOfHeadlines.add(headline);
    }
}
