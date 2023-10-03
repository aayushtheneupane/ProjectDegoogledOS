package p000;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Shader;
import android.os.Build;
import android.support.p002v7.widget.RecyclerView;
import android.util.Log;
import java.util.Arrays;
import java.util.HashSet;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/* renamed from: bax */
/* compiled from: PG */
public final class bax {

    /* renamed from: a */
    public static final Lock f1980a = (!new HashSet(Arrays.asList(new String[]{"XT1085", "XT1092", "XT1093", "XT1094", "XT1095", "XT1096", "XT1097", "XT1098", "XT1031", "XT1028", "XT937C", "XT1032", "XT1008", "XT1033", "XT1035", "XT1034", "XT939G", "XT1039", "XT1040", "XT1042", "XT1045", "XT1063", "XT1064", "XT1068", "XT1069", "XT1072", "XT1077", "XT1078", "XT1079"})).contains(Build.MODEL) ? new baw() : new ReentrantLock());

    /* renamed from: b */
    private static final Paint f1981b = new Paint(6);

    static {
        new Paint(7);
        new Paint(7).setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
    }

    /* renamed from: a */
    public static int m2065a(int i) {
        switch (i) {
            case 3:
            case 4:
                return 180;
            case 5:
            case 6:
                return 90;
            case 7:
            case 8:
                return 270;
            default:
                return 0;
        }
    }

    /* renamed from: b */
    public static boolean m2075b(int i) {
        switch (i) {
            case RecyclerView.SCROLL_STATE_SETTLING:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
                return true;
            default:
                return false;
        }
    }

    /* renamed from: a */
    public static void m2071a(Bitmap bitmap, Bitmap bitmap2, Matrix matrix) {
        f1980a.lock();
        try {
            Canvas canvas = new Canvas(bitmap2);
            canvas.drawBitmap(bitmap, matrix, f1981b);
            m2072a(canvas);
        } finally {
            f1980a.unlock();
        }
    }

    /* renamed from: a */
    public static Bitmap m2069a(auk auk, Bitmap bitmap, int i, int i2) {
        float f;
        float f2;
        if (bitmap.getWidth() == i && bitmap.getHeight() == i2) {
            return bitmap;
        }
        Matrix matrix = new Matrix();
        float f3 = 0.0f;
        if (bitmap.getWidth() * i2 > bitmap.getHeight() * i) {
            f2 = ((float) i2) / ((float) bitmap.getHeight());
            f3 = (((float) i) - (((float) bitmap.getWidth()) * f2)) * 0.5f;
            f = 0.0f;
        } else {
            f2 = ((float) i) / ((float) bitmap.getWidth());
            f = (((float) i2) - (((float) bitmap.getHeight()) * f2)) * 0.5f;
        }
        matrix.setScale(f2, f2);
        matrix.postTranslate((float) ((int) (f3 + 0.5f)), (float) ((int) (f + 0.5f)));
        Bitmap a = auk.mo1642a(i, i2, m2066a(bitmap));
        m2070a(bitmap, a);
        m2071a(bitmap, a, matrix);
        return a;
    }

    /* renamed from: c */
    public static Bitmap m2076c(auk auk, Bitmap bitmap, int i, int i2) {
        return (bitmap.getWidth() > i || bitmap.getHeight() > i2) ? m2074b(auk, bitmap, i, i2) : bitmap;
    }

    /* renamed from: a */
    private static void m2072a(Canvas canvas) {
        canvas.setBitmap((Bitmap) null);
    }

    /* renamed from: b */
    public static Bitmap m2074b(auk auk, Bitmap bitmap, int i, int i2) {
        if (bitmap.getWidth() == i && bitmap.getHeight() == i2) {
            return bitmap;
        }
        float min = Math.min(((float) i) / ((float) bitmap.getWidth()), ((float) i2) / ((float) bitmap.getHeight()));
        int round = Math.round(((float) bitmap.getWidth()) * min);
        int round2 = Math.round(((float) bitmap.getHeight()) * min);
        if (bitmap.getWidth() == round && bitmap.getHeight() == round2) {
            return bitmap;
        }
        Bitmap a = auk.mo1642a((int) (((float) bitmap.getWidth()) * min), (int) (((float) bitmap.getHeight()) * min), m2066a(bitmap));
        m2070a(bitmap, a);
        Matrix matrix = new Matrix();
        matrix.setScale(min, min);
        m2071a(bitmap, a, matrix);
        return a;
    }

    /* renamed from: b */
    private static Bitmap.Config m2073b(Bitmap bitmap) {
        int i = Build.VERSION.SDK_INT;
        if (Bitmap.Config.RGBA_F16.equals(bitmap.getConfig())) {
            return Bitmap.Config.RGBA_F16;
        }
        return Bitmap.Config.ARGB_8888;
    }

    /* renamed from: a */
    public static Bitmap.Config m2066a(Bitmap bitmap) {
        return bitmap.getConfig() != null ? bitmap.getConfig() : Bitmap.Config.ARGB_8888;
    }

    /* renamed from: a */
    public static Bitmap m2067a(Bitmap bitmap, int i) {
        if (i == 0) {
            return bitmap;
        }
        try {
            Matrix matrix = new Matrix();
            matrix.setRotate((float) i);
            return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        } catch (Exception e) {
            if (!Log.isLoggable("TransformationUtils", 6)) {
                return bitmap;
            }
            Log.e("TransformationUtils", "Exception when trying to orient image", e);
            return bitmap;
        }
    }

    /* JADX INFO: finally extract failed */
    /* renamed from: a */
    public static Bitmap m2068a(auk auk, Bitmap bitmap, int i) {
        Bitmap bitmap2;
        cns.m4637a(i > 0, "roundingRadius must be greater than 0.");
        bav bav = new bav(i);
        Bitmap.Config b = m2073b(bitmap);
        Bitmap.Config b2 = m2073b(bitmap);
        if (!b2.equals(bitmap.getConfig())) {
            bitmap2 = auk.mo1642a(bitmap.getWidth(), bitmap.getHeight(), b2);
            new Canvas(bitmap2).drawBitmap(bitmap, 0.0f, 0.0f, (Paint) null);
        } else {
            bitmap2 = bitmap;
        }
        Bitmap a = auk.mo1642a(bitmap2.getWidth(), bitmap2.getHeight(), b);
        a.setHasAlpha(true);
        BitmapShader bitmapShader = new BitmapShader(bitmap2, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setShader(bitmapShader);
        RectF rectF = new RectF(0.0f, 0.0f, (float) a.getWidth(), (float) a.getHeight());
        f1980a.lock();
        try {
            Canvas canvas = new Canvas(a);
            canvas.drawColor(0, PorterDuff.Mode.CLEAR);
            float f = (float) bav.f1979a;
            canvas.drawRoundRect(rectF, f, f, paint);
            m2072a(canvas);
            f1980a.unlock();
            if (!bitmap2.equals(bitmap)) {
                auk.mo1645a(bitmap2);
            }
            return a;
        } catch (Throwable th) {
            f1980a.unlock();
            throw th;
        }
    }

    /* renamed from: a */
    public static void m2070a(Bitmap bitmap, Bitmap bitmap2) {
        bitmap2.setHasAlpha(bitmap.hasAlpha());
    }
}
