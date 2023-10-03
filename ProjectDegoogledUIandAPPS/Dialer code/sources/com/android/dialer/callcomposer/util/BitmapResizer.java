package com.android.dialer.callcomposer.util;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import com.android.dialer.common.Assert;
import com.android.dialer.common.LogUtil;

public final class BitmapResizer {
    static final int MAX_OUTPUT_RESOLUTION = 640;

    public static Bitmap resizeForEnrichedCalling(Bitmap bitmap, int i) {
        Assert.isWorkerThread();
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Matrix matrix = new Matrix();
        matrix.postRotate((float) i);
        LogUtil.m9i("BitmapResizer.resizeForEnrichedCalling", "starting height: %d, width: %d", Integer.valueOf(height), Integer.valueOf(width));
        if (width > MAX_OUTPUT_RESOLUTION || height > MAX_OUTPUT_RESOLUTION) {
            float f = 640.0f / (width > height ? (float) width : (float) height);
            LogUtil.m9i("BitmapResizer.resizeForEnrichedCalling", "ending height: %f, width: %f", Float.valueOf(((float) height) * f), Float.valueOf(((float) width) * f));
            matrix.postScale(f, f);
            return Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
        }
        LogUtil.m9i("BitmapResizer.resizeForEnrichedCalling", "no resizing needed", new Object[0]);
        return Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
    }
}
