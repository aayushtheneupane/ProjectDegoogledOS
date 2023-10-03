package p000;

import android.view.ViewTreeObserver;

/* renamed from: ua */
/* compiled from: PG */
final class C0547ua implements ViewTreeObserver.OnGlobalLayoutListener {

    /* renamed from: a */
    private final /* synthetic */ C0549uc f15972a;

    public C0547ua(C0549uc ucVar) {
        this.f15972a = ucVar;
    }

    public final void onGlobalLayout() {
        C0549uc ucVar = this.f15972a;
        C0553ug ugVar = ucVar.f15978d;
        if (!C0340mj.m14735z(ugVar) || !ugVar.getGlobalVisibleRect(ucVar.f15977c)) {
            this.f15972a.mo9810d();
            return;
        }
        this.f15972a.mo10210g();
        C0547ua.super.mo9805ab();
    }
}
