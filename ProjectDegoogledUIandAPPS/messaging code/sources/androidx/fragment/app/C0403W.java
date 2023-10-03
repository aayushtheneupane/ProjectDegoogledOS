package androidx.fragment.app;

import android.graphics.Rect;
import android.transition.Transition;

/* renamed from: androidx.fragment.app.W */
class C0403W extends Transition.EpicenterCallback {
    final /* synthetic */ Rect val$epicenter;

    C0403W(C0408aa aaVar, Rect rect) {
        this.val$epicenter = rect;
    }

    public Rect onGetEpicenter(Transition transition) {
        return this.val$epicenter;
    }
}
