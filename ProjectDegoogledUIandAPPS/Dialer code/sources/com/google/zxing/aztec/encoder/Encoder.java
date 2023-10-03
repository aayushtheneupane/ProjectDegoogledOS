package com.google.zxing.aztec.encoder;

import com.google.zxing.common.BitArray;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.reedsolomon.GenericGF;
import com.google.zxing.common.reedsolomon.ReedSolomonEncoder;

public final class Encoder {
    private static final int[] WORD_SIZE = {4, 6, 6, 8, 8, 8, 8, 8, 8, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12};

    private static void drawBullsEye(BitMatrix bitMatrix, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3 += 2) {
            int i4 = i - i3;
            int i5 = i4;
            while (true) {
                int i6 = i + i3;
                if (i5 > i6) {
                    break;
                }
                bitMatrix.set(i5, i4);
                bitMatrix.set(i5, i6);
                bitMatrix.set(i4, i5);
                bitMatrix.set(i6, i5);
                i5++;
            }
        }
        int i7 = i - i2;
        bitMatrix.set(i7, i7);
        int i8 = i7 + 1;
        bitMatrix.set(i8, i7);
        bitMatrix.set(i7, i8);
        int i9 = i + i2;
        bitMatrix.set(i9, i7);
        bitMatrix.set(i9, i8);
        bitMatrix.set(i9, i9 - 1);
    }

    public static AztecCode encode(byte[] bArr, int i, int i2) {
        int i3;
        int i4;
        int i5;
        int i6;
        BitArray bitArray;
        BitArray bitArray2;
        int i7;
        int i8;
        BitArray bitArray3;
        BitArray encode = new HighLevelEncoder(bArr).encode();
        int size = ((encode.getSize() * i) / 100) + 11;
        int size2 = encode.getSize() + size;
        int i9 = 32;
        int i10 = 4;
        int i11 = 0;
        int i12 = 1;
        if (i2 != 0) {
            int i13 = i2 < 0 ? 1 : 0;
            int abs = Math.abs(i2);
            if (i13 != 0) {
                i9 = 4;
            }
            if (abs <= i9) {
                int i14 = ((abs * 16) + (i13 != 0 ? 88 : 112)) * abs;
                i6 = WORD_SIZE[abs];
                int i15 = i14 - (i14 % i6);
                bitArray = stuffBits(encode, i6);
                if (bitArray.getSize() + size > i15) {
                    throw new IllegalArgumentException("Data to large for user specified layer");
                } else if (i13 == 0 || bitArray.getSize() <= i6 * 64) {
                    i5 = i13;
                    i3 = i14;
                    i4 = abs;
                } else {
                    throw new IllegalArgumentException("Data to large for user specified layer");
                }
            } else {
                throw new IllegalArgumentException(String.format("Illegal value %s for layers", new Object[]{Integer.valueOf(i2)}));
            }
        } else {
            int i16 = 0;
            BitArray bitArray4 = null;
            int i17 = 0;
            while (i17 <= i9) {
                i5 = i17 <= 3 ? i12 : i11;
                i4 = i5 != 0 ? i17 + 1 : i17;
                i3 = ((i4 * 16) + (i5 != 0 ? 88 : 112)) * i4;
                if (size2 > i3) {
                    i8 = i12;
                } else {
                    int[] iArr = WORD_SIZE;
                    if (i16 != iArr[i4]) {
                        i6 = iArr[i4];
                        bitArray3 = stuffBits(encode, i6);
                    } else {
                        i6 = i16;
                        bitArray3 = bitArray4;
                    }
                    int i18 = i3 - (i3 % i6);
                    if ((i5 == 0 || bitArray3.getSize() <= i6 * 64) && bitArray3.getSize() + size <= i18) {
                        bitArray = bitArray3;
                    } else {
                        i8 = i12;
                        bitArray4 = bitArray3;
                        i16 = i6;
                    }
                }
                i17++;
                i12 = i8;
                i9 = 32;
                i10 = 4;
                i11 = 0;
            }
            throw new IllegalArgumentException("Data too large for an Aztec code");
        }
        BitArray generateCheckWords = generateCheckWords(bitArray, i3, i6);
        int size3 = bitArray.getSize() / i6;
        BitArray bitArray5 = new BitArray();
        char c = 2;
        if (i5 != 0) {
            bitArray5.appendBits(i4 - 1, 2);
            bitArray5.appendBits(size3 - 1, 6);
            bitArray2 = generateCheckWords(bitArray5, 28, i10);
        } else {
            bitArray5.appendBits(i4 - 1, 5);
            bitArray5.appendBits(size3 - 1, 11);
            bitArray2 = generateCheckWords(bitArray5, 40, i10);
        }
        int i19 = i5 != 0 ? (i4 * 4) + 11 : (i4 * 4) + 14;
        int[] iArr2 = new int[i19];
        if (i5 != 0) {
            for (int i20 = i11; i20 < iArr2.length; i20++) {
                iArr2[i20] = i20;
            }
            i7 = i19;
        } else {
            int i21 = i19 / 2;
            i7 = i19 + 1 + (((i21 - 1) / 15) * 2);
            int i22 = i7 / 2;
            for (int i23 = i11; i23 < i21; i23++) {
                int i24 = (i23 / 15) + i23;
                iArr2[(i21 - i23) - 1] = (i22 - i24) - 1;
                iArr2[i21 + i23] = i24 + i22 + i12;
            }
        }
        BitMatrix bitMatrix = new BitMatrix(i7, i7);
        int i25 = i11;
        int i26 = i25;
        while (i25 < i4) {
            int i27 = (i4 - i25) * i10;
            int i28 = i5 != 0 ? i27 + 9 : i27 + 12;
            int i29 = i11;
            while (i29 < i28) {
                int i30 = i29 * 2;
                while (i11 < c) {
                    if (generateCheckWords.get(i26 + i30 + i11)) {
                        int i31 = i25 * 2;
                        bitMatrix.set(iArr2[i31 + i11], iArr2[i31 + i29]);
                    }
                    if (generateCheckWords.get((i28 * 2) + i26 + i30 + i11)) {
                        int i32 = i25 * 2;
                        bitMatrix.set(iArr2[i32 + i29], iArr2[((i19 - 1) - i32) - i11]);
                    }
                    if (generateCheckWords.get((i28 * 4) + i26 + i30 + i11)) {
                        int i33 = (i19 - 1) - (i25 * 2);
                        bitMatrix.set(iArr2[i33 - i11], iArr2[i33 - i29]);
                    }
                    if (generateCheckWords.get((i28 * 6) + i26 + i30 + i11)) {
                        int i34 = i25 * 2;
                        bitMatrix.set(iArr2[((i19 - 1) - i34) - i29], iArr2[i34 + i11]);
                    }
                    i11++;
                    c = 2;
                }
                i29++;
                c = 2;
                i11 = 0;
            }
            i26 += i28 * 8;
            i25++;
            c = 2;
            i10 = 4;
            i11 = 0;
        }
        int i35 = i7 / 2;
        int i36 = 0;
        if (i5 != 0) {
            while (i36 < 7) {
                int i37 = (i35 - 3) + i36;
                if (bitArray2.get(i36)) {
                    bitMatrix.set(i37, i35 - 5);
                }
                if (bitArray2.get(i36 + 7)) {
                    bitMatrix.set(i35 + 5, i37);
                }
                if (bitArray2.get(20 - i36)) {
                    bitMatrix.set(i37, i35 + 5);
                }
                if (bitArray2.get(27 - i36)) {
                    bitMatrix.set(i35 - 5, i37);
                }
                i36++;
            }
        } else {
            while (i36 < 10) {
                int i38 = (i36 / 5) + (i35 - 5) + i36;
                if (bitArray2.get(i36)) {
                    bitMatrix.set(i38, i35 - 7);
                }
                if (bitArray2.get(i36 + 10)) {
                    bitMatrix.set(i35 + 7, i38);
                }
                if (bitArray2.get(29 - i36)) {
                    bitMatrix.set(i38, i35 + 7);
                }
                if (bitArray2.get(39 - i36)) {
                    bitMatrix.set(i35 - 7, i38);
                }
                i36++;
            }
        }
        if (i5 != 0) {
            drawBullsEye(bitMatrix, i35, 5);
        } else {
            drawBullsEye(bitMatrix, i35, 7);
            int i39 = 0;
            int i40 = 0;
            while (i39 < (i19 / 2) - 1) {
                for (int i41 = i35 & 1; i41 < i7; i41 += 2) {
                    int i42 = i35 - i40;
                    bitMatrix.set(i42, i41);
                    int i43 = i35 + i40;
                    bitMatrix.set(i43, i41);
                    bitMatrix.set(i41, i42);
                    bitMatrix.set(i41, i43);
                }
                i39 += 15;
                i40 += 16;
            }
        }
        AztecCode aztecCode = new AztecCode();
        aztecCode.setMatrix(bitMatrix);
        return aztecCode;
    }

    private static BitArray generateCheckWords(BitArray bitArray, int i, int i2) {
        GenericGF genericGF;
        int size = bitArray.getSize() / i2;
        if (i2 == 4) {
            genericGF = GenericGF.AZTEC_PARAM;
        } else if (i2 == 6) {
            genericGF = GenericGF.AZTEC_DATA_6;
        } else if (i2 == 8) {
            genericGF = GenericGF.AZTEC_DATA_8;
        } else if (i2 == 10) {
            genericGF = GenericGF.AZTEC_DATA_10;
        } else if (i2 != 12) {
            genericGF = null;
        } else {
            genericGF = GenericGF.AZTEC_DATA_12;
        }
        ReedSolomonEncoder reedSolomonEncoder = new ReedSolomonEncoder(genericGF);
        int i3 = i / i2;
        int[] iArr = new int[i3];
        int size2 = bitArray.getSize() / i2;
        for (int i4 = 0; i4 < size2; i4++) {
            int i5 = 0;
            for (int i6 = 0; i6 < i2; i6++) {
                i5 |= bitArray.get((i4 * i2) + i6) ? 1 << ((i2 - i6) - 1) : 0;
            }
            iArr[i4] = i5;
        }
        reedSolomonEncoder.encode(iArr, i3 - size);
        BitArray bitArray2 = new BitArray();
        bitArray2.appendBits(0, i % i2);
        for (int appendBits : iArr) {
            bitArray2.appendBits(appendBits, i2);
        }
        return bitArray2;
    }

    static BitArray stuffBits(BitArray bitArray, int i) {
        BitArray bitArray2 = new BitArray();
        int size = bitArray.getSize();
        int i2 = (1 << i) - 2;
        int i3 = 0;
        while (i3 < size) {
            int i4 = 0;
            for (int i5 = 0; i5 < i; i5++) {
                int i6 = i3 + i5;
                if (i6 >= size || bitArray.get(i6)) {
                    i4 |= 1 << ((i - 1) - i5);
                }
            }
            int i7 = i4 & i2;
            if (i7 == i2) {
                bitArray2.appendBits(i7, i);
            } else if (i7 == 0) {
                bitArray2.appendBits(i4 | 1, i);
            } else {
                bitArray2.appendBits(i4, i);
                i3 += i;
            }
            i3--;
            i3 += i;
        }
        return bitArray2;
    }
}
