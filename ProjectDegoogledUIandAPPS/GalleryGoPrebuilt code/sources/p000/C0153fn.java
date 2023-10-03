package p000;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;
import android.view.ViewGroup;

/* renamed from: fn */
/* compiled from: PG */
final class C0153fn extends AnimatorListenerAdapter {

    /* renamed from: a */
    private final /* synthetic */ ViewGroup f10073a;

    /* renamed from: b */
    private final /* synthetic */ View f10074b;

    /* renamed from: c */
    private final /* synthetic */ C0147fh f10075c;

    /* renamed from: d */
    private final /* synthetic */ C0189gu f10076d;

    /* renamed from: e */
    private final /* synthetic */ C0259jj f10077e;

    public C0153fn(ViewGroup viewGroup, View view, C0147fh fhVar, C0189gu guVar, C0259jj jjVar) {
        this.f10073a = viewGroup;
        this.f10074b = view;
        this.f10075c = fhVar;
        this.f10076d = guVar;
        this.f10077e = jjVar;
    }

    public final void onAnimationEnd(Animator animator) {
        this.f10073a.endViewTransition(this.f10074b);
        C0147fh fhVar = this.f10075c;
        C0145ff ffVar = fhVar.f9576O;
        Animator animator2 = ffVar != null ? ffVar.f9424b : null;
        fhVar.mo5636a((Animator) null);
        if (animator2 != null && this.f10073a.indexOfChild(this.f10074b) < 0) {
            this.f10076d.mo6297b(this.f10075c, this.f10077e);
        }
    }
}
