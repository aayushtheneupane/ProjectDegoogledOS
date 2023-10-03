package com.google.common.collect;

import java.util.Collection;

/* renamed from: com.google.common.collect.M */
abstract class C1582M extends C1602N {
    Object[] contents;
    int size = 0;

    C1582M(int i) {
        C1630W.m4536e(i, "initialCapacity");
        this.contents = new Object[i];
    }

    private void ensureCapacity(int i) {
        Object[] objArr = this.contents;
        if (objArr.length < i) {
            this.contents = C1638_a.m4553a(objArr, C1602N.m4410L(objArr.length, i));
        }
    }

    public C1582M add(Object obj) {
        if (obj != null) {
            ensureCapacity(this.size + 1);
            Object[] objArr = this.contents;
            int i = this.size;
            this.size = i + 1;
            objArr[i] = obj;
            return this;
        }
        throw new NullPointerException();
    }

    public C1602N addAll(Iterable iterable) {
        if (iterable instanceof Collection) {
            ensureCapacity(((Collection) iterable).size() + this.size);
        }
        for (Object add : iterable) {
            add(add);
        }
        return this;
    }

    /* renamed from: d */
    public C1602N mo8823d(Object... objArr) {
        C1638_a.m4557f(objArr);
        ensureCapacity(this.size + objArr.length);
        System.arraycopy(objArr, 0, this.contents, this.size, objArr.length);
        this.size += objArr.length;
        return this;
    }
}
