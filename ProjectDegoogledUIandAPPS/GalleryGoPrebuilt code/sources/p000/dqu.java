package p000;

import android.view.View;
import android.view.animation.Animation;
import java.util.List;

/* renamed from: dqu */
/* compiled from: PG */
final class dqu implements Animation.AnimationListener {

    /* renamed from: a */
    private final /* synthetic */ dqx f7130a;

    public dqu(dqx dqx) {
        this.f7130a = dqx;
    }

    public final void onAnimationEnd(Animation animation) {
    }

    public final void onAnimationRepeat(Animation animation) {
    }

    public final void onAnimationStart(Animation animation) {
        for (iqk a : this.f7130a.f7132a.values()) {
            for (View visibility : (List) a.mo2097a()) {
                visibility.setVisibility(0);
            }
        }
    }
}
