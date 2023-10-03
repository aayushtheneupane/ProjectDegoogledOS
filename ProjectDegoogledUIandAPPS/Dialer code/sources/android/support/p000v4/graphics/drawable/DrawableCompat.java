package android.support.p000v4.graphics.drawable;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Build;

/* renamed from: android.support.v4.graphics.drawable.DrawableCompat */
public final class DrawableCompat {
    public static RoundedBitmapDrawable create(Resources resources, Bitmap bitmap) {
        int i = Build.VERSION.SDK_INT;
        return new RoundedBitmapDrawable21(resources, bitmap);
    }

    public static boolean isAutoMirrored(Drawable drawable) {
        int i = Build.VERSION.SDK_INT;
        return drawable.isAutoMirrored();
    }
}
