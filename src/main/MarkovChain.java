package main;



import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

public class MarkovChain {
    Vocabulary vocab;
    int[] probabilities_words;
    int seed;
    HashMap<String, int[]> probabilities_pairs;
    public MarkovChain(Text new_text){
        seed = -1;
        vocab = new Vocabulary(new_text);
        probabilities_pairs = new HashMap<>();
        buildProbabilities();
    }
    public void setSeed(int new_seed){
        seed = new_seed;
    }
    public void print(){
        System.out.println(Arrays.toString(probabilities_words));
        for (var str_arr : probabilities_pairs.entrySet()){
            String word = str_arr.getKey();
            System.out.println(word);
            System.out.println(Arrays.toString(str_arr.getValue()));
        }
    }
    private int[] makeProbabilitiesArray(HashMap<String, Integer> freq_words, ArrayList<String> all_words){
        int len = Vocabulary.getNumberAllWords(freq_words);
        int[] probabilities_array = new int[len];
        Arrays.fill(probabilities_array, 228);
        int ind = 0;
        int counter = 0;
        for (int i = 0; i < len; i++){
            probabilities_array[i] = ind;
            counter++;
            if (counter == freq_words.get(all_words.get(ind))){
                counter = 0;
                ind++;
            }
        }
        return probabilities_array;
    }

    private void buildProbabilitiesWords(){
        probabilities_words = makeProbabilitiesArray(vocab.getFreqWords(), vocab.getAllWords());
    }
    private void buildProbabilitiesPairs(){
        for (String word : vocab.getAllWords()) {
            if (vocab.getFreqPairs().containsKey(word)){
                probabilities_pairs.put(word, makeProbabilitiesArray(vocab.getFreqPairs().get(word),
                        vocab.getAllPairs().get(word)));
            }
        }
    }

    private void buildProbabilities(){
        buildProbabilitiesWords();
        buildProbabilitiesPairs();
    }

    public String generateFirstWord(){
        int ind = 0;
        if (seed != -1){
            ind = new Random(seed).nextInt(probabilities_words.length);
        }
        else{
            ind = new Random().nextInt(probabilities_words.length);
        }
        return vocab.getAllWords().get(probabilities_words[ind]);
    }

    public String generateNextWord(String prev_word){
        if (!probabilities_pairs.containsKey(prev_word)){
            return generateFirstWord();
        }
        else{
            int ind = 0;
            if (seed != -1){
                ind = new Random(seed).nextInt(probabilities_pairs.get(prev_word).length);
            }
            else{
                ind = new Random().nextInt(probabilities_pairs.get(prev_word).length);
            }
            return vocab.getAllPairs().get(prev_word).get(probabilities_pairs.get(prev_word)[ind]);
        }
    }
}
