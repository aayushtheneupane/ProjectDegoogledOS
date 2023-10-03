package androidx.core.widget;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.os.Build;
import android.widget.ImageView;

public class ImageViewCompat {
    private ImageViewCompat() {
    }

    public static ColorStateList getImageTintList(ImageView imageView) {
        int i = Build.VERSION.SDK_INT;
        return imageView.getImageTintList();
    }

    public static PorterDuff.Mode getImageTintMode(ImageView imageView) {
        int i = Build.VERSION.SDK_INT;
        return imageView.getImageTintMode();
    }

    public static void setImageTintList(ImageView imageView, ColorStateList colorStateList) {
        int i = Build.VERSION.SDK_INT;
        imageView.setImageTintList(colorStateList);
        int i2 = Build.VERSION.SDK_INT;
    }

    public static void setImageTintMode(ImageView imageView, PorterDuff.Mode mode) {
        int i = Build.VERSION.SDK_INT;
        imageView.setImageTintMode(mode);
        int i2 = Build.VERSION.SDK_INT;
    }
}
