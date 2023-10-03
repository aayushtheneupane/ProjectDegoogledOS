package android.support.p002v7.view.menu;

import android.content.Context;
import android.os.Build;
import android.support.p000v4.internal.view.SupportMenu;
import android.support.p000v4.internal.view.SupportMenuItem;
import android.view.Menu;
import android.view.MenuItem;

/* renamed from: android.support.v7.view.menu.MenuWrapperFactory */
public final class MenuWrapperFactory {
    public static Menu wrapSupportMenu(Context context, SupportMenu supportMenu) {
        return new MenuWrapperICS(context, supportMenu);
    }

    public static MenuItem wrapSupportMenuItem(Context context, SupportMenuItem supportMenuItem) {
        int i = Build.VERSION.SDK_INT;
        return new MenuItemWrapperJB(context, supportMenuItem);
    }
}
