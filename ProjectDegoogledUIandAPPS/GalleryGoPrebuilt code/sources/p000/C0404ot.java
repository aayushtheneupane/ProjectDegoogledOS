package p000;

import android.view.Window;

/* renamed from: ot */
/* compiled from: PG */
final class C0404ot implements C0485rt {

    /* renamed from: a */
    private final /* synthetic */ C0416pe f15424a;

    public C0404ot(C0416pe peVar) {
        this.f15424a = peVar;
    }

    /* renamed from: a */
    public final void mo9574a(C0472rg rgVar, boolean z) {
        this.f15424a.mo9608b(rgVar);
    }

    /* renamed from: a */
    public final boolean mo9575a(C0472rg rgVar) {
        Window.Callback p = this.f15424a.mo9613p();
        if (p == null) {
            return true;
        }
        p.onMenuOpened(108, rgVar);
        return true;
    }
}
