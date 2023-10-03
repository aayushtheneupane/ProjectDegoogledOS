package android.support.p002v7.view.menu;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.p000v4.internal.view.SupportSubMenu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;

/* renamed from: android.support.v7.view.menu.SubMenuWrapperICS */
class SubMenuWrapperICS extends MenuWrapperICS implements SubMenu {
    SubMenuWrapperICS(Context context, SupportSubMenu supportSubMenu) {
        super(context, supportSubMenu);
    }

    public void clearHeader() {
        ((SupportSubMenu) this.mWrappedObject).clearHeader();
    }

    public MenuItem getItem() {
        return getMenuItemWrapper(((SupportSubMenu) this.mWrappedObject).getItem());
    }

    public SubMenu setHeaderIcon(int i) {
        ((SupportSubMenu) this.mWrappedObject).setHeaderIcon(i);
        return this;
    }

    public SubMenu setHeaderTitle(int i) {
        ((SupportSubMenu) this.mWrappedObject).setHeaderTitle(i);
        return this;
    }

    public SubMenu setHeaderView(View view) {
        ((SupportSubMenu) this.mWrappedObject).setHeaderView(view);
        return this;
    }

    public SubMenu setIcon(int i) {
        ((SupportSubMenu) this.mWrappedObject).setIcon(i);
        return this;
    }

    public SubMenu setHeaderIcon(Drawable drawable) {
        ((SupportSubMenu) this.mWrappedObject).setHeaderIcon(drawable);
        return this;
    }

    public SubMenu setHeaderTitle(CharSequence charSequence) {
        ((SupportSubMenu) this.mWrappedObject).setHeaderTitle(charSequence);
        return this;
    }

    public SubMenu setIcon(Drawable drawable) {
        ((SupportSubMenu) this.mWrappedObject).setIcon(drawable);
        return this;
    }
}
