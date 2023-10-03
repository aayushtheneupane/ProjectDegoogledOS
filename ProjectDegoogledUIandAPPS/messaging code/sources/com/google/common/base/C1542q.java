package com.google.common.base;

/* renamed from: com.google.common.base.q */
class C1542q extends C1545t {

    /* renamed from: MM */
    final C1545t f2409MM;

    C1542q(String str, C1545t tVar) {
        super(str);
        this.f2409MM = tVar;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: Ta */
    public C1545t mo8557Ta(String str) {
        return new C1542q(str, this.f2409MM);
    }

    /* renamed from: d */
    public boolean mo8551d(char c) {
        return !this.f2409MM.mo8551d(c);
    }

    /* renamed from: e */
    public boolean mo8556e(CharSequence charSequence) {
        return this.f2409MM.mo8555d(charSequence);
    }

    public C1545t negate() {
        return this.f2409MM;
    }

    /* renamed from: d */
    public boolean mo8555d(CharSequence charSequence) {
        return this.f2409MM.mo8556e(charSequence);
    }

    C1542q(C1545t tVar) {
        super(tVar + ".negate()");
        this.f2409MM = tVar;
    }
}
