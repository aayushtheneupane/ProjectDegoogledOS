package p000;

/* renamed from: dhe */
/* compiled from: PG */
public final class dhe {

    /* renamed from: a */
    private static final char[] f6536a = "0123456789ABCDEF".toCharArray();

    /* renamed from: a */
    public static String m6107a(byte[] bArr) {
        if (bArr == null) {
            return "";
        }
        int length = bArr.length;
        char[] cArr = new char[(length + length)];
        for (int i = 0; i < bArr.length; i++) {
            byte b = bArr[i] & 255;
            int i2 = i + i;
            char[] cArr2 = f6536a;
            cArr[i2] = cArr2[b >>> 4];
            cArr[i2 + 1] = cArr2[b & 15];
        }
        return new String(cArr);
    }
}
