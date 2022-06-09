package org.example;

import java.util.ArrayList;
import java.util.HashMap;

public class Vocabulary {
    private Text text;
    private HashMap<String, Integer> freq_words;
    private HashMap<String, HashMap<String, Integer>> freq_pairs;
    private ArrayList<String> all_words;
    private HashMap<String, ArrayList<String>> all_pairs;
    public Vocabulary (){
        text = new Text();
        freq_words = new HashMap<>();
        freq_pairs = new HashMap<>();
        all_pairs = new HashMap<>();
        all_words = new ArrayList<>();
    }
    public Vocabulary(Text new_text){
        freq_words = new HashMap<>();
        freq_pairs = new HashMap<>();
        all_pairs = new HashMap<>();
        all_words = new ArrayList<>();
        text = new_text;
        buildVocab();
    }
    public void print(){
        System.out.println(all_words.toString());
        System.out.println(all_pairs.toString());
        System.out.println(freq_words.toString());
        System.out.println(freq_pairs.toString());
    }
    private void sumMaps(HashMap<String, Integer> first, HashMap<String, Integer> second){
        for (var str_int : second.entrySet()) {
            String word = str_int.getKey();
            Integer value = str_int.getValue();
            Integer count = 0;
            if (first.containsKey(word)) {
                count = first.get(word);
            }
            first.put(word, count + value);
        }
    }
    private void build_freq_words(){
        for (Headline headline : text.getHeadlines()) {
            HashMap<String, Integer> res = headline.count_freq_words();
            sumMaps(freq_words, res);
        }
    }

    private void build_freq_pairs(){
        for (Headline headline : text.getHeadlines()) {
            HashMap<String, HashMap<String, Integer>> res_pairs = headline.count_freq_pairs();
            for (var str_map : res_pairs.entrySet()) {
                String word = str_map.getKey();
                HashMap<String, Integer> map = str_map.getValue();
                if (!freq_pairs.containsKey(word)) {
                    freq_pairs.put(word, map);
                } else {
                    sumMaps(freq_pairs.get(word), map);
                }
            }
        }
    }

    private void build_all_words(){
        for (var str_int : freq_words.entrySet()){
            all_words.add(str_int.getKey());
        }
    }
    private void build_all_pairs(){
        for (var str_map : freq_pairs.entrySet()){
            String word = str_map.getKey();
            all_pairs.put(word, new ArrayList<>());
            for (var str_int : str_map.getValue().entrySet()){
                all_pairs.get(word).add(str_int.getKey());
            }
        }
    }

    private void buildVocab(){
        build_freq_words();
        build_freq_pairs();
        build_all_words();
        build_all_pairs();
    }
    public ArrayList<String> getAllWords(){
        return all_words;
    }
    public HashMap<String, Integer> getFreqWords(){
        return freq_words;
    }
    public HashMap<String, HashMap<String, Integer>> getFreqPairs(){
        return freq_pairs;
    }
    public HashMap<String, ArrayList<String>> getAllPairs(){
        return all_pairs;
    }
    public static int getNumberAllWords(HashMap<String, Integer> freq_map){
        int sum = 0;
        for (var str_int : freq_map.entrySet()){
            String word = str_int.getKey();
            sum += freq_map.get(word);
        }
        return sum;
    }
}
