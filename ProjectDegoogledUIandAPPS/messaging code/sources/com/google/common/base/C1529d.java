package com.google.common.base;

/* renamed from: com.google.common.base.d */
class C1529d extends C1540o {

    /* renamed from: KM */
    final /* synthetic */ char f2406KM;

    /* renamed from: LM */
    final /* synthetic */ char f2407LM;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    C1529d(String str, char c, char c2) {
        super(str);
        this.f2406KM = c;
        this.f2407LM = c2;
    }

    /* renamed from: d */
    public boolean mo8551d(char c) {
        return this.f2406KM <= c && c <= this.f2407LM;
    }
}
