package p000;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

/* renamed from: fgg */
/* compiled from: PG */
public class fgg extends AnimatorListenerAdapter {

    /* renamed from: a */
    private final C0309lf f9507a = new C0309lf();

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final boolean mo5583a(Animator animator) {
        return this.f9507a.containsKey(animator) && ((Boolean) this.f9507a.get(animator)).booleanValue();
    }

    public final void onAnimationCancel(Animator animator) {
        this.f9507a.put(animator, true);
    }

    public final void onAnimationStart(Animator animator) {
        this.f9507a.put(animator, false);
    }
}
