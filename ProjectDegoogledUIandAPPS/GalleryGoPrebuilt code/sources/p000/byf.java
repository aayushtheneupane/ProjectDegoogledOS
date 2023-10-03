package p000;

import android.graphics.Matrix;
import android.graphics.RectF;

/* renamed from: byf */
/* compiled from: PG */
public final class byf {

    /* renamed from: a */
    private static final Matrix f3902a = new Matrix();

    /* renamed from: a */
    public static void m3761a(float f, RectF rectF) {
        f3902a.reset();
        f3902a.postRotate((float) Math.toDegrees((double) f), 0.5f, 0.5f);
        f3902a.mapRect(rectF);
    }
}
