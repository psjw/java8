package java8.ch05;

import java.util.concurrent.TimeUnit;

public class SupplierExamplesStep1 {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        printIfValidIdex(0, getVeryExpensiveValue());
        printIfValidIdex(-1, getVeryExpensiveValue());
        printIfValidIdex(-2, getVeryExpensiveValue());
        System.out.println("It took " + ((System.currentTimeMillis() - start) / 1000));
    }

    public static void printIfValidIdex(int num, String value) {
        if (num >= 0) {
            System.out.println("The value is " + value + ".");
        } else {
            System.out.println("Invalid");
        }
    }

    public static String getVeryExpensiveValue() {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "psjw";
    }
}
