package androidx.appcompat.view.menu;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;

/* renamed from: androidx.appcompat.view.menu.M */
public class C0220M extends C0238q implements SubMenu {

    /* renamed from: en */
    private C0238q f221en;

    /* renamed from: fn */
    private C0241t f222fn;

    public C0220M(Context context, C0238q qVar, C0241t tVar) {
        super(context);
        this.f221en = qVar;
        this.f222fn = tVar;
    }

    /* renamed from: a */
    public void mo1450a(C0236o oVar) {
        this.f221en.mo1450a(oVar);
    }

    /* renamed from: b */
    public boolean mo1453b(C0241t tVar) {
        return this.f221en.mo1453b(tVar);
    }

    public String getActionViewStatesKey() {
        C0241t tVar = this.f222fn;
        int itemId = tVar != null ? tVar.getItemId() : 0;
        if (itemId == 0) {
            return null;
        }
        return "android:menu:actionviewstates" + ":" + itemId;
    }

    public MenuItem getItem() {
        return this.f222fn;
    }

    public Menu getParentMenu() {
        return this.f221en;
    }

    public C0238q getRootMenu() {
        return this.f221en.getRootMenu();
    }

    public boolean isGroupDividerEnabled() {
        return this.f221en.isGroupDividerEnabled();
    }

    public boolean isQwertyMode() {
        return this.f221en.isQwertyMode();
    }

    public boolean isShortcutsVisible() {
        return this.f221en.isShortcutsVisible();
    }

    public void setGroupDividerEnabled(boolean z) {
        this.f221en.setGroupDividerEnabled(z);
    }

    public SubMenu setHeaderIcon(Drawable drawable) {
        super.setHeaderIconInt(drawable);
        return this;
    }

    public SubMenu setHeaderTitle(CharSequence charSequence) {
        super.setHeaderTitleInt(charSequence);
        return this;
    }

    public SubMenu setHeaderView(View view) {
        super.setHeaderViewInt(view);
        return this;
    }

    public SubMenu setIcon(Drawable drawable) {
        this.f222fn.setIcon(drawable);
        return this;
    }

    public void setQwertyMode(boolean z) {
        this.f221en.setQwertyMode(z);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public boolean mo1451a(C0238q qVar, MenuItem menuItem) {
        return super.mo1451a(qVar, menuItem) || this.f221en.mo1451a(qVar, menuItem);
    }

    public SubMenu setHeaderIcon(int i) {
        super.setHeaderIconInt(i);
        return this;
    }

    public SubMenu setHeaderTitle(int i) {
        super.setHeaderTitleInt(i);
        return this;
    }

    public SubMenu setIcon(int i) {
        this.f222fn.setIcon(i);
        return this;
    }

    /* renamed from: a */
    public boolean mo1452a(C0241t tVar) {
        return this.f221en.mo1452a(tVar);
    }
}
