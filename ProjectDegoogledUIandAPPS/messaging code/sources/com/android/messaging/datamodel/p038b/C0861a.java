package com.android.messaging.datamodel.p038b;

import java.util.List;

/* renamed from: com.android.messaging.datamodel.b.a */
class C0861a extends C0865e {

    /* renamed from: qz */
    private final C0883w f1113qz;

    private C0861a(C0839B b, C0883w wVar) {
        super(b);
        this.f1113qz = wVar;
    }

    /* renamed from: a */
    public static C0861a m1571a(C0883w wVar, C0839B b) {
        return new C0861a(b, wVar);
    }

    /* renamed from: fa */
    public C0882v mo6121fa() {
        return this.f1113qz.mo6121fa();
    }

    public C0884x getDescriptor() {
        return this.f1113qz.getDescriptor();
    }

    public String getKey() {
        return this.f1113qz.getKey();
    }

    public int getRequestType() {
        return this.f1113qz.getRequestType();
    }

    /* renamed from: a */
    public C0846I mo6120a(List list) {
        return this.f1113qz.mo6120a(list);
    }
}
