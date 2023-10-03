package com.google.zxing.qrcode.encoder;

import com.google.zxing.WriterException;

final class MatrixUtil {
    private static final int[][] POSITION_ADJUSTMENT_PATTERN = {new int[]{1, 1, 1, 1, 1}, new int[]{1, 0, 0, 0, 1}, new int[]{1, 0, 1, 0, 1}, new int[]{1, 0, 0, 0, 1}, new int[]{1, 1, 1, 1, 1}};
    private static final int[][] POSITION_ADJUSTMENT_PATTERN_COORDINATE_TABLE = {new int[]{-1, -1, -1, -1, -1, -1, -1}, new int[]{6, 18, -1, -1, -1, -1, -1}, new int[]{6, 22, -1, -1, -1, -1, -1}, new int[]{6, 26, -1, -1, -1, -1, -1}, new int[]{6, 30, -1, -1, -1, -1, -1}, new int[]{6, 34, -1, -1, -1, -1, -1}, new int[]{6, 22, 38, -1, -1, -1, -1}, new int[]{6, 24, 42, -1, -1, -1, -1}, new int[]{6, 26, 46, -1, -1, -1, -1}, new int[]{6, 28, 50, -1, -1, -1, -1}, new int[]{6, 30, 54, -1, -1, -1, -1}, new int[]{6, 32, 58, -1, -1, -1, -1}, new int[]{6, 34, 62, -1, -1, -1, -1}, new int[]{6, 26, 46, 66, -1, -1, -1}, new int[]{6, 26, 48, 70, -1, -1, -1}, new int[]{6, 26, 50, 74, -1, -1, -1}, new int[]{6, 30, 54, 78, -1, -1, -1}, new int[]{6, 30, 56, 82, -1, -1, -1}, new int[]{6, 30, 58, 86, -1, -1, -1}, new int[]{6, 34, 62, 90, -1, -1, -1}, new int[]{6, 28, 50, 72, 94, -1, -1}, new int[]{6, 26, 50, 74, 98, -1, -1}, new int[]{6, 30, 54, 78, 102, -1, -1}, new int[]{6, 28, 54, 80, 106, -1, -1}, new int[]{6, 32, 58, 84, 110, -1, -1}, new int[]{6, 30, 58, 86, 114, -1, -1}, new int[]{6, 34, 62, 90, 118, -1, -1}, new int[]{6, 26, 50, 74, 98, 122, -1}, new int[]{6, 30, 54, 78, 102, 126, -1}, new int[]{6, 26, 52, 78, 104, 130, -1}, new int[]{6, 30, 56, 82, 108, 134, -1}, new int[]{6, 34, 60, 86, 112, 138, -1}, new int[]{6, 30, 58, 86, 114, 142, -1}, new int[]{6, 34, 62, 90, 118, 146, -1}, new int[]{6, 30, 54, 78, 102, 126, 150}, new int[]{6, 24, 50, 76, 102, 128, 154}, new int[]{6, 28, 54, 80, 106, 132, 158}, new int[]{6, 32, 58, 84, 110, 136, 162}, new int[]{6, 26, 54, 82, 110, 138, 166}, new int[]{6, 30, 58, 86, 114, 142, 170}};
    private static final int[][] POSITION_DETECTION_PATTERN = {new int[]{1, 1, 1, 1, 1, 1, 1}, new int[]{1, 0, 0, 0, 0, 0, 1}, new int[]{1, 0, 1, 1, 1, 0, 1}, new int[]{1, 0, 1, 1, 1, 0, 1}, new int[]{1, 0, 1, 1, 1, 0, 1}, new int[]{1, 0, 0, 0, 0, 0, 1}, new int[]{1, 1, 1, 1, 1, 1, 1}};
    private static final int[][] TYPE_INFO_COORDINATES = {new int[]{8, 0}, new int[]{8, 1}, new int[]{8, 2}, new int[]{8, 3}, new int[]{8, 4}, new int[]{8, 5}, new int[]{8, 7}, new int[]{8, 8}, new int[]{7, 8}, new int[]{5, 8}, new int[]{4, 8}, new int[]{3, 8}, new int[]{2, 8}, new int[]{1, 8}, new int[]{0, 8}};

    /* JADX WARNING: Code restructure failed: missing block: B:79:0x020f, code lost:
        r14 = r14 + r15;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:86:0x022d, code lost:
        r14 = r14 & 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:87:0x022f, code lost:
        if (r14 != 0) goto L_0x0233;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:88:0x0231, code lost:
        r14 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:89:0x0233, code lost:
        r14 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:90:0x0234, code lost:
        if (r14 == false) goto L_0x023b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:91:0x0236, code lost:
        if (r13 != false) goto L_0x023a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:92:0x0238, code lost:
        r13 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:93:0x023a, code lost:
        r13 = false;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static void buildMatrix(com.google.zxing.common.BitArray r16, com.google.zxing.qrcode.decoder.ErrorCorrectionLevel r17, com.google.zxing.qrcode.decoder.Version r18, int r19, com.google.zxing.qrcode.encoder.ByteMatrix r20) throws com.google.zxing.WriterException {
        /*
            r0 = r19
            r1 = r20
            r2 = -1
            r1.clear(r2)
            int[][] r3 = POSITION_DETECTION_PATTERN
            r4 = 0
            r3 = r3[r4]
            int r3 = r3.length
            embedPositionDetectionPattern(r4, r4, r1)
            int r5 = r20.getWidth()
            int r5 = r5 - r3
            embedPositionDetectionPattern(r5, r4, r1)
            int r5 = r20.getWidth()
            int r5 = r5 - r3
            embedPositionDetectionPattern(r4, r5, r1)
            r3 = 7
            embedHorizontalSeparationPattern(r4, r3, r1)
            int r5 = r20.getWidth()
            int r5 = r5 + -8
            embedHorizontalSeparationPattern(r5, r3, r1)
            int r5 = r20.getWidth()
            int r5 = r5 + -8
            embedHorizontalSeparationPattern(r4, r5, r1)
            embedVerticalSeparationPattern(r3, r4, r1)
            int r5 = r20.getHeight()
            int r5 = r5 - r3
            int r5 = r5 + r2
            embedVerticalSeparationPattern(r5, r4, r1)
            int r5 = r20.getHeight()
            int r5 = r5 - r3
            embedVerticalSeparationPattern(r3, r5, r1)
            int r3 = r20.getHeight()
            r5 = 8
            int r3 = r3 - r5
            byte r3 = r1.get(r5, r3)
            if (r3 == 0) goto L_0x02ad
            int r3 = r20.getHeight()
            int r3 = r3 - r5
            r6 = 1
            r1.set((int) r5, (int) r3, (int) r6)
            int r3 = r18.getVersionNumber()
            r7 = 5
            r8 = 2
            if (r3 >= r8) goto L_0x006a
            goto L_0x00b7
        L_0x006a:
            int r3 = r18.getVersionNumber()
            int r3 = r3 + r2
            int[][] r8 = POSITION_ADJUSTMENT_PATTERN_COORDINATE_TABLE
            r9 = r8[r3]
            r3 = r8[r3]
            int r3 = r3.length
            r8 = r2
            r2 = r4
        L_0x0078:
            if (r4 >= r3) goto L_0x00b7
            r10 = r2
        L_0x007b:
            if (r2 >= r3) goto L_0x00b2
            r11 = r9[r4]
            r12 = r9[r2]
            if (r12 == r8) goto L_0x00ad
            if (r11 != r8) goto L_0x0086
            goto L_0x00ad
        L_0x0086:
            byte r8 = r1.get(r12, r11)
            boolean r8 = isEmpty(r8)
            if (r8 == 0) goto L_0x00ad
            int r12 = r12 + -2
            int r11 = r11 + -2
            r8 = r10
        L_0x0095:
            if (r10 >= r7) goto L_0x00ad
        L_0x0097:
            if (r8 >= r7) goto L_0x00a9
            int r13 = r12 + r8
            int r14 = r11 + r10
            int[][] r15 = POSITION_ADJUSTMENT_PATTERN
            r15 = r15[r10]
            r15 = r15[r8]
            r1.set((int) r13, (int) r14, (int) r15)
            int r8 = r8 + 1
            goto L_0x0097
        L_0x00a9:
            int r10 = r10 + 1
            r8 = 0
            goto L_0x0095
        L_0x00ad:
            int r2 = r2 + 1
            r8 = -1
            r10 = 0
            goto L_0x007b
        L_0x00b2:
            int r4 = r4 + 1
            r8 = -1
            r2 = 0
            goto L_0x0078
        L_0x00b7:
            r2 = r5
        L_0x00b8:
            int r3 = r20.getWidth()
            int r3 = r3 - r5
            r4 = 6
            if (r2 >= r3) goto L_0x00e0
            int r3 = r2 + 1
            int r8 = r3 % 2
            byte r9 = r1.get(r2, r4)
            boolean r9 = isEmpty(r9)
            if (r9 == 0) goto L_0x00d1
            r1.set((int) r2, (int) r4, (int) r8)
        L_0x00d1:
            byte r9 = r1.get(r4, r2)
            boolean r9 = isEmpty(r9)
            if (r9 == 0) goto L_0x00de
            r1.set((int) r4, (int) r2, (int) r8)
        L_0x00de:
            r2 = r3
            goto L_0x00b8
        L_0x00e0:
            com.google.zxing.common.BitArray r2 = new com.google.zxing.common.BitArray
            r2.<init>()
            if (r0 < 0) goto L_0x00eb
            if (r0 >= r5) goto L_0x00eb
            r3 = r6
            goto L_0x00ec
        L_0x00eb:
            r3 = 0
        L_0x00ec:
            if (r3 == 0) goto L_0x02a5
            int r3 = r17.getBits()
            r8 = 3
            int r3 = r3 << r8
            r3 = r3 | r0
            r2.appendBits(r3, r7)
            r7 = 1335(0x537, float:1.871E-42)
            int r3 = calculateBCHCode(r3, r7)
            r7 = 10
            r2.appendBits(r3, r7)
            com.google.zxing.common.BitArray r3 = new com.google.zxing.common.BitArray
            r3.<init>()
            r7 = 21522(0x5412, float:3.0159E-41)
            r9 = 15
            r3.appendBits(r7, r9)
            r2.xor(r3)
            int r3 = r2.getSize()
            java.lang.String r7 = "should not happen but we got: "
            if (r3 != r9) goto L_0x0290
            r3 = 0
        L_0x011b:
            int r9 = r2.getSize()
            if (r3 >= r9) goto L_0x0154
            int r9 = r2.getSize()
            int r9 = r9 - r6
            int r9 = r9 - r3
            boolean r9 = r2.get(r9)
            int[][] r10 = TYPE_INFO_COORDINATES
            r11 = r10[r3]
            r12 = 0
            r11 = r11[r12]
            r10 = r10[r3]
            r10 = r10[r6]
            r1.set((int) r11, (int) r10, (boolean) r9)
            if (r3 >= r5) goto L_0x0145
            int r10 = r20.getWidth()
            int r10 = r10 - r3
            int r10 = r10 - r6
            r1.set((int) r10, (int) r5, (boolean) r9)
            goto L_0x0151
        L_0x0145:
            int r10 = r20.getHeight()
            int r10 = r10 + -7
            int r11 = r3 + -8
            int r11 = r11 + r10
            r1.set((int) r5, (int) r11, (boolean) r9)
        L_0x0151:
            int r3 = r3 + 1
            goto L_0x011b
        L_0x0154:
            r2 = 0
            int r3 = r18.getVersionNumber()
            r5 = 7
            if (r3 >= r5) goto L_0x015d
            goto L_0x01ab
        L_0x015d:
            com.google.zxing.common.BitArray r3 = new com.google.zxing.common.BitArray
            r3.<init>()
            int r5 = r18.getVersionNumber()
            r3.appendBits(r5, r4)
            int r5 = r18.getVersionNumber()
            r9 = 7973(0x1f25, float:1.1173E-41)
            int r5 = calculateBCHCode(r5, r9)
            r9 = 12
            r3.appendBits(r5, r9)
            int r5 = r3.getSize()
            r9 = 18
            if (r5 != r9) goto L_0x027b
            r5 = 17
            r7 = r5
            r5 = r2
        L_0x0184:
            if (r5 >= r4) goto L_0x01ab
            r9 = r7
            r7 = r2
        L_0x0188:
            if (r7 >= r8) goto L_0x01a7
            boolean r10 = r3.get(r9)
            int r9 = r9 + -1
            int r11 = r20.getHeight()
            int r11 = r11 + -11
            int r11 = r11 + r7
            r1.set((int) r5, (int) r11, (boolean) r10)
            int r11 = r20.getHeight()
            int r11 = r11 + -11
            int r11 = r11 + r7
            r1.set((int) r11, (int) r5, (boolean) r10)
            int r7 = r7 + 1
            goto L_0x0188
        L_0x01a7:
            int r5 = r5 + 1
            r7 = r9
            goto L_0x0184
        L_0x01ab:
            int r3 = r20.getWidth()
            int r3 = r3 - r6
            int r5 = r20.getHeight()
            int r5 = r5 - r6
            r7 = -1
            r8 = r7
            r7 = r5
            r5 = r2
        L_0x01b9:
            if (r3 <= 0) goto L_0x024f
            if (r3 != r4) goto L_0x01bf
            int r3 = r3 + -1
        L_0x01bf:
            if (r7 < 0) goto L_0x0247
            int r9 = r20.getHeight()
            if (r7 >= r9) goto L_0x0247
            r9 = 2
            r10 = r5
            r5 = r2
        L_0x01ca:
            if (r5 >= r9) goto L_0x0241
            int r11 = r3 - r5
            byte r12 = r1.get(r11, r7)
            boolean r12 = isEmpty(r12)
            if (r12 != 0) goto L_0x01dc
            r12 = r16
            goto L_0x023e
        L_0x01dc:
            int r12 = r16.getSize()
            if (r10 >= r12) goto L_0x01eb
            r12 = r16
            boolean r13 = r12.get(r10)
            int r10 = r10 + 1
            goto L_0x01ee
        L_0x01eb:
            r12 = r16
            r13 = r2
        L_0x01ee:
            r14 = -1
            if (r0 == r14) goto L_0x023b
            switch(r0) {
                case 0: goto L_0x022b;
                case 1: goto L_0x0229;
                case 2: goto L_0x0226;
                case 3: goto L_0x0221;
                case 4: goto L_0x0219;
                case 5: goto L_0x0211;
                case 6: goto L_0x0209;
                case 7: goto L_0x0200;
                default: goto L_0x01f4;
            }
        L_0x01f4:
            java.lang.IllegalArgumentException r1 = new java.lang.IllegalArgumentException
            java.lang.String r2 = "Invalid mask pattern: "
            java.lang.String r0 = com.android.tools.p006r8.GeneratedOutlineSupport.outline5(r2, r0)
            r1.<init>(r0)
            throw r1
        L_0x0200:
            int r14 = r7 * r11
            int r14 = r14 % 3
            int r15 = r7 + r11
            r15 = r15 & 1
            goto L_0x020f
        L_0x0209:
            int r14 = r7 * r11
            r15 = r14 & 1
            int r14 = r14 % 3
        L_0x020f:
            int r14 = r14 + r15
            goto L_0x022d
        L_0x0211:
            int r14 = r7 * r11
            r15 = r14 & 1
            int r14 = r14 % 3
            int r14 = r14 + r15
            goto L_0x022f
        L_0x0219:
            int r14 = r7 >>> 1
            int r15 = r11 / 3
            int r15 = r15 + r14
            r14 = r15 & 1
            goto L_0x022f
        L_0x0221:
            int r14 = r7 + r11
            int r14 = r14 % 3
            goto L_0x022f
        L_0x0226:
            int r14 = r11 % 3
            goto L_0x022f
        L_0x0229:
            r14 = r7
            goto L_0x022d
        L_0x022b:
            int r14 = r7 + r11
        L_0x022d:
            r14 = r14 & 1
        L_0x022f:
            if (r14 != 0) goto L_0x0233
            r14 = r6
            goto L_0x0234
        L_0x0233:
            r14 = r2
        L_0x0234:
            if (r14 == 0) goto L_0x023b
            if (r13 != 0) goto L_0x023a
            r13 = r6
            goto L_0x023b
        L_0x023a:
            r13 = r2
        L_0x023b:
            r1.set((int) r11, (int) r7, (boolean) r13)
        L_0x023e:
            int r5 = r5 + 1
            goto L_0x01ca
        L_0x0241:
            r12 = r16
            int r7 = r7 + r8
            r5 = r10
            goto L_0x01bf
        L_0x0247:
            r12 = r16
            int r8 = -r8
            int r7 = r7 + r8
            int r3 = r3 + -2
            goto L_0x01b9
        L_0x024f:
            r12 = r16
            int r0 = r16.getSize()
            if (r5 != r0) goto L_0x0258
            return
        L_0x0258:
            com.google.zxing.WriterException r0 = new com.google.zxing.WriterException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "Not all bits consumed: "
            r1.append(r2)
            r1.append(r5)
            r2 = 47
            r1.append(r2)
            int r2 = r16.getSize()
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            r0.<init>((java.lang.String) r1)
            throw r0
        L_0x027b:
            com.google.zxing.WriterException r0 = new com.google.zxing.WriterException
            java.lang.StringBuilder r1 = com.android.tools.p006r8.GeneratedOutlineSupport.outline13(r7)
            int r2 = r3.getSize()
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            r0.<init>((java.lang.String) r1)
            throw r0
        L_0x0290:
            com.google.zxing.WriterException r0 = new com.google.zxing.WriterException
            java.lang.StringBuilder r1 = com.android.tools.p006r8.GeneratedOutlineSupport.outline13(r7)
            int r2 = r2.getSize()
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            r0.<init>((java.lang.String) r1)
            throw r0
        L_0x02a5:
            com.google.zxing.WriterException r0 = new com.google.zxing.WriterException
            java.lang.String r1 = "Invalid mask pattern"
            r0.<init>((java.lang.String) r1)
            throw r0
        L_0x02ad:
            com.google.zxing.WriterException r0 = new com.google.zxing.WriterException
            r0.<init>()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.qrcode.encoder.MatrixUtil.buildMatrix(com.google.zxing.common.BitArray, com.google.zxing.qrcode.decoder.ErrorCorrectionLevel, com.google.zxing.qrcode.decoder.Version, int, com.google.zxing.qrcode.encoder.ByteMatrix):void");
    }

    static int calculateBCHCode(int i, int i2) {
        int findMSBSet = findMSBSet(i2);
        int i3 = i << (findMSBSet - 1);
        while (findMSBSet(i3) >= findMSBSet) {
            i3 ^= i2 << (findMSBSet(i3) - findMSBSet);
        }
        return i3;
    }

    private static void embedHorizontalSeparationPattern(int i, int i2, ByteMatrix byteMatrix) throws WriterException {
        int i3 = 0;
        while (i3 < 8) {
            int i4 = i + i3;
            if (isEmpty(byteMatrix.get(i4, i2))) {
                byteMatrix.set(i4, i2, 0);
                i3++;
            } else {
                throw new WriterException();
            }
        }
    }

    private static void embedPositionDetectionPattern(int i, int i2, ByteMatrix byteMatrix) {
        for (int i3 = 0; i3 < 7; i3++) {
            for (int i4 = 0; i4 < 7; i4++) {
                byteMatrix.set(i + i4, i2 + i3, POSITION_DETECTION_PATTERN[i3][i4]);
            }
        }
    }

    private static void embedVerticalSeparationPattern(int i, int i2, ByteMatrix byteMatrix) throws WriterException {
        int i3 = 0;
        while (i3 < 7) {
            int i4 = i2 + i3;
            if (isEmpty(byteMatrix.get(i, i4))) {
                byteMatrix.set(i, i4, 0);
                i3++;
            } else {
                throw new WriterException();
            }
        }
    }

    static int findMSBSet(int i) {
        int i2 = 0;
        while (i != 0) {
            i >>>= 1;
            i2++;
        }
        return i2;
    }

    private static boolean isEmpty(int i) {
        return i == -1;
    }
}
