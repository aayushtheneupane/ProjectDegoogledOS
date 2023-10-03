package androidx.appcompat.view.menu;

import android.view.View;
import android.view.ViewTreeObserver;

/* renamed from: androidx.appcompat.view.menu.K */
class C0218K implements View.OnAttachStateChangeListener {
    final /* synthetic */ C0219L this$0;

    C0218K(C0219L l) {
        this.this$0 = l;
    }

    public void onViewAttachedToWindow(View view) {
    }

    public void onViewDetachedFromWindow(View view) {
        ViewTreeObserver viewTreeObserver = this.this$0.mTreeObserver;
        if (viewTreeObserver != null) {
            if (!viewTreeObserver.isAlive()) {
                this.this$0.mTreeObserver = view.getViewTreeObserver();
            }
            C0219L l = this.this$0;
            l.mTreeObserver.removeGlobalOnLayoutListener(l.f208xn);
        }
        view.removeOnAttachStateChangeListener(this);
    }
}
