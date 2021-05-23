package java8.ch01;

public class OopAndFpExamplesStep4 {
    public static void main(String[] args) {
        final CalculatorServiceStep4 calculatorService =
                new CalculatorServiceStep4(new AdditionStep4(), new SubtractionStep4()
                        , new MultiplicationStep4(), new DivisionStep4());

        final int additionResult = calculatorService.add(11, 4);
        System.out.println(additionResult);

        final int subtractionResult = calculatorService.subtract(11, 1);
        System.out.println(subtractionResult);

        final int multiplicationResult = calculatorService.multiply(11, 2);
        System.out.println(multiplicationResult);

        final int divisionResult = calculatorService.divide(20, 4);
        System.out.println(divisionResult);
    }
}


interface CalculationStep4 {
    int calculate(final int num1, final int num2);
}

class AdditionStep4 implements CalculationStep4 {
    @Override
    public int calculate(final int num1, final int num2) {
        return num1 + num2;
    }
}

class SubtractionStep4 implements CalculationStep4 {
    @Override
    public int calculate(final int num1, final int num2) {
        return num1 - num2;
    }
}

class MultiplicationStep4 implements CalculationStep4 {
    @Override
    public int calculate(final int num1, final int num2) {
        return num1 * num2;
    }
}

class DivisionStep4 implements CalculationStep4 {
    @Override
    public int calculate(int num1, int num2) {
        return num1 / num2;
    }
}

class CalculatorServiceStep4 {
    private final CalculationStep4 addition;
    private final CalculationStep4 subtraction;
    private final CalculationStep4 multiplication;
    private final CalculationStep4 division;

    public CalculatorServiceStep4(final AdditionStep4 addition
            , final SubtractionStep4 subtraction, final MultiplicationStep4 multiplication
            , final DivisionStep4 division) {
        this.addition = addition;
        this.subtraction = subtraction;
        this.multiplication = multiplication;
        this.division = division;
    }

    public int add(final int num1, final int num2) {
        if (num1 > 10 && num2 < num1) { // boilerplate code
            return addition.calculate(num1, num2);
        } else { // boilerplate code
            throw new IllegalArgumentException("Invalid input num1: " + num1 + ", num2: " + num2); // boilerplate code
        } // boilerplate code
    }

    public int subtract(final int num1, final int num2) {
        if (num1 > 10 && num2 < num1) { // boilerplate code
            return subtraction.calculate(num1, num2);
        } else { // boilerplate code
            throw new IllegalArgumentException("Invalid input num1: " + num1 + ", num2: " + num2); // boilerplate code
        } // boilerplate code
    }

    public int multiply(final int num1, final int num2) {
        if (num1 > 10 && num2 < num1) { // boilerplate code
            return multiplication.calculate(num1, num2);
        } else { // boilerplate code
            throw new IllegalArgumentException("Invalid input num1: " + num1 + ", num2: " + num2); // boilerplate code
        } // boilerplate code
    }

    public int divide(final int num1, final int num2) {
        if (num1 > 10 && num2 < num1) { // boilerplate code
            return division.calculate(num1, num2);
        } else { // boilerplate code
            throw new IllegalArgumentException("Invalid input num1: " + num1 + ", num2: " + num2); // boilerplate code
        } // boilerplate code
    }
}
