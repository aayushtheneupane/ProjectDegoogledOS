package androidx.appcompat.view.menu;

import android.content.Context;
import android.content.res.Resources;
import android.os.Handler;
import android.os.Parcelable;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.HeaderViewListAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import androidx.appcompat.C0126R;
import androidx.appcompat.widget.MenuItemHoverListener;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import java.util.ArrayList;
import java.util.List;

/* renamed from: androidx.appcompat.view.menu.k */
final class C0232k extends C0208A implements C0212E, View.OnKeyListener, PopupWindow.OnDismissListener {

    /* renamed from: Hn */
    private static final int f240Hn = C0126R.layout.abc_cascading_menu_item_layout;

    /* renamed from: An */
    private int f241An = 0;

    /* renamed from: Bn */
    View f242Bn;

    /* renamed from: Cn */
    private int f243Cn;

    /* renamed from: Dn */
    private boolean f244Dn;

    /* renamed from: En */
    private boolean f245En;

    /* renamed from: Fn */
    private int f246Fn;

    /* renamed from: Gn */
    boolean f247Gn;
    private View mAnchorView;
    private final Context mContext;
    private int mDropDownGravity = 0;
    private boolean mForceShowIcon;
    private PopupWindow.OnDismissListener mOnDismissListener;
    private final boolean mOverflowOnly;
    private final int mPopupStyleAttr;
    private final int mPopupStyleRes;
    private C0211D mPresenterCallback;
    private boolean mShowTitle;
    ViewTreeObserver mTreeObserver;
    private int mXOffset;

    /* renamed from: tn */
    private final int f248tn;

    /* renamed from: un */
    final Handler f249un;

    /* renamed from: vn */
    private final List f250vn = new ArrayList();

    /* renamed from: wn */
    final List f251wn = new ArrayList();

    /* renamed from: xn */
    final ViewTreeObserver.OnGlobalLayoutListener f252xn = new C0227f(this);

    /* renamed from: yn */
    private final View.OnAttachStateChangeListener f253yn = new C0228g(this);

    /* renamed from: zn */
    private final MenuItemHoverListener f254zn = new C0230i(this);

    public C0232k(Context context, View view, int i, int i2, boolean z) {
        this.mContext = context;
        this.mAnchorView = view;
        this.mPopupStyleAttr = i;
        this.mPopupStyleRes = i2;
        this.mOverflowOnly = z;
        this.mForceShowIcon = false;
        this.f243Cn = ViewCompat.getLayoutDirection(this.mAnchorView) == 1 ? 0 : 1;
        Resources resources = context.getResources();
        this.f248tn = Math.max(resources.getDisplayMetrics().widthPixels / 2, resources.getDimensionPixelSize(C0126R.dimen.abc_config_prefDialogWidth));
        this.f249un = new Handler();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:42:0x0123, code lost:
        if (((r8.getWidth() + r10[0]) + r4) > r12.right) goto L_0x012d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x0129, code lost:
        if ((r10[0] - r4) < 0) goto L_0x012b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x012d, code lost:
        r8 = 0;
     */
    /* renamed from: c */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void m216c(androidx.appcompat.view.menu.C0238q r17) {
        /*
            r16 = this;
            r0 = r16
            r1 = r17
            android.content.Context r2 = r0.mContext
            android.view.LayoutInflater r2 = android.view.LayoutInflater.from(r2)
            androidx.appcompat.view.menu.n r3 = new androidx.appcompat.view.menu.n
            boolean r4 = r0.mOverflowOnly
            int r5 = f240Hn
            r3.<init>(r1, r2, r4, r5)
            boolean r4 = r16.isShowing()
            r5 = 1
            if (r4 != 0) goto L_0x0022
            boolean r4 = r0.mForceShowIcon
            if (r4 == 0) goto L_0x0022
            r3.setForceShowIcon(r5)
            goto L_0x002f
        L_0x0022:
            boolean r4 = r16.isShowing()
            if (r4 == 0) goto L_0x002f
            boolean r4 = androidx.appcompat.view.menu.C0208A.m190b(r17)
            r3.setForceShowIcon(r4)
        L_0x002f:
            android.content.Context r4 = r0.mContext
            int r6 = r0.f248tn
            r7 = 0
            int r4 = androidx.appcompat.view.menu.C0208A.measureIndividualMenuWidth(r3, r7, r4, r6)
            androidx.appcompat.widget.MenuPopupWindow r6 = new androidx.appcompat.widget.MenuPopupWindow
            android.content.Context r8 = r0.mContext
            int r9 = r0.mPopupStyleAttr
            int r10 = r0.mPopupStyleRes
            r6.<init>(r8, r7, r9, r10)
            androidx.appcompat.widget.MenuItemHoverListener r8 = r0.f254zn
            r6.setHoverListener(r8)
            r6.setOnItemClickListener(r0)
            r6.setOnDismissListener(r0)
            android.view.View r8 = r0.mAnchorView
            r6.setAnchorView(r8)
            int r8 = r0.mDropDownGravity
            r6.setDropDownGravity(r8)
            r6.setModal(r5)
            r8 = 2
            r6.setInputMethodMode(r8)
            r6.setAdapter(r3)
            r6.setContentWidth(r4)
            int r3 = r0.mDropDownGravity
            r6.setDropDownGravity(r3)
            java.util.List r3 = r0.f251wn
            int r3 = r3.size()
            r9 = 0
            if (r3 <= 0) goto L_0x00e9
            java.util.List r3 = r0.f251wn
            int r10 = r3.size()
            int r10 = r10 - r5
            java.lang.Object r3 = r3.get(r10)
            androidx.appcompat.view.menu.j r3 = (androidx.appcompat.view.menu.C0231j) r3
            androidx.appcompat.view.menu.q r10 = r3.menu
            int r11 = r10.size()
            r12 = r9
        L_0x0087:
            if (r12 >= r11) goto L_0x009d
            android.view.MenuItem r13 = r10.getItem(r12)
            boolean r14 = r13.hasSubMenu()
            if (r14 == 0) goto L_0x009a
            android.view.SubMenu r14 = r13.getSubMenu()
            if (r1 != r14) goto L_0x009a
            goto L_0x009e
        L_0x009a:
            int r12 = r12 + 1
            goto L_0x0087
        L_0x009d:
            r13 = r7
        L_0x009e:
            if (r13 != 0) goto L_0x00a2
        L_0x00a0:
            r5 = r7
            goto L_0x00eb
        L_0x00a2:
            android.widget.ListView r10 = r3.getListView()
            android.widget.ListAdapter r11 = r10.getAdapter()
            boolean r12 = r11 instanceof android.widget.HeaderViewListAdapter
            if (r12 == 0) goto L_0x00bb
            android.widget.HeaderViewListAdapter r11 = (android.widget.HeaderViewListAdapter) r11
            int r12 = r11.getHeadersCount()
            android.widget.ListAdapter r11 = r11.getWrappedAdapter()
            androidx.appcompat.view.menu.n r11 = (androidx.appcompat.view.menu.C0235n) r11
            goto L_0x00be
        L_0x00bb:
            androidx.appcompat.view.menu.n r11 = (androidx.appcompat.view.menu.C0235n) r11
            r12 = r9
        L_0x00be:
            int r14 = r11.getCount()
            r15 = r9
        L_0x00c3:
            r8 = -1
            if (r15 >= r14) goto L_0x00d1
            androidx.appcompat.view.menu.t r5 = r11.getItem((int) r15)
            if (r13 != r5) goto L_0x00cd
            goto L_0x00d2
        L_0x00cd:
            int r15 = r15 + 1
            r5 = 1
            goto L_0x00c3
        L_0x00d1:
            r15 = r8
        L_0x00d2:
            if (r15 != r8) goto L_0x00d5
            goto L_0x00a0
        L_0x00d5:
            int r15 = r15 + r12
            int r5 = r10.getFirstVisiblePosition()
            int r15 = r15 - r5
            if (r15 < 0) goto L_0x00a0
            int r5 = r10.getChildCount()
            if (r15 < r5) goto L_0x00e4
            goto L_0x00a0
        L_0x00e4:
            android.view.View r5 = r10.getChildAt(r15)
            goto L_0x00eb
        L_0x00e9:
            r3 = r7
            r5 = r3
        L_0x00eb:
            if (r5 == 0) goto L_0x015f
            r6.setTouchModal(r9)
            r6.setEnterTransition(r7)
            java.util.List r8 = r0.f251wn
            int r10 = r8.size()
            r11 = 1
            int r10 = r10 - r11
            java.lang.Object r8 = r8.get(r10)
            androidx.appcompat.view.menu.j r8 = (androidx.appcompat.view.menu.C0231j) r8
            android.widget.ListView r8 = r8.getListView()
            r10 = 2
            int[] r10 = new int[r10]
            r8.getLocationOnScreen(r10)
            android.graphics.Rect r12 = new android.graphics.Rect
            r12.<init>()
            android.view.View r13 = r0.f242Bn
            r13.getWindowVisibleDisplayFrame(r12)
            int r13 = r0.f243Cn
            if (r13 != r11) goto L_0x0126
            r10 = r10[r9]
            int r8 = r8.getWidth()
            int r8 = r8 + r10
            int r8 = r8 + r4
            int r10 = r12.right
            if (r8 <= r10) goto L_0x012b
            goto L_0x012d
        L_0x0126:
            r8 = r10[r9]
            int r8 = r8 - r4
            if (r8 >= 0) goto L_0x012d
        L_0x012b:
            r8 = 1
            goto L_0x012e
        L_0x012d:
            r8 = r9
        L_0x012e:
            r10 = 1
            if (r8 != r10) goto L_0x0133
            r10 = 1
            goto L_0x0134
        L_0x0133:
            r10 = r9
        L_0x0134:
            r0.f243Cn = r8
            int r8 = android.os.Build.VERSION.SDK_INT
            r6.setAnchorView(r5)
            int r8 = r0.mDropDownGravity
            r11 = 5
            r8 = r8 & r11
            if (r8 != r11) goto L_0x014a
            if (r10 == 0) goto L_0x0145
            int r4 = r4 + r9
            goto L_0x0154
        L_0x0145:
            int r4 = r5.getWidth()
            goto L_0x0152
        L_0x014a:
            if (r10 == 0) goto L_0x0152
            int r4 = r5.getWidth()
            int r4 = r4 + r9
            goto L_0x0154
        L_0x0152:
            int r4 = 0 - r4
        L_0x0154:
            r6.setHorizontalOffset(r4)
            r4 = 1
            r6.setOverlapAnchor(r4)
            r6.setVerticalOffset(r9)
            goto L_0x0178
        L_0x015f:
            boolean r4 = r0.f244Dn
            if (r4 == 0) goto L_0x0168
            int r4 = r0.mXOffset
            r6.setHorizontalOffset(r4)
        L_0x0168:
            boolean r4 = r0.f245En
            if (r4 == 0) goto L_0x0171
            int r4 = r0.f246Fn
            r6.setVerticalOffset(r4)
        L_0x0171:
            android.graphics.Rect r4 = r16.getEpicenterBounds()
            r6.setEpicenterBounds(r4)
        L_0x0178:
            androidx.appcompat.view.menu.j r4 = new androidx.appcompat.view.menu.j
            int r5 = r0.f243Cn
            r4.<init>(r6, r1, r5)
            java.util.List r5 = r0.f251wn
            r5.add(r4)
            r6.show()
            android.widget.ListView r4 = r6.getListView()
            r4.setOnKeyListener(r0)
            if (r3 != 0) goto L_0x01b7
            boolean r0 = r0.mShowTitle
            if (r0 == 0) goto L_0x01b7
            java.lang.CharSequence r0 = r1.mHeaderTitle
            if (r0 == 0) goto L_0x01b7
            int r0 = androidx.appcompat.C0126R.layout.abc_popup_menu_header_item_layout
            android.view.View r0 = r2.inflate(r0, r4, r9)
            android.widget.FrameLayout r0 = (android.widget.FrameLayout) r0
            r2 = 16908310(0x1020016, float:2.387729E-38)
            android.view.View r2 = r0.findViewById(r2)
            android.widget.TextView r2 = (android.widget.TextView) r2
            r0.setEnabled(r9)
            java.lang.CharSequence r1 = r1.mHeaderTitle
            r2.setText(r1)
            r4.addHeaderView(r0, r7, r9)
            r6.show()
        L_0x01b7:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.view.menu.C0232k.m216c(androidx.appcompat.view.menu.q):void");
    }

    /* renamed from: a */
    public void mo1349a(C0238q qVar) {
        qVar.mo1582a((C0212E) this, this.mContext);
        if (isShowing()) {
            m216c(qVar);
        } else {
            this.f250vn.add(qVar);
        }
    }

    public void dismiss() {
        int size = this.f251wn.size();
        if (size > 0) {
            C0231j[] jVarArr = (C0231j[]) this.f251wn.toArray(new C0231j[size]);
            for (int i = size - 1; i >= 0; i--) {
                C0231j jVar = jVarArr[i];
                if (jVar.window.isShowing()) {
                    jVar.window.dismiss();
                }
            }
        }
    }

    public boolean flagActionItems() {
        return false;
    }

    public ListView getListView() {
        if (this.f251wn.isEmpty()) {
            return null;
        }
        List list = this.f251wn;
        return ((C0231j) list.get(list.size() - 1)).getListView();
    }

    public boolean isShowing() {
        return this.f251wn.size() > 0 && ((C0231j) this.f251wn.get(0)).window.isShowing();
    }

    /* access modifiers changed from: protected */
    /* renamed from: nc */
    public boolean mo1355nc() {
        return false;
    }

    public void onCloseMenu(C0238q qVar, boolean z) {
        int size = this.f251wn.size();
        int i = 0;
        while (true) {
            if (i >= size) {
                i = -1;
                break;
            } else if (qVar == ((C0231j) this.f251wn.get(i)).menu) {
                break;
            } else {
                i++;
            }
        }
        if (i >= 0) {
            int i2 = i + 1;
            if (i2 < this.f251wn.size()) {
                ((C0231j) this.f251wn.get(i2)).menu.close(false);
            }
            C0231j jVar = (C0231j) this.f251wn.remove(i);
            jVar.menu.mo1593b((C0212E) this);
            if (this.f247Gn) {
                jVar.window.setExitTransition((Object) null);
                jVar.window.setAnimationStyle(0);
            }
            jVar.window.dismiss();
            int size2 = this.f251wn.size();
            if (size2 > 0) {
                this.f243Cn = ((C0231j) this.f251wn.get(size2 - 1)).position;
            } else {
                this.f243Cn = ViewCompat.getLayoutDirection(this.mAnchorView) == 1 ? 0 : 1;
            }
            if (size2 == 0) {
                dismiss();
                C0211D d = this.mPresenterCallback;
                if (d != null) {
                    d.onCloseMenu(qVar, true);
                }
                ViewTreeObserver viewTreeObserver = this.mTreeObserver;
                if (viewTreeObserver != null) {
                    if (viewTreeObserver.isAlive()) {
                        this.mTreeObserver.removeGlobalOnLayoutListener(this.f252xn);
                    }
                    this.mTreeObserver = null;
                }
                this.f242Bn.removeOnAttachStateChangeListener(this.f253yn);
                this.mOnDismissListener.onDismiss();
            } else if (z) {
                ((C0231j) this.f251wn.get(0)).menu.close(false);
            }
        }
    }

    public void onDismiss() {
        C0231j jVar;
        int size = this.f251wn.size();
        int i = 0;
        while (true) {
            if (i >= size) {
                jVar = null;
                break;
            }
            jVar = (C0231j) this.f251wn.get(i);
            if (!jVar.window.isShowing()) {
                break;
            }
            i++;
        }
        if (jVar != null) {
            jVar.menu.close(false);
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
        for (C0231j jVar : this.f251wn) {
            if (m == jVar.menu) {
                jVar.getListView().requestFocus();
                return true;
            }
        }
        if (!m.hasVisibleItems()) {
            return false;
        }
        m.mo1582a((C0212E) this, this.mContext);
        if (isShowing()) {
            m216c(m);
        } else {
            this.f250vn.add(m);
        }
        C0211D d = this.mPresenterCallback;
        if (d != null) {
            d.onOpenSubMenu(m);
        }
        return true;
    }

    public void setAnchorView(View view) {
        if (this.mAnchorView != view) {
            this.mAnchorView = view;
            this.mDropDownGravity = GravityCompat.getAbsoluteGravity(this.f241An, ViewCompat.getLayoutDirection(this.mAnchorView));
        }
    }

    public void setCallback(C0211D d) {
        this.mPresenterCallback = d;
    }

    public void setForceShowIcon(boolean z) {
        this.mForceShowIcon = z;
    }

    public void setGravity(int i) {
        if (this.f241An != i) {
            this.f241An = i;
            this.mDropDownGravity = GravityCompat.getAbsoluteGravity(i, ViewCompat.getLayoutDirection(this.mAnchorView));
        }
    }

    public void setHorizontalOffset(int i) {
        this.f244Dn = true;
        this.mXOffset = i;
    }

    public void setOnDismissListener(PopupWindow.OnDismissListener onDismissListener) {
        this.mOnDismissListener = onDismissListener;
    }

    public void setShowTitle(boolean z) {
        this.mShowTitle = z;
    }

    public void setVerticalOffset(int i) {
        this.f245En = true;
        this.f246Fn = i;
    }

    public void show() {
        if (!isShowing()) {
            for (C0238q c : this.f250vn) {
                m216c(c);
            }
            this.f250vn.clear();
            this.f242Bn = this.mAnchorView;
            if (this.f242Bn != null) {
                boolean z = this.mTreeObserver == null;
                this.mTreeObserver = this.f242Bn.getViewTreeObserver();
                if (z) {
                    this.mTreeObserver.addOnGlobalLayoutListener(this.f252xn);
                }
                this.f242Bn.addOnAttachStateChangeListener(this.f253yn);
            }
        }
    }

    public void updateMenuView(boolean z) {
        for (C0231j listView : this.f251wn) {
            ListAdapter adapter = listView.getListView().getAdapter();
            if (adapter instanceof HeaderViewListAdapter) {
                adapter = ((HeaderViewListAdapter) adapter).getWrappedAdapter();
            }
            ((C0235n) adapter).notifyDataSetChanged();
        }
    }
}
