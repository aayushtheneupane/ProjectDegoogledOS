package p000;

import java.util.Arrays;

/* renamed from: ilm */
/* compiled from: PG */
public final class ilm {

    /* renamed from: a */
    public static final ilm f14449a = new ilm(0, new int[0], new Object[0], false);

    /* renamed from: b */
    public int f14450b;

    /* renamed from: c */
    public int[] f14451c;

    /* renamed from: d */
    public Object[] f14452d;

    /* renamed from: e */
    public int f14453e;

    /* renamed from: f */
    private boolean f14454f;

    private ilm() {
        this(0, new int[8], new Object[8], true);
    }

    private ilm(int i, int[] iArr, Object[] objArr, boolean z) {
        this.f14453e = -1;
        this.f14450b = i;
        this.f14451c = iArr;
        this.f14452d = objArr;
        this.f14454f = z;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && (obj instanceof ilm)) {
            ilm ilm = (ilm) obj;
            int i = this.f14450b;
            if (i == ilm.f14450b) {
                int[] iArr = this.f14451c;
                int[] iArr2 = ilm.f14451c;
                int i2 = 0;
                while (true) {
                    if (i2 < i) {
                        if (iArr[i2] != iArr2[i2]) {
                            break;
                        }
                        i2++;
                    } else {
                        Object[] objArr = this.f14452d;
                        Object[] objArr2 = ilm.f14452d;
                        int i3 = this.f14450b;
                        int i4 = 0;
                        while (i4 < i3) {
                            if (objArr[i4].equals(objArr2[i4])) {
                                i4++;
                            }
                        }
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /* renamed from: c */
    public final int mo8946c() {
        int i;
        int i2 = this.f14453e;
        if (i2 != -1) {
            return i2;
        }
        int i3 = 0;
        for (int i4 = 0; i4 < this.f14450b; i4++) {
            int i5 = this.f14451c[i4];
            int b = imd.m14074b(i5);
            int a = imd.m14072a(i5);
            if (a == 0) {
                i = iie.m13418e(b, ((Long) this.f14452d[i4]).longValue());
            } else if (a == 1) {
                ((Long) this.f14452d[i4]).longValue();
                i = iie.m13434n(b);
            } else if (a == 2) {
                i = iie.m13413c(b, (ihw) this.f14452d[i4]);
            } else if (a == 3) {
                int f = iie.m13420f(b);
                i = f + f + ((ilm) this.f14452d[i4]).mo8946c();
            } else if (a == 5) {
                ((Integer) this.f14452d[i4]).intValue();
                i = iie.m13433m(b);
            } else {
                throw new IllegalStateException(ijh.m13659f());
            }
            i3 += i;
        }
        this.f14453e = i3;
        return i3;
    }

    public final int hashCode() {
        int i = this.f14450b;
        int i2 = (i + 527) * 31;
        int[] iArr = this.f14451c;
        int i3 = 17;
        int i4 = 17;
        for (int i5 = 0; i5 < i; i5++) {
            i4 = (i4 * 31) + iArr[i5];
        }
        int i6 = (i2 + i4) * 31;
        Object[] objArr = this.f14452d;
        int i7 = this.f14450b;
        for (int i8 = 0; i8 < i7; i8++) {
            i3 = (i3 * 31) + objArr[i8].hashCode();
        }
        return i6 + i3;
    }

    /* renamed from: b */
    public final void mo8945b() {
        this.f14454f = false;
    }

    /* renamed from: a */
    static ilm m13975a(ilm ilm, ilm ilm2) {
        int i = ilm.f14450b + ilm2.f14450b;
        int[] copyOf = Arrays.copyOf(ilm.f14451c, i);
        System.arraycopy(ilm2.f14451c, 0, copyOf, ilm.f14450b, ilm2.f14450b);
        Object[] copyOf2 = Arrays.copyOf(ilm.f14452d, i);
        System.arraycopy(ilm2.f14452d, 0, copyOf2, ilm.f14450b, ilm2.f14450b);
        return new ilm(i, copyOf, copyOf2, true);
    }

    /* renamed from: a */
    public static ilm m13974a() {
        return new ilm();
    }

    /* renamed from: a */
    public final void mo8943a(int i, Object obj) {
        int i2;
        if (this.f14454f) {
            int i3 = this.f14450b;
            int[] iArr = this.f14451c;
            if (i3 == iArr.length) {
                if (i3 >= 4) {
                    i2 = i3 >> 1;
                } else {
                    i2 = 8;
                }
                int i4 = i3 + i2;
                this.f14451c = Arrays.copyOf(iArr, i4);
                this.f14452d = Arrays.copyOf(this.f14452d, i4);
            }
            int[] iArr2 = this.f14451c;
            int i5 = this.f14450b;
            iArr2[i5] = i;
            this.f14452d[i5] = obj;
            this.f14450b = i5 + 1;
            return;
        }
        throw new UnsupportedOperationException();
    }

    /* renamed from: a */
    public final void mo8944a(ime ime) {
        if (this.f14450b != 0) {
            for (int i = 0; i < this.f14450b; i++) {
                int i2 = this.f14451c[i];
                Object obj = this.f14452d[i];
                int b = imd.m14074b(i2);
                int a = imd.m14072a(i2);
                if (a == 0) {
                    ime.mo8690a(b, ((Long) obj).longValue());
                } else if (a == 1) {
                    ime.mo8702d(b, ((Long) obj).longValue());
                } else if (a == 2) {
                    ime.mo8691a(b, (ihw) obj);
                } else if (a == 3) {
                    iif iif = (iif) ime;
                    iif.f14238a.mo8655a(b, 3);
                    ((ilm) obj).mo8944a(ime);
                    iif.f14238a.mo8655a(b, 4);
                } else if (a == 5) {
                    ime.mo8701d(b, ((Integer) obj).intValue());
                } else {
                    throw new RuntimeException(ijh.m13659f());
                }
            }
        }
    }
}
