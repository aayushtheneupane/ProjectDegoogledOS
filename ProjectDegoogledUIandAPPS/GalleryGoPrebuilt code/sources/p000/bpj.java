package p000;

/* renamed from: bpj */
/* compiled from: PG */
public final /* synthetic */ class bpj implements Runnable {

    /* renamed from: a */
    private final ieh f3306a;

    public bpj(ieh ieh) {
        this.f3306a = ieh;
    }

    public final void run() {
        if (this.f3306a.isCancelled()) {
            cwn.m5510a("Close: Future should not have been cancelled but was.", new Object[0]);
        }
    }
}
