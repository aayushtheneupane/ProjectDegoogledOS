package p000;

import android.graphics.Bitmap;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.List;

/* renamed from: aqd */
/* compiled from: PG */
public final class aqd implements apz {

    /* renamed from: a */
    public final apy f1430a;

    /* renamed from: b */
    public ByteBuffer f1431b;

    /* renamed from: c */
    public byte[] f1432c;

    /* renamed from: d */
    public byte[] f1433d;

    /* renamed from: e */
    public int[] f1434e;

    /* renamed from: f */
    public int f1435f;

    /* renamed from: g */
    public aqb f1436g;

    /* renamed from: h */
    public Bitmap f1437h;

    /* renamed from: i */
    public Boolean f1438i;

    /* renamed from: j */
    public Bitmap.Config f1439j = Bitmap.Config.ARGB_8888;

    /* renamed from: k */
    private int[] f1440k;

    /* renamed from: l */
    private final int[] f1441l = new int[256];

    /* renamed from: m */
    private short[] f1442m;

    /* renamed from: n */
    private byte[] f1443n;

    /* renamed from: o */
    private byte[] f1444o;

    /* renamed from: p */
    private boolean f1445p;

    /* renamed from: q */
    private int f1446q;

    /* renamed from: r */
    private int f1447r;

    /* renamed from: s */
    private int f1448s;

    /* renamed from: t */
    private int f1449t;

    static {
        aqd.class.getSimpleName();
    }

    public aqd(apy apy, aqb aqb, ByteBuffer byteBuffer, int i) {
        this.f1430a = apy;
        this.f1436g = new aqb();
        m1446a(aqb, byteBuffer, i);
    }

    /* renamed from: a */
    public final void mo1476a() {
        this.f1435f = (this.f1435f + 1) % this.f1436g.f1415c;
    }

    /* renamed from: d */
    private final Bitmap m1448d() {
        Bitmap.Config config;
        Boolean bool = this.f1438i;
        if (bool != null && !bool.booleanValue()) {
            config = this.f1439j;
        } else {
            config = Bitmap.Config.ARGB_8888;
        }
        apy apy = this.f1430a;
        Bitmap b = ((bbr) apy).f2003a.mo1646b(this.f1449t, this.f1448s, config);
        b.setHasAlpha(true);
        return b;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:161:0x0273, code lost:
        if (r0 == 1) goto L_0x0275;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x00b9, code lost:
        if (r10.f1422j == r5.f1409h) goto L_0x00bb;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:82:0x0119, code lost:
        if (r7.length < r10) goto L_0x011b;
     */
    /* JADX WARNING: Removed duplicated region for block: B:157:0x026d  */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x00d4  */
    /* renamed from: b */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized android.graphics.Bitmap mo1477b() {
        /*
            r36 = this;
            r1 = r36
            monitor-enter(r36)
            aqb r0 = r1.f1436g     // Catch:{ all -> 0x0509 }
            int r0 = r0.f1415c     // Catch:{ all -> 0x0509 }
            r2 = 1
            if (r0 <= 0) goto L_0x000f
            int r0 = r1.f1435f     // Catch:{ all -> 0x0509 }
            if (r0 < 0) goto L_0x000f
            goto L_0x0012
        L_0x000f:
            r1.f1446q = r2     // Catch:{ all -> 0x0509 }
        L_0x0012:
            int r0 = r1.f1446q     // Catch:{ all -> 0x0509 }
            r3 = 0
            if (r0 != r2) goto L_0x0019
        L_0x0017:
            goto L_0x0507
        L_0x0019:
            r4 = 2
            if (r0 == r4) goto L_0x0017
            r0 = 0
            r1.f1446q = r0     // Catch:{ all -> 0x0509 }
            byte[] r5 = r1.f1432c     // Catch:{ all -> 0x0509 }
            r6 = 255(0xff, float:3.57E-43)
            if (r5 != 0) goto L_0x002e
            apy r5 = r1.f1430a     // Catch:{ all -> 0x0509 }
            byte[] r5 = r5.mo1475a((int) r6)     // Catch:{ all -> 0x0509 }
            r1.f1432c = r5     // Catch:{ all -> 0x0509 }
        L_0x002e:
            aqb r5 = r1.f1436g     // Catch:{ all -> 0x0509 }
            java.util.List r5 = r5.f1417e     // Catch:{ all -> 0x0509 }
            int r7 = r1.f1435f     // Catch:{ all -> 0x0509 }
            java.lang.Object r5 = r5.get(r7)     // Catch:{ all -> 0x0509 }
            aqa r5 = (p000.aqa) r5     // Catch:{ all -> 0x0509 }
            int r7 = r1.f1435f     // Catch:{ all -> 0x0509 }
            r8 = -1
            int r7 = r7 + r8
            if (r7 < 0) goto L_0x004b
            aqb r9 = r1.f1436g     // Catch:{ all -> 0x0509 }
            java.util.List r9 = r9.f1417e     // Catch:{ all -> 0x0509 }
            java.lang.Object r7 = r9.get(r7)     // Catch:{ all -> 0x0509 }
            aqa r7 = (p000.aqa) r7     // Catch:{ all -> 0x0509 }
            goto L_0x004d
        L_0x004b:
            r7 = r3
        L_0x004d:
            int[] r9 = r5.f1412k     // Catch:{ all -> 0x0509 }
            if (r9 == 0) goto L_0x0052
        L_0x0051:
            goto L_0x0057
        L_0x0052:
            aqb r9 = r1.f1436g     // Catch:{ all -> 0x0509 }
            int[] r9 = r9.f1413a     // Catch:{ all -> 0x0509 }
            goto L_0x0051
        L_0x0057:
            r1.f1440k = r9     // Catch:{ all -> 0x0509 }
            if (r9 != 0) goto L_0x005f
            r1.f1446q = r2     // Catch:{ all -> 0x0509 }
            monitor-exit(r36)
            return r3
        L_0x005f:
            boolean r10 = r5.f1407f     // Catch:{ all -> 0x0509 }
            if (r10 == 0) goto L_0x0080
            int[] r10 = r1.f1441l     // Catch:{ all -> 0x0509 }
            int r11 = r9.length     // Catch:{ all -> 0x0509 }
            java.lang.System.arraycopy(r9, r0, r10, r0, r11)     // Catch:{ all -> 0x0509 }
            int[] r9 = r1.f1441l     // Catch:{ all -> 0x0509 }
            r1.f1440k = r9     // Catch:{ all -> 0x0509 }
            int r10 = r5.f1409h     // Catch:{ all -> 0x0509 }
            r9[r10] = r0     // Catch:{ all -> 0x0509 }
            int r9 = r5.f1408g     // Catch:{ all -> 0x0509 }
            if (r9 == r4) goto L_0x0076
            goto L_0x0080
        L_0x0076:
            int r9 = r1.f1435f     // Catch:{ all -> 0x0509 }
            if (r9 != 0) goto L_0x0080
            java.lang.Boolean r9 = java.lang.Boolean.valueOf(r2)     // Catch:{ all -> 0x0509 }
            r1.f1438i = r9     // Catch:{ all -> 0x0509 }
        L_0x0080:
            int[] r9 = r1.f1434e     // Catch:{ all -> 0x0509 }
            if (r7 != 0) goto L_0x0093
            android.graphics.Bitmap r10 = r1.f1437h     // Catch:{ all -> 0x0509 }
            if (r10 == 0) goto L_0x008d
            apy r11 = r1.f1430a     // Catch:{ all -> 0x0509 }
            r11.mo1473a((android.graphics.Bitmap) r10)     // Catch:{ all -> 0x0509 }
        L_0x008d:
            r1.f1437h = r3     // Catch:{ all -> 0x0509 }
            java.util.Arrays.fill(r9, r0)     // Catch:{ all -> 0x0509 }
        L_0x0093:
            r3 = 3
            if (r7 == 0) goto L_0x00a1
            int r10 = r7.f1408g     // Catch:{ all -> 0x0509 }
            if (r10 != r3) goto L_0x00a1
            android.graphics.Bitmap r10 = r1.f1437h     // Catch:{ all -> 0x0509 }
            if (r10 != 0) goto L_0x00a1
            java.util.Arrays.fill(r9, r0)     // Catch:{ all -> 0x0509 }
        L_0x00a1:
            if (r7 == 0) goto L_0x00f9
            int r10 = r7.f1408g     // Catch:{ all -> 0x0509 }
            if (r10 <= 0) goto L_0x00f9
            if (r10 != r4) goto L_0x00e2
            boolean r10 = r5.f1407f     // Catch:{ all -> 0x0509 }
            if (r10 != 0) goto L_0x00bb
            aqb r10 = r1.f1436g     // Catch:{ all -> 0x0509 }
            int r11 = r10.f1424l     // Catch:{ all -> 0x0509 }
            int[] r12 = r5.f1412k     // Catch:{ all -> 0x0509 }
            if (r12 == 0) goto L_0x00bc
            int r10 = r10.f1422j     // Catch:{ all -> 0x0509 }
            int r12 = r5.f1409h     // Catch:{ all -> 0x0509 }
            if (r10 != r12) goto L_0x00bc
        L_0x00bb:
            r11 = 0
        L_0x00bc:
            int r10 = r7.f1405d     // Catch:{ all -> 0x0509 }
            int r12 = r1.f1447r     // Catch:{ all -> 0x0509 }
            int r10 = r10 / r12
            int r13 = r7.f1403b     // Catch:{ all -> 0x0509 }
            int r13 = r13 / r12
            int r14 = r7.f1404c     // Catch:{ all -> 0x0509 }
            int r14 = r14 / r12
            int r7 = r7.f1402a     // Catch:{ all -> 0x0509 }
            int r7 = r7 / r12
            int r12 = r1.f1449t     // Catch:{ all -> 0x0509 }
            int r13 = r13 * r12
            int r13 = r13 + r7
            int r10 = r10 * r12
            int r10 = r10 + r13
        L_0x00d2:
            if (r13 >= r10) goto L_0x00f9
            int r7 = r13 + r14
            r12 = r13
        L_0x00d7:
            if (r12 < r7) goto L_0x00dd
            int r7 = r1.f1449t     // Catch:{ all -> 0x0509 }
            int r13 = r13 + r7
            goto L_0x00d2
        L_0x00dd:
            r9[r12] = r11     // Catch:{ all -> 0x0509 }
            int r12 = r12 + 1
            goto L_0x00d7
        L_0x00e2:
            if (r10 != r3) goto L_0x00f9
            android.graphics.Bitmap r10 = r1.f1437h     // Catch:{ all -> 0x0509 }
            if (r10 == 0) goto L_0x00f9
            int r7 = r1.f1449t     // Catch:{ all -> 0x0509 }
            r12 = 0
            r14 = 0
            r15 = 0
            int r13 = r1.f1448s     // Catch:{ all -> 0x0509 }
            r11 = r9
            r17 = r13
            r13 = r7
            r16 = r7
            r10.getPixels(r11, r12, r13, r14, r15, r16, r17)     // Catch:{ all -> 0x0509 }
        L_0x00f9:
            if (r5 == 0) goto L_0x0102
            java.nio.ByteBuffer r7 = r1.f1431b     // Catch:{ all -> 0x0509 }
            int r10 = r5.f1411j     // Catch:{ all -> 0x0509 }
            r7.position(r10)     // Catch:{ all -> 0x0509 }
        L_0x0102:
            if (r5 != 0) goto L_0x010d
            aqb r7 = r1.f1436g     // Catch:{ all -> 0x0509 }
            int r10 = r7.f1418f     // Catch:{ all -> 0x0509 }
            int r7 = r7.f1419g     // Catch:{ all -> 0x0509 }
            int r10 = r10 * r7
            goto L_0x0113
        L_0x010d:
            int r7 = r5.f1404c     // Catch:{ all -> 0x0509 }
            int r10 = r5.f1405d     // Catch:{ all -> 0x0509 }
            int r10 = r10 * r7
        L_0x0113:
            byte[] r7 = r1.f1433d     // Catch:{ all -> 0x0509 }
            if (r7 != 0) goto L_0x0118
            goto L_0x011b
        L_0x0118:
            int r7 = r7.length     // Catch:{ all -> 0x0509 }
            if (r7 >= r10) goto L_0x0123
        L_0x011b:
            apy r7 = r1.f1430a     // Catch:{ all -> 0x0509 }
            byte[] r7 = r7.mo1475a((int) r10)     // Catch:{ all -> 0x0509 }
            r1.f1433d = r7     // Catch:{ all -> 0x0509 }
        L_0x0123:
            byte[] r7 = r1.f1433d     // Catch:{ all -> 0x0509 }
            short[] r11 = r1.f1442m     // Catch:{ all -> 0x0509 }
            r12 = 4096(0x1000, float:5.74E-42)
            if (r11 == 0) goto L_0x012c
            goto L_0x0131
        L_0x012c:
            short[] r11 = new short[r12]     // Catch:{ all -> 0x0509 }
            r1.f1442m = r11     // Catch:{ all -> 0x0509 }
        L_0x0131:
            short[] r11 = r1.f1442m     // Catch:{ all -> 0x0509 }
            byte[] r13 = r1.f1443n     // Catch:{ all -> 0x0509 }
            if (r13 == 0) goto L_0x0138
            goto L_0x013d
        L_0x0138:
            byte[] r13 = new byte[r12]     // Catch:{ all -> 0x0509 }
            r1.f1443n = r13     // Catch:{ all -> 0x0509 }
        L_0x013d:
            byte[] r13 = r1.f1443n     // Catch:{ all -> 0x0509 }
            byte[] r14 = r1.f1444o     // Catch:{ all -> 0x0509 }
            if (r14 == 0) goto L_0x0144
            goto L_0x014a
        L_0x0144:
            r14 = 4097(0x1001, float:5.741E-42)
            byte[] r14 = new byte[r14]     // Catch:{ all -> 0x0509 }
            r1.f1444o = r14     // Catch:{ all -> 0x0509 }
        L_0x014a:
            byte[] r14 = r1.f1444o     // Catch:{ all -> 0x0509 }
            int r15 = r36.m1447c()     // Catch:{ all -> 0x0509 }
            int r12 = r2 << r15
            int r6 = r12 + 1
            int r18 = r12 + 2
            int r15 = r15 + r2
            int r19 = r2 << r15
            int r19 = r19 + -1
            r4 = 0
        L_0x015c:
            if (r4 < r12) goto L_0x04e8
            byte[] r4 = r1.f1432c     // Catch:{ all -> 0x0509 }
            r24 = r15
            r30 = r18
            r23 = r19
            r2 = 0
            r8 = 0
            r21 = 0
            r22 = 0
            r25 = -1
            r26 = 0
            r27 = 0
            r28 = 0
            r29 = 0
        L_0x0176:
            r31 = 8
            if (r8 < r10) goto L_0x017b
            goto L_0x01b0
        L_0x017b:
            if (r21 != 0) goto L_0x0418
            int r3 = r36.m1447c()     // Catch:{ all -> 0x0509 }
            if (r3 <= 0) goto L_0x019a
            java.nio.ByteBuffer r0 = r1.f1431b     // Catch:{ all -> 0x0509 }
            r32 = r8
            byte[] r8 = r1.f1432c     // Catch:{ all -> 0x0509 }
            r33 = r15
            int r15 = r0.remaining()     // Catch:{ all -> 0x0509 }
            int r15 = java.lang.Math.min(r3, r15)     // Catch:{ all -> 0x0509 }
            r21 = r3
            r3 = 0
            r0.get(r8, r3, r15)     // Catch:{ all -> 0x0509 }
            goto L_0x01a0
        L_0x019a:
            r32 = r8
            r33 = r15
            r21 = 0
        L_0x01a0:
            if (r21 <= 0) goto L_0x01ac
            r0 = r2
            r35 = r5
            r34 = r9
            r5 = 1
            r22 = 0
            goto L_0x0422
        L_0x01ac:
            r0 = 3
            r1.f1446q = r0     // Catch:{ all -> 0x0509 }
        L_0x01b0:
            r0 = 0
            java.util.Arrays.fill(r7, r2, r10, r0)     // Catch:{ all -> 0x0509 }
            boolean r0 = r5.f1406e     // Catch:{ all -> 0x0509 }
            if (r0 == 0) goto L_0x01bb
            goto L_0x022e
        L_0x01bb:
            int r0 = r1.f1447r     // Catch:{ all -> 0x0509 }
            r2 = 1
            if (r0 != r2) goto L_0x022e
            int[] r0 = r1.f1434e     // Catch:{ all -> 0x0509 }
            int r2 = r5.f1405d     // Catch:{ all -> 0x0509 }
            int r3 = r5.f1403b     // Catch:{ all -> 0x0509 }
            int r4 = r5.f1404c     // Catch:{ all -> 0x0509 }
            int r6 = r5.f1402a     // Catch:{ all -> 0x0509 }
            int r7 = r1.f1435f     // Catch:{ all -> 0x0509 }
            int r8 = r1.f1449t     // Catch:{ all -> 0x0509 }
            byte[] r10 = r1.f1433d     // Catch:{ all -> 0x0509 }
            int[] r11 = r1.f1440k     // Catch:{ all -> 0x0509 }
            r12 = 0
            r13 = -1
        L_0x01d4:
            if (r12 >= r2) goto L_0x020d
            int r14 = r12 + r3
            int r14 = r14 * r8
            int r15 = r14 + r6
            r16 = r2
            int r2 = r15 + r4
            int r14 = r14 + r8
            if (r14 >= r2) goto L_0x01e4
            r2 = r14
        L_0x01e4:
            int r14 = r5.f1404c     // Catch:{ all -> 0x0509 }
            int r14 = r14 * r12
        L_0x01e8:
            if (r15 >= r2) goto L_0x0206
            r17 = r2
            byte r2 = r10[r14]     // Catch:{ all -> 0x0509 }
            r18 = r3
            r3 = r2 & 255(0xff, float:3.57E-43)
            if (r3 != r13) goto L_0x01f5
            goto L_0x01fd
        L_0x01f5:
            r3 = r11[r3]     // Catch:{ all -> 0x0509 }
            if (r3 == 0) goto L_0x01fc
            r0[r15] = r3     // Catch:{ all -> 0x0509 }
            goto L_0x01fd
        L_0x01fc:
            r13 = r2
        L_0x01fd:
            int r14 = r14 + 1
            int r15 = r15 + 1
            r2 = r17
            r3 = r18
            goto L_0x01e8
        L_0x0206:
            r18 = r3
            int r12 = r12 + 1
            r2 = r16
            goto L_0x01d4
        L_0x020d:
            java.lang.Boolean r0 = r1.f1438i     // Catch:{ all -> 0x0509 }
            if (r0 == 0) goto L_0x021a
            boolean r0 = r0.booleanValue()     // Catch:{ all -> 0x0509 }
            if (r0 != 0) goto L_0x0218
            goto L_0x021a
        L_0x0218:
            r0 = 1
            goto L_0x0227
        L_0x021a:
            java.lang.Boolean r0 = r1.f1438i     // Catch:{ all -> 0x0509 }
            if (r0 == 0) goto L_0x0220
        L_0x021e:
            r0 = 0
            goto L_0x0227
        L_0x0220:
            if (r7 == 0) goto L_0x0223
            goto L_0x021e
        L_0x0223:
            r0 = -1
            if (r13 != r0) goto L_0x0218
            goto L_0x021e
        L_0x0227:
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r0)     // Catch:{ all -> 0x0509 }
            r1.f1438i = r0     // Catch:{ all -> 0x0509 }
            goto L_0x0269
        L_0x022e:
            int[] r0 = r1.f1434e     // Catch:{ all -> 0x0509 }
            int r2 = r5.f1405d     // Catch:{ all -> 0x0509 }
            int r3 = r1.f1447r     // Catch:{ all -> 0x0509 }
            int r2 = r2 / r3
            int r4 = r5.f1403b     // Catch:{ all -> 0x0509 }
            int r4 = r4 / r3
            int r6 = r5.f1404c     // Catch:{ all -> 0x0509 }
            int r6 = r6 / r3
            int r7 = r5.f1402a     // Catch:{ all -> 0x0509 }
            int r7 = r7 / r3
            int r8 = r1.f1435f     // Catch:{ all -> 0x0509 }
            int r10 = r1.f1449t     // Catch:{ all -> 0x0509 }
            int r11 = r1.f1448s     // Catch:{ all -> 0x0509 }
            byte[] r12 = r1.f1433d     // Catch:{ all -> 0x0509 }
            int[] r13 = r1.f1440k     // Catch:{ all -> 0x0509 }
            java.lang.Boolean r14 = r1.f1438i     // Catch:{ all -> 0x0509 }
            r16 = r13
            r15 = r14
            r13 = 0
            r14 = 0
            r18 = 1
            r19 = 8
        L_0x0253:
            if (r14 < r2) goto L_0x02a9
            java.lang.Boolean r0 = r1.f1438i     // Catch:{ all -> 0x0509 }
            if (r0 == 0) goto L_0x025a
            goto L_0x0269
        L_0x025a:
            if (r15 == 0) goto L_0x0261
            boolean r0 = r15.booleanValue()     // Catch:{ all -> 0x0509 }
            goto L_0x0263
        L_0x0261:
            r0 = 0
        L_0x0263:
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r0)     // Catch:{ all -> 0x0509 }
            r1.f1438i = r0     // Catch:{ all -> 0x0509 }
        L_0x0269:
            boolean r0 = r1.f1445p     // Catch:{ all -> 0x0509 }
            if (r0 == 0) goto L_0x0292
            int r0 = r5.f1408g     // Catch:{ all -> 0x0509 }
            if (r0 != 0) goto L_0x0272
            goto L_0x0275
        L_0x0272:
            r2 = 1
            if (r0 != r2) goto L_0x0292
        L_0x0275:
            android.graphics.Bitmap r0 = r1.f1437h     // Catch:{ all -> 0x0509 }
            if (r0 == 0) goto L_0x027a
            goto L_0x0280
        L_0x027a:
            android.graphics.Bitmap r0 = r36.m1448d()     // Catch:{ all -> 0x0509 }
            r1.f1437h = r0     // Catch:{ all -> 0x0509 }
        L_0x0280:
            android.graphics.Bitmap r10 = r1.f1437h     // Catch:{ all -> 0x0509 }
            int r0 = r1.f1449t     // Catch:{ all -> 0x0509 }
            r12 = 0
            r14 = 0
            r15 = 0
            int r2 = r1.f1448s     // Catch:{ all -> 0x0509 }
            r11 = r9
            r13 = r0
            r16 = r0
            r17 = r2
            r10.setPixels(r11, r12, r13, r14, r15, r16, r17)     // Catch:{ all -> 0x0509 }
        L_0x0292:
            android.graphics.Bitmap r0 = r36.m1448d()     // Catch:{ all -> 0x0509 }
            int r2 = r1.f1449t     // Catch:{ all -> 0x0509 }
            r12 = 0
            r14 = 0
            r15 = 0
            int r3 = r1.f1448s     // Catch:{ all -> 0x0509 }
            r10 = r0
            r11 = r9
            r13 = r2
            r16 = r2
            r17 = r3
            r10.setPixels(r11, r12, r13, r14, r15, r16, r17)     // Catch:{ all -> 0x0509 }
            monitor-exit(r36)
            return r0
        L_0x02a9:
            r34 = r9
            boolean r9 = r5.f1406e     // Catch:{ all -> 0x0509 }
            if (r9 != 0) goto L_0x02b4
            r21 = r2
            r2 = r13
            r13 = r14
            goto L_0x02dc
        L_0x02b4:
            if (r13 < r2) goto L_0x02d6
            int r9 = r18 + 1
            r21 = r2
            r2 = 2
            if (r9 == r2) goto L_0x02d1
            r2 = 3
            if (r9 == r2) goto L_0x02ca
            r2 = 4
            r18 = r9
            if (r9 == r2) goto L_0x02c6
            goto L_0x02d8
        L_0x02c6:
            r13 = 1
            r19 = 2
            goto L_0x02d8
        L_0x02ca:
            r2 = 4
            r18 = r9
            r13 = 2
            r19 = 4
            goto L_0x02d8
        L_0x02d1:
            r2 = 4
            r18 = r9
            r13 = 4
            goto L_0x02d8
        L_0x02d6:
            r21 = r2
        L_0x02d8:
            int r2 = r13 + r19
        L_0x02dc:
            int r13 = r13 + r4
            if (r13 >= r11) goto L_0x03f2
            int r13 = r13 * r10
            int r9 = r13 + r7
            r20 = r2
            int r2 = r9 + r6
            int r13 = r13 + r10
            if (r13 >= r2) goto L_0x02eb
            r2 = r13
        L_0x02eb:
            int r13 = r14 * r3
            r22 = r4
            int r4 = r5.f1404c     // Catch:{ all -> 0x0509 }
            int r13 = r13 * r4
            r4 = 1
            if (r3 == r4) goto L_0x03c6
            int r4 = r2 - r9
            int r4 = r4 * r3
            int r4 = r4 + r13
        L_0x02fb:
            if (r9 < r2) goto L_0x0309
            r35 = r5
            r23 = r6
            r29 = r7
            r30 = r10
            r32 = r11
            goto L_0x03d2
        L_0x0309:
            r23 = r6
            int r6 = r5.f1404c     // Catch:{ all -> 0x0509 }
            r35 = r5
            r5 = r13
            r24 = 0
            r25 = 0
            r26 = 0
            r27 = 0
            r28 = 0
        L_0x031a:
            r29 = r7
            int r7 = r1.f1447r     // Catch:{ all -> 0x0509 }
            int r7 = r7 + r13
            if (r5 < r7) goto L_0x0326
            r30 = r10
            r32 = r11
            goto L_0x035e
        L_0x0326:
            byte[] r7 = r1.f1433d     // Catch:{ all -> 0x0509 }
            r30 = r10
            int r10 = r7.length     // Catch:{ all -> 0x0509 }
            if (r5 >= r10) goto L_0x035c
            if (r5 >= r4) goto L_0x035c
            byte r7 = r7[r5]     // Catch:{ all -> 0x0509 }
            int[] r10 = r1.f1440k     // Catch:{ all -> 0x0509 }
            r32 = r11
            r11 = 255(0xff, float:3.57E-43)
            r7 = r7 & r11
            r7 = r10[r7]     // Catch:{ all -> 0x0509 }
            if (r7 == 0) goto L_0x0352
            int r10 = r7 >>> 24
            int r28 = r28 + r10
            int r10 = r7 >> 16
            r11 = 255(0xff, float:3.57E-43)
            r10 = r10 & r11
            int r24 = r24 + r10
            int r10 = r7 >> 8
            r10 = r10 & r11
            int r25 = r25 + r10
            r7 = r7 & 255(0xff, float:3.57E-43)
            int r26 = r26 + r7
            int r27 = r27 + 1
        L_0x0352:
            int r5 = r5 + 1
            r7 = r29
            r10 = r30
            r11 = r32
            goto L_0x031a
        L_0x035c:
            r32 = r11
        L_0x035e:
            int r6 = r6 + r13
            r5 = r6
        L_0x0360:
            int r7 = r1.f1447r     // Catch:{ all -> 0x0509 }
            int r7 = r7 + r6
            if (r5 >= r7) goto L_0x0391
            byte[] r7 = r1.f1433d     // Catch:{ all -> 0x0509 }
            int r10 = r7.length     // Catch:{ all -> 0x0509 }
            if (r5 >= r10) goto L_0x0391
            if (r5 >= r4) goto L_0x0391
            byte r7 = r7[r5]     // Catch:{ all -> 0x0509 }
            int[] r10 = r1.f1440k     // Catch:{ all -> 0x0509 }
            r11 = 255(0xff, float:3.57E-43)
            r7 = r7 & r11
            r7 = r10[r7]     // Catch:{ all -> 0x0509 }
            if (r7 == 0) goto L_0x038d
            int r10 = r7 >>> 24
            int r28 = r28 + r10
            int r10 = r7 >> 16
            r11 = 255(0xff, float:3.57E-43)
            r10 = r10 & r11
            int r24 = r24 + r10
            int r10 = r7 >> 8
            r10 = r10 & r11
            int r25 = r25 + r10
            r7 = r7 & 255(0xff, float:3.57E-43)
            int r26 = r26 + r7
            int r27 = r27 + 1
        L_0x038d:
            int r5 = r5 + 1
            goto L_0x0360
        L_0x0391:
            if (r27 == 0) goto L_0x03a6
            int r28 = r28 / r27
            int r5 = r28 << 24
            int r24 = r24 / r27
            int r6 = r24 << 16
            r5 = r5 | r6
            int r25 = r25 / r27
            int r6 = r25 << 8
            r5 = r5 | r6
            int r26 = r26 / r27
            r5 = r5 | r26
            goto L_0x03a8
        L_0x03a6:
            r5 = 0
        L_0x03a8:
            if (r5 == 0) goto L_0x03ad
            r0[r9] = r5     // Catch:{ all -> 0x0509 }
            goto L_0x03b7
        L_0x03ad:
            if (r8 != 0) goto L_0x03b7
            if (r15 != 0) goto L_0x03b7
            r5 = 1
            java.lang.Boolean r6 = java.lang.Boolean.valueOf(r5)     // Catch:{ all -> 0x0509 }
            r15 = r6
        L_0x03b7:
            int r13 = r13 + r3
            int r9 = r9 + 1
            r6 = r23
            r7 = r29
            r10 = r30
            r11 = r32
            r5 = r35
            goto L_0x02fb
        L_0x03c6:
            r35 = r5
            r23 = r6
            r29 = r7
            r30 = r10
            r32 = r11
        L_0x03d0:
            if (r9 < r2) goto L_0x03d4
        L_0x03d2:
            r5 = 1
            goto L_0x0401
        L_0x03d4:
            byte r4 = r12[r13]     // Catch:{ all -> 0x0509 }
            r5 = 255(0xff, float:3.57E-43)
            r4 = r4 & r5
            r4 = r16[r4]     // Catch:{ all -> 0x0509 }
            if (r4 == 0) goto L_0x03e1
            r0[r9] = r4     // Catch:{ all -> 0x0509 }
            r5 = 1
            goto L_0x03ed
        L_0x03e1:
            if (r8 != 0) goto L_0x03ec
            if (r15 != 0) goto L_0x03ec
            r5 = 1
            java.lang.Boolean r4 = java.lang.Boolean.valueOf(r5)     // Catch:{ all -> 0x0509 }
            r15 = r4
            goto L_0x03ed
        L_0x03ec:
            r5 = 1
        L_0x03ed:
            int r13 = r13 + 1
            int r9 = r9 + 1
            goto L_0x03d0
        L_0x03f2:
            r20 = r2
            r22 = r4
            r35 = r5
            r23 = r6
            r29 = r7
            r30 = r10
            r32 = r11
            r5 = 1
        L_0x0401:
            int r14 = r14 + 1
            r13 = r20
            r2 = r21
            r4 = r22
            r6 = r23
            r7 = r29
            r10 = r30
            r11 = r32
            r9 = r34
            r5 = r35
            goto L_0x0253
        L_0x0418:
            r0 = r2
            r35 = r5
            r32 = r8
            r34 = r9
            r33 = r15
            r5 = 1
        L_0x0422:
            byte r2 = r4[r22]     // Catch:{ all -> 0x0509 }
            r3 = 255(0xff, float:3.57E-43)
            r2 = r2 & r3
            int r2 = r2 << r26
            int r27 = r27 + r2
            int r26 = r26 + 8
            int r22 = r22 + 1
            r2 = -1
            int r21 = r21 + -1
            r2 = r0
            r0 = r24
            r3 = r25
            r9 = r26
            r15 = r28
            r5 = r30
            r8 = r32
        L_0x043f:
            if (r9 < r0) goto L_0x04cc
            r24 = r4
            r4 = r27 & r23
            int r27 = r27 >> r0
            int r9 = r9 - r0
            if (r4 != r12) goto L_0x0454
            r5 = r18
            r23 = r19
            r4 = r24
            r0 = r33
            r3 = -1
            goto L_0x043f
        L_0x0454:
            if (r4 == r6) goto L_0x04bf
            r25 = r6
            r6 = -1
            if (r3 != r6) goto L_0x046b
            byte r3 = r13[r4]     // Catch:{ all -> 0x0509 }
            r7[r2] = r3     // Catch:{ all -> 0x0509 }
            int r2 = r2 + 1
            int r8 = r8 + 1
            r3 = r4
            r15 = r3
            r4 = r24
            r6 = r25
            goto L_0x043f
        L_0x046b:
            if (r4 < r5) goto L_0x0474
            byte r15 = (byte) r15     // Catch:{ all -> 0x0509 }
            r14[r29] = r15     // Catch:{ all -> 0x0509 }
            int r29 = r29 + 1
            r15 = r3
            goto L_0x0475
        L_0x0474:
            r15 = r4
        L_0x0475:
            if (r15 < r12) goto L_0x0480
            byte r20 = r13[r15]     // Catch:{ all -> 0x0509 }
            r14[r29] = r20     // Catch:{ all -> 0x0509 }
            int r29 = r29 + 1
            short r15 = r11[r15]     // Catch:{ all -> 0x0509 }
            goto L_0x0475
        L_0x0480:
            byte r15 = r13[r15]     // Catch:{ all -> 0x0509 }
            r6 = 255(0xff, float:3.57E-43)
            r15 = r15 & r6
            byte r6 = (byte) r15     // Catch:{ all -> 0x0509 }
            r7[r2] = r6     // Catch:{ all -> 0x0509 }
            int r2 = r2 + 1
            int r8 = r8 + 1
        L_0x048c:
            if (r29 <= 0) goto L_0x0499
            int r29 = r29 + -1
            byte r26 = r14[r29]     // Catch:{ all -> 0x0509 }
            r7[r2] = r26     // Catch:{ all -> 0x0509 }
            int r2 = r2 + 1
            int r8 = r8 + 1
            goto L_0x048c
        L_0x0499:
            r26 = r2
            r2 = 4096(0x1000, float:5.74E-42)
            if (r5 >= r2) goto L_0x04b4
            short r2 = (short) r3     // Catch:{ all -> 0x0509 }
            r11[r5] = r2     // Catch:{ all -> 0x0509 }
            r13[r5] = r6     // Catch:{ all -> 0x0509 }
            int r5 = r5 + 1
            r2 = r5 & r23
            r6 = 4096(0x1000, float:5.74E-42)
            if (r2 == 0) goto L_0x04ad
            goto L_0x04b6
        L_0x04ad:
            if (r5 >= r6) goto L_0x04b6
            int r0 = r0 + 1
            int r23 = r23 + r5
            goto L_0x04b6
        L_0x04b4:
            r6 = 4096(0x1000, float:5.74E-42)
        L_0x04b6:
            r3 = r4
            r4 = r24
            r6 = r25
            r2 = r26
            goto L_0x043f
        L_0x04bf:
            r25 = r6
            r6 = 4096(0x1000, float:5.74E-42)
            r30 = r5
            r26 = r9
            r28 = r15
            r4 = r24
            goto L_0x04d8
        L_0x04cc:
            r24 = r4
            r25 = r6
            r6 = 4096(0x1000, float:5.74E-42)
            r30 = r5
            r26 = r9
            r28 = r15
        L_0x04d8:
            r6 = r25
            r15 = r33
            r9 = r34
            r5 = r35
            r24 = r0
            r25 = r3
            r0 = 0
            r3 = 3
            goto L_0x0176
        L_0x04e8:
            r35 = r5
            r25 = r6
            r34 = r9
            r33 = r15
            r6 = 4096(0x1000, float:5.74E-42)
            r0 = 0
            r11[r4] = r0     // Catch:{ all -> 0x0509 }
            byte r2 = (byte) r4     // Catch:{ all -> 0x0509 }
            r13[r4] = r2     // Catch:{ all -> 0x0509 }
            int r4 = r4 + 1
            r6 = r25
            r15 = r33
            r9 = r34
            r5 = r35
            r2 = 1
            r3 = 3
            r8 = -1
            goto L_0x015c
        L_0x0507:
            monitor-exit(r36)
            return r3
        L_0x0509:
            r0 = move-exception
            monitor-exit(r36)
            goto L_0x050d
        L_0x050c:
            throw r0
        L_0x050d:
            goto L_0x050c
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.aqd.mo1477b():android.graphics.Bitmap");
    }

    /* renamed from: c */
    private final int m1447c() {
        return this.f1431b.get() & 255;
    }

    /* renamed from: a */
    private final synchronized void m1446a(aqb aqb, ByteBuffer byteBuffer, int i) {
        if (i > 0) {
            int highestOneBit = Integer.highestOneBit(i);
            int i2 = 0;
            this.f1446q = 0;
            this.f1436g = aqb;
            this.f1435f = -1;
            ByteBuffer asReadOnlyBuffer = byteBuffer.asReadOnlyBuffer();
            this.f1431b = asReadOnlyBuffer;
            asReadOnlyBuffer.position(0);
            this.f1431b.order(ByteOrder.LITTLE_ENDIAN);
            this.f1445p = false;
            List list = aqb.f1417e;
            int size = list.size();
            while (true) {
                if (i2 >= size) {
                    break;
                }
                int i3 = i2 + 1;
                if (((aqa) list.get(i2)).f1408g == 3) {
                    this.f1445p = true;
                    break;
                }
                i2 = i3;
            }
            this.f1447r = highestOneBit;
            int i4 = aqb.f1418f;
            this.f1449t = i4 / highestOneBit;
            int i5 = aqb.f1419g;
            this.f1448s = i5 / highestOneBit;
            this.f1433d = this.f1430a.mo1475a(i4 * i5);
            apy apy = this.f1430a;
            int i6 = this.f1449t * this.f1448s;
            aui aui = ((bbr) apy).f2004b;
            this.f1434e = aui != null ? (int[]) aui.mo1634a(i6, int[].class) : new int[i6];
        } else {
            StringBuilder sb = new StringBuilder(41);
            sb.append("Sample size must be >=0, not: ");
            sb.append(i);
            throw new IllegalArgumentException(sb.toString());
        }
    }
}
