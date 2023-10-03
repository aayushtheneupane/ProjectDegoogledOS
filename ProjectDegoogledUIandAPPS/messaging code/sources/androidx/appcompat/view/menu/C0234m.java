package androidx.appcompat.view.menu;

import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcelable;
import android.util.SparseArray;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import androidx.appcompat.C0126R;

/* renamed from: androidx.appcompat.view.menu.m */
public class C0234m implements C0212E, AdapterView.OnItemClickListener {

    /* renamed from: Km */
    int f256Km;
    C0233l mAdapter;
    private C0211D mCallback;
    Context mContext;
    private int mId;
    LayoutInflater mInflater;
    int mItemLayoutRes;
    C0238q mMenu;
    ExpandedMenuView mMenuView;
    int mThemeRes = 0;

    public C0234m(Context context, int i) {
        this.mItemLayoutRes = i;
        this.mContext = context;
        this.mInflater = LayoutInflater.from(this.mContext);
    }

    public boolean collapseItemActionView(C0238q qVar, C0241t tVar) {
        return false;
    }

    public boolean expandItemActionView(C0238q qVar, C0241t tVar) {
        return false;
    }

    public boolean flagActionItems() {
        return false;
    }

    public ListAdapter getAdapter() {
        if (this.mAdapter == null) {
            this.mAdapter = new C0233l(this);
        }
        return this.mAdapter;
    }

    public int getId() {
        return this.mId;
    }

    public C0214G getMenuView(ViewGroup viewGroup) {
        if (this.mMenuView == null) {
            this.mMenuView = (ExpandedMenuView) this.mInflater.inflate(C0126R.layout.abc_expanded_menu_layout, viewGroup, false);
            if (this.mAdapter == null) {
                this.mAdapter = new C0233l(this);
            }
            this.mMenuView.setAdapter(this.mAdapter);
            this.mMenuView.setOnItemClickListener(this);
        }
        return this.mMenuView;
    }

    public void initForMenu(Context context, C0238q qVar) {
        int i = this.mThemeRes;
        if (i != 0) {
            this.mContext = new ContextThemeWrapper(context, i);
            this.mInflater = LayoutInflater.from(this.mContext);
        } else if (this.mContext != null) {
            this.mContext = context;
            if (this.mInflater == null) {
                this.mInflater = LayoutInflater.from(this.mContext);
            }
        }
        this.mMenu = qVar;
        C0233l lVar = this.mAdapter;
        if (lVar != null) {
            lVar.notifyDataSetChanged();
        }
    }

    public void onCloseMenu(C0238q qVar, boolean z) {
        C0211D d = this.mCallback;
        if (d != null) {
            d.onCloseMenu(qVar, z);
        }
    }

    public void onItemClick(AdapterView adapterView, View view, int i, long j) {
        this.mMenu.mo1583a(this.mAdapter.getItem(i), this, 0);
    }

    public void onRestoreInstanceState(Parcelable parcelable) {
        SparseArray sparseParcelableArray = ((Bundle) parcelable).getSparseParcelableArray("android:menu:list");
        if (sparseParcelableArray != null) {
            this.mMenuView.restoreHierarchyState(sparseParcelableArray);
        }
    }

    public Parcelable onSaveInstanceState() {
        if (this.mMenuView == null) {
            return null;
        }
        Bundle bundle = new Bundle();
        SparseArray sparseArray = new SparseArray();
        ExpandedMenuView expandedMenuView = this.mMenuView;
        if (expandedMenuView != null) {
            expandedMenuView.saveHierarchyState(sparseArray);
        }
        bundle.putSparseParcelableArray("android:menu:list", sparseArray);
        return bundle;
    }

    public boolean onSubMenuSelected(C0220M m) {
        if (!m.hasVisibleItems()) {
            return false;
        }
        new C0239r(m).show((IBinder) null);
        C0211D d = this.mCallback;
        if (d == null) {
            return true;
        }
        d.onOpenSubMenu(m);
        return true;
    }

    public void setCallback(C0211D d) {
        this.mCallback = d;
    }

    public void updateMenuView(boolean z) {
        C0233l lVar = this.mAdapter;
        if (lVar != null) {
            lVar.notifyDataSetChanged();
        }
    }
}
