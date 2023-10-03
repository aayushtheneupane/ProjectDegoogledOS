package p000;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;
import android.view.ViewGroup;

/* renamed from: ga */
/* compiled from: PG */
final class C0167ga extends AnimatorListenerAdapter {

    /* renamed from: a */
    private final /* synthetic */ ViewGroup f10769a;

    /* renamed from: b */
    private final /* synthetic */ View f10770b;

    /* renamed from: c */
    private final /* synthetic */ C0147fh f10771c;

    public C0167ga(ViewGroup viewGroup, View view, C0147fh fhVar) {
        this.f10769a = viewGroup;
        this.f10770b = view;
        this.f10771c = fhVar;
    }

    public final void onAnimationEnd(Animator animator) {
        this.f10769a.endViewTransition(this.f10770b);
        animator.removeListener(this);
        C0147fh fhVar = this.f10771c;
        View view = fhVar.f9573L;
        if (view != null && fhVar.f9565D) {
            view.setVisibility(8);
        }
    }
}
