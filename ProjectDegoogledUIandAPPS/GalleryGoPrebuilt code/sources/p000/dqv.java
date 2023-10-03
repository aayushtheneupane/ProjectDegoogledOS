package p000;

import android.view.View;
import android.view.animation.Animation;
import java.util.List;

/* renamed from: dqv */
/* compiled from: PG */
final class dqv implements Animation.AnimationListener {

    /* renamed from: a */
    private final /* synthetic */ dqx f7131a;

    public dqv(dqx dqx) {
        this.f7131a = dqx;
    }

    public final void onAnimationRepeat(Animation animation) {
    }

    public final void onAnimationStart(Animation animation) {
    }

    public final void onAnimationEnd(Animation animation) {
        for (iqk a : this.f7131a.f7132a.values()) {
            for (View visibility : (List) a.mo2097a()) {
                visibility.setVisibility(8);
            }
        }
    }
}
