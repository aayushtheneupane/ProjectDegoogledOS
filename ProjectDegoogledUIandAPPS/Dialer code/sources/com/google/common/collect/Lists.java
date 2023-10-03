package com.google.common.collect;

import android.support.p002v7.appcompat.R$style;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public final class Lists {
    static int computeArrayListCapacity(int i) {
        Collections2.checkNonnegative(i, "arraySize");
        return R$style.saturatedCast(((long) i) + 5 + ((long) (i / 10)));
    }

    public static <E> ArrayList<E> newArrayList(Iterator<? extends E> it) {
        ArrayList<E> arrayList = new ArrayList<>();
        Collections2.addAll(arrayList, it);
        return arrayList;
    }

    public static <E> ArrayList<E> newArrayListWithCapacity(int i) {
        Collections2.checkNonnegative(i, "initialArraySize");
        return new ArrayList<>(i);
    }

    @SafeVarargs
    public static <E> ArrayList<E> newArrayList(E... eArr) {
        if (eArr != null) {
            ArrayList<E> arrayList = new ArrayList<>(computeArrayListCapacity(eArr.length));
            Collections.addAll(arrayList, eArr);
            return arrayList;
        }
        throw new NullPointerException();
    }
}
