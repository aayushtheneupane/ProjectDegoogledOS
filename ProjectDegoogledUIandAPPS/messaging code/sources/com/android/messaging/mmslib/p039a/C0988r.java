package com.android.messaging.mmslib.p039a;

import android.util.Log;
import android.util.SparseArray;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;

/* renamed from: com.android.messaging.mmslib.a.r */
public class C0988r {

    /* renamed from: km */
    private static byte[] f1418km;

    /* renamed from: lm */
    private static byte[] f1419lm;

    /* renamed from: gm */
    private ByteArrayInputStream f1420gm = null;

    /* renamed from: hm */
    private C0987q f1421hm = null;

    /* renamed from: im */
    private C0980j f1422im = null;

    /* renamed from: jm */
    private final boolean f1423jm;

    public C0988r(byte[] bArr, boolean z) {
        this.f1420gm = new ByteArrayInputStream(bArr);
        this.f1423jm = z;
    }

    /* renamed from: a */
    protected static C0975e m2260a(ByteArrayInputStream byteArrayInputStream) {
        int i;
        byteArrayInputStream.mark(1);
        int read = byteArrayInputStream.read() & 255;
        if (read == 0) {
            return null;
        }
        byteArrayInputStream.reset();
        if (read < 32) {
            m2267f(byteArrayInputStream);
            i = byteArrayInputStream.read() & 127;
        } else {
            i = 0;
        }
        byte[] a = m2261a(byteArrayInputStream, 0);
        if (i == 0) {
            return new C0975e(106, a);
        }
        try {
            return new C0975e(i, a);
        } catch (Exception unused) {
            return null;
        }
    }

    /* renamed from: b */
    protected static long m2264b(ByteArrayInputStream byteArrayInputStream) {
        byteArrayInputStream.mark(1);
        int read = byteArrayInputStream.read();
        byteArrayInputStream.reset();
        if (read > 127) {
            return (long) (byteArrayInputStream.read() & 127);
        }
        return m2265c(byteArrayInputStream);
    }

    /* renamed from: c */
    protected static long m2265c(ByteArrayInputStream byteArrayInputStream) {
        int read = byteArrayInputStream.read() & 255;
        if (read <= 8) {
            long j = 0;
            for (int i = 0; i < read; i++) {
                j = (j << 8) + ((long) (byteArrayInputStream.read() & 255));
            }
            return j;
        }
        throw new RuntimeException("Octet count greater than 8 and I can't represent that!");
    }

    /* renamed from: e */
    protected static int m2266e(ByteArrayInputStream byteArrayInputStream) {
        int i = 0;
        int read = byteArrayInputStream.read();
        if (read == -1) {
            return read;
        }
        while ((read & 128) != 0) {
            i = (i << 7) | (read & 127);
            read = byteArrayInputStream.read();
            if (read == -1) {
                return read;
            }
        }
        return (i << 7) | (read & 127);
    }

    /* renamed from: f */
    protected static int m2267f(ByteArrayInputStream byteArrayInputStream) {
        int read = byteArrayInputStream.read() & 255;
        if (read <= 30) {
            return read;
        }
        if (read == 31) {
            return m2266e(byteArrayInputStream);
        }
        throw new RuntimeException("Value length > LENGTH_QUOTE!");
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:146:0x0268, code lost:
        if (true == java.util.Arrays.equals(f1419lm, r2)) goto L_0x026a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:154:0x027f, code lost:
        if (true == java.util.Arrays.equals(f1418km, r2)) goto L_0x026a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:83:0x0179, code lost:
        r2 = false;
     */
    /* JADX WARNING: Removed duplicated region for block: B:135:0x0248  */
    /* JADX WARNING: Removed duplicated region for block: B:143:0x025b  */
    /* JADX WARNING: Removed duplicated region for block: B:149:0x026e  */
    /* JADX WARNING: Removed duplicated region for block: B:156:0x0284  */
    /* JADX WARNING: Removed duplicated region for block: B:157:0x0289  */
    /* JADX WARNING: Removed duplicated region for block: B:162:0x0246 A[SYNTHETIC] */
    /* renamed from: d */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.android.messaging.mmslib.p039a.C0980j mo6721d(java.io.ByteArrayInputStream r19) {
        /*
            r18 = this;
            r0 = r18
            r1 = r19
            r2 = 0
            if (r1 != 0) goto L_0x0008
            return r2
        L_0x0008:
            int r3 = m2266e(r19)
            com.android.messaging.mmslib.a.j r4 = new com.android.messaging.mmslib.a.j
            r4.<init>()
            r5 = 0
            r6 = r5
        L_0x0013:
            if (r6 >= r3) goto L_0x0296
            int r7 = m2266e(r19)
            int r8 = m2266e(r19)
            com.android.messaging.mmslib.a.s r9 = new com.android.messaging.mmslib.a.s
            r9.<init>()
            int r10 = r19.available()
            if (r10 > 0) goto L_0x0029
            return r2
        L_0x0029:
            android.util.SparseArray r11 = new android.util.SparseArray
            r11.<init>()
            byte[] r12 = m2262a((java.io.ByteArrayInputStream) r1, (android.util.SparseArray) r11)
            if (r12 == 0) goto L_0x0038
            r9.mo6728g(r12)
            goto L_0x0043
        L_0x0038:
            java.lang.String[] r12 = com.android.messaging.mmslib.p039a.C0986p.f1416em
            r12 = r12[r5]
            byte[] r12 = r12.getBytes()
            r9.mo6728g(r12)
        L_0x0043:
            r12 = 151(0x97, float:2.12E-43)
            java.lang.Object r12 = r11.get(r12)
            byte[] r12 = (byte[]) r12
            if (r12 == 0) goto L_0x0050
            r9.mo6737i(r12)
        L_0x0050:
            r12 = 129(0x81, float:1.81E-43)
            java.lang.Object r11 = r11.get(r12)
            java.lang.Integer r11 = (java.lang.Integer) r11
            if (r11 == 0) goto L_0x0061
            int r11 = r11.intValue()
            r9.mo6723H(r11)
        L_0x0061:
            int r11 = r19.available()
            int r10 = r10 - r11
            int r7 = r7 - r10
            r10 = -1
            r11 = 1
            if (r7 <= 0) goto L_0x0180
            int r13 = r19.available()
            r14 = r7
        L_0x0070:
            java.lang.String r15 = "Corrupt Part headers"
            java.lang.String r2 = "PduParser"
            if (r14 <= 0) goto L_0x0174
            int r5 = r19.read()
            int r14 = r14 + -1
            r12 = 127(0x7f, float:1.78E-43)
            if (r5 <= r12) goto L_0x012e
            r12 = 142(0x8e, float:1.99E-43)
            if (r5 == r12) goto L_0x011d
            r12 = 174(0xae, float:2.44E-43)
            if (r5 == r12) goto L_0x00ae
            r12 = 192(0xc0, float:2.69E-43)
            if (r5 == r12) goto L_0x009f
            r12 = 197(0xc5, float:2.76E-43)
            if (r5 == r12) goto L_0x00ae
            int r5 = m2263b(r1, r14)
            if (r10 != r5) goto L_0x009b
            android.util.Log.e(r2, r15)
            goto L_0x0179
        L_0x009b:
            r16 = 129(0x81, float:1.81E-43)
            goto L_0x016b
        L_0x009f:
            byte[] r2 = m2261a((java.io.ByteArrayInputStream) r1, (int) r11)
            if (r2 == 0) goto L_0x00a8
            r9.mo6725d(r2)
        L_0x00a8:
            int r2 = r19.available()
            goto L_0x012b
        L_0x00ae:
            boolean r2 = r0.f1423jm
            if (r2 == 0) goto L_0x0117
            int r2 = m2267f(r19)
            r1.mark(r11)
            int r5 = r19.available()
            int r12 = r19.read()
            r14 = 128(0x80, float:1.794E-43)
            if (r12 != r14) goto L_0x00ce
            byte[] r12 = com.android.messaging.mmslib.p039a.C0989s.f1424om
            r9.mo6724c(r12)
            r12 = 0
            r15 = 129(0x81, float:1.81E-43)
            goto L_0x00ee
        L_0x00ce:
            r15 = 129(0x81, float:1.81E-43)
            if (r12 != r15) goto L_0x00d9
            byte[] r12 = com.android.messaging.mmslib.p039a.C0989s.f1425qm
            r9.mo6724c(r12)
        L_0x00d7:
            r12 = 0
            goto L_0x00ee
        L_0x00d9:
            r14 = 130(0x82, float:1.82E-43)
            if (r12 != r14) goto L_0x00e3
            byte[] r12 = com.android.messaging.mmslib.p039a.C0989s.f1426rm
            r9.mo6724c(r12)
            goto L_0x00d7
        L_0x00e3:
            r19.reset()
            r12 = 0
            byte[] r14 = m2261a((java.io.ByteArrayInputStream) r1, (int) r12)
            r9.mo6724c(r14)
        L_0x00ee:
            int r14 = r19.available()
            int r14 = r5 - r14
            if (r14 >= r2) goto L_0x0112
            int r14 = r19.read()
            r15 = 152(0x98, float:2.13E-43)
            if (r14 != r15) goto L_0x0105
            byte[] r14 = m2261a((java.io.ByteArrayInputStream) r1, (int) r12)
            r9.mo6736h((byte[]) r14)
        L_0x0105:
            int r14 = r19.available()
            int r5 = r5 - r14
            if (r5 >= r2) goto L_0x0112
            int r2 = r2 - r5
            byte[] r5 = new byte[r2]
            r1.read(r5, r12, r2)
        L_0x0112:
            int r2 = r19.available()
            goto L_0x012b
        L_0x0117:
            r2 = 0
            r5 = 0
            r12 = 129(0x81, float:1.81E-43)
            goto L_0x0070
        L_0x011d:
            r12 = 0
            byte[] r2 = m2261a((java.io.ByteArrayInputStream) r1, (int) r12)
            if (r2 == 0) goto L_0x0127
            r9.mo6726e(r2)
        L_0x0127:
            int r2 = r19.available()
        L_0x012b:
            r16 = 129(0x81, float:1.81E-43)
            goto L_0x0154
        L_0x012e:
            r10 = 0
            r16 = 129(0x81, float:1.81E-43)
            r11 = 32
            if (r5 < r11) goto L_0x0160
            if (r5 > r12) goto L_0x0160
            byte[] r2 = m2261a((java.io.ByteArrayInputStream) r1, (int) r10)
            byte[] r5 = m2261a((java.io.ByteArrayInputStream) r1, (int) r10)
            java.lang.String r10 = new java.lang.String
            r10.<init>(r2)
            java.lang.String r2 = "Content-Transfer-Encoding"
            boolean r2 = r2.equalsIgnoreCase(r10)
            r10 = 1
            if (r10 != r2) goto L_0x0150
            r9.mo6727f(r5)
        L_0x0150:
            int r2 = r19.available()
        L_0x0154:
            int r2 = r13 - r2
            int r14 = r7 - r2
            r12 = r16
            r2 = 0
            r5 = 0
            r10 = -1
            r11 = 1
            goto L_0x0070
        L_0x0160:
            int r5 = m2263b(r1, r14)
            r10 = -1
            if (r10 != r5) goto L_0x016b
            android.util.Log.e(r2, r15)
            goto L_0x0179
        L_0x016b:
            r12 = r16
            r2 = 0
            r5 = 0
            r10 = -1
            r11 = 1
            r14 = 0
            goto L_0x0070
        L_0x0174:
            if (r14 == 0) goto L_0x017b
            android.util.Log.e(r2, r15)
        L_0x0179:
            r2 = 0
            goto L_0x017c
        L_0x017b:
            r2 = 1
        L_0x017c:
            if (r2 != 0) goto L_0x0183
            r2 = 0
            return r2
        L_0x0180:
            if (r7 >= 0) goto L_0x0183
            return r2
        L_0x0183:
            byte[] r2 = r9.mo6739jc()
            if (r2 != 0) goto L_0x01aa
            byte[] r2 = r9.getName()
            if (r2 != 0) goto L_0x01aa
            byte[] r2 = r9.mo6741lc()
            if (r2 != 0) goto L_0x01aa
            byte[] r2 = r9.mo6738ic()
            if (r2 != 0) goto L_0x01aa
            long r10 = java.lang.System.currentTimeMillis()
            java.lang.String r2 = java.lang.Long.toOctalString(r10)
            byte[] r2 = r2.getBytes()
            r9.mo6726e(r2)
        L_0x01aa:
            if (r8 <= 0) goto L_0x024d
            byte[] r2 = new byte[r8]
            java.lang.String r5 = new java.lang.String
            byte[] r7 = r9.getContentType()
            r5.<init>(r7)
            r7 = 0
            r1.read(r2, r7, r8)
            java.lang.String r8 = "application/vnd.wap.multipart.alternative"
            boolean r5 = r5.equalsIgnoreCase(r8)
            if (r5 == 0) goto L_0x01d2
            java.io.ByteArrayInputStream r5 = new java.io.ByteArrayInputStream
            r5.<init>(r2)
            com.android.messaging.mmslib.a.j r2 = r0.mo6721d(r5)
            com.android.messaging.mmslib.a.s r9 = r2.getPart(r7)
            goto L_0x024d
        L_0x01d2:
            byte[] r5 = r9.mo6740kc()
            if (r5 == 0) goto L_0x0244
            java.lang.String r7 = new java.lang.String
            r7.<init>(r5)
            java.lang.String r5 = "base64"
            boolean r5 = r7.equalsIgnoreCase(r5)
            if (r5 == 0) goto L_0x01ea
            byte[] r2 = com.android.messaging.mmslib.p039a.C0972b.m2207a(r2)
            goto L_0x0244
        L_0x01ea:
            java.lang.String r5 = "quoted-printable"
            boolean r5 = r7.equalsIgnoreCase(r5)
            if (r5 == 0) goto L_0x0244
            java.io.ByteArrayOutputStream r5 = new java.io.ByteArrayOutputStream
            r5.<init>()
            r7 = 0
        L_0x01f8:
            int r8 = r2.length
            if (r7 >= r8) goto L_0x0240
            byte r8 = r2[r7]
            r10 = 61
            if (r8 != r10) goto L_0x0239
            r8 = 13
            int r10 = r7 + 1
            byte r11 = r2[r10]     // Catch:{ ArrayIndexOutOfBoundsException -> 0x0237 }
            char r11 = (char) r11     // Catch:{ ArrayIndexOutOfBoundsException -> 0x0237 }
            if (r8 != r11) goto L_0x0216
            r8 = 10
            int r7 = r7 + 2
            byte r11 = r2[r7]     // Catch:{ ArrayIndexOutOfBoundsException -> 0x0237 }
            char r11 = (char) r11     // Catch:{ ArrayIndexOutOfBoundsException -> 0x0237 }
            if (r8 != r11) goto L_0x0216
            r8 = 1
            r11 = -1
            goto L_0x023e
        L_0x0216:
            byte r7 = r2[r10]     // Catch:{ ArrayIndexOutOfBoundsException -> 0x0237 }
            char r7 = (char) r7     // Catch:{ ArrayIndexOutOfBoundsException -> 0x0237 }
            r8 = 16
            int r7 = java.lang.Character.digit(r7, r8)     // Catch:{ ArrayIndexOutOfBoundsException -> 0x0237 }
            int r10 = r10 + 1
            byte r11 = r2[r10]     // Catch:{ ArrayIndexOutOfBoundsException -> 0x0237 }
            char r11 = (char) r11     // Catch:{ ArrayIndexOutOfBoundsException -> 0x0237 }
            int r8 = java.lang.Character.digit(r11, r8)     // Catch:{ ArrayIndexOutOfBoundsException -> 0x0237 }
            r11 = -1
            if (r7 == r11) goto L_0x0237
            if (r8 != r11) goto L_0x022e
            goto L_0x0237
        L_0x022e:
            int r7 = r7 << 4
            int r7 = r7 + r8
            char r7 = (char) r7     // Catch:{ ArrayIndexOutOfBoundsException -> 0x0237 }
            r5.write(r7)     // Catch:{ ArrayIndexOutOfBoundsException -> 0x0237 }
            r7 = r10
            goto L_0x023d
        L_0x0237:
            r2 = 0
            goto L_0x0244
        L_0x0239:
            r11 = -1
            r5.write(r8)
        L_0x023d:
            r8 = 1
        L_0x023e:
            int r7 = r7 + r8
            goto L_0x01f8
        L_0x0240:
            byte[] r2 = r5.toByteArray()
        L_0x0244:
            if (r2 != 0) goto L_0x0248
            r5 = 0
            return r5
        L_0x0248:
            r5 = 0
            r9.setData(r2)
            goto L_0x024e
        L_0x024d:
            r5 = 0
        L_0x024e:
            byte[] r2 = f1418km
            if (r2 != 0) goto L_0x0257
            byte[] r2 = f1419lm
            if (r2 != 0) goto L_0x0257
            goto L_0x026c
        L_0x0257:
            byte[] r2 = f1419lm
            if (r2 == 0) goto L_0x026e
            byte[] r2 = r9.mo6738ic()
            if (r2 == 0) goto L_0x026c
            byte[] r7 = f1419lm
            boolean r2 = java.util.Arrays.equals(r7, r2)
            r10 = 1
            if (r10 != r2) goto L_0x0282
        L_0x026a:
            r10 = 0
            goto L_0x0282
        L_0x026c:
            r10 = 1
            goto L_0x0282
        L_0x026e:
            r10 = 1
            byte[] r2 = f1418km
            if (r2 == 0) goto L_0x0282
            byte[] r2 = r9.getContentType()
            if (r2 == 0) goto L_0x0282
            byte[] r7 = f1418km
            boolean r2 = java.util.Arrays.equals(r7, r2)
            if (r10 != r2) goto L_0x0282
            goto L_0x026a
        L_0x0282:
            if (r10 != 0) goto L_0x0289
            r2 = 0
            r4.mo6688a(r2, r9)
            goto L_0x028d
        L_0x0289:
            r2 = 0
            r4.mo6689a(r9)
        L_0x028d:
            int r6 = r6 + 1
            r17 = r5
            r5 = r2
            r2 = r17
            goto L_0x0013
        L_0x0296:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.messaging.mmslib.p039a.C0988r.mo6721d(java.io.ByteArrayInputStream):com.android.messaging.mmslib.a.j");
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:108:0x01b6, code lost:
        p026b.p027a.p030b.p031a.C0632a.m1024d(r6, "is not Encoded-String-Value header field!");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:163:0x0288, code lost:
        p026b.p027a.p030b.p031a.C0632a.m1024d(r6, "is not Encoded-String-Value header field!");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:165:0x028d, code lost:
        r4 = 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:187:0x02ed, code lost:
        if (r3.mo6711D(151) == null) goto L_0x03b7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:195:0x030f, code lost:
        if (r3.mo6711D(151) == null) goto L_0x03b7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:203:0x0331, code lost:
        if (r3.mo6711D(151) == null) goto L_0x03b7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:205:0x0339, code lost:
        if (r3.mo6714G(152) == null) goto L_0x03b7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:209:0x034d, code lost:
        if (-1 == r3.mo6712E(133)) goto L_0x03b7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:213:0x035d, code lost:
        if (r3.mo6714G(152) == null) goto L_0x03b7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:223:0x038c, code lost:
        if (r3.mo6714G(152) == null) goto L_0x03b7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:227:0x039c, code lost:
        if (r3.mo6714G(152) == null) goto L_0x03b7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:233:0x03b5, code lost:
        if (r3.mo6714G(152) == null) goto L_0x03b7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x00a3, code lost:
        p026b.p027a.p030b.p031a.C0632a.m1024d(r6, "is not Encoded-String-Value header field!");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:83:0x0157, code lost:
        p026b.p027a.p030b.p031a.C0632a.m1024d(r6, "is not Text-String header field!");
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:166:? A[ExcHandler: NullPointerException (unused java.lang.NullPointerException), SYNTHETIC, Splitter:B:39:0x009e] */
    /* JADX WARNING: Removed duplicated region for block: B:168:? A[ExcHandler: NullPointerException (unused java.lang.NullPointerException), PHI: r4 
      PHI: (r4v12 int) = (r4v14 int), (r4v14 int), (r4v23 int), (r4v23 int), (r4v24 int), (r4v24 int), (r4v24 int) binds: [B:161:0x0284, B:162:?, B:106:0x01b1, B:107:?, B:88:0x0167, B:89:?, B:73:0x011b] A[DONT_GENERATE, DONT_INLINE], SYNTHETIC, Splitter:B:73:0x011b] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.android.messaging.mmslib.p039a.C0976f parse() {
        /*
            r15 = this;
            java.io.ByteArrayInputStream r0 = r15.f1420gm
            r1 = 0
            if (r0 != 0) goto L_0x0006
            return r1
        L_0x0006:
            com.android.messaging.mmslib.a.q r2 = new com.android.messaging.mmslib.a.q
            r2.<init>()
            r3 = 1
            r4 = 0
            r5 = r4
            r4 = r3
        L_0x000f:
            if (r3 == 0) goto L_0x0291
            int r6 = r0.available()
            if (r6 <= 0) goto L_0x0291
            r0.mark(r4)
            int r6 = r0.read()
            r6 = r6 & 255(0xff, float:3.57E-43)
            r7 = 32
            r8 = 127(0x7f, float:1.78E-43)
            if (r6 < r7) goto L_0x0030
            if (r6 > r8) goto L_0x0030
            r0.reset()
            m2261a((java.io.ByteArrayInputStream) r0, (int) r5)
            goto L_0x028e
        L_0x0030:
            java.lang.String r4 = " is not Integer-Value"
            java.lang.String r5 = "is not Octet header field!"
            java.lang.String r7 = " into the header filed: "
            java.lang.String r9 = "Set invalid Octet value: "
            java.lang.String r10 = "is not Text-String header field!"
            java.lang.String r11 = "is not Encoded-String-Value header field!"
            java.lang.String r12 = "is not Long-Integer header field!"
            java.lang.String r13 = "/"
            r14 = 129(0x81, float:1.81E-43)
            switch(r6) {
                case 129: goto L_0x0260;
                case 130: goto L_0x0260;
                case 131: goto L_0x024c;
                case 132: goto L_0x021d;
                case 133: goto L_0x020e;
                case 134: goto L_0x01e7;
                case 135: goto L_0x01c0;
                case 136: goto L_0x01c0;
                case 137: goto L_0x0170;
                case 138: goto L_0x0109;
                case 139: goto L_0x024c;
                case 140: goto L_0x00df;
                case 141: goto L_0x00ba;
                case 142: goto L_0x020e;
                case 143: goto L_0x01e7;
                case 144: goto L_0x01e7;
                case 145: goto L_0x01e7;
                case 146: goto L_0x01e7;
                case 147: goto L_0x00ab;
                case 148: goto L_0x01e7;
                case 149: goto L_0x01e7;
                case 150: goto L_0x00ab;
                case 151: goto L_0x0260;
                case 152: goto L_0x024c;
                case 153: goto L_0x01e7;
                case 154: goto L_0x00ab;
                case 155: goto L_0x01e7;
                case 156: goto L_0x01e7;
                case 157: goto L_0x01c0;
                case 158: goto L_0x024c;
                case 159: goto L_0x020e;
                case 160: goto L_0x0090;
                case 161: goto L_0x0075;
                case 162: goto L_0x01e7;
                case 163: goto L_0x01e7;
                case 164: goto L_0x006a;
                case 165: goto L_0x01e7;
                case 166: goto L_0x00ab;
                case 167: goto L_0x01e7;
                case 168: goto L_0x0045;
                case 169: goto L_0x01e7;
                case 170: goto L_0x005a;
                case 171: goto L_0x01e7;
                case 172: goto L_0x005a;
                case 173: goto L_0x004c;
                case 174: goto L_0x0045;
                case 175: goto L_0x004c;
                case 176: goto L_0x0045;
                case 177: goto L_0x01e7;
                case 178: goto L_0x0047;
                case 179: goto L_0x004c;
                case 180: goto L_0x01e7;
                case 181: goto L_0x00ab;
                case 182: goto L_0x00ab;
                case 183: goto L_0x024c;
                case 184: goto L_0x024c;
                case 185: goto L_0x024c;
                case 186: goto L_0x01e7;
                case 187: goto L_0x01e7;
                case 188: goto L_0x01e7;
                case 189: goto L_0x024c;
                case 190: goto L_0x024c;
                case 191: goto L_0x01e7;
                default: goto L_0x0045;
            }
        L_0x0045:
            goto L_0x028d
        L_0x0047:
            m2262a((java.io.ByteArrayInputStream) r0, (android.util.SparseArray) r1)
            goto L_0x028d
        L_0x004c:
            long r4 = m2264b(r0)     // Catch:{ RuntimeException -> 0x0055 }
            r2.mo6715a((long) r4, (int) r6)     // Catch:{ RuntimeException -> 0x0055 }
            goto L_0x028d
        L_0x0055:
            p026b.p027a.p030b.p031a.C0632a.m1024d(r6, r12)
            goto L_0x0105
        L_0x005a:
            m2267f(r0)
            r0.read()
            m2264b(r0)     // Catch:{ RuntimeException -> 0x0065 }
            goto L_0x028d
        L_0x0065:
            p026b.p027a.p030b.p031a.C0632a.m1024d(r6, r4)
            goto L_0x0105
        L_0x006a:
            m2267f(r0)
            r0.read()
            m2260a(r0)
            goto L_0x028d
        L_0x0075:
            m2267f(r0)
            m2264b(r0)     // Catch:{ RuntimeException -> 0x008b }
            long r4 = m2265c(r0)     // Catch:{ RuntimeException -> 0x0086 }
            r7 = 161(0xa1, float:2.26E-43)
            r2.mo6715a((long) r4, (int) r7)     // Catch:{ RuntimeException -> 0x0086 }
            goto L_0x028d
        L_0x0086:
            p026b.p027a.p030b.p031a.C0632a.m1024d(r6, r12)
            goto L_0x0105
        L_0x008b:
            p026b.p027a.p030b.p031a.C0632a.m1024d(r6, r4)
            goto L_0x0105
        L_0x0090:
            m2267f(r0)
            m2264b(r0)     // Catch:{ RuntimeException -> 0x00a7 }
            com.android.messaging.mmslib.a.e r4 = m2260a(r0)
            if (r4 == 0) goto L_0x028d
            r5 = 160(0xa0, float:2.24E-43)
            r2.mo6718b((com.android.messaging.mmslib.p039a.C0975e) r4, (int) r5)     // Catch:{ NullPointerException -> 0x028d, RuntimeException -> 0x00a3 }
            goto L_0x028d
        L_0x00a3:
            p026b.p027a.p030b.p031a.C0632a.m1024d(r6, r11)
            goto L_0x0105
        L_0x00a7:
            p026b.p027a.p030b.p031a.C0632a.m1024d(r6, r4)
            goto L_0x0105
        L_0x00ab:
            com.android.messaging.mmslib.a.e r4 = m2260a(r0)
            if (r4 == 0) goto L_0x028d
            r2.mo6718b((com.android.messaging.mmslib.p039a.C0975e) r4, (int) r6)     // Catch:{ NullPointerException -> 0x028d, RuntimeException -> 0x00b6 }
            goto L_0x028d
        L_0x00b6:
            p026b.p027a.p030b.p031a.C0632a.m1024d(r6, r11)
            goto L_0x0105
        L_0x00ba:
            int r4 = r0.read()
            r4 = r4 & r8
            r8 = 141(0x8d, float:1.98E-43)
            r2.mo6720e(r4, r8)     // Catch:{ InvalidHeaderValueException -> 0x00ca, RuntimeException -> 0x00c6 }
            goto L_0x028d
        L_0x00c6:
            p026b.p027a.p030b.p031a.C0632a.m1024d(r6, r5)
            goto L_0x0105
        L_0x00ca:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            r0.append(r9)
            r0.append(r4)
            r0.append(r7)
            r0.append(r6)
            r0.toString()
            goto L_0x0105
        L_0x00df:
            int r4 = r0.read()
            r4 = r4 & 255(0xff, float:3.57E-43)
            switch(r4) {
                case 137: goto L_0x0105;
                case 138: goto L_0x0105;
                case 139: goto L_0x0105;
                case 140: goto L_0x0105;
                case 141: goto L_0x0105;
                case 142: goto L_0x0105;
                case 143: goto L_0x0105;
                case 144: goto L_0x0105;
                case 145: goto L_0x0105;
                case 146: goto L_0x0105;
                case 147: goto L_0x0105;
                case 148: goto L_0x0105;
                case 149: goto L_0x0105;
                case 150: goto L_0x0105;
                case 151: goto L_0x0105;
                default: goto L_0x00e8;
            }
        L_0x00e8:
            r2.mo6720e(r4, r6)     // Catch:{ InvalidHeaderValueException -> 0x00f1, RuntimeException -> 0x00ed }
            goto L_0x028d
        L_0x00ed:
            p026b.p027a.p030b.p031a.C0632a.m1024d(r6, r5)
            goto L_0x0105
        L_0x00f1:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            r0.append(r9)
            r0.append(r4)
            r0.append(r7)
            r0.append(r6)
            r0.toString()
        L_0x0105:
            r4 = 1
        L_0x0106:
            r2 = r1
            goto L_0x0291
        L_0x0109:
            r4 = 1
            r0.mark(r4)
            int r5 = r0.read()
            r5 = r5 & 255(0xff, float:3.57E-43)
            r7 = 128(0x80, float:1.794E-43)
            if (r5 < r7) goto L_0x015b
            if (r7 != r5) goto L_0x0126
            java.lang.String r5 = "personal"
            byte[] r5 = r5.getBytes()     // Catch:{ NullPointerException -> 0x028e, RuntimeException -> 0x0157 }
            r7 = 138(0x8a, float:1.93E-43)
            r2.mo6719b((byte[]) r5, (int) r7)     // Catch:{ NullPointerException -> 0x028e, RuntimeException -> 0x0157 }
            goto L_0x028e
        L_0x0126:
            if (r14 != r5) goto L_0x0135
            java.lang.String r5 = "advertisement"
            byte[] r5 = r5.getBytes()     // Catch:{ NullPointerException -> 0x028e, RuntimeException -> 0x0157 }
            r7 = 138(0x8a, float:1.93E-43)
            r2.mo6719b((byte[]) r5, (int) r7)     // Catch:{ NullPointerException -> 0x028e, RuntimeException -> 0x0157 }
            goto L_0x028e
        L_0x0135:
            r7 = 130(0x82, float:1.82E-43)
            if (r7 != r5) goto L_0x0146
            java.lang.String r5 = "informational"
            byte[] r5 = r5.getBytes()     // Catch:{ NullPointerException -> 0x028e, RuntimeException -> 0x0157 }
            r7 = 138(0x8a, float:1.93E-43)
            r2.mo6719b((byte[]) r5, (int) r7)     // Catch:{ NullPointerException -> 0x028e, RuntimeException -> 0x0157 }
            goto L_0x028e
        L_0x0146:
            r7 = 131(0x83, float:1.84E-43)
            if (r7 != r5) goto L_0x028e
            java.lang.String r5 = "auto"
            byte[] r5 = r5.getBytes()     // Catch:{ NullPointerException -> 0x028e, RuntimeException -> 0x0157 }
            r7 = 138(0x8a, float:1.93E-43)
            r2.mo6719b((byte[]) r5, (int) r7)     // Catch:{ NullPointerException -> 0x028e, RuntimeException -> 0x0157 }
            goto L_0x028e
        L_0x0157:
            p026b.p027a.p030b.p031a.C0632a.m1024d(r6, r10)
            goto L_0x0106
        L_0x015b:
            r0.reset()
            r5 = 0
            byte[] r5 = m2261a((java.io.ByteArrayInputStream) r0, (int) r5)
            if (r5 == 0) goto L_0x028e
            r7 = 138(0x8a, float:1.93E-43)
            r2.mo6719b((byte[]) r5, (int) r7)     // Catch:{ NullPointerException -> 0x028e, RuntimeException -> 0x016c }
            goto L_0x028e
        L_0x016c:
            p026b.p027a.p030b.p031a.C0632a.m1024d(r6, r10)
            goto L_0x0106
        L_0x0170:
            r4 = 1
            m2267f(r0)
            int r5 = r0.read()
            r5 = r5 & 255(0xff, float:3.57E-43)
            r7 = 128(0x80, float:1.794E-43)
            if (r7 != r5) goto L_0x01a2
            com.android.messaging.mmslib.a.e r5 = m2260a(r0)
            if (r5 == 0) goto L_0x01af
            byte[] r7 = r5.mo6666fc()
            if (r7 == 0) goto L_0x01af
            java.lang.String r8 = new java.lang.String
            r8.<init>(r7)
            int r7 = r8.indexOf(r13)
            if (r7 <= 0) goto L_0x019a
            r9 = 0
            java.lang.String r8 = r8.substring(r9, r7)
        L_0x019a:
            byte[] r7 = r8.getBytes()     // Catch:{ NullPointerException -> 0x0106 }
            r5.mo6664b(r7)     // Catch:{ NullPointerException -> 0x0106 }
            goto L_0x01af
        L_0x01a2:
            com.android.messaging.mmslib.a.e r5 = new com.android.messaging.mmslib.a.e     // Catch:{ NullPointerException -> 0x01bb }
            java.lang.String r7 = "insert-address-token"
            byte[] r7 = r7.getBytes()     // Catch:{ NullPointerException -> 0x01bb }
            r8 = 106(0x6a, float:1.49E-43)
            r5.<init>((int) r8, (byte[]) r7)     // Catch:{ NullPointerException -> 0x01bb }
        L_0x01af:
            r7 = 137(0x89, float:1.92E-43)
            r2.mo6718b((com.android.messaging.mmslib.p039a.C0975e) r5, (int) r7)     // Catch:{ NullPointerException -> 0x028e, RuntimeException -> 0x01b6 }
            goto L_0x028e
        L_0x01b6:
            p026b.p027a.p030b.p031a.C0632a.m1024d(r6, r11)
            goto L_0x0106
        L_0x01bb:
            p026b.p027a.p030b.p031a.C0632a.m1024d(r6, r11)
            goto L_0x0106
        L_0x01c0:
            r4 = 1
            m2267f(r0)
            int r5 = r0.read()
            r5 = r5 & 255(0xff, float:3.57E-43)
            long r7 = m2265c(r0)     // Catch:{ RuntimeException -> 0x01e2 }
            if (r14 != r5) goto L_0x01d8
            long r9 = java.lang.System.currentTimeMillis()
            r13 = 1000(0x3e8, double:4.94E-321)
            long r9 = r9 / r13
            long r7 = r7 + r9
        L_0x01d8:
            r2.mo6715a((long) r7, (int) r6)     // Catch:{ RuntimeException -> 0x01dd }
            goto L_0x028e
        L_0x01dd:
            p026b.p027a.p030b.p031a.C0632a.m1024d(r6, r12)
            goto L_0x0106
        L_0x01e2:
            p026b.p027a.p030b.p031a.C0632a.m1024d(r6, r12)
            goto L_0x0106
        L_0x01e7:
            r4 = 1
            int r8 = r0.read()
            r8 = r8 & 255(0xff, float:3.57E-43)
            r2.mo6720e(r8, r6)     // Catch:{ InvalidHeaderValueException -> 0x01f8, RuntimeException -> 0x01f3 }
            goto L_0x028e
        L_0x01f3:
            p026b.p027a.p030b.p031a.C0632a.m1024d(r6, r5)
            goto L_0x0106
        L_0x01f8:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            r0.append(r9)
            r0.append(r8)
            r0.append(r7)
            r0.append(r6)
            r0.toString()
            goto L_0x0106
        L_0x020e:
            r4 = 1
            long r7 = m2265c(r0)     // Catch:{ RuntimeException -> 0x0218 }
            r2.mo6715a((long) r7, (int) r6)     // Catch:{ RuntimeException -> 0x0218 }
            goto L_0x028e
        L_0x0218:
            p026b.p027a.p030b.p031a.C0632a.m1024d(r6, r12)
            goto L_0x0106
        L_0x021d:
            r4 = 1
            android.util.SparseArray r3 = new android.util.SparseArray
            r3.<init>()
            byte[] r5 = m2262a((java.io.ByteArrayInputStream) r0, (android.util.SparseArray) r3)
            if (r5 == 0) goto L_0x0234
            r7 = 132(0x84, float:1.85E-43)
            r2.mo6719b((byte[]) r5, (int) r7)     // Catch:{ NullPointerException -> 0x0234, RuntimeException -> 0x022f }
            goto L_0x0234
        L_0x022f:
            p026b.p027a.p030b.p031a.C0632a.m1024d(r6, r10)
            goto L_0x0106
        L_0x0234:
            r5 = 153(0x99, float:2.14E-43)
            java.lang.Object r5 = r3.get(r5)
            byte[] r5 = (byte[]) r5
            f1419lm = r5
            r5 = 131(0x83, float:1.84E-43)
            java.lang.Object r3 = r3.get(r5)
            byte[] r3 = (byte[]) r3
            f1418km = r3
            r5 = 0
            r3 = 0
            goto L_0x000f
        L_0x024c:
            r4 = 0
            r5 = 1
            byte[] r4 = m2261a((java.io.ByteArrayInputStream) r0, (int) r4)
            if (r4 == 0) goto L_0x025e
            r2.mo6719b((byte[]) r4, (int) r6)     // Catch:{ NullPointerException -> 0x025e, RuntimeException -> 0x0258 }
            goto L_0x025e
        L_0x0258:
            p026b.p027a.p030b.p031a.C0632a.m1024d(r6, r10)
            r4 = r5
            goto L_0x0106
        L_0x025e:
            r4 = r5
            goto L_0x028e
        L_0x0260:
            r4 = 1
            com.android.messaging.mmslib.a.e r5 = m2260a(r0)
            if (r5 == 0) goto L_0x028e
            byte[] r7 = r5.mo6666fc()
            if (r7 == 0) goto L_0x0284
            java.lang.String r8 = new java.lang.String
            r8.<init>(r7)
            int r7 = r8.indexOf(r13)
            if (r7 <= 0) goto L_0x027d
            r9 = 0
            java.lang.String r8 = r8.substring(r9, r7)
        L_0x027d:
            byte[] r7 = r8.getBytes()     // Catch:{ NullPointerException -> 0x0106 }
            r5.mo6664b(r7)     // Catch:{ NullPointerException -> 0x0106 }
        L_0x0284:
            r2.mo6716a((com.android.messaging.mmslib.p039a.C0975e) r5, (int) r6)     // Catch:{ NullPointerException -> 0x028e, RuntimeException -> 0x0288 }
            goto L_0x028e
        L_0x0288:
            p026b.p027a.p030b.p031a.C0632a.m1024d(r6, r11)
            goto L_0x0106
        L_0x028d:
            r4 = 1
        L_0x028e:
            r5 = 0
            goto L_0x000f
        L_0x0291:
            r15.f1421hm = r2
            com.android.messaging.mmslib.a.q r0 = r15.f1421hm
            if (r0 != 0) goto L_0x0298
            return r1
        L_0x0298:
            r2 = 140(0x8c, float:1.96E-43)
            int r0 = r0.mo6713F(r2)
            com.android.messaging.mmslib.a.q r3 = r15.f1421hm
            if (r3 != 0) goto L_0x02a4
            goto L_0x03b7
        L_0x02a4:
            int r2 = r3.mo6713F(r2)
            r5 = 141(0x8d, float:1.98E-43)
            int r5 = r3.mo6713F(r5)
            if (r5 != 0) goto L_0x02b2
            goto L_0x03b7
        L_0x02b2:
            r5 = 155(0x9b, float:2.17E-43)
            r6 = 149(0x95, float:2.09E-43)
            r7 = 151(0x97, float:2.12E-43)
            r8 = 139(0x8b, float:1.95E-43)
            r9 = 133(0x85, float:1.86E-43)
            r10 = -1
            r12 = 152(0x98, float:2.13E-43)
            switch(r2) {
                case 128: goto L_0x039f;
                case 129: goto L_0x038f;
                case 130: goto L_0x0360;
                case 131: goto L_0x0351;
                case 132: goto L_0x033d;
                case 133: goto L_0x0335;
                case 134: goto L_0x0313;
                case 135: goto L_0x02f1;
                case 136: goto L_0x02c5;
                default: goto L_0x02c3;
            }
        L_0x02c3:
            goto L_0x03b7
        L_0x02c5:
            long r12 = r3.mo6712E(r9)
            int r2 = (r10 > r12 ? 1 : (r10 == r12 ? 0 : -1))
            if (r2 != 0) goto L_0x02cf
            goto L_0x03b7
        L_0x02cf:
            r2 = 137(0x89, float:1.92E-43)
            com.android.messaging.mmslib.a.e r2 = r3.mo6710C(r2)
            if (r2 != 0) goto L_0x02d9
            goto L_0x03b7
        L_0x02d9:
            byte[] r2 = r3.mo6714G(r8)
            if (r2 != 0) goto L_0x02e1
            goto L_0x03b7
        L_0x02e1:
            int r2 = r3.mo6713F(r5)
            if (r2 != 0) goto L_0x02e9
            goto L_0x03b7
        L_0x02e9:
            com.android.messaging.mmslib.a.e[] r2 = r3.mo6711D(r7)
            if (r2 != 0) goto L_0x03b8
            goto L_0x03b7
        L_0x02f1:
            r2 = 137(0x89, float:1.92E-43)
            com.android.messaging.mmslib.a.e r2 = r3.mo6710C(r2)
            if (r2 != 0) goto L_0x02fb
            goto L_0x03b7
        L_0x02fb:
            byte[] r2 = r3.mo6714G(r8)
            if (r2 != 0) goto L_0x0303
            goto L_0x03b7
        L_0x0303:
            int r2 = r3.mo6713F(r5)
            if (r2 != 0) goto L_0x030b
            goto L_0x03b7
        L_0x030b:
            com.android.messaging.mmslib.a.e[] r2 = r3.mo6711D(r7)
            if (r2 != 0) goto L_0x03b8
            goto L_0x03b7
        L_0x0313:
            long r12 = r3.mo6712E(r9)
            int r2 = (r10 > r12 ? 1 : (r10 == r12 ? 0 : -1))
            if (r2 != 0) goto L_0x031d
            goto L_0x03b7
        L_0x031d:
            byte[] r2 = r3.mo6714G(r8)
            if (r2 != 0) goto L_0x0325
            goto L_0x03b7
        L_0x0325:
            int r2 = r3.mo6713F(r6)
            if (r2 != 0) goto L_0x032d
            goto L_0x03b7
        L_0x032d:
            com.android.messaging.mmslib.a.e[] r2 = r3.mo6711D(r7)
            if (r2 != 0) goto L_0x03b8
            goto L_0x03b7
        L_0x0335:
            byte[] r2 = r3.mo6714G(r12)
            if (r2 != 0) goto L_0x03b8
            goto L_0x03b7
        L_0x033d:
            r2 = 132(0x84, float:1.85E-43)
            byte[] r2 = r3.mo6714G(r2)
            if (r2 != 0) goto L_0x0347
            goto L_0x03b7
        L_0x0347:
            long r2 = r3.mo6712E(r9)
            int r2 = (r10 > r2 ? 1 : (r10 == r2 ? 0 : -1))
            if (r2 != 0) goto L_0x03b8
            goto L_0x03b7
        L_0x0351:
            int r2 = r3.mo6713F(r6)
            if (r2 != 0) goto L_0x0359
            goto L_0x03b7
        L_0x0359:
            byte[] r2 = r3.mo6714G(r12)
            if (r2 != 0) goto L_0x03b8
            goto L_0x03b7
        L_0x0360:
            r2 = 131(0x83, float:1.84E-43)
            byte[] r2 = r3.mo6714G(r2)
            if (r2 != 0) goto L_0x0369
            goto L_0x03b7
        L_0x0369:
            r2 = 136(0x88, float:1.9E-43)
            long r5 = r3.mo6712E(r2)
            int r2 = (r10 > r5 ? 1 : (r10 == r5 ? 0 : -1))
            if (r2 != 0) goto L_0x0374
            goto L_0x03b7
        L_0x0374:
            r2 = 138(0x8a, float:1.93E-43)
            byte[] r2 = r3.mo6714G(r2)
            if (r2 != 0) goto L_0x037d
            goto L_0x03b7
        L_0x037d:
            r2 = 142(0x8e, float:1.99E-43)
            long r5 = r3.mo6712E(r2)
            int r2 = (r10 > r5 ? 1 : (r10 == r5 ? 0 : -1))
            if (r2 != 0) goto L_0x0388
            goto L_0x03b7
        L_0x0388:
            byte[] r2 = r3.mo6714G(r12)
            if (r2 != 0) goto L_0x03b8
            goto L_0x03b7
        L_0x038f:
            r2 = 146(0x92, float:2.05E-43)
            int r2 = r3.mo6713F(r2)
            if (r2 != 0) goto L_0x0398
            goto L_0x03b7
        L_0x0398:
            byte[] r2 = r3.mo6714G(r12)
            if (r2 != 0) goto L_0x03b8
            goto L_0x03b7
        L_0x039f:
            r2 = 132(0x84, float:1.85E-43)
            byte[] r2 = r3.mo6714G(r2)
            if (r2 != 0) goto L_0x03a8
            goto L_0x03b7
        L_0x03a8:
            r2 = 137(0x89, float:1.92E-43)
            com.android.messaging.mmslib.a.e r2 = r3.mo6710C(r2)
            if (r2 != 0) goto L_0x03b1
            goto L_0x03b7
        L_0x03b1:
            byte[] r2 = r3.mo6714G(r12)
            if (r2 != 0) goto L_0x03b8
        L_0x03b7:
            r4 = 0
        L_0x03b8:
            if (r4 != 0) goto L_0x03bb
            return r1
        L_0x03bb:
            com.android.messaging.mmslib.a.q r2 = r15.f1421hm
            r3 = 153(0x99, float:2.14E-43)
            boolean r2 = r2.mo6709Ba(r3)
            if (r2 == 0) goto L_0x03ce
            com.android.messaging.mmslib.a.q r2 = r15.f1421hm
            int r2 = r2.mo6713F(r3)
            r3 = 128(0x80, float:1.794E-43)
            goto L_0x03d2
        L_0x03ce:
            r3 = 128(0x80, float:1.794E-43)
            r2 = 128(0x80, float:1.794E-43)
        L_0x03d2:
            if (r3 == r0) goto L_0x03da
            r4 = 132(0x84, float:1.85E-43)
            if (r4 != r0) goto L_0x03e7
            if (r2 != r3) goto L_0x03e7
        L_0x03da:
            java.io.ByteArrayInputStream r3 = r15.f1420gm
            com.android.messaging.mmslib.a.j r3 = r15.mo6721d(r3)
            r15.f1422im = r3
            com.android.messaging.mmslib.a.j r3 = r15.f1422im
            if (r3 != 0) goto L_0x03e7
            return r1
        L_0x03e7:
            switch(r0) {
                case 128: goto L_0x0475;
                case 129: goto L_0x046d;
                case 130: goto L_0x0465;
                case 131: goto L_0x045d;
                case 132: goto L_0x040c;
                case 133: goto L_0x0404;
                case 134: goto L_0x03fc;
                case 135: goto L_0x03f4;
                case 136: goto L_0x03ec;
                default: goto L_0x03ea;
            }
        L_0x03ea:
            goto L_0x047f
        L_0x03ec:
            com.android.messaging.mmslib.a.u r0 = new com.android.messaging.mmslib.a.u
            com.android.messaging.mmslib.a.q r15 = r15.f1421hm
            r0.<init>(r15)
            return r0
        L_0x03f4:
            com.android.messaging.mmslib.a.v r0 = new com.android.messaging.mmslib.a.v
            com.android.messaging.mmslib.a.q r15 = r15.f1421hm
            r0.<init>(r15)
            return r0
        L_0x03fc:
            com.android.messaging.mmslib.a.d r0 = new com.android.messaging.mmslib.a.d
            com.android.messaging.mmslib.a.q r15 = r15.f1421hm
            r0.<init>(r15)
            return r0
        L_0x0404:
            com.android.messaging.mmslib.a.a r0 = new com.android.messaging.mmslib.a.a
            com.android.messaging.mmslib.a.q r15 = r15.f1421hm
            r0.<init>(r15)
            return r0
        L_0x040c:
            com.android.messaging.mmslib.a.w r0 = new com.android.messaging.mmslib.a.w
            com.android.messaging.mmslib.a.q r3 = r15.f1421hm
            com.android.messaging.mmslib.a.j r4 = r15.f1422im
            r0.<init>(r3, r4)
            r3 = 128(0x80, float:1.794E-43)
            if (r2 == r3) goto L_0x041a
            return r0
        L_0x041a:
            com.android.messaging.mmslib.a.q r2 = r0.f1404Zl
            r3 = 132(0x84, float:1.85E-43)
            byte[] r2 = r2.mo6714G(r3)
            if (r2 != 0) goto L_0x0425
            return r1
        L_0x0425:
            java.lang.String r3 = new java.lang.String
            r3.<init>(r2)
            java.lang.String r2 = "application/vnd.wap.multipart.mixed"
            boolean r2 = r3.equals(r2)
            if (r2 != 0) goto L_0x045c
            java.lang.String r2 = "application/vnd.wap.multipart.related"
            boolean r2 = r3.equals(r2)
            if (r2 != 0) goto L_0x045c
            java.lang.String r2 = "application/vnd.wap.multipart.alternative"
            boolean r4 = r3.equals(r2)
            if (r4 == 0) goto L_0x0443
            goto L_0x045c
        L_0x0443:
            boolean r2 = r3.equals(r2)
            if (r2 == 0) goto L_0x045b
            com.android.messaging.mmslib.a.j r1 = r15.f1422im
            r2 = 0
            com.android.messaging.mmslib.a.s r1 = r1.getPart(r2)
            com.android.messaging.mmslib.a.j r3 = r15.f1422im
            r3.removeAll()
            com.android.messaging.mmslib.a.j r15 = r15.f1422im
            r15.mo6688a(r2, r1)
            return r0
        L_0x045b:
            return r1
        L_0x045c:
            return r0
        L_0x045d:
            com.android.messaging.mmslib.a.i r0 = new com.android.messaging.mmslib.a.i
            com.android.messaging.mmslib.a.q r15 = r15.f1421hm
            r0.<init>(r15)
            return r0
        L_0x0465:
            com.android.messaging.mmslib.a.h r0 = new com.android.messaging.mmslib.a.h
            com.android.messaging.mmslib.a.q r15 = r15.f1421hm
            r0.<init>(r15)
            return r0
        L_0x046d:
            com.android.messaging.mmslib.a.x r0 = new com.android.messaging.mmslib.a.x
            com.android.messaging.mmslib.a.q r15 = r15.f1421hm
            r0.<init>(r15)
            return r0
        L_0x0475:
            com.android.messaging.mmslib.a.y r0 = new com.android.messaging.mmslib.a.y
            com.android.messaging.mmslib.a.q r1 = r15.f1421hm
            com.android.messaging.mmslib.a.j r15 = r15.f1422im
            r0.<init>(r1, r15)
            return r0
        L_0x047f:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.messaging.mmslib.p039a.C0988r.parse():com.android.messaging.mmslib.a.f");
    }

    /* renamed from: b */
    protected static int m2263b(ByteArrayInputStream byteArrayInputStream, int i) {
        int read = byteArrayInputStream.read(new byte[i], 0, i);
        if (read < i) {
            return -1;
        }
        return read;
    }

    /* renamed from: a */
    protected static byte[] m2261a(ByteArrayInputStream byteArrayInputStream, int i) {
        byteArrayInputStream.mark(1);
        int read = byteArrayInputStream.read();
        if (1 == i && 34 == read) {
            byteArrayInputStream.mark(1);
        } else if (i == 0 && 127 == read) {
            byteArrayInputStream.mark(1);
        } else {
            byteArrayInputStream.reset();
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int read2 = byteArrayInputStream.read();
        while (-1 != read2 && read2 != 0) {
            boolean z = false;
            if (i == 2) {
                if (!(read2 < 33 || read2 > 126 || read2 == 34 || read2 == 44 || read2 == 47 || read2 == 123 || read2 == 125 || read2 == 40 || read2 == 41)) {
                    switch (read2) {
                        case 58:
                        case 59:
                        case 60:
                        case 61:
                        case 62:
                        case 63:
                        case 64:
                            break;
                        default:
                            switch (read2) {
                                case 91:
                                case 92:
                                case 93:
                                    break;
                                default:
                                    z = true;
                                    break;
                            }
                    }
                }
                if (z) {
                    byteArrayOutputStream.write(read2);
                }
            } else {
                if ((read2 >= 32 && read2 <= 126) || ((read2 >= 128 && read2 <= 255) || read2 == 9 || read2 == 10 || read2 == 13)) {
                    z = true;
                }
                if (z) {
                    byteArrayOutputStream.write(read2);
                }
            }
            read2 = byteArrayInputStream.read();
        }
        if (byteArrayOutputStream.size() > 0) {
            return byteArrayOutputStream.toByteArray();
        }
        return null;
    }

    /* renamed from: a */
    protected static byte[] m2262a(ByteArrayInputStream byteArrayInputStream, SparseArray sparseArray) {
        byte[] bArr;
        int i;
        int i2;
        int i3;
        ByteArrayInputStream byteArrayInputStream2 = byteArrayInputStream;
        SparseArray sparseArray2 = sparseArray;
        byteArrayInputStream2.mark(1);
        int read = byteArrayInputStream.read();
        byteArrayInputStream.reset();
        int i4 = read & 255;
        if (i4 < 32) {
            int f = m2267f(byteArrayInputStream);
            int available = byteArrayInputStream.available();
            byteArrayInputStream2.mark(1);
            int read2 = byteArrayInputStream.read();
            byteArrayInputStream.reset();
            int i5 = read2 & 255;
            if (i5 >= 32 && i5 <= 127) {
                bArr = m2261a(byteArrayInputStream2, 0);
            } else if (i5 > 127) {
                int read3 = byteArrayInputStream.read() & 127;
                String[] strArr = C0986p.f1416em;
                if (read3 < strArr.length) {
                    bArr = strArr[read3].getBytes();
                } else {
                    byteArrayInputStream.reset();
                    bArr = m2261a(byteArrayInputStream2, 0);
                }
            } else {
                Log.e("PduParser", "Corrupt content-type");
                return C0986p.f1416em[0].getBytes();
            }
            int available2 = f - (available - byteArrayInputStream.available());
            if (available2 > 0) {
                Integer valueOf = Integer.valueOf(available2);
                int available3 = byteArrayInputStream.available();
                int intValue = valueOf.intValue();
                while (intValue > 0) {
                    int read4 = byteArrayInputStream.read();
                    intValue--;
                    if (read4 != 129) {
                        if (read4 != 131) {
                            if (read4 == 133 || read4 == 151) {
                                byte[] a = m2261a(byteArrayInputStream2, 0);
                                if (!(a == null || sparseArray2 == null)) {
                                    sparseArray2.put(151, a);
                                }
                                i2 = byteArrayInputStream.available();
                                i3 = valueOf.intValue();
                                i = i3;
                            } else {
                                if (read4 != 153) {
                                    if (read4 != 137) {
                                        if (read4 != 138) {
                                            if (-1 == m2263b(byteArrayInputStream2, intValue)) {
                                                Log.e("PduParser", "Corrupt Content-Type");
                                            } else {
                                                intValue = 0;
                                            }
                                        }
                                    }
                                }
                                byte[] a2 = m2261a(byteArrayInputStream2, 0);
                                if (!(a2 == null || sparseArray2 == null)) {
                                    sparseArray2.put(153, a2);
                                }
                                i2 = byteArrayInputStream.available();
                                i3 = valueOf.intValue();
                                i = i3;
                            }
                        }
                        byteArrayInputStream2.mark(1);
                        int read5 = byteArrayInputStream.read() & 255;
                        byteArrayInputStream.reset();
                        if (read5 > 127) {
                            int read6 = byteArrayInputStream.read() & 127;
                            String[] strArr2 = C0986p.f1416em;
                            if (read6 < strArr2.length) {
                                sparseArray2.put(131, strArr2[read6].getBytes());
                            }
                        } else {
                            byte[] a3 = m2261a(byteArrayInputStream2, 0);
                            if (!(a3 == null || sparseArray2 == null)) {
                                sparseArray2.put(131, a3);
                            }
                        }
                        i2 = byteArrayInputStream.available();
                        i3 = valueOf.intValue();
                        i = i3;
                    } else {
                        byteArrayInputStream2.mark(1);
                        int read7 = byteArrayInputStream.read() & 255;
                        byteArrayInputStream.reset();
                        if ((read7 <= 32 || read7 >= 127) && read7 != 0) {
                            int b = (int) m2264b(byteArrayInputStream);
                            if (sparseArray2 != null) {
                                sparseArray2.put(129, Integer.valueOf(b));
                            }
                        } else {
                            byte[] a4 = m2261a(byteArrayInputStream2, 0);
                            try {
                                sparseArray2.put(129, Integer.valueOf(C0973c.m2209x(new String(a4))));
                            } catch (UnsupportedEncodingException e) {
                                Log.e("PduParser", Arrays.toString(a4), e);
                                sparseArray2.put(129, 0);
                            }
                        }
                        i2 = byteArrayInputStream.available();
                        i = valueOf.intValue();
                    }
                    intValue = i - (available3 - i2);
                }
                if (intValue != 0) {
                    Log.e("PduParser", "Corrupt Content-Type");
                }
            }
            if (available2 >= 0) {
                return bArr;
            }
            Log.e("PduParser", "Corrupt MMS message");
            return C0986p.f1416em[0].getBytes();
        } else if (i4 <= 127) {
            return m2261a(byteArrayInputStream2, 0);
        } else {
            return C0986p.f1416em[byteArrayInputStream.read() & 127].getBytes();
        }
    }
}
