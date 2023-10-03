package com.google.zxing.oned;

import com.google.zxing.NotFoundException;
import com.google.zxing.common.BitArray;

public final class Code128Reader extends OneDReader {
    static final int[][] CODE_PATTERNS = {new int[]{2, 1, 2, 2, 2, 2}, new int[]{2, 2, 2, 1, 2, 2}, new int[]{2, 2, 2, 2, 2, 1}, new int[]{1, 2, 1, 2, 2, 3}, new int[]{1, 2, 1, 3, 2, 2}, new int[]{1, 3, 1, 2, 2, 2}, new int[]{1, 2, 2, 2, 1, 3}, new int[]{1, 2, 2, 3, 1, 2}, new int[]{1, 3, 2, 2, 1, 2}, new int[]{2, 2, 1, 2, 1, 3}, new int[]{2, 2, 1, 3, 1, 2}, new int[]{2, 3, 1, 2, 1, 2}, new int[]{1, 1, 2, 2, 3, 2}, new int[]{1, 2, 2, 1, 3, 2}, new int[]{1, 2, 2, 2, 3, 1}, new int[]{1, 1, 3, 2, 2, 2}, new int[]{1, 2, 3, 1, 2, 2}, new int[]{1, 2, 3, 2, 2, 1}, new int[]{2, 2, 3, 2, 1, 1}, new int[]{2, 2, 1, 1, 3, 2}, new int[]{2, 2, 1, 2, 3, 1}, new int[]{2, 1, 3, 2, 1, 2}, new int[]{2, 2, 3, 1, 1, 2}, new int[]{3, 1, 2, 1, 3, 1}, new int[]{3, 1, 1, 2, 2, 2}, new int[]{3, 2, 1, 1, 2, 2}, new int[]{3, 2, 1, 2, 2, 1}, new int[]{3, 1, 2, 2, 1, 2}, new int[]{3, 2, 2, 1, 1, 2}, new int[]{3, 2, 2, 2, 1, 1}, new int[]{2, 1, 2, 1, 2, 3}, new int[]{2, 1, 2, 3, 2, 1}, new int[]{2, 3, 2, 1, 2, 1}, new int[]{1, 1, 1, 3, 2, 3}, new int[]{1, 3, 1, 1, 2, 3}, new int[]{1, 3, 1, 3, 2, 1}, new int[]{1, 1, 2, 3, 1, 3}, new int[]{1, 3, 2, 1, 1, 3}, new int[]{1, 3, 2, 3, 1, 1}, new int[]{2, 1, 1, 3, 1, 3}, new int[]{2, 3, 1, 1, 1, 3}, new int[]{2, 3, 1, 3, 1, 1}, new int[]{1, 1, 2, 1, 3, 3}, new int[]{1, 1, 2, 3, 3, 1}, new int[]{1, 3, 2, 1, 3, 1}, new int[]{1, 1, 3, 1, 2, 3}, new int[]{1, 1, 3, 3, 2, 1}, new int[]{1, 3, 3, 1, 2, 1}, new int[]{3, 1, 3, 1, 2, 1}, new int[]{2, 1, 1, 3, 3, 1}, new int[]{2, 3, 1, 1, 3, 1}, new int[]{2, 1, 3, 1, 1, 3}, new int[]{2, 1, 3, 3, 1, 1}, new int[]{2, 1, 3, 1, 3, 1}, new int[]{3, 1, 1, 1, 2, 3}, new int[]{3, 1, 1, 3, 2, 1}, new int[]{3, 3, 1, 1, 2, 1}, new int[]{3, 1, 2, 1, 1, 3}, new int[]{3, 1, 2, 3, 1, 1}, new int[]{3, 3, 2, 1, 1, 1}, new int[]{3, 1, 4, 1, 1, 1}, new int[]{2, 2, 1, 4, 1, 1}, new int[]{4, 3, 1, 1, 1, 1}, new int[]{1, 1, 1, 2, 2, 4}, new int[]{1, 1, 1, 4, 2, 2}, new int[]{1, 2, 1, 1, 2, 4}, new int[]{1, 2, 1, 4, 2, 1}, new int[]{1, 4, 1, 1, 2, 2}, new int[]{1, 4, 1, 2, 2, 1}, new int[]{1, 1, 2, 2, 1, 4}, new int[]{1, 1, 2, 4, 1, 2}, new int[]{1, 2, 2, 1, 1, 4}, new int[]{1, 2, 2, 4, 1, 1}, new int[]{1, 4, 2, 1, 1, 2}, new int[]{1, 4, 2, 2, 1, 1}, new int[]{2, 4, 1, 2, 1, 1}, new int[]{2, 2, 1, 1, 1, 4}, new int[]{4, 1, 3, 1, 1, 1}, new int[]{2, 4, 1, 1, 1, 2}, new int[]{1, 3, 4, 1, 1, 1}, new int[]{1, 1, 1, 2, 4, 2}, new int[]{1, 2, 1, 1, 4, 2}, new int[]{1, 2, 1, 2, 4, 1}, new int[]{1, 1, 4, 2, 1, 2}, new int[]{1, 2, 4, 1, 1, 2}, new int[]{1, 2, 4, 2, 1, 1}, new int[]{4, 1, 1, 2, 1, 2}, new int[]{4, 2, 1, 1, 1, 2}, new int[]{4, 2, 1, 2, 1, 1}, new int[]{2, 1, 2, 1, 4, 1}, new int[]{2, 1, 4, 1, 2, 1}, new int[]{4, 1, 2, 1, 2, 1}, new int[]{1, 1, 1, 1, 4, 3}, new int[]{1, 1, 1, 3, 4, 1}, new int[]{1, 3, 1, 1, 4, 1}, new int[]{1, 1, 4, 1, 1, 3}, new int[]{1, 1, 4, 3, 1, 1}, new int[]{4, 1, 1, 1, 1, 3}, new int[]{4, 1, 1, 3, 1, 1}, new int[]{1, 1, 3, 1, 4, 1}, new int[]{1, 1, 4, 1, 3, 1}, new int[]{3, 1, 1, 1, 4, 1}, new int[]{4, 1, 1, 1, 3, 1}, new int[]{2, 1, 1, 4, 1, 2}, new int[]{2, 1, 1, 2, 1, 4}, new int[]{2, 1, 1, 2, 3, 2}, new int[]{2, 3, 3, 1, 1, 1, 2}};

    private static int[] findStartPattern(BitArray bitArray) throws NotFoundException {
        int size = bitArray.getSize();
        int nextSet = bitArray.getNextSet(0);
        int[] iArr = new int[6];
        int length = iArr.length;
        boolean z = false;
        int i = 0;
        int i2 = nextSet;
        while (nextSet < size) {
            if (bitArray.get(nextSet) ^ z) {
                iArr[i] = iArr[i] + 1;
            } else {
                int i3 = length - 1;
                if (i == i3) {
                    int i4 = 64;
                    int i5 = -1;
                    for (int i6 = 103; i6 <= 105; i6++) {
                        int patternMatchVariance = OneDReader.patternMatchVariance(iArr, CODE_PATTERNS[i6], 179);
                        if (patternMatchVariance < i4) {
                            i5 = i6;
                            i4 = patternMatchVariance;
                        }
                    }
                    if (i5 < 0 || !bitArray.isRange(Math.max(0, i2 - ((nextSet - i2) / 2)), i2, false)) {
                        i2 += iArr[0] + iArr[1];
                        int i7 = length - 2;
                        System.arraycopy(iArr, 2, iArr, 0, i7);
                        iArr[i7] = 0;
                        iArr[i3] = 0;
                        i--;
                    } else {
                        return new int[]{i2, nextSet, i5};
                    }
                } else {
                    i++;
                }
                iArr[i] = 1;
                z = !z;
            }
            nextSet++;
        }
        throw NotFoundException.getNotFoundInstance();
    }

    private static int decodeCode(BitArray bitArray, int[] iArr, int i) throws NotFoundException {
        OneDReader.recordPattern(bitArray, i, iArr);
        int i2 = 64;
        int i3 = -1;
        int i4 = 0;
        while (true) {
            int[][] iArr2 = CODE_PATTERNS;
            if (i4 >= iArr2.length) {
                break;
            }
            int patternMatchVariance = OneDReader.patternMatchVariance(iArr, iArr2[i4], 179);
            if (patternMatchVariance < i2) {
                i3 = i4;
                i2 = patternMatchVariance;
            }
            i4++;
        }
        if (i3 >= 0) {
            return i3;
        }
        throw NotFoundException.getNotFoundInstance();
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0081, code lost:
        r8 = 'd';
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x00bb, code lost:
        r8 = 'd';
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x00e3, code lost:
        r3 = false;
        r8 = 'c';
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x00e8, code lost:
        r8 = 'e';
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x00eb, code lost:
        r8 = r7;
        r3 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x00ee, code lost:
        r8 = r7;
        r3 = false;
        r6 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:0x00f1, code lost:
        r7 = r8;
        r8 = 'd';
     */
    /* JADX WARNING: Code restructure failed: missing block: B:82:0x0129, code lost:
        r3 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:84:0x012c, code lost:
        if (r14 == false) goto L_0x0133;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:85:0x012e, code lost:
        if (r7 != 'e') goto L_0x0132;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:86:0x0130, code lost:
        r7 = r8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:87:0x0132, code lost:
        r7 = 'e';
     */
    /* JADX WARNING: Code restructure failed: missing block: B:88:0x0133, code lost:
        r14 = r3;
        r10 = r12;
        r12 = r21;
        r23 = r16;
        r16 = r2;
        r2 = r23;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.google.zxing.Result decodeRow(int r25, com.google.zxing.common.BitArray r26, java.util.Map<com.google.zxing.DecodeHintType, ?> r27) throws com.google.zxing.NotFoundException, com.google.zxing.FormatException, com.google.zxing.ChecksumException {
        /*
            r24 = this;
            r0 = r26
            r1 = r27
            r2 = 1
            r3 = 0
            if (r1 == 0) goto L_0x0012
            com.google.zxing.DecodeHintType r4 = com.google.zxing.DecodeHintType.ASSUME_GS1
            boolean r1 = r1.containsKey(r4)
            if (r1 == 0) goto L_0x0012
            r1 = r2
            goto L_0x0013
        L_0x0012:
            r1 = r3
        L_0x0013:
            int[] r4 = findStartPattern(r26)
            r5 = 2
            r6 = r4[r5]
            switch(r6) {
                case 103: goto L_0x0028;
                case 104: goto L_0x0025;
                case 105: goto L_0x0022;
                default: goto L_0x001d;
            }
        L_0x001d:
            com.google.zxing.FormatException r0 = com.google.zxing.FormatException.getFormatInstance()
            throw r0
        L_0x0022:
            r10 = 99
            goto L_0x002a
        L_0x0025:
            r10 = 100
            goto L_0x002a
        L_0x0028:
            r10 = 101(0x65, float:1.42E-43)
        L_0x002a:
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            r12 = 20
            r11.<init>(r12)
            java.util.ArrayList r13 = new java.util.ArrayList
            r13.<init>(r12)
            r12 = r4[r3]
            r14 = r4[r2]
            r15 = 6
            int[] r15 = new int[r15]
            r19 = r2
            r2 = r3
            r16 = r2
            r18 = r16
            r17 = r6
            r7 = r10
            r10 = r12
            r12 = r14
            r6 = r18
            r14 = r6
        L_0x004c:
            if (r6 != 0) goto L_0x0141
            int r2 = decodeCode(r0, r15, r12)
            byte r10 = (byte) r2
            java.lang.Byte r10 = java.lang.Byte.valueOf(r10)
            r13.add(r10)
            r10 = 106(0x6a, float:1.49E-43)
            if (r2 == r10) goto L_0x0060
            r19 = 1
        L_0x0060:
            if (r2 == r10) goto L_0x0068
            int r18 = r18 + 1
            int r20 = r18 * r2
            int r17 = r17 + r20
        L_0x0068:
            int r3 = r15.length
            r21 = r12
            r5 = 0
        L_0x006c:
            if (r5 >= r3) goto L_0x0075
            r22 = r15[r5]
            int r21 = r21 + r22
            int r5 = r5 + 1
            goto L_0x006c
        L_0x0075:
            switch(r2) {
                case 103: goto L_0x0085;
                case 104: goto L_0x0085;
                case 105: goto L_0x0085;
                default: goto L_0x0078;
            }
        L_0x0078:
            r3 = 96
            java.lang.String r5 = "]C1"
            r9 = 29
            switch(r7) {
                case 99: goto L_0x00f5;
                case 100: goto L_0x00be;
                case 101: goto L_0x008a;
                default: goto L_0x0081;
            }
        L_0x0081:
            r8 = 100
            goto L_0x0129
        L_0x0085:
            com.google.zxing.FormatException r0 = com.google.zxing.FormatException.getFormatInstance()
            throw r0
        L_0x008a:
            r8 = 64
            if (r2 >= r8) goto L_0x0095
            int r3 = r2 + 32
            char r3 = (char) r3
            r11.append(r3)
            goto L_0x0081
        L_0x0095:
            if (r2 >= r3) goto L_0x009e
            int r3 = r2 + -64
            char r3 = (char) r3
            r11.append(r3)
            goto L_0x0081
        L_0x009e:
            if (r2 == r10) goto L_0x00a2
            r19 = 0
        L_0x00a2:
            if (r2 == r10) goto L_0x00ee
            switch(r2) {
                case 96: goto L_0x00eb;
                case 97: goto L_0x00eb;
                case 98: goto L_0x00ba;
                case 99: goto L_0x00e3;
                case 100: goto L_0x00b8;
                case 101: goto L_0x00eb;
                case 102: goto L_0x00a8;
                default: goto L_0x00a7;
            }
        L_0x00a7:
            goto L_0x00eb
        L_0x00a8:
            if (r1 == 0) goto L_0x00eb
            int r3 = r11.length()
            if (r3 != 0) goto L_0x00b4
            r11.append(r5)
            goto L_0x00eb
        L_0x00b4:
            r11.append(r9)
            goto L_0x00eb
        L_0x00b8:
            r3 = 0
            goto L_0x00bb
        L_0x00ba:
            r3 = 1
        L_0x00bb:
            r8 = 100
            goto L_0x00f1
        L_0x00be:
            if (r2 >= r3) goto L_0x00c7
            int r3 = r2 + 32
            char r3 = (char) r3
            r11.append(r3)
            goto L_0x0081
        L_0x00c7:
            if (r2 == r10) goto L_0x00cb
            r19 = 0
        L_0x00cb:
            if (r2 == r10) goto L_0x00ee
            switch(r2) {
                case 96: goto L_0x00eb;
                case 97: goto L_0x00eb;
                case 98: goto L_0x00e7;
                case 99: goto L_0x00e3;
                case 100: goto L_0x00eb;
                case 101: goto L_0x00e1;
                case 102: goto L_0x00d1;
                default: goto L_0x00d0;
            }
        L_0x00d0:
            goto L_0x00eb
        L_0x00d1:
            if (r1 == 0) goto L_0x00eb
            int r3 = r11.length()
            if (r3 != 0) goto L_0x00dd
            r11.append(r5)
            goto L_0x00eb
        L_0x00dd:
            r11.append(r9)
            goto L_0x00eb
        L_0x00e1:
            r3 = 0
            goto L_0x00e8
        L_0x00e3:
            r3 = 0
            r8 = 99
            goto L_0x00f1
        L_0x00e7:
            r3 = 1
        L_0x00e8:
            r8 = 101(0x65, float:1.42E-43)
            goto L_0x00f1
        L_0x00eb:
            r8 = r7
            r3 = 0
            goto L_0x00f1
        L_0x00ee:
            r8 = r7
            r3 = 0
            r6 = 1
        L_0x00f1:
            r7 = r8
            r8 = 100
            goto L_0x012a
        L_0x00f5:
            r8 = 100
            if (r2 >= r8) goto L_0x0106
            r3 = 10
            if (r2 >= r3) goto L_0x0102
            r3 = 48
            r11.append(r3)
        L_0x0102:
            r11.append(r2)
            goto L_0x0129
        L_0x0106:
            if (r2 == r10) goto L_0x010a
            r19 = 0
        L_0x010a:
            if (r2 == r10) goto L_0x0126
            switch(r2) {
                case 100: goto L_0x0124;
                case 101: goto L_0x0120;
                case 102: goto L_0x0110;
                default: goto L_0x010f;
            }
        L_0x010f:
            goto L_0x0129
        L_0x0110:
            if (r1 == 0) goto L_0x0129
            int r3 = r11.length()
            if (r3 != 0) goto L_0x011c
            r11.append(r5)
            goto L_0x0129
        L_0x011c:
            r11.append(r9)
            goto L_0x0129
        L_0x0120:
            r3 = 0
            r7 = 101(0x65, float:1.42E-43)
            goto L_0x012a
        L_0x0124:
            r7 = r8
            goto L_0x0129
        L_0x0126:
            r3 = 0
            r6 = 1
            goto L_0x012a
        L_0x0129:
            r3 = 0
        L_0x012a:
            r5 = 101(0x65, float:1.42E-43)
            if (r14 == 0) goto L_0x0133
            if (r7 != r5) goto L_0x0132
            r7 = r8
            goto L_0x0133
        L_0x0132:
            r7 = r5
        L_0x0133:
            r14 = r3
            r10 = r12
            r12 = r21
            r3 = 0
            r5 = 2
            r23 = r16
            r16 = r2
            r2 = r23
            goto L_0x004c
        L_0x0141:
            int r1 = r0.getNextUnset(r12)
            int r3 = r26.getSize()
            int r5 = r1 - r10
            r6 = 2
            int r5 = r5 / r6
            int r5 = r5 + r1
            int r3 = java.lang.Math.min(r3, r5)
            r5 = 0
            boolean r0 = r0.isRange(r1, r3, r5)
            if (r0 == 0) goto L_0x01cc
            int r18 = r18 * r2
            int r17 = r17 - r18
            int r0 = r17 % 103
            if (r0 != r2) goto L_0x01c7
            int r0 = r11.length()
            if (r0 == 0) goto L_0x01c2
            if (r0 <= 0) goto L_0x017a
            if (r19 == 0) goto L_0x017a
            r2 = 99
            if (r7 != r2) goto L_0x0175
            int r2 = r0 + -2
            r11.delete(r2, r0)
            goto L_0x017a
        L_0x0175:
            int r2 = r0 + -1
            r11.delete(r2, r0)
        L_0x017a:
            r0 = 1
            r2 = r4[r0]
            r0 = 0
            r3 = r4[r0]
            int r2 = r2 + r3
            float r0 = (float) r2
            r2 = 1073741824(0x40000000, float:2.0)
            float r0 = r0 / r2
            int r1 = r1 + r10
            float r1 = (float) r1
            float r1 = r1 / r2
            int r2 = r13.size()
            byte[] r3 = new byte[r2]
            r4 = 0
        L_0x018f:
            if (r4 >= r2) goto L_0x01a0
            java.lang.Object r5 = r13.get(r4)
            java.lang.Byte r5 = (java.lang.Byte) r5
            byte r5 = r5.byteValue()
            r3[r4] = r5
            int r4 = r4 + 1
            goto L_0x018f
        L_0x01a0:
            com.google.zxing.Result r2 = new com.google.zxing.Result
            java.lang.String r4 = r11.toString()
            r5 = 2
            com.google.zxing.ResultPoint[] r5 = new com.google.zxing.ResultPoint[r5]
            com.google.zxing.ResultPoint r6 = new com.google.zxing.ResultPoint
            r7 = r25
            float r7 = (float) r7
            r6.<init>(r0, r7)
            r0 = 0
            r5[r0] = r6
            com.google.zxing.ResultPoint r0 = new com.google.zxing.ResultPoint
            r0.<init>(r1, r7)
            r1 = 1
            r5[r1] = r0
            com.google.zxing.BarcodeFormat r0 = com.google.zxing.BarcodeFormat.CODE_128
            r2.<init>(r4, r3, r5, r0)
            return r2
        L_0x01c2:
            com.google.zxing.NotFoundException r0 = com.google.zxing.NotFoundException.getNotFoundInstance()
            throw r0
        L_0x01c7:
            com.google.zxing.ChecksumException r0 = com.google.zxing.ChecksumException.getChecksumInstance()
            throw r0
        L_0x01cc:
            com.google.zxing.NotFoundException r0 = com.google.zxing.NotFoundException.getNotFoundInstance()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.oned.Code128Reader.decodeRow(int, com.google.zxing.common.BitArray, java.util.Map):com.google.zxing.Result");
    }
}
