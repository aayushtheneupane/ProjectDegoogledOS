package p000;

import android.view.Menu;
import android.view.Window;

/* renamed from: pd */
/* compiled from: PG */
final class C0415pd implements C0485rt {

    /* renamed from: a */
    private final /* synthetic */ C0416pe f15455a;

    public C0415pd(C0416pe peVar) {
        this.f15455a = peVar;
    }

    /* renamed from: a */
    public final void mo9574a(C0472rg rgVar, boolean z) {
        C0472rg j = rgVar.mo9868j();
        C0414pc a = this.f15455a.mo9600a((Menu) j != rgVar ? j : rgVar);
        if (a == null) {
            return;
        }
        if (j != rgVar) {
            this.f15455a.mo9601a(a.f15438a, a, (Menu) j);
            this.f15455a.mo9602a(a, true);
            return;
        }
        this.f15455a.mo9602a(a, z);
    }

    /* renamed from: a */
    public final boolean mo9575a(C0472rg rgVar) {
        Window.Callback p;
        if (rgVar != null) {
            return true;
        }
        C0416pe peVar = this.f15455a;
        if (!peVar.f15497p || (p = peVar.mo9613p()) == null || this.f15455a.f15501t) {
            return true;
        }
        p.onMenuOpened(108, (Menu) null);
        return true;
    }
}
