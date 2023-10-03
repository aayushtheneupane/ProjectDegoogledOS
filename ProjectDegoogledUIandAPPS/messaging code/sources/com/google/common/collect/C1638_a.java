package com.google.common.collect;

import java.lang.reflect.Array;

/* renamed from: com.google.common.collect._a */
public final class C1638_a {
    static final Object[] EMPTY_ARRAY = new Object[0];

    /* renamed from: a */
    static Object[] m4553a(Object[] objArr, int i) {
        Object[] c = m4556c(objArr, i);
        System.arraycopy(objArr, 0, c, 0, Math.min(objArr.length, i));
        return c;
    }

    /* renamed from: b */
    static Object[] m4554b(Object[] objArr, int i) {
        for (int i2 = 0; i2 < i; i2++) {
            m4555c(objArr[i2], i2);
        }
        return objArr;
    }

    /* renamed from: c */
    public static Object[] m4556c(Object[] objArr, int i) {
        return (Object[]) Array.newInstance(objArr.getClass().getComponentType(), i);
    }

    /* renamed from: f */
    static Object[] m4557f(Object... objArr) {
        m4554b(objArr, objArr.length);
        return objArr;
    }

    /* renamed from: c */
    static Object m4555c(Object obj, int i) {
        if (obj != null) {
            return obj;
        }
        throw new NullPointerException("at index " + i);
    }
}
