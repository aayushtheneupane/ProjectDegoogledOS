package p000;

/* renamed from: uo */
/* compiled from: PG */
public final class C0561uo {

    /* renamed from: a */
    private long f16025a = 0;

    /* renamed from: b */
    private C0561uo f16026b;

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public final void mo10299b(int i) {
        if (i >= 64) {
            C0561uo uoVar = this.f16026b;
            if (uoVar != null) {
                uoVar.mo10299b(i - 64);
                return;
            }
            return;
        }
        this.f16025a &= (1 << i) ^ -1;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: e */
    public final int mo10302e(int i) {
        C0561uo uoVar = this.f16026b;
        if (uoVar == null) {
            if (i < 64) {
                return Long.bitCount(this.f16025a & ((1 << i) - 1));
            }
            return Long.bitCount(this.f16025a);
        } else if (i >= 64) {
            return uoVar.mo10302e(i - 64) + Long.bitCount(this.f16025a);
        } else {
            return Long.bitCount(this.f16025a & ((1 << i) - 1));
        }
    }

    /* renamed from: b */
    private final void m15525b() {
        if (this.f16026b == null) {
            this.f16026b = new C0561uo();
        }
    }

    /* renamed from: c */
    public final boolean mo10300c(int i) {
        if (i < 64) {
            return (this.f16025a & (1 << i)) != 0;
        }
        m15525b();
        return this.f16026b.mo10300c(i - 64);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final void mo10298a(int i, boolean z) {
        if (i < 64) {
            long j = this.f16025a;
            boolean z2 = (Long.MIN_VALUE & j) != 0;
            long j2 = (1 << i) - 1;
            long j3 = (-1 ^ j2) & j;
            this.f16025a = (j & j2) | (j3 + j3);
            if (!z) {
                mo10299b(i);
            } else {
                mo10297a(i);
            }
            if (z2 || this.f16026b != null) {
                m15525b();
                this.f16026b.mo10298a(0, z2);
                return;
            }
            return;
        }
        m15525b();
        this.f16026b.mo10298a(i - 64, z);
    }

    /* renamed from: d */
    public final boolean mo10301d(int i) {
        if (i < 64) {
            long j = 1 << i;
            long j2 = this.f16025a;
            boolean z = (j2 & j) != 0;
            long j3 = j2 & (j ^ -1);
            this.f16025a = j3;
            long j4 = j - 1;
            this.f16025a = Long.rotateRight((j4 ^ -1) & j3, 1) | (j3 & j4);
            C0561uo uoVar = this.f16026b;
            if (uoVar != null) {
                if (uoVar.mo10300c(0)) {
                    mo10297a(63);
                }
                this.f16026b.mo10301d(0);
            }
            return z;
        }
        m15525b();
        return this.f16026b.mo10301d(i - 64);
    }

    /* renamed from: a */
    public final void mo10296a() {
        this.f16025a = 0;
        C0561uo uoVar = this.f16026b;
        if (uoVar != null) {
            uoVar.mo10296a();
        }
    }

    /* renamed from: a */
    public final void mo10297a(int i) {
        if (i >= 64) {
            m15525b();
            this.f16026b.mo10297a(i - 64);
            return;
        }
        this.f16025a |= 1 << i;
    }

    public final String toString() {
        if (this.f16026b == null) {
            return Long.toBinaryString(this.f16025a);
        }
        return this.f16026b.toString() + "xx" + Long.toBinaryString(this.f16025a);
    }
}
