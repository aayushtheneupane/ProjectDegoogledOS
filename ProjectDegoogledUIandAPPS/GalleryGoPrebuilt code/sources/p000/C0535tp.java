package p000;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.PopupWindow;

/* renamed from: tp */
/* compiled from: PG */
final class C0535tp extends PopupWindow {
    static {
        int i = Build.VERSION.SDK_INT;
    }

    public C0535tp(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i, 0);
        C0684zc a = C0684zc.m16192a(context, attributeSet, C0435px.f15591s, i, 0);
        if (a.mo10735f(2)) {
            dcm.m5900a((PopupWindow) this, a.mo10725a(2, false));
        }
        setBackgroundDrawable(a.mo10723a(0));
        a.mo10724a();
    }
}
