package java8.ch08;

public class StreamPrelude {
    public static void main(String[] args) {
        final int abs1 = Math.abs(1);
        final int abs2 = Math.abs(-1);

        //두개의 입력 값이 같은 결과를 갖음
        System.out.println("abs1 = " + abs1);
        System.out.println("abs2 = " + abs2);
        System.out.println("abs1 == abs2 is "+(abs1 == abs2));

        System.out.println(Integer.MIN_VALUE);
        System.out.println(Integer.MAX_VALUE);
        final int minInt = Math.abs(Integer.MIN_VALUE);
        System.out.println("minInt = " + minInt);
    }
}
