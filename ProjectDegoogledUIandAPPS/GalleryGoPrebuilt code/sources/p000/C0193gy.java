package p000;

import android.transition.Transition;
import android.view.View;
import java.util.ArrayList;

/* renamed from: gy */
/* compiled from: PG */
final class C0193gy implements Transition.TransitionListener {

    /* renamed from: a */
    private final /* synthetic */ View f12283a;

    /* renamed from: b */
    private final /* synthetic */ ArrayList f12284b;

    public C0193gy(View view, ArrayList arrayList) {
        this.f12283a = view;
        this.f12284b = arrayList;
    }

    public final void onTransitionCancel(Transition transition) {
    }

    public final void onTransitionPause(Transition transition) {
    }

    public final void onTransitionResume(Transition transition) {
    }

    public final void onTransitionStart(Transition transition) {
    }

    public final void onTransitionEnd(Transition transition) {
        transition.removeListener(this);
        this.f12283a.setVisibility(8);
        int size = this.f12284b.size();
        for (int i = 0; i < size; i++) {
            ((View) this.f12284b.get(i)).setVisibility(0);
        }
    }
}
