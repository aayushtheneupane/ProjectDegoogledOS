package p000;

import android.graphics.Rect;
import android.view.View;
import android.view.WindowInsets;

/* renamed from: bvj */
/* compiled from: PG */
final /* synthetic */ class bvj implements View.OnApplyWindowInsetsListener {

    /* renamed from: a */
    private final bvv f3675a;

    public bvj(bvv bvv) {
        this.f3675a = bvv;
    }

    public final WindowInsets onApplyWindowInsets(View view, WindowInsets windowInsets) {
        bvv bvv = this.f3675a;
        Rect rect = new Rect(windowInsets.getSystemWindowInsetLeft(), windowInsets.getSystemWindowInsetTop(), windowInsets.getSystemWindowInsetRight(), windowInsets.getSystemWindowInsetBottom());
        if (!rect.equals(bvv.f3693F)) {
            bvv.f3693F = rect;
            bvv.mo2811d();
        }
        return windowInsets;
    }
}
