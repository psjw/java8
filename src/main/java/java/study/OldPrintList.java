package java.study;

import java.util.ArrayList;
import java.util.List;

public class OldPrintList {
    // for each 구문으로 출력하는예
    public static void main(String[] args) {
        List<String> list = new ArrayList<String>();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");
        // for each 문장을 이용한 데이터 출력
        for(String entity : list){
            System.out.println(entity);
        }
        list.stream().forEach((String entity)-> System.out.println(entity)); //람다 변경
        list.stream().forEach(System.out::println);
    }
}
