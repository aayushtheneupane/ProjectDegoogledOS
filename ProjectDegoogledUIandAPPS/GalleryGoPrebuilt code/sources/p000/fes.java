package p000;

import java.util.List;

/* renamed from: fes */
/* compiled from: PG */
final /* synthetic */ class fes implements fct {

    /* renamed from: a */
    private final feu f9401a;

    /* renamed from: b */
    private final fdr f9402b;

    public fes(feu feu, fdr fdr) {
        this.f9401a = feu;
        this.f9402b = fdr;
    }

    /* renamed from: a */
    public final List mo5488a(fcy fcy) {
        feu feu = this.f9401a;
        fdr fdr = this.f9402b;
        fdh d = fdr.mo5543d(ffa.f9433a);
        ife.m12878b(fdr.mo5542c(ffa.f9433a), "Not impressed: %s", (Object) fdr);
        int b = ife.m12861b(d.f9313d);
        if (b != 0 && b == 2) {
            return null;
        }
        iir iir = (iir) d.mo8790b(5);
        iir.mo8503a((iix) d);
        int c = fdr.mo5541c();
        if (iir.f14319c) {
            iir.mo8751b();
            iir.f14319c = false;
        }
        fdh fdh = (fdh) iir.f14318b;
        int i = c - 1;
        fdh fdh2 = fdh.f9308e;
        if (c != 0) {
            fdh.f9313d = i;
            fdh.f9310a |= 4;
            fdr.mo5536a((fdh) iir.mo8770g(), ffa.f9433a);
            ffb.m8725a();
            List a = fej.m8699a(fdr);
            fcy.mo5495a(ffb.f9437c, Integer.valueOf(ffb.m8726b()));
            fcy.mo5495a(ffb.f9438d, Integer.valueOf(a.size()));
            fcl.m8575a();
            return hso.m12033a((Object) new ffe(hto.m12120a((Object) new ffd(3, a, -1)), hso.m12047f(), feu.f9409b, hso.m12047f(), feu.f9409b));
        }
        throw null;
    }
}
