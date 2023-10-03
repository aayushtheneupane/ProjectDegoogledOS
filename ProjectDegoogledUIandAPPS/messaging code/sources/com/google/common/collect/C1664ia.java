package com.google.common.collect;

import com.google.common.primitives.C1722a;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;

/* renamed from: com.google.common.collect.ia */
public final class C1664ia {
    /* renamed from: c */
    public static LinkedList m4588c(Iterable iterable) {
        LinkedList linkedList = new LinkedList();
        if (iterable instanceof Collection) {
            linkedList.addAll(C1552A.m4040b(iterable));
        } else if (iterable != null) {
            C1652ea.m4572a((Collection) linkedList, iterable.iterator());
        } else {
            throw new NullPointerException();
        }
        return linkedList;
    }

    static int computeArrayListCapacity(int i) {
        C1630W.m4536e(i, "arraySize");
        return C1722a.m4652G(((long) i) + 5 + ((long) (i / 10)));
    }

    public static ArrayList newArrayList(Object... objArr) {
        if (objArr != null) {
            ArrayList arrayList = new ArrayList(computeArrayListCapacity(objArr.length));
            Collections.addAll(arrayList, objArr);
            return arrayList;
        }
        throw new NullPointerException();
    }
}
