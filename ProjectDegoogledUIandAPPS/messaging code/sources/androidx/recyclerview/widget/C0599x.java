package androidx.recyclerview.widget;

import android.util.SparseIntArray;

/* renamed from: androidx.recyclerview.widget.x */
public final class C0599x {

    /* renamed from: Yq */
    final SparseIntArray f677Yq = new SparseIntArray();

    /* renamed from: Zq */
    final SparseIntArray f678Zq = new SparseIntArray();

    /* renamed from: _q */
    private boolean f679_q = false;

    /* renamed from: ar */
    private boolean f680ar = false;

    /* renamed from: P */
    public int mo5263P(int i) {
        return 1;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: f */
    public int mo5264f(int i, int i2) {
        if (!this.f680ar) {
            return mo5266h(i, i2);
        }
        int i3 = this.f678Zq.get(i, -1);
        if (i3 != -1) {
            return i3;
        }
        int h = mo5266h(i, i2);
        this.f678Zq.put(i, h);
        return h;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: g */
    public int mo5265g(int i, int i2) {
        if (!this.f679_q) {
            return mo5267i(i, i2);
        }
        int i3 = this.f677Yq.get(i, -1);
        if (i3 != -1) {
            return i3;
        }
        int i4 = mo5267i(i, i2);
        this.f677Yq.put(i, i4);
        return i4;
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x004b  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x005e  */
    /* JADX WARNING: Removed duplicated region for block: B:39:? A[RETURN, SYNTHETIC] */
    /* renamed from: h */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int mo5266h(int r9, int r10) {
        /*
            r8 = this;
            boolean r0 = r8.f680ar
            r1 = 0
            r2 = 1
            if (r0 == 0) goto L_0x0046
            android.util.SparseIntArray r0 = r8.f678Zq
            int r3 = r0.size()
            r4 = -1
            int r3 = r3 + r4
            r5 = r3
            r3 = r1
        L_0x0010:
            if (r3 > r5) goto L_0x0021
            int r6 = r3 + r5
            int r6 = r6 >>> r2
            int r7 = r0.keyAt(r6)
            if (r7 >= r9) goto L_0x001e
            int r3 = r6 + 1
            goto L_0x0010
        L_0x001e:
            int r5 = r6 + -1
            goto L_0x0010
        L_0x0021:
            int r3 = r3 + r4
            if (r3 < 0) goto L_0x002f
            int r5 = r0.size()
            if (r3 >= r5) goto L_0x002f
            int r0 = r0.keyAt(r3)
            goto L_0x0030
        L_0x002f:
            r0 = r4
        L_0x0030:
            if (r0 == r4) goto L_0x0046
            android.util.SparseIntArray r3 = r8.f678Zq
            int r3 = r3.get(r0)
            int r4 = r0 + 1
            int r8 = r8.mo5265g(r0, r10)
            int r8 = r8 + r2
            if (r8 != r10) goto L_0x0049
            int r8 = r3 + 1
            r3 = r8
            r8 = r1
            goto L_0x0049
        L_0x0046:
            r8 = r1
            r3 = r8
            r4 = r3
        L_0x0049:
            if (r4 >= r9) goto L_0x005b
            int r8 = r8 + 1
            if (r8 != r10) goto L_0x0053
            int r3 = r3 + 1
            r8 = r1
            goto L_0x0058
        L_0x0053:
            if (r8 <= r10) goto L_0x0058
            int r3 = r3 + 1
            r8 = r2
        L_0x0058:
            int r4 = r4 + 1
            goto L_0x0049
        L_0x005b:
            int r8 = r8 + r2
            if (r8 <= r10) goto L_0x0060
            int r3 = r3 + 1
        L_0x0060:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.C0599x.mo5266h(int, int):int");
    }

    /* renamed from: i */
    public int mo5267i(int i, int i2) {
        return i % i2;
    }
}
