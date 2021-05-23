package java8.ch07;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class FunctionalInterfaceExamplesStep4 {
    public static void main(String[] args) {
        ProductStep4 productA = new ProductStep4(1L, "A", new BigDecimal("10.00"));
        ProductStep4 productB = new ProductStep4(2L, "A", new BigDecimal("55.00"));
        ProductStep4 productC = new ProductStep4(3L, "A", new BigDecimal("17.45"));
        ProductStep4 productD = new ProductStep4(4L, "A", new BigDecimal("20.00"));
        ProductStep4 productE = new ProductStep4(5L, "A", new BigDecimal("110.99"));
        final List<ProductStep4> products = Arrays.asList(
                productA,
                productB,
                productC,
                productD,
                productE
        );

        final List<ProductStep4> expensiveProducts =
                filter(products, product -> product.getPrice().compareTo(new BigDecimal("50")) > 0);
        final List<DiscountedProductStep4> discountedProducts = map(expensiveProducts
                , product -> new DiscountedProductStep4(product.getId()
                        , product.getName()
                        ,product.getPrice().multiply(new BigDecimal("0.5"))));

        System.out.println("expensive products : " + expensiveProducts);
        System.out.println("discounted products : " + discountedProducts);
    }

    private static <T, R> List<R> map(List<T> list, Function<T, R> function) {
        List<R> result = new ArrayList<>();
        for(T t : list){
            result.add(function.apply(t));
        }
        return result;
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
class ProductStep4 {
    private Long id;
    private String name;
    private BigDecimal price;
}

@ToString(callSuper = true)
class DiscountedProductStep4 extends ProductStep4 {
    public DiscountedProductStep4(Long id, String name, BigDecimal price) {
        super(id, name, price);
    }
}