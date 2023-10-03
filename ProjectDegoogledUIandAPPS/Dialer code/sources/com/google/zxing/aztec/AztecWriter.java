package com.google.zxing.aztec;

import com.android.tools.p006r8.GeneratedOutlineSupport;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.Writer;
import com.google.zxing.aztec.encoder.Encoder;
import com.google.zxing.common.BitMatrix;
import java.nio.charset.Charset;
import java.util.Map;

public final class AztecWriter implements Writer {
    private static final Charset DEFAULT_CHARSET = Charset.forName("ISO-8859-1");

    public BitMatrix encode(String str, BarcodeFormat barcodeFormat, int i, int i2, Map<EncodeHintType, ?> map) {
        Number number;
        Integer num = null;
        String str2 = map == null ? null : (String) map.get(EncodeHintType.CHARACTER_SET);
        if (map == null) {
            number = null;
        } else {
            number = (Number) map.get(EncodeHintType.ERROR_CORRECTION);
        }
        if (map != null) {
            num = (Integer) map.get(EncodeHintType.AZTEC_LAYERS);
        }
        Charset forName = str2 == null ? DEFAULT_CHARSET : Charset.forName(str2);
        int intValue = number == null ? 33 : number.intValue();
        int intValue2 = num == null ? 0 : num.intValue();
        if (barcodeFormat == BarcodeFormat.AZTEC) {
            BitMatrix matrix = Encoder.encode(str.getBytes(forName), intValue, intValue2).getMatrix();
            if (matrix != null) {
                int width = matrix.getWidth();
                int height = matrix.getHeight();
                int max = Math.max(i, width);
                int max2 = Math.max(i2, height);
                int min = Math.min(max / width, max2 / height);
                int i3 = (max - (width * min)) / 2;
                int i4 = (max2 - (height * min)) / 2;
                BitMatrix bitMatrix = new BitMatrix(max, max2);
                int i5 = 0;
                while (i5 < height) {
                    int i6 = i3;
                    int i7 = 0;
                    while (i7 < width) {
                        if (matrix.get(i7, i5)) {
                            bitMatrix.setRegion(i6, i4, min, min);
                        }
                        i7++;
                        i6 += min;
                    }
                    i5++;
                    i4 += min;
                }
                return bitMatrix;
            }
            throw new IllegalStateException();
        }
        throw new IllegalArgumentException(GeneratedOutlineSupport.outline6("Can only encode AZTEC, but got ", barcodeFormat));
    }
}
