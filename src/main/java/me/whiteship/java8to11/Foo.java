package me.whiteship.java8to11;

import java.util.function.*;

public class Foo {
    public static void main(String[] args) {
        //익명 내부 클래스 annoymous inner class
/*        RunSomething runSomething=new RunSomething() {
            @Override
            public void doIt() {
                System.out.println("Hello");
            }
        };*/
        //람다형 한줄
        RunSomething runSomething = () -> System.out.println("Hello");

        //람다형 두줄
        RunSomething runSomething1 = () -> {
            System.out.println("Hello");
            System.out.println("Lambda");
        };

        runSomething.doIt();


        //int baseNumber1=10; 외부에 있는 값을 변경 하려는 경우
        //final int baseNumber=10;
        RunSomething1 runSomething2 = new RunSomething1() {
            int baseNumber =10; //내부의 값에 영향
            @Override
            public int doIt(int number) {
                //baseNumber1++; //->순수한 함수가 아니다.
                baseNumber++; //-> 순수한 함수가 아님 -> 상태값에 의존
                return number + baseNumber;
            }
        };

        
        //결과가 같아야 함 -> 1을 넣엇을때 계속 11이 나와야함 -> 보장하지 못하는 상황에는 함수형프로그래밍이 아님
        System.out.println(runSomething2.doIt(1));
        System.out.println(runSomething2.doIt(1));
        System.out.println(runSomething2.doIt(1));

        System.out.println(runSomething2.doIt(2));
        System.out.println(runSomething2.doIt(2));
        System.out.println(runSomething2.doIt(2));



        Plus10 plus10=new Plus10();
        System.out.println(plus10.apply(1));


        Function<Integer,Integer> plus101= (i) -> i+10;
        UnaryOperator<Integer> uPlus101= (i) -> i+10;//입력 출력 같음
        Function<Integer,Integer> mutiply2=(i) -> i*2;
        System.out.println(plus101.apply(1));
        System.out.println(mutiply2.apply(1));
        
        Function<Integer,Integer> multply2AndPlus101=plus101.compose(mutiply2);//compose가 입력하는 값을 가지고 먼저 뒤에 함수를 실행후 앞에 함수실행
        System.out.println(multply2AndPlus101.apply(2));

        System.out.println(plus101.andThen(mutiply2).apply(2)); // 입력값이 들어오면 먼저 앞의 함수를 실행후 뒤에 함수 실행

        Consumer<Integer> printT=(i)-> System.out.println(i);
        printT.accept(10);

        Supplier<Integer> get15 =() -> 15;
        System.out.println(get15);

        Predicate<String> startWithStr = (s)->s.startsWith("ljw");
        Predicate<Integer> isEven = (i) -> i%2 == 0 ;



    }
}
