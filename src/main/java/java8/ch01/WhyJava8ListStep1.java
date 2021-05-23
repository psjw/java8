package java8.ch01;

import java.util.Arrays;
import java.util.List;

public class WhyJava8ListStep1 {


    public static void main(String[] args) {
        final List<Integer> numbers = Arrays.asList(1, 2, 4, 5, 6, 7, 8, 9, 10);
        StringBuilder stringBuilder = new StringBuilder();
        final int size = numbers.size();
        //예전 for loop 사용
        for(int i=0; i<size;i++){
            stringBuilder.append(numbers.get(i));
            if(i != size -1){
                stringBuilder.append(" : ");
            }
        }
        System.out.println(stringBuilder.toString());
    }
}
