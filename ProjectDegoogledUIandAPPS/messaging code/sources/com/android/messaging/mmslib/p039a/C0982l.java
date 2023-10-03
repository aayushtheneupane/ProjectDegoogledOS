package com.android.messaging.mmslib.p039a;

import java.io.ByteArrayOutputStream;

/* renamed from: com.android.messaging.mmslib.a.l */
class C0982l {

    /* renamed from: bD */
    private C0983m f1406bD = null;

    /* renamed from: cD */
    private C0983m f1407cD = null;

    /* renamed from: dD */
    int f1408dD = 0;
    final /* synthetic */ C0985o this$0;

    /* synthetic */ C0982l(C0985o oVar, C0981k kVar) {
        this.this$0 = oVar;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: ci */
    public void mo6693ci() {
        if (this.f1407cD == null) {
            C0983m mVar = new C0983m((C0981k) null);
            C0985o oVar = this.this$0;
            mVar.f1409eD = oVar.mMessage;
            mVar.f1410fD = oVar.mPosition;
            mVar.next = this.f1406bD;
            this.f1406bD = mVar;
            this.f1408dD++;
            oVar.mMessage = new ByteArrayOutputStream();
            this.this$0.mPosition = 0;
            return;
        }
        throw new RuntimeException("BUG: Invalid newbuf() before copy()");
    }

    /* access modifiers changed from: package-private */
    public void copy() {
        this.this$0.mo6699a(this.f1407cD.f1409eD.toByteArray(), 0, this.f1407cD.f1410fD);
        this.f1407cD = null;
    }

    /* access modifiers changed from: package-private */
    public C0984n mark() {
        C0984n nVar = new C0984n(this.this$0, (C0981k) null);
        int unused = nVar.f1411gD = this.this$0.mPosition;
        int unused2 = nVar.f1412hD = this.f1408dD;
        return nVar;
    }

    /* access modifiers changed from: package-private */
    public void pop() {
        C0985o oVar = this.this$0;
        ByteArrayOutputStream byteArrayOutputStream = oVar.mMessage;
        int i = oVar.mPosition;
        C0983m mVar = this.f1406bD;
        oVar.mMessage = mVar.f1409eD;
        oVar.mPosition = mVar.f1410fD;
        this.f1407cD = mVar;
        this.f1406bD = mVar.next;
        this.f1408dD--;
        C0983m mVar2 = this.f1407cD;
        mVar2.f1409eD = byteArrayOutputStream;
        mVar2.f1410fD = i;
    }
}
