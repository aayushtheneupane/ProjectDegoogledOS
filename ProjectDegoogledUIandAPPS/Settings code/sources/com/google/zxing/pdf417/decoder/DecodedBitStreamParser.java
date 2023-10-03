package com.google.zxing.pdf417.decoder;

import com.google.zxing.FormatException;
import com.google.zxing.common.DecoderResult;
import com.google.zxing.pdf417.PDF417ResultMetadata;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;

final class DecodedBitStreamParser {
    private static final BigInteger[] EXP900 = new BigInteger[16];
    private static final char[] MIXED_CHARS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '&', 13, 9, ',', ':', '#', '-', '.', '$', '/', '+', '%', '*', '=', '^'};
    private static final char[] PUNCT_CHARS = {';', '<', '>', '@', '[', '\\', '}', '_', '`', '~', '!', 13, 9, ',', ':', 10, '-', '.', '$', '/', '\"', '|', '*', '(', ')', '?', '{', '}', '\''};

    private enum Mode {
        ALPHA,
        LOWER,
        MIXED,
        PUNCT,
        ALPHA_SHIFT,
        PUNCT_SHIFT
    }

    static {
        EXP900[0] = BigInteger.ONE;
        BigInteger valueOf = BigInteger.valueOf(900);
        EXP900[1] = valueOf;
        int i = 2;
        while (true) {
            BigInteger[] bigIntegerArr = EXP900;
            if (i < bigIntegerArr.length) {
                bigIntegerArr[i] = bigIntegerArr[i - 1].multiply(valueOf);
                i++;
            } else {
                return;
            }
        }
    }

    static DecoderResult decode(int[] iArr, String str) throws FormatException {
        int i;
        int i2 = 2;
        StringBuilder sb = new StringBuilder(iArr.length * 2);
        int i3 = iArr[1];
        PDF417ResultMetadata pDF417ResultMetadata = new PDF417ResultMetadata();
        while (i2 < iArr[0]) {
            if (i3 == 913) {
                i = byteCompaction(i3, iArr, i2, sb);
            } else if (i3 != 928) {
                switch (i3) {
                    case 900:
                        i = textCompaction(iArr, i2, sb);
                        break;
                    case 901:
                        i = byteCompaction(i3, iArr, i2, sb);
                        break;
                    case 902:
                        i = numericCompaction(iArr, i2, sb);
                        break;
                    default:
                        switch (i3) {
                            case 922:
                            case 923:
                                throw FormatException.getFormatInstance();
                            case 924:
                                i = byteCompaction(i3, iArr, i2, sb);
                                break;
                            default:
                                i = textCompaction(iArr, i2 - 1, sb);
                                break;
                        }
                }
            } else {
                i = decodeMacroBlock(iArr, i2, pDF417ResultMetadata);
            }
            if (i < iArr.length) {
                i2 = i + 1;
                i3 = iArr[i];
            } else {
                throw FormatException.getFormatInstance();
            }
        }
        if (sb.length() != 0) {
            DecoderResult decoderResult = new DecoderResult((byte[]) null, sb.toString(), (List<byte[]>) null, str);
            decoderResult.setOther(pDF417ResultMetadata);
            return decoderResult;
        }
        throw FormatException.getFormatInstance();
    }

    private static int decodeMacroBlock(int[] iArr, int i, PDF417ResultMetadata pDF417ResultMetadata) throws FormatException {
        if (i + 2 <= iArr[0]) {
            int[] iArr2 = new int[2];
            int i2 = i;
            int i3 = 0;
            while (i3 < 2) {
                iArr2[i3] = iArr[i2];
                i3++;
                i2++;
            }
            pDF417ResultMetadata.setSegmentIndex(Integer.parseInt(decodeBase900toBase10(iArr2, 2)));
            StringBuilder sb = new StringBuilder();
            int textCompaction = textCompaction(iArr, i2, sb);
            pDF417ResultMetadata.setFileId(sb.toString());
            if (iArr[textCompaction] == 923) {
                int i4 = textCompaction + 1;
                int[] iArr3 = new int[(iArr[0] - i4)];
                boolean z = false;
                int i5 = 0;
                while (i4 < iArr[0] && !z) {
                    int i6 = i4 + 1;
                    int i7 = iArr[i4];
                    if (i7 < 900) {
                        iArr3[i5] = i7;
                        i4 = i6;
                        i5++;
                    } else if (i7 == 922) {
                        pDF417ResultMetadata.setLastSegment(true);
                        z = true;
                        i4 = i6 + 1;
                    } else {
                        throw FormatException.getFormatInstance();
                    }
                }
                pDF417ResultMetadata.setOptionalData(Arrays.copyOf(iArr3, i5));
                return i4;
            } else if (iArr[textCompaction] != 922) {
                return textCompaction;
            } else {
                pDF417ResultMetadata.setLastSegment(true);
                return textCompaction + 1;
            }
        } else {
            throw FormatException.getFormatInstance();
        }
    }

    private static int textCompaction(int[] iArr, int i, StringBuilder sb) {
        int[] iArr2 = new int[((iArr[0] - i) << 1)];
        int[] iArr3 = new int[((iArr[0] - i) << 1)];
        boolean z = false;
        int i2 = 0;
        while (i < iArr[0] && !z) {
            int i3 = i + 1;
            int i4 = iArr[i];
            if (i4 < 900) {
                iArr2[i2] = i4 / 30;
                iArr2[i2 + 1] = i4 % 30;
                i2 += 2;
            } else if (i4 != 913) {
                if (i4 != 928) {
                    switch (i4) {
                        case 900:
                            iArr2[i2] = 900;
                            i2++;
                            break;
                        case 901:
                        case 902:
                            break;
                        default:
                            switch (i4) {
                                case 922:
                                case 923:
                                case 924:
                                    break;
                            }
                    }
                }
                i3--;
                z = true;
            } else {
                iArr2[i2] = 913;
                i = i3 + 1;
                iArr3[i2] = iArr[i3];
                i2++;
            }
            i = i3;
            continue;
        }
        decodeTextCompaction(iArr2, iArr3, i2, sb);
        return i;
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x004a, code lost:
        r6 = r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x004b, code lost:
        r4 = r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0059, code lost:
        r6 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x00ba, code lost:
        r6 = 0;
        r15 = r5;
        r5 = r4;
        r4 = r15;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x00db, code lost:
        r6 = (char) r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:0x00df, code lost:
        r6 = ' ';
     */
    /* JADX WARNING: Code restructure failed: missing block: B:75:0x00fe, code lost:
        if (r6 == 0) goto L_0x0103;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:76:0x0100, code lost:
        r0.append(r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:77:0x0103, code lost:
        r3 = r3 + 1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void decodeTextCompaction(int[] r16, int[] r17, int r18, java.lang.StringBuilder r19) {
        /*
            r0 = r19
            com.google.zxing.pdf417.decoder.DecodedBitStreamParser$Mode r1 = com.google.zxing.pdf417.decoder.DecodedBitStreamParser.Mode.ALPHA
            r2 = 0
            r4 = r1
            r5 = r4
            r3 = r2
            r1 = r18
        L_0x000a:
            if (r3 >= r1) goto L_0x0107
            r6 = r16[r3]
            int[] r7 = com.google.zxing.pdf417.decoder.DecodedBitStreamParser.C17111.f81x45bba1d
            int r8 = r4.ordinal()
            r7 = r7[r8]
            r8 = 28
            r9 = 27
            r10 = 32
            r11 = 913(0x391, float:1.28E-42)
            r12 = 900(0x384, float:1.261E-42)
            r13 = 29
            r14 = 26
            switch(r7) {
                case 1: goto L_0x00d7;
                case 2: goto L_0x00ae;
                case 3: goto L_0x0079;
                case 4: goto L_0x005b;
                case 5: goto L_0x0045;
                case 6: goto L_0x0029;
                default: goto L_0x0027;
            }
        L_0x0027:
            goto L_0x00fd
        L_0x0029:
            if (r6 >= r13) goto L_0x0030
            char[] r4 = PUNCT_CHARS
            char r4 = r4[r6]
            goto L_0x004a
        L_0x0030:
            if (r6 != r13) goto L_0x0036
            com.google.zxing.pdf417.decoder.DecodedBitStreamParser$Mode r4 = com.google.zxing.pdf417.decoder.DecodedBitStreamParser.Mode.ALPHA
            goto L_0x00fd
        L_0x0036:
            if (r6 != r11) goto L_0x003f
            r4 = r17[r3]
            char r4 = (char) r4
            r0.append(r4)
            goto L_0x0059
        L_0x003f:
            if (r6 != r12) goto L_0x0059
            com.google.zxing.pdf417.decoder.DecodedBitStreamParser$Mode r4 = com.google.zxing.pdf417.decoder.DecodedBitStreamParser.Mode.ALPHA
            goto L_0x00fd
        L_0x0045:
            if (r6 >= r14) goto L_0x004e
            int r6 = r6 + 65
            char r4 = (char) r6
        L_0x004a:
            r6 = r4
        L_0x004b:
            r4 = r5
            goto L_0x00fe
        L_0x004e:
            if (r6 != r14) goto L_0x0053
            r4 = r5
            goto L_0x00df
        L_0x0053:
            if (r6 != r12) goto L_0x0059
            com.google.zxing.pdf417.decoder.DecodedBitStreamParser$Mode r4 = com.google.zxing.pdf417.decoder.DecodedBitStreamParser.Mode.ALPHA
            goto L_0x00fd
        L_0x0059:
            r6 = r2
            goto L_0x004b
        L_0x005b:
            if (r6 >= r13) goto L_0x0063
            char[] r7 = PUNCT_CHARS
            char r6 = r7[r6]
            goto L_0x00fe
        L_0x0063:
            if (r6 != r13) goto L_0x0069
            com.google.zxing.pdf417.decoder.DecodedBitStreamParser$Mode r4 = com.google.zxing.pdf417.decoder.DecodedBitStreamParser.Mode.ALPHA
            goto L_0x00fd
        L_0x0069:
            if (r6 != r11) goto L_0x0073
            r6 = r17[r3]
            char r6 = (char) r6
            r0.append(r6)
            goto L_0x00fd
        L_0x0073:
            if (r6 != r12) goto L_0x00fd
            com.google.zxing.pdf417.decoder.DecodedBitStreamParser$Mode r4 = com.google.zxing.pdf417.decoder.DecodedBitStreamParser.Mode.ALPHA
            goto L_0x00fd
        L_0x0079:
            r7 = 25
            if (r6 >= r7) goto L_0x0083
            char[] r7 = MIXED_CHARS
            char r6 = r7[r6]
            goto L_0x00fe
        L_0x0083:
            if (r6 != r7) goto L_0x0089
            com.google.zxing.pdf417.decoder.DecodedBitStreamParser$Mode r4 = com.google.zxing.pdf417.decoder.DecodedBitStreamParser.Mode.PUNCT
            goto L_0x00fd
        L_0x0089:
            if (r6 != r14) goto L_0x008d
            goto L_0x00df
        L_0x008d:
            if (r6 != r9) goto L_0x0093
            com.google.zxing.pdf417.decoder.DecodedBitStreamParser$Mode r4 = com.google.zxing.pdf417.decoder.DecodedBitStreamParser.Mode.LOWER
            goto L_0x00fd
        L_0x0093:
            if (r6 != r8) goto L_0x0099
            com.google.zxing.pdf417.decoder.DecodedBitStreamParser$Mode r4 = com.google.zxing.pdf417.decoder.DecodedBitStreamParser.Mode.ALPHA
            goto L_0x00fd
        L_0x0099:
            if (r6 != r13) goto L_0x009e
            com.google.zxing.pdf417.decoder.DecodedBitStreamParser$Mode r5 = com.google.zxing.pdf417.decoder.DecodedBitStreamParser.Mode.PUNCT_SHIFT
            goto L_0x00ba
        L_0x009e:
            if (r6 != r11) goto L_0x00a8
            r6 = r17[r3]
            char r6 = (char) r6
            r0.append(r6)
            goto L_0x00fd
        L_0x00a8:
            if (r6 != r12) goto L_0x00fd
            com.google.zxing.pdf417.decoder.DecodedBitStreamParser$Mode r4 = com.google.zxing.pdf417.decoder.DecodedBitStreamParser.Mode.ALPHA
            goto L_0x00fd
        L_0x00ae:
            if (r6 >= r14) goto L_0x00b3
            int r6 = r6 + 97
            goto L_0x00db
        L_0x00b3:
            if (r6 != r14) goto L_0x00b6
            goto L_0x00df
        L_0x00b6:
            if (r6 != r9) goto L_0x00bf
            com.google.zxing.pdf417.decoder.DecodedBitStreamParser$Mode r5 = com.google.zxing.pdf417.decoder.DecodedBitStreamParser.Mode.ALPHA_SHIFT
        L_0x00ba:
            r6 = r2
            r15 = r5
            r5 = r4
            r4 = r15
            goto L_0x00fe
        L_0x00bf:
            if (r6 != r8) goto L_0x00c4
            com.google.zxing.pdf417.decoder.DecodedBitStreamParser$Mode r4 = com.google.zxing.pdf417.decoder.DecodedBitStreamParser.Mode.MIXED
            goto L_0x00fd
        L_0x00c4:
            if (r6 != r13) goto L_0x00c9
            com.google.zxing.pdf417.decoder.DecodedBitStreamParser$Mode r5 = com.google.zxing.pdf417.decoder.DecodedBitStreamParser.Mode.PUNCT_SHIFT
            goto L_0x00ba
        L_0x00c9:
            if (r6 != r11) goto L_0x00d2
            r6 = r17[r3]
            char r6 = (char) r6
            r0.append(r6)
            goto L_0x00fd
        L_0x00d2:
            if (r6 != r12) goto L_0x00fd
            com.google.zxing.pdf417.decoder.DecodedBitStreamParser$Mode r4 = com.google.zxing.pdf417.decoder.DecodedBitStreamParser.Mode.ALPHA
            goto L_0x00fd
        L_0x00d7:
            if (r6 >= r14) goto L_0x00dd
            int r6 = r6 + 65
        L_0x00db:
            char r6 = (char) r6
            goto L_0x00fe
        L_0x00dd:
            if (r6 != r14) goto L_0x00e1
        L_0x00df:
            r6 = r10
            goto L_0x00fe
        L_0x00e1:
            if (r6 != r9) goto L_0x00e6
            com.google.zxing.pdf417.decoder.DecodedBitStreamParser$Mode r4 = com.google.zxing.pdf417.decoder.DecodedBitStreamParser.Mode.LOWER
            goto L_0x00fd
        L_0x00e6:
            if (r6 != r8) goto L_0x00eb
            com.google.zxing.pdf417.decoder.DecodedBitStreamParser$Mode r4 = com.google.zxing.pdf417.decoder.DecodedBitStreamParser.Mode.MIXED
            goto L_0x00fd
        L_0x00eb:
            if (r6 != r13) goto L_0x00f0
            com.google.zxing.pdf417.decoder.DecodedBitStreamParser$Mode r5 = com.google.zxing.pdf417.decoder.DecodedBitStreamParser.Mode.PUNCT_SHIFT
            goto L_0x00ba
        L_0x00f0:
            if (r6 != r11) goto L_0x00f9
            r6 = r17[r3]
            char r6 = (char) r6
            r0.append(r6)
            goto L_0x00fd
        L_0x00f9:
            if (r6 != r12) goto L_0x00fd
            com.google.zxing.pdf417.decoder.DecodedBitStreamParser$Mode r4 = com.google.zxing.pdf417.decoder.DecodedBitStreamParser.Mode.ALPHA
        L_0x00fd:
            r6 = r2
        L_0x00fe:
            if (r6 == 0) goto L_0x0103
            r0.append(r6)
        L_0x0103:
            int r3 = r3 + 1
            goto L_0x000a
        L_0x0107:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.pdf417.decoder.DecodedBitStreamParser.decodeTextCompaction(int[], int[], int, java.lang.StringBuilder):void");
    }

    /* renamed from: com.google.zxing.pdf417.decoder.DecodedBitStreamParser$1 */
    static /* synthetic */ class C17111 {

        /* renamed from: $SwitchMap$com$google$zxing$pdf417$decoder$DecodedBitStreamParser$Mode */
        static final /* synthetic */ int[] f81x45bba1d = new int[Mode.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(12:0|1|2|3|4|5|6|7|8|9|10|(3:11|12|14)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x0040 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001f */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x002a */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0035 */
        static {
            /*
                com.google.zxing.pdf417.decoder.DecodedBitStreamParser$Mode[] r0 = com.google.zxing.pdf417.decoder.DecodedBitStreamParser.Mode.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                f81x45bba1d = r0
                int[] r0 = f81x45bba1d     // Catch:{ NoSuchFieldError -> 0x0014 }
                com.google.zxing.pdf417.decoder.DecodedBitStreamParser$Mode r1 = com.google.zxing.pdf417.decoder.DecodedBitStreamParser.Mode.ALPHA     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r0 = f81x45bba1d     // Catch:{ NoSuchFieldError -> 0x001f }
                com.google.zxing.pdf417.decoder.DecodedBitStreamParser$Mode r1 = com.google.zxing.pdf417.decoder.DecodedBitStreamParser.Mode.LOWER     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                int[] r0 = f81x45bba1d     // Catch:{ NoSuchFieldError -> 0x002a }
                com.google.zxing.pdf417.decoder.DecodedBitStreamParser$Mode r1 = com.google.zxing.pdf417.decoder.DecodedBitStreamParser.Mode.MIXED     // Catch:{ NoSuchFieldError -> 0x002a }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002a }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002a }
            L_0x002a:
                int[] r0 = f81x45bba1d     // Catch:{ NoSuchFieldError -> 0x0035 }
                com.google.zxing.pdf417.decoder.DecodedBitStreamParser$Mode r1 = com.google.zxing.pdf417.decoder.DecodedBitStreamParser.Mode.PUNCT     // Catch:{ NoSuchFieldError -> 0x0035 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0035 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0035 }
            L_0x0035:
                int[] r0 = f81x45bba1d     // Catch:{ NoSuchFieldError -> 0x0040 }
                com.google.zxing.pdf417.decoder.DecodedBitStreamParser$Mode r1 = com.google.zxing.pdf417.decoder.DecodedBitStreamParser.Mode.ALPHA_SHIFT     // Catch:{ NoSuchFieldError -> 0x0040 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0040 }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0040 }
            L_0x0040:
                int[] r0 = f81x45bba1d     // Catch:{ NoSuchFieldError -> 0x004b }
                com.google.zxing.pdf417.decoder.DecodedBitStreamParser$Mode r1 = com.google.zxing.pdf417.decoder.DecodedBitStreamParser.Mode.PUNCT_SHIFT     // Catch:{ NoSuchFieldError -> 0x004b }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x004b }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x004b }
            L_0x004b:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.pdf417.decoder.DecodedBitStreamParser.C17111.<clinit>():void");
        }
    }

    private static int byteCompaction(int i, int[] iArr, int i2, StringBuilder sb) {
        int i3;
        int i4 = i;
        StringBuilder sb2 = sb;
        int i5 = 922;
        int i6 = 923;
        long j = 900;
        int i7 = 6;
        if (i4 == 901) {
            char[] cArr = new char[6];
            int[] iArr2 = new int[6];
            int i8 = i2 + 1;
            boolean z = false;
            int i9 = 0;
            int i10 = iArr[i2];
            long j2 = 0;
            while (i8 < iArr[0] && !z) {
                int i11 = i9 + 1;
                iArr2[i9] = i10;
                j2 = (j2 * j) + ((long) i10);
                int i12 = i8 + 1;
                i10 = iArr[i8];
                if (i10 == 900 || i10 == 901 || i10 == 902 || i10 == 924 || i10 == 928 || i10 == 923 || i10 == 922) {
                    i8 = i12 - 1;
                    i10 = i10;
                    i9 = i11;
                    j = 900;
                    i7 = 6;
                    z = true;
                } else {
                    if (i11 % 5 != 0 || i11 <= 0) {
                        i10 = i10;
                        i9 = i11;
                        i8 = i12;
                    } else {
                        int i13 = 0;
                        while (i13 < i7) {
                            cArr[5 - i13] = (char) ((int) (j2 % 256));
                            j2 >>= 8;
                            i13++;
                            i10 = i10;
                            i7 = 6;
                        }
                        int i14 = i10;
                        sb2.append(cArr);
                        i8 = i12;
                        i9 = 0;
                    }
                    j = 900;
                    i7 = 6;
                }
            }
            if (i8 != iArr[0] || i10 >= 900) {
                i3 = i9;
            } else {
                i3 = i9 + 1;
                iArr2[i9] = i10;
            }
            for (int i15 = 0; i15 < i3; i15++) {
                sb2.append((char) iArr2[i15]);
            }
            return i8;
        } else if (i4 != 924) {
            return i2;
        } else {
            int i16 = i2;
            int i17 = 0;
            boolean z2 = false;
            long j3 = 0;
            while (i16 < iArr[0] && !z2) {
                int i18 = i16 + 1;
                int i19 = iArr[i16];
                if (i19 < 900) {
                    i17++;
                    j3 = (j3 * 900) + ((long) i19);
                } else if (i19 == 900 || i19 == 901 || i19 == 902 || i19 == 924 || i19 == 928 || i19 == i6 || i19 == i5) {
                    i16 = i18 - 1;
                    z2 = true;
                    if (i17 % 5 != 0 && i17 > 0) {
                        char[] cArr2 = new char[6];
                        for (int i20 = 0; i20 < 6; i20++) {
                            cArr2[5 - i20] = (char) ((int) (j3 & 255));
                            j3 >>= 8;
                        }
                        sb2.append(cArr2);
                        i17 = 0;
                    }
                    i5 = 922;
                    i6 = 923;
                }
                i16 = i18;
                if (i17 % 5 != 0) {
                }
                i5 = 922;
                i6 = 923;
            }
            return i16;
        }
    }

    private static int numericCompaction(int[] iArr, int i, StringBuilder sb) throws FormatException {
        int[] iArr2 = new int[15];
        boolean z = false;
        int i2 = 0;
        while (i < iArr[0] && !z) {
            int i3 = i + 1;
            int i4 = iArr[i];
            if (i3 == iArr[0]) {
                z = true;
            }
            if (i4 < 900) {
                iArr2[i2] = i4;
                i2++;
            } else if (i4 == 900 || i4 == 901 || i4 == 924 || i4 == 928 || i4 == 923 || i4 == 922) {
                i3--;
                z = true;
            }
            if (i2 % 15 == 0 || i4 == 902 || z) {
                sb.append(decodeBase900toBase10(iArr2, i2));
                i2 = 0;
            }
            i = i3;
        }
        return i;
    }

    private static String decodeBase900toBase10(int[] iArr, int i) throws FormatException {
        BigInteger bigInteger = BigInteger.ZERO;
        for (int i2 = 0; i2 < i; i2++) {
            bigInteger = bigInteger.add(EXP900[(i - i2) - 1].multiply(BigInteger.valueOf((long) iArr[i2])));
        }
        String bigInteger2 = bigInteger.toString();
        if (bigInteger2.charAt(0) == '1') {
            return bigInteger2.substring(1);
        }
        throw FormatException.getFormatInstance();
    }
}
