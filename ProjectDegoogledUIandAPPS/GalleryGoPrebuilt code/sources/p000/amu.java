package p000;

/* renamed from: amu */
/* compiled from: PG */
final class amu implements Runnable {

    /* renamed from: a */
    public final amx f794a;

    /* renamed from: b */
    public final ieh f795b;

    public amu(amx amx, ieh ieh) {
        this.f794a = amx;
        this.f795b = ieh;
    }

    public final void run() {
        if (this.f794a.f803c == this) {
            if (amx.f800b.mo653a(this.f794a, (Object) this, amx.m783a(this.f795b))) {
                amx.m787a(this.f794a);
            }
        }
    }
}
