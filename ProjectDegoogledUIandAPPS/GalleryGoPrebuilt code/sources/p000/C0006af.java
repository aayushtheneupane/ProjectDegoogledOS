package p000;

/* renamed from: af */
/* compiled from: PG */
final class C0006af implements Runnable {

    /* renamed from: a */
    private final /* synthetic */ C0009ai f297a;

    public C0006af(C0009ai aiVar) {
        this.f297a = aiVar;
    }

    public final void run() {
        Object obj;
        synchronized (this.f297a.f520a) {
            obj = this.f297a.f523e;
            this.f297a.f523e = C0009ai.f519b;
        }
        this.f297a.mo512a(obj);
    }
}
