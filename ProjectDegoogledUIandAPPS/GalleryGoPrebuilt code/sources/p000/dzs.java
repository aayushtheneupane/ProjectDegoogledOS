package p000;

import p003j$.util.function.Supplier;

/* renamed from: dzs */
/* compiled from: PG */
final /* synthetic */ class dzs implements Supplier {

    /* renamed from: a */
    private final brx f7741a;

    /* renamed from: b */
    private final int f7742b;

    /* renamed from: c */
    private final iir f7743c;

    public dzs(brx brx, iir iir, int i) {
        this.f7741a = brx;
        this.f7743c = iir;
        this.f7742b = i;
    }

    public final Object get() {
        brx brx = this.f7741a;
        iir iir = this.f7743c;
        int i = this.f7742b;
        iir g = btt.f3568g.mo8793g();
        cxe cxe = brx.f3465a;
        if (g.f14319c) {
            g.mo8751b();
            g.f14319c = false;
        }
        btt btt = (btt) g.f14318b;
        cxe.getClass();
        btt.f3571b = cxe;
        int i2 = btt.f3570a | 1;
        btt.f3570a = i2;
        btt.f3570a = i2 | 8;
        btt.f3574e = false;
        cxd cxd = (cxd) iir.mo8770g();
        if (g.f14319c) {
            g.mo8751b();
            g.f14319c = false;
        }
        btt btt2 = (btt) g.f14318b;
        cxd.getClass();
        btt2.f3573d = cxd;
        btt2.f3570a |= 4;
        iir g2 = cqe.f5414d.mo8793g();
        if (g2.f14319c) {
            g2.mo8751b();
            g2.f14319c = false;
        }
        cqe cqe = (cqe) g2.f14318b;
        cqe.f5417b = i - 1;
        int i3 = cqe.f5416a | 1;
        cqe.f5416a = i3;
        cqe.f5416a = i3 | 2;
        cqe.f5418c = 100;
        if (g.f14319c) {
            g.mo8751b();
            g.f14319c = false;
        }
        btt btt3 = (btt) g.f14318b;
        cqe cqe2 = (cqe) g2.mo8770g();
        cqe2.getClass();
        btt3.f3572c = cqe2;
        btt3.f3570a |= 2;
        cpi cpi = cpi.PICKER;
        if (g.f14319c) {
            g.mo8751b();
            g.f14319c = false;
        }
        btt btt4 = (btt) g.f14318b;
        btt4.f3575f = cpi.f5364h;
        btt4.f3570a |= 16;
        return bts.m3583a((btt) g.mo8770g());
    }
}
