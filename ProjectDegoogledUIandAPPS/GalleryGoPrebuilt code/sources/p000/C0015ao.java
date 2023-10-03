package p000;

/* renamed from: ao */
/* compiled from: PG */
final class C0015ao implements Runnable {

    /* renamed from: a */
    private final C0002ab f1239a;

    /* renamed from: b */
    private final C0546u f1240b;

    /* renamed from: c */
    private boolean f1241c = false;

    public C0015ao(C0002ab abVar, C0546u uVar) {
        this.f1239a = abVar;
        this.f1240b = uVar;
    }

    public final void run() {
        if (!this.f1241c) {
            this.f1239a.mo62a(this.f1240b);
            this.f1241c = true;
        }
    }
}
