package androidx.fragment.app;

/* renamed from: androidx.fragment.app.d */
class C0413d implements Runnable {
    final /* synthetic */ C0424j this$0;

    C0413d(C0424j jVar) {
        this.this$0 = jVar;
    }

    public void run() {
        this.this$0.startPostponedEnterTransition();
    }
}
