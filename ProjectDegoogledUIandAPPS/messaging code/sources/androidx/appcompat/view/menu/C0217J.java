package androidx.appcompat.view.menu;

import android.view.View;
import android.view.ViewTreeObserver;

/* renamed from: androidx.appcompat.view.menu.J */
class C0217J implements ViewTreeObserver.OnGlobalLayoutListener {
    final /* synthetic */ C0219L this$0;

    C0217J(C0219L l) {
        this.this$0 = l;
    }

    public void onGlobalLayout() {
        if (this.this$0.isShowing() && !this.this$0.mPopup.isModal()) {
            View view = this.this$0.f203Bn;
            if (view == null || !view.isShown()) {
                this.this$0.dismiss();
            } else {
                this.this$0.mPopup.show();
            }
        }
    }
}
