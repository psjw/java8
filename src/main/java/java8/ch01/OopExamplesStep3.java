package java8.ch01;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

public class OopExamplesStep3 {
    public static void main(String[] args) {
        final MyNewSet<Integer> myNewSet = new MyNewSet<>();
        myNewSet.add(1);
        myNewSet.add(2);
        myNewSet.add(3);
        System.out.println("myNewSet count: " + myNewSet.getCount());   // 3

        final MyNewSet<Integer> myNewSet2 = new MyNewSet<>();
        myNewSet2.addAll(Arrays.asList(1, 2, 3, 4, 5));
        System.out.println("myNewSet2 count: " + myNewSet2.getCount());   // 5
    }
}

class NewSet<E> extends HashSet<E> {
    @Override
    public boolean add(final E e) {
        return add0(e);
    }

    private boolean add0(final E e) {
        return super.add(e);
    }

    @Override
    public boolean addAll(final Collection<? extends E> c) {
        boolean modified = false;
        for (E e : c)
            if (add0(e))
                modified = true;
        return modified;
    }
}

class MyNewSet<E> extends NewSet<E> {
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

