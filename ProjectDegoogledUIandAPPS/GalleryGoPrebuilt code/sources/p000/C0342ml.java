package p000;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

/* renamed from: ml */
/* compiled from: PG */
final class C0342ml extends AnimatorListenerAdapter {

    /* renamed from: a */
    private final /* synthetic */ C0345mo f15237a;

    public C0342ml(C0345mo moVar) {
        this.f15237a = moVar;
    }

    public final void onAnimationCancel(Animator animator) {
        this.f15237a.mo9405a();
    }

    public final void onAnimationEnd(Animator animator) {
        this.f15237a.mo9406b();
    }

    public final void onAnimationStart(Animator animator) {
        this.f15237a.mo9407c();
    }
}
