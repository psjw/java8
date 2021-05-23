package java8closure;

import java.lang.invoke.LambdaMetafactory;
import java.util.Comparator;
import java.util.function.Function;

public class ClosureExamples2 {
    private int number = 999;

   private static final Comparator<Integer> cmp = new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o1 < o2 ? -1 : o1.equals(o2) ? 0 : 1;
        }
    };

    public static void main(String[] args) {
        new ClosureExamples2().test();
    }

    private void test() {
//    int number = 100;

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println(number);
            }
        };
        runnable.run();



        Function<Integer, Integer> func = i -> i * 2;
        Runnable runnable1 = () -> System.out.println(number);
//        Runnable $r =indy[bootstrap = LambdaMetafactory, staticargs=[Runnable, lambda$0], dynargs=[number]]
        //private static void lambda$0(int number){ System.out.println(number);}
        runnable1.run();
        //lambda 컴파일 할때 anonymous class로 변경해주나? -> anonymous Class 갯수만큼 별도의 class파일 생성됨
        //lambda는 컴파일 시에 따로 class 생성 되지 않음
        //디컴파일 -p private까지 -> javap -c -p 패키지/클래스명
        // InvokeDynamic  :  static메서드 lambda$test$0(int) 메서드 생성
        // lambda 컴파일 시 람다 레시피가 추가 -> 런타임시 레시피를 가지고 Object를 생성
        // 컴파일 타임에 타입이 결정이 안됨 -> 런타임시 생성되는 기능이 InvokeDynamic
        // MethodHandle 기능 : 메서드 제어(직접 접근 실행 할 수 있는 메소드 레퍼런스로 재사용이 가능한 메소드는 저장했다가 바로 꺼내 쓸 수도 있습니다.)
        //Lambda body가 desugar 되면서 lambda body에 해당되는 코드로 메소드가 생성이 됩니다.

        //Closure가 아니라도 lambda expression에  parameter가 있다면 desugar된 메서드에서도 역시 parameter가 필요합니다.
        //예) product -> product.getName() 같은 경우도
        // private static String lambda$0(Product product){ return product.getName();} 같은 형태로
        //메소드에 파라미터가 추가되지면 이경우는 product이 dynargs에 추가 되지 않습니다.
    }

}
