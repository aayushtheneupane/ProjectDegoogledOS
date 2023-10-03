package p000;

import android.view.View;

/* renamed from: os */
/* compiled from: PG */
final class C0403os extends C0346mp {

    /* renamed from: a */
    private final /* synthetic */ C0416pe f15423a;

    public C0403os(C0416pe peVar) {
        this.f15423a = peVar;
    }

    /* renamed from: b */
    public final void mo9406b() {
        this.f15423a.f15490i.setAlpha(1.0f);
        this.f15423a.f15493l.mo9402a((C0345mo) null);
        this.f15423a.f15493l = null;
    }

    /* renamed from: c */
    public final void mo9407c() {
        this.f15423a.f15490i.setVisibility(0);
        this.f15423a.f15490i.sendAccessibilityEvent(32);
        if (this.f15423a.f15490i.getParent() instanceof View) {
            C0340mj.m14724o((View) this.f15423a.f15490i.getParent());
        }
    }
}
