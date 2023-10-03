package com.google.common.base;

/* renamed from: com.google.common.base.r */
class C1543r extends C1545t {
    final C1545t first;
    final C1545t second;

    C1543r(C1545t tVar, C1545t tVar2, String str) {
        super(str);
        if (tVar != null) {
            this.first = tVar;
            if (tVar2 != null) {
                this.second = tVar2;
                return;
            }
            throw new NullPointerException();
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: Ta */
    public C1545t mo8557Ta(String str) {
        return new C1543r(this.first, this.second, str);
    }

    /* renamed from: d */
    public boolean mo8551d(char c) {
        return this.first.mo8551d(c) || this.second.mo8551d(c);
    }
}
