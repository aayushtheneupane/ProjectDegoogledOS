package p000;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import com.google.android.material.behavior.HideBottomViewOnScrollBehavior;

/* renamed from: gcw */
/* compiled from: PG */
public final class gcw extends AnimatorListenerAdapter {

    /* renamed from: a */
    private final /* synthetic */ HideBottomViewOnScrollBehavior f10975a;

    public gcw(HideBottomViewOnScrollBehavior hideBottomViewOnScrollBehavior) {
        this.f10975a = hideBottomViewOnScrollBehavior;
    }

    public final void onAnimationEnd(Animator animator) {
        this.f10975a.f5145a = null;
    }
}
