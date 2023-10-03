package com.google.zxing.qrcode.encoder;

final class MaskUtil {
    static int applyMaskPenaltyRule1(ByteMatrix byteMatrix) {
        return applyMaskPenaltyRule1Internal(byteMatrix, true) + applyMaskPenaltyRule1Internal(byteMatrix, false);
    }

    static int applyMaskPenaltyRule2(ByteMatrix byteMatrix) {
        byte[][] array = byteMatrix.getArray();
        int width = byteMatrix.getWidth();
        int height = byteMatrix.getHeight();
        int i = 0;
        int i2 = 0;
        while (i < height - 1) {
            int i3 = i2;
            int i4 = 0;
            while (i4 < width - 1) {
                byte b = array[i][i4];
                int i5 = i4 + 1;
                if (b == array[i][i5]) {
                    int i6 = i + 1;
                    if (b == array[i6][i4] && b == array[i6][i5]) {
                        i3++;
                    }
                }
                i4 = i5;
            }
            i++;
            i2 = i3;
        }
        return i2 * 3;
    }

    static int applyMaskPenaltyRule3(ByteMatrix byteMatrix) {
        int i;
        int i2;
        int i3;
        int i4;
        byte[][] array = byteMatrix.getArray();
        int width = byteMatrix.getWidth();
        int height = byteMatrix.getHeight();
        int i5 = 0;
        int i6 = 0;
        while (i5 < height) {
            int i7 = i6;
            for (int i8 = 0; i8 < width; i8++) {
                int i9 = i8 + 6;
                if (i9 < width && array[i5][i8] == 1 && array[i5][i8 + 1] == 0 && array[i5][i8 + 2] == 1 && array[i5][i8 + 3] == 1 && array[i5][i8 + 4] == 1 && array[i5][i8 + 5] == 0 && array[i5][i9] == 1 && (((i3 = i8 + 10) < width && array[i5][i8 + 7] == 0 && array[i5][i8 + 8] == 0 && array[i5][i8 + 9] == 0 && array[i5][i3] == 0) || (i8 - 4 >= 0 && array[i5][i8 - 1] == 0 && array[i5][i8 - 2] == 0 && array[i5][i8 - 3] == 0 && array[i5][i4] == 0))) {
                    i7 += 40;
                }
                int i10 = i5 + 6;
                if (i10 < height && array[i5][i8] == 1 && array[i5 + 1][i8] == 0 && array[i5 + 2][i8] == 1 && array[i5 + 3][i8] == 1 && array[i5 + 4][i8] == 1 && array[i5 + 5][i8] == 0 && array[i10][i8] == 1 && (((i = i5 + 10) < height && array[i5 + 7][i8] == 0 && array[i5 + 8][i8] == 0 && array[i5 + 9][i8] == 0 && array[i][i8] == 0) || (i5 - 4 >= 0 && array[i5 - 1][i8] == 0 && array[i5 - 2][i8] == 0 && array[i5 - 3][i8] == 0 && array[i2][i8] == 0))) {
                    i7 += 40;
                }
            }
            i5++;
            i6 = i7;
        }
        return i6;
    }

    static int applyMaskPenaltyRule4(ByteMatrix byteMatrix) {
        byte[][] array = byteMatrix.getArray();
        int width = byteMatrix.getWidth();
        int height = byteMatrix.getHeight();
        int i = 0;
        int i2 = 0;
        while (i < height) {
            byte[] bArr = array[i];
            int i3 = i2;
            for (int i4 = 0; i4 < width; i4++) {
                if (bArr[i4] == 1) {
                    i3++;
                }
            }
            i++;
            i2 = i3;
        }
        return ((int) (Math.abs((((double) i2) / ((double) (byteMatrix.getHeight() * byteMatrix.getWidth()))) - 0.5d) * 20.0d)) * 10;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0040, code lost:
        r1 = r3 & 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0042, code lost:
        if (r1 != 0) goto L_0x0045;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0045, code lost:
        return false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:?, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0035, code lost:
        r1 = r1 + r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0036, code lost:
        r1 = r1 & 1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static boolean getDataMaskBit(int r1, int r2, int r3) {
        /*
            r0 = 1
            switch(r1) {
                case 0: goto L_0x003f;
                case 1: goto L_0x0040;
                case 2: goto L_0x003c;
                case 3: goto L_0x0038;
                case 4: goto L_0x0031;
                case 5: goto L_0x002a;
                case 6: goto L_0x0023;
                case 7: goto L_0x001b;
                default: goto L_0x0004;
            }
        L_0x0004:
            java.lang.IllegalArgumentException r2 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r0 = "Invalid mask pattern: "
            r3.append(r0)
            r3.append(r1)
            java.lang.String r1 = r3.toString()
            r2.<init>(r1)
            throw r2
        L_0x001b:
            int r1 = r3 * r2
            int r1 = r1 % 3
            int r3 = r3 + r2
            r2 = r3 & 1
            goto L_0x0035
        L_0x0023:
            int r3 = r3 * r2
            r1 = r3 & 1
            int r3 = r3 % 3
            int r1 = r1 + r3
            goto L_0x0036
        L_0x002a:
            int r3 = r3 * r2
            r1 = r3 & 1
            int r3 = r3 % 3
            int r1 = r1 + r3
            goto L_0x0042
        L_0x0031:
            int r1 = r3 >>> 1
            int r2 = r2 / 3
        L_0x0035:
            int r1 = r1 + r2
        L_0x0036:
            r1 = r1 & r0
            goto L_0x0042
        L_0x0038:
            int r3 = r3 + r2
            int r1 = r3 % 3
            goto L_0x0042
        L_0x003c:
            int r1 = r2 % 3
            goto L_0x0042
        L_0x003f:
            int r3 = r3 + r2
        L_0x0040:
            r1 = r3 & 1
        L_0x0042:
            if (r1 != 0) goto L_0x0045
            goto L_0x0046
        L_0x0045:
            r0 = 0
        L_0x0046:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.qrcode.encoder.MaskUtil.getDataMaskBit(int, int, int):boolean");
    }

    private static int applyMaskPenaltyRule1Internal(ByteMatrix byteMatrix, boolean z) {
        int height = z ? byteMatrix.getHeight() : byteMatrix.getWidth();
        int width = z ? byteMatrix.getWidth() : byteMatrix.getHeight();
        byte[][] array = byteMatrix.getArray();
        int i = 0;
        for (int i2 = 0; i2 < height; i2++) {
            int i3 = i;
            byte b = -1;
            int i4 = 0;
            for (int i5 = 0; i5 < width; i5++) {
                byte b2 = z ? array[i2][i5] : array[i5][i2];
                if (b2 == b) {
                    i4++;
                } else {
                    if (i4 >= 5) {
                        i3 += (i4 - 5) + 3;
                    }
                    i4 = 1;
                    b = b2;
                }
            }
            if (i4 >= 5) {
                i3 += (i4 - 5) + 3;
            }
            i = i3;
        }
        return i;
    }
}
