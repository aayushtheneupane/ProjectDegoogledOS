package androidx.appcompat.view.menu;

import android.widget.ListView;
import androidx.appcompat.widget.MenuPopupWindow;

/* renamed from: androidx.appcompat.view.menu.j */
class C0231j {
    public final C0238q menu;
    public final int position;
    public final MenuPopupWindow window;

    public C0231j(MenuPopupWindow menuPopupWindow, C0238q qVar, int i) {
        this.window = menuPopupWindow;
        this.menu = qVar;
        this.position = i;
    }

    public ListView getListView() {
        return this.window.getListView();
    }
}
