package p000;

import android.view.View;
import android.view.ViewTreeObserver;
import java.util.Map;
import java.util.WeakHashMap;

/* renamed from: mf */
/* compiled from: PG */
final class C0336mf implements ViewTreeObserver.OnGlobalLayoutListener, View.OnAttachStateChangeListener {

    /* renamed from: a */
    private final WeakHashMap f15226a = new WeakHashMap();

    public final void onViewDetachedFromWindow(View view) {
    }

    public final void onGlobalLayout() {
        boolean z;
        for (Map.Entry entry : this.f15226a.entrySet()) {
            View view = (View) entry.getKey();
            boolean booleanValue = ((Boolean) entry.getValue()).booleanValue();
            if (view.getVisibility() == 0) {
                z = true;
            } else {
                z = false;
            }
            if (booleanValue != z) {
                if (z) {
                    C0340mj.m14713e(view, 16);
                }
                this.f15226a.put(view, Boolean.valueOf(z));
            }
        }
    }

    public final void onViewAttachedToWindow(View view) {
        view.getViewTreeObserver().addOnGlobalLayoutListener(this);
    }
}
