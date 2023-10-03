package androidx.appcompat.view.menu;

import android.view.View;
import android.view.ViewTreeObserver;

/* renamed from: androidx.appcompat.view.menu.f */
class C0227f implements ViewTreeObserver.OnGlobalLayoutListener {
    final /* synthetic */ C0232k this$0;

    C0227f(C0232k kVar) {
        this.this$0 = kVar;
    }

    public void onGlobalLayout() {
        if (this.this$0.isShowing() && this.this$0.f251wn.size() > 0 && !((C0231j) this.this$0.f251wn.get(0)).window.isModal()) {
            View view = this.this$0.f242Bn;
            if (view == null || !view.isShown()) {
                this.this$0.dismiss();
                return;
            }
            for (C0231j jVar : this.this$0.f251wn) {
                jVar.window.show();
            }
        }
    }
}
