package com.google.zxing.qrcode.encoder;

import com.google.zxing.WriterException;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.google.zxing.qrcode.decoder.Version;

public final class Encoder {
    private static final int[] ALPHANUMERIC_TABLE = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 36, -1, -1, -1, 37, 38, -1, -1, -1, -1, 39, 40, -1, 41, 42, 43, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 44, -1, -1, -1, -1, -1, -1, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, -1, -1, -1, -1, -1};

    private static Version chooseVersion(int i, ErrorCorrectionLevel errorCorrectionLevel) throws WriterException {
        for (int i2 = 1; i2 <= 40; i2++) {
            Version versionForNumber = Version.getVersionForNumber(i2);
            if (versionForNumber.getTotalCodewords() - versionForNumber.getECBlocksForLevel(errorCorrectionLevel).getTotalECCodewords() >= (i + 7) / 8) {
                return versionForNumber;
            }
        }
        throw new WriterException("Data too big");
    }

    /* JADX WARNING: Removed duplicated region for block: B:340:0x0109 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:78:0x00fa A[LOOP:2: B:64:0x00cc->B:78:0x00fa, LOOP_END] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.google.zxing.qrcode.encoder.QRCode encode(java.lang.String r20, com.google.zxing.qrcode.decoder.ErrorCorrectionLevel r21, java.util.Map<com.google.zxing.EncodeHintType, ?> r22) throws com.google.zxing.WriterException {
        /*
            r0 = r20
            r1 = r21
            r2 = r22
            if (r2 != 0) goto L_0x000a
            r2 = 0
            goto L_0x0012
        L_0x000a:
            com.google.zxing.EncodeHintType r3 = com.google.zxing.EncodeHintType.CHARACTER_SET
            java.lang.Object r2 = r2.get(r3)
            java.lang.String r2 = (java.lang.String) r2
        L_0x0012:
            java.lang.String r3 = "ISO-8859-1"
            if (r2 != 0) goto L_0x0017
            r2 = r3
        L_0x0017:
            java.lang.String r4 = "Shift_JIS"
            boolean r5 = r4.equals(r2)
            r6 = -1
            r7 = 1
            if (r5 == 0) goto L_0x0051
            byte[] r5 = r0.getBytes(r4)     // Catch:{ UnsupportedEncodingException -> 0x0048 }
            int r8 = r5.length
            int r9 = r8 % 2
            if (r9 == 0) goto L_0x002b
            goto L_0x0048
        L_0x002b:
            r9 = 0
        L_0x002c:
            if (r9 >= r8) goto L_0x0046
            byte r10 = r5[r9]
            r10 = r10 & 255(0xff, float:3.57E-43)
            r11 = 129(0x81, float:1.81E-43)
            if (r10 < r11) goto L_0x003a
            r11 = 159(0x9f, float:2.23E-43)
            if (r10 <= r11) goto L_0x0043
        L_0x003a:
            r11 = 224(0xe0, float:3.14E-43)
            if (r10 < r11) goto L_0x0048
            r11 = 235(0xeb, float:3.3E-43)
            if (r10 <= r11) goto L_0x0043
            goto L_0x0048
        L_0x0043:
            int r9 = r9 + 2
            goto L_0x002c
        L_0x0046:
            r5 = r7
            goto L_0x0049
        L_0x0048:
            r5 = 0
        L_0x0049:
            if (r5 == 0) goto L_0x004e
            com.google.zxing.qrcode.decoder.Mode r5 = com.google.zxing.qrcode.decoder.Mode.KANJI
            goto L_0x0081
        L_0x004e:
            com.google.zxing.qrcode.decoder.Mode r5 = com.google.zxing.qrcode.decoder.Mode.BYTE
            goto L_0x0081
        L_0x0051:
            r5 = 0
            r8 = 0
            r9 = 0
        L_0x0054:
            int r10 = r20.length()
            if (r5 >= r10) goto L_0x0075
            char r10 = r0.charAt(r5)
            r11 = 48
            if (r10 < r11) goto L_0x0068
            r11 = 57
            if (r10 > r11) goto L_0x0068
            r9 = r7
            goto L_0x006f
        L_0x0068:
            int r8 = getAlphanumericCode(r10)
            if (r8 == r6) goto L_0x0072
            r8 = r7
        L_0x006f:
            int r5 = r5 + 1
            goto L_0x0054
        L_0x0072:
            com.google.zxing.qrcode.decoder.Mode r5 = com.google.zxing.qrcode.decoder.Mode.BYTE
            goto L_0x0081
        L_0x0075:
            if (r8 == 0) goto L_0x007a
            com.google.zxing.qrcode.decoder.Mode r5 = com.google.zxing.qrcode.decoder.Mode.ALPHANUMERIC
            goto L_0x0081
        L_0x007a:
            if (r9 == 0) goto L_0x007f
            com.google.zxing.qrcode.decoder.Mode r5 = com.google.zxing.qrcode.decoder.Mode.NUMERIC
            goto L_0x0081
        L_0x007f:
            com.google.zxing.qrcode.decoder.Mode r5 = com.google.zxing.qrcode.decoder.Mode.BYTE
        L_0x0081:
            com.google.zxing.common.BitArray r8 = new com.google.zxing.common.BitArray
            r8.<init>()
            com.google.zxing.qrcode.decoder.Mode r9 = com.google.zxing.qrcode.decoder.Mode.BYTE
            r10 = 4
            r11 = 8
            if (r5 != r9) goto L_0x00a9
            boolean r3 = r3.equals(r2)
            if (r3 != 0) goto L_0x00a9
            com.google.zxing.common.CharacterSetECI r3 = com.google.zxing.common.CharacterSetECI.getCharacterSetECIByName(r2)
            if (r3 == 0) goto L_0x00a9
            com.google.zxing.qrcode.decoder.Mode r9 = com.google.zxing.qrcode.decoder.Mode.ECI
            int r9 = r9.getBits()
            r8.appendBits(r9, r10)
            int r3 = r3.getValue()
            r8.appendBits(r3, r11)
        L_0x00a9:
            int r3 = r5.getBits()
            r8.appendBits(r3, r10)
            com.google.zxing.common.BitArray r3 = new com.google.zxing.common.BitArray
            r3.<init>()
            int r9 = r5.ordinal()
            r12 = 7
            r13 = 10
            if (r9 == r7) goto L_0x0178
            r13 = 6
            r14 = 2
            if (r9 == r14) goto L_0x013d
            if (r9 == r10) goto L_0x0125
            if (r9 != r13) goto L_0x0119
            byte[] r2 = r0.getBytes(r4)     // Catch:{ UnsupportedEncodingException -> 0x0111 }
            int r4 = r2.length
            r9 = 0
        L_0x00cc:
            if (r9 >= r4) goto L_0x01b8
            byte r13 = r2[r9]
            r13 = r13 & 255(0xff, float:3.57E-43)
            int r14 = r9 + 1
            byte r14 = r2[r14]
            r14 = r14 & 255(0xff, float:3.57E-43)
            int r13 = r13 << r11
            r13 = r13 | r14
            r14 = 33088(0x8140, float:4.6366E-41)
            if (r13 < r14) goto L_0x00e8
            r14 = 40956(0x9ffc, float:5.7392E-41)
            if (r13 > r14) goto L_0x00e8
            r14 = 33088(0x8140, float:4.6366E-41)
            goto L_0x00f5
        L_0x00e8:
            r14 = 57408(0xe040, float:8.0446E-41)
            if (r13 < r14) goto L_0x00f7
            r14 = 60351(0xebbf, float:8.457E-41)
            if (r13 > r14) goto L_0x00f7
            r14 = 49472(0xc140, float:6.9325E-41)
        L_0x00f5:
            int r13 = r13 - r14
            goto L_0x00f8
        L_0x00f7:
            r13 = r6
        L_0x00f8:
            if (r13 == r6) goto L_0x0109
            int r14 = r13 >> 8
            int r14 = r14 * 192
            r13 = r13 & 255(0xff, float:3.57E-43)
            int r14 = r14 + r13
            r13 = 13
            r3.appendBits(r14, r13)
            int r9 = r9 + 2
            goto L_0x00cc
        L_0x0109:
            com.google.zxing.WriterException r0 = new com.google.zxing.WriterException
            java.lang.String r1 = "Invalid byte sequence"
            r0.<init>((java.lang.String) r1)
            throw r0
        L_0x0111:
            r0 = move-exception
            r1 = r0
            com.google.zxing.WriterException r0 = new com.google.zxing.WriterException
            r0.<init>((java.lang.Throwable) r1)
            throw r0
        L_0x0119:
            com.google.zxing.WriterException r0 = new com.google.zxing.WriterException
            java.lang.String r1 = "Invalid mode: "
            java.lang.String r1 = com.android.tools.p006r8.GeneratedOutlineSupport.outline6(r1, r5)
            r0.<init>((java.lang.String) r1)
            throw r0
        L_0x0125:
            byte[] r2 = r0.getBytes(r2)     // Catch:{ UnsupportedEncodingException -> 0x0135 }
            int r4 = r2.length
            r6 = 0
        L_0x012b:
            if (r6 >= r4) goto L_0x01b8
            byte r9 = r2[r6]
            r3.appendBits(r9, r11)
            int r6 = r6 + 1
            goto L_0x012b
        L_0x0135:
            r0 = move-exception
            r1 = r0
            com.google.zxing.WriterException r0 = new com.google.zxing.WriterException
            r0.<init>((java.lang.Throwable) r1)
            throw r0
        L_0x013d:
            int r2 = r20.length()
            r4 = 0
        L_0x0142:
            if (r4 >= r2) goto L_0x01b8
            char r9 = r0.charAt(r4)
            int r9 = getAlphanumericCode(r9)
            if (r9 == r6) goto L_0x0172
            int r14 = r4 + 1
            if (r14 >= r2) goto L_0x016d
            char r14 = r0.charAt(r14)
            int r14 = getAlphanumericCode(r14)
            if (r14 == r6) goto L_0x0167
            int r9 = r9 * 45
            int r9 = r9 + r14
            r14 = 11
            r3.appendBits(r9, r14)
            int r4 = r4 + 2
            goto L_0x0142
        L_0x0167:
            com.google.zxing.WriterException r0 = new com.google.zxing.WriterException
            r0.<init>()
            throw r0
        L_0x016d:
            r3.appendBits(r9, r13)
            r4 = r14
            goto L_0x0142
        L_0x0172:
            com.google.zxing.WriterException r0 = new com.google.zxing.WriterException
            r0.<init>()
            throw r0
        L_0x0178:
            int r2 = r20.length()
            r4 = 0
        L_0x017d:
            if (r4 >= r2) goto L_0x01b8
            char r6 = r0.charAt(r4)
            int r6 = r6 + -48
            int r9 = r4 + 2
            if (r9 >= r2) goto L_0x01a2
            int r14 = r4 + 1
            char r14 = r0.charAt(r14)
            int r14 = r14 + -48
            char r9 = r0.charAt(r9)
            int r9 = r9 + -48
            int r6 = r6 * 100
            int r14 = r14 * r13
            int r14 = r14 + r6
            int r14 = r14 + r9
            r3.appendBits(r14, r13)
            int r4 = r4 + 3
            goto L_0x017d
        L_0x01a2:
            int r4 = r4 + 1
            if (r4 >= r2) goto L_0x01b4
            char r4 = r0.charAt(r4)
            int r4 = r4 + -48
            int r6 = r6 * 10
            int r6 = r6 + r4
            r3.appendBits(r6, r12)
            r4 = r9
            goto L_0x017d
        L_0x01b4:
            r3.appendBits(r6, r10)
            goto L_0x017d
        L_0x01b8:
            int r2 = r8.getSize()
            com.google.zxing.qrcode.decoder.Version r4 = com.google.zxing.qrcode.decoder.Version.getVersionForNumber(r7)
            int r4 = r5.getCharacterCountBits(r4)
            int r4 = r4 + r2
            int r2 = r3.getSize()
            int r2 = r2 + r4
            com.google.zxing.qrcode.decoder.Version r2 = chooseVersion(r2, r1)
            int r4 = r8.getSize()
            int r2 = r5.getCharacterCountBits(r2)
            int r2 = r2 + r4
            int r4 = r3.getSize()
            int r4 = r4 + r2
            com.google.zxing.qrcode.decoder.Version r2 = chooseVersion(r4, r1)
            com.google.zxing.common.BitArray r4 = new com.google.zxing.common.BitArray
            r4.<init>()
            r4.appendBitArray(r8)
            com.google.zxing.qrcode.decoder.Mode r6 = com.google.zxing.qrcode.decoder.Mode.BYTE
            if (r5 != r6) goto L_0x01f1
            int r0 = r3.getSizeInBytes()
            goto L_0x01f5
        L_0x01f1:
            int r0 = r20.length()
        L_0x01f5:
            int r6 = r5.getCharacterCountBits(r2)
            int r8 = r7 << r6
            if (r0 >= r8) goto L_0x0607
            r4.appendBits(r0, r6)
            r4.appendBitArray(r3)
            com.google.zxing.qrcode.decoder.Version$ECBlocks r0 = r2.getECBlocksForLevel(r1)
            int r3 = r2.getTotalCodewords()
            int r6 = r0.getTotalECCodewords()
            int r3 = r3 - r6
            int r6 = r3 << 3
            int r8 = r4.getSize()
            if (r8 > r6) goto L_0x05e8
            r8 = 0
        L_0x0219:
            if (r8 >= r10) goto L_0x0228
            int r9 = r4.getSize()
            if (r9 >= r6) goto L_0x0228
            r9 = 0
            r4.appendBit(r9)
            int r8 = r8 + 1
            goto L_0x0219
        L_0x0228:
            r8 = 0
            int r9 = r4.getSize()
            r9 = r9 & r12
            if (r9 <= 0) goto L_0x0239
        L_0x0230:
            if (r9 >= r11) goto L_0x0239
            r4.appendBit(r8)
            int r9 = r9 + 1
            r8 = 0
            goto L_0x0230
        L_0x0239:
            int r8 = r4.getSizeInBytes()
            int r8 = r3 - r8
            r9 = 0
        L_0x0240:
            if (r9 >= r8) goto L_0x0251
            r10 = r9 & 1
            if (r10 != 0) goto L_0x0249
            r10 = 236(0xec, float:3.31E-43)
            goto L_0x024b
        L_0x0249:
            r10 = 17
        L_0x024b:
            r4.appendBits(r10, r11)
            int r9 = r9 + 1
            goto L_0x0240
        L_0x0251:
            int r8 = r4.getSize()
            if (r8 != r6) goto L_0x05e0
            int r6 = r2.getTotalCodewords()
            int r0 = r0.getNumBlocks()
            int r8 = r4.getSizeInBytes()
            if (r8 != r3) goto L_0x05d8
            java.util.ArrayList r8 = new java.util.ArrayList
            r8.<init>(r0)
            r9 = 0
            r10 = 0
            r11 = 0
            r12 = 0
        L_0x026e:
            if (r9 >= r0) goto L_0x0357
            int[] r13 = new int[r7]
            int[] r7 = new int[r7]
            if (r9 >= r0) goto L_0x034f
            int r14 = r6 % r0
            int r15 = r0 - r14
            int r16 = r6 / r0
            int r17 = r16 + 1
            int r18 = r3 / r0
            int r19 = r18 + 1
            r22 = r2
            int r2 = r16 - r18
            r16 = r5
            int r5 = r17 - r19
            if (r2 != r5) goto L_0x0347
            int r1 = r15 + r14
            if (r0 != r1) goto L_0x033f
            int r1 = r18 + r2
            int r1 = r1 * r15
            int r17 = r19 + r5
            int r17 = r17 * r14
            int r1 = r17 + r1
            if (r6 != r1) goto L_0x0337
            r1 = 0
            if (r9 >= r15) goto L_0x02a3
            r13[r1] = r18
            r7[r1] = r2
            goto L_0x02a7
        L_0x02a3:
            r13[r1] = r19
            r7[r1] = r5
        L_0x02a7:
            r1 = r13[r1]
            byte[] r2 = new byte[r1]
            int r5 = r10 * 8
            r14 = 0
        L_0x02ae:
            if (r14 >= r1) goto L_0x02e2
            r15 = 0
            r17 = 8
            r18 = 0
            r20 = r0
            r0 = r15
            r15 = r17
            r17 = r6
            r6 = r5
            r5 = r18
        L_0x02bf:
            if (r5 >= r15) goto L_0x02d5
            boolean r15 = r4.get(r6)
            if (r15 == 0) goto L_0x02ce
            int r15 = 7 - r5
            r18 = 1
            int r15 = r18 << r15
            r0 = r0 | r15
        L_0x02ce:
            int r6 = r6 + 1
            int r5 = r5 + 1
            r15 = 8
            goto L_0x02bf
        L_0x02d5:
            int r5 = r14 + 0
            byte r0 = (byte) r0
            r2[r5] = r0
            int r14 = r14 + 1
            r0 = r20
            r5 = r6
            r6 = r17
            goto L_0x02ae
        L_0x02e2:
            r20 = r0
            r17 = r6
            r0 = 0
            r0 = r7[r0]
            int r5 = r2.length
            int r6 = r5 + r0
            int[] r6 = new int[r6]
            r7 = 0
        L_0x02ef:
            if (r7 >= r5) goto L_0x02fa
            byte r14 = r2[r7]
            r14 = r14 & 255(0xff, float:3.57E-43)
            r6[r7] = r14
            int r7 = r7 + 1
            goto L_0x02ef
        L_0x02fa:
            com.google.zxing.common.reedsolomon.ReedSolomonEncoder r7 = new com.google.zxing.common.reedsolomon.ReedSolomonEncoder
            com.google.zxing.common.reedsolomon.GenericGF r14 = com.google.zxing.common.reedsolomon.GenericGF.QR_CODE_FIELD_256
            r7.<init>(r14)
            r7.encode(r6, r0)
            byte[] r7 = new byte[r0]
            r14 = 0
        L_0x0307:
            if (r14 >= r0) goto L_0x0313
            int r15 = r5 + r14
            r15 = r6[r15]
            byte r15 = (byte) r15
            r7[r14] = r15
            int r14 = r14 + 1
            goto L_0x0307
        L_0x0313:
            com.google.zxing.qrcode.encoder.BlockPair r0 = new com.google.zxing.qrcode.encoder.BlockPair
            r0.<init>(r2, r7)
            r8.add(r0)
            int r12 = java.lang.Math.max(r12, r1)
            int r0 = r7.length
            int r11 = java.lang.Math.max(r11, r0)
            r0 = 0
            r0 = r13[r0]
            int r10 = r10 + r0
            int r9 = r9 + 1
            r7 = 1
            r0 = r20
            r1 = r21
            r2 = r22
            r5 = r16
            r6 = r17
            goto L_0x026e
        L_0x0337:
            com.google.zxing.WriterException r0 = new com.google.zxing.WriterException
            java.lang.String r1 = "Total bytes mismatch"
            r0.<init>((java.lang.String) r1)
            throw r0
        L_0x033f:
            com.google.zxing.WriterException r0 = new com.google.zxing.WriterException
            java.lang.String r1 = "RS blocks mismatch"
            r0.<init>((java.lang.String) r1)
            throw r0
        L_0x0347:
            com.google.zxing.WriterException r0 = new com.google.zxing.WriterException
            java.lang.String r1 = "EC bytes mismatch"
            r0.<init>((java.lang.String) r1)
            throw r0
        L_0x034f:
            com.google.zxing.WriterException r0 = new com.google.zxing.WriterException
            java.lang.String r1 = "Block ID too large"
            r0.<init>((java.lang.String) r1)
            throw r0
        L_0x0357:
            r22 = r2
            r16 = r5
            r17 = r6
            r0 = 0
            if (r3 != r10) goto L_0x05d0
            com.google.zxing.common.BitArray r1 = new com.google.zxing.common.BitArray
            r1.<init>()
            r2 = r0
        L_0x0366:
            if (r2 >= r12) goto L_0x038a
            java.util.Iterator r3 = r8.iterator()
        L_0x036c:
            boolean r4 = r3.hasNext()
            if (r4 == 0) goto L_0x0387
            java.lang.Object r4 = r3.next()
            com.google.zxing.qrcode.encoder.BlockPair r4 = (com.google.zxing.qrcode.encoder.BlockPair) r4
            byte[] r4 = r4.getDataBytes()
            int r5 = r4.length
            if (r2 >= r5) goto L_0x036c
            byte r4 = r4[r2]
            r5 = 8
            r1.appendBits(r4, r5)
            goto L_0x036c
        L_0x0387:
            int r2 = r2 + 1
            goto L_0x0366
        L_0x038a:
            r2 = r0
        L_0x038b:
            if (r2 >= r11) goto L_0x03af
            java.util.Iterator r3 = r8.iterator()
        L_0x0391:
            boolean r4 = r3.hasNext()
            if (r4 == 0) goto L_0x03ac
            java.lang.Object r4 = r3.next()
            com.google.zxing.qrcode.encoder.BlockPair r4 = (com.google.zxing.qrcode.encoder.BlockPair) r4
            byte[] r4 = r4.getErrorCorrectionBytes()
            int r5 = r4.length
            if (r2 >= r5) goto L_0x0391
            byte r4 = r4[r2]
            r5 = 8
            r1.appendBits(r4, r5)
            goto L_0x0391
        L_0x03ac:
            int r2 = r2 + 1
            goto L_0x038b
        L_0x03af:
            int r2 = r1.getSizeInBytes()
            r3 = r17
            if (r3 != r2) goto L_0x05a8
            com.google.zxing.qrcode.encoder.QRCode r2 = new com.google.zxing.qrcode.encoder.QRCode
            r2.<init>()
            r3 = r21
            r2.setECLevel(r3)
            r5 = r16
            r2.setMode(r5)
            r4 = r22
            r2.setVersion(r4)
            int r5 = r4.getDimensionForVersion()
            com.google.zxing.qrcode.encoder.ByteMatrix r6 = new com.google.zxing.qrcode.encoder.ByteMatrix
            r6.<init>(r5, r5)
            r5 = 2147483647(0x7fffffff, float:NaN)
            r7 = -1
            r8 = 8
            r9 = r5
            r5 = r0
        L_0x03dc:
            if (r0 >= r8) goto L_0x059a
            com.google.zxing.qrcode.encoder.MatrixUtil.buildMatrix(r1, r3, r4, r0, r6)
            int r8 = com.google.zxing.qrcode.encoder.MaskUtil.applyMaskPenaltyRule1(r6)
            byte[][] r10 = r6.getArray()
            int r11 = r6.getWidth()
            int r12 = r6.getHeight()
            r13 = r5
        L_0x03f2:
            int r14 = r12 + -1
            if (r5 >= r14) goto L_0x0423
            r14 = 0
        L_0x03f7:
            int r15 = r11 + -1
            if (r14 >= r15) goto L_0x041e
            r15 = r10[r5]
            byte r15 = r15[r14]
            r16 = r10[r5]
            int r17 = r14 + 1
            r20 = r11
            byte r11 = r16[r17]
            if (r15 != r11) goto L_0x0419
            int r11 = r5 + 1
            r16 = r10[r11]
            byte r14 = r16[r14]
            if (r15 != r14) goto L_0x0419
            r11 = r10[r11]
            byte r11 = r11[r17]
            if (r15 != r11) goto L_0x0419
            int r13 = r13 + 1
        L_0x0419:
            r11 = r20
            r14 = r17
            goto L_0x03f7
        L_0x041e:
            r20 = r11
            int r5 = r5 + 1
            goto L_0x03f2
        L_0x0423:
            int r13 = r13 * 3
            int r13 = r13 + r8
            byte[][] r5 = r6.getArray()
            int r8 = r6.getWidth()
            int r10 = r6.getHeight()
            r11 = 0
            r12 = 0
        L_0x0434:
            if (r11 >= r10) goto L_0x054c
            r14 = 0
        L_0x0437:
            if (r14 >= r8) goto L_0x0544
            int r15 = r14 + 6
            if (r15 >= r8) goto L_0x04bb
            r16 = r5[r11]
            r20 = r1
            byte r1 = r16[r14]
            r3 = 1
            if (r1 != r3) goto L_0x04bd
            r1 = r5[r11]
            int r16 = r14 + 1
            byte r1 = r1[r16]
            if (r1 != 0) goto L_0x04bd
            r1 = r5[r11]
            int r16 = r14 + 2
            byte r1 = r1[r16]
            if (r1 != r3) goto L_0x04bd
            r1 = r5[r11]
            int r16 = r14 + 3
            byte r1 = r1[r16]
            if (r1 != r3) goto L_0x04bd
            r1 = r5[r11]
            int r16 = r14 + 4
            byte r1 = r1[r16]
            if (r1 != r3) goto L_0x04bd
            r1 = r5[r11]
            int r16 = r14 + 5
            byte r1 = r1[r16]
            if (r1 != 0) goto L_0x04bd
            r1 = r5[r11]
            byte r1 = r1[r15]
            if (r1 != r3) goto L_0x04bd
            int r1 = r14 + 10
            if (r1 >= r8) goto L_0x0496
            r3 = r5[r11]
            int r15 = r14 + 7
            byte r3 = r3[r15]
            if (r3 != 0) goto L_0x0496
            r3 = r5[r11]
            int r15 = r14 + 8
            byte r3 = r3[r15]
            if (r3 != 0) goto L_0x0496
            r3 = r5[r11]
            int r15 = r14 + 9
            byte r3 = r3[r15]
            if (r3 != 0) goto L_0x0496
            r3 = r5[r11]
            byte r1 = r3[r1]
            if (r1 == 0) goto L_0x04b8
        L_0x0496:
            int r1 = r14 + -4
            if (r1 < 0) goto L_0x04bd
            r3 = r5[r11]
            int r15 = r14 + -1
            byte r3 = r3[r15]
            if (r3 != 0) goto L_0x04bd
            r3 = r5[r11]
            int r15 = r14 + -2
            byte r3 = r3[r15]
            if (r3 != 0) goto L_0x04bd
            r3 = r5[r11]
            int r15 = r14 + -3
            byte r3 = r3[r15]
            if (r3 != 0) goto L_0x04bd
            r3 = r5[r11]
            byte r1 = r3[r1]
            if (r1 != 0) goto L_0x04bd
        L_0x04b8:
            int r12 = r12 + 40
            goto L_0x04bd
        L_0x04bb:
            r20 = r1
        L_0x04bd:
            int r1 = r11 + 6
            if (r1 >= r10) goto L_0x053c
            r3 = r5[r11]
            byte r3 = r3[r14]
            r15 = 1
            if (r3 != r15) goto L_0x053c
            int r3 = r11 + 1
            r3 = r5[r3]
            byte r3 = r3[r14]
            if (r3 != 0) goto L_0x053c
            int r3 = r11 + 2
            r3 = r5[r3]
            byte r3 = r3[r14]
            if (r3 != r15) goto L_0x053c
            int r3 = r11 + 3
            r3 = r5[r3]
            byte r3 = r3[r14]
            if (r3 != r15) goto L_0x053c
            int r3 = r11 + 4
            r3 = r5[r3]
            byte r3 = r3[r14]
            if (r3 != r15) goto L_0x053c
            int r3 = r11 + 5
            r3 = r5[r3]
            byte r3 = r3[r14]
            if (r3 != 0) goto L_0x053c
            r1 = r5[r1]
            byte r1 = r1[r14]
            if (r1 != r15) goto L_0x053c
            int r1 = r11 + 10
            if (r1 >= r10) goto L_0x0518
            int r3 = r11 + 7
            r3 = r5[r3]
            byte r3 = r3[r14]
            if (r3 != 0) goto L_0x0518
            int r3 = r11 + 8
            r3 = r5[r3]
            byte r3 = r3[r14]
            if (r3 != 0) goto L_0x0518
            int r3 = r11 + 9
            r3 = r5[r3]
            byte r3 = r3[r14]
            if (r3 != 0) goto L_0x0518
            r1 = r5[r1]
            byte r1 = r1[r14]
            if (r1 == 0) goto L_0x053a
        L_0x0518:
            int r1 = r11 + -4
            if (r1 < 0) goto L_0x053c
            int r3 = r11 + -1
            r3 = r5[r3]
            byte r3 = r3[r14]
            if (r3 != 0) goto L_0x053c
            int r3 = r11 + -2
            r3 = r5[r3]
            byte r3 = r3[r14]
            if (r3 != 0) goto L_0x053c
            int r3 = r11 + -3
            r3 = r5[r3]
            byte r3 = r3[r14]
            if (r3 != 0) goto L_0x053c
            r1 = r5[r1]
            byte r1 = r1[r14]
            if (r1 != 0) goto L_0x053c
        L_0x053a:
            int r12 = r12 + 40
        L_0x053c:
            int r14 = r14 + 1
            r1 = r20
            r3 = r21
            goto L_0x0437
        L_0x0544:
            r20 = r1
            int r11 = r11 + 1
            r3 = r21
            goto L_0x0434
        L_0x054c:
            r20 = r1
            int r13 = r13 + r12
            byte[][] r1 = r6.getArray()
            int r3 = r6.getWidth()
            int r5 = r6.getHeight()
            r8 = 0
            r10 = 0
        L_0x055d:
            if (r8 >= r5) goto L_0x0571
            r11 = r1[r8]
            r12 = 0
        L_0x0562:
            if (r12 >= r3) goto L_0x056e
            byte r14 = r11[r12]
            r15 = 1
            if (r14 != r15) goto L_0x056b
            int r10 = r10 + 1
        L_0x056b:
            int r12 = r12 + 1
            goto L_0x0562
        L_0x056e:
            int r8 = r8 + 1
            goto L_0x055d
        L_0x0571:
            int r1 = r6.getHeight()
            int r3 = r6.getWidth()
            int r3 = r3 * r1
            double r10 = (double) r10
            double r14 = (double) r3
            double r10 = r10 / r14
            r14 = 4602678819172646912(0x3fe0000000000000, double:0.5)
            double r10 = r10 - r14
            double r10 = java.lang.Math.abs(r10)
            r14 = 4626322717216342016(0x4034000000000000, double:20.0)
            double r10 = r10 * r14
            int r1 = (int) r10
            int r1 = r1 * 10
            int r1 = r1 + r13
            if (r1 >= r9) goto L_0x058f
            r7 = r0
            r9 = r1
        L_0x058f:
            int r0 = r0 + 1
            r5 = 0
            r8 = 8
            r1 = r20
            r3 = r21
            goto L_0x03dc
        L_0x059a:
            r20 = r1
            r2.setMaskPattern(r7)
            r0 = r21
            com.google.zxing.qrcode.encoder.MatrixUtil.buildMatrix(r1, r0, r4, r7, r6)
            r2.setMatrix(r6)
            return r2
        L_0x05a8:
            com.google.zxing.WriterException r0 = new com.google.zxing.WriterException
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r4 = "Interleaving error: "
            r2.append(r4)
            r2.append(r3)
            java.lang.String r3 = " and "
            r2.append(r3)
            int r1 = r1.getSizeInBytes()
            r2.append(r1)
            java.lang.String r1 = " differ."
            r2.append(r1)
            java.lang.String r1 = r2.toString()
            r0.<init>((java.lang.String) r1)
            throw r0
        L_0x05d0:
            com.google.zxing.WriterException r0 = new com.google.zxing.WriterException
            java.lang.String r1 = "Data bytes does not match offset"
            r0.<init>((java.lang.String) r1)
            throw r0
        L_0x05d8:
            com.google.zxing.WriterException r0 = new com.google.zxing.WriterException
            java.lang.String r1 = "Number of bits and data bytes does not match"
            r0.<init>((java.lang.String) r1)
            throw r0
        L_0x05e0:
            com.google.zxing.WriterException r0 = new com.google.zxing.WriterException
            java.lang.String r1 = "Bits size does not equal capacity"
            r0.<init>((java.lang.String) r1)
            throw r0
        L_0x05e8:
            com.google.zxing.WriterException r0 = new com.google.zxing.WriterException
            java.lang.String r1 = "data bits cannot fit in the QR Code"
            java.lang.StringBuilder r1 = com.android.tools.p006r8.GeneratedOutlineSupport.outline13(r1)
            int r2 = r4.getSize()
            r1.append(r2)
            java.lang.String r2 = " > "
            r1.append(r2)
            r1.append(r6)
            java.lang.String r1 = r1.toString()
            r0.<init>((java.lang.String) r1)
            throw r0
        L_0x0607:
            com.google.zxing.WriterException r1 = new com.google.zxing.WriterException
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            r2.append(r0)
            java.lang.String r0 = " is bigger than "
            r2.append(r0)
            int r8 = r8 + -1
            r2.append(r8)
            java.lang.String r0 = r2.toString()
            r1.<init>((java.lang.String) r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.qrcode.encoder.Encoder.encode(java.lang.String, com.google.zxing.qrcode.decoder.ErrorCorrectionLevel, java.util.Map):com.google.zxing.qrcode.encoder.QRCode");
    }

    static int getAlphanumericCode(int i) {
        int[] iArr = ALPHANUMERIC_TABLE;
        if (i < iArr.length) {
            return iArr[i];
        }
        return -1;
    }
}
