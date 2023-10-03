package p000;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* renamed from: abw */
/* compiled from: PG */
final class abw {

    /* renamed from: a */
    public final int f115a;

    /* renamed from: b */
    public final byte[] f116b;

    /* renamed from: c */
    private final int f117c;

    public abw(int i, int i2, byte[] bArr, byte[] bArr2) {
        this.f115a = i;
        this.f117c = i2;
        this.f116b = bArr;
    }

    private abw(int i, int i2, byte[] bArr) {
        this(i, i2, bArr, (byte[]) null);
    }

    /* renamed from: a */
    public static abw m175a(String str) {
        byte[] bytes = (str + 0).getBytes(abz.f138d);
        return new abw(2, bytes.length, bytes);
    }

    /* renamed from: a */
    public static abw m173a(long j, ByteOrder byteOrder) {
        long[] jArr = {j};
        ByteBuffer wrap = ByteBuffer.wrap(new byte[abz.f136b[4]]);
        wrap.order(byteOrder);
        wrap.putInt((int) jArr[0]);
        return new abw(4, 1, wrap.array());
    }

    /* renamed from: a */
    public static abw m174a(aby aby, ByteOrder byteOrder) {
        aby[] abyArr = {aby};
        ByteBuffer wrap = ByteBuffer.wrap(new byte[abz.f136b[5]]);
        wrap.order(byteOrder);
        aby aby2 = abyArr[0];
        wrap.putInt((int) aby2.f122a);
        wrap.putInt((int) aby2.f123b);
        return new abw(5, 1, wrap.array());
    }

    /* renamed from: a */
    public static abw m172a(int i, ByteOrder byteOrder) {
        int[] iArr = {i};
        ByteBuffer wrap = ByteBuffer.wrap(new byte[abz.f136b[3]]);
        wrap.order(byteOrder);
        wrap.putShort((short) iArr[0]);
        return new abw(3, 1, wrap.array());
    }

    /* renamed from: b */
    public final int mo148b(ByteOrder byteOrder) {
        Object a = mo147a(byteOrder);
        if (a == null) {
            throw new NumberFormatException("NULL can't be converted to a integer value");
        } else if (a instanceof String) {
            return Integer.parseInt((String) a);
        } else {
            if (a instanceof long[]) {
                long[] jArr = (long[]) a;
                if (jArr.length == 1) {
                    return (int) jArr[0];
                }
                throw new NumberFormatException("There are more than one component");
            } else if (a instanceof int[]) {
                int[] iArr = (int[]) a;
                if (iArr.length == 1) {
                    return iArr[0];
                }
                throw new NumberFormatException("There are more than one component");
            } else {
                throw new NumberFormatException("Couldn't find a integer value");
            }
        }
    }

    /* renamed from: c */
    public final String mo149c(ByteOrder byteOrder) {
        Object a = mo147a(byteOrder);
        if (a == null) {
            return null;
        }
        if (a instanceof String) {
            return (String) a;
        }
        StringBuilder sb = new StringBuilder();
        int i = 0;
        if (a instanceof long[]) {
            long[] jArr = (long[]) a;
            while (true) {
                int length = jArr.length;
                if (i >= length) {
                    return sb.toString();
                }
                sb.append(jArr[i]);
                i++;
                if (i != length) {
                    sb.append(",");
                }
            }
        } else if (a instanceof int[]) {
            int[] iArr = (int[]) a;
            while (true) {
                int length2 = iArr.length;
                if (i >= length2) {
                    return sb.toString();
                }
                sb.append(iArr[i]);
                i++;
                if (i != length2) {
                    sb.append(",");
                }
            }
        } else if (a instanceof double[]) {
            double[] dArr = (double[]) a;
            while (true) {
                int length3 = dArr.length;
                if (i >= length3) {
                    return sb.toString();
                }
                sb.append(dArr[i]);
                i++;
                if (i != length3) {
                    sb.append(",");
                }
            }
        } else if (!(a instanceof aby[])) {
            return null;
        } else {
            aby[] abyArr = (aby[]) a;
            while (true) {
                int length4 = abyArr.length;
                if (i >= length4) {
                    return sb.toString();
                }
                sb.append(abyArr[i].f122a);
                sb.append('/');
                sb.append(abyArr[i].f123b);
                i++;
                if (i != length4) {
                    sb.append(",");
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:157:0x0194 A[SYNTHETIC, Splitter:B:157:0x0194] */
    /* JADX WARNING: Removed duplicated region for block: B:165:0x01a2 A[SYNTHETIC, Splitter:B:165:0x01a2] */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object mo147a(java.nio.ByteOrder r11) {
        /*
            r10 = this;
            java.lang.String r0 = "IOException occurred while closing InputStream"
            java.lang.String r1 = "ExifInterface"
            r2 = 0
            abv r3 = new abv     // Catch:{ IOException -> 0x018b, all -> 0x0189 }
            byte[] r4 = r10.f116b     // Catch:{ IOException -> 0x018b, all -> 0x0189 }
            r3.<init>((byte[]) r4)     // Catch:{ IOException -> 0x018b, all -> 0x0189 }
            r3.f111a = r11     // Catch:{ IOException -> 0x0186, all -> 0x0183 }
            int r11 = r10.f115a     // Catch:{ IOException -> 0x0186, all -> 0x0183 }
            r4 = 0
            switch(r11) {
                case 1: goto L_0x014c;
                case 2: goto L_0x00fe;
                case 3: goto L_0x00e4;
                case 4: goto L_0x00ca;
                case 5: goto L_0x00a7;
                case 6: goto L_0x014c;
                case 7: goto L_0x00fe;
                case 8: goto L_0x008d;
                case 9: goto L_0x0073;
                case 10: goto L_0x004e;
                case 11: goto L_0x0033;
                case 12: goto L_0x0019;
                default: goto L_0x0014;
            }
        L_0x0014:
            r3.close()     // Catch:{ IOException -> 0x017e }
            goto L_0x0182
        L_0x0019:
            int r11 = r10.f117c     // Catch:{ IOException -> 0x0186, all -> 0x0183 }
            double[] r11 = new double[r11]     // Catch:{ IOException -> 0x0186, all -> 0x0183 }
        L_0x001d:
            int r5 = r10.f117c     // Catch:{ IOException -> 0x0186, all -> 0x0183 }
            if (r4 >= r5) goto L_0x002a
            double r5 = r3.readDouble()     // Catch:{ IOException -> 0x0186, all -> 0x0183 }
            r11[r4] = r5     // Catch:{ IOException -> 0x0186, all -> 0x0183 }
            int r4 = r4 + 1
            goto L_0x001d
        L_0x002a:
            r3.close()     // Catch:{ IOException -> 0x002e }
            goto L_0x0032
        L_0x002e:
            r2 = move-exception
            android.util.Log.e(r1, r0, r2)
        L_0x0032:
            return r11
        L_0x0033:
            int r11 = r10.f117c     // Catch:{ IOException -> 0x0186, all -> 0x0183 }
            double[] r11 = new double[r11]     // Catch:{ IOException -> 0x0186, all -> 0x0183 }
        L_0x0037:
            int r5 = r10.f117c     // Catch:{ IOException -> 0x0186, all -> 0x0183 }
            if (r4 >= r5) goto L_0x0045
            float r5 = r3.readFloat()     // Catch:{ IOException -> 0x0186, all -> 0x0183 }
            double r5 = (double) r5     // Catch:{ IOException -> 0x0186, all -> 0x0183 }
            r11[r4] = r5     // Catch:{ IOException -> 0x0186, all -> 0x0183 }
            int r4 = r4 + 1
            goto L_0x0037
        L_0x0045:
            r3.close()     // Catch:{ IOException -> 0x0049 }
            goto L_0x004d
        L_0x0049:
            r2 = move-exception
            android.util.Log.e(r1, r0, r2)
        L_0x004d:
            return r11
        L_0x004e:
            int r11 = r10.f117c     // Catch:{ IOException -> 0x0186, all -> 0x0183 }
            aby[] r11 = new p000.aby[r11]     // Catch:{ IOException -> 0x0186, all -> 0x0183 }
        L_0x0052:
            int r5 = r10.f117c     // Catch:{ IOException -> 0x0186, all -> 0x0183 }
            if (r4 >= r5) goto L_0x006a
            int r5 = r3.readInt()     // Catch:{ IOException -> 0x0186, all -> 0x0183 }
            long r5 = (long) r5     // Catch:{ IOException -> 0x0186, all -> 0x0183 }
            int r7 = r3.readInt()     // Catch:{ IOException -> 0x0186, all -> 0x0183 }
            long r7 = (long) r7     // Catch:{ IOException -> 0x0186, all -> 0x0183 }
            aby r9 = new aby     // Catch:{ IOException -> 0x0186, all -> 0x0183 }
            r9.<init>(r5, r7)     // Catch:{ IOException -> 0x0186, all -> 0x0183 }
            r11[r4] = r9     // Catch:{ IOException -> 0x0186, all -> 0x0183 }
            int r4 = r4 + 1
            goto L_0x0052
        L_0x006a:
            r3.close()     // Catch:{ IOException -> 0x006e }
            goto L_0x0072
        L_0x006e:
            r2 = move-exception
            android.util.Log.e(r1, r0, r2)
        L_0x0072:
            return r11
        L_0x0073:
            int r11 = r10.f117c     // Catch:{ IOException -> 0x0186, all -> 0x0183 }
            int[] r11 = new int[r11]     // Catch:{ IOException -> 0x0186, all -> 0x0183 }
        L_0x0077:
            int r5 = r10.f117c     // Catch:{ IOException -> 0x0186, all -> 0x0183 }
            if (r4 >= r5) goto L_0x0084
            int r5 = r3.readInt()     // Catch:{ IOException -> 0x0186, all -> 0x0183 }
            r11[r4] = r5     // Catch:{ IOException -> 0x0186, all -> 0x0183 }
            int r4 = r4 + 1
            goto L_0x0077
        L_0x0084:
            r3.close()     // Catch:{ IOException -> 0x0088 }
            goto L_0x008c
        L_0x0088:
            r2 = move-exception
            android.util.Log.e(r1, r0, r2)
        L_0x008c:
            return r11
        L_0x008d:
            int r11 = r10.f117c     // Catch:{ IOException -> 0x0186, all -> 0x0183 }
            int[] r11 = new int[r11]     // Catch:{ IOException -> 0x0186, all -> 0x0183 }
        L_0x0091:
            int r5 = r10.f117c     // Catch:{ IOException -> 0x0186, all -> 0x0183 }
            if (r4 >= r5) goto L_0x009e
            short r5 = r3.readShort()     // Catch:{ IOException -> 0x0186, all -> 0x0183 }
            r11[r4] = r5     // Catch:{ IOException -> 0x0186, all -> 0x0183 }
            int r4 = r4 + 1
            goto L_0x0091
        L_0x009e:
            r3.close()     // Catch:{ IOException -> 0x00a2 }
            goto L_0x00a6
        L_0x00a2:
            r2 = move-exception
            android.util.Log.e(r1, r0, r2)
        L_0x00a6:
            return r11
        L_0x00a7:
            int r11 = r10.f117c     // Catch:{ IOException -> 0x0186, all -> 0x0183 }
            aby[] r11 = new p000.aby[r11]     // Catch:{ IOException -> 0x0186, all -> 0x0183 }
        L_0x00ab:
            int r5 = r10.f117c     // Catch:{ IOException -> 0x0186, all -> 0x0183 }
            if (r4 >= r5) goto L_0x00c1
            long r5 = r3.mo127a()     // Catch:{ IOException -> 0x0186, all -> 0x0183 }
            long r7 = r3.mo127a()     // Catch:{ IOException -> 0x0186, all -> 0x0183 }
            aby r9 = new aby     // Catch:{ IOException -> 0x0186, all -> 0x0183 }
            r9.<init>(r5, r7)     // Catch:{ IOException -> 0x0186, all -> 0x0183 }
            r11[r4] = r9     // Catch:{ IOException -> 0x0186, all -> 0x0183 }
            int r4 = r4 + 1
            goto L_0x00ab
        L_0x00c1:
            r3.close()     // Catch:{ IOException -> 0x00c5 }
            goto L_0x00c9
        L_0x00c5:
            r2 = move-exception
            android.util.Log.e(r1, r0, r2)
        L_0x00c9:
            return r11
        L_0x00ca:
            int r11 = r10.f117c     // Catch:{ IOException -> 0x0186, all -> 0x0183 }
            long[] r11 = new long[r11]     // Catch:{ IOException -> 0x0186, all -> 0x0183 }
        L_0x00ce:
            int r5 = r10.f117c     // Catch:{ IOException -> 0x0186, all -> 0x0183 }
            if (r4 >= r5) goto L_0x00db
            long r5 = r3.mo127a()     // Catch:{ IOException -> 0x0186, all -> 0x0183 }
            r11[r4] = r5     // Catch:{ IOException -> 0x0186, all -> 0x0183 }
            int r4 = r4 + 1
            goto L_0x00ce
        L_0x00db:
            r3.close()     // Catch:{ IOException -> 0x00df }
            goto L_0x00e3
        L_0x00df:
            r2 = move-exception
            android.util.Log.e(r1, r0, r2)
        L_0x00e3:
            return r11
        L_0x00e4:
            int r11 = r10.f117c     // Catch:{ IOException -> 0x0186, all -> 0x0183 }
            int[] r11 = new int[r11]     // Catch:{ IOException -> 0x0186, all -> 0x0183 }
        L_0x00e8:
            int r5 = r10.f117c     // Catch:{ IOException -> 0x0186, all -> 0x0183 }
            if (r4 >= r5) goto L_0x00f5
            int r5 = r3.readUnsignedShort()     // Catch:{ IOException -> 0x0186, all -> 0x0183 }
            r11[r4] = r5     // Catch:{ IOException -> 0x0186, all -> 0x0183 }
            int r4 = r4 + 1
            goto L_0x00e8
        L_0x00f5:
            r3.close()     // Catch:{ IOException -> 0x00f9 }
            goto L_0x00fd
        L_0x00f9:
            r2 = move-exception
            android.util.Log.e(r1, r0, r2)
        L_0x00fd:
            return r11
        L_0x00fe:
            int r11 = r10.f117c     // Catch:{ IOException -> 0x0186, all -> 0x0183 }
            byte[] r5 = p000.abz.f137c     // Catch:{ IOException -> 0x0186, all -> 0x0183 }
            int r5 = r5.length     // Catch:{ IOException -> 0x0186, all -> 0x0183 }
            if (r11 < r5) goto L_0x011c
            r11 = 0
        L_0x0106:
            byte[] r5 = p000.abz.f137c     // Catch:{ IOException -> 0x0186, all -> 0x0183 }
            int r5 = r5.length     // Catch:{ IOException -> 0x0186, all -> 0x0183 }
            if (r11 >= r5) goto L_0x0118
            byte[] r5 = r10.f116b     // Catch:{ IOException -> 0x0186, all -> 0x0183 }
            byte r5 = r5[r11]     // Catch:{ IOException -> 0x0186, all -> 0x0183 }
            byte[] r6 = p000.abz.f137c     // Catch:{ IOException -> 0x0186, all -> 0x0183 }
            byte r6 = r6[r11]     // Catch:{ IOException -> 0x0186, all -> 0x0183 }
            if (r5 != r6) goto L_0x011e
            int r11 = r11 + 1
            goto L_0x0106
        L_0x0118:
            byte[] r11 = p000.abz.f137c     // Catch:{ IOException -> 0x0186, all -> 0x0183 }
            int r4 = r11.length     // Catch:{ IOException -> 0x0186, all -> 0x0183 }
            goto L_0x011e
        L_0x011c:
        L_0x011e:
            java.lang.StringBuilder r11 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0186, all -> 0x0183 }
            r11.<init>()     // Catch:{ IOException -> 0x0186, all -> 0x0183 }
        L_0x0123:
            int r5 = r10.f117c     // Catch:{ IOException -> 0x0186, all -> 0x0183 }
            if (r4 >= r5) goto L_0x013f
            byte[] r5 = r10.f116b     // Catch:{ IOException -> 0x0186, all -> 0x0183 }
            byte r5 = r5[r4]     // Catch:{ IOException -> 0x0186, all -> 0x0183 }
            if (r5 != 0) goto L_0x012e
            goto L_0x013f
        L_0x012e:
            r6 = 32
            if (r5 < r6) goto L_0x0137
            char r5 = (char) r5     // Catch:{ IOException -> 0x0186, all -> 0x0183 }
            r11.append(r5)     // Catch:{ IOException -> 0x0186, all -> 0x0183 }
            goto L_0x013c
        L_0x0137:
            r5 = 63
            r11.append(r5)     // Catch:{ IOException -> 0x0186, all -> 0x0183 }
        L_0x013c:
            int r4 = r4 + 1
            goto L_0x0123
        L_0x013f:
            java.lang.String r11 = r11.toString()     // Catch:{ IOException -> 0x0186, all -> 0x0183 }
            r3.close()     // Catch:{ IOException -> 0x0147 }
            goto L_0x014b
        L_0x0147:
            r2 = move-exception
            android.util.Log.e(r1, r0, r2)
        L_0x014b:
            return r11
        L_0x014c:
            byte[] r11 = r10.f116b     // Catch:{ IOException -> 0x0186, all -> 0x0183 }
            int r5 = r11.length     // Catch:{ IOException -> 0x0186, all -> 0x0183 }
            r6 = 1
            if (r5 != r6) goto L_0x016e
            byte r5 = r11[r4]     // Catch:{ IOException -> 0x0186, all -> 0x0183 }
            if (r5 >= 0) goto L_0x0157
            goto L_0x016e
        L_0x0157:
            if (r5 > r6) goto L_0x016e
            java.lang.String r11 = new java.lang.String     // Catch:{ IOException -> 0x0186, all -> 0x0183 }
            char[] r6 = new char[r6]     // Catch:{ IOException -> 0x0186, all -> 0x0183 }
            int r5 = r5 + 48
            char r5 = (char) r5     // Catch:{ IOException -> 0x0186, all -> 0x0183 }
            r6[r4] = r5     // Catch:{ IOException -> 0x0186, all -> 0x0183 }
            r11.<init>(r6)     // Catch:{ IOException -> 0x0186, all -> 0x0183 }
            r3.close()     // Catch:{ IOException -> 0x0169 }
            goto L_0x016d
        L_0x0169:
            r2 = move-exception
            android.util.Log.e(r1, r0, r2)
        L_0x016d:
            return r11
        L_0x016e:
            java.lang.String r4 = new java.lang.String     // Catch:{ IOException -> 0x0186, all -> 0x0183 }
            java.nio.charset.Charset r5 = p000.abz.f138d     // Catch:{ IOException -> 0x0186, all -> 0x0183 }
            r4.<init>(r11, r5)     // Catch:{ IOException -> 0x0186, all -> 0x0183 }
            r3.close()     // Catch:{ IOException -> 0x0179 }
            goto L_0x017d
        L_0x0179:
            r11 = move-exception
            android.util.Log.e(r1, r0, r11)
        L_0x017d:
            return r4
        L_0x017e:
            r11 = move-exception
            android.util.Log.e(r1, r0, r11)
        L_0x0182:
            return r2
        L_0x0183:
            r11 = move-exception
            r2 = r3
            goto L_0x01a0
        L_0x0186:
            r11 = move-exception
            goto L_0x018d
        L_0x0189:
            r11 = move-exception
            goto L_0x01a0
        L_0x018b:
            r11 = move-exception
            r3 = r2
        L_0x018d:
            java.lang.String r4 = "IOException occurred during reading a value"
            android.util.Log.w(r1, r4, r11)     // Catch:{ all -> 0x019d }
            if (r3 == 0) goto L_0x019c
            r3.close()     // Catch:{ IOException -> 0x0198 }
            goto L_0x019c
        L_0x0198:
            r11 = move-exception
            android.util.Log.e(r1, r0, r11)
        L_0x019c:
            return r2
        L_0x019d:
            r11 = move-exception
            r2 = r3
        L_0x01a0:
            if (r2 == 0) goto L_0x01aa
            r2.close()     // Catch:{ IOException -> 0x01a6 }
            goto L_0x01aa
        L_0x01a6:
            r2 = move-exception
            android.util.Log.e(r1, r0, r2)
        L_0x01aa:
            goto L_0x01ac
        L_0x01ab:
            throw r11
        L_0x01ac:
            goto L_0x01ab
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.abw.mo147a(java.nio.ByteOrder):java.lang.Object");
    }

    public final String toString() {
        return "(" + abz.f135a[this.f115a] + ", data length:" + this.f116b.length + ")";
    }
}
