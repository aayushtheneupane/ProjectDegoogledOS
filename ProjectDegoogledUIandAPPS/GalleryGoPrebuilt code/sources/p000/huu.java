package p000;

import java.util.Arrays;

/* renamed from: huu */
/* compiled from: PG */
public final class huu extends hut {

    /* renamed from: g */
    private transient long[] f13437g;

    /* renamed from: h */
    private transient int f13438h;

    /* renamed from: i */
    private transient int f13439i;

    public huu() {
        super(3, (byte[]) null);
    }

    /* renamed from: a */
    public final int mo8100a() {
        int i = this.f13438h;
        if (i == -2) {
            return -1;
        }
        return i;
    }

    public huu(hut hut) {
        mo8115i(hut.f13431c);
        int a = hut.mo8100a();
        while (a != -1) {
            mo8108b(hut.mo8107b(a), hut.mo8109c(a));
            a = hut.mo8101a(a);
        }
    }

    /* renamed from: j */
    private final int m12183j(int i) {
        return (int) (this.f13437g[i] >>> 32);
    }

    /* renamed from: k */
    private final int m12184k(int i) {
        return (int) this.f13437g[i];
    }

    /* renamed from: i */
    public final void mo8115i(int i) {
        super.mo8115i(i);
        this.f13438h = -2;
        this.f13439i = -2;
        long[] jArr = new long[i];
        this.f13437g = jArr;
        Arrays.fill(jArr, -1);
    }

    /* renamed from: a */
    public final void mo8105a(int i, Object obj, int i2, int i3) {
        super.mo8105a(i, obj, i2, i3);
        m12182b(this.f13439i, i);
        m12182b(i, -2);
    }

    /* renamed from: h */
    public final void mo8114h(int i) {
        int i2 = this.f13431c - 1;
        m12182b(m12183j(i), m12184k(i));
        if (i < i2) {
            m12182b(m12183j(i2), i);
            m12182b(i, m12184k(i2));
        }
        super.mo8114h(i);
    }

    /* renamed from: a */
    public final int mo8101a(int i) {
        int k = m12184k(i);
        if (k == -2) {
            return -1;
        }
        return k;
    }

    /* renamed from: f */
    public final void mo8112f(int i) {
        super.mo8112f(i);
        long[] jArr = this.f13437g;
        int length = jArr.length;
        long[] copyOf = Arrays.copyOf(jArr, i);
        this.f13437g = copyOf;
        Arrays.fill(copyOf, length, i, -1);
    }

    /* renamed from: b */
    private final void m12182b(int i, int i2) {
        if (i != -2) {
            long[] jArr = this.f13437g;
            jArr[i] = (jArr[i] & -4294967296L) | (((long) i2) & 4294967295L);
        } else {
            this.f13438h = i2;
        }
        if (i2 != -2) {
            long[] jArr2 = this.f13437g;
            jArr2[i2] = (4294967295L & jArr2[i2]) | (((long) i) << 32);
            return;
        }
        this.f13439i = i;
    }
}
