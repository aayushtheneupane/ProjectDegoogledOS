package p000;

/* renamed from: atk */
/* compiled from: PG */
final class atk implements Runnable {

    /* renamed from: a */
    private final bef f1623a;

    /* renamed from: b */
    private final /* synthetic */ ato f1624b;

    public atk(ato ato, bef bef) {
        this.f1624b = ato;
        this.f1623a = bef;
    }

    public final void run() {
        synchronized (this.f1623a.mo1905h()) {
            synchronized (this.f1624b) {
                if (this.f1624b.f1630a.mo1592a(this.f1623a)) {
                    ato ato = this.f1624b;
                    try {
                        ((beg) this.f1623a).mo1907a(ato.f1639j);
                    } catch (Throwable th) {
                        throw new asl(th);
                    }
                }
                this.f1624b.mo1596a();
            }
        }
    }
}
