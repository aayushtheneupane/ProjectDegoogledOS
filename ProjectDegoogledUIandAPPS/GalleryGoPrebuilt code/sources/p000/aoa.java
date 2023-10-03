package p000;

import java.util.GregorianCalendar;

/* renamed from: aoa */
/* compiled from: PG */
public final class aoa implements ani {

    /* renamed from: a */
    public final aod f1242a;

    public aoa() {
        this.f1242a = new aod((String) null, (String) null, (aop) null);
    }

    private aoa(aod aod) {
        this.f1242a = aod;
    }

    public final Object clone() {
        return new aoa((aod) this.f1242a.clone());
    }

    /* renamed from: a */
    public final int mo1257a(String str, String str2) {
        ckx.m4485d(str);
        ckx.m4483b(str2);
        aod a = C0637xj.m15894a(this.f1242a, C0643xp.m15938a(str, str2), false, (aop) null);
        if (a == null) {
            return 0;
        }
        if (a.mo1328i().mo1375f()) {
            return a.mo1318c();
        }
        throw new ang("The named property is not an array", 102);
    }

    /* renamed from: b */
    public final void mo1262b(String str, String str2) {
        try {
            ckx.m4485d(str);
            ckx.m4484c(str2);
            aod a = C0637xj.m15894a(this.f1242a, C0643xp.m15938a(str, str2), false, (aop) null);
            if (a != null) {
                C0637xj.m15900a(a);
            }
        } catch (ang e) {
        }
    }

    /* renamed from: c */
    public final boolean mo1264c(String str, String str2) {
        try {
            ckx.m4485d(str);
            ckx.m4484c(str2);
            if (C0637xj.m15894a(this.f1242a, C0643xp.m15938a(str, str2), false, (aop) null) != null) {
                return true;
            }
            return false;
        } catch (ang e) {
            return false;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:49:0x009c, code lost:
        if (java.lang.Integer.parseInt(r8) == 0) goto L_0x009e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x009e, code lost:
        r3 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x00c1, code lost:
        if ("yes".equals(r8) == false) goto L_0x009e;
     */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static final java.lang.Object m1219a(int r7, p000.aod r8) {
        /*
            java.lang.String r0 = r8.f1247b
            java.lang.String r1 = "Empty convert-string"
            r2 = 5
            r3 = 1
            if (r7 == r3) goto L_0x0089
            r3 = 16
            java.lang.String r4 = "0x"
            r5 = 2
            if (r7 == r5) goto L_0x0057
            r6 = 3
            if (r7 == r6) goto L_0x0023
            if (r0 != 0) goto L_0x00c9
            aop r7 = r8.mo1328i()
            boolean r7 = r7.mo1380k()
            if (r7 == 0) goto L_0x0020
            goto L_0x00c9
        L_0x0020:
            java.lang.String r7 = ""
            return r7
        L_0x0023:
            java.lang.Long r7 = new java.lang.Long
            if (r0 == 0) goto L_0x0048
            int r8 = r0.length()     // Catch:{ NumberFormatException -> 0x004e }
            if (r8 == 0) goto L_0x0048
            boolean r8 = r0.startsWith(r4)     // Catch:{ NumberFormatException -> 0x004e }
            if (r8 == 0) goto L_0x003c
            java.lang.String r8 = r0.substring(r5)     // Catch:{ NumberFormatException -> 0x004e }
            long r0 = java.lang.Long.parseLong(r8, r3)     // Catch:{ NumberFormatException -> 0x004e }
            goto L_0x0042
        L_0x003c:
            long r0 = java.lang.Long.parseLong(r0)     // Catch:{ NumberFormatException -> 0x004e }
        L_0x0042:
            r7.<init>(r0)
            r0 = r7
            goto L_0x00c9
        L_0x0048:
            ang r7 = new ang     // Catch:{ NumberFormatException -> 0x004e }
            r7.<init>(r1, r2)     // Catch:{ NumberFormatException -> 0x004e }
            throw r7     // Catch:{ NumberFormatException -> 0x004e }
        L_0x004e:
            r7 = move-exception
            ang r7 = new ang
            java.lang.String r8 = "Invalid long string"
            r7.<init>(r8, r2)
            throw r7
        L_0x0057:
            java.lang.Integer r7 = new java.lang.Integer
            if (r0 == 0) goto L_0x007a
            int r8 = r0.length()     // Catch:{ NumberFormatException -> 0x0080 }
            if (r8 == 0) goto L_0x007a
            boolean r8 = r0.startsWith(r4)     // Catch:{ NumberFormatException -> 0x0080 }
            if (r8 == 0) goto L_0x0070
            java.lang.String r8 = r0.substring(r5)     // Catch:{ NumberFormatException -> 0x0080 }
            int r8 = java.lang.Integer.parseInt(r8, r3)     // Catch:{ NumberFormatException -> 0x0080 }
            goto L_0x0074
        L_0x0070:
            int r8 = java.lang.Integer.parseInt(r0)     // Catch:{ NumberFormatException -> 0x0080 }
        L_0x0074:
            r7.<init>(r8)
            r0 = r7
            goto L_0x00c9
        L_0x007a:
            ang r7 = new ang     // Catch:{ NumberFormatException -> 0x0080 }
            r7.<init>(r1, r2)     // Catch:{ NumberFormatException -> 0x0080 }
            throw r7     // Catch:{ NumberFormatException -> 0x0080 }
        L_0x0080:
            r7 = move-exception
            ang r7 = new ang
            java.lang.String r8 = "Invalid integer string"
            r7.<init>(r8, r2)
            throw r7
        L_0x0089:
            java.lang.Boolean r7 = new java.lang.Boolean
            if (r0 == 0) goto L_0x00ca
            int r8 = r0.length()
            if (r8 == 0) goto L_0x00ca
            java.lang.String r8 = r0.toLowerCase()
            r0 = 0
            int r8 = java.lang.Integer.parseInt(r8)     // Catch:{ NumberFormatException -> 0x00a2 }
            if (r8 != 0) goto L_0x00a0
        L_0x009e:
            r3 = 0
            goto L_0x00c4
        L_0x00a0:
        L_0x00a1:
            goto L_0x00c4
        L_0x00a2:
            r1 = move-exception
            java.lang.String r1 = "true"
            boolean r1 = r1.equals(r8)
            if (r1 != 0) goto L_0x00a1
            java.lang.String r1 = "t"
            boolean r1 = r1.equals(r8)
            if (r1 != 0) goto L_0x00a1
            java.lang.String r1 = "on"
            boolean r1 = r1.equals(r8)
            if (r1 != 0) goto L_0x00a1
            java.lang.String r1 = "yes"
            boolean r8 = r1.equals(r8)
            if (r8 != 0) goto L_0x00a1
            goto L_0x009e
        L_0x00c4:
            r7.<init>(r3)
            r0 = r7
        L_0x00c9:
            return r0
        L_0x00ca:
            ang r7 = new ang
            r7.<init>(r1, r2)
            goto L_0x00d1
        L_0x00d0:
            throw r7
        L_0x00d1:
            goto L_0x00d0
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.aoa.m1219a(int, aod):java.lang.Object");
    }

    /* renamed from: a */
    public final aos mo1260a(String str, String str2, int i) {
        String str3;
        ckx.m4485d(str);
        ckx.m4483b(str2);
        if (i > 0) {
            StringBuilder sb = new StringBuilder(str2.length() + 13);
            sb.append(str2);
            sb.append('[');
            sb.append(i);
            sb.append(']');
            str3 = sb.toString();
        } else if (i == -1) {
            str3 = str2.concat("[last()]");
        } else {
            throw new ang("Array index must be larger than zero", 104);
        }
        ckx.m4485d(str);
        ckx.m4484c(str3);
        aod a = C0637xj.m15894a(this.f1242a, C0643xp.m15938a(str, str3), false, (aop) null);
        if (a != null) {
            return new anz(m1219a(0, a));
        }
        return null;
    }

    /* renamed from: d */
    public final Integer mo1265d(String str, String str2) {
        return (Integer) m1221c(str, str2, 2);
    }

    /* renamed from: e */
    public final Long mo1266e(String str, String str2) {
        return (Long) m1221c(str, str2, 3);
    }

    /* renamed from: c */
    private final Object m1221c(String str, String str2, int i) {
        ckx.m4485d(str);
        ckx.m4484c(str2);
        aod a = C0637xj.m15894a(this.f1242a, C0643xp.m15938a(str, str2), false, (aop) null);
        if (a == null) {
            return null;
        }
        if (i == 0 || !a.mo1328i().mo1380k()) {
            return m1219a(i, a);
        }
        throw new ang("Property must be simple when a value type is requested", 102);
    }

    /* renamed from: f */
    public final String mo1267f(String str, String str2) {
        return (String) m1221c(str, str2, 0);
    }

    /* renamed from: a */
    public final anh mo1258a(aom aom) {
        return mo1259a((String) null, (String) null, aom);
    }

    /* renamed from: a */
    public final anh mo1259a(String str, String str2, aom aom) {
        return new any(this, str, str2, aom);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:100:0x01e4, code lost:
        if (r1.hasNext() == false) goto L_0x0200;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:101:0x01e6, code lost:
        r2 = (p000.aod) r1.next();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:102:0x01ec, code lost:
        if (r2 == r9) goto L_0x01e0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:103:0x01ee, code lost:
        r3 = r2.f1247b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:104:0x01f0, code lost:
        if (r9 == null) goto L_0x01f5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:105:0x01f2, code lost:
        r6 = r9.f1247b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:106:0x01f5, code lost:
        r6 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:108:0x01fb, code lost:
        if (r3.equals(r6) == false) goto L_0x01e0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:109:0x01fd, code lost:
        r2.f1247b = r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:110:0x0200, code lost:
        if (r9 == null) goto L_0x021a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:111:0x0202, code lost:
        r9.f1247b = r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:112:0x0205, code lost:
        if (r7 != false) goto L_0x0208;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:113:0x0208, code lost:
        if (r9 == r1) goto L_0x0218;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:114:0x020a, code lost:
        if (r9 == null) goto L_0x0218;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:116:0x0214, code lost:
        if (r9.f1247b.equals(r1.f1247b) == false) goto L_0x0218;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:117:0x0216, code lost:
        r9.f1247b = r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:118:0x0218, code lost:
        r1.f1247b = r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:119:0x021a, code lost:
        if (r7 != false) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:121:0x0220, code lost:
        if (r4.mo1318c() != 1) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:122:0x0222, code lost:
        p000.C0637xj.m15901a(r4, "x-default", r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:123:0x0225, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:124:0x0226, code lost:
        p000.C0637xj.m15901a(r4, "x-default", r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:125:0x0229, code lost:
        if (r6 != false) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:126:0x022b, code lost:
        p000.C0637xj.m15901a(r4, r3, r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:128:0x0238, code lost:
        throw new p000.ang("Localized text array is not alt-text", 102);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:150:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:151:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:152:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:153:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:154:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x009a, code lost:
        if (r9 == null) goto L_0x00a8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x00a0, code lost:
        if (r4.mo1318c() <= 1) goto L_0x00a8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x00a2, code lost:
        r4.mo1317b(r9);
        r4.mo1312a(1, r9);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x00b0, code lost:
        if (r4.mo1328i().mo1378i() == false) goto L_0x022f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x00b7, code lost:
        if (r4.mo1324e() != false) goto L_0x00c6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x00b9, code lost:
        r1 = new java.lang.Object[]{new java.lang.Integer(0), null};
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x00c6, code lost:
        r14 = r4.mo1325f();
        r16 = null;
        r17 = null;
        r12 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x00d3, code lost:
        if (r14.hasNext() == false) goto L_0x0144;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x00d5, code lost:
        r2 = (p000.aod) r14.next();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x00e5, code lost:
        if (r2.mo1328i().mo1380k() != false) goto L_0x013a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x00eb, code lost:
        if (r2.mo1326g() == false) goto L_0x0130;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x00f7, code lost:
        if ("xml:lang".equals(r2.mo1314b(1).f1246a) == false) goto L_0x0130;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x00f9, code lost:
        r6 = r2.mo1314b(1).f1247b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x0104, code lost:
        if (r3.equals(r6) == false) goto L_0x0113;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x0106, code lost:
        r1 = new java.lang.Object[]{new java.lang.Integer(1), r2};
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x0113, code lost:
        if (r1 != null) goto L_0x0116;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x011a, code lost:
        if (r6.startsWith(r1) == false) goto L_0x0123;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x011c, code lost:
        if (r16 != null) goto L_0x0120;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x011e, code lost:
        r16 = r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x0120, code lost:
        r12 = r12 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x0128, code lost:
        if ("x-default".equals(r6) == false) goto L_0x012c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x012a, code lost:
        r17 = r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x0139, code lost:
        throw new p000.ang("Alt-text array item has no language qualifier", 102);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x0143, code lost:
        throw new p000.ang("Alt-text array item is not simple", 102);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x0144, code lost:
        if (r12 != 1) goto L_0x0152;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x0146, code lost:
        r1 = new java.lang.Object[]{new java.lang.Integer(2), r16};
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:0x0152, code lost:
        if (r12 <= 1) goto L_0x0161;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:0x0154, code lost:
        r1 = new java.lang.Object[]{new java.lang.Integer(3), r16};
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x0161, code lost:
        if (r17 != null) goto L_0x0174;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:0x0163, code lost:
        r1 = new java.lang.Object[]{new java.lang.Integer(5), r4.mo1309a(1)};
     */
    /* JADX WARNING: Code restructure failed: missing block: B:67:0x0174, code lost:
        r1 = new java.lang.Object[]{new java.lang.Integer(4), r17};
     */
    /* JADX WARNING: Code restructure failed: missing block: B:68:0x0180, code lost:
        r2 = ((java.lang.Integer) r1[0]).intValue();
        r1 = (p000.aod) r1[1];
        r6 = "x-default".equals(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:69:0x0190, code lost:
        if (r2 == 0) goto L_0x0226;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:70:0x0192, code lost:
        if (r2 == 1) goto L_0x01da;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:71:0x0194, code lost:
        if (r2 == 2) goto L_0x01c4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:73:0x0197, code lost:
        if (r2 == 3) goto L_0x01be;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:75:0x019a, code lost:
        if (r2 == 4) goto L_0x01b0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:77:0x019d, code lost:
        if (r2 != 5) goto L_0x01a6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:78:0x019f, code lost:
        p000.C0637xj.m15901a(r4, r3, r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:79:0x01a2, code lost:
        if (r6 != false) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:81:0x01af, code lost:
        throw new p000.ang("Unexpected result from ChooseLocalizedText", 9);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:82:0x01b0, code lost:
        if (r9 == null) goto L_0x01ba;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:84:0x01b6, code lost:
        if (r4.mo1318c() != 1) goto L_0x01ba;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:85:0x01b8, code lost:
        r9.f1247b = r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:86:0x01ba, code lost:
        p000.C0637xj.m15901a(r4, r3, r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:87:0x01be, code lost:
        p000.C0637xj.m15901a(r4, r3, r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:88:0x01c1, code lost:
        if (r6 == false) goto L_0x021a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:89:0x01c3, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:90:0x01c4, code lost:
        if (r7 != false) goto L_0x01c7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:91:0x01c7, code lost:
        if (r9 == r1) goto L_0x01d7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:92:0x01c9, code lost:
        if (r9 == null) goto L_0x01d7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:94:0x01d3, code lost:
        if (r9.f1247b.equals(r1.f1247b) == false) goto L_0x01d7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:95:0x01d5, code lost:
        r9.f1247b = r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:96:0x01d7, code lost:
        r1.f1247b = r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:97:0x01da, code lost:
        if (r6 == false) goto L_0x0205;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:98:0x01dc, code lost:
        r1 = r4.mo1325f();
     */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void mo1261a(java.lang.String r20, java.lang.String r21, java.lang.String r22, java.lang.String r23, java.lang.String r24) {
        /*
            r19 = this;
            r0 = r24
            p000.ckx.m4485d(r20)
            p000.ckx.m4483b(r21)
            int r1 = r23.length()
            if (r1 == 0) goto L_0x0243
            java.lang.String r1 = p000.ant.m1191a((java.lang.String) r22)
            java.lang.String r3 = p000.ant.m1191a((java.lang.String) r23)
            aoj r4 = p000.C0643xp.m15938a((java.lang.String) r20, (java.lang.String) r21)
            r5 = r19
            aod r6 = r5.f1242a
            aop r7 = new aop
            r8 = 7680(0x1e00, float:1.0762E-41)
            r7.<init>(r8)
            r8 = 1
            aod r4 = p000.C0637xj.m15894a((p000.aod) r6, (p000.aoj) r4, (boolean) r8, (p000.aop) r7)
            r6 = 102(0x66, float:1.43E-43)
            if (r4 == 0) goto L_0x0239
            aop r7 = r4.mo1328i()
            boolean r7 = r7.mo1378i()
            if (r7 != 0) goto L_0x0058
            boolean r7 = r4.mo1324e()
            if (r7 != 0) goto L_0x0050
            aop r7 = r4.mo1328i()
            boolean r7 = r7.mo1377h()
            if (r7 == 0) goto L_0x0050
            aop r7 = r4.mo1328i()
            r7.mo1382m()
            goto L_0x0058
        L_0x0050:
            ang r0 = new ang
            java.lang.String r1 = "Specified property is no alt-text array"
            r0.<init>(r1, r6)
            throw r0
        L_0x0058:
            java.util.Iterator r7 = r4.mo1325f()
        L_0x005c:
            boolean r9 = r7.hasNext()
            java.lang.String r10 = "xml:lang"
            java.lang.String r11 = "x-default"
            r12 = 0
            r13 = 0
            if (r9 == 0) goto L_0x0097
            java.lang.Object r9 = r7.next()
            aod r9 = (p000.aod) r9
            boolean r14 = r9.mo1326g()
            if (r14 == 0) goto L_0x008f
            aod r14 = r9.mo1314b((int) r8)
            java.lang.String r14 = r14.f1246a
            boolean r14 = r10.equals(r14)
            if (r14 == 0) goto L_0x008f
            aod r14 = r9.mo1314b((int) r8)
            java.lang.String r14 = r14.f1247b
            boolean r14 = r11.equals(r14)
            if (r14 == 0) goto L_0x005c
            r7 = 1
            goto L_0x009a
        L_0x008f:
            ang r0 = new ang
            java.lang.String r1 = "Language qualifier must be first"
            r0.<init>(r1, r6)
            throw r0
        L_0x0097:
            r9 = r12
            r7 = 0
        L_0x009a:
            if (r9 == 0) goto L_0x00a8
            int r14 = r4.mo1318c()
            if (r14 <= r8) goto L_0x00a8
            r4.mo1317b((p000.aod) r9)
            r4.mo1312a((int) r8, (p000.aod) r9)
        L_0x00a8:
            aop r14 = r4.mo1328i()
            boolean r14 = r14.mo1378i()
            if (r14 == 0) goto L_0x022f
            boolean r14 = r4.mo1324e()
            r15 = 2
            if (r14 != 0) goto L_0x00c6
            java.lang.Object[] r1 = new java.lang.Object[r15]
            java.lang.Integer r6 = new java.lang.Integer
            r6.<init>(r13)
            r1[r13] = r6
            r1[r8] = r12
            goto L_0x0180
        L_0x00c6:
            java.util.Iterator r14 = r4.mo1325f()
            r16 = r12
            r17 = r16
            r12 = 0
        L_0x00cf:
            boolean r18 = r14.hasNext()
            if (r18 == 0) goto L_0x0144
            java.lang.Object r18 = r14.next()
            r2 = r18
            aod r2 = (p000.aod) r2
            aop r18 = r2.mo1328i()
            boolean r18 = r18.mo1380k()
            if (r18 != 0) goto L_0x013a
            boolean r18 = r2.mo1326g()
            if (r18 == 0) goto L_0x0130
            aod r6 = r2.mo1314b((int) r8)
            java.lang.String r6 = r6.f1246a
            boolean r6 = r10.equals(r6)
            if (r6 == 0) goto L_0x0130
            aod r6 = r2.mo1314b((int) r8)
            java.lang.String r6 = r6.f1247b
            boolean r18 = r3.equals(r6)
            if (r18 == 0) goto L_0x0113
            java.lang.Object[] r1 = new java.lang.Object[r15]
            java.lang.Integer r6 = new java.lang.Integer
            r6.<init>(r8)
            r1[r13] = r6
            r1[r8] = r2
            goto L_0x0180
        L_0x0113:
            if (r1 != 0) goto L_0x0116
            goto L_0x0123
        L_0x0116:
            boolean r18 = r6.startsWith(r1)
            if (r18 == 0) goto L_0x0123
            if (r16 != 0) goto L_0x0120
            r16 = r2
        L_0x0120:
            int r12 = r12 + 1
            goto L_0x012c
        L_0x0123:
            boolean r6 = r11.equals(r6)
            if (r6 == 0) goto L_0x012c
            r17 = r2
        L_0x012c:
            r6 = 102(0x66, float:1.43E-43)
            goto L_0x00cf
        L_0x0130:
            ang r0 = new ang
            java.lang.String r1 = "Alt-text array item has no language qualifier"
            r2 = 102(0x66, float:1.43E-43)
            r0.<init>(r1, r2)
            throw r0
        L_0x013a:
            r2 = 102(0x66, float:1.43E-43)
            ang r0 = new ang
            java.lang.String r1 = "Alt-text array item is not simple"
            r0.<init>(r1, r2)
            throw r0
        L_0x0144:
            if (r12 != r8) goto L_0x0152
            java.lang.Object[] r1 = new java.lang.Object[r15]
            java.lang.Integer r2 = new java.lang.Integer
            r2.<init>(r15)
            r1[r13] = r2
            r1[r8] = r16
            goto L_0x0180
        L_0x0152:
            if (r12 <= r8) goto L_0x0161
            java.lang.Object[] r1 = new java.lang.Object[r15]
            java.lang.Integer r2 = new java.lang.Integer
            r6 = 3
            r2.<init>(r6)
            r1[r13] = r2
            r1[r8] = r16
            goto L_0x0180
        L_0x0161:
            if (r17 != 0) goto L_0x0174
            java.lang.Object[] r1 = new java.lang.Object[r15]
            java.lang.Integer r2 = new java.lang.Integer
            r6 = 5
            r2.<init>(r6)
            r1[r13] = r2
            aod r2 = r4.mo1309a((int) r8)
            r1[r8] = r2
            goto L_0x0180
        L_0x0174:
            java.lang.Object[] r1 = new java.lang.Object[r15]
            java.lang.Integer r2 = new java.lang.Integer
            r6 = 4
            r2.<init>(r6)
            r1[r13] = r2
            r1[r8] = r17
        L_0x0180:
            r2 = r1[r13]
            java.lang.Integer r2 = (java.lang.Integer) r2
            int r2 = r2.intValue()
            r1 = r1[r8]
            aod r1 = (p000.aod) r1
            boolean r6 = r11.equals(r3)
            if (r2 == 0) goto L_0x0226
            if (r2 == r8) goto L_0x01da
            if (r2 == r15) goto L_0x01c4
            r10 = 3
            if (r2 == r10) goto L_0x01be
            r1 = 4
            if (r2 == r1) goto L_0x01b0
            r1 = 5
            if (r2 != r1) goto L_0x01a6
            p000.C0637xj.m15901a((p000.aod) r4, (java.lang.String) r3, (java.lang.String) r0)
            if (r6 != 0) goto L_0x022e
            goto L_0x021a
        L_0x01a6:
            ang r0 = new ang
            r1 = 9
            java.lang.String r2 = "Unexpected result from ChooseLocalizedText"
            r0.<init>(r2, r1)
            throw r0
        L_0x01b0:
            if (r9 == 0) goto L_0x01ba
            int r1 = r4.mo1318c()
            if (r1 != r8) goto L_0x01ba
            r9.f1247b = r0
        L_0x01ba:
            p000.C0637xj.m15901a((p000.aod) r4, (java.lang.String) r3, (java.lang.String) r0)
            goto L_0x021a
        L_0x01be:
            p000.C0637xj.m15901a((p000.aod) r4, (java.lang.String) r3, (java.lang.String) r0)
            if (r6 == 0) goto L_0x021a
            return
        L_0x01c4:
            if (r7 != 0) goto L_0x01c7
            goto L_0x01d7
        L_0x01c7:
            if (r9 == r1) goto L_0x01d7
            if (r9 == 0) goto L_0x01d7
            java.lang.String r2 = r9.f1247b
            java.lang.String r3 = r1.f1247b
            boolean r2 = r2.equals(r3)
            if (r2 == 0) goto L_0x01d7
            r9.f1247b = r0
        L_0x01d7:
            r1.f1247b = r0
            goto L_0x021a
        L_0x01da:
            if (r6 == 0) goto L_0x0205
            java.util.Iterator r1 = r4.mo1325f()
        L_0x01e0:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto L_0x0200
            java.lang.Object r2 = r1.next()
            aod r2 = (p000.aod) r2
            if (r2 == r9) goto L_0x01e0
            java.lang.String r3 = r2.f1247b
            if (r9 == 0) goto L_0x01f5
            java.lang.String r6 = r9.f1247b
            goto L_0x01f7
        L_0x01f5:
            r6 = 0
        L_0x01f7:
            boolean r3 = r3.equals(r6)
            if (r3 == 0) goto L_0x01e0
            r2.f1247b = r0
            goto L_0x01e0
        L_0x0200:
            if (r9 == 0) goto L_0x021a
            r9.f1247b = r0
            goto L_0x021a
        L_0x0205:
            if (r7 != 0) goto L_0x0208
        L_0x0207:
            goto L_0x0218
        L_0x0208:
            if (r9 == r1) goto L_0x0207
            if (r9 == 0) goto L_0x0207
            java.lang.String r2 = r9.f1247b
            java.lang.String r3 = r1.f1247b
            boolean r2 = r2.equals(r3)
            if (r2 == 0) goto L_0x0207
            r9.f1247b = r0
        L_0x0218:
            r1.f1247b = r0
        L_0x021a:
            if (r7 != 0) goto L_0x022e
            int r1 = r4.mo1318c()
            if (r1 != r8) goto L_0x022e
            p000.C0637xj.m15901a((p000.aod) r4, (java.lang.String) r11, (java.lang.String) r0)
            return
        L_0x0226:
            p000.C0637xj.m15901a((p000.aod) r4, (java.lang.String) r11, (java.lang.String) r0)
            if (r6 != 0) goto L_0x022e
            p000.C0637xj.m15901a((p000.aod) r4, (java.lang.String) r3, (java.lang.String) r0)
        L_0x022e:
            return
        L_0x022f:
            ang r0 = new ang
            java.lang.String r1 = "Localized text array is not alt-text"
            r2 = 102(0x66, float:1.43E-43)
            r0.<init>(r1, r2)
            throw r0
        L_0x0239:
            r2 = 102(0x66, float:1.43E-43)
            ang r0 = new ang
            java.lang.String r1 = "Failed to find or create array node"
            r0.<init>(r1, r2)
            throw r0
        L_0x0243:
            r5 = r19
            ang r0 = new ang
            java.lang.String r1 = "Empty specific language"
            r2 = 4
            r0.<init>(r1, r2)
            goto L_0x024f
        L_0x024e:
            throw r0
        L_0x024f:
            goto L_0x024e
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.aoa.mo1261a(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String):void");
    }

    /* renamed from: a */
    public static final void m1220a(aod aod, Object obj, aop aop) {
        String str;
        aod.mo1328i().mo1366a(aop);
        if (!aod.mo1328i().mo1380k()) {
            String str2 = null;
            if (obj == null) {
                str = null;
            } else if (obj instanceof Boolean) {
                str = !((Boolean) obj).booleanValue() ? "False" : "True";
            } else if (obj instanceof Integer) {
                str = String.valueOf(((Integer) obj).intValue());
            } else if (obj instanceof Long) {
                str = String.valueOf(((Long) obj).longValue());
            } else if (obj instanceof Double) {
                str = String.valueOf(((Double) obj).doubleValue());
            } else if (obj instanceof ane) {
                str = cdu.m4145a((ane) obj);
            } else {
                str = obj instanceof GregorianCalendar ? cdu.m4145a(anf.m1157a((GregorianCalendar) obj)) : obj instanceof byte[] ? new String(ann.m1179a((byte[]) obj)) : obj.toString();
            }
            if (str != null) {
                str2 = ant.m1200f(str);
            }
            if (!aod.mo1328i().mo1370b() || !"xml:lang".equals(aod.f1246a)) {
                aod.f1247b = str2;
            } else {
                aod.f1247b = ant.m1191a(str2);
            }
        } else if (obj == null || obj.toString().length() <= 0) {
            aod.mo1316b();
        } else {
            throw new ang("Composite nodes can't have values", 102);
        }
    }

    /* renamed from: a */
    public final void mo1304a(String str, String str2, Object obj) {
        ckx.m4485d(str);
        ckx.m4484c(str2);
        aop a = C0637xj.m15897a((aop) null, obj);
        aod a2 = C0637xj.m15894a(this.f1242a, C0643xp.m15938a(str, str2), true, a);
        if (a2 != null) {
            m1220a(a2, obj, a);
            return;
        }
        throw new ang("Specified property does not exist", 102);
    }

    /* renamed from: b */
    public final void mo1263b(String str, String str2, int i) {
        mo1304a(str, str2, (Object) new Integer(i));
    }
}
