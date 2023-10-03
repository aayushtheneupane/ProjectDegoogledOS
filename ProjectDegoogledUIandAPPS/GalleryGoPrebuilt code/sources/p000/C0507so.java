package p000;

import android.view.View;

/* renamed from: so */
/* compiled from: PG */
final class C0507so implements Runnable {

    /* renamed from: a */
    private final C0510sr f15873a;

    /* renamed from: b */
    private final /* synthetic */ C0512st f15874b;

    public C0507so(C0512st stVar, C0510sr srVar) {
        this.f15874b = stVar;
        this.f15873a = srVar;
    }

    public final void run() {
        C0470re reVar;
        C0472rg rgVar = this.f15874b.f15692c;
        if (!(rgVar == null || (reVar = rgVar.f15750b) == null)) {
            reVar.mo9603a(rgVar);
        }
        View view = (View) this.f15874b.f15695f;
        if (!(view == null || view.getWindowToken() == null || !this.f15873a.mo9999b())) {
            this.f15874b.f15881i = this.f15873a;
        }
        this.f15874b.f15883k = null;
    }
}
