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

public class FunctionalInterfaceExamplesStep5 {
    public static void main(String[] args) {
        ProductStep5 productA = new ProductStep5(1L, "A", new BigDecimal("10.00"));
        ProductStep5 productB = new ProductStep5(2L, "A", new BigDecimal("55.00"));
        ProductStep5 productC = new ProductStep5(3L, "A", new BigDecimal("17.45"));
        ProductStep5 productD = new ProductStep5(4L, "A", new BigDecimal("20.00"));
        ProductStep5 productE = new ProductStep5(5L, "A", new BigDecimal("110.99"));
        final List<ProductStep5> products = Arrays.asList(
                productA,
                productB,
                productC,
                productD,
                productE
        );
        final List<ProductStep5> expensiveProducts = filter(products, product
                -> product.getPrice().compareTo(new BigDecimal("50")) > 0);

        List<DiscountedProductStep5> discountedProducts = new ArrayList<>();

        discountedProducts = map(expensiveProducts,
                product ->
                        new DiscountedProductStep5(product.getId(), product.getName()
                                , product.getPrice().multiply(new BigDecimal("0.5"))));

        Predicate<ProductStep5> lessTanOrEqualsTo30 = product
                -> product.getPrice().compareTo(new BigDecimal("30")) <= 0;
        //reason: Incompatible equality constraint: ProductStep5 and DiscountedProductStep5
        //Predicate<Product> lessTanOrEqualsTo30  Product로 바꾸면 여기서 에러발생
        System.out.println("discounted products (<=$30): " + filter(discountedProducts, lessTanOrEqualsTo30));
        System.out.println("discounted products (<=$30): " + filter(products, lessTanOrEqualsTo30)); //에러 발생 super 타입에서 사용 안됨
        //해결방법 :  private static <T> List<T> filter(List<T> list, Predicate<T>  predicate) -> private static <T> List<T> filter(List<T> list, Predicate<? super T>  predicate) 변경
        // super의 getPrice()를 사용하므로 아무 문제 없다.
    }

    private static <T, R> List<R> map(List<T> list, Function<T, R> function) {
        List<R> result = new ArrayList<>();
        for(T t : list){
            result.add(function.apply(t));
        }
        return result;
    }
    private static <T> List<T> filter(List<T> list, Predicate<? super T> predicate) {
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
class ProductStep5 {
    private Long id;
    private String name;
    private BigDecimal price;
}

@ToString(callSuper = true)
class DiscountedProductStep5 extends ProductStep5 {
    public DiscountedProductStep5(Long id, String name, BigDecimal price) {
        super(id, name, price);
    }
}