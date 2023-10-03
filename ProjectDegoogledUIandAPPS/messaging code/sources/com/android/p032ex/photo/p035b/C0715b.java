package com.android.p032ex.photo.p035b;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

/* renamed from: com.android.ex.photo.b.b */
public class C0715b {
    public Bitmap bitmap;
    public Drawable drawable;
    public int status;

    /* renamed from: b */
    public Drawable mo5727b(Resources resources) {
        if (resources != null) {
            Drawable drawable2 = this.drawable;
            if (drawable2 != null) {
                return drawable2;
            }
            Bitmap bitmap2 = this.bitmap;
            if (bitmap2 == null) {
                return null;
            }
            return new BitmapDrawable(resources, bitmap2);
        }
        throw new IllegalArgumentException("resources can not be null!");
    }
}
