package android.support.p002v7.view.menu;

/* renamed from: android.support.v7.view.menu.MenuView */
public interface MenuView {

    /* renamed from: android.support.v7.view.menu.MenuView$ItemView */
    public interface ItemView {
        MenuItemImpl getItemData();

        void initialize(MenuItemImpl menuItemImpl, int i);

        boolean prefersCondensedTitle();
    }

    void initialize(MenuBuilder menuBuilder);
}
