package p000;

import android.animation.ValueAnimator;

/* renamed from: vj */
/* compiled from: PG */
final class C0583vj implements Runnable {

    /* renamed from: a */
    private final /* synthetic */ C0587vn f16104a;

    public C0583vj(C0587vn vnVar) {
        this.f16104a = vnVar;
    }

    public final void run() {
        C0587vn vnVar = this.f16104a;
        int i = vnVar.f16130o;
        if (i == 1) {
            vnVar.f16129n.cancel();
        } else if (i != 2) {
            return;
        }
        vnVar.f16130o = 3;
        ValueAnimator valueAnimator = vnVar.f16129n;
        valueAnimator.setFloatValues(new float[]{((Float) valueAnimator.getAnimatedValue()).floatValue(), 0.0f});
        vnVar.f16129n.setDuration(500);
        vnVar.f16129n.start();
    }
}
