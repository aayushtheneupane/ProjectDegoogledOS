package p000;

import java.util.Arrays;

/* renamed from: hut */
/* compiled from: PG */
public class hut {

    /* renamed from: a */
    public transient Object[] f13429a;

    /* renamed from: b */
    public transient int[] f13430b;

    /* renamed from: c */
    public transient int f13431c;

    /* renamed from: d */
    public transient int f13432d;

    /* renamed from: e */
    public transient int[] f13433e;

    /* renamed from: f */
    public transient long[] f13434f;

    /* renamed from: g */
    private transient float f13435g;

    /* renamed from: h */
    private transient int f13436h;

    public hut() {
        mo8115i(3);
    }

    /* renamed from: a */
    private static int m12161a(long j) {
        return (int) (j >>> 32);
    }

    /* renamed from: a */
    private static long m12162a(long j, int i) {
        return (j & -4294967296L) | (((long) i) & 4294967295L);
    }

    /* renamed from: a */
    public int mo8100a() {
        return this.f13431c == 0 ? -1 : 0;
    }

    /* renamed from: a */
    public int mo8101a(int i) {
        int i2 = i + 1;
        if (i2 >= this.f13431c) {
            return -1;
        }
        return i2;
    }

    public hut(int i) {
        this(i, (byte[]) null);
    }

    public hut(int i, byte[] bArr) {
        mo8115i(i);
    }

    public hut(hut hut) {
        mo8115i(hut.f13431c);
        int a = hut.mo8100a();
        while (a != -1) {
            mo8108b(hut.mo8107b(a), hut.mo8109c(a));
            a = hut.mo8101a(a);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: e */
    public final void mo8111e(int i) {
        if (i > this.f13434f.length) {
            mo8112f(i);
        }
        if (i >= this.f13436h) {
            int highestOneBit = Integer.highestOneBit(i - 1);
            m12165k(Math.max(2, highestOneBit + highestOneBit));
        }
    }

    /* renamed from: b */
    public final int mo8106b(Object obj) {
        int a = mo8102a(obj);
        if (a != -1) {
            return this.f13430b[a];
        }
        return 0;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: d */
    public final hun mo8110d(int i) {
        ife.m12873b(i, this.f13431c);
        return new hup(this, i);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public final Object mo8107b(int i) {
        ife.m12873b(i, this.f13431c);
        return this.f13429a[i];
    }

    /* access modifiers changed from: package-private */
    /* renamed from: c */
    public final int mo8109c(int i) {
        ife.m12873b(i, this.f13431c);
        return this.f13430b[i];
    }

    /* renamed from: b */
    private final int m12163b() {
        return this.f13433e.length - 1;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final int mo8102a(Object obj) {
        int b = ife.m12863b(obj);
        int i = this.f13433e[m12163b() & b];
        while (i != -1) {
            long j = this.f13434f[i];
            if (m12161a(j) == b && ife.m12891c(obj, this.f13429a[i])) {
                return i;
            }
            i = (int) j;
        }
        return -1;
    }

    /* renamed from: i */
    public void mo8115i(int i) {
        ife.m12845a(i >= 0, (Object) "Initial capacity must be non-negative");
        ife.m12845a(true, (Object) "Illegal load factor");
        int e = ife.m12897e(i);
        this.f13433e = m12164j(e);
        this.f13435g = 1.0f;
        this.f13429a = new Object[i];
        this.f13430b = new int[i];
        long[] jArr = new long[i];
        Arrays.fill(jArr, -1);
        this.f13434f = jArr;
        this.f13436h = Math.max(1, (int) ((float) e));
    }

    /* renamed from: a */
    public void mo8105a(int i, Object obj, int i2, int i3) {
        this.f13434f[i] = (((long) i3) << 32) | 4294967295L;
        this.f13429a[i] = obj;
        this.f13430b[i] = i2;
    }

    /* renamed from: h */
    public void mo8114h(int i) {
        int i2 = this.f13431c - 1;
        if (i < i2) {
            Object[] objArr = this.f13429a;
            objArr[i] = objArr[i2];
            int[] iArr = this.f13430b;
            iArr[i] = iArr[i2];
            objArr[i2] = null;
            iArr[i2] = 0;
            long[] jArr = this.f13434f;
            long j = jArr[i2];
            jArr[i] = j;
            jArr[i2] = -1;
            int a = m12161a(j) & m12163b();
            int[] iArr2 = this.f13433e;
            int i3 = iArr2[a];
            if (i3 != i2) {
                while (true) {
                    long[] jArr2 = this.f13434f;
                    long j2 = jArr2[i3];
                    int i4 = (int) j2;
                    if (i4 != i2) {
                        i3 = i4;
                    } else {
                        jArr2[i3] = m12162a(j2, i);
                        return;
                    }
                }
            } else {
                iArr2[a] = i;
            }
        } else {
            this.f13429a[i] = null;
            this.f13430b[i] = 0;
            this.f13434f[i] = -1;
        }
    }

    /* renamed from: j */
    private static int[] m12164j(int i) {
        int[] iArr = new int[i];
        Arrays.fill(iArr, -1);
        return iArr;
    }

    /* renamed from: b */
    public final void mo8108b(Object obj, int i) {
        if (i > 0) {
            long[] jArr = this.f13434f;
            Object[] objArr = this.f13429a;
            int[] iArr = this.f13430b;
            int b = ife.m12863b(obj);
            int b2 = m12163b() & b;
            int i2 = this.f13431c;
            int[] iArr2 = this.f13433e;
            int i3 = iArr2[b2];
            if (i3 != -1) {
                while (true) {
                    long j = jArr[i3];
                    if (m12161a(j) != b || !ife.m12891c(obj, objArr[i3])) {
                        int i4 = (int) j;
                        if (i4 == -1) {
                            jArr[i3] = m12162a(j, i2);
                            break;
                        }
                        i3 = i4;
                    } else {
                        iArr[i3] = i;
                        return;
                    }
                }
            } else {
                iArr2[b2] = i2;
            }
            int i5 = Integer.MAX_VALUE;
            if (i2 != Integer.MAX_VALUE) {
                int i6 = i2 + 1;
                int length = this.f13434f.length;
                if (i6 > length) {
                    int max = Math.max(1, length >>> 1) + length;
                    if (max >= 0) {
                        i5 = max;
                    }
                    if (i5 != length) {
                        mo8112f(i5);
                    }
                }
                mo8105a(i2, obj, i, b);
                this.f13431c = i6;
                if (i2 >= this.f13436h) {
                    int length2 = this.f13433e.length;
                    m12165k(length2 + length2);
                }
                this.f13432d++;
                return;
            }
            throw new IllegalStateException("Cannot contain more than Integer.MAX_VALUE elements!");
        }
        StringBuilder sb = new StringBuilder(43);
        sb.append("count must be positive but was: ");
        sb.append(i);
        throw new IllegalArgumentException(sb.toString());
    }

    /* renamed from: a */
    public final int mo8103a(Object obj, int i) {
        int b = m12163b() & i;
        int i2 = this.f13433e[b];
        if (i2 != -1) {
            int i3 = -1;
            while (true) {
                if (m12161a(this.f13434f[i2]) != i || !ife.m12891c(obj, this.f13429a[i2])) {
                    int i4 = (int) this.f13434f[i2];
                    if (i4 == -1) {
                        break;
                    }
                    int i5 = i4;
                    i3 = i2;
                    i2 = i5;
                } else {
                    int i6 = this.f13430b[i2];
                    if (i3 == -1) {
                        this.f13433e[b] = (int) this.f13434f[i2];
                    } else {
                        long[] jArr = this.f13434f;
                        jArr[i3] = m12162a(jArr[i3], (int) jArr[i2]);
                    }
                    mo8114h(i2);
                    this.f13431c--;
                    this.f13432d++;
                    return i6;
                }
            }
        }
        return 0;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: g */
    public final int mo8113g(int i) {
        return mo8103a(this.f13429a[i], m12161a(this.f13434f[i]));
    }

    /* renamed from: f */
    public void mo8112f(int i) {
        this.f13429a = Arrays.copyOf(this.f13429a, i);
        this.f13430b = Arrays.copyOf(this.f13430b, i);
        long[] jArr = this.f13434f;
        int length = jArr.length;
        long[] copyOf = Arrays.copyOf(jArr, i);
        if (i > length) {
            Arrays.fill(copyOf, length, i, -1);
        }
        this.f13434f = copyOf;
    }

    /* renamed from: k */
    private final void m12165k(int i) {
        if (this.f13433e.length < 1073741824) {
            int i2 = ((int) (((float) i) * this.f13435g)) + 1;
            int[] j = m12164j(i);
            long[] jArr = this.f13434f;
            int length = j.length - 1;
            for (int i3 = 0; i3 < this.f13431c; i3++) {
                int a = m12161a(jArr[i3]);
                int i4 = a & length;
                int i5 = j[i4];
                j[i4] = i3;
                jArr[i3] = (((long) a) << 32) | (((long) i5) & 4294967295L);
            }
            this.f13436h = i2;
            this.f13433e = j;
            return;
        }
        this.f13436h = Integer.MAX_VALUE;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final void mo8104a(int i, int i2) {
        ife.m12873b(i, this.f13431c);
        this.f13430b[i] = i2;
    }
}
