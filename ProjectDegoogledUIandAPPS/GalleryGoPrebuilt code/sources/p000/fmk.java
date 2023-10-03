package p000;

/* renamed from: fmk */
/* compiled from: PG */
final class fmk implements fhz, fmx {

    /* renamed from: a */
    private final fid f10048a;

    /* renamed from: b */
    private final hqk f10049b;

    /* renamed from: c */
    private final hqk f10050c;

    /* renamed from: d */
    private final hqk f10051d;

    public fmk(fid fid, hqk hqk, hqk hqk2, hqk hqk3) {
        this.f10048a = fid;
        fid.mo5747a((fic) this);
        this.f10049b = hqk;
        this.f10050c = hqk2;
        this.f10051d = hqk3;
    }

    /* renamed from: a */
    private static long m9215a(fmj fmj) {
        return fmj.f10036b ? fmj.f10037c : fmj.f10039e;
    }

    /* renamed from: a */
    private static irl m9216a(fme fme) {
        iir g = irl.f14867f.mo8793g();
        if (fme.f10019a != null) {
            String str = fme.f10019a;
            if (g.f14319c) {
                g.mo8751b();
                g.f14319c = false;
            }
            irl irl = (irl) g.f14318b;
            str.getClass();
            irl.f14869a |= 1;
            irl.f14870b = str;
        }
        if (fme.f10020b != null) {
            long longValue = fme.f10020b.longValue();
            if (g.f14319c) {
                g.mo8751b();
                g.f14319c = false;
            }
            irl irl2 = (irl) g.f14318b;
            irl2.f14869a |= 2;
            irl2.f14871c = longValue;
        }
        if (fme.f10021c != null) {
            long longValue2 = fme.f10021c.longValue();
            if (g.f14319c) {
                g.mo8751b();
                g.f14319c = false;
            }
            irl irl3 = (irl) g.f14318b;
            irl3.f14869a |= 4;
            irl3.f14872d = longValue2;
        }
        if (fme.f10022d != null) {
            long longValue3 = fme.f10022d.longValue();
            if (g.f14319c) {
                g.mo8751b();
                g.f14319c = false;
            }
            irl irl4 = (irl) g.f14318b;
            irl4.f14869a |= 8;
            irl4.f14873e = longValue3;
        }
        return (irl) g.mo8770g();
    }

    /* JADX WARNING: Removed duplicated region for block: B:39:0x0099  */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x009c  */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x00a2  */
    /* renamed from: b */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void mo5742b(android.app.Activity r28) {
        /*
            r27 = this;
            r7 = r27
            fid r0 = r7.f10048a
            r0.mo5748b(r7)
            fmj r8 = p000.fmj.f10033a
            long r0 = r8.f10040f
            java.lang.String r2 = "PrimesStartupHandler"
            r9 = 0
            r11 = 0
            int r3 = (r0 > r9 ? 1 : (r0 == r9 ? 0 : -1))
            if (r3 <= 0) goto L_0x051e
            hqk r0 = r7.f10051d
            java.lang.Object r0 = r0.mo2652a()
            flo r0 = (p000.flo) r0
            r12 = 1
            if (r0 == 0) goto L_0x0028
            boolean r1 = r0.mo5778a()
            if (r1 == 0) goto L_0x0027
            r13 = 1
            goto L_0x0029
        L_0x0027:
        L_0x0028:
            r13 = 0
        L_0x0029:
            if (r13 == 0) goto L_0x0034
            boolean r1 = r0.mo5779b()
            if (r1 == 0) goto L_0x0033
            r14 = 1
            goto L_0x0035
        L_0x0033:
        L_0x0034:
            r14 = 0
        L_0x0035:
            if (r14 == 0) goto L_0x0040
            boolean r0 = r0.mo5780c()
            if (r0 == 0) goto L_0x003f
            r15 = 1
            goto L_0x0041
        L_0x003f:
        L_0x0040:
            r15 = 0
        L_0x0041:
            java.lang.String r16 = "Warm startup"
            java.lang.String r17 = "Cold startup"
            if (r13 != 0) goto L_0x00c2
            long r18 = m9215a((p000.fmj) r8)
            hqk r0 = r7.f10049b
            java.lang.Object r0 = r0.mo2652a()
            if (r0 != 0) goto L_0x0055
            goto L_0x00b5
        L_0x0055:
            int r0 = (r18 > r9 ? 1 : (r18 == r9 ? 0 : -1))
            if (r0 <= 0) goto L_0x00b5
            long r4 = r8.f10040f
            boolean r6 = r8.f10036b
            if (r6 != 0) goto L_0x0062
            r20 = r16
            goto L_0x0064
        L_0x0062:
            r20 = r17
        L_0x0064:
            r0 = r27
            r1 = r8
            r2 = r18
            r21 = r6
            r6 = r20
            r0.m9217a(r1, r2, r4, r6)
            long r4 = r8.f10041g
            long r0 = r8.f10041g
            long r2 = r8.f10040f
            int r6 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r6 < 0) goto L_0x0083
            if (r21 != 0) goto L_0x0080
            java.lang.String r0 = "Warm startup interactive"
            r6 = r0
            goto L_0x008b
        L_0x0080:
            java.lang.String r0 = "Cold startup interactive"
            goto L_0x008a
        L_0x0083:
            if (r21 == 0) goto L_0x0088
            java.lang.String r0 = "Cold startup interactive before onDraw"
            goto L_0x008a
        L_0x0088:
            java.lang.String r0 = "Warm startup interactive before onDraw"
        L_0x008a:
            r6 = r0
        L_0x008b:
            r0 = r27
            r1 = r8
            r2 = r18
            r0.m9217a(r1, r2, r4, r6)
            fme r0 = r8.f10044j
            java.lang.Long r0 = r0.f10021c
            if (r0 == 0) goto L_0x009c
            fme r0 = r8.f10044j
            goto L_0x009e
        L_0x009c:
            fme r0 = r8.f10043i
        L_0x009e:
            java.lang.Long r1 = r0.f10021c
            if (r1 == 0) goto L_0x00c2
            java.lang.Long r0 = r0.f10021c
            long r2 = r0.longValue()
            long r4 = r8.f10040f
            if (r21 != 0) goto L_0x00c2
            java.lang.String r6 = "Warm startup activity onStart"
            r0 = r27
            r1 = r8
            r0.m9217a(r1, r2, r4, r6)
            goto L_0x00c2
        L_0x00b5:
            java.lang.Long[] r0 = new java.lang.Long[r12]
            java.lang.Long r1 = java.lang.Long.valueOf(r18)
            r0[r11] = r1
            java.lang.String r1 = "not recording startup timer (start time: %d)"
            p000.flw.m9201c(r2, r1, r0)
        L_0x00c2:
            hqk r0 = r7.f10050c
            java.lang.Object r0 = r0.mo2652a()
            hpy r0 = (p000.hpy) r0
            boolean r0 = r0.mo7646a()
            if (r0 == 0) goto L_0x051d
            long r0 = m9215a((p000.fmj) r8)
            int r2 = (r0 > r9 ? 1 : (r0 == r9 ? 0 : -1))
            if (r2 <= 0) goto L_0x051d
            long r0 = m9215a((p000.fmj) r8)
            long r2 = r8.f10040f
            int r4 = (r2 > r0 ? 1 : (r2 == r0 ? 0 : -1))
            if (r4 < 0) goto L_0x051d
            if (r13 != 0) goto L_0x0315
            android.os.Looper r2 = android.os.Looper.getMainLooper()
            java.lang.Thread r2 = r2.getThread()
            long r2 = r2.getId()
            java.util.ArrayList r4 = new java.util.ArrayList
            r4.<init>()
            boolean r5 = r8.f10036b
            fme r6 = r8.f10043i
            java.lang.Long r13 = r6.f10020b
            if (r13 != 0) goto L_0x00ff
            goto L_0x024f
        L_0x00ff:
            java.lang.String r13 = r6.f10019a
            if (r13 == 0) goto L_0x024f
            java.lang.String r13 = ": onCreate"
            if (r5 == 0) goto L_0x0177
            fmt r18 = p000.fmt.f10065a
            long r14 = r8.f10037c
            long r0 = r8.f10038d
            r26 = 3
            java.lang.String r19 = "App create"
            r20 = r14
            r22 = r0
            r24 = r2
            fos r0 = p000.fos.m9330a(r18, r19, r20, r22, r24, r26)
            r4.add(r0)
            fmt r18 = p000.fmt.f10065a
            java.lang.String r0 = r6.f10019a
            java.lang.String r0 = java.lang.String.valueOf(r0)
            java.lang.String r19 = r0.concat(r13)
            long r0 = r8.f10038d
            java.lang.Long r14 = r6.f10020b
            long r22 = r14.longValue()
            r20 = r0
            fos r0 = p000.fos.m9330a(r18, r19, r20, r22, r24, r26)
            r4.add(r0)
            long r14 = r8.f10039e
            int r1 = (r14 > r9 ? 1 : (r14 == r9 ? 0 : -1))
            if (r1 <= 0) goto L_0x0198
            fmt r1 = p000.fmt.f10065a
            fmt r18 = p000.fmt.f10065a
            java.lang.String r14 = r6.f10019a
            java.lang.String r14 = java.lang.String.valueOf(r14)
            java.lang.String r15 = ": init"
            java.lang.String r19 = r14.concat(r15)
            long r14 = r8.f10038d
            long r9 = r8.f10039e
            r26 = 3
            r20 = r14
            r22 = r9
            r24 = r2
            fos r9 = p000.fos.m9330a(r18, r19, r20, r22, r24, r26)
            p000.ife.m12898e((java.lang.Object) r1)
            java.util.List r1 = r0.f10168e
            java.util.List r10 = java.util.Collections.EMPTY_LIST
            if (r1 != r10) goto L_0x0171
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            r0.f10168e = r1
        L_0x0171:
            java.util.List r0 = r0.f10168e
            r0.add(r9)
            goto L_0x0198
        L_0x0177:
            fmt r18 = p000.fmt.f10065a
            java.lang.String r0 = r6.f10019a
            java.lang.String r0 = java.lang.String.valueOf(r0)
            java.lang.String r19 = r0.concat(r13)
            long r0 = r8.f10039e
            java.lang.Long r9 = r6.f10020b
            long r22 = r9.longValue()
            r26 = 3
            r20 = r0
            r24 = r2
            fos r0 = p000.fos.m9330a(r18, r19, r20, r22, r24, r26)
            r4.add(r0)
        L_0x0198:
            fme r0 = r8.f10044j
            java.lang.Long r1 = r0.f10020b
            if (r1 == 0) goto L_0x01c5
            java.lang.String r1 = r0.f10019a
            if (r1 == 0) goto L_0x01c5
            java.lang.Long r1 = r6.f10020b
            long r20 = r1.longValue()
            java.lang.Long r1 = r0.f10020b
            long r22 = r1.longValue()
            fmt r18 = p000.fmt.f10065a
            java.lang.String r1 = r0.f10019a
            java.lang.String r1 = java.lang.String.valueOf(r1)
            java.lang.String r19 = r1.concat(r13)
            r26 = 3
            r24 = r2
            fos r1 = p000.fos.m9330a(r18, r19, r20, r22, r24, r26)
            r4.add(r1)
        L_0x01c5:
            java.lang.Long r1 = r0.f10020b
            if (r1 == 0) goto L_0x01ca
            r6 = r0
        L_0x01ca:
            java.lang.Long r0 = r6.f10020b
            if (r0 == 0) goto L_0x024f
            java.lang.String r0 = r6.f10019a
            if (r0 == 0) goto L_0x024f
            java.lang.Long r0 = r6.f10020b
            long r20 = r0.longValue()
            java.lang.Long r0 = r6.f10021c
            if (r0 == 0) goto L_0x01fb
            fmt r18 = p000.fmt.f10065a
            java.lang.String r0 = r6.f10019a
            java.lang.String r0 = java.lang.String.valueOf(r0)
            java.lang.String r1 = ": onStart"
            java.lang.String r19 = r0.concat(r1)
            java.lang.Long r0 = r6.f10021c
            long r22 = r0.longValue()
            r26 = 3
            r24 = r2
            fos r0 = p000.fos.m9330a(r18, r19, r20, r22, r24, r26)
            r4.add(r0)
        L_0x01fb:
            java.lang.Long r0 = r6.f10021c
            if (r0 == 0) goto L_0x0228
            java.lang.Long r0 = r6.f10022d
            if (r0 == 0) goto L_0x0228
            fmt r18 = p000.fmt.f10065a
            java.lang.String r0 = r6.f10019a
            java.lang.String r0 = java.lang.String.valueOf(r0)
            java.lang.String r1 = ": onResume"
            java.lang.String r19 = r0.concat(r1)
            java.lang.Long r0 = r6.f10021c
            long r20 = r0.longValue()
            java.lang.Long r0 = r6.f10022d
            long r22 = r0.longValue()
            r26 = 3
            r24 = r2
            fos r0 = p000.fos.m9330a(r18, r19, r20, r22, r24, r26)
            r4.add(r0)
        L_0x0228:
            java.lang.Long r0 = r6.f10022d
            if (r0 == 0) goto L_0x024f
            fmt r18 = p000.fmt.f10065a
            java.lang.String r0 = r6.f10019a
            java.lang.String r0 = java.lang.String.valueOf(r0)
            java.lang.String r1 = ": onDraw"
            java.lang.String r19 = r0.concat(r1)
            java.lang.Long r0 = r6.f10022d
            long r20 = r0.longValue()
            long r0 = r8.f10040f
            r26 = 3
            r22 = r0
            r24 = r2
            fos r0 = p000.fos.m9330a(r18, r19, r20, r22, r24, r26)
            r4.add(r0)
        L_0x024f:
            fmt r18 = p000.fmt.f10065a
            boolean r0 = r8.f10036b
            if (r0 != 0) goto L_0x0258
            r19 = r16
            goto L_0x025a
        L_0x0258:
            r19 = r17
        L_0x025a:
            java.lang.Object r0 = r4.get(r11)
            fos r0 = (p000.fos) r0
            long r0 = r0.f10165b
            r22 = -1
            r26 = 2
            r20 = r0
            r24 = r2
            fos r0 = p000.fos.m9330a(r18, r19, r20, r22, r24, r26)
            fmt r1 = p000.fmt.f10065a
            p000.ife.m12898e((java.lang.Object) r1)
            java.util.List r1 = r0.f10168e
            java.util.List r2 = java.util.Collections.EMPTY_LIST
            if (r1 != r2) goto L_0x0281
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            r0.f10168e = r1
        L_0x0281:
            java.util.List r1 = r0.f10168e
            r1.addAll(r4)
            fmt r1 = p000.fmt.f10065a
            p000.ife.m12898e((java.lang.Object) r1)
            fot r1 = new fot
            r1.<init>(r0)
            fmt r0 = p000.fmt.f10065a
            p000.ife.m12898e((java.lang.Object) r0)
            fos r0 = r1.f10171a
            r2 = 0
            r1.mo6010a(r0, r2)
            java.util.List r0 = r1.f10172b
            int r0 = r0.size()
            if (r0 != r12) goto L_0x02af
            java.lang.Object[] r0 = new java.lang.Object[r11]
            java.lang.String r1 = "TraceDataToProto"
            java.lang.String r2 = "No other span except for root span. Dropping trace..."
            p000.flw.m9199b(r1, r2, r0)
            r0 = 0
            goto L_0x02bd
        L_0x02af:
            java.util.List r0 = r1.f10172b
            int r1 = r0.size()
            irk[] r1 = new p000.irk[r1]
            java.lang.Object[] r0 = r0.toArray(r1)
            irk[] r0 = (p000.irk[]) r0
        L_0x02bd:
            iri r1 = p000.iri.f14843h
            iir r1 = r1.mo8793g()
            java.util.UUID r2 = java.util.UUID.randomUUID()
            long r2 = r2.getLeastSignificantBits()
            boolean r4 = r1.f14319c
            if (r4 == 0) goto L_0x02d4
            r1.mo8751b()
            r1.f14319c = r11
        L_0x02d4:
            iix r4 = r1.f14318b
            iri r4 = (p000.iri) r4
            int r6 = r4.f14845a
            r6 = r6 | r12
            r4.f14845a = r6
            r4.f14846b = r2
            java.util.List r0 = java.util.Arrays.asList(r0)
            boolean r2 = r1.f14319c
            if (r2 == 0) goto L_0x02ec
            r1.mo8751b()
            r1.f14319c = r11
        L_0x02ec:
            iix r2 = r1.f14318b
            iri r2 = (p000.iri) r2
            r2.mo9068a()
            ije r2 = r2.f14848d
            p000.igz.m12986a((java.lang.Iterable) r0, (java.util.List) r2)
            boolean r0 = r1.f14319c
            if (r0 == 0) goto L_0x0301
            r1.mo8751b()
            r1.f14319c = r11
        L_0x0301:
            iix r0 = r1.f14318b
            iri r0 = (p000.iri) r0
            r2 = 2
            r0.f14847c = r2
            int r3 = r0.f14845a
            r3 = r3 | r2
            r0.f14845a = r3
            iix r0 = r1.mo8770g()
            iri r0 = (p000.iri) r0
            goto L_0x04a4
        L_0x0315:
            irm r0 = p000.irm.f14874k
            iir r0 = r0.mo8793g()
            boolean r1 = r8.f10036b
            boolean r2 = r0.f14319c
            if (r2 != 0) goto L_0x0322
            goto L_0x0327
        L_0x0322:
            r0.mo8751b()
            r0.f14319c = r11
        L_0x0327:
            iix r2 = r0.f14318b
            irm r2 = (p000.irm) r2
            int r3 = r2.f14876a
            r3 = r3 | 128(0x80, float:1.794E-43)
            r2.f14876a = r3
            r2.f14883h = r1
            fmi r1 = r8.f10042h
            boolean r2 = r1.f10028a
            if (r2 == 0) goto L_0x0351
            long r2 = r8.f10037c
            boolean r4 = r0.f14319c
            if (r4 != 0) goto L_0x0340
            goto L_0x0345
        L_0x0340:
            r0.mo8751b()
            r0.f14319c = r11
        L_0x0345:
            iix r4 = r0.f14318b
            irm r4 = (p000.irm) r4
            int r6 = r4.f14876a
            r6 = r6 | 4
            r4.f14876a = r6
            r4.f14878c = r2
        L_0x0351:
            boolean r2 = r1.f10029b
            if (r2 == 0) goto L_0x036d
            long r2 = r8.f10038d
            boolean r4 = r0.f14319c
            if (r4 != 0) goto L_0x035c
            goto L_0x0361
        L_0x035c:
            r0.mo8751b()
            r0.f14319c = r11
        L_0x0361:
            iix r4 = r0.f14318b
            irm r4 = (p000.irm) r4
            int r6 = r4.f14876a
            r6 = r6 | 8
            r4.f14876a = r6
            r4.f14879d = r2
        L_0x036d:
            boolean r2 = r1.f10030c
            if (r2 == 0) goto L_0x0389
            long r2 = r8.f10039e
            boolean r4 = r0.f14319c
            if (r4 != 0) goto L_0x0378
            goto L_0x037d
        L_0x0378:
            r0.mo8751b()
            r0.f14319c = r11
        L_0x037d:
            iix r4 = r0.f14318b
            irm r4 = (p000.irm) r4
            int r6 = r4.f14876a
            r6 = r6 | 16
            r4.f14876a = r6
            r4.f14880e = r2
        L_0x0389:
            boolean r2 = r1.f10031d
            if (r2 == 0) goto L_0x03a5
            long r2 = r8.f10040f
            boolean r4 = r0.f14319c
            if (r4 != 0) goto L_0x0394
            goto L_0x0399
        L_0x0394:
            r0.mo8751b()
            r0.f14319c = r11
        L_0x0399:
            iix r4 = r0.f14318b
            irm r4 = (p000.irm) r4
            int r6 = r4.f14876a
            r6 = r6 | 32
            r4.f14876a = r6
            r4.f14881f = r2
        L_0x03a5:
            boolean r1 = r1.f10032e
            if (r1 == 0) goto L_0x03c1
            long r1 = r8.f10041g
            boolean r3 = r0.f14319c
            if (r3 != 0) goto L_0x03b0
            goto L_0x03b5
        L_0x03b0:
            r0.mo8751b()
            r0.f14319c = r11
        L_0x03b5:
            iix r3 = r0.f14318b
            irm r3 = (p000.irm) r3
            int r4 = r3.f14876a
            r4 = r4 | 64
            r3.f14876a = r4
            r3.f14882g = r1
        L_0x03c1:
            fme r1 = r8.f10043i
            java.lang.Long r1 = r1.f10020b
            if (r1 == 0) goto L_0x03e5
            fme r1 = r8.f10043i
            irl r1 = m9216a((p000.fme) r1)
            boolean r2 = r0.f14319c
            if (r2 == 0) goto L_0x03d6
            r0.mo8751b()
            r0.f14319c = r11
        L_0x03d6:
            iix r2 = r0.f14318b
            irm r2 = (p000.irm) r2
            r1.getClass()
            r2.f14884i = r1
            int r1 = r2.f14876a
            r1 = r1 | 256(0x100, float:3.59E-43)
            r2.f14876a = r1
        L_0x03e5:
            fme r1 = r8.f10044j
            java.lang.Long r1 = r1.f10020b
            if (r1 == 0) goto L_0x0409
            fme r1 = r8.f10044j
            irl r1 = m9216a((p000.fme) r1)
            boolean r2 = r0.f14319c
            if (r2 == 0) goto L_0x03fa
            r0.mo8751b()
            r0.f14319c = r11
        L_0x03fa:
            iix r2 = r0.f14318b
            irm r2 = (p000.irm) r2
            r1.getClass()
            r2.f14885j = r1
            int r1 = r2.f14876a
            r1 = r1 | 512(0x200, float:7.175E-43)
            r2.f14876a = r1
        L_0x0409:
            if (r14 != 0) goto L_0x040c
            goto L_0x0457
        L_0x040c:
            if (r15 == 0) goto L_0x042a
            int r1 = android.os.Build.VERSION.SDK_INT
            long r1 = android.os.Process.getStartElapsedRealtime()
            boolean r3 = r0.f14319c
            if (r3 == 0) goto L_0x041d
            r0.mo8751b()
            r0.f14319c = r11
        L_0x041d:
            iix r3 = r0.f14318b
            irm r3 = (p000.irm) r3
            int r4 = r3.f14876a
            r5 = 2
            r4 = r4 | r5
            r3.f14876a = r4
            r3.f14877b = r1
            goto L_0x0457
        L_0x042a:
            hqk r1 = p000.fmz.f10072a
            java.lang.Object r1 = r1.mo2652a()
            hpy r1 = (p000.hpy) r1
            boolean r2 = r1.mo7646a()
            if (r2 == 0) goto L_0x0457
            java.lang.Object r1 = r1.mo7647b()
            java.lang.Long r1 = (java.lang.Long) r1
            long r1 = r1.longValue()
            boolean r3 = r0.f14319c
            if (r3 == 0) goto L_0x044b
            r0.mo8751b()
            r0.f14319c = r11
        L_0x044b:
            iix r3 = r0.f14318b
            irm r3 = (p000.irm) r3
            int r4 = r3.f14876a
            r5 = 2
            r4 = r4 | r5
            r3.f14876a = r4
            r3.f14877b = r1
        L_0x0457:
            iri r1 = p000.iri.f14843h
            iir r1 = r1.mo8793g()
            java.util.UUID r2 = java.util.UUID.randomUUID()
            long r2 = r2.getLeastSignificantBits()
            boolean r4 = r1.f14319c
            if (r4 == 0) goto L_0x046e
            r1.mo8751b()
            r1.f14319c = r11
        L_0x046e:
            iix r4 = r1.f14318b
            iri r4 = (p000.iri) r4
            int r6 = r4.f14845a
            r6 = r6 | r12
            r4.f14845a = r6
            r4.f14846b = r2
            r2 = 2
            r4.f14847c = r2
            r3 = r6 | 2
            r4.f14845a = r3
            iix r0 = r0.mo8770g()
            irm r0 = (p000.irm) r0
            boolean r2 = r1.f14319c
            if (r2 == 0) goto L_0x048f
            r1.mo8751b()
            r1.f14319c = r11
        L_0x048f:
            iix r2 = r1.f14318b
            iri r2 = (p000.iri) r2
            r0.getClass()
            r2.f14851g = r0
            int r0 = r2.f14845a
            r0 = r0 | 16
            r2.f14845a = r0
            iix r0 = r1.mo8770g()
            iri r0 = (p000.iri) r0
        L_0x04a4:
            hqk r1 = r7.f10050c
            java.lang.Object r1 = r1.mo2652a()
            hpy r1 = (p000.hpy) r1
            java.lang.Object r1 = r1.mo7647b()
            r13 = r1
            fnh r13 = (p000.fnh) r13
            r8.mo5970a()
            r1 = 0
            java.lang.String r18 = p000.fjy.m9052a(r1)
            isc r2 = p000.isc.f14974r
            iir r2 = r2.mo8793g()
            boolean r3 = r2.f14319c
            if (r3 != 0) goto L_0x04c6
            goto L_0x04cb
        L_0x04c6:
            r2.mo8751b()
            r2.f14319c = r11
        L_0x04cb:
            iix r3 = r2.f14318b
            isc r3 = (p000.isc) r3
            r0.getClass()
            r3.f14989n = r0
            int r4 = r3.f14976a
            r6 = 32768(0x8000, float:4.5918E-41)
            r4 = r4 | r6
            r3.f14976a = r4
            iix r2 = r2.mo8770g()
            r16 = r2
            isc r16 = (p000.isc) r16
            r2 = 2
            java.io.Serializable[] r2 = new java.io.Serializable[r2]
            int r3 = r0.f14845a
            r3 = r3 & r12
            if (r3 == 0) goto L_0x04f3
            long r3 = r0.f14846b
            java.lang.Long r3 = java.lang.Long.valueOf(r3)
            goto L_0x04f5
        L_0x04f3:
            r3 = r1
        L_0x04f5:
            r2[r11] = r3
            ije r3 = r0.f14848d
            int r3 = r3.size()
            if (r3 <= 0) goto L_0x050a
            ije r0 = r0.f14848d
            java.lang.Object r0 = r0.get(r11)
            irk r0 = (p000.irk) r0
            java.lang.String r0 = r0.f14856b
            goto L_0x050c
        L_0x050a:
            r0 = r1
        L_0x050c:
            r2[r12] = r0
            java.lang.String r0 = "BaseTraceMetricService"
            java.lang.String r1 = "Recording trace %d: %s"
            p000.flw.m9199b(r0, r1, r2)
            r14 = 0
            r15 = 1
            r17 = 0
            r13.mo5730a(r14, r15, r16, r17, r18)
            return
        L_0x051d:
            return
        L_0x051e:
            java.lang.Object[] r0 = new java.lang.Object[r11]
            java.lang.String r1 = "missing firstDraw timestamp"
            p000.flw.m9201c(r2, r1, r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.fmk.mo5742b(android.app.Activity):void");
    }

    /* renamed from: a */
    public final void mo5727a() {
        this.f10048a.mo5748b(this);
    }

    /* renamed from: a */
    private final void m9217a(fmj fmj, long j, long j2, String str) {
        if (j2 >= j) {
            fnc fnc = new fnc(j, j2);
            fmj.mo5970a();
            flw.m9191a(((fnd) this.f10049b.mo2652a()).mo5892a(fnc, str, fjy.m9052a((fjy) null)));
            return;
        }
        flw.m9201c("PrimesStartupHandler", "non-positive duration for startup timer %s", str);
    }
}
