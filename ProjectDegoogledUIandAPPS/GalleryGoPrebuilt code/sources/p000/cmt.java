package p000;

import java.util.List;
import p003j$.util.function.Supplier;

/* renamed from: cmt */
/* compiled from: PG */
final /* synthetic */ class cmt implements Supplier {

    /* renamed from: a */
    private final cmv f4698a;

    public cmt(cmv cmv) {
        this.f4698a = cmv;
    }

    public final Object get() {
        cmv cmv = this.f4698a;
        iir g = clb.f4598d.mo8793g();
        boolean z = cmv.f4700a.f4692b;
        if (g.f14319c) {
            g.mo8751b();
            g.f14319c = false;
        }
        clb clb = (clb) g.f14318b;
        clb.f4600a |= 1;
        clb.f4601b = z;
        ije ije = cmv.f4700a.f4693c;
        if (!clb.f4602c.mo8521a()) {
            clb.f4602c = iix.m13608a(clb.f4602c);
        }
        igz.m12986a((Iterable) ije, (List) clb.f4602c);
        return clr.m4509a((clb) g.mo8770g());
    }
}
