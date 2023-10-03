package p000;

import java.util.concurrent.Executor;

/* renamed from: hau */
/* compiled from: PG */
final /* synthetic */ class hau implements icf {

    /* renamed from: a */
    private final hbb f12430a;

    /* renamed from: b */
    private final String f12431b;

    public hau(hbb hbb, String str) {
        this.f12430a = hbb;
        this.f12431b = str;
    }

    /* renamed from: a */
    public final ieh mo2538a(Object obj) {
        hbb hbb = this.f12430a;
        String str = this.f12431b;
        fqi fqi = (fqi) obj;
        gww gww = gww.DEVICE;
        int ordinal = ((gww) hbb.f12441b.get(str)).ordinal();
        if (ordinal == 1) {
            return ibv.m12657a(hbb.f12443d.mo7207a(str, fqi), (hpr) new hav(), (Executor) idh.f13918a);
        }
        if (ordinal == 3) {
            return hbb.f12440a.mo7207a(str, fqi);
        }
        throw new UnsupportedOperationException();
    }
}
