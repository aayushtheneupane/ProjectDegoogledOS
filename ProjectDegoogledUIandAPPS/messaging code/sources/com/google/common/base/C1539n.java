package com.google.common.base;

/* renamed from: com.google.common.base.n */
class C1539n extends C1540o {

    /* renamed from: GM */
    final /* synthetic */ char f2408GM;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    C1539n(String str, char c) {
        super(str);
        this.f2408GM = c;
    }

    /* renamed from: a */
    public C1545t mo8550a(C1545t tVar) {
        if (tVar.mo8551d(this.f2408GM)) {
            return tVar;
        }
        return new C1543r(this, tVar, "CharMatcher.or(" + this + ", " + tVar + ")");
    }

    /* renamed from: d */
    public boolean mo8551d(char c) {
        return c == this.f2408GM;
    }

    public C1545t negate() {
        return C1545t.m4020c(this.f2408GM);
    }
}
