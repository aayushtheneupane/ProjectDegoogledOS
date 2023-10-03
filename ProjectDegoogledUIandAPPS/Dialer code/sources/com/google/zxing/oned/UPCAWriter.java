package com.google.zxing.oned;

import com.android.tools.p006r8.GeneratedOutlineSupport;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.Writer;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import java.util.Map;

public final class UPCAWriter implements Writer {
    private final EAN13Writer subWriter = new EAN13Writer();

    public BitMatrix encode(String str, BarcodeFormat barcodeFormat, int i, int i2, Map<EncodeHintType, ?> map) throws WriterException {
        if (barcodeFormat == BarcodeFormat.UPC_A) {
            EAN13Writer eAN13Writer = this.subWriter;
            int length = str.length();
            if (length == 11) {
                int i3 = 0;
                for (int i4 = 0; i4 < 11; i4++) {
                    i3 += (str.charAt(i4) - '0') * (i4 % 2 == 0 ? 3 : 1);
                }
                StringBuilder outline13 = GeneratedOutlineSupport.outline13(str);
                outline13.append((1000 - i3) % 10);
                str = outline13.toString();
            } else if (length != 12) {
                StringBuilder outline132 = GeneratedOutlineSupport.outline13("Requested contents should be 11 or 12 digits long, but got ");
                outline132.append(str.length());
                throw new IllegalArgumentException(outline132.toString());
            }
            return eAN13Writer.encode('0' + str, BarcodeFormat.EAN_13, i, i2, map);
        }
        throw new IllegalArgumentException(GeneratedOutlineSupport.outline6("Can only encode UPC-A, but got ", barcodeFormat));
    }
}
