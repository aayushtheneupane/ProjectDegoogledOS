package com.google.common.collect;

/* renamed from: com.google.common.collect.X */
class C1632X extends C1639a {

    /* renamed from: lO */
    final /* synthetic */ Object[] f2506lO;

    /* renamed from: mO */
    final /* synthetic */ int f2507mO;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    C1632X(int i, int i2, Object[] objArr, int i3) {
        super(i, i2);
        this.f2506lO = objArr;
        this.f2507mO = i3;
    }

    /* access modifiers changed from: protected */
    public Object get(int i) {
        return this.f2506lO[this.f2507mO + i];
    }
}
