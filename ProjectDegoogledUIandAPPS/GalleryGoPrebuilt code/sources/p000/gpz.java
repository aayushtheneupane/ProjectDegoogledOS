package p000;

/* renamed from: gpz */
/* compiled from: PG */
final /* synthetic */ class gpz implements Runnable {

    /* renamed from: a */
    private final ieh f11820a;

    /* renamed from: b */
    private final ieh f11821b;

    public gpz(ieh ieh, ieh ieh2) {
        this.f11820a = ieh;
        this.f11821b = ieh2;
    }

    public final void run() {
        ieh ieh = this.f11820a;
        ieh ieh2 = this.f11821b;
        if (ieh.isCancelled()) {
            ieh2.cancel(true);
        }
    }
}
