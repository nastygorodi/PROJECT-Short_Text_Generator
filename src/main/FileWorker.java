package main;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class FileWorker {
    private final String path;
    private InputStream inp;
    private File file;
    public FileWorker(String new_path){
        path = new_path;
    }
    public FileWorker(InputStream new_inp){
        inp = new_inp;
        path = "";
    }
    public Boolean open(){
        if (path  !=  "") {
            file = new File(path);
            if (!file.exists()) {
                System.out.println("File '" + path + "' not exists\n");
                return false;
            }
            try{
                inp = new FileInputStream(path);
            }
            catch (java.io.FileNotFoundException ex) {
                System.out.println("Can not read file " + path);
            }
        }
        return true;
    }

    public Text extractText(){
        Text text = new Text();
        final char[] end_symbols = {'!', '?', '.'};
        try{
            Reader fileReader = new InputStreamReader(inp, StandardCharsets.UTF_8);
            BufferedReader reader = new BufferedReader(fileReader);
            String sentence = "";
            String line = reader.readLine() + "\n";
            while (line != null) {
                line = line.replace(" -", " ");
                for (int i = 0; i < line.length(); i++){
                    if (line.charAt(i) == '?' || line.charAt(i) == '!' || line.charAt(i) == '.'){
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
        }
        catch (IOException ex){
            System.out.println("Файла  не существует!");
        }
        return text;
    }
}
