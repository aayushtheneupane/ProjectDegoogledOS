package com.android.messaging.mmslib.p039a;

/* renamed from: com.android.messaging.mmslib.a.b */
public class C0972b {

    /* renamed from: Tl */
    private static byte[] f1398Tl = new byte[255];

    static {
        for (int i = 0; i < 255; i++) {
            f1398Tl[i] = -1;
        }
        for (int i2 = 90; i2 >= 65; i2--) {
            f1398Tl[i2] = (byte) (i2 - 65);
        }
        for (int i3 = 122; i3 >= 97; i3--) {
            f1398Tl[i3] = (byte) ((i3 - 97) + 26);
        }
        for (int i4 = 57; i4 >= 48; i4--) {
            f1398Tl[i4] = (byte) ((i4 - 48) + 52);
        }
        byte[] bArr = f1398Tl;
        bArr[43] = 62;
        bArr[47] = 63;
    }

    /* renamed from: a */
    public static byte[] m2207a(byte[] bArr) {
        byte[] bArr2 = new byte[bArr.length];
        int i = 0;
        int i2 = 0;
        while (true) {
            boolean z = true;
            if (i >= bArr.length) {
                break;
            }
            byte b = bArr[i];
            if (b != 61 && f1398Tl[b] == -1) {
                z = false;
            }
            if (z) {
                bArr2[i2] = bArr[i];
                i2++;
            }
            i++;
        }
        byte[] bArr3 = new byte[i2];
        System.arraycopy(bArr2, 0, bArr3, 0, i2);
        if (bArr3.length == 0) {
            return new byte[0];
        }
        int length = bArr3.length / 4;
        int length2 = bArr3.length;
        while (bArr3[length2 - 1] == 61) {
            length2--;
            if (length2 == 0) {
                return new byte[0];
            }
        }
        byte[] bArr4 = new byte[(length2 - length)];
        int i3 = 0;
        for (int i4 = 0; i4 < length; i4++) {
            int i5 = i4 * 4;
            byte b2 = bArr3[i5 + 2];
            byte b3 = bArr3[i5 + 3];
            byte[] bArr5 = f1398Tl;
            byte b4 = bArr5[bArr3[i5]];
            byte b5 = bArr5[bArr3[i5 + 1]];
            if (b2 != 61 && b3 != 61) {
                byte b6 = bArr5[b2];
                byte b7 = bArr5[b3];
                bArr4[i3] = (byte) ((b4 << 2) | (b5 >> 4));
                bArr4[i3 + 1] = (byte) (((b5 & 15) << 4) | ((b6 >> 2) & 15));
                bArr4[i3 + 2] = (byte) ((b6 << 6) | b7);
            } else if (b2 == 61) {
                bArr4[i3] = (byte) ((b5 >> 4) | (b4 << 2));
            } else if (b3 == 61) {
                byte b8 = f1398Tl[b2];
                bArr4[i3] = (byte) ((b4 << 2) | (b5 >> 4));
                bArr4[i3 + 1] = (byte) (((b5 & 15) << 4) | ((b8 >> 2) & 15));
            }
            i3 += 3;
        }
        return bArr4;
    }
}
