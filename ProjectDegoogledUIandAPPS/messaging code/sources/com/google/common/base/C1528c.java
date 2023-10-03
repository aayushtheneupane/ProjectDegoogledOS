package com.google.common.base;

/* renamed from: com.google.common.base.c */
class C1528c extends C1540o {

    /* renamed from: HM */
    final /* synthetic */ char f2404HM;

    /* renamed from: JM */
    final /* synthetic */ char f2405JM;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    C1528c(String str, char c, char c2) {
        super(str);
        this.f2404HM = c;
        this.f2405JM = c2;
    }

    /* renamed from: d */
    public boolean mo8551d(char c) {
        return c == this.f2404HM || c == this.f2405JM;
    }
}
