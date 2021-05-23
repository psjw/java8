package java8.ch01;


public class OopAndFpExamplesStep3 {
    public static void main(String[] args) {
        final CalculatorServiceStep3 calculatorServiceStep3_1 = new CalculatorServiceStep3(new AdditionStep3());
        final int additionResult = calculatorServiceStep3_1.calculate(11, 4);
        System.out.println("add : "+additionResult);

        final CalculatorServiceStep3 calculatorServiceStep3_2 = new CalculatorServiceStep3(new SubtractionStep3());
        final int subtractionResult = calculatorServiceStep3_2.calculate(11, 1);
        System.out.println("substract : "+subtractionResult);

        final CalculatorServiceStep3 calculatorServiceStep3_3 = new CalculatorServiceStep3(new MultiplicationStep3());
        final int multiplicationResult = calculatorServiceStep3_3.calculate(11, 2);
        System.out.println("multiply : "+multiplicationResult);

        final CalculatorServiceStep3 calculatorServiceStep3_4 = new CalculatorServiceStep3(new DivisionStep3());
        final int divisionResult = calculatorServiceStep3_4.calculate(20, 4);
        System.out.println("divide : "+divisionResult);
    }
}

interface CalculationStep3 {
    int calculate(final int num1, final int num2);
}

class AdditionStep3 implements CalculationStep3 {
    @Override
    public int calculate(final int num1, final int num2) {
        return num1 + num2;
    }
}

class SubtractionStep3 implements CalculationStep3 {
    @Override
    public int calculate(final int num1, final int num2) {
        return num1 - num2;
    }
}

class MultiplicationStep3 implements CalculationStep3 {
    @Override
    public int calculate(final int num1, final int num2) {
        return num1 * num2;
    }
}

class DivisionStep3 implements CalculationStep3 {
    @Override
    public int calculate(int num1, int num2) {
        return num1 / num2;
    }
}

//단일 책임 원칙 : 클래스는 하나의 책임만 가진다.(object 변화가 있을 경우 한가지 변화만 가져야 한다)
class CalculatorServiceStep3 {
    private final CalculationStep3 calculation;

    public CalculatorServiceStep3(CalculationStep3 calculation) {
        this.calculation = calculation;
    }

    public int calculate(int num1, int num2) {
        if (num1 > 10 && num2 < num1)
            return calculation.calculate(num1, num2);
        else
            throw new IllegalArgumentException("Invalid Input num1 : " + num1 + ", num2 : " + num2);
    }
}
