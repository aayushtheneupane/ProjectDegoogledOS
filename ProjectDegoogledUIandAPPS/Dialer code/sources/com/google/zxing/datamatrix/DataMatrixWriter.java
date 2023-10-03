package com.google.zxing.datamatrix;

import com.android.tools.p006r8.GeneratedOutlineSupport;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.Dimension;
import com.google.zxing.EncodeHintType;
import com.google.zxing.Writer;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.datamatrix.encoder.DefaultPlacement;
import com.google.zxing.datamatrix.encoder.ErrorCorrection;
import com.google.zxing.datamatrix.encoder.HighLevelEncoder;
import com.google.zxing.datamatrix.encoder.SymbolInfo;
import com.google.zxing.datamatrix.encoder.SymbolShapeHint;
import com.google.zxing.qrcode.encoder.ByteMatrix;
import java.util.Map;

public final class DataMatrixWriter implements Writer {
    public BitMatrix encode(String str, BarcodeFormat barcodeFormat, int i, int i2, Map<EncodeHintType, ?> map) {
        Dimension dimension;
        if (str.isEmpty()) {
            throw new IllegalArgumentException("Found empty contents");
        } else if (barcodeFormat != BarcodeFormat.DATA_MATRIX) {
            throw new IllegalArgumentException(GeneratedOutlineSupport.outline6("Can only encode DATA_MATRIX, but got ", barcodeFormat));
        } else if (i < 0 || i2 < 0) {
            throw new IllegalArgumentException("Requested dimensions are too small: " + i + 'x' + i2);
        } else {
            SymbolShapeHint symbolShapeHint = SymbolShapeHint.FORCE_NONE;
            Dimension dimension2 = null;
            if (map != null) {
                SymbolShapeHint symbolShapeHint2 = (SymbolShapeHint) map.get(EncodeHintType.DATA_MATRIX_SHAPE);
                if (symbolShapeHint2 != null) {
                    symbolShapeHint = symbolShapeHint2;
                }
                dimension = (Dimension) map.get(EncodeHintType.MIN_SIZE);
                if (dimension == null) {
                    dimension = null;
                }
                Dimension dimension3 = (Dimension) map.get(EncodeHintType.MAX_SIZE);
                if (dimension3 != null) {
                    dimension2 = dimension3;
                }
            } else {
                dimension = null;
            }
            String encodeHighLevel = HighLevelEncoder.encodeHighLevel(str, symbolShapeHint, dimension, dimension2);
            SymbolInfo lookup = SymbolInfo.lookup(encodeHighLevel.length(), symbolShapeHint, dimension, dimension2, true);
            DefaultPlacement defaultPlacement = new DefaultPlacement(ErrorCorrection.encodeECC200(encodeHighLevel, lookup), lookup.getSymbolDataWidth(), lookup.getSymbolDataHeight());
            defaultPlacement.place();
            int symbolDataWidth = lookup.getSymbolDataWidth();
            int symbolDataHeight = lookup.getSymbolDataHeight();
            ByteMatrix byteMatrix = new ByteMatrix(lookup.getSymbolWidth(), lookup.getSymbolHeight());
            int i3 = 0;
            for (int i4 = 0; i4 < symbolDataHeight; i4++) {
                if (i4 % lookup.matrixHeight == 0) {
                    int i5 = 0;
                    for (int i6 = 0; i6 < lookup.getSymbolWidth(); i6++) {
                        byteMatrix.set(i5, i3, i6 % 2 == 0);
                        i5++;
                    }
                    i3++;
                }
                int i7 = 0;
                for (int i8 = 0; i8 < symbolDataWidth; i8++) {
                    if (i8 % lookup.matrixWidth == 0) {
                        byteMatrix.set(i7, i3, true);
                        i7++;
                    }
                    byteMatrix.set(i7, i3, defaultPlacement.getBit(i8, i4));
                    i7++;
                    int i9 = lookup.matrixWidth;
                    if (i8 % i9 == i9 - 1) {
                        byteMatrix.set(i7, i3, i4 % 2 == 0);
                        i7++;
                    }
                }
                i3++;
                int i10 = lookup.matrixHeight;
                if (i4 % i10 == i10 - 1) {
                    int i11 = 0;
                    for (int i12 = 0; i12 < lookup.getSymbolWidth(); i12++) {
                        byteMatrix.set(i11, i3, true);
                        i11++;
                    }
                    i3++;
                }
            }
            int width = byteMatrix.getWidth();
            int height = byteMatrix.getHeight();
            BitMatrix bitMatrix = new BitMatrix(width, height);
            bitMatrix.clear();
            for (int i13 = 0; i13 < width; i13++) {
                for (int i14 = 0; i14 < height; i14++) {
                    if (byteMatrix.get(i13, i14) == 1) {
                        bitMatrix.set(i13, i14);
                    }
                }
            }
            return bitMatrix;
        }
    }
}
