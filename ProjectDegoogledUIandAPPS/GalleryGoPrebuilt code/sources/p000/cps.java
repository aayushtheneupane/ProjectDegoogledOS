package p000;

import android.content.Context;

/* renamed from: cps */
/* compiled from: PG */
public final class cps {

    /* renamed from: a */
    private final Context f5387a;

    public cps(Context context) {
        this.f5387a = context;
    }

    /* renamed from: a */
    public final int mo3740a(int i) {
        int i2 = this.f5387a.getResources().getDisplayMetrics().widthPixels;
        int i3 = this.f5387a.getResources().getDisplayMetrics().heightPixels;
        int min = Math.min(i2, i3);
        return i2 != min ? (int) (((float) Math.max(i2, i3)) / (((float) min) / ((float) i))) : i;
    }
}
