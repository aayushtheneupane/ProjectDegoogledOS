package androidx.appcompat.view.menu;

import android.view.MenuItem;

/* renamed from: androidx.appcompat.view.menu.x */
class C0245x implements MenuItem.OnActionExpandListener {
    private final MenuItem.OnActionExpandListener mObject;
    final /* synthetic */ C0247z this$0;

    C0245x(C0247z zVar, MenuItem.OnActionExpandListener onActionExpandListener) {
        this.this$0 = zVar;
        this.mObject = onActionExpandListener;
    }

    public boolean onMenuItemActionCollapse(MenuItem menuItem) {
        return this.mObject.onMenuItemActionCollapse(this.this$0.mo1551a(menuItem));
    }

    public boolean onMenuItemActionExpand(MenuItem menuItem) {
        return this.mObject.onMenuItemActionExpand(this.this$0.mo1551a(menuItem));
    }
}
