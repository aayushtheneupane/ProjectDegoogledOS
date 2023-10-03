package p000;

import android.util.SparseIntArray;
import java.util.ArrayList;
import java.util.List;

/* renamed from: fer */
/* compiled from: PG */
final /* synthetic */ class fer implements fct {

    /* renamed from: a */
    private final feu f9399a;

    /* renamed from: b */
    private final fdr f9400b;

    public fer(feu feu, fdr fdr) {
        this.f9399a = feu;
        this.f9400b = fdr;
    }

    /* renamed from: a */
    public final List mo5488a(fcy fcy) {
        feu feu = this.f9399a;
        fdr fdr = this.f9400b;
        ife.m12878b(fdr.mo5542c(ffa.f9433a), "Not impressed: %s", (Object) fdr);
        int c = fdr.mo5541c();
        ife.m12878b(c == 1, "Not visible: %s", (Object) fdr);
        fdh d = fdr.mo5543d(ffa.f9433a);
        int b = ife.m12861b(d.f9313d);
        if (b == 0 || b == 1) {
            return null;
        }
        iir iir = (iir) d.mo8790b(5);
        iir.mo8503a((iix) d);
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
            ArrayList arrayList = new ArrayList();
            SparseIntArray sparseIntArray = new SparseIntArray();
            feu.m8720a(fdr, arrayList, sparseIntArray, true);
            fcy.mo5495a(ffb.f9437c, Integer.valueOf(ffb.m8726b()));
            fcy.mo5495a(ffb.f9438d, Integer.valueOf(a.size() + arrayList.size()));
            fcl.m8575a();
            return hso.m12033a((Object) new ffe(hto.m12120a((Object) new ffd(2, a, 0)), hso.m12047f(), feu.f9409b, arrayList, sparseIntArray));
        }
        throw null;
    }
}
