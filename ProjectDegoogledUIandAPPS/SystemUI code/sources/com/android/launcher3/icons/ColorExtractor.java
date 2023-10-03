package com.android.launcher3.icons;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.util.SparseArray;
import java.util.Arrays;

public class ColorExtractor {
    private final int NUM_SAMPLES = 20;
    private final float[] mTmpHsv = new float[3];
    private final float[] mTmpHueScoreHistogram = new float[360];
    private final int[] mTmpPixels = new int[20];
    private final SparseArray<Float> mTmpRgbScores = new SparseArray<>();

    public int findDominantColorByHue(Bitmap bitmap) {
        return findDominantColorByHue(bitmap, 20);
    }

    public int findDominantColorByHue(Bitmap bitmap, int i) {
        int i2;
        int i3 = i;
        int height = bitmap.getHeight();
        int width = bitmap.getWidth();
        int sqrt = (int) Math.sqrt((double) ((height * width) / i3));
        if (sqrt < 1) {
            sqrt = 1;
        }
        float[] fArr = this.mTmpHsv;
        Arrays.fill(fArr, 0.0f);
        float[] fArr2 = this.mTmpHueScoreHistogram;
        Arrays.fill(fArr2, 0.0f);
        int[] iArr = this.mTmpPixels;
        int i4 = 0;
        Arrays.fill(iArr, 0);
        int i5 = -1;
        int i6 = 0;
        int i7 = 0;
        float f = -1.0f;
        while (true) {
            i2 = -16777216;
            if (i6 >= height) {
                break;
            }
            float f2 = f;
            int i8 = i7;
            int i9 = i5;
            int i10 = i4;
            while (i10 < width) {
                int pixel = bitmap.getPixel(i10, i6);
                if (((pixel >> 24) & 255) >= 128) {
                    int i11 = pixel | -16777216;
                    Color.colorToHSV(i11, fArr);
                    int i12 = (int) fArr[i4];
                    if (i12 >= 0 && i12 < fArr2.length) {
                        if (i8 < i3) {
                            iArr[i8] = i11;
                            i8++;
                        }
                        fArr2[i12] = fArr2[i12] + (fArr[1] * fArr[2]);
                        if (fArr2[i12] > f2) {
                            f2 = fArr2[i12];
                            i9 = i12;
                        }
                    }
                }
                i10 += sqrt;
                i4 = 0;
            }
            Bitmap bitmap2 = bitmap;
            i6 += sqrt;
            i5 = i9;
            i7 = i8;
            f = f2;
            i4 = 0;
        }
        SparseArray<Float> sparseArray = this.mTmpRgbScores;
        sparseArray.clear();
        float f3 = -1.0f;
        for (int i13 = 0; i13 < i7; i13++) {
            int i14 = iArr[i13];
            Color.colorToHSV(i14, fArr);
            if (((int) fArr[0]) == i5) {
                float f4 = fArr[1];
                float f5 = fArr[2];
                int i15 = ((int) (100.0f * f4)) + ((int) (10000.0f * f5));
                float f6 = f4 * f5;
                Float f7 = sparseArray.get(i15);
                if (f7 != null) {
                    f6 += f7.floatValue();
                }
                sparseArray.put(i15, Float.valueOf(f6));
                if (f6 > f3) {
                    i2 = i14;
                    f3 = f6;
                }
            }
        }
        return i2;
    }
}
