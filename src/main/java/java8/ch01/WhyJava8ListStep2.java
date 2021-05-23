package java8.ch01;

import java.util.Arrays;
import java.util.List;

public class WhyJava8ListStep2 {

    public static void main(String[] args) {
        final List<Integer> numbers = Arrays.asList(1, 2, 4, 5, 6, 7, 8, 9, 10);
        StringBuilder stringBuilder = new StringBuilder();
        final int size = numbers.size();
        //향상된 for문 사용
        final String separator = " : ";
        for(final Integer number : numbers){
            stringBuilder.append(number).append(separator);
        }
        final int stringLength = stringBuilder.length();
        if(stringLength > 0){
            //String 빌더 length 뺴기 "  : " length부터 String 빌더 length까지 지우기
            stringBuilder.delete(stringLength - separator.length(), stringLength);
        }
        System.out.println(stringBuilder.toString());
    }
}
