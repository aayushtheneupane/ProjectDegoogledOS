package androidx.appcompat.view.menu;

import android.content.Context;
import android.graphics.Rect;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.HeaderViewListAdapter;
import android.widget.ListAdapter;
import android.widget.PopupWindow;

/* renamed from: androidx.appcompat.view.menu.A */
abstract class C0208A implements C0216I, C0212E, AdapterView.OnItemClickListener {
    private Rect mEpicenterBounds;

    C0208A() {
    }

    /* renamed from: b */
    protected static boolean m190b(C0238q qVar) {
        int size = qVar.size();
        for (int i = 0; i < size; i++) {
            MenuItem item = qVar.getItem(i);
            if (item.isVisible() && item.getIcon() != null) {
                return true;
            }
        }
        return false;
    }

    protected static int measureIndividualMenuWidth(ListAdapter listAdapter, ViewGroup viewGroup, Context context, int i) {
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
        int makeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(0, 0);
        int count = listAdapter.getCount();
        ViewGroup viewGroup2 = viewGroup;
        int i2 = 0;
        int i3 = 0;
        View view = null;
        for (int i4 = 0; i4 < count; i4++) {
            int itemViewType = listAdapter.getItemViewType(i4);
            if (itemViewType != i3) {
                view = null;
                i3 = itemViewType;
            }
            if (viewGroup2 == null) {
                viewGroup2 = new FrameLayout(context);
            }
            view = listAdapter.getView(i4, view, viewGroup2);
            view.measure(makeMeasureSpec, makeMeasureSpec2);
            int measuredWidth = view.getMeasuredWidth();
            if (measuredWidth >= i) {
                return i;
            }
            if (measuredWidth > i2) {
                i2 = measuredWidth;
            }
        }
        return i2;
    }

    /* renamed from: a */
    public abstract void mo1349a(C0238q qVar);

    public boolean collapseItemActionView(C0238q qVar, C0241t tVar) {
        return false;
    }

    public boolean expandItemActionView(C0238q qVar, C0241t tVar) {
        return false;
    }

    public Rect getEpicenterBounds() {
        return this.mEpicenterBounds;
    }

    public int getId() {
        return 0;
    }

    public void initForMenu(Context context, C0238q qVar) {
    }

    /* access modifiers changed from: protected */
    /* renamed from: nc */
    public boolean mo1355nc() {
        return true;
    }

    public void onItemClick(AdapterView adapterView, View view, int i, long j) {
        C0235n nVar;
        ListAdapter listAdapter = (ListAdapter) adapterView.getAdapter();
        if (listAdapter instanceof HeaderViewListAdapter) {
            nVar = (C0235n) ((HeaderViewListAdapter) listAdapter).getWrappedAdapter();
        } else {
            nVar = (C0235n) listAdapter;
        }
        nVar.mAdapterMenu.mo1583a((MenuItem) listAdapter.getItem(i), this, mo1355nc() ? 0 : 4);
    }

    public abstract void setAnchorView(View view);

    public void setEpicenterBounds(Rect rect) {
        this.mEpicenterBounds = rect;
    }

    public abstract void setForceShowIcon(boolean z);

    public abstract void setGravity(int i);

    public abstract void setHorizontalOffset(int i);

    public abstract void setOnDismissListener(PopupWindow.OnDismissListener onDismissListener);

    public abstract void setShowTitle(boolean z);

    public abstract void setVerticalOffset(int i);
}
