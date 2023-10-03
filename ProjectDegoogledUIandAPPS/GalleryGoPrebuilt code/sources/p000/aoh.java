package p000;

import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/* renamed from: aoh */
/* compiled from: PG */
public final class aoh {

    /* renamed from: g */
    private static final Set f1265g = new HashSet(Arrays.asList(new String[]{"xml:lang", "rdf:resource", "rdf:ID", "rdf:bagID", "rdf:nodeID"}));

    /* renamed from: a */
    public aoa f1266a;

    /* renamed from: b */
    public anp f1267b;

    /* renamed from: c */
    public OutputStreamWriter f1268c;

    /* renamed from: d */
    public aoq f1269d;

    /* renamed from: e */
    public int f1270e = 1;

    /* renamed from: f */
    public int f1271f;

    /* renamed from: a */
    private final void m1276a(String str, boolean z) {
        mo1340a(ant.m1192a(str, z));
    }

    /* renamed from: a */
    private static final boolean m1277a(aod aod) {
        return !aod.mo1326g() && !aod.mo1328i().mo1368a() && !aod.mo1328i().mo1380k() && !"[]".equals(aod.f1246a);
    }

    /* renamed from: a */
    private final void m1275a(String str, String str2, Set set) {
        if (str2 == null) {
            ans ans = new ans(str);
            String str3 = ans.f1209a;
            if (str3 != null && str3.length() > 0) {
                str = ans.f1209a;
                str2 = ank.f1195a.mo1272b(String.valueOf(str).concat(":"));
                m1275a(str, str2, set);
            } else {
                return;
            }
        }
        if (!set.contains(str)) {
            mo1342b();
            mo1337a(4);
            mo1340a("xmlns:");
            mo1340a(str);
            mo1340a("=\"");
            mo1340a(str2);
            mo1343b(34);
            set.add(str);
        }
    }

    /* renamed from: a */
    public final void mo1338a(aod aod, Set set) {
        if (aod.mo1328i().mo1379j()) {
            String str = aod.f1247b;
            m1275a(str.substring(0, str.length() - 1), aod.f1246a, set);
        } else if (aod.mo1328i().mo1374e()) {
            Iterator f = aod.mo1325f();
            while (f.hasNext()) {
                m1275a(((aod) f.next()).f1246a, (String) null, set);
            }
        }
        Iterator f2 = aod.mo1325f();
        while (f2.hasNext()) {
            mo1338a((aod) f2.next(), set);
        }
        Iterator h = aod.mo1327h();
        while (h.hasNext()) {
            aod aod2 = (aod) h.next();
            m1275a(aod2.f1246a, (String) null, set);
            mo1338a(aod2, set);
        }
    }

    /* renamed from: b */
    private final void m1278b(aod aod, boolean z, int i) {
        String str;
        if (z || aod.mo1324e()) {
            mo1337a(i);
            if (!z) {
                str = "</rdf:";
            } else {
                str = "<rdf:";
            }
            mo1340a(str);
            if (aod.mo1328i().mo1377h()) {
                mo1340a("Alt");
            } else if (aod.mo1328i().mo1376g()) {
                mo1340a("Seq");
            } else {
                mo1340a("Bag");
            }
            if (z && !aod.mo1324e()) {
                mo1340a("/>");
            } else {
                mo1340a(">");
            }
            mo1342b();
        }
    }

    /* renamed from: a */
    public final boolean mo1341a(aod aod, int i) {
        Iterator f = aod.mo1325f();
        boolean z = true;
        while (f.hasNext()) {
            aod aod2 = (aod) f.next();
            if (m1277a(aod2)) {
                mo1342b();
                mo1337a(i);
                mo1340a(aod2.f1246a);
                mo1340a("=\"");
                m1276a(aod2.f1247b, true);
                mo1343b(34);
            } else {
                z = false;
            }
        }
        return z;
    }

    /* renamed from: b */
    public final void mo1344b(aod aod, int i) {
        boolean z;
        boolean z2;
        Iterator f = aod.mo1325f();
        while (f.hasNext()) {
            aod aod2 = (aod) f.next();
            if (!m1277a(aod2)) {
                String str = aod2.f1246a;
                if ("[]".equals(str)) {
                    str = "rdf:li";
                }
                mo1337a(i);
                mo1343b(60);
                mo1340a(str);
                Iterator h = aod2.mo1327h();
                boolean z3 = false;
                boolean z4 = false;
                boolean z5 = false;
                while (true) {
                    z = true;
                    if (!h.hasNext()) {
                        break;
                    }
                    aod aod3 = (aod) h.next();
                    if (f1265g.contains(aod3.f1246a)) {
                        z5 = "rdf:resource".equals(aod3.f1246a);
                        mo1343b(32);
                        mo1340a(aod3.f1246a);
                        mo1340a("=\"");
                        m1276a(aod3.f1247b, true);
                        mo1343b(34);
                    } else {
                        z4 = true;
                    }
                }
                if (z4) {
                    mo1340a(" rdf:parseType=\"Resource\">");
                    mo1342b();
                    int i2 = i + 1;
                    mo1339a(aod2, true, i2);
                    Iterator h2 = aod2.mo1327h();
                    while (h2.hasNext()) {
                        mo1339a((aod) h2.next(), false, i2);
                    }
                    z2 = true;
                } else if (!aod2.mo1328i().mo1380k()) {
                    Boolean bool = Boolean.TRUE;
                    Boolean bool2 = Boolean.TRUE;
                    if (aod2.mo1328i().mo1368a()) {
                        mo1340a(" rdf:resource=\"");
                        m1276a(aod2.f1247b, true);
                        mo1340a("\"/>");
                        mo1342b();
                        bool = Boolean.FALSE;
                    } else {
                        String str2 = aod2.f1247b;
                        if (str2 == null || str2.length() == 0) {
                            mo1340a("/>");
                            mo1342b();
                            bool = Boolean.FALSE;
                        } else {
                            mo1343b(62);
                            m1276a(aod2.f1247b, false);
                            bool2 = Boolean.FALSE;
                        }
                    }
                    Object[] objArr = {bool, bool2};
                    boolean booleanValue = ((Boolean) objArr[0]).booleanValue();
                    z2 = ((Boolean) objArr[1]).booleanValue();
                    z = booleanValue;
                } else if (aod2.mo1328i().mo1375f()) {
                    mo1343b(62);
                    mo1342b();
                    int i3 = i + 1;
                    m1278b(aod2, true, i3);
                    if (aod2.mo1328i().mo1378i()) {
                        C0637xj.m15909b(aod2);
                    }
                    mo1344b(aod2, i + 2);
                    m1278b(aod2, false, i3);
                    z2 = true;
                } else {
                    Iterator f2 = aod2.mo1325f();
                    boolean z6 = false;
                    boolean z7 = false;
                    while (f2.hasNext()) {
                        boolean a = m1277a((aod) f2.next());
                        z6 |= !a;
                        z7 |= a;
                        if (z7 && z6) {
                            break;
                        }
                    }
                    if (!z5 || !z6) {
                        if (!aod2.mo1324e()) {
                            mo1340a(" rdf:parseType=\"Resource\"/>");
                            mo1342b();
                        } else if (!z6) {
                            mo1341a(aod2, i + 1);
                            mo1340a("/>");
                            mo1342b();
                        } else if (z7) {
                            mo1343b(62);
                            mo1342b();
                            int i4 = i + 1;
                            mo1337a(i4);
                            mo1340a("<rdf:Description");
                            mo1341a(aod2, i + 2);
                            mo1340a(">");
                            mo1342b();
                            mo1344b(aod2, i4);
                            mo1337a(i4);
                            mo1340a("</rdf:Description>");
                            mo1342b();
                            z3 = true;
                        } else {
                            mo1340a(" rdf:parseType=\"Resource\">");
                            mo1342b();
                            mo1344b(aod2, i + 1);
                            z3 = true;
                        }
                        z = z3;
                        z2 = true;
                    } else {
                        throw new ang("Can't mix rdf:resource qualifier and element fields", 202);
                    }
                }
                if (z) {
                    if (z2) {
                        mo1337a(i);
                    }
                    mo1340a("</");
                    mo1340a(str);
                    mo1343b(62);
                    mo1342b();
                }
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:61:0x0177  */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x018d A[RETURN] */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void mo1339a(p000.aod r13, boolean r14, int r15) {
        /*
            r12 = this;
            java.lang.String r0 = r13.f1246a
            if (r14 == 0) goto L_0x0007
            java.lang.String r0 = "rdf:value"
            goto L_0x0012
        L_0x0007:
            java.lang.String r1 = "[]"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L_0x0012
            java.lang.String r0 = "rdf:li"
        L_0x0012:
            r12.mo1337a((int) r15)
            r1 = 60
            r12.mo1343b(r1)
            r12.mo1340a((java.lang.String) r0)
            java.util.Iterator r1 = r13.mo1327h()
            r2 = 0
            r3 = 0
            r4 = 0
        L_0x0024:
            boolean r5 = r1.hasNext()
            r6 = 34
            java.lang.String r7 = "=\""
            r8 = 32
            r9 = 1
            if (r5 != 0) goto L_0x018e
            java.lang.String r1 = " rdf:parseType=\"Resource\">"
            r5 = 202(0xca, float:2.83E-43)
            r10 = 62
            if (r3 == 0) goto L_0x0072
            if (r14 != 0) goto L_0x0072
            if (r4 != 0) goto L_0x006a
            r12.mo1340a((java.lang.String) r1)
            r12.mo1342b()
            int r14 = r15 + 1
            r12.mo1339a((p000.aod) r13, (boolean) r9, (int) r14)
            java.util.Iterator r13 = r13.mo1327h()
        L_0x004c:
            boolean r1 = r13.hasNext()
            if (r1 == 0) goto L_0x0066
            java.lang.Object r1 = r13.next()
            aod r1 = (p000.aod) r1
            java.util.Set r3 = f1265g
            java.lang.String r4 = r1.f1246a
            boolean r3 = r3.contains(r4)
            if (r3 != 0) goto L_0x004c
            r12.mo1339a((p000.aod) r1, (boolean) r2, (int) r14)
            goto L_0x004c
        L_0x0066:
        L_0x0067:
            r2 = 1
            goto L_0x0175
        L_0x006a:
            ang r13 = new ang
            java.lang.String r14 = "Can't mix rdf:resource and general qualifiers"
            r13.<init>(r14, r5)
            throw r13
        L_0x0072:
            aop r14 = r13.mo1328i()
            boolean r14 = r14.mo1380k()
            java.lang.String r3 = "/>"
            if (r14 != 0) goto L_0x00c1
            aop r14 = r13.mo1328i()
            boolean r14 = r14.mo1368a()
            if (r14 == 0) goto L_0x009c
            java.lang.String r14 = " rdf:resource=\""
            r12.mo1340a((java.lang.String) r14)
            java.lang.String r13 = r13.f1247b
            r12.m1276a((java.lang.String) r13, (boolean) r9)
            java.lang.String r13 = "\"/>"
            r12.mo1340a((java.lang.String) r13)
            r12.mo1342b()
            goto L_0x0175
        L_0x009c:
            java.lang.String r14 = r13.f1247b
            if (r14 != 0) goto L_0x00a1
            goto L_0x00b7
        L_0x00a1:
            java.lang.String r1 = ""
            boolean r14 = r1.equals(r14)
            if (r14 != 0) goto L_0x00b7
            r12.mo1343b(r10)
            java.lang.String r13 = r13.f1247b
            r12.m1276a((java.lang.String) r13, (boolean) r2)
            r2 = 1
            r9 = 0
            goto L_0x0175
        L_0x00b7:
            r12.mo1340a((java.lang.String) r3)
            r12.mo1342b()
            goto L_0x0175
        L_0x00c1:
            aop r14 = r13.mo1328i()
            boolean r14 = r14.mo1375f()
            if (r14 == 0) goto L_0x0101
            r12.mo1343b(r10)
            r12.mo1342b()
            int r14 = r15 + 1
            r12.m1278b(r13, r9, r14)
            aop r1 = r13.mo1328i()
            boolean r1 = r1.mo1378i()
            if (r1 == 0) goto L_0x00e3
            p000.C0637xj.m15909b((p000.aod) r13)
        L_0x00e3:
            java.util.Iterator r1 = r13.mo1325f()
        L_0x00e7:
            boolean r3 = r1.hasNext()
            if (r3 == 0) goto L_0x00f9
            java.lang.Object r3 = r1.next()
            aod r3 = (p000.aod) r3
            int r4 = r15 + 2
            r12.mo1339a((p000.aod) r3, (boolean) r2, (int) r4)
            goto L_0x00e7
        L_0x00f9:
            r12.m1278b(r13, r2, r14)
            r2 = 1
            goto L_0x0175
        L_0x0101:
            if (r4 == 0) goto L_0x0146
            java.util.Iterator r13 = r13.mo1325f()
        L_0x0107:
            boolean r14 = r13.hasNext()
            if (r14 == 0) goto L_0x013d
            java.lang.Object r14 = r13.next()
            aod r14 = (p000.aod) r14
            boolean r1 = m1277a((p000.aod) r14)
            if (r1 == 0) goto L_0x0135
            r12.mo1342b()
            int r1 = r15 + 1
            r12.mo1337a((int) r1)
            r12.mo1343b(r8)
            java.lang.String r1 = r14.f1246a
            r12.mo1340a((java.lang.String) r1)
            r12.mo1340a((java.lang.String) r7)
            java.lang.String r14 = r14.f1247b
            r12.m1276a((java.lang.String) r14, (boolean) r9)
            r12.mo1343b(r6)
            goto L_0x0107
        L_0x0135:
            ang r13 = new ang
            java.lang.String r14 = "Can't mix rdf:resource and complex fields"
            r13.<init>(r14, r5)
            throw r13
        L_0x013d:
            r12.mo1340a((java.lang.String) r3)
            r12.mo1342b()
            goto L_0x0175
        L_0x0146:
            boolean r14 = r13.mo1324e()
            if (r14 != 0) goto L_0x0156
            java.lang.String r13 = " rdf:parseType=\"Resource\"/>"
            r12.mo1340a((java.lang.String) r13)
            r12.mo1342b()
            goto L_0x0175
        L_0x0156:
            r12.mo1340a((java.lang.String) r1)
            r12.mo1342b()
            java.util.Iterator r13 = r13.mo1325f()
        L_0x0161:
            boolean r14 = r13.hasNext()
            if (r14 == 0) goto L_0x0173
            java.lang.Object r14 = r13.next()
            aod r14 = (p000.aod) r14
            int r1 = r15 + 1
            r12.mo1339a((p000.aod) r14, (boolean) r2, (int) r1)
            goto L_0x0161
        L_0x0173:
            goto L_0x0067
        L_0x0175:
            if (r2 == 0) goto L_0x018d
            if (r9 != 0) goto L_0x017a
            goto L_0x017d
        L_0x017a:
            r12.mo1337a((int) r15)
        L_0x017d:
            java.lang.String r13 = "</"
            r12.mo1340a((java.lang.String) r13)
            r12.mo1340a((java.lang.String) r0)
            r12.mo1343b(r10)
            r12.mo1342b()
            return
        L_0x018d:
            return
        L_0x018e:
            java.lang.Object r5 = r1.next()
            aod r5 = (p000.aod) r5
            java.util.Set r10 = f1265g
            java.lang.String r11 = r5.f1246a
            boolean r10 = r10.contains(r11)
            if (r10 == 0) goto L_0x01bd
            java.lang.String r4 = r5.f1246a
            java.lang.String r10 = "rdf:resource"
            boolean r4 = r10.equals(r4)
            if (r14 != 0) goto L_0x01bc
            r12.mo1343b(r8)
            java.lang.String r8 = r5.f1246a
            r12.mo1340a((java.lang.String) r8)
            r12.mo1340a((java.lang.String) r7)
            java.lang.String r5 = r5.f1247b
            r12.m1276a((java.lang.String) r5, (boolean) r9)
            r12.mo1343b(r6)
            goto L_0x01be
        L_0x01bc:
            goto L_0x01be
        L_0x01bd:
            r3 = 1
        L_0x01be:
            goto L_0x0024
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.aoh.mo1339a(aod, boolean, int):void");
    }

    /* renamed from: b */
    public final void mo1343b(int i) {
        this.f1268c.write(i);
    }

    /* renamed from: a */
    public final void mo1340a(String str) {
        this.f1268c.write(str);
    }

    /* renamed from: c */
    public final void mo1345c(int i) {
        while (i > 0) {
            this.f1268c.write(32);
            i--;
        }
    }

    /* renamed from: a */
    public final void mo1337a(int i) {
        while (i > 0) {
            this.f1268c.write(this.f1269d.f1285d);
            i--;
        }
    }

    /* renamed from: b */
    public final void mo1342b() {
        this.f1268c.write(this.f1269d.f1284c);
    }

    /* renamed from: a */
    public final void mo1336a() {
        mo1343b(34);
        String str = this.f1266a.f1242a.f1246a;
        if (str != null) {
            m1276a(str, true);
        }
        mo1343b(34);
    }
}
