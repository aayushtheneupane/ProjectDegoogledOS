package p000;

/* renamed from: ann */
/* compiled from: PG */
public final class ann {

    /* renamed from: a */
    private static byte[] f1197a = {65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 43, 47};

    /* renamed from: b */
    private static byte[] f1198b = new byte[255];

    static {
        int i = 0;
        for (int i2 = 0; i2 < 255; i2++) {
            f1198b[i2] = -1;
        }
        while (true) {
            byte[] bArr = f1197a;
            if (i < bArr.length) {
                f1198b[bArr[i]] = (byte) i;
                i++;
            } else {
                byte[] bArr2 = f1198b;
                bArr2[9] = -2;
                bArr2[10] = -2;
                bArr2[13] = -2;
                bArr2[32] = -2;
                bArr2[61] = -3;
                return;
            }
        }
    }

    /* renamed from: a */
    public static final byte[] m1179a(byte[] bArr) {
        int length;
        byte[] bArr2 = new byte[(((bArr.length + 2) / 3) << 2)];
        int i = 0;
        int i2 = 0;
        while (true) {
            length = bArr.length;
            if (i + 3 > length) {
                break;
            }
            int i3 = i + 1;
            int i4 = i3 + 1;
            int i5 = i4 + 1;
            byte b = ((bArr[i] & 255) << 16) | ((bArr[i3] & 255) << 8) | (bArr[i4] & 255);
            int i6 = i2 + 1;
            byte[] bArr3 = f1197a;
            bArr2[i2] = bArr3[b >> 18];
            int i7 = i6 + 1;
            bArr2[i6] = bArr3[(b >> 12) & 63];
            int i8 = i7 + 1;
            bArr2[i7] = bArr3[(b & 4032) >> 6];
            i2 = i8 + 1;
            bArr2[i8] = bArr3[b & 63];
            i = i5;
        }
        int i9 = length - i;
        if (i9 == 2) {
            int i10 = ((bArr[i + 1] & 255) << 8) | ((bArr[i] & 255) << 16);
            int i11 = i2 + 1;
            byte[] bArr4 = f1197a;
            bArr2[i2] = bArr4[i10 >> 18];
            int i12 = i11 + 1;
            bArr2[i11] = bArr4[(i10 >> 12) & 63];
            bArr2[i12] = bArr4[(i10 & 4032) >> 6];
            bArr2[i12 + 1] = 61;
        } else if (i9 == 1) {
            int i13 = (bArr[i] & 255) << 16;
            int i14 = i2 + 1;
            byte[] bArr5 = f1197a;
            bArr2[i2] = bArr5[i13 >> 18];
            int i15 = i14 + 1;
            bArr2[i14] = bArr5[(i13 >> 12) & 63];
            bArr2[i15] = 61;
            bArr2[i15 + 1] = 61;
        }
        return bArr2;
    }
}
