package androidx.fragment.app;

import android.transition.Transition;
import android.view.View;
import java.util.ArrayList;

/* renamed from: androidx.fragment.app.X */
class C0404X implements Transition.TransitionListener {

    /* renamed from: bp */
    final /* synthetic */ ArrayList f391bp;

    /* renamed from: yp */
    final /* synthetic */ View f392yp;

    C0404X(C0408aa aaVar, View view, ArrayList arrayList) {
        this.f392yp = view;
        this.f391bp = arrayList;
    }

    public void onTransitionCancel(Transition transition) {
    }

    public void onTransitionEnd(Transition transition) {
        transition.removeListener(this);
        this.f392yp.setVisibility(8);
        int size = this.f391bp.size();
        for (int i = 0; i < size; i++) {
            ((View) this.f391bp.get(i)).setVisibility(0);
        }
    }

    public void onTransitionPause(Transition transition) {
    }

    public void onTransitionResume(Transition transition) {
    }

    public void onTransitionStart(Transition transition) {
    }
}
