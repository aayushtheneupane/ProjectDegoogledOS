package p000;

import java.io.Serializable;

/* renamed from: df */
/* compiled from: PG */
final class C0091df implements Serializable, C0086da {
    public static final long serialVersionUID = 1;

    /* renamed from: a */
    private final int f6421a;

    /* renamed from: b */
    private final boolean f6422b;

    /* renamed from: c */
    private final boolean f6423c;

    /* renamed from: d */
    private final double f6424d;

    /* renamed from: e */
    private final double f6425e;

    /* renamed from: f */
    private final long[] f6426f;

    /* renamed from: g */
    private final int f6427g;

    public C0091df(int i, boolean z, int i2, boolean z2, double d, double d2, long[] jArr) {
        this.f6421a = i;
        this.f6422b = z;
        this.f6423c = z2;
        this.f6424d = d;
        this.f6425e = d2;
        this.f6426f = jArr;
        this.f6427g = i2;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0035, code lost:
        if ((r3 - r5) != 0.0d) goto L_0x0040;
     */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x002a  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x0048  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x005a  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x005c  */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x0068  */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x0087 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x0088 A[RETURN] */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean mo3936a(p000.C0087db r10) {
        /*
            r9 = this;
            int r0 = r9.f6427g
            int r1 = r0 + -1
            r2 = 1
            if (r1 == r2) goto L_0x0023
            r3 = 2
            if (r1 == r3) goto L_0x0020
            r3 = 3
            if (r1 == r3) goto L_0x001d
            r3 = 4
            if (r1 == r3) goto L_0x0019
            r3 = 5
            if (r1 == r3) goto L_0x0016
            double r3 = r10.f6159a
            goto L_0x0026
        L_0x0016:
            int r1 = r10.f6161c
            goto L_0x001b
        L_0x0019:
            int r1 = r10.f6160b
        L_0x001b:
            double r3 = (double) r1
            goto L_0x0026
        L_0x001d:
            long r3 = r10.f6163e
            goto L_0x0025
        L_0x0020:
            long r3 = r10.f6162d
            goto L_0x0025
        L_0x0023:
            long r3 = r10.f6164f
        L_0x0025:
            double r3 = (double) r3
        L_0x0026:
            boolean r1 = r9.f6423c
            if (r1 == 0) goto L_0x0038
            long r5 = (long) r3
            double r5 = (double) r5
            java.lang.Double.isNaN(r5)
            double r5 = r3 - r5
            r7 = 0
            int r1 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r1 == 0) goto L_0x0038
            goto L_0x0040
        L_0x0038:
            r1 = 7
            if (r0 == r1) goto L_0x003c
        L_0x003b:
            goto L_0x0044
        L_0x003c:
            int r10 = r10.f6160b
            if (r10 == 0) goto L_0x003b
        L_0x0040:
            boolean r10 = r9.f6422b
            r10 = r10 ^ r2
            return r10
        L_0x0044:
            int r10 = r9.f6421a
            if (r10 == 0) goto L_0x004d
            double r0 = (double) r10
            java.lang.Double.isNaN(r0)
            double r3 = r3 % r0
        L_0x004d:
            double r0 = r9.f6424d
            r10 = 0
            int r5 = (r3 > r0 ? 1 : (r3 == r0 ? 0 : -1))
            if (r5 < 0) goto L_0x005c
            double r0 = r9.f6425e
            int r5 = (r3 > r0 ? 1 : (r3 == r0 ? 0 : -1))
            if (r5 > 0) goto L_0x005c
            r0 = 1
            goto L_0x005d
        L_0x005c:
            r0 = 0
        L_0x005d:
            if (r0 != 0) goto L_0x0060
            goto L_0x0083
        L_0x0060:
            long[] r1 = r9.f6426f
            if (r1 == 0) goto L_0x0083
            r0 = 0
            r1 = 0
        L_0x0066:
            if (r0 != 0) goto L_0x0083
            long[] r5 = r9.f6426f
            int r6 = r5.length
            if (r1 >= r6) goto L_0x0083
            r6 = r5[r1]
            double r6 = (double) r6
            int r0 = (r3 > r6 ? 1 : (r3 == r6 ? 0 : -1))
            if (r0 < 0) goto L_0x007f
            int r0 = r1 + 1
            r6 = r5[r0]
            double r5 = (double) r6
            int r0 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r0 > 0) goto L_0x007f
            r0 = 1
            goto L_0x0080
        L_0x007f:
            r0 = 0
        L_0x0080:
            int r1 = r1 + 2
            goto L_0x0066
        L_0x0083:
            boolean r1 = r9.f6422b
            if (r1 == r0) goto L_0x0088
            return r10
        L_0x0088:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.C0091df.mo3936a(db):boolean");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:21:0x004c, code lost:
        if (r9.f6422b != false) goto L_0x0055;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0051, code lost:
        if (r9.f6422b != false) goto L_0x0055;
     */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x005c  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0066  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.String toString() {
        /*
            r9 = this;
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            int r0 = r9.f6427g
            switch(r0) {
                case 1: goto L_0x001c;
                case 2: goto L_0x0019;
                case 3: goto L_0x0016;
                case 4: goto L_0x0013;
                case 5: goto L_0x0010;
                case 6: goto L_0x000d;
                default: goto L_0x000a;
            }
        L_0x000a:
            java.lang.String r0 = "j"
            goto L_0x001e
        L_0x000d:
            java.lang.String r0 = "w"
            goto L_0x001e
        L_0x0010:
            java.lang.String r0 = "v"
            goto L_0x001e
        L_0x0013:
            java.lang.String r0 = "t"
            goto L_0x001e
        L_0x0016:
            java.lang.String r0 = "f"
            goto L_0x001e
        L_0x0019:
            java.lang.String r0 = "i"
            goto L_0x001e
        L_0x001c:
            java.lang.String r0 = "n"
        L_0x001e:
            r6.append(r0)
            int r0 = r9.f6421a
            if (r0 == 0) goto L_0x002f
            java.lang.String r0 = " % "
            r6.append(r0)
            int r0 = r9.f6421a
            r6.append(r0)
        L_0x002f:
            double r0 = r9.f6424d
            double r2 = r9.f6425e
            java.lang.String r4 = " = "
            java.lang.String r5 = " != "
            int r7 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r7 == 0) goto L_0x004f
            boolean r0 = r9.f6423c
            if (r0 != 0) goto L_0x004a
            boolean r0 = r9.f6422b
            if (r0 != 0) goto L_0x0046
            java.lang.String r4 = " not within "
            goto L_0x0055
        L_0x0046:
            java.lang.String r4 = " within "
            goto L_0x0055
        L_0x004a:
            boolean r0 = r9.f6422b
            if (r0 == 0) goto L_0x0054
            goto L_0x0053
        L_0x004f:
            boolean r0 = r9.f6422b
            if (r0 == 0) goto L_0x0054
        L_0x0053:
            goto L_0x0055
        L_0x0054:
            r4 = r5
        L_0x0055:
            r6.append(r4)
            long[] r0 = r9.f6426f
            if (r0 != 0) goto L_0x0066
            double r1 = r9.f6424d
            double r3 = r9.f6425e
            r5 = 0
            r0 = r6
            p000.C0094di.m6138a(r0, r1, r3, r5)
            goto L_0x0083
        L_0x0066:
            r7 = 0
            r8 = 0
        L_0x0068:
            long[] r0 = r9.f6426f
            int r1 = r0.length
            if (r8 >= r1) goto L_0x0083
            r1 = r0[r8]
            double r1 = (double) r1
            int r3 = r8 + 1
            r3 = r0[r3]
            double r3 = (double) r3
            if (r8 == 0) goto L_0x007a
            r0 = 1
            r5 = 1
            goto L_0x007c
        L_0x007a:
            r5 = 0
        L_0x007c:
            r0 = r6
            p000.C0094di.m6138a(r0, r1, r3, r5)
            int r8 = r8 + 2
            goto L_0x0068
        L_0x0083:
            java.lang.String r0 = r6.toString()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.C0091df.toString():java.lang.String");
    }
}
