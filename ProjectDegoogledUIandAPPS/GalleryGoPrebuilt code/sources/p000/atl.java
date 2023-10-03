package p000;

/* renamed from: atl */
/* compiled from: PG */
final class atl implements Runnable {

    /* renamed from: a */
    private final bef f1625a;

    /* renamed from: b */
    private final /* synthetic */ ato f1626b;

    public atl(ato ato, bef bef) {
        this.f1626b = ato;
        this.f1625a = bef;
    }

    public final void run() {
        synchronized (this.f1625a.mo1905h()) {
            synchronized (this.f1626b) {
                if (this.f1626b.f1630a.mo1592a(this.f1625a)) {
                    this.f1626b.f1641l.mo1608e();
                    ato ato = this.f1626b;
                    try {
                        this.f1625a.mo1904a(ato.f1641l, ato.f1643n);
                        this.f1626b.mo1599a(this.f1625a);
                    } catch (Throwable th) {
                        throw new asl(th);
                    }
                }
                this.f1626b.mo1596a();
            }
        }
    }
}
