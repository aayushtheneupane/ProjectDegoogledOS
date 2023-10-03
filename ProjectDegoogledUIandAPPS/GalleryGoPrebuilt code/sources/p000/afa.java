package p000;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.graphics.Paint;
import android.view.View;

/* renamed from: afa */
/* compiled from: PG */
final class afa extends AnimatorListenerAdapter {

    /* renamed from: a */
    private final View f298a;

    /* renamed from: b */
    private boolean f299b = false;

    public afa(View view) {
        this.f298a = view;
    }

    public final void onAnimationEnd(Animator animator) {
        agb.m421a(this.f298a, 1.0f);
        if (this.f299b) {
            this.f298a.setLayerType(0, (Paint) null);
        }
    }

    public final void onAnimationStart(Animator animator) {
        if (C0340mj.m14726q(this.f298a) && this.f298a.getLayerType() == 0) {
            this.f299b = true;
            this.f298a.setLayerType(2, (Paint) null);
        }
    }
}
