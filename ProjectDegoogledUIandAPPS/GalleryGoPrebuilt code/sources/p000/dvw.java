package p000;

import android.content.Context;
import android.util.DisplayMetrics;

/* renamed from: dvw */
/* compiled from: PG */
public final class dvw extends C0663yi {

    /* renamed from: i */
    private final int f7465i;

    public dvw(Context context, int i) {
        super(context);
        this.f7465i = Math.max(0, i);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final float mo4514a(DisplayMetrics displayMetrics) {
        int i = this.f7465i;
        if (i != 0) {
            return ((245.0f / ((float) i)) + 5.0f) / ((float) displayMetrics.densityDpi);
        }
        return 250.0f;
    }
}
