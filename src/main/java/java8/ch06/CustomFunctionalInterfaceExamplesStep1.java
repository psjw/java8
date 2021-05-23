package java8.ch06;

public class CustomFunctionalInterfaceExamplesStep1 {
    public static void main(String[] args) {
        print(1, 2, 3, (t1, t2, t3) -> String.valueOf(t1 + t2 + t3));
        print("Area is ", 12, 20, (message, length, width)
                -> message + (length * width));
        print(1L, "psjw", "test@gmail.com", (id, name, email)
                -> "User info : ID = " + id + ", name = " + name + ", email = " + email);

        Function3<Integer, Integer, Integer, String> f3 = (i1, i2, i3) -> String.valueOf(1 + 2 + 3);
    }
    private static <T1, T2, T3> void print(T1 t1, T2 t2, T3 t3, Function3<T1, T2, T3, String> function) {
        System.out.println(function.apply(t1, t2, t3));
    }
}

@FunctionalInterface
interface Function3<T1, T2, T3, R> {
    R apply(T1 t1, T2 t2, T3 t3);
    // void print(int i) //abstract method 2개 되면 오류
}