package p000;

/* renamed from: ewx */
/* compiled from: PG */
final class ewx implements Runnable {

    /* renamed from: a */
    private final /* synthetic */ exb f9152a;

    /* renamed from: b */
    private final /* synthetic */ ewy f9153b;

    public ewx(ewy ewy, exb exb) {
        this.f9153b = ewy;
        this.f9152a = exb;
    }

    public final void run() {
        Object obj;
        synchronized (this.f9153b.f9154a) {
            ewz ewz = this.f9153b.f9155b;
            exb exb = this.f9152a;
            synchronized (exb.f9158a) {
                abj.m108a(exb.f9160c, (Object) "Task is not yet complete");
                boolean z = exb.f9161d;
                Exception exc = exb.f9163f;
                if (exc == null) {
                    obj = exb.f9162e;
                } else {
                    throw new exa(exc);
                }
            }
            fcg fcg = ((fci) ewz).f9261a;
            ((ezu) fcg).f9226a.mo8346b(((fci) ewz).f9262b.f9264a.mo1484a(obj));
        }
    }
}
