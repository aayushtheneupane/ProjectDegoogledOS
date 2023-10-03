package p000;

import android.view.ViewGroup;
import android.view.animation.Animation;

/* renamed from: fm */
/* compiled from: PG */
final class C0152fm implements Animation.AnimationListener {

    /* renamed from: a */
    public final /* synthetic */ C0147fh f10012a;

    /* renamed from: b */
    public final /* synthetic */ C0189gu f10013b;

    /* renamed from: c */
    public final /* synthetic */ C0259jj f10014c;

    /* renamed from: d */
    private final /* synthetic */ ViewGroup f10015d;

    public C0152fm(ViewGroup viewGroup, C0147fh fhVar, C0189gu guVar, C0259jj jjVar) {
        this.f10015d = viewGroup;
        this.f10012a = fhVar;
        this.f10013b = guVar;
        this.f10014c = jjVar;
    }

    public final void onAnimationRepeat(Animation animation) {
    }

    public final void onAnimationStart(Animation animation) {
    }

    public final void onAnimationEnd(Animation animation) {
        this.f10015d.post(new C0151fl(this));
    }
}
