package p000;

/* renamed from: kt */
/* compiled from: PG */
public final class C0296kt implements Cloneable {

    /* renamed from: a */
    public static final Object f15153a = new Object();

    /* renamed from: b */
    public boolean f15154b = false;

    /* renamed from: c */
    public long[] f15155c;

    /* renamed from: d */
    public Object[] f15156d;

    /* renamed from: e */
    public int f15157e;

    public C0296kt() {
        int b = C0294kr.m14540b(10);
        this.f15155c = new long[b];
        this.f15156d = new Object[b];
    }

    /* renamed from: c */
    public final void mo9234c() {
        int i = this.f15157e;
        Object[] objArr = this.f15156d;
        for (int i2 = 0; i2 < i; i2++) {
            objArr[i2] = null;
        }
        this.f15157e = 0;
        this.f15154b = false;
    }

    /* access modifiers changed from: private */
    /* renamed from: d */
    public final C0296kt clone() {
        try {
            C0296kt ktVar = (C0296kt) super.clone();
            ktVar.f15155c = (long[]) this.f15155c.clone();
            ktVar.f15156d = (Object[]) this.f15156d.clone();
            return ktVar;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }

    /* renamed from: a */
    public final void mo9230a() {
        int i = this.f15157e;
        long[] jArr = this.f15155c;
        Object[] objArr = this.f15156d;
        int i2 = 0;
        for (int i3 = 0; i3 < i; i3++) {
            Object obj = objArr[i3];
            if (obj != f15153a) {
                if (i3 != i2) {
                    jArr[i2] = jArr[i3];
                    objArr[i2] = obj;
                    objArr[i3] = null;
                }
                i2++;
            }
        }
        this.f15154b = false;
        this.f15157e = i2;
    }

    /* renamed from: a */
    public final Object mo9229a(long j) {
        int a = C0294kr.m14538a(this.f15155c, this.f15157e, j);
        if (a < 0) {
            return null;
        }
        Object[] objArr = this.f15156d;
        if (objArr[a] != f15153a) {
            return objArr[a];
        }
        return null;
    }

    /* renamed from: a */
    public final long mo9228a(int i) {
        if (this.f15154b) {
            mo9230a();
        }
        return this.f15155c[i];
    }

    /* renamed from: a */
    public final void mo9231a(long j, Object obj) {
        int a = C0294kr.m14538a(this.f15155c, this.f15157e, j);
        if (a < 0) {
            int i = a ^ -1;
            if (i < this.f15157e) {
                Object[] objArr = this.f15156d;
                if (objArr[i] == f15153a) {
                    this.f15155c[i] = j;
                    objArr[i] = obj;
                    return;
                }
            }
            if (this.f15154b && this.f15157e >= this.f15155c.length) {
                mo9230a();
                i = C0294kr.m14538a(this.f15155c, this.f15157e, j) ^ -1;
            }
            int i2 = this.f15157e;
            if (i2 >= this.f15155c.length) {
                int b = C0294kr.m14540b(i2 + 1);
                long[] jArr = new long[b];
                Object[] objArr2 = new Object[b];
                long[] jArr2 = this.f15155c;
                System.arraycopy(jArr2, 0, jArr, 0, jArr2.length);
                Object[] objArr3 = this.f15156d;
                System.arraycopy(objArr3, 0, objArr2, 0, objArr3.length);
                this.f15155c = jArr;
                this.f15156d = objArr2;
            }
            int i3 = this.f15157e - i;
            if (i3 != 0) {
                long[] jArr3 = this.f15155c;
                int i4 = i + 1;
                System.arraycopy(jArr3, i, jArr3, i4, i3);
                Object[] objArr4 = this.f15156d;
                System.arraycopy(objArr4, i, objArr4, i4, this.f15157e - i);
            }
            this.f15155c[i] = j;
            this.f15156d[i] = obj;
            this.f15157e++;
            return;
        }
        this.f15156d[a] = obj;
    }

    /* renamed from: b */
    public final int mo9232b() {
        if (this.f15154b) {
            mo9230a();
        }
        return this.f15157e;
    }

    public final String toString() {
        if (mo9232b() <= 0) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder(this.f15157e * 28);
        sb.append('{');
        for (int i = 0; i < this.f15157e; i++) {
            if (i > 0) {
                sb.append(", ");
            }
            sb.append(mo9228a(i));
            sb.append('=');
            Object b = mo9233b(i);
            if (b == this) {
                sb.append("(this Map)");
            } else {
                sb.append(b);
            }
        }
        sb.append('}');
        return sb.toString();
    }

    /* renamed from: b */
    public final Object mo9233b(int i) {
        if (this.f15154b) {
            mo9230a();
        }
        return this.f15156d[i];
    }
}
