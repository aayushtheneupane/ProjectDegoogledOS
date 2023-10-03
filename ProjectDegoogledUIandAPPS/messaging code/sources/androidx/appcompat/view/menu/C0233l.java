package androidx.appcompat.view.menu;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import java.util.ArrayList;

/* renamed from: androidx.appcompat.view.menu.l */
class C0233l extends BaseAdapter {
    final /* synthetic */ C0234m this$0;

    /* renamed from: tl */
    private int f255tl = -1;

    public C0233l(C0234m mVar) {
        this.this$0 = mVar;
        findExpandedIndex();
    }

    /* access modifiers changed from: package-private */
    public void findExpandedIndex() {
        C0241t expandedItem = this.this$0.mMenu.getExpandedItem();
        if (expandedItem != null) {
            ArrayList nonActionItems = this.this$0.mMenu.getNonActionItems();
            int size = nonActionItems.size();
            for (int i = 0; i < size; i++) {
                if (((C0241t) nonActionItems.get(i)) == expandedItem) {
                    this.f255tl = i;
                    return;
                }
            }
        }
        this.f255tl = -1;
    }

    public int getCount() {
        int size = this.this$0.mMenu.getNonActionItems().size() - this.this$0.f256Km;
        return this.f255tl < 0 ? size : size - 1;
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            C0234m mVar = this.this$0;
            view = mVar.mInflater.inflate(mVar.mItemLayoutRes, viewGroup, false);
        }
        ((C0213F) view).mo1367a(getItem(i), 0);
        return view;
    }

    public void notifyDataSetChanged() {
        findExpandedIndex();
        super.notifyDataSetChanged();
    }

    public C0241t getItem(int i) {
        ArrayList nonActionItems = this.this$0.mMenu.getNonActionItems();
        int i2 = i + this.this$0.f256Km;
        int i3 = this.f255tl;
        if (i3 >= 0 && i2 >= i3) {
            i2++;
        }
        return (C0241t) nonActionItems.get(i2);
    }
}
