package java8methodreference;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.function.Function;

public class MethodReferenceExamples2Constructor {
    public static void main(String[] args) {
        final Section section1 = new Section(1);

        final Function<Integer, Section> sectionFactory = number -> new Section(number);
        final Section section1WithLambdaExpression = sectionFactory.apply(1);

        final Function<Integer,Section> sectionFactoryWithMethodReference = Section::new; //여기서 Object가 생성되는게 아님
        final Section section1WithMethodReference = sectionFactoryWithMethodReference.apply(1);

        System.out.println(section1);
        System.out.println(section1WithLambdaExpression);
        System.out.println(section1WithMethodReference);

        final OldProduct product=new OldProduct(1L, "A", new BigDecimal("100"));
        System.out.println(product);
        //product = new Product(2L, "B", new BigDecimal("11")); //실수 할 이유가 없음
        final OldProductCreator productCreator = OldProduct::new;
        System.out.println(productCreator.create(1L, "A", new BigDecimal("100")));
        System.out.println("==================================\n");
        ProductA a = createProduct(1L, "A", new BigDecimal("123"), ProductA::new);
        ProductB b = createProduct(2L, "B", new BigDecimal("111"), ProductB::new);
        ProductC c = createProduct(3L, "C", new BigDecimal("10"), ProductC::new);
        System.out.println(a);
        System.out.println(b);
        System.out.println(c);
    }

    private static <T extends Product> T createProduct(final Long id,
                                                       final String name,
                                                       final BigDecimal price,
                                                       final ProductCreator<T> productCreator) {
        //final 실수를 줄여 코드가 안정적임
        if(id == null || id <1L){
            throw new IllegalArgumentException("The id must be a positive Long");
        }
        if(name == null || name.isEmpty()){
            throw new IllegalArgumentException("The name is not given.");
        }
        if(price == null  || price.compareTo(BigDecimal.ZERO) <= 0){ // price>=ZERO
            throw new IllegalArgumentException("The price must be greater than 0..");
        }
        return productCreator.create(id, name, price);
    }
}


@FunctionalInterface
interface ProductCreator<T extends Product> {
    T create(Long id, String name, BigDecimal price);
}


@FunctionalInterface
interface OldProductCreator {
    OldProduct create(Long id, String name, BigDecimal price);
}

@AllArgsConstructor
@Data
class Section {
    private int number;
}


@AllArgsConstructor
@Data
class OldProduct {
    private Long id;
    private String name;
    private BigDecimal price;
}

@AllArgsConstructor
@Data
abstract class Product {
    private Long id;
    private String name;
    private BigDecimal price;
}


class ProductA extends Product{
    public ProductA(Long id, String name, BigDecimal price) {
        super(id, name, price);
    }

    @Override
    public String toString() {
        return "ProductA = "+super.toString();
    }
}
class ProductB extends Product{

    public ProductB(Long id, String name, BigDecimal price) {
        super(id, name, price);
    }
    @Override
    public String toString() {
        return "ProductB = "+super.toString();
    }
}

class ProductC extends Product{

    public ProductC(Long id, String name, BigDecimal price) {
        super(id, name, price);
    }
    @Override
    public String toString() {
        return "ProductC = "+super.toString();
    }
}