package main;


public class Main {
    public static void main(String[] args) {
        System.out.println("Добро пожаловать в генератор коротких заголовков!");
        FileWorker fileWorker = ConsoleWorker.readFile();
        MarkovChain markov_chain = new MarkovChain(fileWorker.extractText());
        Generator generator = new Generator(markov_chain);
        generator.generateHeadlines();
    }
}