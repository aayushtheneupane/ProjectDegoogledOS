package p000;

import java.util.List;
import p003j$.util.function.Supplier;

/* renamed from: cql */
/* compiled from: PG */
final /* synthetic */ class cql implements Supplier {

    /* renamed from: a */
    private final dxo f5429a;

    public cql(dxo dxo) {
        this.f5429a = dxo;
    }

    public final Object get() {
        dxo dxo = this.f5429a;
        iir g = cmo.f4689d.mo8793g();
        boolean a = dxo.mo4506a();
        if (g.f14319c) {
            g.mo8751b();
            g.f14319c = false;
        }
        cmo cmo = (cmo) g.f14318b;
        cmo.f4691a |= 1;
        cmo.f4692b = a;
        hto b = dxo.mo4507b();
        if (g.f14319c) {
            g.mo8751b();
            g.f14319c = false;
        }
        cmo cmo2 = (cmo) g.f14318b;
        if (!cmo2.f4693c.mo8521a()) {
            cmo2.f4693c = iix.m13608a(cmo2.f4693c);
        }
        igz.m12986a((Iterable) b, (List) cmo2.f4693c);
        cmn cmn = new cmn();
        ftr.m9611b(cmn);
        ftr.m9610a((C0147fh) cmn);
        hcl.m11210a(cmn, (cmo) g.mo8770g());
        return cmn;
    }
}
