package androidx.fragment.app;

/* renamed from: androidx.fragment.app.e */
class C0415e implements Runnable {
    final /* synthetic */ C0424j this$0;

    C0415e(C0424j jVar) {
        this.this$0 = jVar;
    }

    public void run() {
        this.this$0.callStartTransitionListener();
    }
}
