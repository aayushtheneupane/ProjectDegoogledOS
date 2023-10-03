package com.android.launcher3.icons;

import android.graphics.Bitmap;

public class BitmapInfo {
    public static final Bitmap LOW_RES_ICON = Bitmap.createBitmap(1, 1, Bitmap.Config.ALPHA_8);
    public int color;
    public Bitmap icon;

    public static BitmapInfo fromBitmap(Bitmap bitmap, ColorExtractor colorExtractor) {
        BitmapInfo bitmapInfo = new BitmapInfo();
        bitmapInfo.icon = bitmap;
        bitmapInfo.color = colorExtractor != null ? colorExtractor.findDominantColorByHue(bitmap) : 0;
        return bitmapInfo;
    }
}
