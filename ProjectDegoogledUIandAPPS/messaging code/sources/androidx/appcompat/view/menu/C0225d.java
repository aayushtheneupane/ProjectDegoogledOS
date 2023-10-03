package androidx.appcompat.view.menu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;

/* renamed from: androidx.appcompat.view.menu.d */
public abstract class C0225d implements C0212E {
    private C0211D mCallback;
    protected Context mContext;
    private int mId;
    protected LayoutInflater mInflater;
    private int mItemLayoutRes;
    protected C0238q mMenu;
    private int mMenuLayoutRes;
    protected C0214G mMenuView;
    protected Context mSystemContext;
    protected LayoutInflater mSystemInflater;

    public C0225d(Context context, int i, int i2) {
        this.mSystemContext = context;
        this.mSystemInflater = LayoutInflater.from(context);
        this.mMenuLayoutRes = i;
        this.mItemLayoutRes = i2;
    }

    /* access modifiers changed from: protected */
    public void addItemView(View view, int i) {
        ViewGroup viewGroup = (ViewGroup) view.getParent();
        if (viewGroup != null) {
            viewGroup.removeView(view);
        }
        ((ViewGroup) this.mMenuView).addView(view, i);
    }

    public abstract void bindItemView(C0241t tVar, C0213F f);

    public boolean collapseItemActionView(C0238q qVar, C0241t tVar) {
        return false;
    }

    public C0213F createItemView(ViewGroup viewGroup) {
        return (C0213F) this.mSystemInflater.inflate(this.mItemLayoutRes, viewGroup, false);
    }

    public boolean expandItemActionView(C0238q qVar, C0241t tVar) {
        return false;
    }

    /* access modifiers changed from: protected */
    public abstract boolean filterLeftoverView(ViewGroup viewGroup, int i);

    public abstract boolean flagActionItems();

    public C0211D getCallback() {
        return this.mCallback;
    }

    public int getId() {
        return this.mId;
    }

    public View getItemView(C0241t tVar, View view, ViewGroup viewGroup) {
        C0213F f;
        if (view instanceof C0213F) {
            f = (C0213F) view;
        } else {
            f = createItemView(viewGroup);
        }
        bindItemView(tVar, f);
        return (View) f;
    }

    public C0214G getMenuView(ViewGroup viewGroup) {
        if (this.mMenuView == null) {
            this.mMenuView = (C0214G) this.mSystemInflater.inflate(this.mMenuLayoutRes, viewGroup, false);
            this.mMenuView.initialize(this.mMenu);
            updateMenuView(true);
        }
        return this.mMenuView;
    }

    public void initForMenu(Context context, C0238q qVar) {
        this.mContext = context;
        this.mInflater = LayoutInflater.from(this.mContext);
        this.mMenu = qVar;
    }

    public void onCloseMenu(C0238q qVar, boolean z) {
        C0211D d = this.mCallback;
        if (d != null) {
            d.onCloseMenu(qVar, z);
        }
    }

    public boolean onSubMenuSelected(C0220M m) {
        C0211D d = this.mCallback;
        if (d != null) {
            return d.onOpenSubMenu(m);
        }
        return false;
    }

    public void setCallback(C0211D d) {
        this.mCallback = d;
    }

    public void setId(int i) {
        this.mId = i;
    }

    public abstract boolean shouldIncludeItem(int i, C0241t tVar);

    public void updateMenuView(boolean z) {
        ViewGroup viewGroup = (ViewGroup) this.mMenuView;
        if (viewGroup != null) {
            C0238q qVar = this.mMenu;
            int i = 0;
            if (qVar != null) {
                qVar.flagActionItems();
                ArrayList visibleItems = this.mMenu.getVisibleItems();
                int size = visibleItems.size();
                int i2 = 0;
                for (int i3 = 0; i3 < size; i3++) {
                    C0241t tVar = (C0241t) visibleItems.get(i3);
                    if (shouldIncludeItem(i2, tVar)) {
                        View childAt = viewGroup.getChildAt(i2);
                        C0241t itemData = childAt instanceof C0213F ? ((C0213F) childAt).getItemData() : null;
                        View itemView = getItemView(tVar, childAt, viewGroup);
                        if (tVar != itemData) {
                            itemView.setPressed(false);
                            itemView.jumpDrawablesToCurrentState();
                        }
                        if (itemView != childAt) {
                            addItemView(itemView, i2);
                        }
                        i2++;
                    }
                }
                i = i2;
            }
            while (i < viewGroup.getChildCount()) {
                if (!filterLeftoverView(viewGroup, i)) {
                    i++;
                }
            }
        }
    }
}
