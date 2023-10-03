package p000;

import android.util.SparseArray;

/* renamed from: ya */
/* compiled from: PG */
public final class C0655ya {

    /* renamed from: a */
    public final SparseArray f16322a = new SparseArray();

    /* renamed from: b */
    public int f16323b = 0;

    /* renamed from: a */
    static final long m16072a(long j, long j2) {
        return j != 0 ? ((j / 4) * 3) + (j2 / 4) : j2;
    }

    /* renamed from: a */
    public final void mo10601a() {
        this.f16323b++;
    }

    /* renamed from: b */
    public final void mo10602b() {
        this.f16323b--;
    }

    /* renamed from: a */
    public final C0653xz mo10600a(int i) {
        C0653xz xzVar = (C0653xz) this.f16322a.get(i);
        if (xzVar != null) {
            return xzVar;
        }
        C0653xz xzVar2 = new C0653xz();
        this.f16322a.put(i, xzVar2);
        return xzVar2;
    }
}
