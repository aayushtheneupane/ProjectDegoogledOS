package androidx.appcompat.view.menu;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import java.util.ArrayList;

/* renamed from: androidx.appcompat.view.menu.n */
public class C0235n extends BaseAdapter {
    C0238q mAdapterMenu;
    private boolean mForceShowIcon;
    private final LayoutInflater mInflater;
    private final int mItemLayoutRes;
    private final boolean mOverflowOnly;

    /* renamed from: tl */
    private int f257tl = -1;

    public C0235n(C0238q qVar, LayoutInflater layoutInflater, boolean z, int i) {
        this.mOverflowOnly = z;
        this.mInflater = layoutInflater;
        this.mAdapterMenu = qVar;
        this.mItemLayoutRes = i;
        findExpandedIndex();
    }

    /* access modifiers changed from: package-private */
    public void findExpandedIndex() {
        C0241t expandedItem = this.mAdapterMenu.getExpandedItem();
        if (expandedItem != null) {
            ArrayList nonActionItems = this.mAdapterMenu.getNonActionItems();
            int size = nonActionItems.size();
            for (int i = 0; i < size; i++) {
                if (((C0241t) nonActionItems.get(i)) == expandedItem) {
                    this.f257tl = i;
                    return;
                }
            }
        }
        this.f257tl = -1;
    }

    public C0238q getAdapterMenu() {
        return this.mAdapterMenu;
    }

    public int getCount() {
        ArrayList nonActionItems = this.mOverflowOnly ? this.mAdapterMenu.getNonActionItems() : this.mAdapterMenu.getVisibleItems();
        if (this.f257tl < 0) {
            return nonActionItems.size();
        }
        return nonActionItems.size() - 1;
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = this.mInflater.inflate(this.mItemLayoutRes, viewGroup, false);
        }
        int groupId = getItem(i).getGroupId();
        int i2 = i - 1;
        ListMenuItemView listMenuItemView = (ListMenuItemView) view;
        listMenuItemView.setGroupDividerEnabled(this.mAdapterMenu.isGroupDividerEnabled() && groupId != (i2 >= 0 ? getItem(i2).getGroupId() : groupId));
        C0213F f = (C0213F) view;
        if (this.mForceShowIcon) {
            listMenuItemView.setForceShowIcon(true);
        }
        f.mo1367a(getItem(i), 0);
        return view;
    }

    public void notifyDataSetChanged() {
        findExpandedIndex();
        super.notifyDataSetChanged();
    }

    public void setForceShowIcon(boolean z) {
        this.mForceShowIcon = z;
    }

    public C0241t getItem(int i) {
        ArrayList nonActionItems = this.mOverflowOnly ? this.mAdapterMenu.getNonActionItems() : this.mAdapterMenu.getVisibleItems();
        int i2 = this.f257tl;
        if (i2 >= 0 && i >= i2) {
            i++;
        }
        return (C0241t) nonActionItems.get(i);
    }
}
