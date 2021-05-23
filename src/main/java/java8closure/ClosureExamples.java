package java8closure;

public class ClosureExamples {
    //Closure
    //First Class Function
    //Function = First Class Citizen
    // 1. parameter로 넘길수 있음
    //2. return 값으로 리턴 가능
    // 3. variable에 저장
    // 4. Data Structure에 저장
    // 표현하기 위한 방법 anonymous Function = Lambda Expression
    // method(i -> i + 1)
    // Closure란?
    /* 메소드안
        int i = 100; //non-local variable = Free variable
         someMethod(x -> x*2+i); // x는 파라미터 / i는 Lambda Expression 밖에 있음
        // x -> x*2+i -> closure라 부름 (scope자체를 외부로 확장 : i까지 사용하겠다)
      */
    private int number = 999;

    @Override
    public String toString() {
        return "ClosureExamples{" +
                "number=" + number +
                '}';
    }

    public static <T> String toString(int number, T value) {
        return "[" + number + "]The value is " + String.valueOf(value); //String.valueOf -> null safe -> null이면 "null" 리턴
    }

    public static void main(String[] args) {
//        new ClosureExamples().test1();
//        new ClosureExamples().test2();
//        new ClosureExamples().test3();
        new ClosureExamples().test4();
    }

    private void test1() { //static이 아님
//        final int number = 100;
        int number = 100; //effectively final (결국은 final)
//        number = 1;//Anonymous 에서에러
        //number가 반드시 final이여야함
        testClosure("Anonymous Class", new Runnable() {
            @Override
            public void run() {
                //number = 1; //오류 발생 ->사실상 final
                //System.out.println(number); //this.number 접근안됨 -> 가장가까운 int number=100에 접근
//                System.out.println(this.number); //this.number 접근안됨 -> 가장가까운 int number=100에 접근
                System.out.println(number);
                System.out.println(ClosureExamples.this.number); //this.number 접근안됨 -> 가장가까운 int number=100에 접근
                // int number=100 주석을 해도 에러  -> this는 Runnable의 this.number Runnable에는 number가 없어서 에러
                //this.toString() -> Runnable의 toString() 메서드 호출
            }
        });


//        final Runnable runnable1 = () -> System.out.println(number); // ; 은 lambda expression에 ; 가 아니고 assign하는 자체의 ;
        //final이 아니여도 접근 가능
//        testClosure("Lambda Expression", () -> System.out.println(number));
        //testClosure("Lambda Expression", () -> System.out.println(ClosureExamples.this.number)); //에러 안남
        testClosure("Lambda Expression", () -> {
            System.out.println(this.number); //에러 안남
            System.out.println(number);
        });
        //-> 왜 에러 안남 ?lambda expression 자체는 scope이 없음 this는 ClosureExample의 this가 됨
        //this.toString() -> ClosureExample의 toString() 메서드 호출


        //number = 2; //동일하게 에러남
        //멀티쓰레드가 어떻게 쓸지 모르기 때문에 final
        //Race Condition을 만듬(동시에 바꾸려고함)
    }


    private void test3() {
        System.out.println("toString()" + toString());
        System.out.println("\"ClosureExamples calling toString(int, String)\": " + toString(1, "Hello"));

        testClosure("Anonymous Class", new Runnable() {
            @Override
            public void run() {
                //        System.out.println("toString(int, String): " + toString(1, "Test"));
                System.out.println("toString(int, String) causes compile-time error");
                System.out.println("ClosureExamples.this.toString(int, String): " + ClosureExamples.toString(1, "Test"));
                //this.toString() -> Runnable의 toString() 메서드 호출
            }
        });

        testClosure("Anonymous Class", new Runnable() {
            @Override
            public void run() {
                //static toString() 메서드에러 발생 -> Runnable의 toString으로 인식
                // anonymous class가 가지고 있는 메소드(상속한 메서드 포함)와 이름이 동일한 외부 메소드에
                //접근할 경우 shadowing이 발생 구분을 못함
//                System.out.println("ClosureExamples.this.toString(int, String): " + toString(1,"TEST"));
                System.out.println("toString(int) causes compile-time error");
                System.out.println("ClosureExamples.this.toString(int, String): " + ClosureExamples.this.toString(1, "TEST"));


            }
        });


        testClosure("Lambda Expression", () -> System.out.println("this.toString(): " + toString(1, "TEST")));
        //static toString() 메서드에러 발생 안함
        //scope이 확장 -> 가지고있는 Object로 확장

    }


    private void test2() {


        testClosure("Anonymous Class", new Runnable() {
            @Override
            public void run() {
                System.out.println("this.toString(): " + this.toString());
                //this.toString() -> Runnable의 toString() 메서드 호출
            }
        });

        testClosure("Anonymous Class", new Runnable() {
            @Override
            public void run() {
                System.out.println("ClosureExamples.this.toString(): " + ClosureExamples.this.toString());
                //ClosureExamples.this.toString() -> ClosureExample의 toString() 메서드 호출
            }
        });


        testClosure("Lambda Expression", () -> System.out.println("this.toString(): " + this.toString()));
        //this.toString() -> ClosureExample의 toString() 메서드 호출


    }


    private void test4() {
        int number = 100;
        testClosure("Anonymous Class", new Runnable() {
            @Override
            public void run() {
                // no compile-time error
                int number = 50; //재정의해서 사용하는것은 위험
//                System.out.println(number);
                //another code
                int x = number + 100;
                System.out.println(x);
            }

        });


        testClosure("Lambda Expression", () -> {
            //compile-time error
//            int number = 50; //Enclosing Class Closure Example -> 이미 정의되어 못씀
            //자체 Scope이 없어서 Enclosing Class ClosureExamples로 Scope 확장되어 두번 정의 된것으로 판단
            System.out.println(number);
        });
    }


    private static void testClosure(final String name, Runnable runnable) {
        System.out.println("==================================================");
        System.out.println("Start : " + name);
        runnable.run();
        System.out.println("==================================================");
    }


}
