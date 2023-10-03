package p000;

/* renamed from: cd */
/* compiled from: PG */
final class C0060cd implements Runnable {

    /* renamed from: a */
    private final /* synthetic */ Runnable f4092a;

    /* renamed from: b */
    private final /* synthetic */ C0061ce f4093b;

    public C0060cd(C0061ce ceVar, Runnable runnable) {
        this.f4093b = ceVar;
        this.f4092a = runnable;
    }

    public final void run() {
        try {
            this.f4092a.run();
        } finally {
            this.f4093b.mo3052a();
        }
    }
}
