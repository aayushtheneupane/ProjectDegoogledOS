package p000;

import android.content.Context;
import com.google.android.apps.photosgo.R;

/* renamed from: gfk */
/* compiled from: PG */
public final class gfk {

    /* renamed from: a */
    public final boolean f11167a;

    /* renamed from: b */
    public final int f11168b;

    /* renamed from: c */
    public final int f11169c;

    /* renamed from: d */
    public final float f11170d;

    public gfk(Context context) {
        this.f11167a = ggf.m10253a(context, (int) R.attr.elevationOverlayEnabled, false);
        this.f11168b = ggf.m10255b(context, R.attr.elevationOverlayColor);
        this.f11169c = ggf.m10255b(context, R.attr.colorSurface);
        this.f11170d = context.getResources().getDisplayMetrics().density;
    }
}
