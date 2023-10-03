package androidx.fragment.app;

import android.animation.Animator;
import android.view.View;
import androidx.core.app.SharedElementCallback;

/* renamed from: androidx.fragment.app.g */
class C0419g {
    View mAnimatingAway;
    Animator mAnimator;
    SharedElementCallback mEnterTransitionCallback;
    boolean mEnterTransitionPostponed;
    SharedElementCallback mExitTransitionCallback;
    boolean mIsHideReplaced;
    int mNextAnim;
    int mNextTransition;
    int mNextTransitionStyle;
    C0421h mStartEnterTransitionListener;
    int mStateAfterAnimating;

    /* renamed from: ro */
    Object f408ro = null;

    /* renamed from: so */
    Object f409so;

    /* renamed from: uo */
    Object f410uo;

    /* renamed from: vo */
    Object f411vo;

    /* renamed from: wo */
    Object f412wo;

    /* renamed from: xo */
    Object f413xo;

    /* renamed from: yo */
    Boolean f414yo;

    /* renamed from: zo */
    Boolean f415zo;

    C0419g() {
        Object obj = C0424j.USE_DEFAULT_TRANSITION;
        this.f409so = obj;
        this.f410uo = null;
        this.f411vo = obj;
        this.f412wo = null;
        this.f413xo = obj;
        this.mEnterTransitionCallback = null;
        this.mExitTransitionCallback = null;
    }
}
