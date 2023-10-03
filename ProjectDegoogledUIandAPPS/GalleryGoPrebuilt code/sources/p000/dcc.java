package p000;

/* renamed from: dcc */
/* compiled from: PG */
final /* synthetic */ class dcc implements Runnable {

    /* renamed from: a */
    private final ieh f6232a;

    public dcc(ieh ieh) {
        this.f6232a = ieh;
    }

    public final void run() {
        this.f6232a.cancel(true);
    }
}
