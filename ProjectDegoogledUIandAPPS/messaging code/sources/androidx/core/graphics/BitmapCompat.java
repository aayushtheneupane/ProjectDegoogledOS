package androidx.core.graphics;

import android.graphics.Bitmap;
import android.os.Build;

public final class BitmapCompat {
    private BitmapCompat() {
    }

    public static int getAllocationByteCount(Bitmap bitmap) {
        int i = Build.VERSION.SDK_INT;
        return bitmap.getAllocationByteCount();
    }

    public static boolean hasMipMap(Bitmap bitmap) {
        int i = Build.VERSION.SDK_INT;
        return bitmap.hasMipMap();
    }

    public static void setHasMipMap(Bitmap bitmap, boolean z) {
        int i = Build.VERSION.SDK_INT;
        bitmap.setHasMipMap(z);
    }
}
