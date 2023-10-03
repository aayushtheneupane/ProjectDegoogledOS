package com.google.common.base;

/* renamed from: com.google.common.base.w */
class C1548w extends C1504A {

    /* renamed from: SM */
    final /* synthetic */ String f2415SM;
    final /* synthetic */ C1504A this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    C1548w(C1504A a, C1504A a2, String str) {
        super(a2, (C1548w) null);
        this.this$0 = a;
        this.f2415SM = str;
    }

    /* renamed from: Va */
    public C1504A mo8509Va(String str) {
        throw new UnsupportedOperationException("already specified useForNull");
    }

    /* renamed from: Vk */
    public C1504A mo8510Vk() {
        throw new UnsupportedOperationException("already specified useForNull");
    }

    /* access modifiers changed from: package-private */
    public CharSequence toString(Object obj) {
        return obj == null ? this.f2415SM : this.this$0.toString(obj);
    }
}
