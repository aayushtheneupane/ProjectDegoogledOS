package androidx.fragment.app;

import android.graphics.Rect;
import android.transition.Transition;

/* renamed from: androidx.fragment.app.Z */
class C0406Z extends Transition.EpicenterCallback {
    final /* synthetic */ Rect val$epicenter;

    C0406Z(C0408aa aaVar, Rect rect) {
        this.val$epicenter = rect;
    }

    public Rect onGetEpicenter(Transition transition) {
        Rect rect = this.val$epicenter;
        if (rect == null || rect.isEmpty()) {
            return null;
        }
        return this.val$epicenter;
    }
}
