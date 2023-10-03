package p000;

import java.util.concurrent.Executor;

/* renamed from: gyk */
/* compiled from: PG */
final /* synthetic */ class gyk implements icf {

    /* renamed from: a */
    private final gyt f12307a;

    /* renamed from: b */
    private final String f12308b;

    public gyk(gyt gyt, String str) {
        this.f12307a = gyt;
        this.f12308b = str;
    }

    /* renamed from: a */
    public final ieh mo2538a(Object obj) {
        gyt gyt = this.f12307a;
        String str = this.f12308b;
        fqi fqi = (fqi) obj;
        gww gww = gww.DEVICE;
        int ordinal = ((gww) gyt.f12322c.get(str)).ordinal();
        if (ordinal == 0) {
            return ibv.m12657a(gyt.f12321b.mo7207a(str, fqi), (hpr) new gyl(), (Executor) idh.f13918a);
        }
        if (ordinal == 2) {
            return gyt.f12320a.mo7207a(str, fqi);
        }
        throw new UnsupportedOperationException();
    }
}
