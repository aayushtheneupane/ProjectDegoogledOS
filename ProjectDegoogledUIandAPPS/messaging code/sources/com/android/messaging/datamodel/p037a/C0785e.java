package com.android.messaging.datamodel.p037a;

import com.android.messaging.util.C1424b;

/* renamed from: com.android.messaging.datamodel.a.e */
public class C0785e extends C0783c {

    /* renamed from: Wz */
    private C0781a f1054Wz;

    C0785e(Object obj) {
        super(obj);
    }

    /* renamed from: b */
    public void mo5930b(C0781a aVar) {
        super.mo5930b(aVar);
        this.f1054Wz = null;
    }

    public void detach() {
        C1424b.isNull(this.f1054Wz);
        C1424b.m3592ia(isBound());
        this.f1054Wz = getData();
        unbind();
    }

    /* renamed from: zf */
    public void mo5937zf() {
        if (this.f1054Wz != null) {
            C1424b.m3591ha(isBound());
            mo5930b(this.f1054Wz);
            this.f1054Wz = null;
        }
    }
}
