package android.support.p002v7.view.menu;

import android.content.Context;
import android.content.res.Resources;
import android.os.Handler;
import android.os.SystemClock;
import android.support.design.R$dimen;
import android.support.p000v4.view.ViewCompat;
import android.support.p002v7.view.menu.MenuPresenter;
import android.support.p002v7.widget.MenuItemHoverListener;
import android.support.p002v7.widget.MenuPopupWindow;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.HeaderViewListAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import com.android.dialer.R;
import java.util.ArrayList;
import java.util.List;

/* renamed from: android.support.v7.view.menu.CascadingMenuPopup */
final class CascadingMenuPopup extends MenuPopup implements MenuPresenter, View.OnKeyListener, PopupWindow.OnDismissListener {
    private View mAnchorView;
    private final View.OnAttachStateChangeListener mAttachStateChangeListener = new View.OnAttachStateChangeListener() {
        public void onViewAttachedToWindow(View view) {
        }

        public void onViewDetachedFromWindow(View view) {
            if (CascadingMenuPopup.this.mTreeObserver != null) {
                if (!CascadingMenuPopup.this.mTreeObserver.isAlive()) {
                    ViewTreeObserver unused = CascadingMenuPopup.this.mTreeObserver = view.getViewTreeObserver();
                }
                CascadingMenuPopup.this.mTreeObserver.removeGlobalOnLayoutListener(CascadingMenuPopup.this.mGlobalLayoutListener);
            }
            view.removeOnAttachStateChangeListener(this);
        }
    };
    private final Context mContext;
    private int mDropDownGravity = 0;
    private boolean mForceShowIcon;
    /* access modifiers changed from: private */
    public final ViewTreeObserver.OnGlobalLayoutListener mGlobalLayoutListener = new ViewTreeObserver.OnGlobalLayoutListener() {
        public void onGlobalLayout() {
            if (CascadingMenuPopup.this.isShowing() && CascadingMenuPopup.this.mShowingMenus.size() > 0 && !CascadingMenuPopup.this.mShowingMenus.get(0).window.isModal()) {
                View view = CascadingMenuPopup.this.mShownAnchorView;
                if (view == null || !view.isShown()) {
                    CascadingMenuPopup.this.dismiss();
                    return;
                }
                for (CascadingMenuInfo cascadingMenuInfo : CascadingMenuPopup.this.mShowingMenus) {
                    cascadingMenuInfo.window.show();
                }
            }
        }
    };
    private boolean mHasXOffset;
    private boolean mHasYOffset;
    private int mLastPosition;
    private final MenuItemHoverListener mMenuItemHoverListener = new MenuItemHoverListener() {
        public void onItemHoverEnter(final MenuBuilder menuBuilder, final MenuItem menuItem) {
            final CascadingMenuInfo cascadingMenuInfo = null;
            CascadingMenuPopup.this.mSubMenuHoverHandler.removeCallbacksAndMessages((Object) null);
            int size = CascadingMenuPopup.this.mShowingMenus.size();
            int i = 0;
            while (true) {
                if (i >= size) {
                    i = -1;
                    break;
                } else if (menuBuilder == CascadingMenuPopup.this.mShowingMenus.get(i).menu) {
                    break;
                } else {
                    i++;
                }
            }
            if (i != -1) {
                int i2 = i + 1;
                if (i2 < CascadingMenuPopup.this.mShowingMenus.size()) {
                    cascadingMenuInfo = CascadingMenuPopup.this.mShowingMenus.get(i2);
                }
                CascadingMenuPopup.this.mSubMenuHoverHandler.postAtTime(new Runnable() {
                    public void run() {
                        CascadingMenuInfo cascadingMenuInfo = cascadingMenuInfo;
                        if (cascadingMenuInfo != null) {
                            CascadingMenuPopup.this.mShouldCloseImmediately = true;
                            cascadingMenuInfo.menu.close(false);
                            CascadingMenuPopup.this.mShouldCloseImmediately = false;
                        }
                        if (menuItem.isEnabled() && menuItem.hasSubMenu()) {
                            menuBuilder.performItemAction(menuItem, 4);
                        }
                    }
                }, menuBuilder, SystemClock.uptimeMillis() + 200);
            }
        }

        public void onItemHoverExit(MenuBuilder menuBuilder, MenuItem menuItem) {
            CascadingMenuPopup.this.mSubMenuHoverHandler.removeCallbacksAndMessages(menuBuilder);
        }
    };
    private final int mMenuMaxWidth;
    private PopupWindow.OnDismissListener mOnDismissListener;
    private final boolean mOverflowOnly;
    private final List<MenuBuilder> mPendingMenus = new ArrayList();
    private final int mPopupStyleAttr;
    private final int mPopupStyleRes;
    private MenuPresenter.Callback mPresenterCallback;
    private int mRawDropDownGravity = 0;
    boolean mShouldCloseImmediately;
    private boolean mShowTitle;
    final List<CascadingMenuInfo> mShowingMenus = new ArrayList();
    View mShownAnchorView;
    final Handler mSubMenuHoverHandler;
    /* access modifiers changed from: private */
    public ViewTreeObserver mTreeObserver;
    private int mXOffset;
    private int mYOffset;

    /* renamed from: android.support.v7.view.menu.CascadingMenuPopup$CascadingMenuInfo */
    private static class CascadingMenuInfo {
        public final MenuBuilder menu;
        public final int position;
        public final MenuPopupWindow window;

        public CascadingMenuInfo(MenuPopupWindow menuPopupWindow, MenuBuilder menuBuilder, int i) {
            this.window = menuPopupWindow;
            this.menu = menuBuilder;
            this.position = i;
        }

        public ListView getListView() {
            return this.window.getListView();
        }
    }

    public CascadingMenuPopup(Context context, View view, int i, int i2, boolean z) {
        this.mContext = context;
        this.mAnchorView = view;
        this.mPopupStyleAttr = i;
        this.mPopupStyleRes = i2;
        this.mOverflowOnly = z;
        this.mForceShowIcon = false;
        this.mLastPosition = ViewCompat.getLayoutDirection(this.mAnchorView) == 1 ? 0 : 1;
        Resources resources = context.getResources();
        this.mMenuMaxWidth = Math.max(resources.getDisplayMetrics().widthPixels / 2, resources.getDimensionPixelSize(R.dimen.abc_config_prefDialogWidth));
        this.mSubMenuHoverHandler = new Handler();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:42:0x0124, code lost:
        if (((r8.getWidth() + r10[0]) + r4) > r12.right) goto L_0x012e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x012a, code lost:
        if ((r10[0] - r4) < 0) goto L_0x012c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x012e, code lost:
        r8 = 0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void showMenu(android.support.p002v7.view.menu.MenuBuilder r17) {
        /*
            r16 = this;
            r0 = r16
            r1 = r17
            android.content.Context r2 = r0.mContext
            android.view.LayoutInflater r2 = android.view.LayoutInflater.from(r2)
            android.support.v7.view.menu.MenuAdapter r3 = new android.support.v7.view.menu.MenuAdapter
            boolean r4 = r0.mOverflowOnly
            r5 = 2131492875(0x7f0c000b, float:1.8609214E38)
            r3.<init>(r1, r2, r4, r5)
            boolean r4 = r16.isShowing()
            r5 = 1
            if (r4 != 0) goto L_0x0023
            boolean r4 = r0.mForceShowIcon
            if (r4 == 0) goto L_0x0023
            r3.setForceShowIcon(r5)
            goto L_0x0030
        L_0x0023:
            boolean r4 = r16.isShowing()
            if (r4 == 0) goto L_0x0030
            boolean r4 = android.support.p002v7.view.menu.MenuPopup.shouldPreserveIconSpacing(r17)
            r3.setForceShowIcon(r4)
        L_0x0030:
            android.content.Context r4 = r0.mContext
            int r6 = r0.mMenuMaxWidth
            r7 = 0
            int r4 = android.support.p002v7.view.menu.MenuPopup.measureIndividualMenuWidth(r3, r7, r4, r6)
            android.support.v7.widget.MenuPopupWindow r6 = new android.support.v7.widget.MenuPopupWindow
            android.content.Context r8 = r0.mContext
            int r9 = r0.mPopupStyleAttr
            int r10 = r0.mPopupStyleRes
            r6.<init>(r8, r7, r9, r10)
            android.support.v7.widget.MenuItemHoverListener r8 = r0.mMenuItemHoverListener
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
            java.util.List<android.support.v7.view.menu.CascadingMenuPopup$CascadingMenuInfo> r3 = r0.mShowingMenus
            int r3 = r3.size()
            r9 = 0
            if (r3 <= 0) goto L_0x00ea
            java.util.List<android.support.v7.view.menu.CascadingMenuPopup$CascadingMenuInfo> r3 = r0.mShowingMenus
            int r10 = r3.size()
            int r10 = r10 - r5
            java.lang.Object r3 = r3.get(r10)
            android.support.v7.view.menu.CascadingMenuPopup$CascadingMenuInfo r3 = (android.support.p002v7.view.menu.CascadingMenuPopup.CascadingMenuInfo) r3
            android.support.v7.view.menu.MenuBuilder r10 = r3.menu
            int r11 = r10.size()
            r12 = r9
        L_0x0088:
            if (r12 >= r11) goto L_0x009e
            android.view.MenuItem r13 = r10.getItem(r12)
            boolean r14 = r13.hasSubMenu()
            if (r14 == 0) goto L_0x009b
            android.view.SubMenu r14 = r13.getSubMenu()
            if (r1 != r14) goto L_0x009b
            goto L_0x009f
        L_0x009b:
            int r12 = r12 + 1
            goto L_0x0088
        L_0x009e:
            r13 = r7
        L_0x009f:
            if (r13 != 0) goto L_0x00a3
        L_0x00a1:
            r5 = r7
            goto L_0x00ec
        L_0x00a3:
            android.widget.ListView r10 = r3.getListView()
            android.widget.ListAdapter r11 = r10.getAdapter()
            boolean r12 = r11 instanceof android.widget.HeaderViewListAdapter
            if (r12 == 0) goto L_0x00bc
            android.widget.HeaderViewListAdapter r11 = (android.widget.HeaderViewListAdapter) r11
            int r12 = r11.getHeadersCount()
            android.widget.ListAdapter r11 = r11.getWrappedAdapter()
            android.support.v7.view.menu.MenuAdapter r11 = (android.support.p002v7.view.menu.MenuAdapter) r11
            goto L_0x00bf
        L_0x00bc:
            android.support.v7.view.menu.MenuAdapter r11 = (android.support.p002v7.view.menu.MenuAdapter) r11
            r12 = r9
        L_0x00bf:
            int r14 = r11.getCount()
            r15 = r9
        L_0x00c4:
            r8 = -1
            if (r15 >= r14) goto L_0x00d2
            android.support.v7.view.menu.MenuItemImpl r5 = r11.getItem((int) r15)
            if (r13 != r5) goto L_0x00ce
            goto L_0x00d3
        L_0x00ce:
            int r15 = r15 + 1
            r5 = 1
            goto L_0x00c4
        L_0x00d2:
            r15 = r8
        L_0x00d3:
            if (r15 != r8) goto L_0x00d6
            goto L_0x00a1
        L_0x00d6:
            int r15 = r15 + r12
            int r5 = r10.getFirstVisiblePosition()
            int r15 = r15 - r5
            if (r15 < 0) goto L_0x00a1
            int r5 = r10.getChildCount()
            if (r15 < r5) goto L_0x00e5
            goto L_0x00a1
        L_0x00e5:
            android.view.View r5 = r10.getChildAt(r15)
            goto L_0x00ec
        L_0x00ea:
            r3 = r7
            r5 = r3
        L_0x00ec:
            if (r5 == 0) goto L_0x0160
            r6.setTouchModal(r9)
            r6.setEnterTransition(r7)
            java.util.List<android.support.v7.view.menu.CascadingMenuPopup$CascadingMenuInfo> r8 = r0.mShowingMenus
            int r10 = r8.size()
            r11 = 1
            int r10 = r10 - r11
            java.lang.Object r8 = r8.get(r10)
            android.support.v7.view.menu.CascadingMenuPopup$CascadingMenuInfo r8 = (android.support.p002v7.view.menu.CascadingMenuPopup.CascadingMenuInfo) r8
            android.widget.ListView r8 = r8.getListView()
            r10 = 2
            int[] r10 = new int[r10]
            r8.getLocationOnScreen(r10)
            android.graphics.Rect r12 = new android.graphics.Rect
            r12.<init>()
            android.view.View r13 = r0.mShownAnchorView
            r13.getWindowVisibleDisplayFrame(r12)
            int r13 = r0.mLastPosition
            if (r13 != r11) goto L_0x0127
            r10 = r10[r9]
            int r8 = r8.getWidth()
            int r8 = r8 + r10
            int r8 = r8 + r4
            int r10 = r12.right
            if (r8 <= r10) goto L_0x012c
            goto L_0x012e
        L_0x0127:
            r8 = r10[r9]
            int r8 = r8 - r4
            if (r8 >= 0) goto L_0x012e
        L_0x012c:
            r8 = 1
            goto L_0x012f
        L_0x012e:
            r8 = r9
        L_0x012f:
            r10 = 1
            if (r8 != r10) goto L_0x0134
            r10 = 1
            goto L_0x0135
        L_0x0134:
            r10 = r9
        L_0x0135:
            r0.mLastPosition = r8
            int r8 = android.os.Build.VERSION.SDK_INT
            r6.setAnchorView(r5)
            int r8 = r0.mDropDownGravity
            r11 = 5
            r8 = r8 & r11
            if (r8 != r11) goto L_0x014b
            if (r10 == 0) goto L_0x0146
            int r4 = r4 + r9
            goto L_0x0155
        L_0x0146:
            int r4 = r5.getWidth()
            goto L_0x0153
        L_0x014b:
            if (r10 == 0) goto L_0x0153
            int r4 = r5.getWidth()
            int r4 = r4 + r9
            goto L_0x0155
        L_0x0153:
            int r4 = 0 - r4
        L_0x0155:
            r6.setHorizontalOffset(r4)
            r4 = 1
            r6.setOverlapAnchor(r4)
            r6.setVerticalOffset(r9)
            goto L_0x0179
        L_0x0160:
            boolean r4 = r0.mHasXOffset
            if (r4 == 0) goto L_0x0169
            int r4 = r0.mXOffset
            r6.setHorizontalOffset(r4)
        L_0x0169:
            boolean r4 = r0.mHasYOffset
            if (r4 == 0) goto L_0x0172
            int r4 = r0.mYOffset
            r6.setVerticalOffset(r4)
        L_0x0172:
            android.graphics.Rect r4 = r16.getEpicenterBounds()
            r6.setEpicenterBounds(r4)
        L_0x0179:
            android.support.v7.view.menu.CascadingMenuPopup$CascadingMenuInfo r4 = new android.support.v7.view.menu.CascadingMenuPopup$CascadingMenuInfo
            int r5 = r0.mLastPosition
            r4.<init>(r6, r1, r5)
            java.util.List<android.support.v7.view.menu.CascadingMenuPopup$CascadingMenuInfo> r5 = r0.mShowingMenus
            r5.add(r4)
            r6.show()
            android.widget.ListView r4 = r6.getListView()
            r4.setOnKeyListener(r0)
            if (r3 != 0) goto L_0x01b9
            boolean r0 = r0.mShowTitle
            if (r0 == 0) goto L_0x01b9
            java.lang.CharSequence r0 = r1.mHeaderTitle
            if (r0 == 0) goto L_0x01b9
            r0 = 2131492882(0x7f0c0012, float:1.8609228E38)
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
        L_0x01b9:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.p002v7.view.menu.CascadingMenuPopup.showMenu(android.support.v7.view.menu.MenuBuilder):void");
    }

    public void addMenu(MenuBuilder menuBuilder) {
        menuBuilder.addMenuPresenter(this, this.mContext);
        if (isShowing()) {
            showMenu(menuBuilder);
        } else {
            this.mPendingMenus.add(menuBuilder);
        }
    }

    /* access modifiers changed from: protected */
    public boolean closeMenuOnSubMenuOpened() {
        return false;
    }

    public void dismiss() {
        int size = this.mShowingMenus.size();
        if (size > 0) {
            CascadingMenuInfo[] cascadingMenuInfoArr = (CascadingMenuInfo[]) this.mShowingMenus.toArray(new CascadingMenuInfo[size]);
            for (int i = size - 1; i >= 0; i--) {
                CascadingMenuInfo cascadingMenuInfo = cascadingMenuInfoArr[i];
                if (cascadingMenuInfo.window.isShowing()) {
                    cascadingMenuInfo.window.dismiss();
                }
            }
        }
    }

    public boolean flagActionItems() {
        return false;
    }

    public ListView getListView() {
        if (this.mShowingMenus.isEmpty()) {
            return null;
        }
        List<CascadingMenuInfo> list = this.mShowingMenus;
        return list.get(list.size() - 1).getListView();
    }

    public boolean isShowing() {
        return this.mShowingMenus.size() > 0 && this.mShowingMenus.get(0).window.isShowing();
    }

    public void onCloseMenu(MenuBuilder menuBuilder, boolean z) {
        int size = this.mShowingMenus.size();
        int i = 0;
        while (true) {
            if (i >= size) {
                i = -1;
                break;
            } else if (menuBuilder == this.mShowingMenus.get(i).menu) {
                break;
            } else {
                i++;
            }
        }
        if (i >= 0) {
            int i2 = i + 1;
            if (i2 < this.mShowingMenus.size()) {
                this.mShowingMenus.get(i2).menu.close(false);
            }
            CascadingMenuInfo remove = this.mShowingMenus.remove(i);
            remove.menu.removeMenuPresenter(this);
            if (this.mShouldCloseImmediately) {
                remove.window.setExitTransition((Object) null);
                remove.window.setAnimationStyle(0);
            }
            remove.window.dismiss();
            int size2 = this.mShowingMenus.size();
            if (size2 > 0) {
                this.mLastPosition = this.mShowingMenus.get(size2 - 1).position;
            } else {
                this.mLastPosition = ViewCompat.getLayoutDirection(this.mAnchorView) == 1 ? 0 : 1;
            }
            if (size2 == 0) {
                dismiss();
                MenuPresenter.Callback callback = this.mPresenterCallback;
                if (callback != null) {
                    callback.onCloseMenu(menuBuilder, true);
                }
                ViewTreeObserver viewTreeObserver = this.mTreeObserver;
                if (viewTreeObserver != null) {
                    if (viewTreeObserver.isAlive()) {
                        this.mTreeObserver.removeGlobalOnLayoutListener(this.mGlobalLayoutListener);
                    }
                    this.mTreeObserver = null;
                }
                this.mShownAnchorView.removeOnAttachStateChangeListener(this.mAttachStateChangeListener);
                this.mOnDismissListener.onDismiss();
            } else if (z) {
                this.mShowingMenus.get(0).menu.close(false);
            }
        }
    }

    public void onDismiss() {
        CascadingMenuInfo cascadingMenuInfo;
        int size = this.mShowingMenus.size();
        int i = 0;
        while (true) {
            if (i >= size) {
                cascadingMenuInfo = null;
                break;
            }
            cascadingMenuInfo = this.mShowingMenus.get(i);
            if (!cascadingMenuInfo.window.isShowing()) {
                break;
            }
            i++;
        }
        if (cascadingMenuInfo != null) {
            cascadingMenuInfo.menu.close(false);
        }
    }

    public boolean onKey(View view, int i, KeyEvent keyEvent) {
        if (keyEvent.getAction() != 1 || i != 82) {
            return false;
        }
        dismiss();
        return true;
    }

    public boolean onSubMenuSelected(SubMenuBuilder subMenuBuilder) {
        for (CascadingMenuInfo next : this.mShowingMenus) {
            if (subMenuBuilder == next.menu) {
                next.getListView().requestFocus();
                return true;
            }
        }
        if (!subMenuBuilder.hasVisibleItems()) {
            return false;
        }
        subMenuBuilder.addMenuPresenter(this, this.mContext);
        if (isShowing()) {
            showMenu(subMenuBuilder);
        } else {
            this.mPendingMenus.add(subMenuBuilder);
        }
        MenuPresenter.Callback callback = this.mPresenterCallback;
        if (callback != null) {
            callback.onOpenSubMenu(subMenuBuilder);
        }
        return true;
    }

    public void setAnchorView(View view) {
        if (this.mAnchorView != view) {
            this.mAnchorView = view;
            this.mDropDownGravity = R$dimen.getAbsoluteGravity(this.mRawDropDownGravity, ViewCompat.getLayoutDirection(this.mAnchorView));
        }
    }

    public void setCallback(MenuPresenter.Callback callback) {
        this.mPresenterCallback = callback;
    }

    public void setForceShowIcon(boolean z) {
        this.mForceShowIcon = z;
    }

    public void setGravity(int i) {
        if (this.mRawDropDownGravity != i) {
            this.mRawDropDownGravity = i;
            this.mDropDownGravity = R$dimen.getAbsoluteGravity(i, ViewCompat.getLayoutDirection(this.mAnchorView));
        }
    }

    public void setHorizontalOffset(int i) {
        this.mHasXOffset = true;
        this.mXOffset = i;
    }

    public void setOnDismissListener(PopupWindow.OnDismissListener onDismissListener) {
        this.mOnDismissListener = onDismissListener;
    }

    public void setShowTitle(boolean z) {
        this.mShowTitle = z;
    }

    public void setVerticalOffset(int i) {
        this.mHasYOffset = true;
        this.mYOffset = i;
    }

    public void show() {
        if (!isShowing()) {
            for (MenuBuilder showMenu : this.mPendingMenus) {
                showMenu(showMenu);
            }
            this.mPendingMenus.clear();
            this.mShownAnchorView = this.mAnchorView;
            if (this.mShownAnchorView != null) {
                boolean z = this.mTreeObserver == null;
                this.mTreeObserver = this.mShownAnchorView.getViewTreeObserver();
                if (z) {
                    this.mTreeObserver.addOnGlobalLayoutListener(this.mGlobalLayoutListener);
                }
                this.mShownAnchorView.addOnAttachStateChangeListener(this.mAttachStateChangeListener);
            }
        }
    }

    public void updateMenuView(boolean z) {
        for (CascadingMenuInfo listView : this.mShowingMenus) {
            ListAdapter adapter = listView.getListView().getAdapter();
            if (adapter instanceof HeaderViewListAdapter) {
                adapter = ((HeaderViewListAdapter) adapter).getWrappedAdapter();
            }
            ((MenuAdapter) adapter).notifyDataSetChanged();
        }
    }
}
