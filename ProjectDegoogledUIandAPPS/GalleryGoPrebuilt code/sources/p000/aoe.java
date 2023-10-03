package p000;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* renamed from: aoe */
/* compiled from: PG */
public final class aoe {

    /* renamed from: a */
    private static final Map f1256a = new HashMap();

    static {
        aop aop = new aop();
        aop.mo1381l();
        f1256a.put("dc:contributor", aop);
        f1256a.put("dc:language", aop);
        f1256a.put("dc:publisher", aop);
        f1256a.put("dc:relation", aop);
        f1256a.put("dc:subject", aop);
        f1256a.put("dc:type", aop);
        aop aop2 = new aop();
        aop2.mo1381l();
        aop2.mo1384o();
        f1256a.put("dc:creator", aop2);
        f1256a.put("dc:date", aop2);
        aop aop3 = new aop();
        aop3.mo1381l();
        aop3.mo1384o();
        aop3.mo1383n();
        aop3.mo1382m();
        f1256a.put("dc:description", aop3);
        f1256a.put("dc:rights", aop3);
        f1256a.put("dc:title", aop3);
    }

    /* renamed from: a */
    private static void m1264a(aod aod, aod aod2, boolean z) {
        if (!aod.f1247b.equals(aod2.f1247b) || aod.mo1318c() != aod2.mo1318c()) {
            throw new ang("Mismatch between alias and base nodes", 203);
        } else if (z || (aod.f1246a.equals(aod2.f1246a) && aod.mo1328i().equals(aod2.mo1328i()) && aod.mo1322d() == aod2.mo1322d())) {
            Iterator f = aod.mo1325f();
            Iterator f2 = aod2.mo1325f();
            while (f.hasNext() && f2.hasNext()) {
                m1264a((aod) f.next(), (aod) f2.next(), false);
            }
            Iterator h = aod.mo1327h();
            Iterator h2 = aod2.mo1327h();
            while (h.hasNext() && h2.hasNext()) {
                m1264a((aod) h.next(), (aod) h2.next(), false);
            }
        } else {
            throw new ang("Mismatch between alias and base nodes", 203);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:131:0x0346  */
    /* JADX WARNING: Removed duplicated region for block: B:132:0x034b  */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static p000.ani m1262a(p000.aoa r19, p000.aoo r20) {
        /*
            r7 = r19
            aod r8 = r7.f1242a
            java.lang.String r9 = "http://purl.org/dc/elements/1.1/"
            r10 = 1
            p000.C0637xj.m15896a((p000.aod) r8, (java.lang.String) r9, (boolean) r10)
            aod r0 = r7.f1242a
            java.util.Iterator r11 = r0.mo1325f()
        L_0x0010:
            boolean r0 = r11.hasNext()
            r1 = 5
            java.lang.String r12 = "x-default"
            r2 = 0
            r13 = 0
            if (r0 == 0) goto L_0x022b
            java.lang.Object r0 = r11.next()
            r3 = r0
            aod r3 = (p000.aod) r3
            java.lang.String r0 = r3.f1246a
            boolean r0 = r9.equals(r0)
            if (r0 != 0) goto L_0x01b6
            java.lang.String r0 = r3.f1246a
            java.lang.String r2 = "http://ns.adobe.com/exif/1.0/"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x0141
            java.lang.String r0 = r3.f1246a
            java.lang.String r1 = "http://ns.adobe.com/xmp/1.0/DynamicMedia/"
            boolean r0 = r1.equals(r0)
            if (r0 == 0) goto L_0x0122
            java.lang.String r0 = "xmpDM:copyright"
            aod r0 = p000.C0637xj.m15907b((p000.aod) r3, (java.lang.String) r0, (boolean) r13)
            if (r0 == 0) goto L_0x011f
            aod r1 = r7.f1242a     // Catch:{ ang -> 0x011b }
            aod r1 = p000.C0637xj.m15896a((p000.aod) r1, (java.lang.String) r9, (boolean) r10)     // Catch:{ ang -> 0x011b }
            java.lang.String r15 = r0.f1247b     // Catch:{ ang -> 0x011b }
            java.lang.String r6 = "\n\n"
            java.lang.String r2 = "dc:rights"
            aod r5 = p000.C0637xj.m15907b((p000.aod) r1, (java.lang.String) r2, (boolean) r13)     // Catch:{ ang -> 0x011b }
            if (r5 == 0) goto L_0x00f0
            boolean r1 = r5.mo1324e()     // Catch:{ ang -> 0x011b }
            if (r1 == 0) goto L_0x00ee
            int r1 = p000.C0637xj.m15890a((p000.aod) r5, (java.lang.String) r12)     // Catch:{ ang -> 0x011b }
            if (r1 < 0) goto L_0x0067
            r10 = r5
            r14 = r6
            goto L_0x0089
        L_0x0067:
            aod r1 = r5.mo1309a((int) r10)     // Catch:{ ang -> 0x011b }
            java.lang.String r4 = r1.f1247b     // Catch:{ ang -> 0x011b }
            java.lang.String r2 = "http://purl.org/dc/elements/1.1/"
            java.lang.String r3 = "rights"
            java.lang.String r16 = ""
            java.lang.String r17 = "x-default"
            r1 = r19
            r18 = r4
            r4 = r16
            r10 = r5
            r5 = r17
            r14 = r6
            r6 = r18
            r1.mo1261a(r2, r3, r4, r5, r6)     // Catch:{ ang -> 0x011b }
            int r1 = p000.C0637xj.m15890a((p000.aod) r10, (java.lang.String) r12)     // Catch:{ ang -> 0x011b }
        L_0x0089:
            aod r1 = r10.mo1309a((int) r1)     // Catch:{ ang -> 0x011b }
            java.lang.String r2 = r1.f1247b     // Catch:{ ang -> 0x011b }
            int r3 = r2.indexOf(r14)     // Catch:{ ang -> 0x011b }
            if (r3 < 0) goto L_0x00c0
            int r3 = r3 + 2
            java.lang.String r4 = r2.substring(r3)     // Catch:{ ang -> 0x011b }
            boolean r4 = r4.equals(r15)     // Catch:{ ang -> 0x011b }
            if (r4 != 0) goto L_0x0113
            java.lang.String r2 = r2.substring(r13, r3)     // Catch:{ ang -> 0x011b }
            java.lang.String r2 = java.lang.String.valueOf(r2)     // Catch:{ ang -> 0x011b }
            java.lang.String r3 = java.lang.String.valueOf(r15)     // Catch:{ ang -> 0x011b }
            int r4 = r3.length()     // Catch:{ ang -> 0x011b }
            if (r4 != 0) goto L_0x00b9
            java.lang.String r3 = new java.lang.String     // Catch:{ ang -> 0x011b }
            r3.<init>(r2)     // Catch:{ ang -> 0x011b }
            goto L_0x00bd
        L_0x00b9:
            java.lang.String r3 = r2.concat(r3)     // Catch:{ ang -> 0x011b }
        L_0x00bd:
            r1.f1247b = r3     // Catch:{ ang -> 0x011b }
            goto L_0x0113
        L_0x00c0:
            boolean r3 = r15.equals(r2)     // Catch:{ ang -> 0x011b }
            if (r3 != 0) goto L_0x0113
            java.lang.String r3 = java.lang.String.valueOf(r2)     // Catch:{ ang -> 0x011b }
            int r3 = r3.length()     // Catch:{ ang -> 0x011b }
            r4 = 2
            int r3 = r3 + r4
            java.lang.String r4 = java.lang.String.valueOf(r15)     // Catch:{ ang -> 0x011b }
            int r4 = r4.length()     // Catch:{ ang -> 0x011b }
            int r3 = r3 + r4
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ ang -> 0x011b }
            r4.<init>(r3)     // Catch:{ ang -> 0x011b }
            r4.append(r2)     // Catch:{ ang -> 0x011b }
            r4.append(r14)     // Catch:{ ang -> 0x011b }
            r4.append(r15)     // Catch:{ ang -> 0x011b }
            java.lang.String r2 = r4.toString()     // Catch:{ ang -> 0x011b }
            r1.f1247b = r2     // Catch:{ ang -> 0x011b }
            goto L_0x0113
        L_0x00ee:
            r14 = r6
            goto L_0x00f1
        L_0x00f0:
            r14 = r6
        L_0x00f1:
            java.lang.String r1 = java.lang.String.valueOf(r15)     // Catch:{ ang -> 0x011b }
            int r2 = r1.length()     // Catch:{ ang -> 0x011b }
            if (r2 != 0) goto L_0x0101
            java.lang.String r1 = new java.lang.String     // Catch:{ ang -> 0x011b }
            r1.<init>(r14)     // Catch:{ ang -> 0x011b }
            goto L_0x0105
        L_0x0101:
            java.lang.String r1 = r14.concat(r1)     // Catch:{ ang -> 0x011b }
        L_0x0105:
            r6 = r1
            java.lang.String r2 = "http://purl.org/dc/elements/1.1/"
            java.lang.String r3 = "rights"
            java.lang.String r4 = ""
            java.lang.String r5 = "x-default"
            r1 = r19
            r1.mo1261a(r2, r3, r4, r5, r6)     // Catch:{ ang -> 0x011b }
        L_0x0113:
            aod r1 = r0.f1248c     // Catch:{ ang -> 0x011b }
            r1.mo1317b((p000.aod) r0)     // Catch:{ ang -> 0x011b }
            r10 = 1
            goto L_0x0010
        L_0x011b:
            r0 = move-exception
            r10 = 1
            goto L_0x0010
        L_0x011f:
            r10 = 1
            goto L_0x0010
        L_0x0122:
            java.lang.String r0 = r3.f1246a
            java.lang.String r1 = "http://ns.adobe.com/xap/1.0/rights/"
            boolean r0 = r1.equals(r0)
            if (r0 == 0) goto L_0x013e
            java.lang.String r0 = "xmpRights:UsageTerms"
            aod r0 = p000.C0637xj.m15907b((p000.aod) r3, (java.lang.String) r0, (boolean) r13)
            if (r0 == 0) goto L_0x013b
            m1263a(r0)
            r10 = 1
            goto L_0x0010
        L_0x013b:
            r10 = 1
            goto L_0x0010
        L_0x013e:
            r10 = 1
            goto L_0x0010
        L_0x0141:
            java.lang.String r0 = "exif:GPSTimeStamp"
            aod r0 = p000.C0637xj.m15907b((p000.aod) r3, (java.lang.String) r0, (boolean) r13)
            if (r0 != 0) goto L_0x014b
            goto L_0x01a4
        L_0x014b:
            java.lang.String r2 = r0.f1247b     // Catch:{ ang -> 0x01a3 }
            ane r2 = p000.iol.m14225a((java.lang.String) r2)     // Catch:{ ang -> 0x01a3 }
            r4 = r2
            anu r4 = (p000.anu) r4     // Catch:{ ang -> 0x01a3 }
            int r4 = r4.f1212a     // Catch:{ ang -> 0x01a3 }
            if (r4 != 0) goto L_0x01a4
            r4 = r2
            anu r4 = (p000.anu) r4     // Catch:{ ang -> 0x01a3 }
            int r4 = r4.f1213b     // Catch:{ ang -> 0x01a3 }
            if (r4 != 0) goto L_0x01a4
            r4 = r2
            anu r4 = (p000.anu) r4     // Catch:{ ang -> 0x01a3 }
            int r4 = r4.f1214c     // Catch:{ ang -> 0x01a3 }
            if (r4 != 0) goto L_0x01a4
            java.lang.String r4 = "exif:DateTimeOriginal"
            aod r4 = p000.C0637xj.m15907b((p000.aod) r3, (java.lang.String) r4, (boolean) r13)     // Catch:{ ang -> 0x01a3 }
            if (r4 != 0) goto L_0x0174
            java.lang.String r4 = "exif:DateTimeDigitized"
            aod r4 = p000.C0637xj.m15907b((p000.aod) r3, (java.lang.String) r4, (boolean) r13)     // Catch:{ ang -> 0x01a3 }
        L_0x0174:
            java.lang.String r4 = r4.f1247b     // Catch:{ ang -> 0x01a3 }
            ane r4 = p000.iol.m14225a((java.lang.String) r4)     // Catch:{ ang -> 0x01a3 }
            java.util.Calendar r2 = r2.mo1256i()     // Catch:{ ang -> 0x01a3 }
            r5 = r4
            anu r5 = (p000.anu) r5     // Catch:{ ang -> 0x01a3 }
            int r5 = r5.f1212a     // Catch:{ ang -> 0x01a3 }
            r6 = 1
            r2.set(r6, r5)     // Catch:{ ang -> 0x01a3 }
            r5 = r4
            anu r5 = (p000.anu) r5     // Catch:{ ang -> 0x01a3 }
            int r5 = r5.f1213b     // Catch:{ ang -> 0x01a3 }
            r6 = 2
            r2.set(r6, r5)     // Catch:{ ang -> 0x01a3 }
            anu r4 = (p000.anu) r4     // Catch:{ ang -> 0x01a3 }
            int r4 = r4.f1214c     // Catch:{ ang -> 0x01a3 }
            r2.set(r1, r4)     // Catch:{ ang -> 0x01a3 }
            anu r1 = new anu     // Catch:{ ang -> 0x01a3 }
            r1.<init>(r2)     // Catch:{ ang -> 0x01a3 }
            java.lang.String r1 = p000.cdu.m4145a((p000.ane) r1)     // Catch:{ ang -> 0x01a3 }
            r0.f1247b = r1     // Catch:{ ang -> 0x01a3 }
            goto L_0x01a4
        L_0x01a3:
            r0 = move-exception
        L_0x01a4:
            java.lang.String r0 = "exif:UserComment"
            aod r0 = p000.C0637xj.m15907b((p000.aod) r3, (java.lang.String) r0, (boolean) r13)
            if (r0 == 0) goto L_0x01b3
            m1263a(r0)
            r10 = 1
            goto L_0x0010
        L_0x01b3:
            r10 = 1
            goto L_0x0010
        L_0x01b6:
            r0 = 1
        L_0x01b7:
            int r1 = r3.mo1318c()
            if (r0 > r1) goto L_0x0228
            aod r1 = r3.mo1309a((int) r0)
            java.util.Map r4 = f1256a
            java.lang.String r5 = r1.f1246a
            java.lang.Object r4 = r4.get(r5)
            aop r4 = (p000.aop) r4
            if (r4 == 0) goto L_0x0225
            aop r5 = r1.mo1328i()
            int r5 = r5.f1282a
            r5 = r5 & 768(0x300, float:1.076E-42)
            if (r5 != 0) goto L_0x020c
            aod r5 = new aod
            java.lang.String r6 = r1.f1246a
            r5.<init>(r6, r4)
            java.lang.String r6 = "[]"
            r1.f1246a = r6
            r5.mo1313a((p000.aod) r1)
            r5.f1248c = r3
            java.util.List r6 = r3.mo1330k()
            int r10 = r0 + -1
            r6.set(r10, r5)
            boolean r4 = r4.mo1378i()
            if (r4 != 0) goto L_0x01f7
        L_0x01f6:
            goto L_0x0225
        L_0x01f7:
            aop r4 = r1.mo1328i()
            boolean r4 = r4.mo1372c()
            if (r4 != 0) goto L_0x01f6
            aod r4 = new aod
            java.lang.String r5 = "xml:lang"
            r4.<init>(r5, r12, r2)
            r1.mo1319c((p000.aod) r4)
            goto L_0x0225
        L_0x020c:
            aop r5 = r1.mo1328i()
            r6 = 7680(0x1e00, float:1.0762E-41)
            r5.mo1358a(r6, r13)
            aop r5 = r1.mo1328i()
            r5.mo1366a((p000.aop) r4)
            boolean r4 = r4.mo1378i()
            if (r4 == 0) goto L_0x0225
            m1263a(r1)
        L_0x0225:
            int r0 = r0 + 1
            goto L_0x01b7
        L_0x0228:
            r10 = 1
            goto L_0x0010
        L_0x022b:
            boolean r0 = r8.f1252g
            if (r0 == 0) goto L_0x0365
            r8.f1252g = r13
            r0 = 4
            r3 = r20
            boolean r0 = r3.mo1359a(r0)
            java.util.ArrayList r3 = new java.util.ArrayList
            java.util.List r4 = r8.mo1330k()
            r3.<init>(r4)
            java.util.List r3 = java.util.Collections.unmodifiableList(r3)
            java.util.Iterator r3 = r3.iterator()
        L_0x0249:
            boolean r4 = r3.hasNext()
            if (r4 == 0) goto L_0x0365
            java.lang.Object r4 = r3.next()
            aod r4 = (p000.aod) r4
            boolean r5 = r4.f1252g
            if (r5 == 0) goto L_0x0249
            java.util.Iterator r5 = r4.mo1325f()
        L_0x025d:
            boolean r6 = r5.hasNext()
            if (r6 == 0) goto L_0x0360
            java.lang.Object r6 = r5.next()
            aod r6 = (p000.aod) r6
            boolean r9 = r6.f1253h
            if (r9 == 0) goto L_0x025d
            r6.f1253h = r13
            anl r9 = p000.ank.f1195a
            java.lang.String r10 = r6.f1246a
            aor r9 = r9.mo1273c(r10)
            if (r9 == 0) goto L_0x025d
            java.lang.String r10 = r9.mo1331a()
            r11 = 1
            aod r10 = p000.C0637xj.m15895a((p000.aod) r8, (java.lang.String) r10, (java.lang.String) r2, (boolean) r11)
            r10.f1251f = r13
            java.lang.String r11 = r9.mo1332b()
            java.lang.String r11 = java.lang.String.valueOf(r11)
            java.lang.String r14 = r9.mo1333c()
            java.lang.String r14 = java.lang.String.valueOf(r14)
            int r15 = r14.length()
            if (r15 != 0) goto L_0x02a0
            java.lang.String r14 = new java.lang.String
            r14.<init>(r11)
            goto L_0x02a4
        L_0x02a0:
            java.lang.String r14 = r11.concat(r14)
        L_0x02a4:
            aod r11 = p000.C0637xj.m15907b((p000.aod) r10, (java.lang.String) r14, (boolean) r13)
            if (r11 != 0) goto L_0x0313
            aol r11 = r9.mo1334d()
            boolean r11 = r11.mo1352a()
            if (r11 != 0) goto L_0x02e9
            aod r11 = new aod
            java.lang.String r14 = r9.mo1332b()
            java.lang.String r14 = java.lang.String.valueOf(r14)
            java.lang.String r15 = r9.mo1333c()
            java.lang.String r15 = java.lang.String.valueOf(r15)
            int r17 = r15.length()
            if (r17 != 0) goto L_0x02d2
            java.lang.String r15 = new java.lang.String
            r15.<init>(r14)
            goto L_0x02d6
        L_0x02d2:
            java.lang.String r15 = r14.concat(r15)
        L_0x02d6:
            aol r9 = r9.mo1334d()
            aop r9 = r9.mo1354c()
            r11.<init>(r15, r9)
            r10.mo1313a((p000.aod) r11)
            m1265a((java.util.Iterator) r5, (p000.aod) r6, (p000.aod) r11)
            goto L_0x025d
        L_0x02e9:
            java.lang.String r11 = r9.mo1332b()
            java.lang.String r11 = java.lang.String.valueOf(r11)
            java.lang.String r9 = r9.mo1333c()
            java.lang.String r9 = java.lang.String.valueOf(r9)
            int r14 = r9.length()
            if (r14 != 0) goto L_0x0305
            java.lang.String r9 = new java.lang.String
            r9.<init>(r11)
            goto L_0x0309
        L_0x0305:
            java.lang.String r9 = r11.concat(r9)
        L_0x0309:
            r6.f1246a = r9
            r10.mo1313a((p000.aod) r6)
            r5.remove()
            goto L_0x025d
        L_0x0313:
            aol r10 = r9.mo1334d()
            boolean r10 = r10.mo1352a()
            if (r10 != 0) goto L_0x0355
            aol r9 = r9.mo1334d()
            boolean r9 = r9.mo1353b()
            if (r9 == 0) goto L_0x0335
            int r9 = p000.C0637xj.m15890a((p000.aod) r11, (java.lang.String) r12)
            r10 = -1
            if (r9 == r10) goto L_0x0334
            aod r9 = r11.mo1309a((int) r9)
            r10 = r9
            goto L_0x033c
        L_0x0334:
            goto L_0x033b
        L_0x0335:
            boolean r9 = r11.mo1324e()
            if (r9 != 0) goto L_0x033e
        L_0x033b:
            r10 = r2
        L_0x033c:
            r9 = 1
            goto L_0x0344
        L_0x033e:
            r9 = 1
            aod r10 = r11.mo1309a((int) r9)
        L_0x0344:
            if (r10 != 0) goto L_0x034b
            m1265a((java.util.Iterator) r5, (p000.aod) r6, (p000.aod) r11)
            goto L_0x025d
        L_0x034b:
            if (r0 == 0) goto L_0x0350
            m1264a((p000.aod) r6, (p000.aod) r10, (boolean) r9)
        L_0x0350:
            r5.remove()
            goto L_0x025d
        L_0x0355:
            r9 = 1
            if (r0 == 0) goto L_0x035b
            m1264a((p000.aod) r6, (p000.aod) r11, (boolean) r9)
        L_0x035b:
            r5.remove()
            goto L_0x025d
        L_0x0360:
            r4.f1252g = r13
            goto L_0x0249
        L_0x0365:
            java.lang.String r0 = r8.f1246a
            if (r0 == 0) goto L_0x03cf
            int r0 = r0.length()
            r3 = 36
            if (r0 < r3) goto L_0x03cf
            java.lang.String r0 = r8.f1246a
            java.lang.String r0 = r0.toLowerCase()
            java.lang.String r3 = "uuid:"
            boolean r4 = r0.startsWith(r3)
            if (r4 == 0) goto L_0x0383
            java.lang.String r0 = r0.substring(r1)
        L_0x0383:
            boolean r1 = p000.ant.m1197c((java.lang.String) r0)
            if (r1 == 0) goto L_0x03cf
            java.lang.String r1 = "http://ns.adobe.com/xap/1.0/mm/"
            java.lang.String r4 = "InstanceID"
            aoj r1 = p000.C0643xp.m15938a((java.lang.String) r1, (java.lang.String) r4)
            r4 = 1
            aod r1 = p000.C0637xj.m15894a((p000.aod) r8, (p000.aoj) r1, (boolean) r4, (p000.aop) r2)
            if (r1 == 0) goto L_0x03c5
            r1.f1250e = r2
            java.lang.String r0 = java.lang.String.valueOf(r0)
            int r4 = r0.length()
            if (r4 != 0) goto L_0x03aa
            java.lang.String r0 = new java.lang.String
            r0.<init>(r3)
            goto L_0x03ae
        L_0x03aa:
            java.lang.String r0 = r3.concat(r0)
        L_0x03ae:
            r1.f1247b = r0
            r1.mo1316b()
            aop r0 = r1.mo1328i()
            r0.mo1369b(r13)
            r0.mo1367a((boolean) r13)
            r0.mo1371c((boolean) r13)
            r1.f1249d = r2
            r8.f1246a = r2
            goto L_0x03cf
        L_0x03c5:
            ang r0 = new ang
            r1 = 9
            java.lang.String r2 = "Failure creating xmpMM:InstanceID"
            r0.<init>(r2, r1)
            throw r0
        L_0x03cf:
            java.util.Iterator r0 = r8.mo1325f()
        L_0x03d3:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x03e9
            java.lang.Object r1 = r0.next()
            aod r1 = (p000.aod) r1
            boolean r1 = r1.mo1324e()
            if (r1 != 0) goto L_0x03d3
            r0.remove()
            goto L_0x03d3
        L_0x03e9:
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.aoe.m1262a(aoa, aoo):ani");
    }

    /* renamed from: a */
    private static void m1263a(aod aod) {
        if (aod != null && aod.mo1328i().mo1375f()) {
            aop i = aod.mo1328i();
            i.mo1384o();
            i.mo1383n();
            i.mo1382m();
            Iterator f = aod.mo1325f();
            while (f.hasNext()) {
                aod aod2 = (aod) f.next();
                if (aod2.mo1328i().mo1380k()) {
                    f.remove();
                } else if (!aod2.mo1328i().mo1372c()) {
                    String str = aod2.f1247b;
                    if (str == null || str.length() == 0) {
                        f.remove();
                    } else {
                        aod2.mo1319c(new aod("xml:lang", "x-repair", (aop) null));
                    }
                }
            }
        }
    }

    /* renamed from: a */
    private static void m1265a(Iterator it, aod aod, aod aod2) {
        if (aod2.mo1328i().mo1378i()) {
            if (!aod.mo1328i().mo1372c()) {
                aod.mo1319c(new aod("xml:lang", "x-default", (aop) null));
            } else {
                throw new ang("Alias to x-default already has a language qualifier", 203);
            }
        }
        it.remove();
        aod.f1246a = "[]";
        aod2.mo1313a(aod);
    }
}
