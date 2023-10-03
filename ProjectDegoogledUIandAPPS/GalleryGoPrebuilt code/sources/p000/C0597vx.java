package p000;

import android.util.SparseIntArray;

/* renamed from: vx */
/* compiled from: PG */
public abstract class C0597vx {

    /* renamed from: a */
    private final SparseIntArray f16168a = new SparseIntArray();

    /* renamed from: b */
    private final SparseIntArray f16169b = new SparseIntArray();

    /* renamed from: c */
    private boolean f16170c = false;

    /* renamed from: a */
    public abstract int mo2711a(int i);

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public final int mo10417b(int i, int i2) {
        if (!this.f16170c) {
            return mo10415a(i, i2);
        }
        int i3 = this.f16168a.get(i, -1);
        if (i3 != -1) {
            return i3;
        }
        int a = mo10415a(i, i2);
        this.f16168a.put(i, a);
        return a;
    }

    /* renamed from: c */
    public final int mo10419c(int i, int i2) {
        int a = mo2711a(i);
        int i3 = 0;
        int i4 = 0;
        for (int i5 = 0; i5 < i; i5++) {
            int a2 = mo2711a(i5);
            i3 += a2;
            if (i3 == i2) {
                i4++;
                i3 = 0;
            } else if (i3 > i2) {
                i4++;
                i3 = a2;
            }
        }
        return i3 + a > i2 ? i4 + 1 : i4;
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x004e  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0060 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0061 A[RETURN] */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int mo10415a(int r9, int r10) {
        /*
            r8 = this;
            int r0 = r8.mo2711a(r9)
            r1 = 0
            if (r0 == r10) goto L_0x0062
            boolean r2 = r8.f16170c
            if (r2 == 0) goto L_0x0049
            android.util.SparseIntArray r2 = r8.f16168a
            int r3 = r2.size()
            r4 = -1
            int r3 = r3 + r4
            r5 = 0
        L_0x0014:
            if (r5 > r3) goto L_0x0028
            int r6 = r5 + r3
            int r6 = r6 >>> 1
            int r7 = r2.keyAt(r6)
            if (r7 >= r9) goto L_0x0023
            int r5 = r6 + 1
            goto L_0x0026
        L_0x0023:
            int r6 = r6 + -1
            r3 = r6
        L_0x0026:
            goto L_0x0014
        L_0x0028:
            int r5 = r5 + r4
            if (r5 < 0) goto L_0x0037
            int r3 = r2.size()
            if (r5 >= r3) goto L_0x0036
            int r4 = r2.keyAt(r5)
            goto L_0x0038
        L_0x0036:
        L_0x0037:
        L_0x0038:
            if (r4 < 0) goto L_0x0048
            android.util.SparseIntArray r2 = r8.f16168a
            int r2 = r2.get(r4)
            int r3 = r8.mo2711a(r4)
            int r2 = r2 + r3
            int r4 = r4 + 1
            goto L_0x004c
        L_0x0048:
            goto L_0x004a
        L_0x0049:
        L_0x004a:
            r2 = 0
            r4 = 0
        L_0x004c:
            if (r4 >= r9) goto L_0x005d
            int r3 = r8.mo2711a(r4)
            int r2 = r2 + r3
            if (r2 != r10) goto L_0x0057
            r2 = 0
            goto L_0x005a
        L_0x0057:
            if (r2 <= r10) goto L_0x005a
            r2 = r3
        L_0x005a:
            int r4 = r4 + 1
            goto L_0x004c
        L_0x005d:
            int r0 = r0 + r2
            if (r0 > r10) goto L_0x0061
            return r2
        L_0x0061:
            return r1
        L_0x0062:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.C0597vx.mo10415a(int, int):int");
    }

    /* renamed from: b */
    public final void mo10418b() {
        this.f16169b.clear();
    }

    /* renamed from: a */
    public final void mo10416a() {
        this.f16168a.clear();
    }

    /* renamed from: c */
    public final void mo10420c() {
        this.f16170c = true;
    }
}
