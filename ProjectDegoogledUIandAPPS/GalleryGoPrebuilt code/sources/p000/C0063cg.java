package p000;

import java.util.Locale;

/* renamed from: cg */
/* compiled from: PG */
public final class C0063cg {

    /* renamed from: a */
    private final String f4304a;

    /* renamed from: b */
    private final String f4305b;

    /* renamed from: c */
    private final int f4306c;

    /* renamed from: d */
    private final boolean f4307d;

    /* renamed from: e */
    private final int f4308e;

    /* renamed from: f */
    private final String f4309f;

    /* renamed from: g */
    private final int f4310g;

    public C0063cg(String str, String str2, boolean z, int i, String str3, int i2) {
        this.f4304a = str;
        this.f4305b = str2;
        this.f4307d = z;
        this.f4308e = i;
        int i3 = 5;
        if (str2 != null) {
            String upperCase = str2.toUpperCase(Locale.US);
            if (upperCase.contains("INT")) {
                i3 = 3;
            } else if (upperCase.contains("CHAR") || upperCase.contains("CLOB") || upperCase.contains("TEXT")) {
                i3 = 2;
            } else if (!upperCase.contains("BLOB")) {
                i3 = (upperCase.contains("REAL") || upperCase.contains("FLOA") || upperCase.contains("DOUB")) ? 4 : 1;
            }
        }
        this.f4306c = i3;
        this.f4309f = str3;
        this.f4310g = i2;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:30:0x0055, code lost:
        r1 = r5.f4309f;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean equals(java.lang.Object r6) {
        /*
            r5 = this;
            r0 = 1
            if (r5 == r6) goto L_0x0070
            boolean r1 = r6 instanceof p000.C0063cg
            r2 = 0
            if (r1 == 0) goto L_0x006f
            cg r6 = (p000.C0063cg) r6
            int r1 = android.os.Build.VERSION.SDK_INT
            int r1 = r5.f4308e
            int r3 = r6.f4308e
            if (r1 != r3) goto L_0x006f
            java.lang.String r1 = r5.f4304a
            java.lang.String r3 = r6.f4304a
            boolean r1 = r1.equals(r3)
            if (r1 == 0) goto L_0x006f
            boolean r1 = r5.f4307d
            boolean r3 = r6.f4307d
            if (r1 != r3) goto L_0x006f
            int r1 = r5.f4310g
            r3 = 2
            if (r1 != r0) goto L_0x0039
            int r1 = r6.f4310g
            if (r1 != r3) goto L_0x0039
            java.lang.String r1 = r5.f4309f
            if (r1 == 0) goto L_0x0039
            java.lang.String r4 = r6.f4309f
            boolean r1 = r1.equals(r4)
            if (r1 == 0) goto L_0x0038
            goto L_0x0039
        L_0x0038:
            return r2
        L_0x0039:
            int r1 = r5.f4310g
            if (r1 == r3) goto L_0x003e
            goto L_0x004f
        L_0x003e:
            int r1 = r6.f4310g
            if (r1 != r0) goto L_0x004f
            java.lang.String r1 = r6.f4309f
            if (r1 == 0) goto L_0x004f
            java.lang.String r3 = r5.f4309f
            boolean r1 = r1.equals(r3)
            if (r1 != 0) goto L_0x004f
            return r2
        L_0x004f:
            int r1 = r5.f4310g
            int r3 = r6.f4310g
            if (r1 != r3) goto L_0x0067
            java.lang.String r1 = r5.f4309f
            if (r1 == 0) goto L_0x0062
            java.lang.String r3 = r6.f4309f
            boolean r1 = r1.equals(r3)
            if (r1 == 0) goto L_0x0066
            goto L_0x0067
        L_0x0062:
            java.lang.String r1 = r6.f4309f
            if (r1 == 0) goto L_0x0067
        L_0x0066:
            return r2
        L_0x0067:
            int r1 = r5.f4306c
            int r6 = r6.f4306c
            if (r1 == r6) goto L_0x006e
            return r2
        L_0x006e:
            return r0
        L_0x006f:
            return r2
        L_0x0070:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.C0063cg.equals(java.lang.Object):boolean");
    }

    public final int hashCode() {
        return (((((this.f4304a.hashCode() * 31) + this.f4306c) * 31) + (!this.f4307d ? 1237 : 1231)) * 31) + this.f4308e;
    }

    public final String toString() {
        return "Column{name='" + this.f4304a + "', type='" + this.f4305b + "', affinity='" + this.f4306c + "', notNull=" + this.f4307d + ", primaryKeyPosition=" + this.f4308e + ", defaultValue='" + this.f4309f + "'}";
    }
}
