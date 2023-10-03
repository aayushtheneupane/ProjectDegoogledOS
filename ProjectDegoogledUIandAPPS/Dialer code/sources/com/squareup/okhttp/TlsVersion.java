package com.squareup.okhttp;

public enum TlsVersion {
    TLS_1_2("TLSv1.2"),
    TLS_1_1("TLSv1.1"),
    TLS_1_0("TLSv1"),
    SSL_3_0("SSLv3");
    
    final String javaName;

    private TlsVersion(String str) {
        this.javaName = str;
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.squareup.okhttp.TlsVersion forJavaName(java.lang.String r4) {
        /*
            int r0 = r4.hashCode()
            r1 = 3
            r2 = 2
            r3 = 1
            switch(r0) {
                case -503070503: goto L_0x0029;
                case -503070502: goto L_0x001f;
                case 79201641: goto L_0x0015;
                case 79923350: goto L_0x000b;
                default: goto L_0x000a;
            }
        L_0x000a:
            goto L_0x0033
        L_0x000b:
            java.lang.String r0 = "TLSv1"
            boolean r0 = r4.equals(r0)
            if (r0 == 0) goto L_0x0033
            r0 = r2
            goto L_0x0034
        L_0x0015:
            java.lang.String r0 = "SSLv3"
            boolean r0 = r4.equals(r0)
            if (r0 == 0) goto L_0x0033
            r0 = r1
            goto L_0x0034
        L_0x001f:
            java.lang.String r0 = "TLSv1.2"
            boolean r0 = r4.equals(r0)
            if (r0 == 0) goto L_0x0033
            r0 = 0
            goto L_0x0034
        L_0x0029:
            java.lang.String r0 = "TLSv1.1"
            boolean r0 = r4.equals(r0)
            if (r0 == 0) goto L_0x0033
            r0 = r3
            goto L_0x0034
        L_0x0033:
            r0 = -1
        L_0x0034:
            if (r0 == 0) goto L_0x0051
            if (r0 == r3) goto L_0x004e
            if (r0 == r2) goto L_0x004b
            if (r0 != r1) goto L_0x003f
            com.squareup.okhttp.TlsVersion r4 = SSL_3_0
            return r4
        L_0x003f:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = "Unexpected TLS version: "
            java.lang.String r4 = com.android.tools.p006r8.GeneratedOutlineSupport.outline8(r1, r4)
            r0.<init>(r4)
            throw r0
        L_0x004b:
            com.squareup.okhttp.TlsVersion r4 = TLS_1_0
            return r4
        L_0x004e:
            com.squareup.okhttp.TlsVersion r4 = TLS_1_1
            return r4
        L_0x0051:
            com.squareup.okhttp.TlsVersion r4 = TLS_1_2
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.squareup.okhttp.TlsVersion.forJavaName(java.lang.String):com.squareup.okhttp.TlsVersion");
    }
}
