package org.example;

import java.util.Scanner;

public class ConsoleWorker {
    public static FileWorker readFile(){
        System.out.println("Пожалуйста, введите путь к файлу, откуда генератор достанет текстовую базу!");
        Scanner in;
        in = new Scanner(System.in);
        String path = in.nextLine();
        FileWorker fileWorker = new FileWorker(path);
        while (!fileWorker.open()){
            path = in.nextLine();
            fileWorker = new FileWorker(path);
        }
        return fileWorker;
    }
    public static int readLen(){
        Scanner in;
        in = new Scanner(System.in);
        boolean false_input = true;
        int len = 0;
        while (false_input){
            try{
                System.out.println("Введите размер требуемого заголовка!");
                String line = in.nextLine();
                len = Integer.parseInt(line);
                if (len <= 0){
                    System.out.println("Неправильный формат ввода (введите целое положительное число)!");
                    continue;
                }
                false_input = false;
            }
            catch(NumberFormatException ex){
                System.out.println("Неправильный формат ввода (введите целое положительное число)!");
            }
        }
        return len;
    }
    public static int readNumber(){
        Scanner in;
        in = new Scanner(System.in);
        boolean false_input = true;
        int number = 0;
        while (false_input){
            try{
                System.out.println("Введите число необходимых заголовков!");
                String line = in.nextLine();
                number = Integer.parseInt(line);
                if (number <= 0){
                    System.out.println("Неправильный формат ввода (введите целое положительное число)!");
                    continue;
                }
                false_input = false;
            }
            catch(NumberFormatException ex){
                System.out.println("Неправильный формат ввода (введите целое положительное число)!");
            }
        }
        return number;
    }
    public static Text readStartHeadlines(Text start_headlines){
        System.out.println("Хотите ли вы задавать начала заголовков? Y/N");
        Scanner in;
        in = new Scanner(System.in);
        String flag = in.nextLine();
        while (!flag.equals("Y") && !flag.equals("N")){
            System.out.println("Неправильный формат! Введите Y или N");
            flag = in.nextLine();
        }
        if (flag.equals("N")){
            return start_headlines;
        }
        for (int i = 0; i < start_headlines.getHeadlines().size(); i++){
            start_headlines.addHeadline(i, new Headline(in.nextLine()));
        }
        return start_headlines;
    }
}