package main;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

public class Headline {
    private ArrayList<String> body;
    public Headline(){
        body = new ArrayList<>();
    }
    public Headline(ArrayList<String> new_body){
        body = new_body;
    }
    public Headline(String sentence){
        body = new ArrayList<>();
        splitSentence(sentence);
    }
    public ArrayList<String> getBody(){
        return body;
    }

    public void print(){
        if (body.size() > 0){
            body.set(0, body.get(0).substring(0, 1).toUpperCase(Locale.ROOT) + body.get(0).substring(1));
        }
        String res = "";
        for (String word : body){
            res += word + " ";
        }
        res = res.substring(0, res.length() - 1) + ".";
        System.out.println(res);
    }
    private void splitSentence(String sentence){
        final char[] special_symbols = {',', ':', ';', ' ', '(', ')', '\n', '-'};
        String cur_word = "";
        for (int i = 0; i < sentence.length(); i++){
            if (new String(special_symbols).indexOf(sentence.charAt(i)) != -1){
                if (cur_word.length() > 0) {
                    body.add(cur_word);
                    cur_word = "";
                }
            }
            else{
                cur_word += sentence.charAt(i);
            }
        }
        if (cur_word.length() > 0){
            body.add(cur_word);
        }
    }
    public HashMap<String, Integer> count_freq_words(){
        HashMap<String, Integer> res = new HashMap<>();
        for (String cur_word : body){
            int count = 0;
            if (res.containsKey(cur_word)){
                count = res.get(cur_word);
            }
            res.put(cur_word, count + 1);
        }
        return res;
    }
    public HashMap<String, HashMap<String, Integer>> count_freq_pairs(){
        HashMap<String, HashMap<String, Integer>> res = new HashMap<>();
        for (int i = 1; i < body.size(); i++){
            String prev_word = body.get(i - 1);
            String cur_word = body.get(i);
            if (!res.containsKey(prev_word)){
                HashMap<String, Integer> value = new HashMap<>();
                value.put(cur_word, 1);
                res.put(prev_word, value);
            }
            else{
                int count = 0;
                if (res.get(prev_word).containsKey(cur_word)){
                    count = res.get(prev_word).get(cur_word);
                }
                res.get(prev_word).put(cur_word, count + 1);
            }
        }
        return res;
    }
}
