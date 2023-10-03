package p000;

import android.content.Context;
import android.view.MenuItem;
import android.view.SubMenu;
import java.util.Map;

/* renamed from: qu */
/* compiled from: PG */
class C0459qu {

    /* renamed from: a */
    public Map f15696a;

    /* renamed from: b */
    public Map f15697b;

    /* renamed from: c */
    private final Context f15698c;

    public C0459qu(Context context) {
        this.f15698c = context;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final MenuItem mo9793a(MenuItem menuItem) {
        if (!(menuItem instanceof C0255jf)) {
            return menuItem;
        }
        C0255jf jfVar = (C0255jf) menuItem;
        if (this.f15696a == null) {
            this.f15696a = new C0290kn();
        }
        MenuItem menuItem2 = (MenuItem) this.f15696a.get(menuItem);
        if (menuItem2 != null) {
            return menuItem2;
        }
        C0481rp rpVar = new C0481rp(this.f15698c, jfVar);
        this.f15696a.put(jfVar, rpVar);
        return rpVar;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final SubMenu mo9794a(SubMenu subMenu) {
        if (!(subMenu instanceof C0256jg)) {
            return subMenu;
        }
        C0256jg jgVar = (C0256jg) subMenu;
        if (this.f15697b == null) {
            this.f15697b = new C0290kn();
        }
        SubMenu subMenu2 = (SubMenu) this.f15697b.get(jgVar);
        if (subMenu2 != null) {
            return subMenu2;
        }
        C0496sd sdVar = new C0496sd(this.f15698c, jgVar);
        this.f15697b.put(jgVar, sdVar);
        return sdVar;
    }
}
