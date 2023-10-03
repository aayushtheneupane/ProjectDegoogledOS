package androidx.fragment.app;

/* renamed from: androidx.fragment.app.u */
class C0435u implements Runnable {
    final /* synthetic */ C0389H this$0;

    C0435u(C0389H h) {
        this.this$0 = h;
    }

    public void run() {
        this.this$0.execPendingActions();
    }
}
