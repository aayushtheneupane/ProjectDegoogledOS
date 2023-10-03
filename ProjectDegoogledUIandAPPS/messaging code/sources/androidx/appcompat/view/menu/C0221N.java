package androidx.appcompat.view.menu;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import androidx.core.internal.p023a.C0330c;

/* renamed from: androidx.appcompat.view.menu.N */
class C0221N extends C0215H implements SubMenu {

    /* renamed from: Gm */
    private final C0330c f223Gm;

    C0221N(Context context, C0330c cVar) {
        super(context, cVar);
        this.f223Gm = cVar;
    }

    public void clearHeader() {
        this.f223Gm.clearHeader();
    }

    public MenuItem getItem() {
        return mo1551a(this.f223Gm.getItem());
    }

    public SubMenu setHeaderIcon(int i) {
        this.f223Gm.setHeaderIcon(i);
        return this;
    }

    public SubMenu setHeaderTitle(int i) {
        this.f223Gm.setHeaderTitle(i);
        return this;
    }

    public SubMenu setHeaderView(View view) {
        this.f223Gm.setHeaderView(view);
        return this;
    }

    public SubMenu setIcon(int i) {
        this.f223Gm.setIcon(i);
        return this;
    }

    public SubMenu setHeaderIcon(Drawable drawable) {
        this.f223Gm.setHeaderIcon(drawable);
        return this;
    }

    public SubMenu setHeaderTitle(CharSequence charSequence) {
        this.f223Gm.setHeaderTitle(charSequence);
        return this;
    }

    public SubMenu setIcon(Drawable drawable) {
        this.f223Gm.setIcon(drawable);
        return this;
    }
}
