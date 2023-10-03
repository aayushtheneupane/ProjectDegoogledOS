package androidx.core.content.p022a;

/* renamed from: androidx.core.content.a.j */
class C0317j implements Runnable {

    /* renamed from: do */
    final /* synthetic */ int f314do;
    final /* synthetic */ C0318k this$0;

    C0317j(C0318k kVar, int i) {
        this.this$0 = kVar;
        this.f314do = i;
    }

    public void run() {
        this.this$0.onFontRetrievalFailed(this.f314do);
    }
}
