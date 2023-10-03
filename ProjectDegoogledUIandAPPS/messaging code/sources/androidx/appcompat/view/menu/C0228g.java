package androidx.appcompat.view.menu;

import android.view.View;
import android.view.ViewTreeObserver;

/* renamed from: androidx.appcompat.view.menu.g */
class C0228g implements View.OnAttachStateChangeListener {
    final /* synthetic */ C0232k this$0;

    C0228g(C0232k kVar) {
        this.this$0 = kVar;
    }

    public void onViewAttachedToWindow(View view) {
    }

    public void onViewDetachedFromWindow(View view) {
        ViewTreeObserver viewTreeObserver = this.this$0.mTreeObserver;
        if (viewTreeObserver != null) {
            if (!viewTreeObserver.isAlive()) {
                this.this$0.mTreeObserver = view.getViewTreeObserver();
            }
            C0232k kVar = this.this$0;
            kVar.mTreeObserver.removeGlobalOnLayoutListener(kVar.f252xn);
        }
        view.removeOnAttachStateChangeListener(this);
    }
}
