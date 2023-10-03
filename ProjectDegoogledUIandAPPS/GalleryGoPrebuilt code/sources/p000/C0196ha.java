package p000;

import android.transition.Transition;

/* renamed from: ha */
/* compiled from: PG */
final class C0196ha implements Transition.TransitionListener {

    /* renamed from: a */
    private final /* synthetic */ Runnable f12392a;

    public C0196ha(Runnable runnable) {
        this.f12392a = runnable;
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
        this.f12392a.run();
    }
}
