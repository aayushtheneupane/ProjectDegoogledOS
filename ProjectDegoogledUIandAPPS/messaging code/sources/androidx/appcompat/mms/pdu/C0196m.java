package androidx.appcompat.mms.pdu;

import android.util.Log;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.HashMap;

/* renamed from: androidx.appcompat.mms.pdu.m */
public class C0196m {

    /* renamed from: km */
    private static byte[] f183km;

    /* renamed from: lm */
    private static byte[] f184lm;

    /* renamed from: gm */
    private ByteArrayInputStream f185gm = null;

    /* renamed from: hm */
    private C0195l f186hm = null;

    /* renamed from: im */
    private C0193j f187im = null;

    /* renamed from: jm */
    private final boolean f188jm;

    public C0196m(byte[] bArr, boolean z) {
        this.f185gm = new ByteArrayInputStream(bArr);
        this.f188jm = z;
    }

    /* renamed from: a */
    protected static C0188e m168a(ByteArrayInputStream byteArrayInputStream) {
        int i;
        byteArrayInputStream.mark(1);
        int read = byteArrayInputStream.read() & 255;
        if (read == 0) {
            return new C0188e("");
        }
        byteArrayInputStream.reset();
        if (read < 32) {
            m175f(byteArrayInputStream);
            i = byteArrayInputStream.read() & 127;
        } else {
            i = 0;
        }
        byte[] a = m169a(byteArrayInputStream, 0);
        if (i == 0) {
            return new C0188e(106, a);
        }
        try {
            return new C0188e(i, a);
        } catch (Exception unused) {
            return null;
        }
    }

    /* renamed from: b */
    protected static long m172b(ByteArrayInputStream byteArrayInputStream) {
        byteArrayInputStream.mark(1);
        int read = byteArrayInputStream.read();
        byteArrayInputStream.reset();
        if (read > 127) {
            return (long) (byteArrayInputStream.read() & 127);
        }
        return m173c(byteArrayInputStream);
    }

    /* renamed from: c */
    protected static long m173c(ByteArrayInputStream byteArrayInputStream) {
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
    protected static int m174e(ByteArrayInputStream byteArrayInputStream) {
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
    protected static int m175f(ByteArrayInputStream byteArrayInputStream) {
        int read = byteArrayInputStream.read() & 255;
        if (read <= 30) {
            return read;
        }
        if (read == 31) {
            return m174e(byteArrayInputStream);
        }
        throw new RuntimeException("Value length > LENGTH_QUOTE!");
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:146:0x0270, code lost:
        if (true == java.util.Arrays.equals(f184lm, r2)) goto L_0x0272;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:154:0x0287, code lost:
        if (true == java.util.Arrays.equals(f183km, r2)) goto L_0x0272;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:83:0x0181, code lost:
        r2 = false;
     */
    /* JADX WARNING: Removed duplicated region for block: B:143:0x0263  */
    /* JADX WARNING: Removed duplicated region for block: B:149:0x0276  */
    /* JADX WARNING: Removed duplicated region for block: B:156:0x028c  */
    /* JADX WARNING: Removed duplicated region for block: B:157:0x0291  */
    /* renamed from: d */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public androidx.appcompat.mms.pdu.C0193j mo1253d(java.io.ByteArrayInputStream r19) {
        /*
            r18 = this;
            r0 = r18
            r1 = r19
            r2 = 0
            if (r1 != 0) goto L_0x0008
            return r2
        L_0x0008:
            int r3 = m174e(r19)
            androidx.appcompat.mms.pdu.j r4 = new androidx.appcompat.mms.pdu.j
            r4.<init>()
            r5 = 0
            r6 = r5
        L_0x0013:
            if (r6 >= r3) goto L_0x029e
            int r7 = m174e(r19)
            int r8 = m174e(r19)
            androidx.appcompat.mms.pdu.n r9 = new androidx.appcompat.mms.pdu.n
            r9.<init>()
            int r10 = r19.available()
            if (r10 > 0) goto L_0x0029
            return r2
        L_0x0029:
            java.util.HashMap r11 = new java.util.HashMap
            r11.<init>()
            byte[] r12 = m170a((java.io.ByteArrayInputStream) r1, (java.util.HashMap) r11)
            if (r12 == 0) goto L_0x0038
            r9.mo1260g(r12)
            goto L_0x0043
        L_0x0038:
            java.lang.String[] r12 = androidx.appcompat.mms.pdu.C0194k.f181em
            r12 = r12[r5]
            byte[] r12 = r12.getBytes()
            r9.mo1260g(r12)
        L_0x0043:
            r12 = 151(0x97, float:2.12E-43)
            java.lang.Integer r12 = java.lang.Integer.valueOf(r12)
            java.lang.Object r12 = r11.get(r12)
            byte[] r12 = (byte[]) r12
            if (r12 == 0) goto L_0x0054
            r9.mo1264i(r12)
        L_0x0054:
            r12 = 129(0x81, float:1.81E-43)
            java.lang.Integer r13 = java.lang.Integer.valueOf(r12)
            java.lang.Object r11 = r11.get(r13)
            java.lang.Integer r11 = (java.lang.Integer) r11
            if (r11 == 0) goto L_0x0069
            int r11 = r11.intValue()
            r9.mo1255H(r11)
        L_0x0069:
            int r11 = r19.available()
            int r10 = r10 - r11
            int r7 = r7 - r10
            r10 = -1
            r11 = 1
            if (r7 <= 0) goto L_0x0188
            int r13 = r19.available()
            r14 = r7
        L_0x0078:
            java.lang.String r15 = "Corrupt Part headers"
            java.lang.String r2 = "PduParser"
            if (r14 <= 0) goto L_0x017c
            int r5 = r19.read()
            int r14 = r14 + -1
            r12 = 127(0x7f, float:1.78E-43)
            if (r5 <= r12) goto L_0x0136
            r12 = 142(0x8e, float:1.99E-43)
            if (r5 == r12) goto L_0x0125
            r12 = 174(0xae, float:2.44E-43)
            if (r5 == r12) goto L_0x00b6
            r12 = 192(0xc0, float:2.69E-43)
            if (r5 == r12) goto L_0x00a7
            r12 = 197(0xc5, float:2.76E-43)
            if (r5 == r12) goto L_0x00b6
            int r5 = m171b(r1, r14)
            if (r10 != r5) goto L_0x00a3
            android.util.Log.e(r2, r15)
            goto L_0x0181
        L_0x00a3:
            r16 = 129(0x81, float:1.81E-43)
            goto L_0x0173
        L_0x00a7:
            byte[] r2 = m169a((java.io.ByteArrayInputStream) r1, (int) r11)
            if (r2 == 0) goto L_0x00b0
            r9.mo1257d(r2)
        L_0x00b0:
            int r2 = r19.available()
            goto L_0x0133
        L_0x00b6:
            boolean r2 = r0.f188jm
            if (r2 == 0) goto L_0x011f
            int r2 = m175f(r19)
            r1.mark(r11)
            int r5 = r19.available()
            int r12 = r19.read()
            r14 = 128(0x80, float:1.794E-43)
            if (r12 != r14) goto L_0x00d6
            byte[] r12 = androidx.appcompat.mms.pdu.C0197n.f189om
            r9.mo1256c(r12)
            r12 = 0
            r15 = 129(0x81, float:1.81E-43)
            goto L_0x00f6
        L_0x00d6:
            r15 = 129(0x81, float:1.81E-43)
            if (r12 != r15) goto L_0x00e1
            byte[] r12 = androidx.appcompat.mms.pdu.C0197n.f190qm
            r9.mo1256c(r12)
        L_0x00df:
            r12 = 0
            goto L_0x00f6
        L_0x00e1:
            r14 = 130(0x82, float:1.82E-43)
            if (r12 != r14) goto L_0x00eb
            byte[] r12 = androidx.appcompat.mms.pdu.C0197n.f191rm
            r9.mo1256c(r12)
            goto L_0x00df
        L_0x00eb:
            r19.reset()
            r12 = 0
            byte[] r14 = m169a((java.io.ByteArrayInputStream) r1, (int) r12)
            r9.mo1256c(r14)
        L_0x00f6:
            int r14 = r19.available()
            int r14 = r5 - r14
            if (r14 >= r2) goto L_0x011a
            int r14 = r19.read()
            r15 = 152(0x98, float:2.13E-43)
            if (r14 != r15) goto L_0x010d
            byte[] r14 = m169a((java.io.ByteArrayInputStream) r1, (int) r12)
            r9.mo1263h(r14)
        L_0x010d:
            int r14 = r19.available()
            int r5 = r5 - r14
            if (r5 >= r2) goto L_0x011a
            int r2 = r2 - r5
            byte[] r5 = new byte[r2]
            r1.read(r5, r12, r2)
        L_0x011a:
            int r2 = r19.available()
            goto L_0x0133
        L_0x011f:
            r2 = 0
            r5 = 0
            r12 = 129(0x81, float:1.81E-43)
            goto L_0x0078
        L_0x0125:
            r12 = 0
            byte[] r2 = m169a((java.io.ByteArrayInputStream) r1, (int) r12)
            if (r2 == 0) goto L_0x012f
            r9.mo1258e(r2)
        L_0x012f:
            int r2 = r19.available()
        L_0x0133:
            r16 = 129(0x81, float:1.81E-43)
            goto L_0x015c
        L_0x0136:
            r10 = 0
            r16 = 129(0x81, float:1.81E-43)
            r11 = 32
            if (r5 < r11) goto L_0x0168
            if (r5 > r12) goto L_0x0168
            byte[] r2 = m169a((java.io.ByteArrayInputStream) r1, (int) r10)
            byte[] r5 = m169a((java.io.ByteArrayInputStream) r1, (int) r10)
            java.lang.String r10 = new java.lang.String
            r10.<init>(r2)
            java.lang.String r2 = "Content-Transfer-Encoding"
            boolean r2 = r2.equalsIgnoreCase(r10)
            r10 = 1
            if (r10 != r2) goto L_0x0158
            r9.mo1259f(r5)
        L_0x0158:
            int r2 = r19.available()
        L_0x015c:
            int r2 = r13 - r2
            int r14 = r7 - r2
            r12 = r16
            r2 = 0
            r5 = 0
            r10 = -1
            r11 = 1
            goto L_0x0078
        L_0x0168:
            int r5 = m171b(r1, r14)
            r10 = -1
            if (r10 != r5) goto L_0x0173
            android.util.Log.e(r2, r15)
            goto L_0x0181
        L_0x0173:
            r12 = r16
            r2 = 0
            r5 = 0
            r10 = -1
            r11 = 1
            r14 = 0
            goto L_0x0078
        L_0x017c:
            if (r14 == 0) goto L_0x0183
            android.util.Log.e(r2, r15)
        L_0x0181:
            r2 = 0
            goto L_0x0184
        L_0x0183:
            r2 = 1
        L_0x0184:
            if (r2 != 0) goto L_0x018b
            r2 = 0
            return r2
        L_0x0188:
            if (r7 >= 0) goto L_0x018b
            return r2
        L_0x018b:
            byte[] r2 = r9.mo1266jc()
            if (r2 != 0) goto L_0x01b2
            byte[] r2 = r9.getName()
            if (r2 != 0) goto L_0x01b2
            byte[] r2 = r9.mo1268lc()
            if (r2 != 0) goto L_0x01b2
            byte[] r2 = r9.mo1265ic()
            if (r2 != 0) goto L_0x01b2
            long r10 = java.lang.System.currentTimeMillis()
            java.lang.String r2 = java.lang.Long.toOctalString(r10)
            byte[] r2 = r2.getBytes()
            r9.mo1258e(r2)
        L_0x01b2:
            if (r8 <= 0) goto L_0x0255
            byte[] r2 = new byte[r8]
            java.lang.String r5 = new java.lang.String
            byte[] r7 = r9.getContentType()
            r5.<init>(r7)
            r7 = 0
            r1.read(r2, r7, r8)
            java.lang.String r8 = "application/vnd.wap.multipart.alternative"
            boolean r5 = r5.equalsIgnoreCase(r8)
            if (r5 == 0) goto L_0x01da
            java.io.ByteArrayInputStream r5 = new java.io.ByteArrayInputStream
            r5.<init>(r2)
            androidx.appcompat.mms.pdu.j r2 = r0.mo1253d(r5)
            androidx.appcompat.mms.pdu.n r9 = r2.getPart(r7)
            goto L_0x0255
        L_0x01da:
            byte[] r5 = r9.mo1267kc()
            if (r5 == 0) goto L_0x024c
            java.lang.String r7 = new java.lang.String
            r7.<init>(r5)
            java.lang.String r5 = "base64"
            boolean r5 = r7.equalsIgnoreCase(r5)
            if (r5 == 0) goto L_0x01f2
            byte[] r2 = androidx.appcompat.mms.pdu.C0185b.m151a(r2)
            goto L_0x024c
        L_0x01f2:
            java.lang.String r5 = "quoted-printable"
            boolean r5 = r7.equalsIgnoreCase(r5)
            if (r5 == 0) goto L_0x024c
            java.io.ByteArrayOutputStream r5 = new java.io.ByteArrayOutputStream
            r5.<init>()
            r7 = 0
        L_0x0200:
            int r8 = r2.length
            if (r7 >= r8) goto L_0x0248
            byte r8 = r2[r7]
            r10 = 61
            if (r8 != r10) goto L_0x0241
            r8 = 13
            int r10 = r7 + 1
            byte r11 = r2[r10]     // Catch:{ ArrayIndexOutOfBoundsException -> 0x023f }
            char r11 = (char) r11     // Catch:{ ArrayIndexOutOfBoundsException -> 0x023f }
            if (r8 != r11) goto L_0x021e
            r8 = 10
            int r7 = r7 + 2
            byte r11 = r2[r7]     // Catch:{ ArrayIndexOutOfBoundsException -> 0x023f }
            char r11 = (char) r11     // Catch:{ ArrayIndexOutOfBoundsException -> 0x023f }
            if (r8 != r11) goto L_0x021e
            r8 = 1
            r11 = -1
            goto L_0x0246
        L_0x021e:
            byte r7 = r2[r10]     // Catch:{ ArrayIndexOutOfBoundsException -> 0x023f }
            char r7 = (char) r7     // Catch:{ ArrayIndexOutOfBoundsException -> 0x023f }
            r8 = 16
            int r7 = java.lang.Character.digit(r7, r8)     // Catch:{ ArrayIndexOutOfBoundsException -> 0x023f }
            int r10 = r10 + 1
            byte r11 = r2[r10]     // Catch:{ ArrayIndexOutOfBoundsException -> 0x023f }
            char r11 = (char) r11     // Catch:{ ArrayIndexOutOfBoundsException -> 0x023f }
            int r8 = java.lang.Character.digit(r11, r8)     // Catch:{ ArrayIndexOutOfBoundsException -> 0x023f }
            r11 = -1
            if (r7 == r11) goto L_0x023f
            if (r8 != r11) goto L_0x0236
            goto L_0x023f
        L_0x0236:
            int r7 = r7 << 4
            int r7 = r7 + r8
            char r7 = (char) r7     // Catch:{ ArrayIndexOutOfBoundsException -> 0x023f }
            r5.write(r7)     // Catch:{ ArrayIndexOutOfBoundsException -> 0x023f }
            r7 = r10
            goto L_0x0245
        L_0x023f:
            r2 = 0
            goto L_0x024c
        L_0x0241:
            r11 = -1
            r5.write(r8)
        L_0x0245:
            r8 = 1
        L_0x0246:
            int r7 = r7 + r8
            goto L_0x0200
        L_0x0248:
            byte[] r2 = r5.toByteArray()
        L_0x024c:
            if (r2 != 0) goto L_0x0250
            r5 = 0
            return r5
        L_0x0250:
            r5 = 0
            r9.setData(r2)
            goto L_0x0256
        L_0x0255:
            r5 = 0
        L_0x0256:
            byte[] r2 = f183km
            if (r2 != 0) goto L_0x025f
            byte[] r2 = f184lm
            if (r2 != 0) goto L_0x025f
            goto L_0x0274
        L_0x025f:
            byte[] r2 = f184lm
            if (r2 == 0) goto L_0x0276
            byte[] r2 = r9.mo1265ic()
            if (r2 == 0) goto L_0x0274
            byte[] r7 = f184lm
            boolean r2 = java.util.Arrays.equals(r7, r2)
            r10 = 1
            if (r10 != r2) goto L_0x028a
        L_0x0272:
            r10 = 0
            goto L_0x028a
        L_0x0274:
            r10 = 1
            goto L_0x028a
        L_0x0276:
            r10 = 1
            byte[] r2 = f183km
            if (r2 == 0) goto L_0x028a
            byte[] r2 = r9.getContentType()
            if (r2 == 0) goto L_0x028a
            byte[] r7 = f183km
            boolean r2 = java.util.Arrays.equals(r7, r2)
            if (r10 != r2) goto L_0x028a
            goto L_0x0272
        L_0x028a:
            if (r10 != 0) goto L_0x0291
            r2 = 0
            r4.mo1239a(r2, r9)
            goto L_0x0295
        L_0x0291:
            r2 = 0
            r4.mo1240a(r9)
        L_0x0295:
            int r6 = r6 + 1
            r17 = r5
            r5 = r2
            r2 = r17
            goto L_0x0013
        L_0x029e:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.mms.pdu.C0196m.mo1253d(java.io.ByteArrayInputStream):androidx.appcompat.mms.pdu.j");
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:111:0x01be, code lost:
        p026b.p027a.p030b.p031a.C0632a.m1024d(r8, "is not Encoded-String-Value header field!");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:169:0x0294, code lost:
        p026b.p027a.p030b.p031a.C0632a.m1024d(r8, "is not Encoded-String-Value header field!");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:171:0x0298, code lost:
        r5 = 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:194:0x02f7, code lost:
        if (r6.mo1244D(151) == null) goto L_0x03c1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:202:0x0319, code lost:
        if (r6.mo1244D(151) == null) goto L_0x03c1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:210:0x033b, code lost:
        if (r6.mo1244D(151) == null) goto L_0x03c1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:212:0x0343, code lost:
        if (r6.mo1247G(152) == null) goto L_0x03c1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:216:0x0357, code lost:
        if (-1 == r6.mo1245E(133)) goto L_0x03c1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:220:0x0367, code lost:
        if (r6.mo1247G(152) == null) goto L_0x03c1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:230:0x0396, code lost:
        if (r6.mo1247G(152) == null) goto L_0x03c1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:234:0x03a6, code lost:
        if (r6.mo1247G(152) == null) goto L_0x03c1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:240:0x03bf, code lost:
        if (r6.mo1247G(152) == null) goto L_0x03c1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x00ab, code lost:
        p026b.p027a.p030b.p031a.C0632a.m1024d(r8, "is not Encoded-String-Value header field!");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:93:0x0175, code lost:
        p026b.p027a.p030b.p031a.C0632a.m1024d(r8, "is not Text-String header field!");
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:172:? A[ExcHandler: NullPointerException (unused java.lang.NullPointerException), SYNTHETIC, Splitter:B:42:0x00a6] */
    /* JADX WARNING: Removed duplicated region for block: B:174:? A[ExcHandler: NullPointerException (unused java.lang.NullPointerException), PHI: r5 
      PHI: (r5v17 int) = (r5v19 int), (r5v19 int), (r5v29 int), (r5v29 int), (r5v30 int), (r5v30 int), (r5v30 int) binds: [B:167:0x0290, B:168:?, B:109:0x01b9, B:110:?, B:91:0x0170, B:92:?, B:75:0x0121] A[DONT_GENERATE, DONT_INLINE], SYNTHETIC, Splitter:B:75:0x0121] */
    /* JADX WARNING: Removed duplicated region for block: B:178:0x02a3 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:179:0x02a4  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public androidx.appcompat.mms.pdu.C0189f parse() {
        /*
            r17 = this;
            r0 = r17
            java.io.ByteArrayInputStream r1 = r0.f185gm
            r2 = 0
            if (r1 != 0) goto L_0x0008
            return r2
        L_0x0008:
            r3 = 141(0x8d, float:1.98E-43)
            r4 = 1
            r5 = 0
            if (r1 != 0) goto L_0x0012
            r6 = r2
            r1 = r4
            goto L_0x029d
        L_0x0012:
            androidx.appcompat.mms.pdu.l r6 = new androidx.appcompat.mms.pdu.l
            r6.<init>()
            r7 = r5
            r5 = r4
        L_0x0019:
            if (r4 == 0) goto L_0x029c
            int r8 = r1.available()
            if (r8 <= 0) goto L_0x029c
            r1.mark(r5)
            int r8 = r1.read()
            r8 = r8 & 255(0xff, float:3.57E-43)
            r9 = 32
            r10 = 127(0x7f, float:1.78E-43)
            if (r8 < r9) goto L_0x003a
            if (r8 > r10) goto L_0x003a
            r1.reset()
            m169a((java.io.ByteArrayInputStream) r1, (int) r7)
            goto L_0x0299
        L_0x003a:
            java.lang.String r5 = " is not Integer-Value"
            java.lang.String r7 = "is not Octet header field!"
            java.lang.String r9 = " into the header filed: "
            java.lang.String r11 = "Set invalid Octet value: "
            java.lang.String r12 = "is not Text-String header field!"
            java.lang.String r13 = "is not Encoded-String-Value header field!"
            java.lang.String r14 = "is not Long-Integer header field!"
            java.lang.String r15 = "/"
            switch(r8) {
                case 129: goto L_0x026c;
                case 130: goto L_0x026c;
                case 131: goto L_0x0258;
                case 132: goto L_0x0222;
                case 133: goto L_0x0212;
                case 134: goto L_0x01ed;
                case 135: goto L_0x01c6;
                case 136: goto L_0x01c6;
                case 137: goto L_0x017a;
                case 138: goto L_0x010f;
                case 139: goto L_0x0258;
                case 140: goto L_0x00e5;
                case 141: goto L_0x00c2;
                case 142: goto L_0x0212;
                case 143: goto L_0x01ed;
                case 144: goto L_0x01ed;
                case 145: goto L_0x01ed;
                case 146: goto L_0x01ed;
                case 147: goto L_0x00b3;
                case 148: goto L_0x01ed;
                case 149: goto L_0x01ed;
                case 150: goto L_0x00b3;
                case 151: goto L_0x026c;
                case 152: goto L_0x0258;
                case 153: goto L_0x01ed;
                case 154: goto L_0x00b3;
                case 155: goto L_0x01ed;
                case 156: goto L_0x01ed;
                case 157: goto L_0x01c6;
                case 158: goto L_0x0258;
                case 159: goto L_0x0212;
                case 160: goto L_0x0098;
                case 161: goto L_0x007d;
                case 162: goto L_0x01ed;
                case 163: goto L_0x01ed;
                case 164: goto L_0x0072;
                case 165: goto L_0x01ed;
                case 166: goto L_0x00b3;
                case 167: goto L_0x01ed;
                case 168: goto L_0x004d;
                case 169: goto L_0x01ed;
                case 170: goto L_0x0062;
                case 171: goto L_0x01ed;
                case 172: goto L_0x0062;
                case 173: goto L_0x0054;
                case 174: goto L_0x004d;
                case 175: goto L_0x0054;
                case 176: goto L_0x004d;
                case 177: goto L_0x01ed;
                case 178: goto L_0x004f;
                case 179: goto L_0x0054;
                case 180: goto L_0x01ed;
                case 181: goto L_0x00b3;
                case 182: goto L_0x00b3;
                case 183: goto L_0x0258;
                case 184: goto L_0x0258;
                case 185: goto L_0x0258;
                case 186: goto L_0x01ed;
                case 187: goto L_0x01ed;
                case 188: goto L_0x01ed;
                case 189: goto L_0x0258;
                case 190: goto L_0x0258;
                case 191: goto L_0x01ed;
                default: goto L_0x004d;
            }
        L_0x004d:
            goto L_0x0298
        L_0x004f:
            m170a((java.io.ByteArrayInputStream) r1, (java.util.HashMap) r2)
            goto L_0x0298
        L_0x0054:
            long r9 = m172b(r1)     // Catch:{ RuntimeException -> 0x005d }
            r6.mo1248a((long) r9, (int) r8)     // Catch:{ RuntimeException -> 0x005d }
            goto L_0x0298
        L_0x005d:
            p026b.p027a.p030b.p031a.C0632a.m1024d(r8, r14)
            goto L_0x010b
        L_0x0062:
            m175f(r1)
            r1.read()
            m172b(r1)     // Catch:{ RuntimeException -> 0x006d }
            goto L_0x0298
        L_0x006d:
            p026b.p027a.p030b.p031a.C0632a.m1024d(r8, r5)
            goto L_0x010b
        L_0x0072:
            m175f(r1)
            r1.read()
            m168a(r1)
            goto L_0x0298
        L_0x007d:
            m175f(r1)
            m172b(r1)     // Catch:{ RuntimeException -> 0x0093 }
            long r9 = m173c(r1)     // Catch:{ RuntimeException -> 0x008e }
            r5 = 161(0xa1, float:2.26E-43)
            r6.mo1248a((long) r9, (int) r5)     // Catch:{ RuntimeException -> 0x008e }
            goto L_0x0298
        L_0x008e:
            p026b.p027a.p030b.p031a.C0632a.m1024d(r8, r14)
            goto L_0x010b
        L_0x0093:
            p026b.p027a.p030b.p031a.C0632a.m1024d(r8, r5)
            goto L_0x010b
        L_0x0098:
            m175f(r1)
            m172b(r1)     // Catch:{ RuntimeException -> 0x00af }
            androidx.appcompat.mms.pdu.e r5 = m168a(r1)
            if (r5 == 0) goto L_0x0298
            r7 = 160(0xa0, float:2.24E-43)
            r6.mo1250b((androidx.appcompat.mms.pdu.C0188e) r5, (int) r7)     // Catch:{ NullPointerException -> 0x0298, RuntimeException -> 0x00ab }
            goto L_0x0298
        L_0x00ab:
            p026b.p027a.p030b.p031a.C0632a.m1024d(r8, r13)
            goto L_0x010b
        L_0x00af:
            p026b.p027a.p030b.p031a.C0632a.m1024d(r8, r5)
            goto L_0x010b
        L_0x00b3:
            androidx.appcompat.mms.pdu.e r5 = m168a(r1)
            if (r5 == 0) goto L_0x0298
            r6.mo1250b((androidx.appcompat.mms.pdu.C0188e) r5, (int) r8)     // Catch:{ NullPointerException -> 0x0298, RuntimeException -> 0x00be }
            goto L_0x0298
        L_0x00be:
            p026b.p027a.p030b.p031a.C0632a.m1024d(r8, r13)
            goto L_0x010b
        L_0x00c2:
            int r5 = r1.read()
            r5 = r5 & r10
            r6.mo1252e(r5, r3)     // Catch:{ InvalidHeaderValueException -> 0x00d0, RuntimeException -> 0x00cc }
            goto L_0x0298
        L_0x00cc:
            p026b.p027a.p030b.p031a.C0632a.m1024d(r8, r7)
            goto L_0x010b
        L_0x00d0:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r11)
            r1.append(r5)
            r1.append(r9)
            r1.append(r8)
            r1.toString()
            goto L_0x010b
        L_0x00e5:
            int r5 = r1.read()
            r5 = r5 & 255(0xff, float:3.57E-43)
            switch(r5) {
                case 137: goto L_0x010b;
                case 138: goto L_0x010b;
                case 139: goto L_0x010b;
                case 140: goto L_0x010b;
                case 141: goto L_0x010b;
                case 142: goto L_0x010b;
                case 143: goto L_0x010b;
                case 144: goto L_0x010b;
                case 145: goto L_0x010b;
                case 146: goto L_0x010b;
                case 147: goto L_0x010b;
                case 148: goto L_0x010b;
                case 149: goto L_0x010b;
                case 150: goto L_0x010b;
                case 151: goto L_0x010b;
                default: goto L_0x00ee;
            }
        L_0x00ee:
            r6.mo1252e(r5, r8)     // Catch:{ InvalidHeaderValueException -> 0x00f7, RuntimeException -> 0x00f3 }
            goto L_0x0298
        L_0x00f3:
            p026b.p027a.p030b.p031a.C0632a.m1024d(r8, r7)
            goto L_0x010b
        L_0x00f7:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r11)
            r1.append(r5)
            r1.append(r9)
            r1.append(r8)
            r1.toString()
        L_0x010b:
            r1 = 1
        L_0x010c:
            r6 = r2
            goto L_0x029d
        L_0x010f:
            r5 = 1
            r1.mark(r5)
            int r7 = r1.read()
            r7 = r7 & 255(0xff, float:3.57E-43)
            r9 = 128(0x80, float:1.794E-43)
            if (r7 < r9) goto L_0x0164
            if (r9 != r7) goto L_0x012c
            java.lang.String r7 = "personal"
            byte[] r7 = r7.getBytes()     // Catch:{ NullPointerException -> 0x0299, RuntimeException -> 0x015f }
            r9 = 138(0x8a, float:1.93E-43)
            r6.mo1251b((byte[]) r7, (int) r9)     // Catch:{ NullPointerException -> 0x0299, RuntimeException -> 0x015f }
            goto L_0x0299
        L_0x012c:
            r9 = 129(0x81, float:1.81E-43)
            if (r9 != r7) goto L_0x013d
            java.lang.String r7 = "advertisement"
            byte[] r7 = r7.getBytes()     // Catch:{ NullPointerException -> 0x0299, RuntimeException -> 0x015f }
            r9 = 138(0x8a, float:1.93E-43)
            r6.mo1251b((byte[]) r7, (int) r9)     // Catch:{ NullPointerException -> 0x0299, RuntimeException -> 0x015f }
            goto L_0x0299
        L_0x013d:
            r9 = 130(0x82, float:1.82E-43)
            if (r9 != r7) goto L_0x014e
            java.lang.String r7 = "informational"
            byte[] r7 = r7.getBytes()     // Catch:{ NullPointerException -> 0x0299, RuntimeException -> 0x015f }
            r9 = 138(0x8a, float:1.93E-43)
            r6.mo1251b((byte[]) r7, (int) r9)     // Catch:{ NullPointerException -> 0x0299, RuntimeException -> 0x015f }
            goto L_0x0299
        L_0x014e:
            r9 = 131(0x83, float:1.84E-43)
            if (r9 != r7) goto L_0x0299
            java.lang.String r7 = "auto"
            byte[] r7 = r7.getBytes()     // Catch:{ NullPointerException -> 0x0299, RuntimeException -> 0x015f }
            r9 = 138(0x8a, float:1.93E-43)
            r6.mo1251b((byte[]) r7, (int) r9)     // Catch:{ NullPointerException -> 0x0299, RuntimeException -> 0x015f }
            goto L_0x0299
        L_0x015f:
            p026b.p027a.p030b.p031a.C0632a.m1024d(r8, r12)
            goto L_0x021f
        L_0x0164:
            r1.reset()
            r7 = 0
            byte[] r7 = m169a((java.io.ByteArrayInputStream) r1, (int) r7)
            if (r7 == 0) goto L_0x0299
            r9 = 138(0x8a, float:1.93E-43)
            r6.mo1251b((byte[]) r7, (int) r9)     // Catch:{ NullPointerException -> 0x0299, RuntimeException -> 0x0175 }
            goto L_0x0299
        L_0x0175:
            p026b.p027a.p030b.p031a.C0632a.m1024d(r8, r12)
            goto L_0x021f
        L_0x017a:
            r5 = 1
            m175f(r1)
            int r7 = r1.read()
            r7 = r7 & 255(0xff, float:3.57E-43)
            r9 = 128(0x80, float:1.794E-43)
            if (r9 != r7) goto L_0x01ac
            androidx.appcompat.mms.pdu.e r7 = m168a(r1)
            if (r7 == 0) goto L_0x01b7
            byte[] r9 = r7.mo1238fc()
            if (r9 == 0) goto L_0x01b7
            java.lang.String r10 = new java.lang.String
            r10.<init>(r9)
            int r9 = r10.indexOf(r15)
            if (r9 <= 0) goto L_0x01a4
            r11 = 0
            java.lang.String r10 = r10.substring(r11, r9)
        L_0x01a4:
            byte[] r9 = r10.getBytes()     // Catch:{ NullPointerException -> 0x021f }
            r7.mo1236b(r9)     // Catch:{ NullPointerException -> 0x021f }
            goto L_0x01b7
        L_0x01ac:
            androidx.appcompat.mms.pdu.e r7 = new androidx.appcompat.mms.pdu.e     // Catch:{ NullPointerException -> 0x01c2 }
            java.lang.String r9 = "insert-address-token"
            byte[] r9 = r9.getBytes()     // Catch:{ NullPointerException -> 0x01c2 }
            r7.<init>((byte[]) r9)     // Catch:{ NullPointerException -> 0x01c2 }
        L_0x01b7:
            r9 = 137(0x89, float:1.92E-43)
            r6.mo1250b((androidx.appcompat.mms.pdu.C0188e) r7, (int) r9)     // Catch:{ NullPointerException -> 0x0299, RuntimeException -> 0x01be }
            goto L_0x0299
        L_0x01be:
            p026b.p027a.p030b.p031a.C0632a.m1024d(r8, r13)
            goto L_0x021f
        L_0x01c2:
            p026b.p027a.p030b.p031a.C0632a.m1024d(r8, r13)
            goto L_0x021f
        L_0x01c6:
            r5 = 1
            m175f(r1)
            int r7 = r1.read()
            r7 = r7 & 255(0xff, float:3.57E-43)
            long r9 = m173c(r1)     // Catch:{ RuntimeException -> 0x01e9 }
            r11 = 129(0x81, float:1.81E-43)
            if (r11 != r7) goto L_0x01e0
            long r11 = java.lang.System.currentTimeMillis()
            r15 = 1000(0x3e8, double:4.94E-321)
            long r11 = r11 / r15
            long r9 = r9 + r11
        L_0x01e0:
            r6.mo1248a((long) r9, (int) r8)     // Catch:{ RuntimeException -> 0x01e5 }
            goto L_0x0299
        L_0x01e5:
            p026b.p027a.p030b.p031a.C0632a.m1024d(r8, r14)
            goto L_0x021f
        L_0x01e9:
            p026b.p027a.p030b.p031a.C0632a.m1024d(r8, r14)
            goto L_0x021f
        L_0x01ed:
            r5 = 1
            int r10 = r1.read()
            r10 = r10 & 255(0xff, float:3.57E-43)
            r6.mo1252e(r10, r8)     // Catch:{ InvalidHeaderValueException -> 0x01fd, RuntimeException -> 0x01f9 }
            goto L_0x0299
        L_0x01f9:
            p026b.p027a.p030b.p031a.C0632a.m1024d(r8, r7)
            goto L_0x021f
        L_0x01fd:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r11)
            r1.append(r10)
            r1.append(r9)
            r1.append(r8)
            r1.toString()
            goto L_0x021f
        L_0x0212:
            r5 = 1
            long r9 = m173c(r1)     // Catch:{ RuntimeException -> 0x021c }
            r6.mo1248a((long) r9, (int) r8)     // Catch:{ RuntimeException -> 0x021c }
            goto L_0x0299
        L_0x021c:
            p026b.p027a.p030b.p031a.C0632a.m1024d(r8, r14)
        L_0x021f:
            r1 = r5
            goto L_0x010c
        L_0x0222:
            r5 = 1
            java.util.HashMap r4 = new java.util.HashMap
            r4.<init>()
            byte[] r7 = m170a((java.io.ByteArrayInputStream) r1, (java.util.HashMap) r4)
            if (r7 == 0) goto L_0x0238
            r9 = 132(0x84, float:1.85E-43)
            r6.mo1251b((byte[]) r7, (int) r9)     // Catch:{ NullPointerException -> 0x0238, RuntimeException -> 0x0234 }
            goto L_0x0238
        L_0x0234:
            p026b.p027a.p030b.p031a.C0632a.m1024d(r8, r12)
            goto L_0x021f
        L_0x0238:
            r7 = 153(0x99, float:2.14E-43)
            java.lang.Integer r7 = java.lang.Integer.valueOf(r7)
            java.lang.Object r7 = r4.get(r7)
            byte[] r7 = (byte[]) r7
            f184lm = r7
            r7 = 131(0x83, float:1.84E-43)
            java.lang.Integer r7 = java.lang.Integer.valueOf(r7)
            java.lang.Object r4 = r4.get(r7)
            byte[] r4 = (byte[]) r4
            f183km = r4
            r7 = 0
            r4 = 0
            goto L_0x0019
        L_0x0258:
            r5 = 0
            r7 = 1
            byte[] r5 = m169a((java.io.ByteArrayInputStream) r1, (int) r5)
            if (r5 == 0) goto L_0x026a
            r6.mo1251b((byte[]) r5, (int) r8)     // Catch:{ NullPointerException -> 0x026a, RuntimeException -> 0x0264 }
            goto L_0x026a
        L_0x0264:
            p026b.p027a.p030b.p031a.C0632a.m1024d(r8, r12)
            r1 = r7
            goto L_0x010c
        L_0x026a:
            r5 = r7
            goto L_0x0299
        L_0x026c:
            r5 = 1
            androidx.appcompat.mms.pdu.e r7 = m168a(r1)
            if (r7 == 0) goto L_0x0299
            byte[] r9 = r7.mo1238fc()
            if (r9 == 0) goto L_0x0290
            java.lang.String r10 = new java.lang.String
            r10.<init>(r9)
            int r9 = r10.indexOf(r15)
            if (r9 <= 0) goto L_0x0289
            r11 = 0
            java.lang.String r10 = r10.substring(r11, r9)
        L_0x0289:
            byte[] r9 = r10.getBytes()     // Catch:{ NullPointerException -> 0x021f }
            r7.mo1236b(r9)     // Catch:{ NullPointerException -> 0x021f }
        L_0x0290:
            r6.mo1249a((androidx.appcompat.mms.pdu.C0188e) r7, (int) r8)     // Catch:{ NullPointerException -> 0x0299, RuntimeException -> 0x0294 }
            goto L_0x0299
        L_0x0294:
            p026b.p027a.p030b.p031a.C0632a.m1024d(r8, r13)
            goto L_0x021f
        L_0x0298:
            r5 = 1
        L_0x0299:
            r7 = 0
            goto L_0x0019
        L_0x029c:
            r1 = r5
        L_0x029d:
            r0.f186hm = r6
            androidx.appcompat.mms.pdu.l r4 = r0.f186hm
            if (r4 != 0) goto L_0x02a4
            return r2
        L_0x02a4:
            r5 = 140(0x8c, float:1.96E-43)
            int r4 = r4.mo1246F(r5)
            androidx.appcompat.mms.pdu.l r6 = r0.f186hm
            if (r6 != 0) goto L_0x02b0
            goto L_0x03c1
        L_0x02b0:
            int r5 = r6.mo1246F(r5)
            int r3 = r6.mo1246F(r3)
            if (r3 != 0) goto L_0x02bc
            goto L_0x03c1
        L_0x02bc:
            r3 = 155(0x9b, float:2.17E-43)
            r7 = 149(0x95, float:2.09E-43)
            r8 = 151(0x97, float:2.12E-43)
            r9 = 139(0x8b, float:1.95E-43)
            r10 = 133(0x85, float:1.86E-43)
            r11 = -1
            r13 = 152(0x98, float:2.13E-43)
            switch(r5) {
                case 128: goto L_0x03a9;
                case 129: goto L_0x0399;
                case 130: goto L_0x036a;
                case 131: goto L_0x035b;
                case 132: goto L_0x0347;
                case 133: goto L_0x033f;
                case 134: goto L_0x031d;
                case 135: goto L_0x02fb;
                case 136: goto L_0x02cf;
                default: goto L_0x02cd;
            }
        L_0x02cd:
            goto L_0x03c1
        L_0x02cf:
            long r13 = r6.mo1245E(r10)
            int r5 = (r11 > r13 ? 1 : (r11 == r13 ? 0 : -1))
            if (r5 != 0) goto L_0x02d9
            goto L_0x03c1
        L_0x02d9:
            r5 = 137(0x89, float:1.92E-43)
            androidx.appcompat.mms.pdu.e r5 = r6.mo1243C(r5)
            if (r5 != 0) goto L_0x02e3
            goto L_0x03c1
        L_0x02e3:
            byte[] r5 = r6.mo1247G(r9)
            if (r5 != 0) goto L_0x02eb
            goto L_0x03c1
        L_0x02eb:
            int r3 = r6.mo1246F(r3)
            if (r3 != 0) goto L_0x02f3
            goto L_0x03c1
        L_0x02f3:
            androidx.appcompat.mms.pdu.e[] r3 = r6.mo1244D(r8)
            if (r3 != 0) goto L_0x03c2
            goto L_0x03c1
        L_0x02fb:
            r5 = 137(0x89, float:1.92E-43)
            androidx.appcompat.mms.pdu.e r5 = r6.mo1243C(r5)
            if (r5 != 0) goto L_0x0305
            goto L_0x03c1
        L_0x0305:
            byte[] r5 = r6.mo1247G(r9)
            if (r5 != 0) goto L_0x030d
            goto L_0x03c1
        L_0x030d:
            int r3 = r6.mo1246F(r3)
            if (r3 != 0) goto L_0x0315
            goto L_0x03c1
        L_0x0315:
            androidx.appcompat.mms.pdu.e[] r3 = r6.mo1244D(r8)
            if (r3 != 0) goto L_0x03c2
            goto L_0x03c1
        L_0x031d:
            long r13 = r6.mo1245E(r10)
            int r3 = (r11 > r13 ? 1 : (r11 == r13 ? 0 : -1))
            if (r3 != 0) goto L_0x0327
            goto L_0x03c1
        L_0x0327:
            byte[] r3 = r6.mo1247G(r9)
            if (r3 != 0) goto L_0x032f
            goto L_0x03c1
        L_0x032f:
            int r3 = r6.mo1246F(r7)
            if (r3 != 0) goto L_0x0337
            goto L_0x03c1
        L_0x0337:
            androidx.appcompat.mms.pdu.e[] r3 = r6.mo1244D(r8)
            if (r3 != 0) goto L_0x03c2
            goto L_0x03c1
        L_0x033f:
            byte[] r3 = r6.mo1247G(r13)
            if (r3 != 0) goto L_0x03c2
            goto L_0x03c1
        L_0x0347:
            r3 = 132(0x84, float:1.85E-43)
            byte[] r3 = r6.mo1247G(r3)
            if (r3 != 0) goto L_0x0351
            goto L_0x03c1
        L_0x0351:
            long r5 = r6.mo1245E(r10)
            int r3 = (r11 > r5 ? 1 : (r11 == r5 ? 0 : -1))
            if (r3 != 0) goto L_0x03c2
            goto L_0x03c1
        L_0x035b:
            int r3 = r6.mo1246F(r7)
            if (r3 != 0) goto L_0x0363
            goto L_0x03c1
        L_0x0363:
            byte[] r3 = r6.mo1247G(r13)
            if (r3 != 0) goto L_0x03c2
            goto L_0x03c1
        L_0x036a:
            r3 = 131(0x83, float:1.84E-43)
            byte[] r3 = r6.mo1247G(r3)
            if (r3 != 0) goto L_0x0373
            goto L_0x03c1
        L_0x0373:
            r3 = 136(0x88, float:1.9E-43)
            long r7 = r6.mo1245E(r3)
            int r3 = (r11 > r7 ? 1 : (r11 == r7 ? 0 : -1))
            if (r3 != 0) goto L_0x037e
            goto L_0x03c1
        L_0x037e:
            r3 = 138(0x8a, float:1.93E-43)
            byte[] r3 = r6.mo1247G(r3)
            if (r3 != 0) goto L_0x0387
            goto L_0x03c1
        L_0x0387:
            r3 = 142(0x8e, float:1.99E-43)
            long r7 = r6.mo1245E(r3)
            int r3 = (r11 > r7 ? 1 : (r11 == r7 ? 0 : -1))
            if (r3 != 0) goto L_0x0392
            goto L_0x03c1
        L_0x0392:
            byte[] r3 = r6.mo1247G(r13)
            if (r3 != 0) goto L_0x03c2
            goto L_0x03c1
        L_0x0399:
            r3 = 146(0x92, float:2.05E-43)
            int r3 = r6.mo1246F(r3)
            if (r3 != 0) goto L_0x03a2
            goto L_0x03c1
        L_0x03a2:
            byte[] r3 = r6.mo1247G(r13)
            if (r3 != 0) goto L_0x03c2
            goto L_0x03c1
        L_0x03a9:
            r3 = 132(0x84, float:1.85E-43)
            byte[] r3 = r6.mo1247G(r3)
            if (r3 != 0) goto L_0x03b2
            goto L_0x03c1
        L_0x03b2:
            r3 = 137(0x89, float:1.92E-43)
            androidx.appcompat.mms.pdu.e r3 = r6.mo1243C(r3)
            if (r3 != 0) goto L_0x03bb
            goto L_0x03c1
        L_0x03bb:
            byte[] r3 = r6.mo1247G(r13)
            if (r3 != 0) goto L_0x03c2
        L_0x03c1:
            r1 = 0
        L_0x03c2:
            if (r1 != 0) goto L_0x03c5
            return r2
        L_0x03c5:
            r1 = 128(0x80, float:1.794E-43)
            if (r1 == r4) goto L_0x03cd
            r1 = 132(0x84, float:1.85E-43)
            if (r1 != r4) goto L_0x03da
        L_0x03cd:
            java.io.ByteArrayInputStream r1 = r0.f185gm
            androidx.appcompat.mms.pdu.j r1 = r0.mo1253d(r1)
            r0.f187im = r1
            androidx.appcompat.mms.pdu.j r1 = r0.f187im
            if (r1 != 0) goto L_0x03da
            return r2
        L_0x03da:
            switch(r4) {
                case 128: goto L_0x0463;
                case 129: goto L_0x045b;
                case 130: goto L_0x0453;
                case 131: goto L_0x044b;
                case 132: goto L_0x03ff;
                case 133: goto L_0x03f7;
                case 134: goto L_0x03ef;
                case 135: goto L_0x03e7;
                case 136: goto L_0x03df;
                default: goto L_0x03dd;
            }
        L_0x03dd:
            goto L_0x046d
        L_0x03df:
            androidx.appcompat.mms.pdu.o r1 = new androidx.appcompat.mms.pdu.o
            androidx.appcompat.mms.pdu.l r0 = r0.f186hm
            r1.<init>(r0)
            return r1
        L_0x03e7:
            androidx.appcompat.mms.pdu.p r1 = new androidx.appcompat.mms.pdu.p
            androidx.appcompat.mms.pdu.l r0 = r0.f186hm
            r1.<init>(r0)
            return r1
        L_0x03ef:
            androidx.appcompat.mms.pdu.d r1 = new androidx.appcompat.mms.pdu.d
            androidx.appcompat.mms.pdu.l r0 = r0.f186hm
            r1.<init>(r0)
            return r1
        L_0x03f7:
            androidx.appcompat.mms.pdu.a r1 = new androidx.appcompat.mms.pdu.a
            androidx.appcompat.mms.pdu.l r0 = r0.f186hm
            r1.<init>(r0)
            return r1
        L_0x03ff:
            androidx.appcompat.mms.pdu.q r1 = new androidx.appcompat.mms.pdu.q
            androidx.appcompat.mms.pdu.l r3 = r0.f186hm
            androidx.appcompat.mms.pdu.j r4 = r0.f187im
            r1.<init>(r3, r4)
            androidx.appcompat.mms.pdu.l r3 = r1.f175Zl
            r4 = 132(0x84, float:1.85E-43)
            byte[] r3 = r3.mo1247G(r4)
            if (r3 != 0) goto L_0x0413
            return r2
        L_0x0413:
            java.lang.String r4 = new java.lang.String
            r4.<init>(r3)
            java.lang.String r3 = "application/vnd.wap.multipart.mixed"
            boolean r3 = r4.equals(r3)
            if (r3 != 0) goto L_0x044a
            java.lang.String r3 = "application/vnd.wap.multipart.related"
            boolean r3 = r4.equals(r3)
            if (r3 != 0) goto L_0x044a
            java.lang.String r3 = "application/vnd.wap.multipart.alternative"
            boolean r5 = r4.equals(r3)
            if (r5 == 0) goto L_0x0431
            goto L_0x044a
        L_0x0431:
            boolean r3 = r4.equals(r3)
            if (r3 == 0) goto L_0x0449
            androidx.appcompat.mms.pdu.j r2 = r0.f187im
            r3 = 0
            androidx.appcompat.mms.pdu.n r2 = r2.getPart(r3)
            androidx.appcompat.mms.pdu.j r4 = r0.f187im
            r4.removeAll()
            androidx.appcompat.mms.pdu.j r0 = r0.f187im
            r0.mo1239a(r3, r2)
            return r1
        L_0x0449:
            return r2
        L_0x044a:
            return r1
        L_0x044b:
            androidx.appcompat.mms.pdu.i r1 = new androidx.appcompat.mms.pdu.i
            androidx.appcompat.mms.pdu.l r0 = r0.f186hm
            r1.<init>(r0)
            return r1
        L_0x0453:
            androidx.appcompat.mms.pdu.h r1 = new androidx.appcompat.mms.pdu.h
            androidx.appcompat.mms.pdu.l r0 = r0.f186hm
            r1.<init>(r0)
            return r1
        L_0x045b:
            androidx.appcompat.mms.pdu.r r1 = new androidx.appcompat.mms.pdu.r
            androidx.appcompat.mms.pdu.l r0 = r0.f186hm
            r1.<init>(r0)
            return r1
        L_0x0463:
            androidx.appcompat.mms.pdu.s r1 = new androidx.appcompat.mms.pdu.s
            androidx.appcompat.mms.pdu.l r2 = r0.f186hm
            androidx.appcompat.mms.pdu.j r0 = r0.f187im
            r1.<init>(r2, r0)
            return r1
        L_0x046d:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.mms.pdu.C0196m.parse():androidx.appcompat.mms.pdu.f");
    }

    /* renamed from: b */
    protected static int m171b(ByteArrayInputStream byteArrayInputStream, int i) {
        int read = byteArrayInputStream.read(new byte[i], 0, i);
        if (read < i) {
            return -1;
        }
        return read;
    }

    /* renamed from: a */
    protected static byte[] m169a(ByteArrayInputStream byteArrayInputStream, int i) {
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
    protected static byte[] m170a(ByteArrayInputStream byteArrayInputStream, HashMap hashMap) {
        byte[] bArr;
        int i;
        int i2;
        int i3;
        ByteArrayInputStream byteArrayInputStream2 = byteArrayInputStream;
        HashMap hashMap2 = hashMap;
        byteArrayInputStream2.mark(1);
        int read = byteArrayInputStream.read();
        byteArrayInputStream.reset();
        int i4 = read & 255;
        if (i4 < 32) {
            int f = m175f(byteArrayInputStream);
            int available = byteArrayInputStream.available();
            byteArrayInputStream2.mark(1);
            int read2 = byteArrayInputStream.read();
            byteArrayInputStream.reset();
            int i5 = read2 & 255;
            if (i5 >= 32 && i5 <= 127) {
                bArr = m169a(byteArrayInputStream2, 0);
            } else if (i5 > 127) {
                int read3 = byteArrayInputStream.read() & 127;
                String[] strArr = C0194k.f181em;
                if (read3 < strArr.length) {
                    bArr = strArr[read3].getBytes();
                } else {
                    byteArrayInputStream.reset();
                    bArr = m169a(byteArrayInputStream2, 0);
                }
            } else {
                Log.e("PduParser", "Corrupt content-type");
                return C0194k.f181em[0].getBytes();
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
                                byte[] a = m169a(byteArrayInputStream2, 0);
                                if (!(a == null || hashMap2 == null)) {
                                    hashMap2.put(151, a);
                                }
                                i2 = byteArrayInputStream.available();
                                i3 = valueOf.intValue();
                                i = i3;
                            } else {
                                if (read4 != 153) {
                                    if (read4 != 137) {
                                        if (read4 != 138) {
                                            if (-1 == m171b(byteArrayInputStream2, intValue)) {
                                                Log.e("PduParser", "Corrupt Content-Type");
                                            } else {
                                                intValue = 0;
                                            }
                                        }
                                    }
                                }
                                byte[] a2 = m169a(byteArrayInputStream2, 0);
                                if (!(a2 == null || hashMap2 == null)) {
                                    hashMap2.put(153, a2);
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
                            String[] strArr2 = C0194k.f181em;
                            if (read6 < strArr2.length) {
                                hashMap2.put(131, strArr2[read6].getBytes());
                            }
                        } else {
                            byte[] a3 = m169a(byteArrayInputStream2, 0);
                            if (!(a3 == null || hashMap2 == null)) {
                                hashMap2.put(131, a3);
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
                            int b = (int) m172b(byteArrayInputStream);
                            if (hashMap2 != null) {
                                hashMap2.put(129, Integer.valueOf(b));
                            }
                        } else {
                            byte[] a4 = m169a(byteArrayInputStream2, 0);
                            try {
                                hashMap2.put(129, Integer.valueOf(C0186c.m152x(new String(a4))));
                            } catch (UnsupportedEncodingException e) {
                                Log.e("PduParser", Arrays.toString(a4), e);
                                hashMap2.put(129, 0);
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
            return C0194k.f181em[0].getBytes();
        } else if (i4 <= 127) {
            return m169a(byteArrayInputStream2, 0);
        } else {
            return C0194k.f181em[byteArrayInputStream.read() & 127].getBytes();
        }
    }
}
