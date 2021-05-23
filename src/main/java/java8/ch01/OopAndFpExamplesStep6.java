package java8.ch01;


public class OopAndFpExamplesStep6 {
    public static void main(String[] args) {
        FpCalculatorServiceStep6 fpCalculatorService = new FpCalculatorServiceStep6();
        //함수를 변수에 넣어 전달 가능
        final CalculationStep6 addtion = (i1, i2) -> i1 + i2;
        System.out.println("addition :" + fpCalculatorService.calculate(addtion, 11, 4));
        System.out.println("substraction :" + fpCalculatorService.calculate((i1, i2) -> i1 - i2, 11, 1));
        System.out.println("multiplication :" + fpCalculatorService.calculate((i1, i2) -> i1 * i2, 11, 2));
        System.out.println("division :" + fpCalculatorService.calculate((i1, i2) -> i1 / i2, 20, 4));
        System.out.println("custom calc.  :" + fpCalculatorService.calculate((i1, i2) -> ((i1 + i2) *2)/i2, 20, 4));
    }
}


interface CalculationStep6 {
    int calculate(final int num1, final int num2);
}

class AdditionStep6 implements CalculationStep6 {
    @Override
    public int calculate(final int num1, final int num2) {
        return num1 + num2;
    }
}

class SubtractionStep6 implements CalculationStep6 {
    @Override
    public int calculate(final int num1, final int num2) {
        return num1 - num2;
    }
}

class MultiplicationStep6 implements CalculationStep6 {
    @Override
    public int calculate(final int num1, final int num2) {
        return num1 * num2;
    }
}

class DivisionStep6 implements CalculationStep6 {
    @Override
    public int calculate(final int num1, final int num2) {
        return num1 / num2;
    }
}

//Functional Programing
class FpCalculatorServiceStep6 {
    public int calculate(CalculationStep6 calculation, int num1, int num2) {
        if (num1 > 10 && num2 < num1)
            return calculation.calculate(num1, num2);
        else
            throw new IllegalArgumentException("Invalid Input num1 : " + num1 + ", num2 : " + num2);
    }
}
