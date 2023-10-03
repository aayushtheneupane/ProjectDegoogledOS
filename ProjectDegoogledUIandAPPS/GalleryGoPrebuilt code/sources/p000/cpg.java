package p000;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import java.security.MessageDigest;

/* renamed from: cpg */
/* compiled from: PG */
public final class cpg extends azl {

    /* renamed from: b */
    private static final Paint f5354b = new Paint(2);

    public final boolean equals(Object obj) {
        return obj instanceof cpg;
    }

    public final int hashCode() {
        return getClass().getName().hashCode();
    }

    /* renamed from: a */
    public final Bitmap mo1740a(auk auk, Bitmap bitmap, int i, int i2) {
        if (bitmap.getWidth() == i && bitmap.getHeight() == i2) {
            return bitmap;
        }
        Bitmap a = auk.mo1642a(i, i2, bitmap.getConfig());
        Canvas canvas = new Canvas(a);
        canvas.drawBitmap(bitmap, (Rect) null, new Rect(0, 0, i, i2), f5354b);
        canvas.setBitmap((Bitmap) null);
        return a;
    }

    /* renamed from: a */
    public final void mo1494a(MessageDigest messageDigest) {
        messageDigest.update(getClass().getName().getBytes(f1466a));
    }
}
