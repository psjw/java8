package java8.ch07;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FunctionalInterfaceExamplesStep1 {
    public static void main(String[] args) {
        ProductStep1 productA = new ProductStep1(1L, "A", new BigDecimal("10.00"));
        ProductStep1 productB = new ProductStep1(2L, "A", new BigDecimal("55.00"));
        ProductStep1 productC = new ProductStep1(3L, "A", new BigDecimal("17.45"));
        ProductStep1 productD = new ProductStep1(4L, "A", new BigDecimal("20.00"));
        ProductStep1 productE = new ProductStep1(5L, "A", new BigDecimal("110.99"));
        final List<ProductStep1> products = Arrays.asList(
                productA,
                productB,
                productC,
                productD,
                productE
        );
        List<ProductStep1> result = new ArrayList<>();
        for (final ProductStep1 product : products) {
            BigDecimal twenty = new BigDecimal("20");
            if (product.getPrice().compareTo(twenty) >= 0) {
                result.add(product);
            }
        }
        System.out.println("product >= $20 : " + result);
    }
}

@Data
@AllArgsConstructor
class ProductStep1 {
    private Long id;
    private String name;
    private BigDecimal price;
}