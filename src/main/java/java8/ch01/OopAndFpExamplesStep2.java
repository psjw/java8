package java8.ch01;

public class OopAndFpExamplesStep2 {
    public static void main(String[] args) {
        final CalculatorServiceStep2 calculatorService = new CalculatorServiceStep2();
        final int additionResult = calculatorService.calculate('+', 1, 1);
        System.out.println("add : "+additionResult);
        final int subtractionResult = calculatorService.calculate('-', 1, 1);
        System.out.println("substract : "+subtractionResult);
        final int multiplicationResult = calculatorService.calculate('*', 1, 1);
        System.out.println("multiply"+multiplicationResult);
        final int divisionResult = calculatorService.calculate('/', 8, 4);
        System.out.println("divide : "+divisionResult);
    }
}
class CalculatorServiceStep2 {
    public int calculate(char calculation, int num1, int num2) {
        if (calculation == '+') {
            return num1 + num2;
        } else if (calculation == '-') {
            return num1 - num2;
        } else if (calculation == '*') {
            return num1 * num2;
        } else if (calculation == '/') {
            return num1 / num2;
        } else {
            throw new IllegalArgumentException("Unknown calculation : " + calculation);
        }
    }
}
