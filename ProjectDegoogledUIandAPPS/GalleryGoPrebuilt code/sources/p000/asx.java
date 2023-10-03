package p000;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* renamed from: asx */
/* compiled from: PG */
public final class asx implements Runnable, Comparable, asp, bfv {

    /* renamed from: A */
    private volatile boolean f1563A;

    /* renamed from: B */
    private int f1564B;

    /* renamed from: C */
    private int f1565C;

    /* renamed from: a */
    public final asr f1566a = new asr();

    /* renamed from: b */
    public final asv f1567b;

    /* renamed from: c */
    public final asw f1568c = new asw();

    /* renamed from: d */
    public apa f1569d;

    /* renamed from: e */
    public aqu f1570e;

    /* renamed from: f */
    public apb f1571f;

    /* renamed from: g */
    public atq f1572g;

    /* renamed from: h */
    public int f1573h;

    /* renamed from: i */
    public int f1574i;

    /* renamed from: j */
    public atc f1575j;

    /* renamed from: k */
    public aqz f1576k;

    /* renamed from: l */
    public ass f1577l;

    /* renamed from: m */
    public int f1578m;

    /* renamed from: n */
    public boolean f1579n;

    /* renamed from: o */
    public volatile asq f1580o;

    /* renamed from: p */
    public volatile boolean f1581p;

    /* renamed from: q */
    public int f1582q;

    /* renamed from: r */
    private final List f1583r = new ArrayList();

    /* renamed from: s */
    private final bfy f1584s = bfy.m2451a();

    /* renamed from: t */
    private final C0306lc f1585t;

    /* renamed from: u */
    private final asu f1586u = new asu();

    /* renamed from: v */
    private Thread f1587v;

    /* renamed from: w */
    private aqu f1588w;

    /* renamed from: x */
    private aqu f1589x;

    /* renamed from: y */
    private Object f1590y;

    /* renamed from: z */
    private ari f1591z;

    public asx(asv asv, C0306lc lcVar) {
        this.f1567b = asv;
        this.f1585t = lcVar;
    }

    /* renamed from: af */
    public final bfy mo1573af() {
        return this.f1584s;
    }

    public final /* bridge */ /* synthetic */ int compareTo(Object obj) {
        asx asx = (asx) obj;
        int d = m1587d() - asx.m1587d();
        return d == 0 ? this.f1578m - asx.f1578m : d;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v3, resolved type: java.util.List} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v19, resolved type: auc} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v20, resolved type: asn} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v11, resolved type: java.util.List} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v18, resolved type: atz} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v32, resolved type: auc} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r22v1, resolved type: auc} */
    /* JADX WARNING: type inference failed for: r17v4 */
    /* JADX WARNING: type inference failed for: r0v85, types: [aua] */
    /* JADX WARNING: type inference failed for: r17v13 */
    /* JADX WARNING: type inference failed for: r17v15 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:104:0x0203 A[LOOP:0: B:24:0x007a->B:104:0x0203, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:106:0x0214 A[SYNTHETIC, Splitter:B:106:0x0214] */
    /* JADX WARNING: Removed duplicated region for block: B:113:0x0225 A[SYNTHETIC, Splitter:B:113:0x0225] */
    /* JADX WARNING: Removed duplicated region for block: B:199:0x0212 A[EDGE_INSN: B:199:0x0212->B:105:0x0212 ?: BREAK  , SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x007c A[Catch:{ all -> 0x0232, all -> 0x0239 }] */
    /* renamed from: i */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void m1592i() {
        /*
            r32 = this;
            r1 = r32
            ari r4 = r1.f1591z     // Catch:{ atu -> 0x024b }
            java.lang.Object r0 = r1.f1590y     // Catch:{ atu -> 0x024b }
            int r5 = r1.f1565C     // Catch:{ atu -> 0x024b }
            if (r0 == 0) goto L_0x0243
            p000.bfk.m2412a()     // Catch:{ all -> 0x023e }
            asr r6 = r1.f1566a     // Catch:{ all -> 0x023e }
            java.lang.Class r7 = r0.getClass()     // Catch:{ all -> 0x023e }
            atx r6 = r6.mo1559b(r7)     // Catch:{ all -> 0x023e }
            aqz r7 = r1.f1576k     // Catch:{ all -> 0x023e }
            int r8 = android.os.Build.VERSION.SDK_INT     // Catch:{ all -> 0x023e }
            r8 = 4
            if (r5 == r8) goto L_0x0027
            asr r10 = r1.f1566a     // Catch:{ all -> 0x023e }
            boolean r10 = r10.f1554r     // Catch:{ all -> 0x023e }
            if (r10 != 0) goto L_0x0026
            r10 = 0
            goto L_0x0028
        L_0x0026:
        L_0x0027:
            r10 = 1
        L_0x0028:
            aqy r11 = p000.bac.f1933b     // Catch:{ all -> 0x023e }
            java.lang.Object r11 = r7.mo1502a((p000.aqy) r11)     // Catch:{ all -> 0x023e }
            java.lang.Boolean r11 = (java.lang.Boolean) r11     // Catch:{ all -> 0x023e }
            if (r11 != 0) goto L_0x0033
            goto L_0x003c
        L_0x0033:
            boolean r11 = r11.booleanValue()     // Catch:{ all -> 0x023e }
            if (r11 == 0) goto L_0x004f
            if (r10 == 0) goto L_0x003c
            goto L_0x004f
        L_0x003c:
            aqz r7 = new aqz     // Catch:{ all -> 0x023e }
            r7.<init>()     // Catch:{ all -> 0x023e }
            aqz r11 = r1.f1576k     // Catch:{ all -> 0x023e }
            r7.mo1504a((p000.aqz) r11)     // Catch:{ all -> 0x023e }
            aqy r11 = p000.bac.f1933b     // Catch:{ all -> 0x023e }
            java.lang.Boolean r10 = java.lang.Boolean.valueOf(r10)     // Catch:{ all -> 0x023e }
            r7.mo1503a(r11, r10)     // Catch:{ all -> 0x023e }
        L_0x004f:
            apa r10 = r1.f1569d     // Catch:{ all -> 0x023e }
            aph r10 = r10.f1316c     // Catch:{ all -> 0x023e }
            arn r10 = r10.f1332e     // Catch:{ all -> 0x023e }
            ark r10 = r10.mo1530a((java.lang.Object) r0)     // Catch:{ all -> 0x023e }
            int r11 = r1.f1573h     // Catch:{ all -> 0x0239 }
            int r15 = r1.f1574i     // Catch:{ all -> 0x0239 }
            ast r14 = new ast     // Catch:{ all -> 0x0239 }
            r14.<init>(r1, r5)     // Catch:{ all -> 0x0239 }
            lc r0 = r6.f1676a     // Catch:{ all -> 0x0239 }
            java.lang.Object r0 = r0.mo1971a()     // Catch:{ all -> 0x0239 }
            java.util.List r0 = (java.util.List) r0     // Catch:{ all -> 0x0239 }
            java.lang.Object r0 = p000.cns.m4632a((java.lang.Object) r0)     // Catch:{ all -> 0x0239 }
            r5 = r0
            java.util.List r5 = (java.util.List) r5     // Catch:{ all -> 0x0239 }
            java.util.List r0 = r6.f1677b     // Catch:{ all -> 0x0232 }
            int r13 = r0.size()     // Catch:{ all -> 0x0232 }
            r12 = 0
            r18 = 0
        L_0x007a:
            if (r12 >= r13) goto L_0x0212
            java.util.List r0 = r6.f1677b     // Catch:{ all -> 0x0232 }
            java.lang.Object r0 = r0.get(r12)     // Catch:{ all -> 0x0232 }
            r9 = r0
            asy r9 = (p000.asy) r9     // Catch:{ all -> 0x0232 }
            lc r0 = r9.f1593b     // Catch:{ atu -> 0x01f2 }
            java.lang.Object r0 = r0.mo1971a()     // Catch:{ atu -> 0x01f2 }
            java.util.List r0 = (java.util.List) r0     // Catch:{ atu -> 0x01f2 }
            java.lang.Object r0 = p000.cns.m4632a((java.lang.Object) r0)     // Catch:{ atu -> 0x01f2 }
            r2 = r0
            java.util.List r2 = (java.util.List) r2     // Catch:{ atu -> 0x01f2 }
            r19 = r12
            r12 = r9
            r20 = r13
            r13 = r10
            r3 = r14
            r14 = r11
            r21 = r15
            r16 = r7
            r17 = r2
            aua r0 = r12.mo1576a(r13, r14, r15, r16, r17)     // Catch:{ all -> 0x01e4 }
            lc r12 = r9.f1593b     // Catch:{ atu -> 0x01de }
            r12.mo1972a(r2)     // Catch:{ atu -> 0x01de }
            asx r2 = r3.f1555a     // Catch:{ atu -> 0x01de }
            int r12 = r3.f1556b     // Catch:{ atu -> 0x01de }
            java.lang.Object r13 = r0.mo1605b()     // Catch:{ atu -> 0x01de }
            java.lang.Class r13 = r13.getClass()     // Catch:{ atu -> 0x01de }
            if (r12 == r8) goto L_0x00ce
            asr r14 = r2.f1566a     // Catch:{ atu -> 0x01de }
            ard r14 = r14.mo1561c(r13)     // Catch:{ atu -> 0x01de }
            apa r15 = r2.f1569d     // Catch:{ atu -> 0x01de }
            int r8 = r2.f1573h     // Catch:{ atu -> 0x01de }
            r17 = r3
            int r3 = r2.f1574i     // Catch:{ atu -> 0x01dc }
            aua r3 = r14.mo1497a(r15, r0, r8, r3)     // Catch:{ atu -> 0x01dc }
            r28 = r14
            goto L_0x00d3
        L_0x00ce:
            r17 = r3
            r3 = r0
            r28 = 0
        L_0x00d3:
            boolean r8 = r0.equals(r3)     // Catch:{ atu -> 0x01dc }
            if (r8 == 0) goto L_0x00da
            goto L_0x00dd
        L_0x00da:
            r0.mo1607d()     // Catch:{ atu -> 0x01dc }
        L_0x00dd:
            asr r0 = r2.f1566a     // Catch:{ atu -> 0x01dc }
            apa r0 = r0.f1539c     // Catch:{ atu -> 0x01dc }
            aph r0 = r0.f1316c     // Catch:{ atu -> 0x01dc }
            bdw r0 = r0.f1331d     // Catch:{ atu -> 0x01dc }
            java.lang.Class r8 = r3.mo1604a()     // Catch:{ atu -> 0x01dc }
            arc r0 = r0.mo1848a(r8)     // Catch:{ atu -> 0x01dc }
            if (r0 == 0) goto L_0x0110
            asr r0 = r2.f1566a     // Catch:{ atu -> 0x01dc }
            apa r0 = r0.f1539c     // Catch:{ atu -> 0x01dc }
            aph r0 = r0.f1316c     // Catch:{ atu -> 0x01dc }
            bdw r0 = r0.f1331d     // Catch:{ atu -> 0x01dc }
            java.lang.Class r14 = r3.mo1604a()     // Catch:{ atu -> 0x01dc }
            arc r0 = r0.mo1848a(r14)     // Catch:{ atu -> 0x01dc }
            if (r0 == 0) goto L_0x0106
            int r14 = r0.mo1509a()     // Catch:{ atu -> 0x01dc }
            goto L_0x0113
        L_0x0106:
            apf r0 = new apf     // Catch:{ atu -> 0x01dc }
            java.lang.Class r2 = r3.mo1604a()     // Catch:{ atu -> 0x01dc }
            r0.<init>(r2)     // Catch:{ atu -> 0x01dc }
            throw r0     // Catch:{ atu -> 0x01dc }
        L_0x0110:
            r0 = 0
            r14 = 3
        L_0x0113:
            asr r15 = r2.f1566a     // Catch:{ atu -> 0x01dc }
            aqu r8 = r2.f1588w     // Catch:{ atu -> 0x01dc }
            java.util.List r15 = r15.mo1562c()     // Catch:{ atu -> 0x01dc }
            r31 = r11
            int r11 = r15.size()     // Catch:{ atu -> 0x01f0 }
            r1 = 0
        L_0x0122:
            if (r1 >= r11) goto L_0x013d
            java.lang.Object r23 = r15.get(r1)     // Catch:{ atu -> 0x01f0 }
            r24 = r11
            r11 = r23
            axn r11 = (p000.axn) r11     // Catch:{ atu -> 0x01f0 }
            aqu r11 = r11.f1829a     // Catch:{ atu -> 0x01f0 }
            boolean r11 = r11.equals(r8)     // Catch:{ atu -> 0x01f0 }
            if (r11 != 0) goto L_0x013b
            int r1 = r1 + 1
            r11 = r24
            goto L_0x0122
        L_0x013b:
            r1 = 1
            goto L_0x013f
        L_0x013d:
            r1 = 0
        L_0x013f:
            r8 = 1
            r1 = r1 ^ r8
            atc r8 = r2.f1575j     // Catch:{ atu -> 0x01f0 }
            boolean r1 = r8.mo1580a(r1, r12, r14)     // Catch:{ atu -> 0x01f0 }
            if (r1 != 0) goto L_0x014b
            goto L_0x01c2
        L_0x014b:
            if (r0 == 0) goto L_0x01ce
            int r1 = r14 + -1
            if (r14 == 0) goto L_0x01cb
            if (r1 == 0) goto L_0x01aa
            r8 = 1
            if (r1 == r8) goto L_0x0186
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException     // Catch:{ atu -> 0x01f0 }
            if (r14 == r8) goto L_0x0169
            r1 = 2
            if (r14 == r1) goto L_0x0166
            r1 = 3
            if (r14 == r1) goto L_0x0163
            java.lang.String r1 = "null"
            goto L_0x016b
        L_0x0163:
            java.lang.String r1 = "NONE"
            goto L_0x016b
        L_0x0166:
            java.lang.String r1 = "TRANSFORMED"
            goto L_0x016b
        L_0x0169:
            java.lang.String r1 = "SOURCE"
        L_0x016b:
            int r2 = r1.length()     // Catch:{ atu -> 0x01f0 }
            int r2 = r2 + 18
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ atu -> 0x01f0 }
            r3.<init>(r2)     // Catch:{ atu -> 0x01f0 }
            java.lang.String r2 = "Unknown strategy: "
            r3.append(r2)     // Catch:{ atu -> 0x01f0 }
            r3.append(r1)     // Catch:{ atu -> 0x01f0 }
            java.lang.String r1 = r3.toString()     // Catch:{ atu -> 0x01f0 }
            r0.<init>(r1)     // Catch:{ atu -> 0x01f0 }
            throw r0     // Catch:{ atu -> 0x01f0 }
        L_0x0186:
            auc r1 = new auc     // Catch:{ atu -> 0x01f0 }
            asr r8 = r2.f1566a     // Catch:{ atu -> 0x01f0 }
            aui r23 = r8.mo1560b()     // Catch:{ atu -> 0x01f0 }
            aqu r8 = r2.f1588w     // Catch:{ atu -> 0x01f0 }
            aqu r11 = r2.f1570e     // Catch:{ atu -> 0x01f0 }
            int r12 = r2.f1573h     // Catch:{ atu -> 0x01f0 }
            int r14 = r2.f1574i     // Catch:{ atu -> 0x01f0 }
            aqz r15 = r2.f1576k     // Catch:{ atu -> 0x01f0 }
            r22 = r1
            r24 = r8
            r25 = r11
            r26 = r12
            r27 = r14
            r29 = r13
            r30 = r15
            r22.<init>(r23, r24, r25, r26, r27, r28, r29, r30)     // Catch:{ atu -> 0x01f0 }
            goto L_0x01b3
        L_0x01aa:
            asn r1 = new asn     // Catch:{ atu -> 0x01f0 }
            aqu r8 = r2.f1588w     // Catch:{ atu -> 0x01f0 }
            aqu r11 = r2.f1570e     // Catch:{ atu -> 0x01f0 }
            r1.<init>(r8, r11)     // Catch:{ atu -> 0x01f0 }
        L_0x01b3:
            atz r3 = p000.atz.m1660a(r3)     // Catch:{ atu -> 0x01f0 }
            asu r2 = r2.f1586u     // Catch:{ atu -> 0x01f0 }
            r2.f1557a = r1     // Catch:{ atu -> 0x01f0 }
            r2.f1558b = r0     // Catch:{ atu -> 0x01f0 }
            r2.f1559c = r3     // Catch:{ atu -> 0x01f0 }
        L_0x01c2:
            bci r0 = r9.f1592a     // Catch:{ atu -> 0x01f0 }
            aua r0 = r0.mo1806a(r3, r7)     // Catch:{ atu -> 0x01f0 }
            r18 = r0
            goto L_0x0201
        L_0x01cb:
            r1 = 0
            throw r1     // Catch:{ atu -> 0x01f0 }
        L_0x01ce:
            apf r0 = new apf     // Catch:{ atu -> 0x01f0 }
            java.lang.Object r1 = r3.mo1605b()     // Catch:{ atu -> 0x01f0 }
            java.lang.Class r1 = r1.getClass()     // Catch:{ atu -> 0x01f0 }
            r0.<init>(r1)     // Catch:{ atu -> 0x01f0 }
            throw r0     // Catch:{ atu -> 0x01f0 }
        L_0x01dc:
            r0 = move-exception
            goto L_0x01e1
        L_0x01de:
            r0 = move-exception
            r17 = r3
        L_0x01e1:
            r31 = r11
            goto L_0x01fd
        L_0x01e4:
            r0 = move-exception
            r17 = r3
            r31 = r11
            r1 = r0
            lc r0 = r9.f1593b     // Catch:{ atu -> 0x01f0 }
            r0.mo1972a(r2)     // Catch:{ atu -> 0x01f0 }
            throw r1     // Catch:{ atu -> 0x01f0 }
        L_0x01f0:
            r0 = move-exception
            goto L_0x01fd
        L_0x01f2:
            r0 = move-exception
            r31 = r11
            r19 = r12
            r20 = r13
            r17 = r14
            r21 = r15
        L_0x01fd:
            r5.add(r0)     // Catch:{ all -> 0x0232 }
        L_0x0201:
            if (r18 != 0) goto L_0x0212
            int r12 = r19 + 1
            r8 = 4
            r1 = r32
            r14 = r17
            r13 = r20
            r15 = r21
            r11 = r31
            goto L_0x007a
        L_0x0212:
            if (r18 == 0) goto L_0x0225
            lc r0 = r6.f1676a     // Catch:{ all -> 0x0239 }
            r0.mo1972a(r5)     // Catch:{ all -> 0x0239 }
            r10.mo1529b()     // Catch:{ all -> 0x023e }
            r4.mo1516b()     // Catch:{ atu -> 0x024b }
            r4 = 0
            r1 = r32
            r0 = r18
            goto L_0x025d
        L_0x0225:
            atu r0 = new atu     // Catch:{ all -> 0x0232 }
            java.lang.String r1 = r6.f1678c     // Catch:{ all -> 0x0232 }
            java.util.ArrayList r2 = new java.util.ArrayList     // Catch:{ all -> 0x0232 }
            r2.<init>(r5)     // Catch:{ all -> 0x0232 }
            r0.<init>((java.lang.String) r1, (java.util.List) r2)     // Catch:{ all -> 0x0232 }
            throw r0     // Catch:{ all -> 0x0232 }
        L_0x0232:
            r0 = move-exception
            lc r1 = r6.f1676a     // Catch:{ all -> 0x0239 }
            r1.mo1972a(r5)     // Catch:{ all -> 0x0239 }
            throw r0     // Catch:{ all -> 0x0239 }
        L_0x0239:
            r0 = move-exception
            r10.mo1529b()     // Catch:{ all -> 0x023e }
            throw r0     // Catch:{ all -> 0x023e }
        L_0x023e:
            r0 = move-exception
            r4.mo1516b()     // Catch:{ atu -> 0x024b }
            throw r0     // Catch:{ atu -> 0x024b }
        L_0x0243:
            r4.mo1516b()     // Catch:{ atu -> 0x024b }
            r0 = 0
            r4 = 0
            r1 = r32
            goto L_0x025d
        L_0x024b:
            r0 = move-exception
            r1 = r32
            aqu r2 = r1.f1589x
            int r3 = r1.f1565C
            r4 = 0
            r0.mo1615a(r2, r3, r4)
            java.util.List r2 = r1.f1583r
            r2.add(r0)
            r0 = r4
        L_0x025d:
            if (r0 == 0) goto L_0x038d
            int r2 = r1.f1565C
            boolean r3 = r0 instanceof p000.atv
            if (r3 != 0) goto L_0x0266
            goto L_0x026c
        L_0x0266:
            r3 = r0
            atv r3 = (p000.atv) r3
            r3.mo1621e()
        L_0x026c:
            asu r3 = r1.f1586u
            boolean r3 = r3.mo1565a()
            if (r3 == 0) goto L_0x027a
            atz r0 = p000.atz.m1660a(r0)
            r4 = r0
            goto L_0x027c
        L_0x027a:
        L_0x027c:
            r32.m1591h()
            ass r3 = r1.f1577l
            monitor-enter(r3)
            r5 = r3
            ato r5 = (p000.ato) r5     // Catch:{ all -> 0x038a }
            r5.f1637h = r0     // Catch:{ all -> 0x038a }
            r0 = r3
            ato r0 = (p000.ato) r0     // Catch:{ all -> 0x038a }
            r0.f1643n = r2     // Catch:{ all -> 0x038a }
            monitor-exit(r3)     // Catch:{ all -> 0x038a }
            monitor-enter(r3)
            r0 = r3
            ato r0 = (p000.ato) r0     // Catch:{ all -> 0x0387 }
            bfy r0 = r0.f1631b     // Catch:{ all -> 0x0387 }
            r0.mo1973b()     // Catch:{ all -> 0x0387 }
            r0 = r3
            ato r0 = (p000.ato) r0     // Catch:{ all -> 0x0387 }
            boolean r0 = r0.f1642m     // Catch:{ all -> 0x0387 }
            if (r0 != 0) goto L_0x032e
            r0 = r3
            ato r0 = (p000.ato) r0     // Catch:{ all -> 0x0387 }
            atn r0 = r0.f1630a     // Catch:{ all -> 0x0387 }
            boolean r0 = r0.mo1591a()     // Catch:{ all -> 0x0387 }
            if (r0 != 0) goto L_0x0326
            r0 = r3
            ato r0 = (p000.ato) r0     // Catch:{ all -> 0x0387 }
            boolean r0 = r0.f1638i     // Catch:{ all -> 0x0387 }
            if (r0 != 0) goto L_0x031e
            r0 = r3
            ato r0 = (p000.ato) r0     // Catch:{ all -> 0x0387 }
            aua r0 = r0.f1637h     // Catch:{ all -> 0x0387 }
            r2 = r3
            ato r2 = (p000.ato) r2     // Catch:{ all -> 0x0387 }
            boolean r2 = r2.f1635f     // Catch:{ all -> 0x0387 }
            r5 = r3
            ato r5 = (p000.ato) r5     // Catch:{ all -> 0x0387 }
            aqu r5 = r5.f1634e     // Catch:{ all -> 0x0387 }
            r6 = r3
            ato r6 = (p000.ato) r6     // Catch:{ all -> 0x0387 }
            atr r6 = r6.f1632c     // Catch:{ all -> 0x0387 }
            ats r7 = new ats     // Catch:{ all -> 0x0387 }
            r7.<init>(r0, r2, r5, r6)     // Catch:{ all -> 0x0387 }
            r0 = r3
            ato r0 = (p000.ato) r0     // Catch:{ all -> 0x0387 }
            r0.f1641l = r7     // Catch:{ all -> 0x0387 }
            r0 = r3
            ato r0 = (p000.ato) r0     // Catch:{ all -> 0x0387 }
            r2 = 1
            r0.f1638i = r2     // Catch:{ all -> 0x0387 }
            r0 = r3
            ato r0 = (p000.ato) r0     // Catch:{ all -> 0x0387 }
            atn r0 = r0.f1630a     // Catch:{ all -> 0x0387 }
            atn r0 = r0.mo1594c()     // Catch:{ all -> 0x0387 }
            int r2 = r0.mo1593b()     // Catch:{ all -> 0x0387 }
            r5 = 1
            int r2 = r2 + r5
            r5 = r3
            ato r5 = (p000.ato) r5     // Catch:{ all -> 0x0387 }
            r5.mo1597a((int) r2)     // Catch:{ all -> 0x0387 }
            r2 = r3
            ato r2 = (p000.ato) r2     // Catch:{ all -> 0x0387 }
            aqu r2 = r2.f1634e     // Catch:{ all -> 0x0387 }
            r5 = r3
            ato r5 = (p000.ato) r5     // Catch:{ all -> 0x0387 }
            ats r5 = r5.f1641l     // Catch:{ all -> 0x0387 }
            monitor-exit(r3)     // Catch:{ all -> 0x0387 }
            ato r3 = (p000.ato) r3
            atp r6 = r3.f1633d
            r6.mo1586a(r3, r2, r5)
            java.util.Iterator r0 = r0.iterator()
        L_0x02ff:
            boolean r2 = r0.hasNext()
            if (r2 == 0) goto L_0x0319
            java.lang.Object r2 = r0.next()
            atm r2 = (p000.atm) r2
            java.util.concurrent.Executor r5 = r2.f1628b
            atl r6 = new atl
            bef r2 = r2.f1627a
            r6.<init>(r3, r2)
            r5.execute(r6)
            goto L_0x02ff
        L_0x0319:
            r3.mo1596a()
            goto L_0x033d
        L_0x031e:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException     // Catch:{ all -> 0x0387 }
            java.lang.String r2 = "Already have resource"
            r0.<init>(r2)     // Catch:{ all -> 0x0387 }
            throw r0     // Catch:{ all -> 0x0387 }
        L_0x0326:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException     // Catch:{ all -> 0x0387 }
            java.lang.String r2 = "Received a resource without any callbacks to notify"
            r0.<init>(r2)     // Catch:{ all -> 0x0387 }
            throw r0     // Catch:{ all -> 0x0387 }
        L_0x032e:
            r0 = r3
            ato r0 = (p000.ato) r0     // Catch:{ all -> 0x0387 }
            aua r0 = r0.f1637h     // Catch:{ all -> 0x0387 }
            r0.mo1607d()     // Catch:{ all -> 0x0387 }
            r0 = r3
            ato r0 = (p000.ato) r0     // Catch:{ all -> 0x0387 }
            r0.mo1602c()     // Catch:{ all -> 0x0387 }
            monitor-exit(r3)     // Catch:{ all -> 0x0387 }
        L_0x033d:
            r0 = 5
            r1.f1564B = r0
            asu r0 = r1.f1586u     // Catch:{ all -> 0x037f }
            boolean r0 = r0.mo1565a()     // Catch:{ all -> 0x037f }
            if (r0 == 0) goto L_0x036d
            asu r2 = r1.f1586u     // Catch:{ all -> 0x037f }
            asv r0 = r1.f1567b     // Catch:{ all -> 0x037f }
            aqz r3 = r1.f1576k     // Catch:{ all -> 0x037f }
            avc r0 = r0.mo1566a()     // Catch:{ all -> 0x0366 }
            aqu r5 = r2.f1557a     // Catch:{ all -> 0x0366 }
            aso r6 = new aso     // Catch:{ all -> 0x0366 }
            arc r7 = r2.f1558b     // Catch:{ all -> 0x0366 }
            atz r8 = r2.f1559c     // Catch:{ all -> 0x0366 }
            r6.<init>(r7, r8, r3)     // Catch:{ all -> 0x0366 }
            r0.mo1668a(r5, r6)     // Catch:{ all -> 0x0366 }
            atz r0 = r2.f1559c     // Catch:{ all -> 0x037f }
            r0.mo1625e()     // Catch:{ all -> 0x037f }
            goto L_0x036d
        L_0x0366:
            r0 = move-exception
            atz r2 = r2.f1559c     // Catch:{ all -> 0x037f }
            r2.mo1625e()     // Catch:{ all -> 0x037f }
            throw r0     // Catch:{ all -> 0x037f }
        L_0x036d:
            if (r4 != 0) goto L_0x0370
            goto L_0x0373
        L_0x0370:
            r4.mo1625e()
        L_0x0373:
            asw r0 = r1.f1568c
            boolean r0 = r0.mo1567a()
            if (r0 == 0) goto L_0x037e
            r32.mo1572a()
        L_0x037e:
            return
        L_0x037f:
            r0 = move-exception
            if (r4 != 0) goto L_0x0383
            goto L_0x0386
        L_0x0383:
            r4.mo1625e()
        L_0x0386:
            throw r0
        L_0x0387:
            r0 = move-exception
            monitor-exit(r3)     // Catch:{ all -> 0x0387 }
            throw r0
        L_0x038a:
            r0 = move-exception
            monitor-exit(r3)     // Catch:{ all -> 0x038a }
            throw r0
        L_0x038d:
            r32.m1589f()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.asx.m1592i():void");
    }

    /* renamed from: e */
    private final asq m1588e() {
        int i = this.f1564B;
        int i2 = i - 1;
        if (i == 0) {
            throw null;
        } else if (i2 == 1) {
            return new aub(this.f1566a, this);
        } else {
            if (i2 == 2) {
                return new asm(this.f1566a, this);
            }
            if (i2 == 3) {
                return new aug(this.f1566a, this);
            }
            if (i2 == 5) {
                return null;
            }
            String a = abj.m87a(i);
            StringBuilder sb = new StringBuilder(a.length() + 20);
            sb.append("Unrecognized stage: ");
            sb.append(a);
            throw new IllegalStateException(sb.toString());
        }
    }

    /* renamed from: a */
    public final int mo1571a(int i) {
        int i2 = i - 1;
        if (i == 0) {
            throw null;
        } else if (i2 != 0) {
            if (i2 != 1) {
                if (i2 != 2) {
                    if (i2 == 3 || i2 == 5) {
                        return 6;
                    }
                    String a = abj.m87a(i);
                    StringBuilder sb = new StringBuilder(a.length() + 20);
                    sb.append("Unrecognized stage: ");
                    sb.append(a);
                    throw new IllegalArgumentException(sb.toString());
                } else if (!this.f1579n) {
                    return 4;
                } else {
                    return 6;
                }
            } else if (!this.f1575j.mo1581b()) {
                return mo1571a(3);
            } else {
                return 3;
            }
        } else if (!this.f1575j.mo1578a()) {
            return mo1571a(2);
        } else {
            return 2;
        }
    }

    /* renamed from: d */
    private final int m1587d() {
        return this.f1571f.ordinal();
    }

    /* renamed from: g */
    private final void m1590g() {
        m1591h();
        atu atu = new atu("Failed to load resource", (List) new ArrayList(this.f1583r));
        ass ass = this.f1577l;
        synchronized (ass) {
            ((ato) ass).f1639j = atu;
        }
        synchronized (ass) {
            ((ato) ass).f1631b.mo1973b();
            if (((ato) ass).f1642m) {
                ((ato) ass).mo1602c();
            } else if (((ato) ass).f1630a.mo1591a()) {
                throw new IllegalStateException("Received an exception without any callbacks to notify");
            } else if (!((ato) ass).f1640k) {
                ((ato) ass).f1640k = true;
                aqu aqu = ((ato) ass).f1634e;
                atn c = ((ato) ass).f1630a.mo1594c();
                ((ato) ass).mo1597a(c.mo1593b() + 1);
                ato ato = (ato) ass;
                ato.f1633d.mo1586a(ato, aqu, (ats) null);
                Iterator it = c.iterator();
                while (it.hasNext()) {
                    atm atm = (atm) it.next();
                    atm.f1628b.execute(new atk(ato, atm.f1627a));
                }
                ato.mo1596a();
            } else {
                throw new IllegalStateException("Already failed once");
            }
        }
        if (this.f1568c.mo1568b()) {
            mo1572a();
        }
    }

    /* renamed from: a */
    public final void mo1553a(aqu aqu, Exception exc, ari ari, int i) {
        ari.mo1516b();
        atu atu = new atu("Fetching data failed", (Throwable) exc);
        atu.mo1615a(aqu, i, ari.mo1510a());
        this.f1583r.add(atu);
        if (Thread.currentThread() != this.f1587v) {
            this.f1582q = 2;
            this.f1577l.mo1564a(this);
            return;
        }
        m1589f();
    }

    /* renamed from: a */
    public final void mo1554a(aqu aqu, Object obj, ari ari, int i, aqu aqu2) {
        this.f1588w = aqu;
        this.f1590y = obj;
        this.f1591z = ari;
        this.f1565C = i;
        this.f1589x = aqu2;
        if (Thread.currentThread() != this.f1587v) {
            this.f1582q = 3;
            this.f1577l.mo1564a(this);
            return;
        }
        m1592i();
    }

    /* renamed from: a */
    public final void mo1572a() {
        this.f1568c.mo1569c();
        asu asu = this.f1586u;
        asu.f1557a = null;
        asu.f1558b = null;
        asu.f1559c = null;
        asr asr = this.f1566a;
        asr.f1539c = null;
        asr.f1540d = null;
        asr.f1550n = null;
        asr.f1543g = null;
        asr.f1547k = null;
        asr.f1545i = null;
        asr.f1551o = null;
        asr.f1546j = null;
        asr.f1552p = null;
        asr.f1537a.clear();
        asr.f1548l = false;
        asr.f1538b.clear();
        asr.f1549m = false;
        this.f1563A = false;
        this.f1569d = null;
        this.f1570e = null;
        this.f1576k = null;
        this.f1571f = null;
        this.f1572g = null;
        this.f1577l = null;
        this.f1564B = 0;
        this.f1580o = null;
        this.f1587v = null;
        this.f1588w = null;
        this.f1590y = null;
        this.f1565C = 0;
        this.f1591z = null;
        this.f1581p = false;
        this.f1583r.clear();
        this.f1585t.mo1972a(this);
    }

    /* renamed from: c */
    public final void mo1555c() {
        this.f1582q = 2;
        this.f1577l.mo1564a(this);
    }

    public final void run() {
        ari ari = this.f1591z;
        try {
            if (!this.f1581p) {
                int i = this.f1582q;
                int i2 = i - 1;
                if (i != 0) {
                    if (i2 == 0) {
                        this.f1564B = mo1571a(1);
                        this.f1580o = m1588e();
                        m1589f();
                    } else if (i2 == 1) {
                        m1589f();
                    } else if (i2 != 2) {
                        String str = i != 1 ? i != 2 ? "DECODE_DATA" : "SWITCH_TO_SOURCE_SERVICE" : "INITIALIZE";
                        StringBuilder sb = new StringBuilder(str.length() + 25);
                        sb.append("Unrecognized run reason: ");
                        sb.append(str);
                        throw new IllegalStateException(sb.toString());
                    } else {
                        m1592i();
                    }
                    if (ari == null) {
                        return;
                    }
                } else {
                    throw null;
                }
            } else {
                m1590g();
                if (ari == null) {
                    return;
                }
            }
            ari.mo1516b();
        } catch (asl e) {
            throw e;
        } catch (Throwable th) {
            if (ari != null) {
                ari.mo1516b();
            }
            throw th;
        }
    }

    /* renamed from: f */
    private final void m1589f() {
        this.f1587v = Thread.currentThread();
        bfk.m2412a();
        boolean z = false;
        while (!this.f1581p && this.f1580o != null && !(z = this.f1580o.mo1550a())) {
            this.f1564B = mo1571a(this.f1564B);
            this.f1580o = m1588e();
            if (this.f1564B == 4) {
                mo1555c();
                return;
            }
        }
        if ((this.f1564B == 6 || this.f1581p) && !z) {
            m1590g();
        }
    }

    /* renamed from: h */
    private final void m1591h() {
        Throwable th;
        this.f1584s.mo1973b();
        if (this.f1563A) {
            if (!this.f1583r.isEmpty()) {
                List list = this.f1583r;
                th = (Throwable) list.get(list.size() - 1);
            } else {
                th = null;
            }
            throw new IllegalStateException("Already notified", th);
        }
        this.f1563A = true;
    }
}
