package p000;

/* renamed from: ibk */
/* compiled from: PG */
final class ibk implements Runnable {

    /* renamed from: a */
    public final ibr f13843a;

    /* renamed from: b */
    public final ieh f13844b;

    public ibk(ibr ibr, ieh ieh) {
        this.f13843a = ibr;
        this.f13844b = ieh;
    }

    public final void run() {
        ibr ibr = this.f13843a;
        boolean z = ibr.f13852b;
        if (ibr.value == this) {
            if (ibr.f13854d.mo8340a(this.f13843a, (Object) this, ibr.m12636b(this.f13844b))) {
                ibr.m12633a(this.f13843a);
            }
        }
    }
}
