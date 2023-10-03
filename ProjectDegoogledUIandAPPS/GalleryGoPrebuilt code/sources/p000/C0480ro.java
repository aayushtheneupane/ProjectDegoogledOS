package p000;

import android.view.MenuItem;

/* renamed from: ro */
/* compiled from: PG */
final class C0480ro implements MenuItem.OnMenuItemClickListener {

    /* renamed from: a */
    private final MenuItem.OnMenuItemClickListener f15812a;

    /* renamed from: b */
    private final /* synthetic */ C0481rp f15813b;

    public C0480ro(C0481rp rpVar, MenuItem.OnMenuItemClickListener onMenuItemClickListener) {
        this.f15813b = rpVar;
        this.f15812a = onMenuItemClickListener;
    }

    public final boolean onMenuItemClick(MenuItem menuItem) {
        return this.f15812a.onMenuItemClick(this.f15813b.mo9793a(menuItem));
    }
}
