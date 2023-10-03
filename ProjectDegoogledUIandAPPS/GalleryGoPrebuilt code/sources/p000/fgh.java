package p000;

/* renamed from: fgh */
/* compiled from: PG */
final class fgh extends fgl {

    /* renamed from: a */
    private final /* synthetic */ fgi f9508a;

    public fgh(fgi fgi) {
        this.f9508a = fgi;
    }

    /* renamed from: a */
    public final void mo5586a(long j) {
        fgi fgi = this.f9508a;
        fgi.f9512d++;
        if (!fgi.mo5583a(fgi.f9509a) && !this.f9508a.f9509a.isStarted()) {
            fgi fgi2 = this.f9508a;
            if (fgi2.f9511c == -1 || fgi2.f9512d < 0) {
                Runnable runnable = fgi2.f9510b;
                if (runnable != null) {
                    runnable.run();
                }
                this.f9508a.f9509a.start();
            }
        }
    }
}
