package androidx.appcompat.view.menu;

import android.content.Context;
import android.view.MenuItem;
import android.view.SubMenu;
import androidx.core.internal.p023a.C0329b;
import androidx.core.internal.p023a.C0330c;
import java.util.Iterator;
import java.util.Map;
import p000a.p005b.C0015b;

/* renamed from: androidx.appcompat.view.menu.e */
abstract class C0226e {

    /* renamed from: Dm */
    private Map f235Dm;

    /* renamed from: Em */
    private Map f236Em;
    final Context mContext;

    C0226e(Context context) {
        this.mContext = context;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: I */
    public final void mo1549I(int i) {
        Map map = this.f235Dm;
        if (map != null) {
            Iterator it = map.keySet().iterator();
            while (it.hasNext()) {
                if (i == ((MenuItem) it.next()).getGroupId()) {
                    it.remove();
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: J */
    public final void mo1550J(int i) {
        Map map = this.f235Dm;
        if (map != null) {
            Iterator it = map.keySet().iterator();
            while (it.hasNext()) {
                if (i == ((MenuItem) it.next()).getItemId()) {
                    it.remove();
                    return;
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final MenuItem mo1551a(MenuItem menuItem) {
        if (!(menuItem instanceof C0329b)) {
            return menuItem;
        }
        C0329b bVar = (C0329b) menuItem;
        if (this.f235Dm == null) {
            this.f235Dm = new C0015b();
        }
        MenuItem menuItem2 = (MenuItem) this.f235Dm.get(menuItem);
        if (menuItem2 != null) {
            return menuItem2;
        }
        C0247z zVar = new C0247z(this.mContext, bVar);
        this.f235Dm.put(bVar, zVar);
        return zVar;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: mc */
    public final void mo1553mc() {
        Map map = this.f235Dm;
        if (map != null) {
            map.clear();
        }
        Map map2 = this.f236Em;
        if (map2 != null) {
            map2.clear();
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final SubMenu mo1552a(SubMenu subMenu) {
        if (!(subMenu instanceof C0330c)) {
            return subMenu;
        }
        C0330c cVar = (C0330c) subMenu;
        if (this.f236Em == null) {
            this.f236Em = new C0015b();
        }
        SubMenu subMenu2 = (SubMenu) this.f236Em.get(cVar);
        if (subMenu2 != null) {
            return subMenu2;
        }
        C0221N n = new C0221N(this.mContext, cVar);
        this.f236Em.put(cVar, n);
        return n;
    }
}
