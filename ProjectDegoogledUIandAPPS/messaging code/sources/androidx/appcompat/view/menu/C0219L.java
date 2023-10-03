package androidx.appcompat.view.menu;

import android.content.Context;
import android.content.res.Resources;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import androidx.appcompat.C0126R;
import androidx.appcompat.widget.MenuPopupWindow;
import androidx.core.view.ViewCompat;

/* renamed from: androidx.appcompat.view.menu.L */
final class C0219L extends C0208A implements PopupWindow.OnDismissListener, AdapterView.OnItemClickListener, C0212E, View.OnKeyListener {

    /* renamed from: Hn */
    private static final int f202Hn = C0126R.layout.abc_popup_menu_item_layout;

    /* renamed from: Bn */
    View f203Bn;

    /* renamed from: In */
    private final int f204In;

    /* renamed from: Jn */
    private boolean f205Jn;

    /* renamed from: Kn */
    private boolean f206Kn;

    /* renamed from: Ln */
    private int f207Ln;
    private final C0235n mAdapter;
    private View mAnchorView;
    private final Context mContext;
    private int mDropDownGravity = 0;
    private final C0238q mMenu;
    private PopupWindow.OnDismissListener mOnDismissListener;
    private final boolean mOverflowOnly;
    final MenuPopupWindow mPopup;
    private final int mPopupStyleAttr;
    private final int mPopupStyleRes;
    private C0211D mPresenterCallback;
    private boolean mShowTitle;
    ViewTreeObserver mTreeObserver;

    /* renamed from: xn */
    final ViewTreeObserver.OnGlobalLayoutListener f208xn = new C0217J(this);

    /* renamed from: yn */
    private final View.OnAttachStateChangeListener f209yn = new C0218K(this);

    public C0219L(Context context, C0238q qVar, View view, int i, int i2, boolean z) {
        this.mContext = context;
        this.mMenu = qVar;
        this.mOverflowOnly = z;
        this.mAdapter = new C0235n(qVar, LayoutInflater.from(context), this.mOverflowOnly, f202Hn);
        this.mPopupStyleAttr = i;
        this.mPopupStyleRes = i2;
        Resources resources = context.getResources();
        this.f204In = Math.max(resources.getDisplayMetrics().widthPixels / 2, resources.getDimensionPixelSize(C0126R.dimen.abc_config_prefDialogWidth));
        this.mAnchorView = view;
        this.mPopup = new MenuPopupWindow(this.mContext, (AttributeSet) null, this.mPopupStyleAttr, this.mPopupStyleRes);
        qVar.mo1582a((C0212E) this, context);
    }

    /* renamed from: a */
    public void mo1349a(C0238q qVar) {
    }

    public void dismiss() {
        if (isShowing()) {
            this.mPopup.dismiss();
        }
    }

    public boolean flagActionItems() {
        return false;
    }

    public ListView getListView() {
        return this.mPopup.getListView();
    }

    public boolean isShowing() {
        return !this.f205Jn && this.mPopup.isShowing();
    }

    public void onCloseMenu(C0238q qVar, boolean z) {
        if (qVar == this.mMenu) {
            dismiss();
            C0211D d = this.mPresenterCallback;
            if (d != null) {
                d.onCloseMenu(qVar, z);
            }
        }
    }

    public void onDismiss() {
        this.f205Jn = true;
        this.mMenu.close(true);
        ViewTreeObserver viewTreeObserver = this.mTreeObserver;
        if (viewTreeObserver != null) {
            if (!viewTreeObserver.isAlive()) {
                this.mTreeObserver = this.f203Bn.getViewTreeObserver();
            }
            this.mTreeObserver.removeGlobalOnLayoutListener(this.f208xn);
            this.mTreeObserver = null;
        }
        this.f203Bn.removeOnAttachStateChangeListener(this.f209yn);
        PopupWindow.OnDismissListener onDismissListener = this.mOnDismissListener;
        if (onDismissListener != null) {
            onDismissListener.onDismiss();
        }
    }

    public boolean onKey(View view, int i, KeyEvent keyEvent) {
        if (keyEvent.getAction() != 1 || i != 82) {
            return false;
        }
        dismiss();
        return true;
    }

    public void onRestoreInstanceState(Parcelable parcelable) {
    }

    public Parcelable onSaveInstanceState() {
        return null;
    }

    public boolean onSubMenuSelected(C0220M m) {
        if (m.hasVisibleItems()) {
            C0210C c = new C0210C(this.mContext, m, this.f203Bn, this.mOverflowOnly, this.mPopupStyleAttr, this.mPopupStyleRes);
            c.setPresenterCallback(this.mPresenterCallback);
            c.setForceShowIcon(C0208A.m190b(m));
            c.setOnDismissListener(this.mOnDismissListener);
            this.mOnDismissListener = null;
            this.mMenu.close(false);
            int horizontalOffset = this.mPopup.getHorizontalOffset();
            int verticalOffset = this.mPopup.getVerticalOffset();
            if ((Gravity.getAbsoluteGravity(this.mDropDownGravity, ViewCompat.getLayoutDirection(this.mAnchorView)) & 7) == 5) {
                horizontalOffset += this.mAnchorView.getWidth();
            }
            if (c.tryShow(horizontalOffset, verticalOffset)) {
                C0211D d = this.mPresenterCallback;
                if (d == null) {
                    return true;
                }
                d.onOpenSubMenu(m);
                return true;
            }
        }
        return false;
    }

    public void setAnchorView(View view) {
        this.mAnchorView = view;
    }

    public void setCallback(C0211D d) {
        this.mPresenterCallback = d;
    }

    public void setForceShowIcon(boolean z) {
        this.mAdapter.setForceShowIcon(z);
    }

    public void setGravity(int i) {
        this.mDropDownGravity = i;
    }

    public void setHorizontalOffset(int i) {
        this.mPopup.setHorizontalOffset(i);
    }

    public void setOnDismissListener(PopupWindow.OnDismissListener onDismissListener) {
        this.mOnDismissListener = onDismissListener;
    }

    public void setShowTitle(boolean z) {
        this.mShowTitle = z;
    }

    public void setVerticalOffset(int i) {
        this.mPopup.setVerticalOffset(i);
    }

    public void show() {
        View view;
        boolean z = true;
        if (!isShowing()) {
            if (this.f205Jn || (view = this.mAnchorView) == null) {
                z = false;
            } else {
                this.f203Bn = view;
                this.mPopup.setOnDismissListener(this);
                this.mPopup.setOnItemClickListener(this);
                this.mPopup.setModal(true);
                View view2 = this.f203Bn;
                boolean z2 = this.mTreeObserver == null;
                this.mTreeObserver = view2.getViewTreeObserver();
                if (z2) {
                    this.mTreeObserver.addOnGlobalLayoutListener(this.f208xn);
                }
                view2.addOnAttachStateChangeListener(this.f209yn);
                this.mPopup.setAnchorView(view2);
                this.mPopup.setDropDownGravity(this.mDropDownGravity);
                if (!this.f206Kn) {
                    this.f207Ln = C0208A.measureIndividualMenuWidth(this.mAdapter, (ViewGroup) null, this.mContext, this.f204In);
                    this.f206Kn = true;
                }
                this.mPopup.setContentWidth(this.f207Ln);
                this.mPopup.setInputMethodMode(2);
                this.mPopup.setEpicenterBounds(getEpicenterBounds());
                this.mPopup.show();
                ListView listView = this.mPopup.getListView();
                listView.setOnKeyListener(this);
                if (this.mShowTitle && this.mMenu.mHeaderTitle != null) {
                    FrameLayout frameLayout = (FrameLayout) LayoutInflater.from(this.mContext).inflate(C0126R.layout.abc_popup_menu_header_item_layout, listView, false);
                    TextView textView = (TextView) frameLayout.findViewById(16908310);
                    if (textView != null) {
                        textView.setText(this.mMenu.mHeaderTitle);
                    }
                    frameLayout.setEnabled(false);
                    listView.addHeaderView(frameLayout, (Object) null, false);
                }
                this.mPopup.setAdapter(this.mAdapter);
                this.mPopup.show();
            }
        }
        if (!z) {
            throw new IllegalStateException("StandardMenuPopup cannot be used without an anchor");
        }
    }

    public void updateMenuView(boolean z) {
        this.f206Kn = false;
        C0235n nVar = this.mAdapter;
        if (nVar != null) {
            nVar.notifyDataSetChanged();
        }
    }
}
