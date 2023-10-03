package p000;

import android.view.MenuItem;

/* renamed from: rn */
/* compiled from: PG */
final class C0479rn implements MenuItem.OnActionExpandListener {

    /* renamed from: a */
    private final MenuItem.OnActionExpandListener f15810a;

    /* renamed from: b */
    private final /* synthetic */ C0481rp f15811b;

    public C0479rn(C0481rp rpVar, MenuItem.OnActionExpandListener onActionExpandListener) {
        this.f15811b = rpVar;
        this.f15810a = onActionExpandListener;
    }

    public final boolean onMenuItemActionCollapse(MenuItem menuItem) {
        return this.f15810a.onMenuItemActionCollapse(this.f15811b.mo9793a(menuItem));
    }

    public final boolean onMenuItemActionExpand(MenuItem menuItem) {
        return this.f15810a.onMenuItemActionExpand(this.f15811b.mo9793a(menuItem));
    }
}
