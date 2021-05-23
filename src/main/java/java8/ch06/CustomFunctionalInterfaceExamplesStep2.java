package java8.ch06;

import java.math.BigDecimal;

public class CustomFunctionalInterfaceExamplesStep2 {
    public static void main(String[] args) {
        BigDecimalToCurrency bigDecimalToCurrency = bd -> "$" + bd.toString();
        System.out.println(bigDecimalToCurrency.toCurrency(new BigDecimal("120.00")));
    }
}

@FunctionalInterface
interface BigDecimalToCurrency{
    String toCurrency(BigDecimal value);
}