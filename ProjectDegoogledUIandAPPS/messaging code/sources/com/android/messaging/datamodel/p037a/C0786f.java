package com.android.messaging.datamodel.p037a;

import com.android.messaging.util.C1424b;

/* renamed from: com.android.messaging.datamodel.a.f */
public class C0786f extends C0784d {
    private final C0784d mBinding;

    C0786f(C0784d dVar) {
        while (dVar instanceof C0786f) {
            dVar = ((C0786f) dVar).mBinding;
        }
        C1424b.m3592ia(dVar instanceof C0783c);
        this.mBinding = dVar;
    }

    /* renamed from: a */
    public void mo5929a(C0781a aVar) {
        this.mBinding.mo5929a(aVar);
    }

    public String getBindingId() {
        return this.mBinding.getBindingId();
    }

    public C0781a getData() {
        return this.mBinding.getData();
    }

    public boolean isBound() {
        return this.mBinding.isBound();
    }
}
