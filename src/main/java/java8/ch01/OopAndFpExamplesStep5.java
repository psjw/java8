package java8.ch01;


public class OopAndFpExamplesStep5 {
    public static void main(String[] args) {
        FpCalculatorServiceStep5 fpCalculatorService = new FpCalculatorServiceStep5();
        System.out.println("addition :" + fpCalculatorService.calculate(new AdditionStep5(), 11, 4));
        System.out.println("substraction :" + fpCalculatorService.calculate(new SubtractionStep5(), 11, 1));
        System.out.println("multiplication :" + fpCalculatorService.calculate(new MultiplicationStep5(), 11, 2));
        System.out.println("division :" + fpCalculatorService.calculate(new DivisionStep5(), 20, 4));
    }
}


interface CalculationStep5 {
    int calculate(final int num1, final int num2);
}

class AdditionStep5 implements CalculationStep5 {
    @Override
    public int calculate(final int num1, final int num2) {
        return num1 + num2;
    }
}

class SubtractionStep5 implements CalculationStep5 {
    @Override
    public int calculate(final int num1, final int num2) {
        return num1 - num2;
    }
}

class MultiplicationStep5 implements CalculationStep5 {
    @Override
    public int calculate(final int num1, final int num2) {
        return num1 * num2;
    }
}

class DivisionStep5 implements CalculationStep5 {
    @Override
    public int calculate(final int num1, final int num2) {
        return num1 / num2;
    }
}

//Functional Programing
class FpCalculatorServiceStep5 {
    public int calculate(CalculationStep5 calculation, int num1, int num2) {
        if (num1 > 10 && num2 < num1)
            return calculation.calculate(num1, num2);
        else
            throw new IllegalArgumentException("Invalid Input num1 : " + num1 + ", num2 : " + num2);
    }
}
