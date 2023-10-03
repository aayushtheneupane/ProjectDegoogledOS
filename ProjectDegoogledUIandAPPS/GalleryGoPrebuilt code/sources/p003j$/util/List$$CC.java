package p003j$.util;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.ListIterator;
import p003j$.util.function.UnaryOperator;

/* renamed from: j$.util.List$$CC */
public abstract /* synthetic */ class List$$CC {
    public static void replaceAll$$dflt$$(List list, UnaryOperator unaryOperator) {
        unaryOperator.getClass();
        ListIterator listIterator = list.listIterator();
        while (listIterator.hasNext()) {
            listIterator.set(unaryOperator.apply(listIterator.next()));
        }
    }

    public static void sort$$dflt$$(List list, Comparator comparator) {
        Object[] array = list.toArray();
        Arrays.sort(array, comparator);
        ListIterator listIterator = list.listIterator();
        for (Object obj : array) {
            listIterator.next();
            listIterator.set(obj);
        }
    }

    public static Spliterator spliterator$$dflt$$(List list) {
        return Spliterators.spliterator(list, 16);
    }
}
