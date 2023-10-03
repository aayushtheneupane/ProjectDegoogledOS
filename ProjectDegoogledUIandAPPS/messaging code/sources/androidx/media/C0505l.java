package androidx.media;

/* renamed from: androidx.media.l */
class C0505l implements Runnable {
    final /* synthetic */ C0506m this$1;

    C0505l(C0506m mVar) {
        this.this$1 = mVar;
    }

    public void run() {
        C0506m mVar = this.this$1;
        mVar.this$0.f478Ob.remove(mVar.callbacks.asBinder());
    }
}
