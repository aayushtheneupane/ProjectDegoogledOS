package com.google.zxing.oned;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.ChecksumException;
import com.google.zxing.DecodeHintType;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.Reader;
import com.google.zxing.ReaderException;
import com.google.zxing.Result;
import com.google.zxing.ResultMetadataType;
import com.google.zxing.ResultPoint;
import com.google.zxing.common.BitArray;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.Map;

public abstract class OneDReader implements Reader {
    public abstract Result decodeRow(int i, BitArray bitArray, Map<DecodeHintType, ?> map) throws NotFoundException, ChecksumException, FormatException;

    public void reset() {
    }

    public Result decode(BinaryBitmap binaryBitmap, Map<DecodeHintType, ?> map) throws NotFoundException, FormatException {
        try {
            return doDecode(binaryBitmap, map);
        } catch (NotFoundException e) {
            if (!(map != null && map.containsKey(DecodeHintType.TRY_HARDER)) || !binaryBitmap.isRotateSupported()) {
                throw e;
            }
            binaryBitmap.rotateCounterClockwise();
            throw null;
        }
    }

    private Result doDecode(BinaryBitmap binaryBitmap, Map<DecodeHintType, ?> map) throws NotFoundException {
        int i;
        int i2;
        int i3;
        int i4;
        Map<DecodeHintType, ?> map2 = map;
        int width = binaryBitmap.getWidth();
        int height = binaryBitmap.getHeight();
        BitArray bitArray = new BitArray(width);
        int i5 = height >> 1;
        int i6 = 0;
        int i7 = 1;
        boolean z = map2 != null && map2.containsKey(DecodeHintType.TRY_HARDER);
        int max = Math.max(1, height >> (z ? 8 : 5));
        int i8 = z ? height : 15;
        Map<DecodeHintType, ?> map3 = map2;
        int i9 = 0;
        while (i9 < i8) {
            int i10 = i9 + 1;
            int i11 = i10 >> 1;
            if (((i9 & 1) == 0 ? i7 : i6) == 0) {
                i11 = -i11;
            }
            int i12 = (i11 * max) + i5;
            if (i12 < 0 || i12 >= height) {
                break;
            }
            try {
                bitArray = binaryBitmap.getBlackRow(i12, bitArray);
                EnumMap enumMap = map3;
                int i13 = i6;
                while (i13 < 2) {
                    if (i13 == i7) {
                        bitArray.reverse();
                        if (enumMap != null && enumMap.containsKey(DecodeHintType.NEED_RESULT_POINT_CALLBACK)) {
                            EnumMap enumMap2 = new EnumMap(DecodeHintType.class);
                            enumMap2.putAll(enumMap);
                            enumMap2.remove(DecodeHintType.NEED_RESULT_POINT_CALLBACK);
                            enumMap = enumMap2;
                        }
                    }
                    try {
                        Result decodeRow = decodeRow(i12, bitArray, enumMap);
                        if (i13 == i7) {
                            try {
                                decodeRow.putMetadata(ResultMetadataType.ORIENTATION, 180);
                                ResultPoint[] resultPoints = decodeRow.getResultPoints();
                                if (resultPoints != null) {
                                    float f = (float) width;
                                    i3 = width;
                                    try {
                                        resultPoints[0] = new ResultPoint((f - resultPoints[i6].getX()) - 1.0f, resultPoints[i6].getY());
                                        i4 = 1;
                                        try {
                                            resultPoints[1] = new ResultPoint((f - resultPoints[1].getX()) - 1.0f, resultPoints[1].getY());
                                        } catch (ReaderException unused) {
                                            continue;
                                        }
                                    } catch (ReaderException unused2) {
                                        i4 = 1;
                                        i13++;
                                        BinaryBitmap binaryBitmap2 = binaryBitmap;
                                        i7 = i4;
                                        width = i3;
                                        i6 = 0;
                                    }
                                }
                            } catch (ReaderException unused3) {
                                i3 = width;
                                i4 = 1;
                                i13++;
                                BinaryBitmap binaryBitmap22 = binaryBitmap;
                                i7 = i4;
                                width = i3;
                                i6 = 0;
                            }
                        }
                        return decodeRow;
                    } catch (ReaderException unused4) {
                        i3 = width;
                        i4 = i7;
                        i13++;
                        BinaryBitmap binaryBitmap222 = binaryBitmap;
                        i7 = i4;
                        width = i3;
                        i6 = 0;
                    }
                }
                i = width;
                i2 = i7;
                map3 = enumMap;
            } catch (NotFoundException unused5) {
                i = width;
                i2 = i7;
            }
            i9 = i10;
            i7 = i2;
            width = i;
            i6 = 0;
        }
        throw NotFoundException.getNotFoundInstance();
    }

    protected static void recordPattern(BitArray bitArray, int i, int[] iArr) throws NotFoundException {
        int length = iArr.length;
        Arrays.fill(iArr, 0, length, 0);
        int size = bitArray.getSize();
        if (i < size) {
            boolean z = !bitArray.get(i);
            int i2 = 0;
            while (i < size) {
                if (bitArray.get(i) ^ z) {
                    iArr[i2] = iArr[i2] + 1;
                } else {
                    i2++;
                    if (i2 == length) {
                        break;
                    }
                    iArr[i2] = 1;
                    z = !z;
                }
                i++;
            }
            if (i2 == length) {
                return;
            }
            if (i2 != length - 1 || i != size) {
                throw NotFoundException.getNotFoundInstance();
            }
            return;
        }
        throw NotFoundException.getNotFoundInstance();
    }

    protected static void recordPatternInReverse(BitArray bitArray, int i, int[] iArr) throws NotFoundException {
        int length = iArr.length;
        boolean z = bitArray.get(i);
        while (i > 0 && length >= 0) {
            i--;
            if (bitArray.get(i) != z) {
                length--;
                z = !z;
            }
        }
        if (length < 0) {
            recordPattern(bitArray, i + 1, iArr);
            return;
        }
        throw NotFoundException.getNotFoundInstance();
    }

    protected static int patternMatchVariance(int[] iArr, int[] iArr2, int i) {
        int length = iArr.length;
        int i2 = 0;
        int i3 = 0;
        for (int i4 = 0; i4 < length; i4++) {
            i2 += iArr[i4];
            i3 += iArr2[i4];
        }
        if (i2 < i3) {
            return Integer.MAX_VALUE;
        }
        int i5 = (i2 << 8) / i3;
        int i6 = (i * i5) >> 8;
        int i7 = 0;
        for (int i8 = 0; i8 < length; i8++) {
            int i9 = iArr[i8] << 8;
            int i10 = iArr2[i8] * i5;
            int i11 = i9 > i10 ? i9 - i10 : i10 - i9;
            if (i11 > i6) {
                return Integer.MAX_VALUE;
            }
            i7 += i11;
        }
        return i7 / i2;
    }
}
