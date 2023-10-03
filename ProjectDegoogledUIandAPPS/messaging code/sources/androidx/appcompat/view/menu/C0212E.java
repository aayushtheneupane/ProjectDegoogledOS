package androidx.appcompat.view.menu;

import android.content.Context;
import android.os.Parcelable;

/* renamed from: androidx.appcompat.view.menu.E */
public interface C0212E {
    boolean collapseItemActionView(C0238q qVar, C0241t tVar);

    boolean expandItemActionView(C0238q qVar, C0241t tVar);

    boolean flagActionItems();

    int getId();

    void initForMenu(Context context, C0238q qVar);

    void onCloseMenu(C0238q qVar, boolean z);

    void onRestoreInstanceState(Parcelable parcelable);

    Parcelable onSaveInstanceState();

    boolean onSubMenuSelected(C0220M m);

    void setCallback(C0211D d);

    void updateMenuView(boolean z);
}
