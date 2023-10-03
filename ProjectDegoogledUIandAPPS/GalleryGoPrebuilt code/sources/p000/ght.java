package p000;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Handler;

/* renamed from: ght */
/* compiled from: PG */
final class ght extends AnimatorListenerAdapter {

    /* renamed from: a */
    private final /* synthetic */ gik f11393a;

    public ght(gik gik) {
        this.f11393a = gik;
    }

    public final void onAnimationEnd(Animator animator) {
        this.f11393a.mo6720h();
    }

    public final void onAnimationStart(Animator animator) {
        gik gik = this.f11393a;
        Handler handler = gik.f11413a;
        gik.f11419f.mo3674b();
    }
}
