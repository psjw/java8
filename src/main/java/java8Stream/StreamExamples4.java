package java8Stream;

import java8Tube.FunctionalInterfaceExample;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

public class StreamExamples4 {
    public static void main(String[] args) {
        Stream.of(1, 2, 3, 4, 5)
                .collect(toList());

        Stream.of(1, 2, 3, 4, 5)
                .collect(toSet());

        Stream.of(1, 2, 3, 4, 5)
                .map(i -> String.valueOf(i))
                .collect(joining());


        final List<Product> products = Arrays.asList(
                new Product(1L, "A", new BigDecimal("100.50")),
                new Product(2L, "B", new BigDecimal("23.00")),
                new Product(3L, "C", new BigDecimal("31.45")),
                new Product(4L, "D", new BigDecimal("80.20")),
                new Product(5L, "E", new BigDecimal("7.50"))
        );
        System.out.println("Products.price >= 30 : \n" +
                products.stream()
                        .filter(product -> product.getPrice().compareTo(new BigDecimal("30")) >= 0)
                        .collect(Collectors.toList()));
        System.out.println("===============================================================");
        System.out.println("Products.price >= 30(with joining(\"\\n\")) :  \n" +
                products.stream()
                        .filter(product -> product.getPrice().compareTo(new BigDecimal("30")) >= 0)
                        .map(product -> product.toString())
                        .collect(Collectors.joining("\n")));//joining은 Product 타입이라 오류 String이여야함
        System.out.println("===============================================================");
        System.out.println("IntStream.sum : " + IntStream.of(1, 2, 3, 4, 5).sum());
        System.out.println("===============================================================");

        //reduce(초기값(initail value),
        //(price1, price2)  파라미터=Arity  파리미터가 2개 인 경우 Arity가 2인 경우 Binary 혹은 a-ary라고 하기도 함.
        // (price1, price2) -> price1.add(price2) (이전값, 새로받은 값) -> 함수 실행의 결과 리턴 -> 초기값 있는 이유는 처음에는 이전값이 없다.
        System.out.println("Total Price : " +
                products.stream()
                        .map(product -> product.getPrice()) // price만 남겨야 됨 . 타입이 모두 BigDecimal로 변경
                        //Identity function의 기능 :  0
                        //reduce  Terminal Operation 메소드 -> map 1번 하고  reduce수행
                        .reduce(BigDecimal.ZERO, (price1, price2) -> price1.add(price2)) //여러개의 다른 종류의 파라미터를 받음 sum이 없다. -> element를 하나하나 줄여나가기 때문에 reduce
        );
        //map으로 price만 남겨야 오류 안남 map없이 아래와 같이 한다면 리턴타입이 Product이기 때문에 오류발생
        // 최초에 BigDecimal.ZERO가 product1로 들아가야함 -> 된다해도 결과가 Bigdeciaml이 다시 product1으로 들어가야되므로 에러
//                .reduce(BigDecimal.ZERO, (product1, product2) -> product1.getPrice().add(product2.getPrice())); //여러개의 다른 종류의 파라미터를 받음 sum이 없다. -> element를 하나하나 줄여나가기 때문에 reduce
        System.out.println("===============================================================");
        System.out.println("Total Price of Products.price >= 30  : " +
                products.stream().filter(product -> product.getPrice().compareTo(new BigDecimal("30")) >= 0)
                        .map(product -> product.getPrice())
//                        .reduce(BigDecimal.ZERO, (price1, price2) -> price1.add(price2))
                .reduce(BigDecimal.ZERO, BigDecimal::add)
        );

        System.out.println("===============================================================");
        //매번 new BigDecimal("30") 이 생성되기 떄문에 variable에 넣고 재사용하는게 좋다.
        //특히 BigDecimal은 immutable object(불변객체)이기 때문에 안전하게 재사용 가능
        System.out.println("# of Products.price >= 30  : " +
                products.stream().filter(product -> product.getPrice().compareTo(new BigDecimal("30")) >= 0)
                        .count()
        );
        System.out.println("===============================================================");
        final OrderedItem item1 = new OrderedItem(1L, products.get(0), 1);
        final OrderedItem item2 = new OrderedItem(2L, products.get(2), 3);
        final OrderedItem item13= new OrderedItem(3L, products.get(4), 10);
        final Order order = new Order(1L, Arrays.asList(item1, item2, item13));
        System.out.println("order.totalPrice() : "+order.totalPrice());

    }
}


@Data
@AllArgsConstructor
class Product {
    private Long id;
    private String name;
    private BigDecimal price;

}

@Data
@AllArgsConstructor
class OrderedItem {
    private Long id;
    private Product product;
    private int quantity;

    public BigDecimal getTotalPrice() {
        return product.getPrice().multiply(new BigDecimal(quantity));
    }
}

@AllArgsConstructor
@Data
class Order {
    private Long id;
    private List<OrderedItem> items;
    public BigDecimal totalPrice(){

        return items.stream()
                .map(item -> item.getTotalPrice())
                .reduce(BigDecimal.ZERO, (price1, price2) -> price1.add(price2));
    }
}
