package java8.ch01;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WhyJava8ReadFileStep2 {
    public static void main(String[] args) {

        try {
            Stream<String> lines = Files
                    .lines(Paths.get("C:\\ljw-study" +
                    "\\Java\\java8\\src\\main\\java" +
                    "\\java8arrangement\\ch01\\test.txt"));

            List<String> result = lines.map(line -> line.split("[\\s]+"))
                    //split으로 나온 String[] 에 Stream 부여
                    .flatMap(Arrays::stream) //Stream<Array[String]> -> Stream<Stream<String>> -> Stream<String> 변환 과정을 한줄로
                    .distinct()
                    .sorted()
                    .collect(Collectors.toList());
            System.out.println(result);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
