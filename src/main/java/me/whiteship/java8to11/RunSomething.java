package me.whiteship.java8to11;

@FunctionalInterface //자바제공하는 기능-> 위배시 컴파일 에러
public interface RunSomething {
    //추상메서드가 딱 하나만 있으면 함수형 인터페이스 O
    // abstract 생략 가능
    void doIt();
    //만약 추상메서드 2개 있다면 함수형 인터페이스 X
//    void doItAgain();
    //public static --> 생략 가능
    //다른 메서드의 유무는 중요하지 않음 -> 추상메서드만 1개 있음됨
    static void printName(){
        System.out.println("psjw");
    }
    default void printAge(){
        System.out.println("44");
    }
}
