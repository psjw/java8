package java8.ch01;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

/**
 * 상속(Inheritance)이 캡슐화(Encapsulation)를 망치는 예제입니다.
 */
public class OopExampleStep2 {
    public static void main(String[] args) {
        final MySet<Integer> mySet = new MySet<>();
        mySet.add(1);
        mySet.add(2);
        mySet.add(3);
        System.out.println("mySet count: " + mySet.getCount());   // 3

        final MySet<Integer> mySet2 = new MySet<>();
        mySet2.addAll(Arrays.asList(1, 2, 3, 4, 5));
        System.out.println("mySet2 count: " + mySet2.getCount());   // 10

    }
}

class MySet<E> extends HashSet<E> {
    private int count;

    public int getCount() {
        return count;
    }

    @Override
    public boolean addAll(final Collection c) {
        count += c.size();
        return super.addAll(c);
    }

    @Override
    public boolean add(final E o) {
        count++;
        return super.add(o);
    }
}
