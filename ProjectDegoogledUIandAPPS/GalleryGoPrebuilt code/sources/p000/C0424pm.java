package p000;

import android.view.MenuItem;
import android.view.View;

/* renamed from: pm */
/* compiled from: PG */
final class C0424pm implements C0470re {

    /* renamed from: a */
    private final /* synthetic */ C0426po f15517a;

    public C0424pm(C0426po poVar) {
        this.f15517a = poVar;
    }

    /* renamed from: a */
    public final boolean mo9607a(C0472rg rgVar, MenuItem menuItem) {
        return false;
    }

    /* renamed from: a */
    public final void mo9603a(C0472rg rgVar) {
        C0426po poVar = this.f15517a;
        if (poVar.f15521c == null) {
            return;
        }
        if (poVar.f15519a.mo10337h()) {
            this.f15517a.f15521c.onPanelClosed(108, rgVar);
        } else if (this.f15517a.f15521c.onPreparePanel(0, (View) null, rgVar)) {
            this.f15517a.f15521c.onMenuOpened(108, rgVar);
        }
    }
}
