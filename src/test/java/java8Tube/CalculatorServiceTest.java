package java8Tube;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


class CalculatorServiceTest {

    @Test
    void testCalculateAdditon() {
//        Calculation calculation = new Addtion();
        Calculation calculation = (i1, i2) -> i1 + i2;
        final int actual = calculation.calculate(1, 1);
        assertThat(actual).isEqualTo(2);
    }

    @Test
    void testCalculateSubstraction() {
        Calculation calculation = new Subtraction();
        final int actual = calculation.calculate(1, 1);
        assertThat(actual).isEqualTo(0);
    }

    @Test
    void testCalculateMultiplication() {
        Calculation calculation = new Multiplication();
        final int actual = calculation.calculate(1, 1);
        assertThat(actual).isEqualTo(1);
    }

    @Test
    void testCalculateDivision() {
        Calculation calculation = new Division();
        final int actual = calculation.calculate(8, 4);
        assertThat(actual).isEqualTo(2);
    }
}


