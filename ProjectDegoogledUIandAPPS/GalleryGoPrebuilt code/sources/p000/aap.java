package p000;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;

/* renamed from: aap */
/* compiled from: PG */
final class aap extends Drawable {

    /* renamed from: a */
    private static final double f40a = Math.cos(Math.toRadians(45.0d));

    /* renamed from: a */
    static float m29a(float f, float f2, boolean z) {
        float f3 = f * 1.5f;
        if (!z) {
            return f3;
        }
        double d = (double) f3;
        double d2 = (double) f2;
        Double.isNaN(d2);
        Double.isNaN(d);
        return (float) (d + ((1.0d - f40a) * d2));
    }

    /* renamed from: b */
    static float m30b(float f, float f2, boolean z) {
        if (!z) {
            return f;
        }
        double d = (double) f;
        double d2 = (double) f2;
        Double.isNaN(d2);
        Double.isNaN(d);
        return (float) (d + ((1.0d - f40a) * d2));
    }

    public final void draw(Canvas canvas) {
        throw null;
    }

    public final int getOpacity() {
        throw null;
    }

    public final boolean getPadding(Rect rect) {
        throw null;
    }

    public final boolean isStateful() {
        throw null;
    }

    /* access modifiers changed from: protected */
    public final void onBoundsChange(Rect rect) {
        throw null;
    }

    /* access modifiers changed from: protected */
    public final boolean onStateChange(int[] iArr) {
        throw null;
    }

    public final void setAlpha(int i) {
        throw null;
    }

    public final void setColorFilter(ColorFilter colorFilter) {
        throw null;
    }
}
