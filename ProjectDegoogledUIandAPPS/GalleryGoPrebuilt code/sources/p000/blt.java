package p000;

import android.view.View;
import android.view.ViewGroup;
import android.view.WindowInsets;

/* renamed from: blt */
/* compiled from: PG */
final /* synthetic */ class blt implements View.OnApplyWindowInsetsListener {

    /* renamed from: a */
    public static final View.OnApplyWindowInsetsListener f3117a = new blt();

    private blt() {
    }

    public final WindowInsets onApplyWindowInsets(View view, WindowInsets windowInsets) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        marginLayoutParams.bottomMargin = windowInsets.getSystemWindowInsetBottom();
        marginLayoutParams.leftMargin = windowInsets.getSystemWindowInsetLeft();
        marginLayoutParams.rightMargin = windowInsets.getSystemWindowInsetRight();
        return windowInsets;
    }
}
