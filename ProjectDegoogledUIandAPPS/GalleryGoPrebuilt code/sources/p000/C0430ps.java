package p000;

import android.support.p002v7.widget.ActionBarOverlayLayout;
import android.view.View;

/* renamed from: ps */
/* compiled from: PG */
final class C0430ps extends C0346mp {

    /* renamed from: a */
    private final /* synthetic */ C0434pw f15537a;

    public C0430ps(C0434pw pwVar) {
        this.f15537a = pwVar;
    }

    /* renamed from: b */
    public final void mo9406b() {
        View view;
        C0434pw pwVar = this.f15537a;
        if (pwVar.f15558k && (view = pwVar.f15553f) != null) {
            view.setTranslationY(0.0f);
            this.f15537a.f15550c.setTranslationY(0.0f);
        }
        this.f15537a.f15550c.setVisibility(8);
        this.f15537a.f15550c.mo781a(false);
        C0434pw pwVar2 = this.f15537a;
        pwVar2.f15561n = null;
        C0442qd qdVar = pwVar2.f15556i;
        if (qdVar != null) {
            qdVar.mo9576a(pwVar2.f15555h);
            pwVar2.f15555h = null;
            pwVar2.f15556i = null;
        }
        ActionBarOverlayLayout actionBarOverlayLayout = this.f15537a.f15549b;
        if (actionBarOverlayLayout != null) {
            C0340mj.m14724o(actionBarOverlayLayout);
        }
    }
}
