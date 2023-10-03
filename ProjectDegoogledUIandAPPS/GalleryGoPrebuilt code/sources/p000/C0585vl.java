package p000;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

/* renamed from: vl */
/* compiled from: PG */
final class C0585vl extends AnimatorListenerAdapter {

    /* renamed from: a */
    private boolean f16106a = false;

    /* renamed from: b */
    private final /* synthetic */ C0587vn f16107b;

    public C0585vl(C0587vn vnVar) {
        this.f16107b = vnVar;
    }

    public final void onAnimationCancel(Animator animator) {
        this.f16106a = true;
    }

    public final void onAnimationEnd(Animator animator) {
        if (this.f16106a) {
            this.f16106a = false;
        } else if (((Float) this.f16107b.f16129n.getAnimatedValue()).floatValue() == 0.0f) {
            C0587vn vnVar = this.f16107b;
            vnVar.f16130o = 0;
            vnVar.mo10393a(0);
        } else {
            C0587vn vnVar2 = this.f16107b;
            vnVar2.f16130o = 2;
            vnVar2.mo10392a();
        }
    }
}
