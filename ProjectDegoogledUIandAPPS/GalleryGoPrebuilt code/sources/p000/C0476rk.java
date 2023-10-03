package p000;

import android.view.ActionProvider;
import android.view.SubMenu;
import android.view.View;

/* renamed from: rk */
/* compiled from: PG */
class C0476rk extends C0317ln {

    /* renamed from: b */
    public final ActionProvider f15806b;

    /* renamed from: c */
    private final /* synthetic */ C0481rp f15807c;

    public C0476rk(C0481rp rpVar, ActionProvider actionProvider) {
        this.f15807c = rpVar;
        this.f15806b = actionProvider;
    }

    /* renamed from: e */
    public final boolean mo9369e() {
        return this.f15806b.hasSubMenu();
    }

    /* renamed from: a */
    public final View mo9362a() {
        return this.f15806b.onCreateActionView();
    }

    /* renamed from: d */
    public final boolean mo9368d() {
        return this.f15806b.onPerformDefaultAction();
    }

    /* renamed from: a */
    public final void mo9364a(SubMenu subMenu) {
        this.f15806b.onPrepareSubMenu(this.f15807c.mo9794a(subMenu));
    }
}
