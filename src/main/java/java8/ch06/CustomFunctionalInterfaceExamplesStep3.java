package java8.ch06;



public class CustomFunctionalInterfaceExamplesStep3 {
    public static void main(String[] args) {
        final InvalidFunctionalInterface anonoymousClass = new InvalidFunctionalInterface() {
            @Override
            public <T> String mkString(T value) {
                return value.toString();
            }
        };
        //value -> value.toString() 부분에서 에러! Target method is generic.
        //final InvalidFunctionalInterface invalidClass = value -> value.toString(); // 에러발생
        System.out.println(anonoymousClass.mkString("anonymous class : "+123));
    }
}

@FunctionalInterface
interface InvalidFunctionalInterface{
    <T> String mkString(T value);
}