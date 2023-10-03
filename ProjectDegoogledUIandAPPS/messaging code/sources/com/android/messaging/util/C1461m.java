package com.android.messaging.util;

/* renamed from: com.android.messaging.util.m */
public class C1461m {

    /* renamed from: MJ */
    private int f2314MJ = 0;

    /* renamed from: NJ */
    private boolean f2315NJ = false;

    /* renamed from: OJ */
    private int f2316OJ;
    Object[] mList = new Object[this.f2316OJ];

    public C1461m(int i) {
        this.f2316OJ = i;
    }

    /* renamed from: Jj */
    public Object mo8190Jj() {
        if (!this.f2315NJ) {
            return null;
        }
        return this.mList[this.f2314MJ];
    }

    public void add(Object obj) {
        Object[] objArr = this.mList;
        int i = this.f2314MJ;
        objArr[i] = obj;
        this.f2314MJ = i + 1;
        if (this.f2314MJ == this.f2316OJ) {
            this.f2314MJ = 0;
            this.f2315NJ = true;
        }
    }

    public int count() {
        if (this.f2315NJ) {
            return this.f2316OJ;
        }
        return this.f2314MJ;
    }

    public Object get(int i) {
        if (!this.f2315NJ) {
            return this.mList[i];
        }
        int i2 = i + this.f2314MJ;
        int i3 = this.f2316OJ;
        if (i2 >= i3) {
            i2 -= i3;
        }
        return this.mList[i2];
    }
}
