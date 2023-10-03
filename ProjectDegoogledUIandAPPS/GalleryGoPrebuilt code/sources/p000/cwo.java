package p000;

/* renamed from: cwo */
/* compiled from: PG */
final /* synthetic */ class cwo implements Runnable {

    /* renamed from: a */
    private final cwq f5809a;

    /* renamed from: b */
    private final int f5810b;

    /* renamed from: c */
    private final iir f5811c;

    public cwo(cwq cwq, iir iir, int i) {
        this.f5809a = cwq;
        this.f5811c = iir;
        this.f5810b = i;
    }

    public final void run() {
        cwq cwq = this.f5809a;
        iir iir = this.f5811c;
        int i = this.f5810b;
        eja eja = (eja) cwq.f5815a.mo9034a();
        bka bka = (bka) cwq.f5816b.mo9034a();
        if (iir.f14319c) {
            iir.mo8751b();
            iir.f14319c = false;
        }
        bjx bjx = (bjx) iir.f14318b;
        bjx bjx2 = bjx.f2967d;
        bka.getClass();
        bjx.f2971c = bka;
        bjx.f2969a |= 2;
        eix a = eja.mo4867a(((bjx) iir.mo8770g()).mo8512ag());
        a.mo4865a(i - 1);
        a.mo4864a();
    }
}
