package p000;

/* renamed from: hyu */
/* compiled from: PG */
public abstract class hyu extends hys {

    /* renamed from: a */
    private static final String f13662a;

    /* JADX WARNING: Code restructure failed: missing block: B:3:0x000c, code lost:
        if (r0.matches("\\n|\\r(?:\\n)?") == false) goto L_0x0010;
     */
    static {
        /*
            java.lang.String r0 = "line.separator"
            java.lang.String r0 = java.lang.System.getProperty(r0)     // Catch:{ SecurityException -> 0x000f }
            java.lang.String r1 = "\\n|\\r(?:\\n)?"
            boolean r1 = r0.matches(r1)     // Catch:{ SecurityException -> 0x000f }
            if (r1 != 0) goto L_0x0012
            goto L_0x0010
        L_0x000f:
            r0 = move-exception
        L_0x0010:
            java.lang.String r0 = "\n"
        L_0x0012:
            f13662a = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.hyu.<clinit>():void");
    }

    /* renamed from: a */
    public abstract int mo8272a(hyr hyr, int i, String str, int i2, int i3, int i4);

    /* renamed from: a */
    private static int m12479a(String str, int i) {
        while (i < str.length()) {
            int i2 = i + 1;
            if (str.charAt(i) != '%') {
                i = i2;
            } else if (i2 < str.length()) {
                char charAt = str.charAt(i2);
                if (charAt != '%' && charAt != 'n') {
                    return i2 - 1;
                }
                i = i2 + 1;
            } else {
                throw hyt.m12477b("trailing unquoted '%' character", str, i2 - 1);
            }
        }
        return -1;
    }

    /* renamed from: a */
    public final void mo8275a(hyr hyr) {
        int i;
        int i2;
        int i3;
        String b = hyr.mo8274b();
        int a = m12479a(b, 0);
        int i4 = -1;
        int i5 = 0;
        while (a >= 0) {
            int i6 = a + 1;
            int i7 = i6;
            int i8 = 0;
            while (i7 < b.length()) {
                int i9 = i7 + 1;
                char charAt = b.charAt(i7);
                char c = (char) (charAt - '0');
                if (c < 10) {
                    i8 = (i8 * 10) + c;
                    if (i8 < 1000000) {
                        i7 = i9;
                    } else {
                        throw hyt.m12476a("index too large", b, a, i9);
                    }
                } else {
                    if (charAt == '$') {
                        if ((i9 - 1) - i6 == 0) {
                            throw hyt.m12476a("missing index", b, a, i9);
                        } else if (b.charAt(i6) != '0') {
                            int i10 = i8 - 1;
                            if (i9 != b.length()) {
                                b.charAt(i9);
                                i2 = i10;
                                i = i5;
                                i3 = i9;
                                i9++;
                            } else {
                                throw hyt.m12477b("unterminated parameter", b, a);
                            }
                        } else {
                            throw hyt.m12476a("index has leading zero", b, a, i9);
                        }
                    } else if (charAt != '<') {
                        i = i5 + 1;
                        i2 = i5;
                        i3 = i6;
                    } else if (i4 == -1) {
                        throw hyt.m12476a("invalid relative parameter", b, a, i9);
                    } else if (i9 != b.length()) {
                        b.charAt(i9);
                        i2 = i4;
                        i = i5;
                        i3 = i9;
                        i9++;
                    } else {
                        throw hyt.m12477b("unterminated parameter", b, a);
                    }
                    int i11 = i9 - 1;
                    while (i11 < b.length()) {
                        if (((char) ((b.charAt(i11) & 65503) - 'A')) < 26) {
                            a = m12479a(b, mo8272a(hyr, i2, b, a, i3, i11));
                            i4 = i2;
                            i5 = i;
                        } else {
                            i11++;
                        }
                    }
                    throw hyt.m12477b("unterminated parameter", b, a);
                }
            }
            throw hyt.m12477b("unterminated parameter", b, a);
        }
    }

    /* renamed from: a */
    public final void mo8276a(StringBuilder sb, String str, int i, int i2) {
        int i3 = i;
        while (i < i2) {
            int i4 = i + 1;
            if (str.charAt(i) == '%') {
                if (i4 == i2) {
                    break;
                }
                char charAt = str.charAt(i4);
                if (charAt == '%') {
                    sb.append(str, i3, i4);
                } else if (charAt == 'n') {
                    sb.append(str, i3, i4 - 1);
                    sb.append(f13662a);
                }
                i3 = i4 + 1;
                i = i3;
            }
            i = i4;
        }
        if (i3 < i2) {
            sb.append(str, i3, i2);
        }
    }
}
