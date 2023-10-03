package androidx.lifecycle;

/* renamed from: androidx.lifecycle.o */
class C0458o implements Runnable {
    final /* synthetic */ C0460q this$0;

    C0458o(C0460q qVar) {
        this.this$0 = qVar;
    }

    public void run() {
        Object obj;
        synchronized (this.this$0.f448Up) {
            obj = this.this$0.f449Vp;
            this.this$0.f449Vp = C0460q.NOT_SET;
        }
        this.this$0.setValue(obj);
    }
}
