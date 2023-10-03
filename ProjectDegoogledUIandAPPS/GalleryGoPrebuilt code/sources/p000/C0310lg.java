package p000;

/* renamed from: lg */
/* compiled from: PG */
public final class C0310lg implements Cloneable {

    /* renamed from: e */
    private static final Object f15195e = new Object();

    /* renamed from: a */
    public boolean f15196a;

    /* renamed from: b */
    public int[] f15197b;

    /* renamed from: c */
    public Object[] f15198c;

    /* renamed from: d */
    public int f15199d;

    public C0310lg() {
        this(10);
    }

    public C0310lg(int i) {
        this.f15196a = false;
        if (i == 0) {
            this.f15197b = C0294kr.f15150a;
            this.f15198c = C0294kr.f15151b;
            return;
        }
        int a = C0294kr.m14536a(i);
        this.f15197b = new int[a];
        this.f15198c = new Object[a];
    }

    /* access modifiers changed from: private */
    /* renamed from: c */
    public final C0310lg clone() {
        try {
            C0310lg lgVar = (C0310lg) super.clone();
            lgVar.f15197b = (int[]) this.f15197b.clone();
            lgVar.f15198c = (Object[]) this.f15198c.clone();
            return lgVar;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }

    /* renamed from: a */
    public final void mo9336a() {
        int i = this.f15199d;
        int[] iArr = this.f15197b;
        Object[] objArr = this.f15198c;
        int i2 = 0;
        for (int i3 = 0; i3 < i; i3++) {
            Object obj = objArr[i3];
            if (obj != f15195e) {
                if (i3 != i2) {
                    iArr[i2] = iArr[i3];
                    objArr[i2] = obj;
                    objArr[i3] = null;
                }
                i2++;
            }
        }
        this.f15196a = false;
        this.f15199d = i2;
    }

    /* renamed from: a */
    public final Object mo9335a(int i) {
        int a = C0294kr.m14537a(this.f15197b, this.f15199d, i);
        if (a < 0) {
            return null;
        }
        Object[] objArr = this.f15198c;
        if (objArr[a] != f15195e) {
            return objArr[a];
        }
        return null;
    }

    /* renamed from: c */
    public final int mo9340c(int i) {
        if (this.f15196a) {
            mo9336a();
        }
        return this.f15197b[i];
    }

    /* renamed from: a */
    public final void mo9337a(int i, Object obj) {
        int a = C0294kr.m14537a(this.f15197b, this.f15199d, i);
        if (a < 0) {
            int i2 = a ^ -1;
            if (i2 < this.f15199d) {
                Object[] objArr = this.f15198c;
                if (objArr[i2] == f15195e) {
                    this.f15197b[i2] = i;
                    objArr[i2] = obj;
                    return;
                }
            }
            if (this.f15196a && this.f15199d >= this.f15197b.length) {
                mo9336a();
                i2 = C0294kr.m14537a(this.f15197b, this.f15199d, i) ^ -1;
            }
            int i3 = this.f15199d;
            if (i3 >= this.f15197b.length) {
                int a2 = C0294kr.m14536a(i3 + 1);
                int[] iArr = new int[a2];
                Object[] objArr2 = new Object[a2];
                int[] iArr2 = this.f15197b;
                System.arraycopy(iArr2, 0, iArr, 0, iArr2.length);
                Object[] objArr3 = this.f15198c;
                System.arraycopy(objArr3, 0, objArr2, 0, objArr3.length);
                this.f15197b = iArr;
                this.f15198c = objArr2;
            }
            int i4 = this.f15199d - i2;
            if (i4 != 0) {
                int[] iArr3 = this.f15197b;
                int i5 = i2 + 1;
                System.arraycopy(iArr3, i2, iArr3, i5, i4);
                Object[] objArr4 = this.f15198c;
                System.arraycopy(objArr4, i2, objArr4, i5, this.f15199d - i2);
            }
            this.f15197b[i2] = i;
            this.f15198c[i2] = obj;
            this.f15199d++;
            return;
        }
        this.f15198c[a] = obj;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x000a, code lost:
        r0 = r3.f15198c;
     */
    /* renamed from: b */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void mo9339b(int r4) {
        /*
            r3 = this;
            int[] r0 = r3.f15197b
            int r1 = r3.f15199d
            int r4 = p000.C0294kr.m14537a((int[]) r0, (int) r1, (int) r4)
            if (r4 < 0) goto L_0x0017
            java.lang.Object[] r0 = r3.f15198c
            r1 = r0[r4]
            java.lang.Object r2 = f15195e
            if (r1 == r2) goto L_0x0017
            r0[r4] = r2
            r4 = 1
            r3.f15196a = r4
        L_0x0017:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.C0310lg.mo9339b(int):void");
    }

    /* renamed from: b */
    public final int mo9338b() {
        if (this.f15196a) {
            mo9336a();
        }
        return this.f15199d;
    }

    public final String toString() {
        if (mo9338b() <= 0) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder(this.f15199d * 28);
        sb.append('{');
        for (int i = 0; i < this.f15199d; i++) {
            if (i > 0) {
                sb.append(", ");
            }
            sb.append(mo9340c(i));
            sb.append('=');
            Object d = mo9342d(i);
            if (d == this) {
                sb.append("(this Map)");
            } else {
                sb.append(d);
            }
        }
        sb.append('}');
        return sb.toString();
    }

    /* renamed from: d */
    public final Object mo9342d(int i) {
        if (this.f15196a) {
            mo9336a();
        }
        return this.f15198c[i];
    }
}
