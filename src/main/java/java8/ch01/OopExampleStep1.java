package java8.ch01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

/**
 * 상속(Inheritance)이 캡슐화(Encapsulation)를 망치는 예제입니다.
 */
public class OopExampleStep1 {
    public static void main(String[] args) {
        final MyList<Integer> list = new MyList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        System.out.println("list count: " + list.getCount());   // 3

        final MyList<Integer> list2 = new MyList<>();
        list2.addAll(Arrays.asList(1, 2, 3, 4, 5));
        System.out.println("list2 count: " + list2.getCount());  // 5

    }
}

class MyList<E> extends ArrayList<E> {
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

