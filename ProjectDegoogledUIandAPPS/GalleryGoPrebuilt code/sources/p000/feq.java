package p000;

import android.util.SparseIntArray;
import java.util.ArrayList;
import java.util.List;

/* renamed from: feq */
/* compiled from: PG */
final /* synthetic */ class feq implements fct {

    /* renamed from: a */
    private final feu f9397a;

    /* renamed from: b */
    private final fdr f9398b;

    public feq(feu feu, fdr fdr) {
        this.f9397a = feu;
        this.f9398b = fdr;
    }

    /* renamed from: a */
    public final List mo5488a(fcy fcy) {
        feu feu = this.f9397a;
        fdr fdr = this.f9398b;
        ife.m12878b(!fdr.mo5542c(ffa.f9433a), "Already impressed: %s", (Object) fdr);
        fdh d = fdr.mo5543d(ffa.f9433a);
        iir iir = (iir) d.mo8790b(5);
        iir.mo8503a((iix) d);
        ial a = fcl.m8575a();
        if (iir.f14319c) {
            iir.mo8751b();
            iir.f14319c = false;
        }
        fdh fdh = (fdh) iir.f14318b;
        fdh fdh2 = fdh.f9308e;
        a.getClass();
        fdh.f9311b = a;
        int i = fdh.f9310a | 1;
        fdh.f9310a = i;
        fdh.f9310a = i | 2;
        fdh.f9312c = 0;
        int c = fdr.mo5541c();
        if (iir.f14319c) {
            iir.mo8751b();
            iir.f14319c = false;
        }
        fdh fdh3 = (fdh) iir.f14318b;
        int i2 = c - 1;
        if (c != 0) {
            fdh3.f9313d = i2;
            fdh3.f9310a |= 4;
            fdr.mo5536a((fdh) iir.mo8770g(), ffa.f9433a);
            ffb.m8725a();
            List a2 = fej.m8699a(fdr);
            ArrayList arrayList = new ArrayList();
            SparseIntArray sparseIntArray = new SparseIntArray();
            feu.m8720a(fdr, arrayList, sparseIntArray, false);
            fcy.mo5495a(ffb.f9437c, Integer.valueOf(ffb.m8726b()));
            fcy.mo5495a(ffb.f9438d, Integer.valueOf(a2.size() + arrayList.size()));
            fdh fdh4 = ((fdx) a2.get(0)).f9343d;
            if (fdh4 == null) {
                fdh4 = fdh.f9308e;
            }
            if (fdh4.f9311b == null) {
                ial ial = ial.f13725d;
            }
            return hso.m12033a((Object) new ffe(hto.m12120a((Object) new ffd(1, a2, 0)), arrayList, sparseIntArray, hso.m12047f(), feu.f9409b));
        }
        throw null;
    }
}
