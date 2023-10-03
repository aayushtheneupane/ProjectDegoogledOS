package com.google.common.collect;

import com.google.common.base.C1508E;
import java.util.List;

final class SingletonImmutableList extends ImmutableList {

    /* renamed from: LP */
    final transient Object f2487LP;

    SingletonImmutableList(Object obj) {
        if (obj != null) {
            this.f2487LP = obj;
            return;
        }
        throw new NullPointerException();
    }

    public boolean contains(Object obj) {
        return this.f2487LP.equals(obj);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: d */
    public int mo8652d(Object[] objArr, int i) {
        objArr[i] = this.f2487LP;
        return i + 1;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof List)) {
            return false;
        }
        List list = (List) obj;
        if (list.size() != 1 || !this.f2487LP.equals(list.get(0))) {
            return false;
        }
        return true;
    }

    public Object get(int i) {
        C1508E.m3958J(i, 1);
        return this.f2487LP;
    }

    public int hashCode() {
        return this.f2487LP.hashCode() + 31;
    }

    public int indexOf(Object obj) {
        return this.f2487LP.equals(obj) ? 0 : -1;
    }

    public boolean isEmpty() {
        return false;
    }

    public int lastIndexOf(Object obj) {
        return indexOf(obj);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: pl */
    public boolean mo8636pl() {
        return false;
    }

    public ImmutableList reverse() {
        return this;
    }

    public int size() {
        return 1;
    }

    public String toString() {
        String obj = this.f2487LP.toString();
        StringBuilder sb = new StringBuilder(obj.length() + 2);
        sb.append('[');
        sb.append(obj);
        sb.append(']');
        return sb.toString();
    }

    public C1692rb iterator() {
        return C1652ea.m4567B(this.f2487LP);
    }

    public ImmutableList subList(int i, int i2) {
        C1508E.m3963c(i, i2, 1);
        return i == i2 ? ImmutableList.m4204ql() : this;
    }
}
