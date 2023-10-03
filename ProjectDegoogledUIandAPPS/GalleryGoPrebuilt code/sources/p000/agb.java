package p000;

import android.graphics.Rect;
import android.os.Build;
import android.util.Property;
import android.view.View;

/* renamed from: agb */
/* compiled from: PG */
final class agb {

    /* renamed from: a */
    public static final Property f366a = new afz(Float.class, "translationAlpha");

    /* renamed from: b */
    public static final gbz f367b;

    static {
        if (Build.VERSION.SDK_INT >= 29) {
            f367b = new agg();
        } else {
            int i = Build.VERSION.SDK_INT;
            f367b = new agf();
        }
        new aga(Rect.class, "clipBounds");
    }

    /* renamed from: b */
    static float m424b(View view) {
        return f367b.mo345a(view);
    }

    /* renamed from: a */
    static agm m420a(View view) {
        int i = Build.VERSION.SDK_INT;
        return new agl(view);
    }

    /* renamed from: a */
    static void m423a(View view, int i, int i2, int i3, int i4) {
        f367b.mo349a(view, i, i2, i3, i4);
    }

    /* renamed from: a */
    static void m421a(View view, float f) {
        f367b.mo346a(view, f);
    }

    /* renamed from: a */
    static void m422a(View view, int i) {
        f367b.mo350a(view, i);
    }
}
