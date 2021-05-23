package java8.ch07;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class FunctionalInterfaceExamplesStep2 {
    public static void main(String[] args) {
        ProductStep2 productA = new ProductStep2(1L, "A", new BigDecimal("10.00"));
        ProductStep2 productB = new ProductStep2(2L, "A", new BigDecimal("55.00"));
        ProductStep2 productC = new ProductStep2(3L, "A", new BigDecimal("17.45"));
        ProductStep2 productD = new ProductStep2(4L, "A", new BigDecimal("20.00"));
        ProductStep2 productE = new ProductStep2(5L, "A", new BigDecimal("110.99"));
        final List<ProductStep2> products = Arrays.asList(
                productA,
                productB,
                productC,
                productD,
                productE
        );

        List<ProductStep2> result=filter(products, product -> product.getPrice().compareTo(new BigDecimal("20")) >= 0);
        System.out.println("product >= $20 : " + result);
    }

    private static <T> List<T> filter(List<T> list, Predicate<T>  predicate) {
        List<T> result = new ArrayList<>();
        for (T t : list) {
            if (predicate.test(t)) {
                result.add(t);
            }
        }
        return result;
    }
}

@Data
@AllArgsConstructor
class ProductStep2 {
    private Long id;
    private String name;
    private BigDecimal price;
}