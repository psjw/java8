package java8.ch01;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WhyJava8ReadFileStep1 {
    public static void main(String[] args) {
        //Java7
        try {
            final FileReader fileReader
                    = new FileReader(new File("C:\\ljw-study" +
                    "\\Java\\java8\\src\\main\\java" +
                    "\\java8arrangement\\ch01\\test.txt"));
            final BufferedReader bufferedReader = new BufferedReader(fileReader);
            final List<String> uniqueWords = new ArrayList<>();
            String line = bufferedReader.readLine();
            while (line != null) {
                final String[] words = line.split("[\\s]+"); // 공백문자로 String 나누기
                for (final String word : words) {
                    if (!uniqueWords.contains(word)) { //중복되지 않은 단어만 넣기
                        uniqueWords.add(word);
                    }
                }
                line = bufferedReader.readLine();
            }
            Collections.sort(uniqueWords);
            System.out.println(uniqueWords);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
