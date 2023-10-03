package com.android.messaging.util;

import com.google.common.base.C1545t;

/* renamed from: com.android.messaging.util.O */
public final class C1411O {

    /* renamed from: bK */
    private static final C1545t f2214bK = C1545t.m4019b((CharSequence) " \t\n\r\f\u000b  ‍￯�￾￿");

    /* renamed from: cK */
    private static final C1545t f2215cK = C1545t.m4017a(0, 31).mo8550a(C1545t.m4018b(127)).mo8550a(C1545t.m4019b((CharSequence) " @,:<>")).negate();

    /* renamed from: aK */
    protected boolean f2216aK;
    protected String host;
    protected String user;
    protected boolean valid;

    /* JADX WARNING: Code restructure failed: missing block: B:60:0x0109, code lost:
        if (r8 == false) goto L_0x013a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:70:0x0136, code lost:
        if (com.google.common.base.C1545t.f2412CM.mo8555d((java.lang.CharSequence) r7.user) == false) goto L_0x013a;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public C1411O(java.lang.String r8) {
        /*
            r7 = this;
            r7.<init>()
            r0 = 0
            r7.valid = r0
            r1 = 0
            r7.user = r1
            r7.host = r1
            r7.f2216aK = r0
            r7.f2216aK = r0
            r1 = 1
            if (r8 != 0) goto L_0x0014
            goto L_0x013a
        L_0x0014:
            r2 = 64
            int r2 = r8.lastIndexOf(r2)
            if (r2 <= 0) goto L_0x013a
            int r3 = r8.length()
            int r3 = r3 - r1
            if (r2 != r3) goto L_0x0025
            goto L_0x013a
        L_0x0025:
            java.lang.String r3 = r8.substring(r0, r2)
            r7.user = r3
            int r2 = r2 + r1
            java.lang.String r8 = r8.substring(r2)
            r7.host = r8
            java.lang.String r8 = r7.user
            if (r8 == 0) goto L_0x013a
            java.lang.String r2 = r7.host
            if (r2 != 0) goto L_0x003c
            goto L_0x013a
        L_0x003c:
            int r8 = r8.length()
            if (r8 == 0) goto L_0x013a
            java.lang.String r8 = r7.host
            int r8 = r8.length()
            if (r8 != 0) goto L_0x004c
            goto L_0x013a
        L_0x004c:
            com.google.common.base.t r8 = f2214bK
            java.lang.String r2 = r7.host
            int r8 = r8.mo8554c((java.lang.CharSequence) r2)
            if (r8 < 0) goto L_0x0058
            goto L_0x013a
        L_0x0058:
            java.lang.String r8 = r7.host
            int r8 = r8.length()
            r2 = 4
            if (r8 >= r2) goto L_0x0063
            goto L_0x013a
        L_0x0063:
            java.lang.String r8 = r7.host
            r2 = 46
            int r8 = r8.indexOf(r2)
            r3 = -1
            if (r8 != r3) goto L_0x0070
            goto L_0x013a
        L_0x0070:
            java.lang.String r4 = r7.host
            java.lang.String r5 = ".."
            int r4 = r4.indexOf(r5)
            if (r4 < 0) goto L_0x007c
            goto L_0x013a
        L_0x007c:
            java.lang.String r4 = r7.host
            char r4 = r4.charAt(r0)
            if (r4 != r2) goto L_0x0086
            goto L_0x013a
        L_0x0086:
            java.lang.String r4 = r7.host
            int r8 = r8 + r1
            java.lang.String r6 = "."
            int r8 = r4.indexOf(r6, r8)
            java.lang.String r4 = r7.host
            int r6 = r4.length()
            int r6 = r6 - r1
            char r4 = r4.charAt(r6)
            if (r4 != r2) goto L_0x00a0
            if (r8 != r3) goto L_0x00a0
            goto L_0x013a
        L_0x00a0:
            com.google.common.base.t r8 = f2215cK
            java.lang.String r2 = r7.host
            boolean r8 = r8.mo8555d((java.lang.CharSequence) r2)
            if (r8 == 0) goto L_0x013a
            boolean r8 = r7.f2216aK
            if (r8 != 0) goto L_0x00ba
            com.google.common.base.t r8 = com.google.common.base.C1545t.f2412CM
            java.lang.String r2 = r7.host
            boolean r8 = r8.mo8555d((java.lang.CharSequence) r2)
            if (r8 != 0) goto L_0x00ba
            goto L_0x013a
        L_0x00ba:
            java.lang.String r8 = r7.user
            java.lang.String r2 = "\""
            boolean r8 = r8.startsWith(r2)
            if (r8 == 0) goto L_0x010c
            java.lang.String r8 = r7.user
            int r8 = r8.length()
            int r8 = r8 - r1
            if (r8 < r1) goto L_0x0108
            java.lang.String r3 = r7.user
            boolean r2 = r3.endsWith(r2)
            if (r2 != 0) goto L_0x00d6
            goto L_0x0108
        L_0x00d6:
            r2 = r1
        L_0x00d7:
            if (r2 >= r8) goto L_0x0106
            java.lang.String r3 = r7.user
            char r3 = r3.charAt(r2)
            r4 = 34
            if (r3 == r4) goto L_0x0108
            r4 = 127(0x7f, float:1.78E-43)
            if (r3 == r4) goto L_0x0108
            r4 = 32
            if (r3 >= r4) goto L_0x00f3
            com.google.common.base.t r4 = f2214bK
            boolean r4 = r4.mo8551d((char) r3)
            if (r4 == 0) goto L_0x0108
        L_0x00f3:
            r4 = 128(0x80, float:1.794E-43)
            if (r3 < r4) goto L_0x00fc
            boolean r4 = r7.f2216aK
            if (r4 != 0) goto L_0x00fc
            goto L_0x0108
        L_0x00fc:
            r4 = 92
            if (r3 != r4) goto L_0x0104
            int r2 = r2 + 1
            if (r2 >= r8) goto L_0x0108
        L_0x0104:
            int r2 = r2 + r1
            goto L_0x00d7
        L_0x0106:
            r8 = r1
            goto L_0x0109
        L_0x0108:
            r8 = r0
        L_0x0109:
            if (r8 != 0) goto L_0x0139
            goto L_0x013a
        L_0x010c:
            com.google.common.base.t r8 = f2214bK
            java.lang.String r2 = r7.user
            int r8 = r8.mo8554c((java.lang.CharSequence) r2)
            if (r8 < 0) goto L_0x0117
            goto L_0x013a
        L_0x0117:
            java.lang.String r8 = r7.user
            int r8 = r8.indexOf(r5)
            if (r8 < 0) goto L_0x0120
            goto L_0x013a
        L_0x0120:
            com.google.common.base.t r8 = f2215cK
            java.lang.String r2 = r7.user
            boolean r8 = r8.mo8555d((java.lang.CharSequence) r2)
            if (r8 == 0) goto L_0x013a
            boolean r8 = r7.f2216aK
            if (r8 != 0) goto L_0x0139
            com.google.common.base.t r8 = com.google.common.base.C1545t.f2412CM
            java.lang.String r2 = r7.user
            boolean r8 = r8.mo8555d((java.lang.CharSequence) r2)
            if (r8 != 0) goto L_0x0139
            goto L_0x013a
        L_0x0139:
            r0 = r1
        L_0x013a:
            r7.valid = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.messaging.util.C1411O.<init>(java.lang.String):void");
    }

    /* renamed from: Ga */
    public static boolean m3555Ga(String str) {
        return new C1411O(str).valid;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof C1411O) {
            return toString().equals(((C1411O) obj).toString());
        }
        return false;
    }

    public int hashCode() {
        return toString().hashCode();
    }

    public String toString() {
        return this.user + "@" + this.host;
    }
}
