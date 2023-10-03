package androidx.appcompat.view.menu;

import android.view.MenuItem;

/* renamed from: androidx.appcompat.view.menu.y */
class C0246y implements MenuItem.OnMenuItemClickListener {
    private final MenuItem.OnMenuItemClickListener mObject;
    final /* synthetic */ C0247z this$0;

    C0246y(C0247z zVar, MenuItem.OnMenuItemClickListener onMenuItemClickListener) {
        this.this$0 = zVar;
        this.mObject = onMenuItemClickListener;
    }

    public boolean onMenuItemClick(MenuItem menuItem) {
        return this.mObject.onMenuItemClick(this.this$0.mo1551a(menuItem));
    }
}
