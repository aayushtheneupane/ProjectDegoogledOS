package p000;

import android.animation.ValueAnimator;
import android.view.View;

/* renamed from: mm */
/* compiled from: PG */
final class C0343mm implements ValueAnimator.AnimatorUpdateListener {

    /* renamed from: a */
    private final /* synthetic */ C0347mq f15238a;

    public C0343mm(C0347mq mqVar) {
        this.f15238a = mqVar;
    }

    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
        ((View) ((C0432pu) this.f15238a).f15539a.f15550c.getParent()).invalidate();
    }
}
