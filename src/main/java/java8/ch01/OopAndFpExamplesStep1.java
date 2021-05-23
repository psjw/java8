package java8.ch01;

public class OopAndFpExamplesStep1 {
    public static void main(String[] args) {
        final CalculatorServiceStep1 calculatorServiceStep1 = new CalculatorServiceStep1();
        int result = calculatorServiceStep1.calculate(1, 1);
        System.out.println(result);
    }
}

class CalculatorServiceStep1 {
    public int calculate(int num1, int num2) {
        return num1 + num2;
    }
}
