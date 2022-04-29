package main;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.File;

public class FileWorker {
    private final String path;
    private File file;
    public FileWorker(String new_path){
        path = new_path;
    }
    public Boolean open(){
        file = new File(path);
        if (!file.exists()) {
            System.out.println("File '" + path + "' not exists\n");
            return false;
        }
        return true;
    }

    public Text extractText(){
        Text text = new Text();
        final char[] end_symbols = {'!', '?', '.'};
        try{
            FileReader fileReader = new FileReader(file);
            BufferedReader reader = new BufferedReader(fileReader);
            String sentence = "";
            String line = reader.readLine() + "\n";
            while (line != null) {
                line = line.replace(" -", " ");
                for (int i = 0; i < line.length(); i++){
                    if (new String(end_symbols).indexOf(line.charAt(i)) != -1){
                        if (sentence.length() > 0) {
                            text.addHeadline(new Headline(sentence));
                            sentence = "";
                        }
                    }
                    else{
                        sentence += line.substring(i, i + 1).toLowerCase();
                    }
                }
                line = reader.readLine();
            }
            if (sentence.length() > 0) {
                text.addHeadline(new Headline(sentence));
            }
            reader.close();
        }
        catch (IOException ex){
            System.out.println("Файла '" + path + "' не существует!");
        }
        return text;
    }
}
