package p000;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import java.util.concurrent.locks.Lock;

/* renamed from: bae */
/* compiled from: PG */
final class bae {

    /* renamed from: a */
    private static final auk f1943a = new bad();

    /* JADX INFO: finally extract failed */
    /* renamed from: a */
    static aua m2022a(auk auk, Drawable drawable, int i, int i2) {
        Drawable current = drawable.getCurrent();
        boolean z = false;
        Bitmap bitmap = null;
        if (current instanceof BitmapDrawable) {
            bitmap = ((BitmapDrawable) current).getBitmap();
        } else if (!(current instanceof Animatable)) {
            if (i == Integer.MIN_VALUE && current.getIntrinsicWidth() <= 0) {
                if (Log.isLoggable("DrawableToBitmap", 5)) {
                    String valueOf = String.valueOf(current);
                    StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 95);
                    sb.append("Unable to draw ");
                    sb.append(valueOf);
                    sb.append(" to Bitmap with Target.SIZE_ORIGINAL because the Drawable has no intrinsic width");
                    Log.w("DrawableToBitmap", sb.toString());
                }
            } else if (i2 != Integer.MIN_VALUE || current.getIntrinsicHeight() > 0) {
                if (current.getIntrinsicWidth() > 0) {
                    i = current.getIntrinsicWidth();
                }
                if (current.getIntrinsicHeight() > 0) {
                    i2 = current.getIntrinsicHeight();
                }
                Lock lock = bax.f1980a;
                lock.lock();
                Bitmap a = auk.mo1642a(i, i2, Bitmap.Config.ARGB_8888);
                try {
                    Canvas canvas = new Canvas(a);
                    current.setBounds(0, 0, i, i2);
                    current.draw(canvas);
                    canvas.setBitmap((Bitmap) null);
                    lock.unlock();
                    bitmap = a;
                } catch (Throwable th) {
                    lock.unlock();
                    throw th;
                }
            } else if (Log.isLoggable("DrawableToBitmap", 5)) {
                String valueOf2 = String.valueOf(current);
                StringBuilder sb2 = new StringBuilder(String.valueOf(valueOf2).length() + 96);
                sb2.append("Unable to draw ");
                sb2.append(valueOf2);
                sb2.append(" to Bitmap with Target.SIZE_ORIGINAL because the Drawable has no intrinsic height");
                Log.w("DrawableToBitmap", sb2.toString());
            }
            z = true;
        }
        if (!z) {
            auk = f1943a;
        }
        return azk.m1961a(bitmap, auk);
    }
}
