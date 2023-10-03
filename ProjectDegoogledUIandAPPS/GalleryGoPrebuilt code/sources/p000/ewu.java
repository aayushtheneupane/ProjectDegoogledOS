package p000;

/* renamed from: ewu */
/* compiled from: PG */
final class ewu implements Runnable {

    /* renamed from: a */
    private final /* synthetic */ exb f9147a;

    /* renamed from: b */
    private final /* synthetic */ ewv f9148b;

    public ewu(ewv ewv, exb exb) {
        this.f9148b = ewv;
        this.f9147a = exb;
    }

    public final void run() {
        Exception exc;
        synchronized (this.f9148b.f9149a) {
            eww eww = this.f9148b.f9150b;
            exb exb = this.f9147a;
            synchronized (exb.f9158a) {
                exc = exb.f9163f;
            }
            ((ezv) ((fcj) eww).f9263a).f9227a.mo6952a((Throwable) exc);
        }
    }
}
