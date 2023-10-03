package p000;

/* renamed from: aiq */
/* compiled from: PG */
final class aiq implements Runnable {

    /* renamed from: a */
    private final /* synthetic */ ait f561a;

    /* renamed from: b */
    private final /* synthetic */ amx f562b;

    public aiq(ait ait, amx amx) {
        this.f561a = ait;
        this.f562b = amx;
    }

    public final void run() {
        try {
            iol.m14231a();
            String str = ait.f573a;
            String.format("Starting work for %s", new Object[]{this.f561a.f574b.f714c});
            ait ait = this.f561a;
            ait.f576d = ait.f575c.mo1221c();
            this.f562b.mo658b(this.f561a.f576d);
        } catch (Throwable th) {
            this.f562b.mo657a(th);
        }
    }
}
