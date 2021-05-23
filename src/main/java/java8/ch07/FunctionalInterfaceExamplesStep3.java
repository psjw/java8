package java8.ch07;

import java8Tube.FunctionalInterfaceExample;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class FunctionalInterfaceExamplesStep3 {
    public static void main(String[] args) {
        ProductStep3 productA = new ProductStep3(1L, "A", new BigDecimal("10.00"));
        ProductStep3 productB = new ProductStep3(2L, "A", new BigDecimal("55.00"));
        ProductStep3 productC = new ProductStep3(3L, "A", new BigDecimal("17.45"));
        ProductStep3 productD = new ProductStep3(4L, "A", new BigDecimal("20.00"));
        ProductStep3 productE = new ProductStep3(5L, "A", new BigDecimal("110.99"));
        final List<ProductStep3> products = Arrays.asList(
                productA,
                productB,
                productC,
                productD,
                productE
        );

        final List<ProductStep3> expensiveProducts =
                filter(products, product -> product.getPrice().compareTo(new BigDecimal("50")) > 0);
        List<DiscountedProductStep3> discountedProducts = new ArrayList<>();
        for (final ProductStep3 product : expensiveProducts) {
            discountedProducts.add(new DiscountedProductStep3(product.getId()
                    , product.getName(), product.getPrice().multiply(new BigDecimal("0.5"))));
        }
        System.out.println("expensive products : " + expensiveProducts);
        System.out.println("discounted products : " + discountedProducts);
    }

    private static <T> List<T> filter(List<T> list, Predicate<T> predicate) {
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
class ProductStep3 {
    private Long id;
    private String name;
    private BigDecimal price;
}

@ToString(callSuper = true)
class DiscountedProductStep3 extends ProductStep3 {
    public DiscountedProductStep3(Long id, String name, BigDecimal price) {
        super(id, name, price);
    }
}