package me.whiteship.java8to11;

@FunctionalInterface //자바제공하는 기능-> 위배시 컴파일 에러
public interface RunSomething1 {
    //추상메서드가 딱 하나만 있으면 함수형 인터페이스 O
    // abstract 생략 가능
    int doIt(int number);
}
