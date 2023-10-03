package com.google.common.collect;

import com.google.common.base.C1504A;
import java.util.Collection;

/* renamed from: com.google.common.collect.A */
public final class C1552A {

    /* renamed from: lN */
    static final C1504A f2421lN = C1504A.m3943Ua(", ").mo8509Va("null");

    /* renamed from: a */
    static boolean m4039a(Collection collection, Object obj) {
        if (collection != null) {
            try {
                return collection.contains(obj);
            } catch (ClassCastException | NullPointerException unused) {
                return false;
            }
        } else {
            throw new NullPointerException();
        }
    }

    /* renamed from: b */
    static Collection m4040b(Iterable iterable) {
        return (Collection) iterable;
    }

    /* renamed from: bb */
    static StringBuilder m4041bb(int i) {
        C1630W.m4536e(i, "size");
        return new StringBuilder((int) Math.min(((long) i) * 8, 1073741824));
    }
}
