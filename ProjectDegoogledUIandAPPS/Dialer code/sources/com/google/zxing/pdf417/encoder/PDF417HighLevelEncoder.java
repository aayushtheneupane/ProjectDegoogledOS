package com.google.zxing.pdf417.encoder;

import com.google.zxing.WriterException;
import java.math.BigInteger;
import java.util.Arrays;

final class PDF417HighLevelEncoder {
    private static final byte[] MIXED = new byte[128];
    private static final byte[] PUNCTUATION = new byte[128];
    private static final byte[] TEXT_MIXED_RAW = {48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 38, 13, 9, 44, 58, 35, 45, 46, 36, 47, 43, 37, 42, 61, 94, 0, 32, 0, 0, 0};
    private static final byte[] TEXT_PUNCTUATION_RAW = {59, 60, 62, 64, 91, 92, 93, 95, 96, 126, 33, 13, 9, 44, 58, 10, 45, 46, 36, 47, 34, 124, 42, 40, 41, 63, 123, 125, 39, 0};

    static {
        Arrays.fill(MIXED, (byte) -1);
        byte b = 0;
        byte b2 = 0;
        while (true) {
            byte[] bArr = TEXT_MIXED_RAW;
            if (b2 >= bArr.length) {
                break;
            }
            byte b3 = bArr[b2];
            if (b3 > 0) {
                MIXED[b3] = b2;
            }
            b2 = (byte) (b2 + 1);
        }
        Arrays.fill(PUNCTUATION, (byte) -1);
        while (true) {
            byte[] bArr2 = TEXT_PUNCTUATION_RAW;
            if (b < bArr2.length) {
                byte b4 = bArr2[b];
                if (b4 > 0) {
                    PUNCTUATION[b4] = b;
                }
                b = (byte) (b + 1);
            } else {
                return;
            }
        }
    }

    private static void encodeBinary(byte[] bArr, int i, int i2, int i3, StringBuilder sb) {
        int i4;
        if (i2 == 1 && i3 == 0) {
            sb.append(913);
        }
        if (i2 >= 6) {
            sb.append(924);
            char[] cArr = new char[5];
            i4 = i;
            while ((i + i2) - i4 >= 6) {
                long j = 0;
                for (int i5 = 0; i5 < 6; i5++) {
                    j = (j << 8) + ((long) (bArr[i4 + i5] & 255));
                }
                for (int i6 = 0; i6 < 5; i6++) {
                    cArr[i6] = (char) ((int) (j % 900));
                    j /= 900;
                }
                for (int length = cArr.length - 1; length >= 0; length--) {
                    sb.append(cArr[length]);
                }
                i4 += 6;
            }
        } else {
            i4 = i;
        }
        int i7 = i + i2;
        if (i4 < i7) {
            sb.append(901);
        }
        while (i4 < i7) {
            sb.append((char) (bArr[i4] & 255));
            i4++;
        }
    }

    static String encodeHighLevel(String str, Compaction compaction) throws WriterException {
        int i;
        int i2;
        int i3;
        String str2 = str;
        Compaction compaction2 = compaction;
        StringBuilder sb = new StringBuilder(str.length());
        int length = str.length();
        if (compaction2 == Compaction.TEXT) {
            encodeText(str2, 0, length, sb, 0);
        } else if (compaction2 == Compaction.BYTE) {
            byte[] bytes = str.getBytes();
            encodeBinary(bytes, 0, bytes.length, 1, sb);
        } else {
            char c = 902;
            if (compaction2 == Compaction.NUMERIC) {
                sb.append(902);
                encodeNumeric(str2, 0, length, sb);
            } else {
                byte[] bArr = null;
                int i4 = 0;
                int i5 = 0;
                int i6 = 0;
                while (i4 < length) {
                    int length2 = str.length();
                    if (i4 < length2) {
                        char charAt = str2.charAt(i4);
                        int i7 = i4;
                        i = 0;
                        while (isDigit(charAt) && i7 < length2) {
                            i++;
                            i7++;
                            if (i7 < length2) {
                                charAt = str2.charAt(i7);
                            }
                        }
                    } else {
                        i = 0;
                    }
                    if (i >= 13) {
                        sb.append(c);
                        encodeNumeric(str2, i4, i, sb);
                        i4 += i;
                        i6 = 2;
                        i5 = 0;
                    } else {
                        int length3 = str.length();
                        int i8 = i4;
                        while (true) {
                            if (i8 >= length3) {
                                i2 = i8;
                                break;
                            }
                            char charAt2 = str2.charAt(i8);
                            i2 = i8;
                            int i9 = 0;
                            while (i9 < 13 && isDigit(charAt2) && i2 < length3) {
                                i9++;
                                i2++;
                                if (i2 < length3) {
                                    charAt2 = str2.charAt(i2);
                                }
                            }
                            if (i9 >= 13) {
                                i3 = (i2 - i4) - i9;
                                break;
                            } else if (i9 > 0) {
                                i8 = i2;
                            } else if (!isText(str2.charAt(i2))) {
                                break;
                            } else {
                                i8 = i2 + 1;
                            }
                        }
                        i3 = i2 - i4;
                        if (i3 >= 5 || i == length) {
                            if (i6 != 0) {
                                sb.append(900);
                                i5 = 0;
                                i6 = 0;
                            }
                            int encodeText = encodeText(str2, i4, i3, sb, i5);
                            i4 += i3;
                            i5 = encodeText;
                        } else {
                            if (bArr == null) {
                                bArr = str.getBytes();
                            }
                            int length4 = str.length();
                            int i10 = i4;
                            while (i10 < length4) {
                                char charAt3 = str2.charAt(i10);
                                int i11 = 0;
                                while (i11 < 13 && isDigit(charAt3)) {
                                    i11++;
                                    int i12 = i10 + i11;
                                    if (i12 >= length4) {
                                        break;
                                    }
                                    charAt3 = str2.charAt(i12);
                                }
                                if (i11 >= 13) {
                                    break;
                                }
                                int i13 = 0;
                                while (i13 < 5 && isText(charAt3)) {
                                    i13++;
                                    int i14 = i10 + i13;
                                    if (i14 >= length4) {
                                        break;
                                    }
                                    charAt3 = str2.charAt(i14);
                                }
                                if (i13 >= 5) {
                                    break;
                                }
                                char charAt4 = str2.charAt(i10);
                                if (bArr[i10] != 63 || charAt4 == '?') {
                                    i10++;
                                } else {
                                    throw new WriterException("Non-encodable character detected: " + charAt4 + " (Unicode: " + charAt4 + ')');
                                }
                            }
                            int i15 = i10 - i4;
                            if (i15 == 0) {
                                i15 = 1;
                            }
                            if (i15 == 1 && i6 == 0) {
                                encodeBinary(bArr, i4, 1, 0, sb);
                            } else {
                                encodeBinary(bArr, i4, i15, i6, sb);
                                i5 = 0;
                                i6 = 1;
                            }
                            i4 += i15;
                        }
                    }
                    c = 902;
                }
            }
        }
        return sb.toString();
    }

    private static void encodeNumeric(String str, int i, int i2, StringBuilder sb) {
        StringBuilder sb2 = new StringBuilder((i2 / 3) + 1);
        BigInteger valueOf = BigInteger.valueOf(900);
        BigInteger valueOf2 = BigInteger.valueOf(0);
        int i3 = 0;
        while (i3 < i2 - 1) {
            sb2.setLength(0);
            int min = Math.min(44, i2 - i3);
            StringBuilder sb3 = new StringBuilder();
            sb3.append('1');
            int i4 = i + i3;
            sb3.append(str.substring(i4, i4 + min));
            BigInteger bigInteger = new BigInteger(sb3.toString());
            do {
                sb2.append((char) bigInteger.mod(valueOf).intValue());
                bigInteger = bigInteger.divide(valueOf);
            } while (!bigInteger.equals(valueOf2));
            for (int length = sb2.length() - 1; length >= 0; length--) {
                sb.append(sb2.charAt(length));
            }
            i3 += min;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:75:0x0101 A[EDGE_INSN: B:75:0x0101->B:60:0x0101 ?: BREAK  , SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:80:0x0011 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static int encodeText(java.lang.CharSequence r16, int r17, int r18, java.lang.StringBuilder r19, int r20) {
        /*
            r0 = r16
            r1 = r18
            r2 = r19
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>(r1)
            r4 = 2
            r5 = 0
            r6 = 1
            r8 = r20
            r7 = r5
        L_0x0011:
            int r9 = r17 + r7
            char r10 = r0.charAt(r9)
            r11 = 26
            r12 = 32
            r13 = 28
            r14 = 27
            r15 = 29
            if (r8 == 0) goto L_0x00c7
            if (r8 == r6) goto L_0x008e
            r11 = -1
            if (r8 == r4) goto L_0x0041
            byte[] r9 = PUNCTUATION
            byte r9 = r9[r10]
            if (r9 == r11) goto L_0x0030
            r9 = r6
            goto L_0x0031
        L_0x0030:
            r9 = r5
        L_0x0031:
            if (r9 == 0) goto L_0x003d
            byte[] r9 = PUNCTUATION
            byte r9 = r9[r10]
            char r9 = (char) r9
            r3.append(r9)
            goto L_0x00fd
        L_0x003d:
            r3.append(r15)
            goto L_0x005a
        L_0x0041:
            boolean r12 = isMixed(r10)
            if (r12 == 0) goto L_0x0051
            byte[] r9 = MIXED
            byte r9 = r9[r10]
            char r9 = (char) r9
            r3.append(r9)
            goto L_0x00fd
        L_0x0051:
            boolean r12 = isAlphaUpper(r10)
            if (r12 == 0) goto L_0x005c
            r3.append(r13)
        L_0x005a:
            r8 = r5
            goto L_0x0011
        L_0x005c:
            boolean r12 = isAlphaLower(r10)
            if (r12 == 0) goto L_0x0067
            r3.append(r14)
            goto L_0x00e3
        L_0x0067:
            int r9 = r9 + 1
            if (r9 >= r1) goto L_0x0081
            char r9 = r0.charAt(r9)
            byte[] r12 = PUNCTUATION
            byte r9 = r12[r9]
            if (r9 == r11) goto L_0x0077
            r9 = r6
            goto L_0x0078
        L_0x0077:
            r9 = r5
        L_0x0078:
            if (r9 == 0) goto L_0x0081
            r8 = 3
            r9 = 25
            r3.append(r9)
            goto L_0x0011
        L_0x0081:
            r3.append(r15)
            byte[] r9 = PUNCTUATION
            byte r9 = r9[r10]
            char r9 = (char) r9
            r3.append(r9)
            goto L_0x00fd
        L_0x008e:
            boolean r9 = isAlphaLower(r10)
            if (r9 == 0) goto L_0x00a1
            if (r10 != r12) goto L_0x009a
            r3.append(r11)
            goto L_0x00fd
        L_0x009a:
            int r10 = r10 + -97
            char r9 = (char) r10
            r3.append(r9)
            goto L_0x00fd
        L_0x00a1:
            boolean r9 = isAlphaUpper(r10)
            if (r9 == 0) goto L_0x00b1
            r3.append(r14)
            int r10 = r10 + -65
            char r9 = (char) r10
            r3.append(r9)
            goto L_0x00fd
        L_0x00b1:
            boolean r9 = isMixed(r10)
            if (r9 == 0) goto L_0x00bb
            r3.append(r13)
            goto L_0x00ef
        L_0x00bb:
            r3.append(r15)
            byte[] r9 = PUNCTUATION
            byte r9 = r9[r10]
            char r9 = (char) r9
            r3.append(r9)
            goto L_0x00fd
        L_0x00c7:
            boolean r9 = isAlphaUpper(r10)
            if (r9 == 0) goto L_0x00da
            if (r10 != r12) goto L_0x00d3
            r3.append(r11)
            goto L_0x00fd
        L_0x00d3:
            int r10 = r10 + -65
            char r9 = (char) r10
            r3.append(r9)
            goto L_0x00fd
        L_0x00da:
            boolean r9 = isAlphaLower(r10)
            if (r9 == 0) goto L_0x00e6
            r3.append(r14)
        L_0x00e3:
            r8 = r6
            goto L_0x0011
        L_0x00e6:
            boolean r9 = isMixed(r10)
            if (r9 == 0) goto L_0x00f2
            r3.append(r13)
        L_0x00ef:
            r8 = r4
            goto L_0x0011
        L_0x00f2:
            r3.append(r15)
            byte[] r9 = PUNCTUATION
            byte r9 = r9[r10]
            char r9 = (char) r9
            r3.append(r9)
        L_0x00fd:
            int r7 = r7 + 1
            if (r7 < r1) goto L_0x0011
            int r0 = r3.length()
            r1 = r5
            r7 = r1
        L_0x0107:
            if (r1 >= r0) goto L_0x0125
            int r9 = r1 % 2
            if (r9 == 0) goto L_0x010f
            r9 = r6
            goto L_0x0110
        L_0x010f:
            r9 = r5
        L_0x0110:
            if (r9 == 0) goto L_0x011e
            int r7 = r7 * 30
            char r9 = r3.charAt(r1)
            int r9 = r9 + r7
            char r7 = (char) r9
            r2.append(r7)
            goto L_0x0122
        L_0x011e:
            char r7 = r3.charAt(r1)
        L_0x0122:
            int r1 = r1 + 1
            goto L_0x0107
        L_0x0125:
            int r0 = r0 % r4
            if (r0 == 0) goto L_0x012f
            int r7 = r7 * 30
            int r7 = r7 + r15
            char r0 = (char) r7
            r2.append(r0)
        L_0x012f:
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.pdf417.encoder.PDF417HighLevelEncoder.encodeText(java.lang.CharSequence, int, int, java.lang.StringBuilder, int):int");
    }

    private static boolean isAlphaLower(char c) {
        return c == ' ' || (c >= 'a' && c <= 'z');
    }

    private static boolean isAlphaUpper(char c) {
        return c == ' ' || (c >= 'A' && c <= 'Z');
    }

    private static boolean isDigit(char c) {
        return c >= '0' && c <= '9';
    }

    private static boolean isMixed(char c) {
        return MIXED[c] != -1;
    }

    private static boolean isText(char c) {
        return c == 9 || c == 10 || c == 13 || (c >= ' ' && c <= '~');
    }
}
