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

import static java8.ch07.FunctionalInterfaceExamplesStep6.total;

public class FunctionalInterfaceExamplesStep6 {
    public static void main(String[] args) {
        ProductStep6 productA = new ProductStep6(1L, "A", new BigDecimal("10.00"));
        ProductStep6 productC = new ProductStep6(3L, "A", new BigDecimal("17.45"));
        ProductStep6 productD = new ProductStep6(4L, "A", new BigDecimal("20.00"));


        final OrderStep6 order = new OrderStep6(1L, "on-1234", Arrays.asList(
                new OrderedItemStep6(1L, productA, 2),
                new OrderedItemStep6(2L, productC, 1),
                new OrderedItemStep6(3L, productD, 10)
        ));

        BigDecimal orderTotal = BigDecimal.ZERO;
        for (OrderedItemStep6 item : order.getItems()) {
            orderTotal = orderTotal.add(item.getProduct().getPrice().multiply(new BigDecimal(item.getQuantity())));
        }


        System.out.println("order total in old way: " + orderTotal);
        System.out.println("           order total: " + order.totalPrice());
    }

    public static <T> List<T> filter(List<T> list, Predicate<? super T> predicate) {
        final List<T> result = new ArrayList<>();
        for (T t : list) {
            if (predicate.test(t)) {
                result.add(t);
            }
        }
        return result;
    }

    public static <T, R> List<R> map(List<T> list, Function<T, R> function) {
        final List<R> result = new ArrayList<>();
        for (T t : list) {
            result.add(function.apply(t));
        }
        return result;
    }

    public static <T> BigDecimal total(List<T> list, Function<T, BigDecimal> function) {
        BigDecimal total = BigDecimal.ZERO;
        for (T t : list) {
            total = total.add(function.apply(t));
        }
        return total;
    }

}

@Data
@AllArgsConstructor
class ProductStep6 {
    private Long id;
    private String name;
    private BigDecimal price;
}

@ToString(callSuper = true)
class DiscountedProductStep6 extends ProductStep6 {
    public DiscountedProductStep6(Long id, String name, BigDecimal price) {
        super(id, name, price);
    }
}

@Data
@AllArgsConstructor
class OrderedItemStep6 {
    private Long id;
    private ProductStep6 product;
    private int quantity;

    public BigDecimal getItemTotal() {
        return product.getPrice().multiply(new BigDecimal(quantity));
    }
}

@AllArgsConstructor
@Data
class OrderStep6 {
    private Long id;
    private String orderName;
    private List<OrderedItemStep6> items;

    public BigDecimal totalPrice() {
        return total(items, item -> item.getItemTotal());
    }
}