package p000;

/* renamed from: fek */
/* compiled from: PG */
final /* synthetic */ class fek implements Runnable {

    /* renamed from: a */
    private final fep f9371a;

    public fek(fep fep) {
        this.f9371a = fep;
    }

    public final void run() {
        fep fep = this.f9371a;
        hlj a = hnb.m11765a("GIL:AutoProcessBatch");
        try {
            fep.f9383a.mo5490a((fct) new fel(fep));
            fep.f9395m = null;
            if (a != null) {
                a.close();
                return;
            }
            return;
        } catch (Throwable th) {
            ifn.m12935a(th, th);
        }
        throw th;
    }
}
