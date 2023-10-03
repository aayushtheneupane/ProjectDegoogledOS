package com.android.messaging.mmslib.p039a;

/* renamed from: com.android.messaging.mmslib.a.n */
class C0984n {
    /* access modifiers changed from: private */

    /* renamed from: gD */
    public int f1411gD;
    /* access modifiers changed from: private */

    /* renamed from: hD */
    public int f1412hD;
    final /* synthetic */ C0985o this$0;

    /* synthetic */ C0984n(C0985o oVar, C0981k kVar) {
        this.this$0 = oVar;
    }

    /* access modifiers changed from: package-private */
    public int getLength() {
        if (this.f1412hD == this.this$0.f1414iD.f1408dD) {
            return this.this$0.mPosition - this.f1411gD;
        }
        throw new RuntimeException("BUG: Invalid call to getLength()");
    }
}
