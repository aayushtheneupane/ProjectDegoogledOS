package androidx.media;

/* renamed from: androidx.media.B */
class C0481B implements Runnable {
    final /* synthetic */ C0489J this$1;
    final /* synthetic */ C0490K val$callbacks;

    C0481B(C0489J j, C0490K k) {
        this.this$1 = j;
        this.val$callbacks = k;
    }

    public void run() {
        C0506m mVar = (C0506m) this.this$1.this$0.f478Ob.remove(this.val$callbacks.asBinder());
        if (mVar != null) {
            mVar.callbacks.asBinder().unlinkToDeath(mVar, 0);
        }
    }
}
