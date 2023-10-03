package androidx.appcompat.p020b.p021a;

/* renamed from: androidx.appcompat.b.a.i */
class C0166i implements Runnable {
    final /* synthetic */ C0169l this$0;

    C0166i(C0169l lVar) {
        this.this$0 = lVar;
    }

    public void run() {
        this.this$0.animate(true);
        this.this$0.invalidateSelf();
    }
}
