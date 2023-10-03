package com.google.common.collect;

import java.util.Set;

final class SingletonImmutableSet extends ImmutableSet {

    /* renamed from: LP */
    final transient Object f2488LP;

    /* renamed from: NP */
    private transient int f2489NP;

    SingletonImmutableSet(Object obj) {
        if (obj != null) {
            this.f2488LP = obj;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: Rl */
    public boolean mo8649Rl() {
        return this.f2489NP != 0;
    }

    public boolean contains(Object obj) {
        return this.f2488LP.equals(obj);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: d */
    public int mo8652d(Object[] objArr, int i) {
        objArr[i] = this.f2488LP;
        return i + 1;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Set)) {
            return false;
        }
        Set set = (Set) obj;
        if (set.size() != 1 || !this.f2488LP.equals(set.iterator().next())) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        int i = this.f2489NP;
        if (i != 0) {
            return i;
        }
        int hashCode = this.f2488LP.hashCode();
        this.f2489NP = hashCode;
        return hashCode;
    }

    public boolean isEmpty() {
        return false;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: pl */
    public boolean mo8636pl() {
        return false;
    }

    public int size() {
        return 1;
    }

    public String toString() {
        String obj = this.f2488LP.toString();
        StringBuilder sb = new StringBuilder(obj.length() + 2);
        sb.append('[');
        sb.append(obj);
        sb.append(']');
        return sb.toString();
    }

    public C1692rb iterator() {
        return C1652ea.m4567B(this.f2488LP);
    }

    SingletonImmutableSet(Object obj, int i) {
        this.f2488LP = obj;
        this.f2489NP = i;
    }
}
