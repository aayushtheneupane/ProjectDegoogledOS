package p000;

import java.util.concurrent.Callable;
import p003j$.util.Optional;

/* renamed from: dce */
/* compiled from: PG */
final /* synthetic */ class dce implements Callable {

    /* renamed from: a */
    private final Optional f6236a;

    /* renamed from: b */
    private final dbo f6237b;

    /* renamed from: c */
    private final ebi f6238c;

    /* renamed from: d */
    private final ddj f6239d;

    /* renamed from: e */
    private final boolean f6240e;

    public dce(Optional optional, dbo dbo, ebi ebi, ddj ddj, boolean z) {
        this.f6236a = optional;
        this.f6237b = dbo;
        this.f6238c = ebi;
        this.f6239d = ddj;
        this.f6240e = z;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:339:0x066a, code lost:
        r3 = r27;
     */
    /* JADX WARNING: Removed duplicated region for block: B:130:0x023d A[Catch:{ IOException -> 0x080a }] */
    /* JADX WARNING: Removed duplicated region for block: B:166:0x02f9 A[SYNTHETIC, Splitter:B:166:0x02f9] */
    /* JADX WARNING: Removed duplicated region for block: B:176:0x031f A[Catch:{ ang -> 0x0661 }] */
    /* JADX WARNING: Removed duplicated region for block: B:177:0x0327 A[Catch:{ ang -> 0x0661 }] */
    /* JADX WARNING: Removed duplicated region for block: B:282:0x0521 A[Catch:{ ang -> 0x054f }] */
    /* JADX WARNING: Removed duplicated region for block: B:283:0x052e A[Catch:{ ang -> 0x054f }] */
    /* JADX WARNING: Removed duplicated region for block: B:296:0x0576 A[Catch:{ ang -> 0x05d9 }] */
    /* JADX WARNING: Removed duplicated region for block: B:312:0x05f3  */
    /* JADX WARNING: Removed duplicated region for block: B:315:0x05fc A[Catch:{ ang -> 0x065c }] */
    /* JADX WARNING: Removed duplicated region for block: B:318:0x0606 A[Catch:{ ang -> 0x065c }] */
    /* JADX WARNING: Removed duplicated region for block: B:319:0x0607 A[Catch:{ ang -> 0x065c }] */
    /* JADX WARNING: Removed duplicated region for block: B:338:0x0669 A[ExcHandler: IOException (e java.io.IOException), Splitter:B:150:0x0296] */
    /* JADX WARNING: Removed duplicated region for block: B:354:0x06be A[Catch:{ IOException -> 0x0804 }] */
    /* JADX WARNING: Removed duplicated region for block: B:356:0x06c7  */
    /* JADX WARNING: Removed duplicated region for block: B:428:0x07f2 A[ExcHandler: IOException (e java.io.IOException), Splitter:B:365:0x06e7] */
    /* JADX WARNING: Removed duplicated region for block: B:445:0x081e A[Catch:{ all -> 0x0881 }] */
    /* JADX WARNING: Removed duplicated region for block: B:446:0x081f A[Catch:{ all -> 0x0881 }] */
    /* JADX WARNING: Removed duplicated region for block: B:449:0x084e A[Catch:{ all -> 0x0881 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object call() {
        /*
            r46 = this;
            r1 = r46
            java.lang.String r2 = "Directory"
            java.lang.String r3 = "http://ns.google.com/photos/1.0/container/"
            java.lang.String r4 = "MotionPhotoPresentationTimestampUs"
            java.lang.String r5 = "MicroVideoVersion"
            java.lang.String r6 = "MotionPhoto"
            java.lang.String r7 = "MicroVideoPresentationTimestampUs"
            java.lang.String r8 = "MicroVideoOffset"
            java.lang.String r9 = "BurstPrimary"
            java.lang.String r10 = "MicroVideo"
            java.lang.String r11 = "BurstID"
            java.lang.String r12 = "SpecialTypeID"
            java.lang.String r13 = "DisableAutoCreation"
            java.lang.String r14 = "http://ns.google.com/photos/1.0/camera/"
            j$.util.Optional r15 = r1.f6236a
            r16 = r13
            dbo r13 = r1.f6237b
            r17 = r2
            ebi r2 = r1.f6238c
            r18 = r3
            ddj r3 = r1.f6239d
            r19 = r4
            boolean r4 = r1.f6240e
            boolean r20 = r15.isPresent()
            if (r20 == 0) goto L_0x08a4
            java.lang.String r13 = r13.mo3205b()
            java.lang.String r1 = "image/jpeg"
            boolean r1 = r1.equals(r13)
            if (r1 == 0) goto L_0x08a4
            java.lang.Object r1 = r15.get()
            cyd r1 = (p000.cyd) r1
            java.lang.String r1 = r1.f5988b
            android.net.Uri r1 = android.net.Uri.parse(r1)
            java.io.BufferedInputStream r13 = new java.io.BufferedInputStream     // Catch:{ IOException -> 0x0895 }
            java.io.InputStream r2 = r2.mo4664a(r1)     // Catch:{ IOException -> 0x0895 }
            r13.<init>(r2)     // Catch:{ IOException -> 0x0895 }
            java.lang.Object r2 = r15.get()     // Catch:{ all -> 0x0883 }
            cyd r2 = (p000.cyd) r2     // Catch:{ all -> 0x0883 }
            ehf r2 = r2.f5994h     // Catch:{ all -> 0x0883 }
            if (r2 == 0) goto L_0x0060
            goto L_0x0062
        L_0x0060:
            ehf r2 = p000.ehf.f8283d     // Catch:{ all -> 0x0883 }
        L_0x0062:
            r3.f6342c = r13     // Catch:{ all -> 0x0883 }
            r15 = 1
            r3.f6343d = r15     // Catch:{ all -> 0x0883 }
            r15 = r6
            r21 = r7
            long r6 = r2.f8286b     // Catch:{ all -> 0x0883 }
            r22 = r8
            r23 = r9
            long r8 = r2.f8287c     // Catch:{ all -> 0x0883 }
            long r6 = r6 - r8
            java.lang.Long r6 = java.lang.Long.valueOf(r6)     // Catch:{ all -> 0x0883 }
            r3.f6346g = r6     // Catch:{ all -> 0x0883 }
            long r6 = r2.f8287c     // Catch:{ all -> 0x0883 }
            r8 = -2147483648(0xffffffff80000000, double:NaN)
            int r2 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
            if (r2 <= 0) goto L_0x008d
            r8 = 2147483647(0x7fffffff, double:1.060997895E-314)
            int r2 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
            if (r2 >= 0) goto L_0x008b
            r2 = 1
            goto L_0x008e
        L_0x008b:
        L_0x008d:
            r2 = 0
        L_0x008e:
            java.lang.String r8 = "Invalid timezone offset"
            p000.ife.m12876b((boolean) r2, (java.lang.Object) r8)     // Catch:{ all -> 0x0883 }
            int r2 = (int) r6     // Catch:{ all -> 0x0883 }
            java.lang.String[] r2 = java.util.TimeZone.getAvailableIDs(r2)     // Catch:{ all -> 0x0883 }
            int r6 = r2.length     // Catch:{ all -> 0x0883 }
            if (r6 <= 0) goto L_0x00a3
            r6 = 0
            r2 = r2[r6]     // Catch:{ all -> 0x0883 }
            java.util.TimeZone r2 = p003j$.util.DesugarTimeZone.getTimeZone(r2)     // Catch:{ all -> 0x0883 }
            goto L_0x00a7
        L_0x00a3:
            java.util.TimeZone r2 = java.util.TimeZone.getDefault()     // Catch:{ all -> 0x0883 }
        L_0x00a7:
            r3.f6347h = r2     // Catch:{ all -> 0x0883 }
            if (r4 != 0) goto L_0x00b6
            r2 = 1
            r3.f6348i = r2     // Catch:{ all -> 0x0883 }
            r3.f6344e = r2     // Catch:{ all -> 0x0883 }
            r3.f6350k = r2     // Catch:{ all -> 0x0883 }
            r3.f6349j = r2     // Catch:{ all -> 0x0883 }
            r3.f6351l = r2     // Catch:{ all -> 0x0883 }
        L_0x00b6:
            fsc r2 = new fsc     // Catch:{ all -> 0x0883 }
            r2.<init>()     // Catch:{ all -> 0x0883 }
            java.io.InputStream r4 = r3.f6342c     // Catch:{ all -> 0x0883 }
            if (r4 != 0) goto L_0x00c5
            r19 = r1
            r17 = r13
            goto L_0x0816
        L_0x00c5:
            r2.mo6106a((java.io.InputStream) r4)     // Catch:{ IOException -> 0x0811 }
            boolean r4 = r3.f6343d     // Catch:{ IOException -> 0x0811 }
            r6 = 0
            if (r4 == 0) goto L_0x00d1
            fsd r4 = r2.f10476s     // Catch:{ IOException -> 0x0811 }
            r4.f10478b = r6     // Catch:{ IOException -> 0x0811 }
        L_0x00d1:
            boolean r4 = r3.f6344e     // Catch:{ IOException -> 0x0811 }
            if (r4 == 0) goto L_0x0132
            int r4 = p000.fsc.f10450c     // Catch:{ IOException -> 0x0811 }
            short r4 = (short) r4     // Catch:{ IOException -> 0x0811 }
            fsd r7 = r2.f10476s     // Catch:{ IOException -> 0x0811 }
            java.util.ArrayList r8 = new java.util.ArrayList     // Catch:{ IOException -> 0x0811 }
            r8.<init>()     // Catch:{ IOException -> 0x0811 }
            fsm[] r7 = r7.f10477a     // Catch:{ IOException -> 0x0811 }
            int r9 = r7.length     // Catch:{ IOException -> 0x0811 }
            r6 = 0
        L_0x00e3:
            if (r6 >= r9) goto L_0x00fa
            r24 = r9
            r9 = r7[r6]     // Catch:{ IOException -> 0x0811 }
            if (r9 != 0) goto L_0x00ec
            goto L_0x00f5
        L_0x00ec:
            fsl r9 = r9.mo6147a((short) r4)     // Catch:{ IOException -> 0x0811 }
            if (r9 == 0) goto L_0x00f5
            r8.add(r9)     // Catch:{ IOException -> 0x0811 }
        L_0x00f5:
            int r6 = r6 + 1
            r9 = r24
            goto L_0x00e3
        L_0x00fa:
            boolean r4 = r8.isEmpty()     // Catch:{ IOException -> 0x0811 }
            if (r4 != 0) goto L_0x0101
            goto L_0x0103
        L_0x0101:
            r8 = 0
        L_0x0103:
            if (r8 != 0) goto L_0x0106
            goto L_0x0124
        L_0x0106:
            java.util.Iterator r4 = r8.iterator()     // Catch:{ IOException -> 0x0811 }
        L_0x010a:
            boolean r6 = r4.hasNext()     // Catch:{ IOException -> 0x0811 }
            if (r6 == 0) goto L_0x0124
            java.lang.Object r6 = r4.next()     // Catch:{ IOException -> 0x0811 }
            fsl r6 = (p000.fsl) r6     // Catch:{ IOException -> 0x0811 }
            short r7 = r6.f10524a     // Catch:{ IOException -> 0x0811 }
            int r6 = r6.f10528e     // Catch:{ IOException -> 0x0811 }
            fsd r8 = r2.f10476s     // Catch:{ IOException -> 0x0811 }
            short r7 = p000.fsc.m9495a((int) r7)     // Catch:{ IOException -> 0x0811 }
            r8.mo6118a(r7, r6)     // Catch:{ IOException -> 0x0811 }
            goto L_0x010a
        L_0x0124:
            int r4 = p000.fsc.f10450c     // Catch:{ IOException -> 0x0811 }
            r6 = 1
            java.lang.Integer r7 = java.lang.Integer.valueOf(r6)     // Catch:{ IOException -> 0x0811 }
            fsl r4 = r2.mo6103a((int) r4, (java.lang.Object) r7)     // Catch:{ IOException -> 0x0811 }
            r2.mo6105a((p000.fsl) r4)     // Catch:{ IOException -> 0x0811 }
        L_0x0132:
            byte[] r4 = r2.mo6108a()     // Catch:{ IOException -> 0x0811 }
            if (r4 == 0) goto L_0x080c
            byte[] r4 = r2.mo6108a()     // Catch:{ ang -> 0x021a }
            ani r4 = p000.ank.m1171a(r4)     // Catch:{ ang -> 0x021a }
            boolean r6 = r3.f6351l     // Catch:{ ang -> 0x021a }
            if (r6 == 0) goto L_0x0164
            dds r6 = r3.f6340a     // Catch:{ ang -> 0x014f }
            ddu r6 = (p000.ddu) r6     // Catch:{ ang -> 0x014f }
            r6.mo4080a()     // Catch:{ ang -> 0x014f }
            r4.mo1262b(r14, r12)     // Catch:{ ang -> 0x014f }
            goto L_0x0164
        L_0x014f:
            r0 = move-exception
            r4 = r0
        L_0x0151:
            r9 = r15
            r15 = r19
            r8 = r21
            r7 = r22
            r19 = r1
            r1 = r18
            r45 = r17
            r17 = r13
            r13 = r45
            goto L_0x022d
        L_0x0164:
            boolean r6 = r3.f6349j     // Catch:{ ang -> 0x021a }
            if (r6 == 0) goto L_0x0180
            dds r6 = r3.f6340a     // Catch:{ ang -> 0x014f }
            ddu r6 = (p000.ddu) r6     // Catch:{ ang -> 0x014f }
            r6.mo4080a()     // Catch:{ ang -> 0x014f }
            r4.mo1262b(r14, r12)     // Catch:{ ang -> 0x014f }
            r4.mo1262b(r14, r11)     // Catch:{ ang -> 0x014f }
            r6 = r23
            r4.mo1262b(r14, r6)     // Catch:{ ang -> 0x017b }
            goto L_0x0182
        L_0x017b:
            r0 = move-exception
            r4 = r0
            r23 = r6
            goto L_0x0151
        L_0x0180:
            r6 = r23
        L_0x0182:
            boolean r7 = r3.f6350k     // Catch:{ ang -> 0x0216 }
            if (r7 == 0) goto L_0x01e2
            dds r7 = r3.f6340a     // Catch:{ ang -> 0x01cc }
            r8 = r7
            ddu r8 = (p000.ddu) r8     // Catch:{ ang -> 0x01cc }
            r8.mo4080a()     // Catch:{ ang -> 0x01cc }
            ddu r7 = (p000.ddu) r7     // Catch:{ ang -> 0x01cc }
            r7.mo4081b()     // Catch:{ ang -> 0x01cc }
            r4.mo1262b(r14, r10)     // Catch:{ ang -> 0x01cc }
            r4.mo1262b(r14, r5)     // Catch:{ ang -> 0x01cc }
            r7 = r22
            r4.mo1262b(r14, r7)     // Catch:{ ang -> 0x01c5 }
            r8 = r21
            r4.mo1262b(r14, r8)     // Catch:{ ang -> 0x01c0 }
            r9 = r15
            r4.mo1262b(r14, r9)     // Catch:{ ang -> 0x01be }
            r15 = r19
            r4.mo1262b(r14, r15)     // Catch:{ ang -> 0x01bc }
            r19 = r1
            r1 = r18
            r45 = r17
            r17 = r13
            r13 = r45
            r4.mo1262b(r1, r13)     // Catch:{ ang -> 0x01ba }
            goto L_0x01f3
        L_0x01ba:
            r0 = move-exception
            goto L_0x01de
        L_0x01bc:
            r0 = move-exception
            goto L_0x01d4
        L_0x01be:
            r0 = move-exception
            goto L_0x01c2
        L_0x01c0:
            r0 = move-exception
            r9 = r15
        L_0x01c2:
            r15 = r19
            goto L_0x01d4
        L_0x01c5:
            r0 = move-exception
            r9 = r15
            r15 = r19
            r8 = r21
            goto L_0x01d4
        L_0x01cc:
            r0 = move-exception
            r9 = r15
            r15 = r19
            r8 = r21
            r7 = r22
        L_0x01d4:
            r19 = r1
            r1 = r18
            r45 = r17
            r17 = r13
            r13 = r45
        L_0x01de:
            r4 = r0
            r23 = r6
            goto L_0x022d
        L_0x01e2:
            r9 = r15
            r15 = r19
            r8 = r21
            r7 = r22
            r19 = r1
            r1 = r18
            r45 = r17
            r17 = r13
            r13 = r45
        L_0x01f3:
            boolean r18 = p000.ddz.m5977a(r4)     // Catch:{ ang -> 0x0212 }
            if (r18 != 0) goto L_0x01fa
            goto L_0x01fd
        L_0x01fa:
            p000.ddz.m5978b(r4)     // Catch:{ ang -> 0x0212 }
        L_0x01fd:
            r23 = r6
            aoq r6 = new aoq     // Catch:{ ang -> 0x0210 }
            r6.<init>()     // Catch:{ ang -> 0x0210 }
            byte[] r4 = p000.ank.m1172a(r4, r6)     // Catch:{ ang -> 0x0210 }
            r2.mo6107a((byte[]) r4)     // Catch:{ ang -> 0x0210 }
            r21 = r1
            r18 = r15
            goto L_0x0239
        L_0x0210:
            r0 = move-exception
            goto L_0x022c
        L_0x0212:
            r0 = move-exception
            r23 = r6
            goto L_0x022c
        L_0x0216:
            r0 = move-exception
            r23 = r6
            goto L_0x021b
        L_0x021a:
            r0 = move-exception
        L_0x021b:
            r9 = r15
            r15 = r19
            r8 = r21
            r7 = r22
            r19 = r1
            r1 = r18
            r45 = r17
            r17 = r13
            r13 = r45
        L_0x022c:
            r4 = r0
        L_0x022d:
            java.lang.String r6 = "error occurred while parsing XMP"
            r21 = r1
            r18 = r15
            r15 = 0
            java.lang.Object[] r1 = new java.lang.Object[r15]     // Catch:{ IOException -> 0x080a }
            p000.cwn.m5515b((java.lang.Throwable) r4, (java.lang.String) r6, (java.lang.Object[]) r1)     // Catch:{ IOException -> 0x080a }
        L_0x0239:
            boolean r1 = r3.f6348i     // Catch:{ IOException -> 0x080a }
            if (r1 == 0) goto L_0x0816
            dds r1 = r3.f6340a     // Catch:{ IOException -> 0x080a }
            byte[] r4 = r2.mo6108a()     // Catch:{ IOException -> 0x080a }
            java.lang.String r6 = " mime"
            r15 = r1
            ddu r15 = (p000.ddu) r15     // Catch:{ IOException -> 0x080a }
            r15.mo4080a()     // Catch:{ IOException -> 0x080a }
            ani r4 = p000.ank.m1171a(r4)     // Catch:{ ang -> 0x06a0 }
            r15 = r1
            ddu r15 = (p000.ddu) r15     // Catch:{ ang -> 0x06a0 }
            r15.mo4080a()     // Catch:{ ang -> 0x06a0 }
            if (r4 == 0) goto L_0x068e
            ddq r15 = new ddq     // Catch:{ ang -> 0x06a0 }
            r15.<init>()     // Catch:{ ang -> 0x06a0 }
            r22 = r6
            java.lang.String r6 = p000.ddu.m5970a(r4, r14, r11)     // Catch:{ ang -> 0x06a0 }
            r15.f6365b = r6     // Catch:{ ang -> 0x06a0 }
            java.lang.String r6 = p000.ddu.m5970a(r4, r14, r12)     // Catch:{ ang -> 0x06a0 }
            r15.f6364a = r6     // Catch:{ ang -> 0x06a0 }
            r6 = r16
            boolean r16 = r4.mo1264c(r14, r6)     // Catch:{ ang -> 0x067e }
            r24 = r11
            if (r16 != 0) goto L_0x0282
            java.util.ArrayList r16 = new java.util.ArrayList     // Catch:{ ang -> 0x0672 }
            r16.<init>()     // Catch:{ ang -> 0x0672 }
            r29 = r2
            r27 = r3
            r26 = r12
            r28 = r16
            goto L_0x02d3
        L_0x0282:
            java.util.ArrayList r11 = new java.util.ArrayList     // Catch:{ ang -> 0x0672 }
            r11.<init>()     // Catch:{ ang -> 0x0672 }
            r26 = r12
            int r12 = r4.mo1257a(r14, r6)     // Catch:{ ang -> 0x02b8 }
            if (r12 == 0) goto L_0x02b1
            r27 = r3
            r3 = 0
        L_0x0292:
            if (r3 >= r12) goto L_0x02b3
            int r3 = r3 + 1
            aos r28 = r4.mo1260a((java.lang.String) r14, (java.lang.String) r6, (int) r3)     // Catch:{ ang -> 0x02af, IOException -> 0x0669 }
            if (r28 == 0) goto L_0x02aa
            java.lang.Object r28 = r28.mo1293c()     // Catch:{ ang -> 0x02af, IOException -> 0x0669 }
            r29 = r3
            r3 = r28
            java.lang.String r3 = (java.lang.String) r3     // Catch:{ ang -> 0x02af, IOException -> 0x0669 }
            r11.add(r3)     // Catch:{ ang -> 0x02af, IOException -> 0x0669 }
            goto L_0x02ac
        L_0x02aa:
            r29 = r3
        L_0x02ac:
            r3 = r29
            goto L_0x0292
        L_0x02af:
            r0 = move-exception
            goto L_0x02bb
        L_0x02b1:
            r27 = r3
        L_0x02b3:
            r29 = r2
            r28 = r11
            goto L_0x02d3
        L_0x02b8:
            r0 = move-exception
            r27 = r3
        L_0x02bb:
            r3 = r0
            java.lang.String r12 = "Error looking up XMP DisabledAutoCreations property. xmpMeta: %s; namespace: %s; property: %s"
            r29 = r2
            r28 = r11
            r11 = 3
            java.lang.Object[] r2 = new java.lang.Object[r11]     // Catch:{ ang -> 0x0661 }
            r11 = 0
            r2[r11] = r4     // Catch:{ ang -> 0x0661 }
            r11 = 1
            r2[r11] = r14     // Catch:{ ang -> 0x0661 }
            r11 = 2
            r2[r11] = r6     // Catch:{ ang -> 0x0661 }
            p000.cwn.m5511a((java.lang.Throwable) r3, (java.lang.String) r12, (java.lang.Object[]) r2)     // Catch:{ ang -> 0x0661 }
        L_0x02d3:
            java.lang.Object r2 = p000.ife.m12898e((java.lang.Object) r28)     // Catch:{ ang -> 0x0661 }
            java.util.List r2 = (java.util.List) r2     // Catch:{ ang -> 0x0661 }
            java.util.List r2 = java.util.Collections.unmodifiableList(r2)     // Catch:{ ang -> 0x0661 }
            r15.f6372i = r2     // Catch:{ ang -> 0x0661 }
            java.lang.Boolean r2 = p000.ddu.m5972b(r4, r14, r10)     // Catch:{ ang -> 0x0661 }
            r15.f6367d = r2     // Catch:{ ang -> 0x0661 }
            java.lang.Integer r3 = p000.ddu.m5973c(r4, r14, r5)     // Catch:{ ang -> 0x0661 }
            r15.f6368e = r3     // Catch:{ ang -> 0x0661 }
            java.lang.Integer r3 = p000.ddu.m5973c(r4, r14, r7)     // Catch:{ ang -> 0x0661 }
            r15.f6369f = r3     // Catch:{ ang -> 0x0661 }
            java.lang.Long r3 = p000.ddu.m5974d(r4, r14, r8)     // Catch:{ ang -> 0x0661 }
            r15.f6370g = r3     // Catch:{ ang -> 0x0661 }
            if (r2 == 0) goto L_0x0314
            boolean r2 = r2.booleanValue()     // Catch:{ ang -> 0x0308 }
            if (r2 != 0) goto L_0x0300
            goto L_0x0314
        L_0x0300:
            r28 = r6
            r34 = r7
            r32 = r8
            goto L_0x05eb
        L_0x0308:
            r0 = move-exception
            r2 = r0
            r28 = r6
            r34 = r7
            r32 = r8
        L_0x0310:
            r1 = r23
            goto L_0x06b2
        L_0x0314:
            ddu r1 = (p000.ddu) r1     // Catch:{ ang -> 0x0661 }
            r1.mo4081b()     // Catch:{ ang -> 0x0661 }
            java.lang.Boolean r1 = p000.ddu.m5972b(r4, r14, r9)     // Catch:{ ang -> 0x0661 }
            if (r1 != 0) goto L_0x0327
            r28 = r6
            r34 = r7
            r32 = r8
            goto L_0x05eb
        L_0x0327:
            boolean r2 = r1.booleanValue()     // Catch:{ ang -> 0x0661 }
            if (r2 == 0) goto L_0x05e5
            java.util.ArrayList r2 = new java.util.ArrayList     // Catch:{ ang -> 0x05db }
            r2.<init>()     // Catch:{ ang -> 0x05db }
            r3 = r21
            boolean r5 = r4.mo1264c(r3, r13)     // Catch:{ ang -> 0x0551 }
            if (r5 == 0) goto L_0x053f
            aom r5 = new aom     // Catch:{ ang -> 0x0551 }
            r5.<init>()     // Catch:{ ang -> 0x0551 }
            r5.mo1357b()     // Catch:{ ang -> 0x0551 }
            anh r5 = r4.mo1259a((java.lang.String) r3, (java.lang.String) r13, (p000.aom) r5)     // Catch:{ ang -> 0x0551 }
            java.util.ArrayList r11 = new java.util.ArrayList     // Catch:{ ang -> 0x0551 }
            r11.<init>()     // Catch:{ ang -> 0x0551 }
        L_0x034b:
            boolean r12 = r5.hasNext()     // Catch:{ ang -> 0x0551 }
            if (r12 == 0) goto L_0x0369
            java.lang.Object r12 = r5.next()     // Catch:{ ang -> 0x035f }
            aot r12 = (p000.aot) r12     // Catch:{ ang -> 0x035f }
            java.lang.String r12 = r12.mo1292b()     // Catch:{ ang -> 0x035f }
            r11.add(r12)     // Catch:{ ang -> 0x035f }
            goto L_0x034b
        L_0x035f:
            r0 = move-exception
            r5 = r0
            r28 = r6
            r34 = r7
            r32 = r8
            goto L_0x0559
        L_0x0369:
            java.util.Iterator r5 = r11.iterator()     // Catch:{ ang -> 0x0551 }
        L_0x036d:
            boolean r11 = r5.hasNext()     // Catch:{ ang -> 0x0551 }
            if (r11 == 0) goto L_0x0538
            java.lang.Object r11 = r5.next()     // Catch:{ ang -> 0x0551 }
            java.lang.String r11 = (java.lang.String) r11     // Catch:{ ang -> 0x0551 }
            aom r12 = new aom     // Catch:{ ang -> 0x0502 }
            r12.<init>()     // Catch:{ ang -> 0x0502 }
            r12.mo1357b()     // Catch:{ ang -> 0x0502 }
            r21 = r5
            r5 = 1024(0x400, float:1.435E-42)
            r28 = r6
            r6 = 1
            r12.mo1358a(r5, r6)     // Catch:{ ang -> 0x0500 }
            anh r5 = r4.mo1259a((java.lang.String) r3, (java.lang.String) r11, (p000.aom) r12)     // Catch:{ ang -> 0x0500 }
            r6 = 0
            java.lang.Integer r12 = java.lang.Integer.valueOf(r6)     // Catch:{ ang -> 0x0500 }
            java.lang.String r6 = ""
            r31 = r6
            r32 = r8
            r30 = r12
            r8 = r31
        L_0x039e:
            boolean r33 = r5.hasNext()     // Catch:{ ang -> 0x04fc }
            if (r33 == 0) goto L_0x042a
            java.lang.Object r33 = r5.next()     // Catch:{ ang -> 0x04fc }
            aot r33 = (p000.aot) r33     // Catch:{ ang -> 0x04fc }
            java.lang.String r34 = r33.mo1292b()     // Catch:{ ang -> 0x04fc }
            if (r34 == 0) goto L_0x0424
            java.lang.String r34 = ":"
            r35 = r5
            hqj r5 = p000.hqj.m11915a((java.lang.String) r34)     // Catch:{ ang -> 0x04fc }
            r34 = r7
            java.lang.String r7 = r33.mo1292b()     // Catch:{ ang -> 0x04fa }
            java.util.List r5 = r5.mo7681c(r7)     // Catch:{ ang -> 0x04fa }
            java.lang.Object r5 = p000.ife.m12907g((java.lang.Iterable) r5)     // Catch:{ ang -> 0x04fa }
            java.lang.String r5 = (java.lang.String) r5     // Catch:{ ang -> 0x04fa }
            java.lang.String r7 = "Mime"
            boolean r7 = r5.equals(r7)     // Catch:{ ang -> 0x04fa }
            if (r7 == 0) goto L_0x03dc
            java.lang.String r6 = r33.mo1292b()     // Catch:{ ang -> 0x04fa }
            java.lang.String r6 = p000.ddu.m5971a(r11, r6)     // Catch:{ ang -> 0x04fa }
            java.lang.String r6 = r4.mo1267f(r3, r6)     // Catch:{ ang -> 0x04fa }
        L_0x03dc:
            java.lang.String r7 = "Semantic"
            boolean r7 = r5.equals(r7)     // Catch:{ ang -> 0x04fa }
            if (r7 == 0) goto L_0x03f0
            java.lang.String r7 = r33.mo1292b()     // Catch:{ ang -> 0x04fa }
            java.lang.String r7 = p000.ddu.m5971a(r11, r7)     // Catch:{ ang -> 0x04fa }
            java.lang.String r8 = r4.mo1267f(r3, r7)     // Catch:{ ang -> 0x04fa }
        L_0x03f0:
            java.lang.String r7 = "Length"
            boolean r7 = r5.equals(r7)     // Catch:{ ang -> 0x04fa }
            if (r7 == 0) goto L_0x0405
            java.lang.String r7 = r33.mo1292b()     // Catch:{ ang -> 0x04fa }
            java.lang.String r7 = p000.ddu.m5971a(r11, r7)     // Catch:{ ang -> 0x04fa }
            java.lang.Integer r7 = r4.mo1265d(r3, r7)     // Catch:{ ang -> 0x04fa }
            r12 = r7
        L_0x0405:
            java.lang.String r7 = "Padding"
            boolean r5 = r5.equals(r7)     // Catch:{ ang -> 0x04fa }
            if (r5 == 0) goto L_0x041e
            java.lang.String r5 = r33.mo1292b()     // Catch:{ ang -> 0x04fa }
            java.lang.String r5 = p000.ddu.m5971a(r11, r5)     // Catch:{ ang -> 0x04fa }
            java.lang.Integer r30 = r4.mo1265d(r3, r5)     // Catch:{ ang -> 0x04fa }
            r7 = r34
            r5 = r35
            goto L_0x039e
        L_0x041e:
            r7 = r34
            r5 = r35
            goto L_0x039e
        L_0x0424:
            r35 = r5
            r34 = r7
            goto L_0x039e
        L_0x042a:
            r34 = r7
            r5 = 4
            java.lang.Object[] r5 = new java.lang.Object[r5]     // Catch:{ ang -> 0x04fa }
            r7 = 0
            r5[r7] = r6     // Catch:{ ang -> 0x04fa }
            r7 = 1
            r5[r7] = r8     // Catch:{ ang -> 0x04fa }
            if (r12 == 0) goto L_0x043c
            int r7 = r12.intValue()     // Catch:{ ang -> 0x04fa }
            goto L_0x043e
        L_0x043c:
            r7 = 0
        L_0x043e:
            java.lang.Integer r7 = java.lang.Integer.valueOf(r7)     // Catch:{ ang -> 0x04fa }
            r16 = 2
            r5[r16] = r7     // Catch:{ ang -> 0x04fa }
            if (r30 == 0) goto L_0x044d
            int r7 = r30.intValue()     // Catch:{ ang -> 0x04fa }
            goto L_0x044f
        L_0x044d:
            r7 = 0
        L_0x044f:
            java.lang.Integer r7 = java.lang.Integer.valueOf(r7)     // Catch:{ ang -> 0x04fa }
            r25 = 3
            r5[r25] = r7     // Catch:{ ang -> 0x04fa }
            ddo r5 = new ddo     // Catch:{ ang -> 0x04fa }
            r7 = 0
            r5.<init>(r7)     // Catch:{ ang -> 0x04fa }
            if (r6 == 0) goto L_0x04f2
            r5.f6360a = r6     // Catch:{ ang -> 0x04fa }
            if (r8 == 0) goto L_0x04ea
            r5.f6361b = r8     // Catch:{ ang -> 0x04fa }
            if (r12 == 0) goto L_0x046c
            int r6 = r12.intValue()     // Catch:{ ang -> 0x04fa }
            goto L_0x046e
        L_0x046c:
            r6 = 0
        L_0x046e:
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)     // Catch:{ ang -> 0x04fa }
            r5.f6362c = r6     // Catch:{ ang -> 0x04fa }
            if (r30 == 0) goto L_0x047b
            int r6 = r30.intValue()     // Catch:{ ang -> 0x04fa }
            goto L_0x047d
        L_0x047b:
            r6 = 0
        L_0x047d:
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)     // Catch:{ ang -> 0x04fa }
            r5.f6363d = r6     // Catch:{ ang -> 0x04fa }
            java.lang.String r6 = r5.f6360a     // Catch:{ ang -> 0x04fa }
            if (r6 == 0) goto L_0x048a
            r6 = r31
            goto L_0x048c
        L_0x048a:
            r6 = r22
        L_0x048c:
            java.lang.String r7 = r5.f6361b     // Catch:{ ang -> 0x04fa }
            if (r7 != 0) goto L_0x0496
            java.lang.String r7 = " semantic"
            java.lang.String r6 = r6.concat(r7)     // Catch:{ ang -> 0x04fa }
        L_0x0496:
            java.lang.Integer r7 = r5.f6362c     // Catch:{ ang -> 0x04fa }
            if (r7 != 0) goto L_0x04a4
            java.lang.String r6 = java.lang.String.valueOf(r6)     // Catch:{ ang -> 0x04fa }
            java.lang.String r7 = " length"
            java.lang.String r6 = r6.concat(r7)     // Catch:{ ang -> 0x04fa }
        L_0x04a4:
            java.lang.Integer r7 = r5.f6363d     // Catch:{ ang -> 0x04fa }
            if (r7 != 0) goto L_0x04b2
            java.lang.String r6 = java.lang.String.valueOf(r6)     // Catch:{ ang -> 0x04fa }
            java.lang.String r7 = " padding"
            java.lang.String r6 = r6.concat(r7)     // Catch:{ ang -> 0x04fa }
        L_0x04b2:
            boolean r7 = r6.isEmpty()     // Catch:{ ang -> 0x04fa }
            if (r7 == 0) goto L_0x04ce
            ddm r6 = new ddm     // Catch:{ ang -> 0x04fa }
            java.lang.String r7 = r5.f6360a     // Catch:{ ang -> 0x04fa }
            java.lang.String r8 = r5.f6361b     // Catch:{ ang -> 0x04fa }
            java.lang.Integer r12 = r5.f6362c     // Catch:{ ang -> 0x04fa }
            int r12 = r12.intValue()     // Catch:{ ang -> 0x04fa }
            java.lang.Integer r5 = r5.f6363d     // Catch:{ ang -> 0x04fa }
            int r5 = r5.intValue()     // Catch:{ ang -> 0x04fa }
            r6.<init>(r7, r8, r12, r5)     // Catch:{ ang -> 0x04fa }
            goto L_0x051f
        L_0x04ce:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException     // Catch:{ ang -> 0x04fa }
            java.lang.String r7 = "Missing required properties:"
            java.lang.String r6 = java.lang.String.valueOf(r6)     // Catch:{ ang -> 0x04fa }
            int r8 = r6.length()     // Catch:{ ang -> 0x04fa }
            if (r8 != 0) goto L_0x04e2
            java.lang.String r6 = new java.lang.String     // Catch:{ ang -> 0x04fa }
            r6.<init>(r7)     // Catch:{ ang -> 0x04fa }
            goto L_0x04e6
        L_0x04e2:
            java.lang.String r6 = r7.concat(r6)     // Catch:{ ang -> 0x04fa }
        L_0x04e6:
            r5.<init>(r6)     // Catch:{ ang -> 0x04fa }
            throw r5     // Catch:{ ang -> 0x04fa }
        L_0x04ea:
            java.lang.NullPointerException r5 = new java.lang.NullPointerException     // Catch:{ ang -> 0x04fa }
            java.lang.String r6 = "Null semantic"
            r5.<init>(r6)     // Catch:{ ang -> 0x04fa }
            throw r5     // Catch:{ ang -> 0x04fa }
        L_0x04f2:
            java.lang.NullPointerException r5 = new java.lang.NullPointerException     // Catch:{ ang -> 0x04fa }
            java.lang.String r6 = "Null mime"
            r5.<init>(r6)     // Catch:{ ang -> 0x04fa }
            throw r5     // Catch:{ ang -> 0x04fa }
        L_0x04fa:
            r0 = move-exception
            goto L_0x050b
        L_0x04fc:
            r0 = move-exception
            r34 = r7
            goto L_0x050b
        L_0x0500:
            r0 = move-exception
            goto L_0x0507
        L_0x0502:
            r0 = move-exception
            r21 = r5
            r28 = r6
        L_0x0507:
            r34 = r7
            r32 = r8
        L_0x050b:
            r5 = r0
            java.lang.String r6 = "Error looking up XMP property. xmpMeta: %s; namespace: %s; property prefix: %s"
            r7 = 3
            java.lang.Object[] r8 = new java.lang.Object[r7]     // Catch:{ ang -> 0x054f }
            r7 = 0
            r8[r7] = r4     // Catch:{ ang -> 0x054f }
            r7 = 1
            r8[r7] = r3     // Catch:{ ang -> 0x054f }
            r7 = 2
            r8[r7] = r11     // Catch:{ ang -> 0x054f }
            p000.cwn.m5511a((java.lang.Throwable) r5, (java.lang.String) r6, (java.lang.Object[]) r8)     // Catch:{ ang -> 0x054f }
            r6 = 0
        L_0x051f:
            if (r6 == 0) goto L_0x052e
            r2.add(r6)     // Catch:{ ang -> 0x054f }
            r5 = r21
            r6 = r28
            r8 = r32
            r7 = r34
            goto L_0x036d
        L_0x052e:
            r5 = r21
            r6 = r28
            r8 = r32
            r7 = r34
            goto L_0x036d
        L_0x0538:
            r28 = r6
            r34 = r7
            r32 = r8
            goto L_0x056a
        L_0x053f:
            r28 = r6
            r34 = r7
            r32 = r8
            r5 = 2
            java.lang.Object[] r6 = new java.lang.Object[r5]     // Catch:{ ang -> 0x054f }
            r5 = 0
            r6[r5] = r13     // Catch:{ ang -> 0x054f }
            r5 = 1
            r6[r5] = r3     // Catch:{ ang -> 0x054f }
            goto L_0x056a
        L_0x054f:
            r0 = move-exception
            goto L_0x0558
        L_0x0551:
            r0 = move-exception
            r28 = r6
            r34 = r7
            r32 = r8
        L_0x0558:
            r5 = r0
        L_0x0559:
            java.lang.String r6 = "Error looking up XMP property. xmpMeta: %s; namespace: %s; property: %s"
            r7 = 3
            java.lang.Object[] r7 = new java.lang.Object[r7]     // Catch:{ ang -> 0x05d9 }
            r8 = 0
            r7[r8] = r4     // Catch:{ ang -> 0x05d9 }
            r8 = 1
            r7[r8] = r3     // Catch:{ ang -> 0x05d9 }
            r3 = 2
            r7[r3] = r13     // Catch:{ ang -> 0x05d9 }
            p000.cwn.m5511a((java.lang.Throwable) r5, (java.lang.String) r6, (java.lang.Object[]) r7)     // Catch:{ ang -> 0x05d9 }
        L_0x056a:
            java.util.Iterator r2 = r2.iterator()     // Catch:{ ang -> 0x05d9 }
            r3 = 0
            r5 = 0
        L_0x0570:
            boolean r6 = r2.hasNext()     // Catch:{ ang -> 0x05d9 }
            if (r6 == 0) goto L_0x059c
            java.lang.Object r6 = r2.next()     // Catch:{ ang -> 0x05d9 }
            ddp r6 = (p000.ddp) r6     // Catch:{ ang -> 0x05d9 }
            java.lang.String r7 = r6.mo4068b()     // Catch:{ ang -> 0x05d9 }
            boolean r7 = r7.equals(r9)     // Catch:{ ang -> 0x05d9 }
            if (r7 != 0) goto L_0x0587
            goto L_0x058b
        L_0x0587:
            int r3 = r6.mo4069c()     // Catch:{ ang -> 0x05d9 }
        L_0x058b:
            java.lang.String r7 = r6.mo4068b()     // Catch:{ ang -> 0x05d9 }
            java.lang.String r8 = "Primary"
            boolean r7 = r7.equals(r8)     // Catch:{ ang -> 0x05d9 }
            if (r7 == 0) goto L_0x0570
            int r5 = r6.mo4070d()     // Catch:{ ang -> 0x05d9 }
            goto L_0x0570
        L_0x059c:
            ddn r2 = new ddn     // Catch:{ ang -> 0x05d9 }
            r2.<init>(r3, r5)     // Catch:{ ang -> 0x05d9 }
            r3 = 2
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch:{ ang -> 0x05d9 }
            int r5 = r2.f6358a     // Catch:{ ang -> 0x05d9 }
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)     // Catch:{ ang -> 0x05d9 }
            r6 = 0
            r3[r6] = r5     // Catch:{ ang -> 0x05d9 }
            int r5 = r2.f6359b     // Catch:{ ang -> 0x05d9 }
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)     // Catch:{ ang -> 0x05d9 }
            r6 = 1
            r3[r6] = r5     // Catch:{ ang -> 0x05d9 }
            r15.f6367d = r1     // Catch:{ ang -> 0x05d9 }
            java.lang.String r1 = "MotionPhotoVersion"
            java.lang.Integer r1 = p000.ddu.m5973c(r4, r14, r1)     // Catch:{ ang -> 0x05d9 }
            r15.f6368e = r1     // Catch:{ ang -> 0x05d9 }
            int r1 = r2.f6358a     // Catch:{ ang -> 0x05d9 }
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)     // Catch:{ ang -> 0x05d9 }
            r15.f6369f = r1     // Catch:{ ang -> 0x05d9 }
            r1 = r18
            java.lang.Long r1 = p000.ddu.m5974d(r4, r14, r1)     // Catch:{ ang -> 0x05d9 }
            r15.f6370g = r1     // Catch:{ ang -> 0x05d9 }
            int r1 = r2.f6359b     // Catch:{ ang -> 0x05d9 }
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)     // Catch:{ ang -> 0x05d9 }
            r15.f6371h = r1     // Catch:{ ang -> 0x05d9 }
            goto L_0x05eb
        L_0x05d9:
            r0 = move-exception
            goto L_0x05e2
        L_0x05db:
            r0 = move-exception
            r28 = r6
            r34 = r7
            r32 = r8
        L_0x05e2:
            r2 = r0
            goto L_0x0310
        L_0x05e5:
            r28 = r6
            r34 = r7
            r32 = r8
        L_0x05eb:
            java.lang.String r1 = r15.f6365b     // Catch:{ ang -> 0x065f }
            boolean r1 = android.text.TextUtils.isEmpty(r1)     // Catch:{ ang -> 0x065f }
            if (r1 != 0) goto L_0x05fc
            r1 = r23
            java.lang.Boolean r2 = p000.ddu.m5972b(r4, r14, r1)     // Catch:{ ang -> 0x065c }
            r15.f6366c = r2     // Catch:{ ang -> 0x065c }
            goto L_0x05fe
        L_0x05fc:
            r1 = r23
        L_0x05fe:
            java.lang.String r2 = r15.f6364a     // Catch:{ ang -> 0x065c }
            boolean r2 = android.text.TextUtils.isEmpty(r2)     // Catch:{ ang -> 0x065c }
            if (r2 != 0) goto L_0x0607
            goto L_0x0629
        L_0x0607:
            java.lang.String r2 = r15.f6365b     // Catch:{ ang -> 0x065c }
            boolean r2 = android.text.TextUtils.isEmpty(r2)     // Catch:{ ang -> 0x065c }
            if (r2 == 0) goto L_0x0629
            java.util.List r2 = r15.f6372i     // Catch:{ ang -> 0x065c }
            boolean r2 = r2.isEmpty()     // Catch:{ ang -> 0x065c }
            if (r2 == 0) goto L_0x0629
            java.lang.Integer r2 = r15.f6369f     // Catch:{ ang -> 0x065c }
            if (r2 != 0) goto L_0x0629
            java.lang.Boolean r2 = r15.f6367d     // Catch:{ ang -> 0x065c }
            if (r2 != 0) goto L_0x0629
            java.lang.Long r2 = r15.f6370g     // Catch:{ ang -> 0x065c }
            if (r2 != 0) goto L_0x0629
            java.lang.Integer r2 = r15.f6371h     // Catch:{ ang -> 0x065c }
            if (r2 != 0) goto L_0x0629
            r2 = 0
            goto L_0x0654
        L_0x0629:
            ddr r2 = new ddr     // Catch:{ ang -> 0x065c }
            java.lang.String r3 = r15.f6364a     // Catch:{ ang -> 0x065c }
            java.lang.String r4 = r15.f6365b     // Catch:{ ang -> 0x065c }
            java.lang.Boolean r5 = r15.f6366c     // Catch:{ ang -> 0x065c }
            java.lang.Boolean r6 = r15.f6367d     // Catch:{ ang -> 0x065c }
            java.lang.Integer r7 = r15.f6368e     // Catch:{ ang -> 0x065c }
            java.lang.Integer r8 = r15.f6369f     // Catch:{ ang -> 0x065c }
            java.lang.Long r9 = r15.f6370g     // Catch:{ ang -> 0x065c }
            java.lang.Integer r11 = r15.f6371h     // Catch:{ ang -> 0x065c }
            java.util.List r12 = r15.f6372i     // Catch:{ ang -> 0x065c }
            r35 = r2
            r36 = r3
            r37 = r4
            r38 = r5
            r39 = r6
            r40 = r7
            r41 = r8
            r42 = r9
            r43 = r11
            r44 = r12
            r35.<init>(r36, r37, r38, r39, r40, r41, r42, r43, r44)     // Catch:{ ang -> 0x065c }
        L_0x0654:
            r3 = 1
            java.lang.Object[] r4 = new java.lang.Object[r3]     // Catch:{ ang -> 0x065c }
            r3 = 0
            r4[r3] = r2     // Catch:{ ang -> 0x065c }
            goto L_0x06bc
        L_0x065c:
            r0 = move-exception
            goto L_0x06b1
        L_0x065f:
            r0 = move-exception
            goto L_0x06af
        L_0x0661:
            r0 = move-exception
        L_0x0662:
            r28 = r6
            r34 = r7
            r32 = r8
            goto L_0x06af
        L_0x0669:
            r0 = move-exception
            r3 = r27
            goto L_0x0816
        L_0x066e:
            r0 = move-exception
            r29 = r2
            goto L_0x0662
        L_0x0672:
            r0 = move-exception
            r29 = r2
            r27 = r3
            r28 = r6
            r34 = r7
            r32 = r8
            goto L_0x068b
        L_0x067e:
            r0 = move-exception
            r29 = r2
            r27 = r3
            r28 = r6
            r34 = r7
            r32 = r8
            r24 = r11
        L_0x068b:
            r26 = r12
            goto L_0x06af
        L_0x068e:
            r29 = r2
            r27 = r3
            r34 = r7
            r32 = r8
            r24 = r11
            r26 = r12
            r28 = r16
            r1 = r23
            r2 = 0
            goto L_0x06bc
        L_0x06a0:
            r0 = move-exception
            r29 = r2
            r27 = r3
            r34 = r7
            r32 = r8
            r24 = r11
            r26 = r12
            r28 = r16
        L_0x06af:
            r1 = r23
        L_0x06b1:
            r2 = r0
        L_0x06b2:
            java.lang.String r3 = "Error parsing xmp meta."
            r4 = 0
            java.lang.Object[] r5 = new java.lang.Object[r4]     // Catch:{ IOException -> 0x0804 }
            p000.cwn.m5511a((java.lang.Throwable) r2, (java.lang.String) r3, (java.lang.Object[]) r5)     // Catch:{ IOException -> 0x0804 }
            r2 = 0
        L_0x06bc:
            if (r2 != 0) goto L_0x06c7
            r29.mo6110b()     // Catch:{ IOException -> 0x0804 }
            r3 = r27
            r2 = r29
            goto L_0x0816
        L_0x06c7:
            r3 = r27
            dds r4 = r3.f6340a     // Catch:{ ang -> 0x07f4, IOException -> 0x07f2 }
            ddu r4 = (p000.ddu) r4     // Catch:{ ang -> 0x07f4, IOException -> 0x07f2 }
            r4.mo4080a()     // Catch:{ ang -> 0x07f4, IOException -> 0x07f2 }
            ani r4 = p000.ank.m1170a()     // Catch:{ ang -> 0x07f4, IOException -> 0x07f2 }
            java.lang.String r5 = r2.f6373a     // Catch:{ ang -> 0x07f4, IOException -> 0x07f2 }
            if (r5 == 0) goto L_0x06e7
            r6 = r4
            aoa r6 = (p000.aoa) r6     // Catch:{ ang -> 0x06e1, IOException -> 0x07f2 }
            r7 = r26
            r6.mo1304a((java.lang.String) r14, (java.lang.String) r7, (java.lang.Object) r5)     // Catch:{ ang -> 0x06e1, IOException -> 0x07f2 }
            goto L_0x06e7
        L_0x06e1:
            r0 = move-exception
            r1 = r0
            r2 = r29
            goto L_0x07f8
        L_0x06e7:
            java.lang.String r5 = r2.f6374b     // Catch:{ ang -> 0x07f4, IOException -> 0x07f2 }
            if (r5 == 0) goto L_0x06f3
            r6 = r4
            aoa r6 = (p000.aoa) r6     // Catch:{ ang -> 0x06e1, IOException -> 0x07f2 }
            r7 = r24
            r6.mo1304a((java.lang.String) r14, (java.lang.String) r7, (java.lang.Object) r5)     // Catch:{ ang -> 0x06e1, IOException -> 0x07f2 }
        L_0x06f3:
            java.lang.Boolean r5 = r2.f6375c     // Catch:{ ang -> 0x07f4, IOException -> 0x07f2 }
            if (r5 == 0) goto L_0x06fe
            boolean r5 = r5.booleanValue()     // Catch:{ ang -> 0x06e1, IOException -> 0x07f2 }
            r4.mo1263b(r14, r1, r5)     // Catch:{ ang -> 0x06e1, IOException -> 0x07f2 }
        L_0x06fe:
            java.lang.Boolean r1 = r2.f6376d     // Catch:{ ang -> 0x07f4, IOException -> 0x07f2 }
            if (r1 == 0) goto L_0x0709
            boolean r1 = r1.booleanValue()     // Catch:{ ang -> 0x06e1, IOException -> 0x07f2 }
            r4.mo1263b(r14, r10, r1)     // Catch:{ ang -> 0x06e1, IOException -> 0x07f2 }
        L_0x0709:
            java.lang.Integer r1 = r2.f6377e     // Catch:{ ang -> 0x07f4, IOException -> 0x07f2 }
            if (r1 == 0) goto L_0x0716
            int r1 = r1.intValue()     // Catch:{ ang -> 0x06e1, IOException -> 0x07f2 }
            r5 = r34
            r4.mo1263b(r14, r5, r1)     // Catch:{ ang -> 0x06e1, IOException -> 0x07f2 }
        L_0x0716:
            java.lang.Long r1 = r2.f6378f     // Catch:{ ang -> 0x07f4, IOException -> 0x07f2 }
            if (r1 != 0) goto L_0x071b
            goto L_0x072d
        L_0x071b:
            long r5 = r1.longValue()     // Catch:{ ang -> 0x07f4, IOException -> 0x07f2 }
            java.lang.Long r1 = new java.lang.Long     // Catch:{ ang -> 0x07f4, IOException -> 0x07f2 }
            r1.<init>(r5)     // Catch:{ ang -> 0x07f4, IOException -> 0x07f2 }
            r5 = r4
            aoa r5 = (p000.aoa) r5     // Catch:{ ang -> 0x07f4, IOException -> 0x07f2 }
            r6 = r32
            r5.mo1304a((java.lang.String) r14, (java.lang.String) r6, (java.lang.Object) r1)     // Catch:{ ang -> 0x07f4, IOException -> 0x07f2 }
        L_0x072d:
            java.util.List r1 = r2.f6379g     // Catch:{ ang -> 0x07f4, IOException -> 0x07f2 }
            boolean r1 = r1.isEmpty()     // Catch:{ ang -> 0x07f4, IOException -> 0x07f2 }
            if (r1 != 0) goto L_0x07e1
            aop r1 = new aop     // Catch:{ ang -> 0x06e1, IOException -> 0x07f2 }
            r1.<init>()     // Catch:{ ang -> 0x06e1, IOException -> 0x07f2 }
            r1.mo1381l()     // Catch:{ ang -> 0x06e1, IOException -> 0x07f2 }
            aop r5 = new aop     // Catch:{ ang -> 0x06e1, IOException -> 0x07f2 }
            r5.<init>()     // Catch:{ ang -> 0x06e1, IOException -> 0x07f2 }
            java.util.List r2 = r2.f6379g     // Catch:{ ang -> 0x06e1, IOException -> 0x07f2 }
            java.util.Iterator r2 = r2.iterator()     // Catch:{ ang -> 0x06e1, IOException -> 0x07f2 }
        L_0x0748:
            boolean r6 = r2.hasNext()     // Catch:{ ang -> 0x06e1, IOException -> 0x07f2 }
            if (r6 == 0) goto L_0x07e1
            java.lang.Object r6 = r2.next()     // Catch:{ ang -> 0x06e1, IOException -> 0x07f2 }
            java.lang.String r6 = (java.lang.String) r6     // Catch:{ ang -> 0x06e1, IOException -> 0x07f2 }
            p000.ckx.m4485d(r14)     // Catch:{ ang -> 0x06e1, IOException -> 0x07f2 }
            p000.ckx.m4483b(r28)     // Catch:{ ang -> 0x06e1, IOException -> 0x07f2 }
            int r7 = r1.f1282a     // Catch:{ ang -> 0x06e1, IOException -> 0x07f2 }
            r7 = r7 & -7681(0xffffffffffffe1ff, float:NaN)
            r8 = 103(0x67, float:1.44E-43)
            if (r7 != 0) goto L_0x07d9
            r7 = 0
            aop r9 = p000.C0637xj.m15897a((p000.aop) r1, (java.lang.Object) r7)     // Catch:{ ang -> 0x06e1, IOException -> 0x07f2 }
            r7 = r28
            aoj r10 = p000.C0643xp.m15938a((java.lang.String) r14, (java.lang.String) r7)     // Catch:{ ang -> 0x06e1, IOException -> 0x07f2 }
            r11 = r4
            aoa r11 = (p000.aoa) r11     // Catch:{ ang -> 0x06e1, IOException -> 0x07f2 }
            aod r11 = r11.f1242a     // Catch:{ ang -> 0x06e1, IOException -> 0x07f2 }
            r12 = 0
            r13 = 0
            aod r11 = p000.C0637xj.m15894a((p000.aod) r11, (p000.aoj) r10, (boolean) r13, (p000.aop) r12)     // Catch:{ ang -> 0x06e1, IOException -> 0x07f2 }
            r12 = 102(0x66, float:1.43E-43)
            if (r11 == 0) goto L_0x078f
            aop r8 = r11.mo1328i()     // Catch:{ ang -> 0x06e1, IOException -> 0x07f2 }
            boolean r8 = r8.mo1375f()     // Catch:{ ang -> 0x06e1, IOException -> 0x07f2 }
            if (r8 == 0) goto L_0x0787
            goto L_0x07a2
        L_0x0787:
            ang r1 = new ang     // Catch:{ ang -> 0x06e1, IOException -> 0x07f2 }
            java.lang.String r2 = "The named property is not an array"
            r1.<init>(r2, r12)     // Catch:{ ang -> 0x06e1, IOException -> 0x07f2 }
            throw r1     // Catch:{ ang -> 0x06e1, IOException -> 0x07f2 }
        L_0x078f:
            boolean r11 = r9.mo1375f()     // Catch:{ ang -> 0x06e1, IOException -> 0x07f2 }
            if (r11 == 0) goto L_0x07d1
            r8 = r4
            aoa r8 = (p000.aoa) r8     // Catch:{ ang -> 0x06e1, IOException -> 0x07f2 }
            aod r8 = r8.f1242a     // Catch:{ ang -> 0x06e1, IOException -> 0x07f2 }
            r11 = 1
            aod r8 = p000.C0637xj.m15894a((p000.aod) r8, (p000.aoj) r10, (boolean) r11, (p000.aop) r9)     // Catch:{ ang -> 0x06e1, IOException -> 0x07f2 }
            if (r8 == 0) goto L_0x07c9
            r11 = r8
        L_0x07a2:
            aod r8 = new aod     // Catch:{ ang -> 0x06e1, IOException -> 0x07f2 }
            java.lang.String r9 = "[]"
            r10 = 0
            r8.<init>(r9, r10)     // Catch:{ ang -> 0x06e1, IOException -> 0x07f2 }
            aop r9 = p000.C0637xj.m15897a((p000.aop) r5, (java.lang.Object) r6)     // Catch:{ ang -> 0x06e1, IOException -> 0x07f2 }
            int r12 = r11.mo1318c()     // Catch:{ ang -> 0x06e1, IOException -> 0x07f2 }
            r13 = 1
            int r12 = r12 + r13
            if (r12 <= 0) goto L_0x07bf
            r11.mo1312a((int) r12, (p000.aod) r8)     // Catch:{ ang -> 0x06e1, IOException -> 0x07f2 }
            p000.aoa.m1220a((p000.aod) r8, (java.lang.Object) r6, (p000.aop) r9)     // Catch:{ ang -> 0x06e1, IOException -> 0x07f2 }
            r28 = r7
            goto L_0x0748
        L_0x07bf:
            ang r1 = new ang     // Catch:{ ang -> 0x06e1, IOException -> 0x07f2 }
            java.lang.String r2 = "Array index out of bounds"
            r4 = 104(0x68, float:1.46E-43)
            r1.<init>(r2, r4)     // Catch:{ ang -> 0x06e1, IOException -> 0x07f2 }
            throw r1     // Catch:{ ang -> 0x06e1, IOException -> 0x07f2 }
        L_0x07c9:
            ang r1 = new ang     // Catch:{ ang -> 0x06e1, IOException -> 0x07f2 }
            java.lang.String r2 = "Failure creating array node"
            r1.<init>(r2, r12)     // Catch:{ ang -> 0x06e1, IOException -> 0x07f2 }
            throw r1     // Catch:{ ang -> 0x06e1, IOException -> 0x07f2 }
        L_0x07d1:
            ang r1 = new ang     // Catch:{ ang -> 0x06e1, IOException -> 0x07f2 }
            java.lang.String r2 = "Explicit arrayOptions required to create new array"
            r1.<init>(r2, r8)     // Catch:{ ang -> 0x06e1, IOException -> 0x07f2 }
            throw r1     // Catch:{ ang -> 0x06e1, IOException -> 0x07f2 }
        L_0x07d9:
            ang r1 = new ang     // Catch:{ ang -> 0x06e1, IOException -> 0x07f2 }
            java.lang.String r2 = "Only array form flags allowed for arrayOptions"
            r1.<init>(r2, r8)     // Catch:{ ang -> 0x06e1, IOException -> 0x07f2 }
            throw r1     // Catch:{ ang -> 0x06e1, IOException -> 0x07f2 }
        L_0x07e1:
            aoq r1 = new aoq     // Catch:{ ang -> 0x07f4, IOException -> 0x07f2 }
            r1.<init>()     // Catch:{ ang -> 0x07f4, IOException -> 0x07f2 }
            byte[] r1 = p000.ank.m1172a(r4, r1)     // Catch:{ ang -> 0x07f4, IOException -> 0x07f2 }
            r2 = r29
            r2.mo6107a((byte[]) r1)     // Catch:{ ang -> 0x07f0 }
            goto L_0x0816
        L_0x07f0:
            r0 = move-exception
            goto L_0x07f7
        L_0x07f2:
            r0 = move-exception
            goto L_0x0807
        L_0x07f4:
            r0 = move-exception
            r2 = r29
        L_0x07f7:
            r1 = r0
        L_0x07f8:
            java.lang.String r4 = "Failed to write OEM xmp metadata"
            r5 = 0
            java.lang.Object[] r6 = new java.lang.Object[r5]     // Catch:{ IOException -> 0x080a }
            p000.cwn.m5515b((java.lang.Throwable) r1, (java.lang.String) r4, (java.lang.Object[]) r6)     // Catch:{ IOException -> 0x080a }
            r2.mo6110b()     // Catch:{ IOException -> 0x080a }
            goto L_0x0816
        L_0x0804:
            r0 = move-exception
            r3 = r27
        L_0x0807:
            r2 = r29
            goto L_0x0816
        L_0x080a:
            r0 = move-exception
            goto L_0x0816
        L_0x080c:
            r19 = r1
            r17 = r13
            goto L_0x0816
        L_0x0811:
            r0 = move-exception
            r19 = r1
            r17 = r13
        L_0x0816:
            java.lang.String r1 = r3.f6345f     // Catch:{ all -> 0x0881 }
            boolean r1 = android.text.TextUtils.isEmpty(r1)     // Catch:{ all -> 0x0881 }
            if (r1 == 0) goto L_0x081f
            goto L_0x082a
        L_0x081f:
            int r1 = p000.fsc.f10452e     // Catch:{ all -> 0x0881 }
            java.lang.String r4 = r3.f6345f     // Catch:{ all -> 0x0881 }
            fsl r1 = r2.mo6103a((int) r1, (java.lang.Object) r4)     // Catch:{ all -> 0x0881 }
            r2.mo6105a((p000.fsl) r1)     // Catch:{ all -> 0x0881 }
        L_0x082a:
            exm r1 = r3.f6341b     // Catch:{ all -> 0x0881 }
            long r4 = r1.mo5370a()     // Catch:{ all -> 0x0881 }
            int r1 = p000.fsc.f10453f     // Catch:{ all -> 0x0881 }
            java.util.TimeZone r6 = java.util.TimeZone.getDefault()     // Catch:{ all -> 0x0881 }
            r2.mo6104a(r1, r4, r6)     // Catch:{ all -> 0x0881 }
            int r1 = p000.fsc.f10464q     // Catch:{ all -> 0x0881 }
            java.util.TimeZone r6 = java.util.TimeZone.getDefault()     // Catch:{ all -> 0x0881 }
            java.lang.String r4 = p000.dek.m6000a(r4, r6)     // Catch:{ all -> 0x0881 }
            fsl r1 = r2.mo6103a((int) r1, (java.lang.Object) r4)     // Catch:{ all -> 0x0881 }
            r2.mo6105a((p000.fsl) r1)     // Catch:{ all -> 0x0881 }
            java.lang.Long r1 = r3.f6346g     // Catch:{ all -> 0x0881 }
            if (r1 == 0) goto L_0x0879
            int r4 = p000.fsc.f10461n     // Catch:{ all -> 0x0881 }
            int r5 = r2.mo6113d(r4)     // Catch:{ all -> 0x0881 }
            fsl r4 = r2.mo6102a((int) r4, (int) r5)     // Catch:{ all -> 0x0881 }
            if (r4 == 0) goto L_0x085b
            goto L_0x0866
        L_0x085b:
            int r4 = p000.fsc.f10461n     // Catch:{ all -> 0x0881 }
            long r5 = r1.longValue()     // Catch:{ all -> 0x0881 }
            java.util.TimeZone r7 = r3.f6347h     // Catch:{ all -> 0x0881 }
            r2.mo6104a(r4, r5, r7)     // Catch:{ all -> 0x0881 }
        L_0x0866:
            int r4 = p000.fsc.f10465r     // Catch:{ all -> 0x0881 }
            long r5 = r1.longValue()     // Catch:{ all -> 0x0881 }
            java.util.TimeZone r1 = r3.f6347h     // Catch:{ all -> 0x0881 }
            java.lang.String r1 = p000.dek.m6000a(r5, r1)     // Catch:{ all -> 0x0881 }
            fsl r1 = r2.mo6103a((int) r4, (java.lang.Object) r1)     // Catch:{ all -> 0x0881 }
            r2.mo6105a((p000.fsl) r1)     // Catch:{ all -> 0x0881 }
        L_0x0879:
            j$.util.Optional r1 = p003j$.util.Optional.m16285of(r2)     // Catch:{ all -> 0x0881 }
            r17.close()     // Catch:{ IOException -> 0x0893 }
            goto L_0x08a8
        L_0x0881:
            r0 = move-exception
            goto L_0x0888
        L_0x0883:
            r0 = move-exception
            r19 = r1
            r17 = r13
        L_0x0888:
            r1 = r0
            r17.close()     // Catch:{ all -> 0x088d }
            goto L_0x0892
        L_0x088d:
            r0 = move-exception
            r2 = r0
            p000.ifn.m12935a((java.lang.Throwable) r1, (java.lang.Throwable) r2)     // Catch:{ IOException -> 0x0893 }
        L_0x0892:
            throw r1     // Catch:{ IOException -> 0x0893 }
        L_0x0893:
            r0 = move-exception
            goto L_0x0898
        L_0x0895:
            r0 = move-exception
            r19 = r1
        L_0x0898:
            r1 = r0
            r2 = 1
            java.lang.Object[] r2 = new java.lang.Object[r2]
            r3 = 0
            r2[r3] = r19
            java.lang.String r3 = "SaverModule: Failed to load exif/xmp data from uri[%s]."
            p000.cwn.m5515b((java.lang.Throwable) r1, (java.lang.String) r3, (java.lang.Object[]) r2)
        L_0x08a4:
            j$.util.Optional r1 = p003j$.util.Optional.empty()
        L_0x08a8:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.dce.call():java.lang.Object");
    }
}
