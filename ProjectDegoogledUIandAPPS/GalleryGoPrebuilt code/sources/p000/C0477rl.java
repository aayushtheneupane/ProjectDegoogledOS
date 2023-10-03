package p000;

import android.view.ActionProvider;
import android.view.MenuItem;
import android.view.View;

/* renamed from: rl */
/* compiled from: PG */
final class C0477rl extends C0476rk implements ActionProvider.VisibilityListener {

    /* renamed from: c */
    private C0316lm f15808c;

    public C0477rl(C0481rp rpVar, ActionProvider actionProvider) {
        super(rpVar, actionProvider);
    }

    /* renamed from: c */
    public final boolean mo9367c() {
        return this.f15806b.isVisible();
    }

    public final void onActionProviderVisibilityChanged(boolean z) {
        C0316lm lmVar = this.f15808c;
        if (lmVar != null) {
            ((C0474ri) lmVar).f15776a.f15789j.mo9870l();
        }
    }

    /* renamed from: a */
    public final View mo9363a(MenuItem menuItem) {
        return this.f15806b.onCreateActionView(menuItem);
    }

    /* renamed from: b */
    public final boolean mo9366b() {
        return this.f15806b.overridesItemVisibility();
    }

    /* renamed from: a */
    public final void mo9365a(C0316lm lmVar) {
        this.f15808c = lmVar;
        this.f15806b.setVisibilityListener(this);
    }
}
