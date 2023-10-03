package com.google.common.collect;

import com.google.common.base.C1508E;

class RegularImmutableList extends ImmutableList {

    /* renamed from: KP */
    private final transient Object[] f2481KP;
    private final transient int offset;
    private final transient int size;

    RegularImmutableList(Object[] objArr, int i, int i2) {
        this.offset = i;
        this.size = i2;
        this.f2481KP = objArr;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: M */
    public ImmutableList mo8720M(int i, int i2) {
        return new RegularImmutableList(this.f2481KP, this.offset + i, i2 - i);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: d */
    public int mo8652d(Object[] objArr, int i) {
        System.arraycopy(this.f2481KP, this.offset, objArr, i, this.size);
        return i + this.size;
    }

    public Object get(int i) {
        C1508E.m3958J(i, this.size);
        return this.f2481KP[i + this.offset];
    }

    public int indexOf(Object obj) {
        if (obj == null) {
            return -1;
        }
        for (int i = 0; i < this.size; i++) {
            if (this.f2481KP[this.offset + i].equals(obj)) {
                return i;
            }
        }
        return -1;
    }

    public int lastIndexOf(Object obj) {
        if (obj == null) {
            return -1;
        }
        for (int i = this.size - 1; i >= 0; i--) {
            if (this.f2481KP[this.offset + i].equals(obj)) {
                return i;
            }
        }
        return -1;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: pl */
    public boolean mo8636pl() {
        return this.size != this.f2481KP.length;
    }

    public int size() {
        return this.size;
    }

    public C1695sb listIterator(int i) {
        return C1652ea.m4569a(this.f2481KP, this.offset, this.size, i);
    }

    RegularImmutableList(Object[] objArr) {
        int length = objArr.length;
        this.offset = 0;
        this.size = length;
        this.f2481KP = objArr;
    }
}
