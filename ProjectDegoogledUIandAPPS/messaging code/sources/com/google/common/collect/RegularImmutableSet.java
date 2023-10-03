package com.google.common.collect;

final class RegularImmutableSet extends ImmutableSet {
    private final Object[] elements;
    private final transient int mask;

    /* renamed from: sN */
    private final transient int f2482sN;
    final transient Object[] table;

    RegularImmutableSet(Object[] objArr, int i, Object[] objArr2, int i2) {
        this.elements = objArr;
        this.table = objArr2;
        this.mask = i2;
        this.f2482sN = i;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: Pl */
    public ImmutableList mo8702Pl() {
        return new RegularImmutableAsList((ImmutableCollection) this, this.elements);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: Rl */
    public boolean mo8649Rl() {
        return true;
    }

    public boolean contains(Object obj) {
        if (obj == null) {
            return false;
        }
        int cb = C1578K.m4255cb(obj.hashCode());
        while (true) {
            Object obj2 = this.table[this.mask & cb];
            if (obj2 == null) {
                return false;
            }
            if (obj2.equals(obj)) {
                return true;
            }
            cb++;
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: d */
    public int mo8652d(Object[] objArr, int i) {
        Object[] objArr2 = this.elements;
        System.arraycopy(objArr2, 0, objArr, i, objArr2.length);
        return i + this.elements.length;
    }

    public int hashCode() {
        return this.f2482sN;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: pl */
    public boolean mo8636pl() {
        return false;
    }

    public int size() {
        return this.elements.length;
    }

    public C1692rb iterator() {
        return C1652ea.m4581e(this.elements);
    }
}
