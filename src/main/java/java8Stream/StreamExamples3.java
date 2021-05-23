package java8Stream;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

public class StreamExamples3 {
    public static void main(String[] args) {
        System.out.println(" collect(toList()) : " +
                Stream.of(1, 3, 3, 5, 5)
                        .filter(i -> i > 2)
                        .map(i -> i * 2)
                        .map(i -> "#" + i)
                        .collect(toList())
        );

        System.out.println(" collect(toSet()) : " +
                Stream.of(1, 3, 3, 5, 5)
                        .filter(i -> i > 2)
                        .map(i -> i * 2)
                        .map(i -> "#" + i)
                        .collect(toSet())
        );

        System.out.println(" collect(joing()) : " +
                Stream.of(1, 3, 3, 5, 5)
                        .filter(i -> i > 2)
                        .map(i -> i * 2)
                        .map(i -> "#" + i)
                        .collect(joining())
        );
        System.out.println(" collect(joing(\", \")) : " +
                Stream.of(1, 3, 3, 5, 5)
                        .filter(i -> i > 2)
                        .map(i -> i * 2)
                        .map(i -> "#" + i)
                        .collect(joining(", "))
        );

        System.out.println(" collect(joing(\", \" ,\"[\",\"]\")) : " +
                Stream.of(1, 3, 3, 5, 5)
                        .filter(i -> i > 2)
                        .map(i -> i * 2)
                        .map(i -> "#" + i)
                        .collect(joining(", ","[","]"))
        );

        System.out.println("distinct().collect(joing(\", \" ,\"[\",\"]\")) : " +
                Stream.of(1, 3, 3, 5, 5)
                        .filter(i -> i > 2)
                        .map(i -> i * 2)
                        .map(i -> "#" + i)
                        .distinct()
                        .collect(joining(", ","[","]"))
        );

        System.out.println("distinct().collect(toList()) : " +
                Stream.of(1, 3, 3, 5, 5)
                        .filter(i -> i > 2)
                        .map(i -> i * 2)
                        .map(i -> "#" + i)
                        .distinct()
                        .collect(toList())
        );


        final Integer integer3= 3;
        System.out.println(
                Stream.of(1,2,3,4,5)//new Integer(1) 이런식으로 들어가지 않음 -> Integer.valueOf(1) 이런식으로 들어감 -> -128 -> 127 캐쉬됨
                        .filter(i -> i == integer3) //컴파일시 AutoBoxing을 통해 Integer Object로 된다 -> 주소 비교라 검색이 안될것 같다.
                        .findFirst()
        );


        final Integer integer127 = 127;
        System.out.println(
                Stream.of(1,2,3,4,127)//new Integer(1) 이런식으로 들어가지 않음 -> Integer.valueOf(1) 이런식으로 들어감 -> -128 -> 127 캐쉬됨
                        .filter(i -> i == integer127) //컴파일시 AutoBoxing을 통해 Integer Object로 된다 -> 주소 비교라 검색이 안될것 같다.
                        .findFirst()
        );



        final Integer integer128 = 128;
        System.out.println(
                Stream.of(1,2,3,4,128)//new Integer(1) 이런식으로 들어가지 않음 -> Integer.valueOf(1) 이런식으로 들어감 -> -128 -> 127 캐쉬됨
                        .filter(i -> i == integer128) //컴파일시 AutoBoxing을 통해 Integer Object로 된다 -> 주소 비교라 검색이 안될것 같다.
                        .findFirst()
        );



        System.out.println(
                Stream.of(1,2,3,4,128)//new Integer(1) 이런식으로 들어가지 않음 -> Integer.valueOf(1) 이런식으로 들어감 -> -128 -> 127 캐쉬됨
                        .filter(i -> i.equals(integer128)) //컴파일시 AutoBoxing을 통해 Integer Object로 된다 -> 주소 비교라 검색이 안될것 같다.
                        .findFirst()
        );



        System.out.println( ".filter(i -> i > integer3).count() : "+
                Stream.of(1,2,3,4,128)//new Integer(1) 이런식으로 들어가지 않음 -> Integer.valueOf(1) 이런식으로 들어감 -> -128 -> 127 캐쉬됨
                        .filter(i -> i > integer3) //컴파일시 AutoBoxing을 통해 Integer Object로 된다 -> 주소 비교라 검색이 안될것 같다.
                        .count()
        );

        final List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        System.out.println("for(Integer i : numbers) : ");
        //External Iterator
        for(Integer i : numbers){
            System.out.print("i = "+i+" ");
        }

        System.out.println("");
        System.out.println("forEach(i -> System.out.print(i+\" \")); : ");
        //Internal Iterator
        numbers.stream().forEach(i -> System.out.print(i+" "));

    }
}
