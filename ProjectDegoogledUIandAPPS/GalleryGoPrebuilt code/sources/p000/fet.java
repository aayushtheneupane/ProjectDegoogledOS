package p000;

import android.util.SparseIntArray;
import java.util.List;

/* renamed from: fet */
/* compiled from: PG */
final class fet implements fdl {

    /* renamed from: a */
    private int f9403a = -1;

    /* renamed from: b */
    private final /* synthetic */ boolean f9404b;

    /* renamed from: c */
    private final /* synthetic */ List f9405c;

    /* renamed from: d */
    private final /* synthetic */ fdr f9406d;

    /* renamed from: e */
    private final /* synthetic */ SparseIntArray f9407e;

    public fet(boolean z, List list, fdr fdr, SparseIntArray sparseIntArray) {
        this.f9404b = z;
        this.f9405c = list;
        this.f9406d = fdr;
        this.f9407e = sparseIntArray;
    }

    /* renamed from: a */
    public final void mo5515a(fdr fdr) {
        int b;
        ife.m12896d(fdr.mo5540b(ffa.f9433a));
        fdh d = fdr.mo5543d(ffa.f9433a);
        boolean z = true;
        if (!this.f9404b || (b = ife.m12861b(d.f9313d)) == 0 || b == 1) {
            int size = this.f9405c.size();
            if (this.f9404b) {
                if ((d.f9310a & 2) == 0) {
                    z = false;
                }
                ife.m12896d(z);
            } else if ((d.f9310a & 2) == 0) {
                iir iir = (iir) d.mo8790b(5);
                iir.mo8503a((iix) d);
                if (iir.f14319c) {
                    iir.mo8751b();
                    iir.f14319c = false;
                }
                fdh fdh = (fdh) iir.f14318b;
                fdh fdh2 = fdh.f9308e;
                fdh.f9310a |= 2;
                fdh.f9312c = size;
                int c = fdr.mo5541c();
                if (iir.f14319c) {
                    iir.mo8751b();
                    iir.f14319c = false;
                }
                fdh fdh3 = (fdh) iir.f14318b;
                int i = c - 1;
                if (c != 0) {
                    fdh3.f9313d = i;
                    fdh3.f9310a |= 4;
                    fdr.mo5536a((fdh) iir.mo8770g(), ffa.f9433a);
                } else {
                    throw null;
                }
            } else {
                if (fdr != this.f9406d) {
                    z = false;
                }
                ife.m12878b(z, "Child %s was already impressed. Instrumentation must be added root -> leaf.", (Object) fdr);
            }
            this.f9405c.add(fdr.mo5534a(ffa.f9433a));
            this.f9407e.put(size, this.f9403a);
            int i2 = this.f9403a;
            this.f9403a = size;
            fdr.mo5544e(ffa.f9433a).mo5521a((fdl) this);
            this.f9403a = i2;
        }
    }
}
