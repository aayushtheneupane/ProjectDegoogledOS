package p000;

import android.view.View;
import android.view.ViewTreeObserver;

/* renamed from: sa */
/* compiled from: PG */
final class C0493sa implements View.OnAttachStateChangeListener {

    /* renamed from: a */
    private final /* synthetic */ C0494sb f15833a;

    public C0493sa(C0494sb sbVar) {
        this.f15833a = sbVar;
    }

    public final void onViewAttachedToWindow(View view) {
    }

    public final void onViewDetachedFromWindow(View view) {
        ViewTreeObserver viewTreeObserver = this.f15833a.f15837d;
        if (viewTreeObserver != null) {
            if (!viewTreeObserver.isAlive()) {
                this.f15833a.f15837d = view.getViewTreeObserver();
            }
            C0494sb sbVar = this.f15833a;
            sbVar.f15837d.removeGlobalOnLayoutListener(sbVar.f15835b);
        }
        view.removeOnAttachStateChangeListener(this);
    }
}
