package p000;

import android.content.Context;
import android.graphics.Rect;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.HeaderViewListAdapter;
import android.widget.ListAdapter;
import android.widget.PopupWindow;

/* renamed from: rq */
/* compiled from: PG */
abstract class C0482rq implements AdapterView.OnItemClickListener, C0490ry, C0486ru {

    /* renamed from: g */
    public Rect f15816g;

    /* renamed from: a */
    public abstract void mo9800a(int i);

    /* renamed from: a */
    public final void mo9785a(Context context, C0472rg rgVar) {
    }

    /* renamed from: a */
    public abstract void mo9801a(View view);

    /* renamed from: a */
    public abstract void mo9802a(PopupWindow.OnDismissListener onDismissListener);

    /* renamed from: a */
    public abstract void mo9803a(C0472rg rgVar);

    /* renamed from: a */
    public abstract void mo9804a(boolean z);

    /* renamed from: a */
    public final boolean mo9789a(C0475rj rjVar) {
        return false;
    }

    /* renamed from: b */
    public abstract void mo9807b(int i);

    /* renamed from: b */
    public abstract void mo9808b(boolean z);

    /* renamed from: b */
    public final boolean mo9792b(C0475rj rjVar) {
        return false;
    }

    /* renamed from: c */
    public abstract void mo9809c(int i);

    /* access modifiers changed from: protected */
    /* renamed from: g */
    public boolean mo9812g() {
        return true;
    }

    /* renamed from: a */
    protected static int m15297a(ListAdapter listAdapter, Context context, int i) {
        int i2 = 0;
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
        int makeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(0, 0);
        int count = listAdapter.getCount();
        FrameLayout frameLayout = null;
        View view = null;
        int i3 = 0;
        int i4 = 0;
        while (i2 < count) {
            int itemViewType = listAdapter.getItemViewType(i2);
            int i5 = itemViewType == i4 ? i4 : itemViewType;
            if (itemViewType != i4) {
                view = null;
            }
            if (frameLayout == null) {
                frameLayout = new FrameLayout(context);
            }
            view = listAdapter.getView(i2, view, frameLayout);
            view.measure(makeMeasureSpec, makeMeasureSpec2);
            int measuredWidth = view.getMeasuredWidth();
            if (measuredWidth >= i) {
                return i;
            }
            if (measuredWidth > i3) {
                i3 = measuredWidth;
            }
            i2++;
            i4 = i5;
        }
        return i3;
    }

    public final void onItemClick(AdapterView adapterView, View view, int i, long j) {
        int i2;
        ListAdapter listAdapter = (ListAdapter) adapterView.getAdapter();
        C0472rg rgVar = m15298a(listAdapter).f15742a;
        MenuItem menuItem = (MenuItem) listAdapter.getItem(i);
        if (!mo9812g()) {
            i2 = 4;
        } else {
            i2 = 0;
        }
        rgVar.mo9837a(menuItem, (C0486ru) this, i2);
    }

    /* renamed from: b */
    protected static boolean m15299b(C0472rg rgVar) {
        int size = rgVar.size();
        for (int i = 0; i < size; i++) {
            MenuItem item = rgVar.getItem(i);
            if (item.isVisible() && item.getIcon() != null) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: a */
    protected static C0469rd m15298a(ListAdapter listAdapter) {
        if (listAdapter instanceof HeaderViewListAdapter) {
            return (C0469rd) ((HeaderViewListAdapter) listAdapter).getWrappedAdapter();
        }
        return (C0469rd) listAdapter;
    }
}
