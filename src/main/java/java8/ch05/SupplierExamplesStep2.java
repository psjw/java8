package java8.ch05;

import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

public class SupplierExamplesStep2 {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        Supplier<String> supplier = () -> getVeryExpensiveValue();
        printIfValidIdex(0, supplier);
        printIfValidIdex(-1, supplier);
        printIfValidIdex(-2, supplier);
        System.out.println("It took " + ((System.currentTimeMillis() - start) / 1000));
    }

    public static void printIfValidIdex(int num, Supplier<String> valueSupplier) {
        if (num >= 0) {
            System.out.println("The value is " + valueSupplier.get() + ".");
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
