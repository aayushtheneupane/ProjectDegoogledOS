package p000;

import android.animation.ValueAnimator;
import android.view.View;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.google.android.material.appbar.AppBarLayout;

/* renamed from: gcl */
/* compiled from: PG */
public final class gcl implements ValueAnimator.AnimatorUpdateListener {

    /* renamed from: a */
    private final /* synthetic */ CoordinatorLayout f10943a;

    /* renamed from: b */
    private final /* synthetic */ AppBarLayout f10944b;

    /* renamed from: c */
    private final /* synthetic */ AppBarLayout.BaseBehavior f10945c;

    public gcl(AppBarLayout.BaseBehavior baseBehavior, CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout) {
        this.f10945c = baseBehavior;
        this.f10943a = coordinatorLayout;
        this.f10944b = appBarLayout;
    }

    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
        this.f10945c.mo6399b(this.f10943a, (View) this.f10944b, ((Integer) valueAnimator.getAnimatedValue()).intValue());
    }
}
