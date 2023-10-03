package p000;

import android.util.SparseIntArray;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/* renamed from: feo */
/* compiled from: PG */
final class feo {

    /* renamed from: a */
    public final Set f9376a = new C0292kp();

    /* renamed from: b */
    public final List f9377b;

    /* renamed from: c */
    public final SparseIntArray f9378c;

    /* renamed from: d */
    public final List f9379d;

    /* renamed from: e */
    public final SparseIntArray f9380e;

    /* renamed from: f */
    private final int f9381f;

    /* renamed from: g */
    private final ial f9382g = fcl.m8575a();

    public feo(int i, int i2) {
        this.f9381f = i;
        this.f9377b = new ArrayList(i2);
        this.f9378c = new SparseIntArray(i2);
        this.f9379d = new ArrayList();
        this.f9380e = new SparseIntArray();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final void mo5569a(ffd ffd) {
        boolean z = true;
        if (ffd.f9439a == 1) {
            if (this.f9378c.valueAt(ffd.mo5575c()) != -1) {
                z = false;
            }
            ife.m12890c(z);
        }
        this.f9376a.add(ffd);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final fdx mo5568a(fdr fdr, int i) {
        fdr.mo5535a(this.f9381f, ffa.f9433a);
        int size = this.f9377b.size();
        fdh d = fdr.mo5543d(ffa.f9433a);
        iir iir = (iir) d.mo8790b(5);
        iir.mo8503a((iix) d);
        ial ial = this.f9382g;
        if (iir.f14319c) {
            iir.mo8751b();
            iir.f14319c = false;
        }
        fdh fdh = (fdh) iir.f14318b;
        fdh fdh2 = fdh.f9308e;
        ial.getClass();
        fdh.f9311b = ial;
        int i2 = fdh.f9310a | 1;
        fdh.f9310a = i2;
        fdh.f9310a = i2 | 2;
        fdh.f9312c = size;
        int c = fdr.mo5541c();
        if (iir.f14319c) {
            iir.mo8751b();
            iir.f14319c = false;
        }
        fdh fdh3 = (fdh) iir.f14318b;
        int i3 = c - 1;
        if (c != 0) {
            fdh3.f9313d = i3;
            fdh3.f9310a |= 4;
            fdr.mo5536a((fdh) iir.mo8770g(), ffa.f9433a);
            fdx a = fdr.mo5534a(ffa.f9433a);
            this.f9377b.add(a);
            this.f9378c.append(size, i);
            return a;
        }
        throw null;
    }
}
