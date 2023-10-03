package p000;

import com.bumptech.glide.load.ImageHeaderParser$ImageType;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;

/* renamed from: azu */
/* compiled from: PG */
public final class azu implements aqm {

    /* renamed from: a */
    private static final byte[] f1915a = "Exif\u0000\u0000".getBytes(Charset.forName("UTF-8"));

    /* renamed from: b */
    private static final int[] f1916b = {0, 1, 1, 2, 4, 8, 1, 1, 2, 4, 8, 4, 8};

    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0025, code lost:
        if (r1 != 18761) goto L_0x0027;
     */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0047 A[Catch:{ all -> 0x006e, azr -> 0x0115 }] */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0055 A[Catch:{ all -> 0x006e, azr -> 0x0115 }] */
    /* JADX WARNING: Removed duplicated region for block: B:81:0x0051 A[SYNTHETIC] */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final int mo1489a(java.io.InputStream r10, p000.aui r11) {
        /*
            r9 = this;
            azt r0 = new azt
            java.lang.Object r10 = p000.cns.m4632a((java.lang.Object) r10)
            java.io.InputStream r10 = (java.io.InputStream) r10
            r0.<init>(r10)
            java.lang.Object r10 = p000.cns.m4632a((java.lang.Object) r11)
            aui r10 = (p000.aui) r10
            r11 = -1
            int r1 = r0.mo1743b()     // Catch:{ azr -> 0x0115 }
            r2 = 65496(0xffd8, float:9.178E-41)
            r3 = r1 & r2
            r4 = 18761(0x4949, float:2.629E-41)
            r5 = 19789(0x4d4d, float:2.773E-41)
            if (r3 != r2) goto L_0x0022
            goto L_0x0029
        L_0x0022:
            if (r1 == r5) goto L_0x0029
            if (r1 == r4) goto L_0x0029
        L_0x0027:
            goto L_0x0117
        L_0x0029:
            short r1 = r0.mo1742a()     // Catch:{ azr -> 0x0115 }
            r2 = 255(0xff, float:3.57E-43)
            if (r1 != r2) goto L_0x0052
            short r1 = r0.mo1742a()     // Catch:{ azr -> 0x0115 }
            r2 = 218(0xda, float:3.05E-43)
            if (r1 == r2) goto L_0x0052
            r2 = 217(0xd9, float:3.04E-43)
            if (r1 == r2) goto L_0x0052
            int r2 = r0.mo1743b()     // Catch:{ azr -> 0x0115 }
            int r2 = r2 + -2
            r3 = 225(0xe1, float:3.15E-43)
            if (r1 == r3) goto L_0x0051
            long r1 = (long) r2     // Catch:{ azr -> 0x0115 }
            long r6 = r0.mo1741a(r1)     // Catch:{ azr -> 0x0115 }
            int r3 = (r6 > r1 ? 1 : (r6 == r1 ? 0 : -1))
            if (r3 == 0) goto L_0x0029
            goto L_0x0052
        L_0x0051:
            goto L_0x0053
        L_0x0052:
            r2 = -1
        L_0x0053:
            if (r2 == r11) goto L_0x0113
            java.lang.Class<byte[]> r1 = byte[].class
            java.lang.Object r1 = r10.mo1634a(r2, r1)     // Catch:{ azr -> 0x0115 }
            byte[] r1 = (byte[]) r1     // Catch:{ azr -> 0x0115 }
            r3 = 0
            r6 = 0
            r7 = 0
        L_0x0060:
            if (r6 >= r2) goto L_0x0071
            java.io.InputStream r7 = r0.f1914a     // Catch:{ all -> 0x006e }
            int r8 = r2 - r6
            int r7 = r7.read(r1, r6, r8)     // Catch:{ all -> 0x006e }
            if (r7 == r11) goto L_0x0071
            int r6 = r6 + r7
            goto L_0x0060
        L_0x006e:
            r0 = move-exception
            goto L_0x010a
        L_0x0071:
            if (r6 != 0) goto L_0x007c
            if (r7 == r11) goto L_0x0076
            goto L_0x007c
        L_0x0076:
            azr r0 = new azr     // Catch:{ all -> 0x006e }
            r0.<init>()     // Catch:{ all -> 0x006e }
            throw r0     // Catch:{ all -> 0x006e }
        L_0x007c:
            if (r6 == r2) goto L_0x0081
        L_0x007e:
            r0 = -1
            goto L_0x010e
        L_0x0081:
            if (r1 == 0) goto L_0x007e
            byte[] r0 = f1915a     // Catch:{ all -> 0x006e }
            int r0 = r0.length     // Catch:{ all -> 0x006e }
            if (r2 <= r0) goto L_0x0108
            r0 = 0
        L_0x0089:
            byte[] r6 = f1915a     // Catch:{ all -> 0x006e }
            int r6 = r6.length     // Catch:{ all -> 0x006e }
            if (r0 >= r6) goto L_0x0099
            byte r6 = r1[r0]     // Catch:{ all -> 0x006e }
            byte[] r7 = f1915a     // Catch:{ all -> 0x006e }
            byte r7 = r7[r0]     // Catch:{ all -> 0x006e }
            if (r6 != r7) goto L_0x007e
            int r0 = r0 + 1
            goto L_0x0089
        L_0x0099:
            azq r0 = new azq     // Catch:{ all -> 0x006e }
            r0.<init>(r1, r2)     // Catch:{ all -> 0x006e }
            r2 = 6
            short r6 = r0.mo1746b(r2)     // Catch:{ all -> 0x006e }
            if (r6 == r4) goto L_0x00ad
            if (r6 == r5) goto L_0x00aa
            java.nio.ByteOrder r4 = java.nio.ByteOrder.BIG_ENDIAN     // Catch:{ all -> 0x006e }
            goto L_0x00af
        L_0x00aa:
            java.nio.ByteOrder r4 = java.nio.ByteOrder.BIG_ENDIAN     // Catch:{ all -> 0x006e }
            goto L_0x00af
        L_0x00ad:
            java.nio.ByteOrder r4 = java.nio.ByteOrder.LITTLE_ENDIAN     // Catch:{ all -> 0x006e }
        L_0x00af:
            java.nio.ByteBuffer r5 = r0.f1913a     // Catch:{ all -> 0x006e }
            r5.order(r4)     // Catch:{ all -> 0x006e }
            r4 = 10
            int r4 = r0.mo1745a(r4)     // Catch:{ all -> 0x006e }
            int r4 = r4 + r2
            short r2 = r0.mo1746b(r4)     // Catch:{ all -> 0x006e }
        L_0x00bf:
            if (r3 >= r2) goto L_0x0106
            int r5 = r4 + 2
            int r6 = r3 * 12
            int r5 = r5 + r6
            short r6 = r0.mo1746b(r5)     // Catch:{ all -> 0x006e }
            r7 = 274(0x112, float:3.84E-43)
            if (r6 != r7) goto L_0x0103
            int r6 = r5 + 2
            short r6 = r0.mo1746b(r6)     // Catch:{ all -> 0x006e }
            if (r6 > 0) goto L_0x00d7
        L_0x00d6:
            goto L_0x0103
        L_0x00d7:
            r7 = 12
            if (r6 > r7) goto L_0x00d6
            int r7 = r5 + 4
            int r7 = r0.mo1745a(r7)     // Catch:{ all -> 0x006e }
            if (r7 < 0) goto L_0x0103
            int[] r8 = f1916b     // Catch:{ all -> 0x006e }
            r6 = r8[r6]     // Catch:{ all -> 0x006e }
            int r7 = r7 + r6
            r6 = 4
            if (r7 > r6) goto L_0x0103
            int r5 = r5 + 8
            if (r5 < 0) goto L_0x0103
            int r6 = r0.mo1744a()     // Catch:{ all -> 0x006e }
            if (r5 > r6) goto L_0x0103
            if (r7 < 0) goto L_0x0103
            int r7 = r7 + r5
            int r6 = r0.mo1744a()     // Catch:{ all -> 0x006e }
            if (r7 > r6) goto L_0x0103
            short r0 = r0.mo1746b(r5)     // Catch:{ all -> 0x006e }
            goto L_0x010e
        L_0x0103:
            int r3 = r3 + 1
            goto L_0x00bf
        L_0x0106:
            goto L_0x007e
        L_0x0108:
            goto L_0x007e
        L_0x010a:
            r10.mo1638a((java.lang.Object) r1)     // Catch:{ azr -> 0x0115 }
            throw r0     // Catch:{ azr -> 0x0115 }
        L_0x010e:
            r10.mo1638a((java.lang.Object) r1)     // Catch:{ azr -> 0x0115 }
            r11 = r0
            goto L_0x0117
        L_0x0113:
            goto L_0x0027
        L_0x0115:
            r10 = move-exception
        L_0x0117:
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.azu.mo1489a(java.io.InputStream, aui):int");
    }

    /* renamed from: a */
    private static final ImageHeaderParser$ImageType m1988a(azs azs) {
        try {
            int b = azs.mo1743b();
            if (b == 65496) {
                return ImageHeaderParser$ImageType.JPEG;
            }
            short a = (b << 8) | azs.mo1742a();
            if (a == 4671814) {
                return ImageHeaderParser$ImageType.GIF;
            }
            short a2 = (a << 8) | azs.mo1742a();
            if (a2 == -1991225785) {
                azs.mo1741a(21);
                try {
                    return azs.mo1742a() < 3 ? ImageHeaderParser$ImageType.PNG : ImageHeaderParser$ImageType.PNG_A;
                } catch (azr e) {
                    return ImageHeaderParser$ImageType.PNG;
                }
            } else if (a2 != 1380533830) {
                return ImageHeaderParser$ImageType.UNKNOWN;
            } else {
                azs.mo1741a(4);
                if (((azs.mo1743b() << 16) | azs.mo1743b()) != 1464156752) {
                    return ImageHeaderParser$ImageType.UNKNOWN;
                }
                int b2 = (azs.mo1743b() << 16) | azs.mo1743b();
                if ((b2 & -256) != 1448097792) {
                    return ImageHeaderParser$ImageType.UNKNOWN;
                }
                int i = b2 & 255;
                if (i == 88) {
                    azs.mo1741a(4);
                    return (azs.mo1742a() & 16) == 0 ? ImageHeaderParser$ImageType.WEBP : ImageHeaderParser$ImageType.WEBP_A;
                } else if (i != 76) {
                    return ImageHeaderParser$ImageType.WEBP;
                } else {
                    azs.mo1741a(4);
                    return (azs.mo1742a() & 8) == 0 ? ImageHeaderParser$ImageType.WEBP : ImageHeaderParser$ImageType.WEBP_A;
                }
            }
        } catch (azr e2) {
            return ImageHeaderParser$ImageType.UNKNOWN;
        }
    }

    /* renamed from: a */
    public final ImageHeaderParser$ImageType mo1490a(InputStream inputStream) {
        return m1988a((azs) new azt((InputStream) cns.m4632a((Object) inputStream)));
    }

    /* renamed from: a */
    public final ImageHeaderParser$ImageType mo1491a(ByteBuffer byteBuffer) {
        return m1988a((azs) new azp((ByteBuffer) cns.m4632a((Object) byteBuffer)));
    }
}
