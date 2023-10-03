package p000;

/* renamed from: hvb */
/* compiled from: PG */
public final class hvb extends hsu {

    /* renamed from: a */
    public static final hsu f13454a = new hvb((Object) null, new Object[0], 0);
    public static final long serialVersionUID = 0;

    /* renamed from: b */
    public final transient int f13455b;

    /* renamed from: c */
    private final transient Object f13456c;

    /* renamed from: d */
    private final transient Object[] f13457d;

    /* renamed from: c */
    public final boolean mo7899c() {
        return false;
    }

    public final int size() {
        return this.f13455b;
    }

    private hvb(Object obj, Object[] objArr, int i) {
        this.f13456c = obj;
        this.f13457d = objArr;
        this.f13455b = i;
    }

    /*  JADX ERROR: JadxRuntimeException in pass: InitCodeVariables
        jadx.core.utils.exceptions.JadxRuntimeException: Several immutable types in one variable: [short[], byte[]], vars: [r2v4 ?, r2v6 ?, r2v5 ?, r2v7 ?]
        	at jadx.core.dex.visitors.InitCodeVariables.setCodeVarType(InitCodeVariables.java:102)
        	at jadx.core.dex.visitors.InitCodeVariables.setCodeVar(InitCodeVariables.java:78)
        	at jadx.core.dex.visitors.InitCodeVariables.initCodeVar(InitCodeVariables.java:69)
        	at jadx.core.dex.visitors.InitCodeVariables.initCodeVars(InitCodeVariables.java:51)
        	at jadx.core.dex.visitors.InitCodeVariables.visit(InitCodeVariables.java:32)
        */
    /* renamed from: a */
    public static p000.hvb m12214a(int r10, java.lang.Object[] r11) {
        /*
            if (r10 == 0) goto L_0x00e4
            r0 = 0
            r1 = 0
            r2 = 1
            if (r10 == r2) goto L_0x00d6
            int r3 = r11.length
            int r3 = r3 >> r2
            p000.ife.m12888c((int) r10, (int) r3)
            int r3 = p000.hto.m12128b(r10)
            if (r10 == r2) goto L_0x00c6
            int r0 = r3 + -1
            r2 = 128(0x80, float:1.794E-43)
            r4 = -1
            if (r3 > r2) goto L_0x0054
            byte[] r2 = new byte[r3]
            java.util.Arrays.fill(r2, r4)
        L_0x001e:
            if (r1 >= r10) goto L_0x0051
            int r3 = r1 + r1
            r4 = r11[r3]
            r5 = r3 ^ 1
            r5 = r11[r5]
            p000.ife.m12843a((java.lang.Object) r4, (java.lang.Object) r5)
            int r6 = r4.hashCode()
            int r6 = p000.ife.m12892d((int) r6)
        L_0x0033:
            r6 = r6 & r0
            byte r7 = r2[r6]
            r8 = 255(0xff, float:3.57E-43)
            r7 = r7 & r8
            if (r7 != r8) goto L_0x0041
            byte r3 = (byte) r3
            r2[r6] = r3
            int r1 = r1 + 1
            goto L_0x001e
        L_0x0041:
            r8 = r11[r7]
            boolean r8 = r8.equals(r4)
            if (r8 != 0) goto L_0x004c
            int r6 = r6 + 1
            goto L_0x0033
        L_0x004c:
            java.lang.IllegalArgumentException r10 = m12215a(r4, r5, r11, r7)
            throw r10
        L_0x0051:
            r0 = r2
            goto L_0x00d0
        L_0x0054:
            r2 = 32768(0x8000, float:4.5918E-41)
            if (r3 <= r2) goto L_0x008d
            int[] r2 = new int[r3]
            java.util.Arrays.fill(r2, r4)
        L_0x005e:
            if (r1 >= r10) goto L_0x0051
            int r3 = r1 + r1
            r5 = r11[r3]
            r6 = r3 ^ 1
            r6 = r11[r6]
            p000.ife.m12843a((java.lang.Object) r5, (java.lang.Object) r6)
            int r7 = r5.hashCode()
            int r7 = p000.ife.m12892d((int) r7)
        L_0x0073:
            r7 = r7 & r0
            r8 = r2[r7]
            if (r8 == r4) goto L_0x0088
            r9 = r11[r8]
            boolean r9 = r9.equals(r5)
            if (r9 != 0) goto L_0x0083
            int r7 = r7 + 1
            goto L_0x0073
        L_0x0083:
            java.lang.IllegalArgumentException r10 = m12215a(r5, r6, r11, r8)
            throw r10
        L_0x0088:
            r2[r7] = r3
            int r1 = r1 + 1
            goto L_0x005e
        L_0x008d:
            short[] r2 = new short[r3]
            java.util.Arrays.fill(r2, r4)
        L_0x0092:
            if (r1 >= r10) goto L_0x0051
            int r3 = r1 + r1
            r4 = r11[r3]
            r5 = r3 ^ 1
            r5 = r11[r5]
            p000.ife.m12843a((java.lang.Object) r4, (java.lang.Object) r5)
            int r6 = r4.hashCode()
            int r6 = p000.ife.m12892d((int) r6)
        L_0x00a7:
            r6 = r6 & r0
            short r7 = r2[r6]
            char r7 = (char) r7
            r8 = 65535(0xffff, float:9.1834E-41)
            if (r7 != r8) goto L_0x00b6
            short r3 = (short) r3
            r2[r6] = r3
            int r1 = r1 + 1
            goto L_0x0092
        L_0x00b6:
            r8 = r11[r7]
            boolean r8 = r8.equals(r4)
            if (r8 != 0) goto L_0x00c1
            int r6 = r6 + 1
            goto L_0x00a7
        L_0x00c1:
            java.lang.IllegalArgumentException r10 = m12215a(r4, r5, r11, r7)
            throw r10
        L_0x00c6:
            r1 = r11[r1]
            r2 = r11[r2]
            p000.ife.m12843a((java.lang.Object) r1, (java.lang.Object) r2)
        L_0x00d0:
            hvb r1 = new hvb
            r1.<init>(r0, r11, r10)
            return r1
        L_0x00d6:
            r10 = r11[r1]
            r1 = r11[r2]
            p000.ife.m12843a((java.lang.Object) r10, (java.lang.Object) r1)
            hvb r10 = new hvb
            r10.<init>(r0, r11, r2)
            return r10
        L_0x00e4:
            hsu r10 = f13454a
            hvb r10 = (p000.hvb) r10
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.hvb.m12214a(int, java.lang.Object[]):hvb");
    }

    /* renamed from: e */
    public final hto mo7936e() {
        return new huy(this, this.f13457d, this.f13455b);
    }

    /* renamed from: d */
    public final hto mo7935d() {
        return new huz(this, new hva(this.f13457d, 0, this.f13455b));
    }

    /* renamed from: f */
    public final hsf mo7937f() {
        return new hva(this.f13457d, 1, this.f13455b);
    }

    /* renamed from: a */
    private static IllegalArgumentException m12215a(Object obj, Object obj2, Object[] objArr, int i) {
        String valueOf = String.valueOf(obj);
        String valueOf2 = String.valueOf(obj2);
        String valueOf3 = String.valueOf(objArr[i]);
        String valueOf4 = String.valueOf(objArr[i ^ 1]);
        int length = String.valueOf(valueOf).length();
        int length2 = String.valueOf(valueOf2).length();
        StringBuilder sb = new StringBuilder(length + 39 + length2 + String.valueOf(valueOf3).length() + String.valueOf(valueOf4).length());
        sb.append("Multiple entries with same key: ");
        sb.append(valueOf);
        sb.append("=");
        sb.append(valueOf2);
        sb.append(" and ");
        sb.append(valueOf3);
        sb.append("=");
        sb.append(valueOf4);
        return new IllegalArgumentException(sb.toString());
    }

    public final Object get(Object obj) {
        Object obj2 = this.f13456c;
        Object[] objArr = this.f13457d;
        int i = this.f13455b;
        if (obj == null) {
            return null;
        }
        if (i == 1) {
            if (!objArr[0].equals(obj)) {
                return null;
            }
            return objArr[1];
        } else if (obj2 == null) {
            return null;
        } else {
            if (obj2 instanceof byte[]) {
                byte[] bArr = (byte[]) obj2;
                int length = bArr.length - 1;
                int d = ife.m12892d(obj.hashCode());
                while (true) {
                    int i2 = d & length;
                    byte b = bArr[i2] & 255;
                    if (b == 255) {
                        return null;
                    }
                    if (objArr[b].equals(obj)) {
                        return objArr[b ^ 1];
                    }
                    d = i2 + 1;
                }
            } else if (obj2 instanceof short[]) {
                short[] sArr = (short[]) obj2;
                int length2 = sArr.length - 1;
                int d2 = ife.m12892d(obj.hashCode());
                while (true) {
                    int i3 = d2 & length2;
                    char c = (char) sArr[i3];
                    if (c == 65535) {
                        return null;
                    }
                    if (objArr[c].equals(obj)) {
                        return objArr[c ^ 1];
                    }
                    d2 = i3 + 1;
                }
            } else {
                int[] iArr = (int[]) obj2;
                int length3 = iArr.length - 1;
                int d3 = ife.m12892d(obj.hashCode());
                while (true) {
                    int i4 = d3 & length3;
                    int i5 = iArr[i4];
                    if (i5 == -1) {
                        return null;
                    }
                    if (objArr[i5].equals(obj)) {
                        return objArr[i5 ^ 1];
                    }
                    d3 = i4 + 1;
                }
            }
        }
    }
}
