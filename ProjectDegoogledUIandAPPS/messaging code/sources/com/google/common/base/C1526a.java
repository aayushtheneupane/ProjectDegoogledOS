package com.google.common.base;

/* renamed from: com.google.common.base.a */
class C1526a extends C1540o {

    /* renamed from: GM */
    final /* synthetic */ char f2402GM;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    C1526a(String str, char c) {
        super(str);
        this.f2402GM = c;
    }

    /* renamed from: a */
    public C1545t mo8550a(C1545t tVar) {
        return tVar.mo8551d(this.f2402GM) ? C1545t.ANY : this;
    }

    /* renamed from: d */
    public boolean mo8551d(char c) {
        return c != this.f2402GM;
    }

    public C1545t negate() {
        return C1545t.m4018b(this.f2402GM);
    }
}
