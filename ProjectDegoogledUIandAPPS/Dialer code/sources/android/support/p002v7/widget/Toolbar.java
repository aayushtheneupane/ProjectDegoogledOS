package android.support.p002v7.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.design.R$dimen;
import android.support.p000v4.view.AbsSavedState;
import android.support.p000v4.view.ViewCompat;
import android.support.p002v7.app.ActionBar;
import android.support.p002v7.appcompat.R$styleable;
import android.support.p002v7.content.res.AppCompatResources;
import android.support.p002v7.view.CollapsibleActionView;
import android.support.p002v7.view.menu.MenuBuilder;
import android.support.p002v7.view.menu.MenuItemImpl;
import android.support.p002v7.view.menu.MenuPresenter;
import android.support.p002v7.view.menu.SubMenuBuilder;
import android.support.p002v7.widget.ActionMenuView;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.ContextThemeWrapper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import com.android.dialer.R;
import java.util.ArrayList;
import java.util.List;

/* renamed from: android.support.v7.widget.Toolbar */
public class Toolbar extends ViewGroup {
    private MenuPresenter.Callback mActionMenuPresenterCallback;
    int mButtonGravity;
    ImageButton mCollapseButtonView;
    private CharSequence mCollapseDescription;
    private Drawable mCollapseIcon;
    private boolean mCollapsible;
    private int mContentInsetEndWithActions;
    private int mContentInsetStartWithNavigation;
    private RtlSpacingHelper mContentInsets;
    private boolean mEatingHover;
    private boolean mEatingTouch;
    View mExpandedActionView;
    private ExpandedActionViewMenuPresenter mExpandedMenuPresenter;
    private int mGravity;
    private final ArrayList<View> mHiddenViews;
    private ImageView mLogoView;
    private int mMaxButtonHeight;
    private MenuBuilder.Callback mMenuBuilderCallback;
    private ActionMenuView mMenuView;
    private final ActionMenuView.OnMenuItemClickListener mMenuViewItemClickListener;
    private ImageButton mNavButtonView;
    OnMenuItemClickListener mOnMenuItemClickListener;
    private ActionMenuPresenter mOuterActionMenuPresenter;
    private Context mPopupContext;
    private int mPopupTheme;
    private final Runnable mShowOverflowMenuRunnable;
    private CharSequence mSubtitleText;
    private int mSubtitleTextAppearance;
    private int mSubtitleTextColor;
    private TextView mSubtitleTextView;
    private final int[] mTempMargins;
    private final ArrayList<View> mTempViews;
    private int mTitleMarginBottom;
    private int mTitleMarginEnd;
    private int mTitleMarginStart;
    private int mTitleMarginTop;
    private CharSequence mTitleText;
    private int mTitleTextAppearance;
    private int mTitleTextColor;
    private TextView mTitleTextView;
    private ToolbarWidgetWrapper mWrapper;

    /* renamed from: android.support.v7.widget.Toolbar$ExpandedActionViewMenuPresenter */
    private class ExpandedActionViewMenuPresenter implements MenuPresenter {
        MenuItemImpl mCurrentExpandedItem;
        MenuBuilder mMenu;

        ExpandedActionViewMenuPresenter() {
        }

        public boolean collapseItemActionView(MenuBuilder menuBuilder, MenuItemImpl menuItemImpl) {
            View view = Toolbar.this.mExpandedActionView;
            if (view instanceof CollapsibleActionView) {
                ((CollapsibleActionView) view).onActionViewCollapsed();
            }
            Toolbar toolbar = Toolbar.this;
            toolbar.removeView(toolbar.mExpandedActionView);
            Toolbar toolbar2 = Toolbar.this;
            toolbar2.removeView(toolbar2.mCollapseButtonView);
            Toolbar toolbar3 = Toolbar.this;
            toolbar3.mExpandedActionView = null;
            toolbar3.addChildrenForExpandedActionView();
            this.mCurrentExpandedItem = null;
            Toolbar.this.requestLayout();
            menuItemImpl.setActionViewExpanded(false);
            return true;
        }

        public boolean expandItemActionView(MenuBuilder menuBuilder, MenuItemImpl menuItemImpl) {
            Toolbar.this.ensureCollapseButtonView();
            ViewParent parent = Toolbar.this.mCollapseButtonView.getParent();
            Toolbar toolbar = Toolbar.this;
            if (parent != toolbar) {
                if (parent instanceof ViewGroup) {
                    ((ViewGroup) parent).removeView(toolbar.mCollapseButtonView);
                }
                Toolbar toolbar2 = Toolbar.this;
                toolbar2.addView(toolbar2.mCollapseButtonView);
            }
            Toolbar.this.mExpandedActionView = menuItemImpl.getActionView();
            this.mCurrentExpandedItem = menuItemImpl;
            ViewParent parent2 = Toolbar.this.mExpandedActionView.getParent();
            Toolbar toolbar3 = Toolbar.this;
            if (parent2 != toolbar3) {
                if (parent2 instanceof ViewGroup) {
                    ((ViewGroup) parent2).removeView(toolbar3.mExpandedActionView);
                }
                LayoutParams generateDefaultLayoutParams = Toolbar.this.generateDefaultLayoutParams();
                Toolbar toolbar4 = Toolbar.this;
                generateDefaultLayoutParams.gravity = 8388611 | (toolbar4.mButtonGravity & 112);
                generateDefaultLayoutParams.mViewType = 2;
                toolbar4.mExpandedActionView.setLayoutParams(generateDefaultLayoutParams);
                Toolbar toolbar5 = Toolbar.this;
                toolbar5.addView(toolbar5.mExpandedActionView);
            }
            Toolbar.this.removeChildrenForExpandedActionView();
            Toolbar.this.requestLayout();
            menuItemImpl.setActionViewExpanded(true);
            View view = Toolbar.this.mExpandedActionView;
            if (view instanceof CollapsibleActionView) {
                ((CollapsibleActionView) view).onActionViewExpanded();
            }
            return true;
        }

        public boolean flagActionItems() {
            return false;
        }

        public void initForMenu(Context context, MenuBuilder menuBuilder) {
            MenuItemImpl menuItemImpl;
            MenuBuilder menuBuilder2 = this.mMenu;
            if (!(menuBuilder2 == null || (menuItemImpl = this.mCurrentExpandedItem) == null)) {
                menuBuilder2.collapseItemActionView(menuItemImpl);
            }
            this.mMenu = menuBuilder;
        }

        public void onCloseMenu(MenuBuilder menuBuilder, boolean z) {
        }

        public boolean onSubMenuSelected(SubMenuBuilder subMenuBuilder) {
            return false;
        }

        public void updateMenuView(boolean z) {
            boolean z2;
            if (this.mCurrentExpandedItem != null) {
                MenuBuilder menuBuilder = this.mMenu;
                if (menuBuilder != null) {
                    int size = menuBuilder.size();
                    int i = 0;
                    while (true) {
                        if (i >= size) {
                            break;
                        } else if (this.mMenu.getItem(i) == this.mCurrentExpandedItem) {
                            z2 = true;
                            break;
                        } else {
                            i++;
                        }
                    }
                }
                z2 = false;
                if (!z2) {
                    MenuItemImpl menuItemImpl = this.mCurrentExpandedItem;
                    View view = Toolbar.this.mExpandedActionView;
                    if (view instanceof CollapsibleActionView) {
                        ((CollapsibleActionView) view).onActionViewCollapsed();
                    }
                    Toolbar toolbar = Toolbar.this;
                    toolbar.removeView(toolbar.mExpandedActionView);
                    Toolbar toolbar2 = Toolbar.this;
                    toolbar2.removeView(toolbar2.mCollapseButtonView);
                    Toolbar toolbar3 = Toolbar.this;
                    toolbar3.mExpandedActionView = null;
                    toolbar3.addChildrenForExpandedActionView();
                    this.mCurrentExpandedItem = null;
                    Toolbar.this.requestLayout();
                    menuItemImpl.setActionViewExpanded(false);
                }
            }
        }
    }

    /* renamed from: android.support.v7.widget.Toolbar$OnMenuItemClickListener */
    public interface OnMenuItemClickListener {
        boolean onMenuItemClick(MenuItem menuItem);
    }

    public Toolbar(Context context) {
        this(context, (AttributeSet) null, R.attr.toolbarStyle);
    }

    private void addCustomViewsWithGravity(List<View> list, int i) {
        boolean z = ViewCompat.getLayoutDirection(this) == 1;
        int childCount = getChildCount();
        int absoluteGravity = R$dimen.getAbsoluteGravity(i, ViewCompat.getLayoutDirection(this));
        list.clear();
        if (z) {
            for (int i2 = childCount - 1; i2 >= 0; i2--) {
                View childAt = getChildAt(i2);
                LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
                if (layoutParams.mViewType == 0 && shouldLayout(childAt) && getChildHorizontalGravity(layoutParams.gravity) == absoluteGravity) {
                    list.add(childAt);
                }
            }
            return;
        }
        for (int i3 = 0; i3 < childCount; i3++) {
            View childAt2 = getChildAt(i3);
            LayoutParams layoutParams2 = (LayoutParams) childAt2.getLayoutParams();
            if (layoutParams2.mViewType == 0 && shouldLayout(childAt2) && getChildHorizontalGravity(layoutParams2.gravity) == absoluteGravity) {
                list.add(childAt2);
            }
        }
    }

    private void addSystemView(View view, boolean z) {
        LayoutParams layoutParams;
        ViewGroup.LayoutParams layoutParams2 = view.getLayoutParams();
        if (layoutParams2 == null) {
            layoutParams = generateDefaultLayoutParams();
        } else if (!checkLayoutParams(layoutParams2)) {
            layoutParams = generateLayoutParams(layoutParams2);
        } else {
            layoutParams = (LayoutParams) layoutParams2;
        }
        layoutParams.mViewType = 1;
        if (!z || this.mExpandedActionView == null) {
            addView(view, layoutParams);
            return;
        }
        view.setLayoutParams(layoutParams);
        this.mHiddenViews.add(view);
    }

    private void ensureContentInsets() {
        if (this.mContentInsets == null) {
            this.mContentInsets = new RtlSpacingHelper();
        }
    }

    private void ensureMenuView() {
        if (this.mMenuView == null) {
            this.mMenuView = new ActionMenuView(getContext(), (AttributeSet) null);
            this.mMenuView.setPopupTheme(this.mPopupTheme);
            this.mMenuView.setOnMenuItemClickListener(this.mMenuViewItemClickListener);
            this.mMenuView.setMenuCallbacks(this.mActionMenuPresenterCallback, this.mMenuBuilderCallback);
            LayoutParams generateDefaultLayoutParams = generateDefaultLayoutParams();
            generateDefaultLayoutParams.gravity = 8388613 | (this.mButtonGravity & 112);
            this.mMenuView.setLayoutParams(generateDefaultLayoutParams);
            addSystemView(this.mMenuView, false);
        }
    }

    private void ensureNavButtonView() {
        if (this.mNavButtonView == null) {
            this.mNavButtonView = new AppCompatImageButton(getContext(), (AttributeSet) null, R.attr.toolbarNavigationButtonStyle);
            LayoutParams generateDefaultLayoutParams = generateDefaultLayoutParams();
            generateDefaultLayoutParams.gravity = 8388611 | (this.mButtonGravity & 112);
            this.mNavButtonView.setLayoutParams(generateDefaultLayoutParams);
        }
    }

    private int getChildHorizontalGravity(int i) {
        int layoutDirection = ViewCompat.getLayoutDirection(this);
        int absoluteGravity = R$dimen.getAbsoluteGravity(i, layoutDirection) & 7;
        if (absoluteGravity == 1 || absoluteGravity == 3 || absoluteGravity == 5) {
            return absoluteGravity;
        }
        return layoutDirection == 1 ? 5 : 3;
    }

    private int getChildTop(View view, int i) {
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        int measuredHeight = view.getMeasuredHeight();
        int i2 = i > 0 ? (measuredHeight - i) / 2 : 0;
        int i3 = layoutParams.gravity & 112;
        if (!(i3 == 16 || i3 == 48 || i3 == 80)) {
            i3 = this.mGravity & 112;
        }
        if (i3 == 48) {
            return getPaddingTop() - i2;
        }
        if (i3 == 80) {
            return (((getHeight() - getPaddingBottom()) - measuredHeight) - layoutParams.bottomMargin) - i2;
        }
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();
        int height = getHeight();
        int i4 = (((height - paddingTop) - paddingBottom) - measuredHeight) / 2;
        int i5 = layoutParams.topMargin;
        if (i4 < i5) {
            i4 = i5;
        } else {
            int i6 = (((height - paddingBottom) - measuredHeight) - i4) - paddingTop;
            int i7 = layoutParams.bottomMargin;
            if (i6 < i7) {
                i4 = Math.max(0, i4 - (i7 - i6));
            }
        }
        return paddingTop + i4;
    }

    private int getHorizontalMargins(View view) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        int i = Build.VERSION.SDK_INT;
        int marginStart = marginLayoutParams.getMarginStart();
        int i2 = Build.VERSION.SDK_INT;
        return marginStart + marginLayoutParams.getMarginEnd();
    }

    private int getVerticalMargins(View view) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        return marginLayoutParams.topMargin + marginLayoutParams.bottomMargin;
    }

    private boolean isChildOrHidden(View view) {
        return view.getParent() == this || this.mHiddenViews.contains(view);
    }

    private int layoutChildLeft(View view, int i, int[] iArr, int i2) {
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        int i3 = layoutParams.leftMargin - iArr[0];
        int max = Math.max(0, i3) + i;
        iArr[0] = Math.max(0, -i3);
        int childTop = getChildTop(view, i2);
        int measuredWidth = view.getMeasuredWidth();
        view.layout(max, childTop, max + measuredWidth, view.getMeasuredHeight() + childTop);
        return measuredWidth + layoutParams.rightMargin + max;
    }

    private int layoutChildRight(View view, int i, int[] iArr, int i2) {
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        int i3 = layoutParams.rightMargin - iArr[1];
        int max = i - Math.max(0, i3);
        iArr[1] = Math.max(0, -i3);
        int childTop = getChildTop(view, i2);
        int measuredWidth = view.getMeasuredWidth();
        view.layout(max - measuredWidth, childTop, max, view.getMeasuredHeight() + childTop);
        return max - (measuredWidth + layoutParams.leftMargin);
    }

    private int measureChildCollapseMargins(View view, int i, int i2, int i3, int i4, int[] iArr) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        int i5 = marginLayoutParams.leftMargin - iArr[0];
        int i6 = marginLayoutParams.rightMargin - iArr[1];
        int max = Math.max(0, i6) + Math.max(0, i5);
        iArr[0] = Math.max(0, -i5);
        iArr[1] = Math.max(0, -i6);
        view.measure(ViewGroup.getChildMeasureSpec(i, getPaddingRight() + getPaddingLeft() + max + i2, marginLayoutParams.width), ViewGroup.getChildMeasureSpec(i3, getPaddingBottom() + getPaddingTop() + marginLayoutParams.topMargin + marginLayoutParams.bottomMargin + i4, marginLayoutParams.height));
        return view.getMeasuredWidth() + max;
    }

    private void measureChildConstrained(View view, int i, int i2, int i3, int i4, int i5) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        int childMeasureSpec = ViewGroup.getChildMeasureSpec(i, getPaddingRight() + getPaddingLeft() + marginLayoutParams.leftMargin + marginLayoutParams.rightMargin + i2, marginLayoutParams.width);
        int childMeasureSpec2 = ViewGroup.getChildMeasureSpec(i3, getPaddingBottom() + getPaddingTop() + marginLayoutParams.topMargin + marginLayoutParams.bottomMargin + i4, marginLayoutParams.height);
        int mode = View.MeasureSpec.getMode(childMeasureSpec2);
        if (mode != 1073741824 && i5 >= 0) {
            if (mode != 0) {
                i5 = Math.min(View.MeasureSpec.getSize(childMeasureSpec2), i5);
            }
            childMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(i5, 1073741824);
        }
        view.measure(childMeasureSpec, childMeasureSpec2);
    }

    private boolean shouldLayout(View view) {
        return (view == null || view.getParent() != this || view.getVisibility() == 8) ? false : true;
    }

    /* access modifiers changed from: package-private */
    public void addChildrenForExpandedActionView() {
        for (int size = this.mHiddenViews.size() - 1; size >= 0; size--) {
            addView(this.mHiddenViews.get(size));
        }
        this.mHiddenViews.clear();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0006, code lost:
        r1 = r1.mMenuView;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean canShowOverflowMenu() {
        /*
            r1 = this;
            int r0 = r1.getVisibility()
            if (r0 != 0) goto L_0x0012
            android.support.v7.widget.ActionMenuView r1 = r1.mMenuView
            if (r1 == 0) goto L_0x0012
            boolean r1 = r1.isOverflowReserved()
            if (r1 == 0) goto L_0x0012
            r1 = 1
            goto L_0x0013
        L_0x0012:
            r1 = 0
        L_0x0013:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.p002v7.widget.Toolbar.canShowOverflowMenu():boolean");
    }

    /* access modifiers changed from: protected */
    public boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return super.checkLayoutParams(layoutParams) && (layoutParams instanceof LayoutParams);
    }

    public void collapseActionView() {
        ExpandedActionViewMenuPresenter expandedActionViewMenuPresenter = this.mExpandedMenuPresenter;
        MenuItemImpl menuItemImpl = expandedActionViewMenuPresenter == null ? null : expandedActionViewMenuPresenter.mCurrentExpandedItem;
        if (menuItemImpl != null) {
            menuItemImpl.collapseActionView();
        }
    }

    public void dismissPopupMenus() {
        ActionMenuView actionMenuView = this.mMenuView;
        if (actionMenuView != null) {
            actionMenuView.dismissPopupMenus();
        }
    }

    /* access modifiers changed from: package-private */
    public void ensureCollapseButtonView() {
        if (this.mCollapseButtonView == null) {
            this.mCollapseButtonView = new AppCompatImageButton(getContext(), (AttributeSet) null, R.attr.toolbarNavigationButtonStyle);
            this.mCollapseButtonView.setImageDrawable(this.mCollapseIcon);
            this.mCollapseButtonView.setContentDescription(this.mCollapseDescription);
            LayoutParams generateDefaultLayoutParams = generateDefaultLayoutParams();
            generateDefaultLayoutParams.gravity = 8388611 | (this.mButtonGravity & 112);
            generateDefaultLayoutParams.mViewType = 2;
            this.mCollapseButtonView.setLayoutParams(generateDefaultLayoutParams);
            this.mCollapseButtonView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    Toolbar.this.collapseActionView();
                }
            });
        }
    }

    public int getContentInsetEnd() {
        RtlSpacingHelper rtlSpacingHelper = this.mContentInsets;
        if (rtlSpacingHelper != null) {
            return rtlSpacingHelper.getEnd();
        }
        return 0;
    }

    public int getContentInsetStart() {
        RtlSpacingHelper rtlSpacingHelper = this.mContentInsets;
        if (rtlSpacingHelper != null) {
            return rtlSpacingHelper.getStart();
        }
        return 0;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0005, code lost:
        r0 = r0.peekMenu();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int getCurrentContentInsetEnd() {
        /*
            r2 = this;
            android.support.v7.widget.ActionMenuView r0 = r2.mMenuView
            r1 = 0
            if (r0 == 0) goto L_0x0013
            android.support.v7.view.menu.MenuBuilder r0 = r0.peekMenu()
            if (r0 == 0) goto L_0x0013
            boolean r0 = r0.hasVisibleItems()
            if (r0 == 0) goto L_0x0013
            r0 = 1
            goto L_0x0014
        L_0x0013:
            r0 = r1
        L_0x0014:
            if (r0 == 0) goto L_0x0025
            int r0 = r2.getContentInsetEnd()
            int r2 = r2.mContentInsetEndWithActions
            int r2 = java.lang.Math.max(r2, r1)
            int r2 = java.lang.Math.max(r0, r2)
            goto L_0x0029
        L_0x0025:
            int r2 = r2.getContentInsetEnd()
        L_0x0029:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.p002v7.widget.Toolbar.getCurrentContentInsetEnd():int");
    }

    public int getCurrentContentInsetLeft() {
        if (ViewCompat.getLayoutDirection(this) == 1) {
            return getCurrentContentInsetEnd();
        }
        return getCurrentContentInsetStart();
    }

    public int getCurrentContentInsetRight() {
        if (ViewCompat.getLayoutDirection(this) == 1) {
            return getCurrentContentInsetStart();
        }
        return getCurrentContentInsetEnd();
    }

    public int getCurrentContentInsetStart() {
        if (getNavigationIcon() != null) {
            return Math.max(getContentInsetStart(), Math.max(this.mContentInsetStartWithNavigation, 0));
        }
        return getContentInsetStart();
    }

    public Menu getMenu() {
        ensureMenuView();
        if (this.mMenuView.peekMenu() == null) {
            MenuBuilder menuBuilder = (MenuBuilder) this.mMenuView.getMenu();
            if (this.mExpandedMenuPresenter == null) {
                this.mExpandedMenuPresenter = new ExpandedActionViewMenuPresenter();
            }
            this.mMenuView.setExpandedActionViewsExclusive(true);
            menuBuilder.addMenuPresenter(this.mExpandedMenuPresenter, this.mPopupContext);
        }
        return this.mMenuView.getMenu();
    }

    public CharSequence getNavigationContentDescription() {
        ImageButton imageButton = this.mNavButtonView;
        if (imageButton != null) {
            return imageButton.getContentDescription();
        }
        return null;
    }

    public Drawable getNavigationIcon() {
        ImageButton imageButton = this.mNavButtonView;
        if (imageButton != null) {
            return imageButton.getDrawable();
        }
        return null;
    }

    public CharSequence getSubtitle() {
        return this.mSubtitleText;
    }

    public CharSequence getTitle() {
        return this.mTitleText;
    }

    public DecorToolbar getWrapper() {
        if (this.mWrapper == null) {
            this.mWrapper = new ToolbarWidgetWrapper(this, true);
        }
        return this.mWrapper;
    }

    public boolean hasExpandedActionView() {
        ExpandedActionViewMenuPresenter expandedActionViewMenuPresenter = this.mExpandedMenuPresenter;
        return (expandedActionViewMenuPresenter == null || expandedActionViewMenuPresenter.mCurrentExpandedItem == null) ? false : true;
    }

    public boolean hideOverflowMenu() {
        ActionMenuView actionMenuView = this.mMenuView;
        return actionMenuView != null && actionMenuView.hideOverflowMenu();
    }

    public boolean isOverflowMenuShowPending() {
        ActionMenuView actionMenuView = this.mMenuView;
        return actionMenuView != null && actionMenuView.isOverflowMenuShowPending();
    }

    public boolean isOverflowMenuShowing() {
        ActionMenuView actionMenuView = this.mMenuView;
        return actionMenuView != null && actionMenuView.isOverflowMenuShowing();
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        removeCallbacks(this.mShowOverflowMenuRunnable);
    }

    public boolean onHoverEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 9) {
            this.mEatingHover = false;
        }
        if (!this.mEatingHover) {
            boolean onHoverEvent = super.onHoverEvent(motionEvent);
            if (actionMasked == 9 && !onHoverEvent) {
                this.mEatingHover = true;
            }
        }
        if (actionMasked == 10 || actionMasked == 3) {
            this.mEatingHover = false;
        }
        return true;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:101:0x02a5 A[LOOP:0: B:100:0x02a3->B:101:0x02a5, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:104:0x02c7 A[LOOP:1: B:103:0x02c5->B:104:0x02c7, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:107:0x02ed A[LOOP:2: B:106:0x02eb->B:107:0x02ed, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:110:0x032f  */
    /* JADX WARNING: Removed duplicated region for block: B:115:0x033e A[LOOP:3: B:114:0x033c->B:115:0x033e, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x005f  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0076  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x00b3  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x00ca  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x00e7  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x0100  */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x0105  */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x011d  */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x012c  */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x012f  */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x0133  */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x0136  */
    /* JADX WARNING: Removed duplicated region for block: B:62:0x0169  */
    /* JADX WARNING: Removed duplicated region for block: B:72:0x01a7  */
    /* JADX WARNING: Removed duplicated region for block: B:74:0x01b8  */
    /* JADX WARNING: Removed duplicated region for block: B:87:0x022a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onLayout(boolean r20, int r21, int r22, int r23, int r24) {
        /*
            r19 = this;
            r0 = r19
            int r1 = android.support.p000v4.view.ViewCompat.getLayoutDirection(r19)
            r2 = 1
            r3 = 0
            if (r1 != r2) goto L_0x000c
            r1 = r2
            goto L_0x000d
        L_0x000c:
            r1 = r3
        L_0x000d:
            int r4 = r19.getWidth()
            int r5 = r19.getHeight()
            int r6 = r19.getPaddingLeft()
            int r7 = r19.getPaddingRight()
            int r8 = r19.getPaddingTop()
            int r9 = r19.getPaddingBottom()
            int r10 = r4 - r7
            int[] r11 = r0.mTempMargins
            r11[r2] = r3
            r11[r3] = r3
            int r12 = android.support.p000v4.view.ViewCompat.getMinimumHeight(r19)
            if (r12 < 0) goto L_0x003a
            int r13 = r24 - r22
            int r12 = java.lang.Math.min(r12, r13)
            goto L_0x003b
        L_0x003a:
            r12 = r3
        L_0x003b:
            android.widget.ImageButton r13 = r0.mNavButtonView
            boolean r13 = r0.shouldLayout(r13)
            if (r13 == 0) goto L_0x0055
            if (r1 == 0) goto L_0x004e
            android.widget.ImageButton r13 = r0.mNavButtonView
            int r13 = r0.layoutChildRight(r13, r10, r11, r12)
            r14 = r13
            r13 = r6
            goto L_0x0057
        L_0x004e:
            android.widget.ImageButton r13 = r0.mNavButtonView
            int r13 = r0.layoutChildLeft(r13, r6, r11, r12)
            goto L_0x0056
        L_0x0055:
            r13 = r6
        L_0x0056:
            r14 = r10
        L_0x0057:
            android.widget.ImageButton r15 = r0.mCollapseButtonView
            boolean r15 = r0.shouldLayout(r15)
            if (r15 == 0) goto L_0x006e
            if (r1 == 0) goto L_0x0068
            android.widget.ImageButton r15 = r0.mCollapseButtonView
            int r14 = r0.layoutChildRight(r15, r14, r11, r12)
            goto L_0x006e
        L_0x0068:
            android.widget.ImageButton r15 = r0.mCollapseButtonView
            int r13 = r0.layoutChildLeft(r15, r13, r11, r12)
        L_0x006e:
            android.support.v7.widget.ActionMenuView r15 = r0.mMenuView
            boolean r15 = r0.shouldLayout(r15)
            if (r15 == 0) goto L_0x0085
            if (r1 == 0) goto L_0x007f
            android.support.v7.widget.ActionMenuView r15 = r0.mMenuView
            int r13 = r0.layoutChildLeft(r15, r13, r11, r12)
            goto L_0x0085
        L_0x007f:
            android.support.v7.widget.ActionMenuView r15 = r0.mMenuView
            int r14 = r0.layoutChildRight(r15, r14, r11, r12)
        L_0x0085:
            int r15 = r19.getCurrentContentInsetLeft()
            int r16 = r19.getCurrentContentInsetRight()
            int r2 = r15 - r13
            int r2 = java.lang.Math.max(r3, r2)
            r11[r3] = r2
            int r2 = r10 - r14
            int r2 = r16 - r2
            int r2 = java.lang.Math.max(r3, r2)
            r17 = 1
            r11[r17] = r2
            int r2 = java.lang.Math.max(r13, r15)
            int r10 = r10 - r16
            int r10 = java.lang.Math.min(r14, r10)
            android.view.View r13 = r0.mExpandedActionView
            boolean r13 = r0.shouldLayout(r13)
            if (r13 == 0) goto L_0x00c2
            if (r1 == 0) goto L_0x00bc
            android.view.View r13 = r0.mExpandedActionView
            int r10 = r0.layoutChildRight(r13, r10, r11, r12)
            goto L_0x00c2
        L_0x00bc:
            android.view.View r13 = r0.mExpandedActionView
            int r2 = r0.layoutChildLeft(r13, r2, r11, r12)
        L_0x00c2:
            android.widget.ImageView r13 = r0.mLogoView
            boolean r13 = r0.shouldLayout(r13)
            if (r13 == 0) goto L_0x00d9
            if (r1 == 0) goto L_0x00d3
            android.widget.ImageView r13 = r0.mLogoView
            int r10 = r0.layoutChildRight(r13, r10, r11, r12)
            goto L_0x00d9
        L_0x00d3:
            android.widget.ImageView r13 = r0.mLogoView
            int r2 = r0.layoutChildLeft(r13, r2, r11, r12)
        L_0x00d9:
            android.widget.TextView r13 = r0.mTitleTextView
            boolean r13 = r0.shouldLayout(r13)
            android.widget.TextView r14 = r0.mSubtitleTextView
            boolean r14 = r0.shouldLayout(r14)
            if (r13 == 0) goto L_0x0100
            android.widget.TextView r15 = r0.mTitleTextView
            android.view.ViewGroup$LayoutParams r15 = r15.getLayoutParams()
            android.support.v7.widget.Toolbar$LayoutParams r15 = (android.support.p002v7.widget.Toolbar.LayoutParams) r15
            int r3 = r15.topMargin
            r23 = r7
            android.widget.TextView r7 = r0.mTitleTextView
            int r7 = r7.getMeasuredHeight()
            int r7 = r7 + r3
            int r3 = r15.bottomMargin
            int r7 = r7 + r3
            r3 = 0
            int r7 = r7 + r3
            goto L_0x0103
        L_0x0100:
            r23 = r7
            r7 = 0
        L_0x0103:
            if (r14 == 0) goto L_0x011d
            android.widget.TextView r3 = r0.mSubtitleTextView
            android.view.ViewGroup$LayoutParams r3 = r3.getLayoutParams()
            android.support.v7.widget.Toolbar$LayoutParams r3 = (android.support.p002v7.widget.Toolbar.LayoutParams) r3
            int r15 = r3.topMargin
            r16 = r4
            android.widget.TextView r4 = r0.mSubtitleTextView
            int r4 = r4.getMeasuredHeight()
            int r4 = r4 + r15
            int r3 = r3.bottomMargin
            int r4 = r4 + r3
            int r7 = r7 + r4
            goto L_0x011f
        L_0x011d:
            r16 = r4
        L_0x011f:
            if (r13 != 0) goto L_0x012a
            if (r14 == 0) goto L_0x0124
            goto L_0x012a
        L_0x0124:
            r17 = r6
            r22 = r12
            goto L_0x0295
        L_0x012a:
            if (r13 == 0) goto L_0x012f
            android.widget.TextView r3 = r0.mTitleTextView
            goto L_0x0131
        L_0x012f:
            android.widget.TextView r3 = r0.mSubtitleTextView
        L_0x0131:
            if (r14 == 0) goto L_0x0136
            android.widget.TextView r4 = r0.mSubtitleTextView
            goto L_0x0138
        L_0x0136:
            android.widget.TextView r4 = r0.mTitleTextView
        L_0x0138:
            android.view.ViewGroup$LayoutParams r3 = r3.getLayoutParams()
            android.support.v7.widget.Toolbar$LayoutParams r3 = (android.support.p002v7.widget.Toolbar.LayoutParams) r3
            android.view.ViewGroup$LayoutParams r4 = r4.getLayoutParams()
            android.support.v7.widget.Toolbar$LayoutParams r4 = (android.support.p002v7.widget.Toolbar.LayoutParams) r4
            if (r13 == 0) goto L_0x014e
            android.widget.TextView r15 = r0.mTitleTextView
            int r15 = r15.getMeasuredWidth()
            if (r15 > 0) goto L_0x0158
        L_0x014e:
            if (r14 == 0) goto L_0x015c
            android.widget.TextView r15 = r0.mSubtitleTextView
            int r15 = r15.getMeasuredWidth()
            if (r15 <= 0) goto L_0x015c
        L_0x0158:
            r17 = r6
            r15 = 1
            goto L_0x015f
        L_0x015c:
            r17 = r6
            r15 = 0
        L_0x015f:
            int r6 = r0.mGravity
            r6 = r6 & 112(0x70, float:1.57E-43)
            r22 = r12
            r12 = 48
            if (r6 == r12) goto L_0x01a7
            r12 = 80
            if (r6 == r12) goto L_0x0199
            int r6 = r5 - r8
            int r6 = r6 - r9
            int r6 = r6 - r7
            int r6 = r6 / 2
            int r12 = r3.topMargin
            r24 = r2
            int r2 = r0.mTitleMarginTop
            r18 = r14
            int r14 = r12 + r2
            if (r6 >= r14) goto L_0x0182
            int r6 = r12 + r2
            goto L_0x0197
        L_0x0182:
            int r5 = r5 - r9
            int r5 = r5 - r7
            int r5 = r5 - r6
            int r5 = r5 - r8
            int r2 = r3.bottomMargin
            int r3 = r0.mTitleMarginBottom
            int r2 = r2 + r3
            if (r5 >= r2) goto L_0x0197
            int r2 = r4.bottomMargin
            int r2 = r2 + r3
            int r2 = r2 - r5
            int r6 = r6 - r2
            r2 = 0
            int r6 = java.lang.Math.max(r2, r6)
        L_0x0197:
            int r8 = r8 + r6
            goto L_0x01b6
        L_0x0199:
            r24 = r2
            r18 = r14
            int r5 = r5 - r9
            int r2 = r4.bottomMargin
            int r5 = r5 - r2
            int r2 = r0.mTitleMarginBottom
            int r5 = r5 - r2
            int r8 = r5 - r7
            goto L_0x01b6
        L_0x01a7:
            r24 = r2
            r18 = r14
            int r2 = r19.getPaddingTop()
            int r3 = r3.topMargin
            int r2 = r2 + r3
            int r3 = r0.mTitleMarginTop
            int r8 = r2 + r3
        L_0x01b6:
            if (r1 == 0) goto L_0x022a
            if (r15 == 0) goto L_0x01be
            int r3 = r0.mTitleMarginStart
            r1 = 1
            goto L_0x01c0
        L_0x01be:
            r1 = 1
            r3 = 0
        L_0x01c0:
            r2 = r11[r1]
            int r3 = r3 - r2
            r2 = 0
            int r4 = java.lang.Math.max(r2, r3)
            int r10 = r10 - r4
            int r3 = -r3
            int r3 = java.lang.Math.max(r2, r3)
            r11[r1] = r3
            if (r13 == 0) goto L_0x01f6
            android.widget.TextView r1 = r0.mTitleTextView
            android.view.ViewGroup$LayoutParams r1 = r1.getLayoutParams()
            android.support.v7.widget.Toolbar$LayoutParams r1 = (android.support.p002v7.widget.Toolbar.LayoutParams) r1
            android.widget.TextView r2 = r0.mTitleTextView
            int r2 = r2.getMeasuredWidth()
            int r2 = r10 - r2
            android.widget.TextView r3 = r0.mTitleTextView
            int r3 = r3.getMeasuredHeight()
            int r3 = r3 + r8
            android.widget.TextView r4 = r0.mTitleTextView
            r4.layout(r2, r8, r10, r3)
            int r4 = r0.mTitleMarginEnd
            int r2 = r2 - r4
            int r1 = r1.bottomMargin
            int r8 = r3 + r1
            goto L_0x01f7
        L_0x01f6:
            r2 = r10
        L_0x01f7:
            if (r18 == 0) goto L_0x021f
            android.widget.TextView r1 = r0.mSubtitleTextView
            android.view.ViewGroup$LayoutParams r1 = r1.getLayoutParams()
            android.support.v7.widget.Toolbar$LayoutParams r1 = (android.support.p002v7.widget.Toolbar.LayoutParams) r1
            int r3 = r1.topMargin
            int r8 = r8 + r3
            android.widget.TextView r3 = r0.mSubtitleTextView
            int r3 = r3.getMeasuredWidth()
            int r3 = r10 - r3
            android.widget.TextView r4 = r0.mSubtitleTextView
            int r4 = r4.getMeasuredHeight()
            int r4 = r4 + r8
            android.widget.TextView r5 = r0.mSubtitleTextView
            r5.layout(r3, r8, r10, r4)
            int r3 = r0.mTitleMarginEnd
            int r3 = r10 - r3
            int r1 = r1.bottomMargin
            goto L_0x0220
        L_0x021f:
            r3 = r10
        L_0x0220:
            if (r15 == 0) goto L_0x0227
            int r1 = java.lang.Math.min(r2, r3)
            r10 = r1
        L_0x0227:
            r2 = r24
            goto L_0x0295
        L_0x022a:
            if (r15 == 0) goto L_0x0230
            int r3 = r0.mTitleMarginStart
            r1 = 0
            goto L_0x0232
        L_0x0230:
            r1 = 0
            r3 = 0
        L_0x0232:
            r2 = r11[r1]
            int r3 = r3 - r2
            int r2 = java.lang.Math.max(r1, r3)
            int r2 = r2 + r24
            int r3 = -r3
            int r3 = java.lang.Math.max(r1, r3)
            r11[r1] = r3
            if (r13 == 0) goto L_0x0267
            android.widget.TextView r1 = r0.mTitleTextView
            android.view.ViewGroup$LayoutParams r1 = r1.getLayoutParams()
            android.support.v7.widget.Toolbar$LayoutParams r1 = (android.support.p002v7.widget.Toolbar.LayoutParams) r1
            android.widget.TextView r3 = r0.mTitleTextView
            int r3 = r3.getMeasuredWidth()
            int r3 = r3 + r2
            android.widget.TextView r4 = r0.mTitleTextView
            int r4 = r4.getMeasuredHeight()
            int r4 = r4 + r8
            android.widget.TextView r5 = r0.mTitleTextView
            r5.layout(r2, r8, r3, r4)
            int r5 = r0.mTitleMarginEnd
            int r3 = r3 + r5
            int r1 = r1.bottomMargin
            int r8 = r4 + r1
            goto L_0x0268
        L_0x0267:
            r3 = r2
        L_0x0268:
            if (r18 == 0) goto L_0x028e
            android.widget.TextView r1 = r0.mSubtitleTextView
            android.view.ViewGroup$LayoutParams r1 = r1.getLayoutParams()
            android.support.v7.widget.Toolbar$LayoutParams r1 = (android.support.p002v7.widget.Toolbar.LayoutParams) r1
            int r4 = r1.topMargin
            int r8 = r8 + r4
            android.widget.TextView r4 = r0.mSubtitleTextView
            int r4 = r4.getMeasuredWidth()
            int r4 = r4 + r2
            android.widget.TextView r5 = r0.mSubtitleTextView
            int r5 = r5.getMeasuredHeight()
            int r5 = r5 + r8
            android.widget.TextView r6 = r0.mSubtitleTextView
            r6.layout(r2, r8, r4, r5)
            int r5 = r0.mTitleMarginEnd
            int r4 = r4 + r5
            int r1 = r1.bottomMargin
            goto L_0x028f
        L_0x028e:
            r4 = r2
        L_0x028f:
            if (r15 == 0) goto L_0x0295
            int r2 = java.lang.Math.max(r3, r4)
        L_0x0295:
            java.util.ArrayList<android.view.View> r1 = r0.mTempViews
            r3 = 3
            r0.addCustomViewsWithGravity(r1, r3)
            java.util.ArrayList<android.view.View> r1 = r0.mTempViews
            int r1 = r1.size()
            r3 = r2
            r2 = 0
        L_0x02a3:
            if (r2 >= r1) goto L_0x02b6
            java.util.ArrayList<android.view.View> r4 = r0.mTempViews
            java.lang.Object r4 = r4.get(r2)
            android.view.View r4 = (android.view.View) r4
            r12 = r22
            int r3 = r0.layoutChildLeft(r4, r3, r11, r12)
            int r2 = r2 + 1
            goto L_0x02a3
        L_0x02b6:
            r12 = r22
            java.util.ArrayList<android.view.View> r1 = r0.mTempViews
            r2 = 5
            r0.addCustomViewsWithGravity(r1, r2)
            java.util.ArrayList<android.view.View> r1 = r0.mTempViews
            int r1 = r1.size()
            r2 = 0
        L_0x02c5:
            if (r2 >= r1) goto L_0x02d6
            java.util.ArrayList<android.view.View> r4 = r0.mTempViews
            java.lang.Object r4 = r4.get(r2)
            android.view.View r4 = (android.view.View) r4
            int r10 = r0.layoutChildRight(r4, r10, r11, r12)
            int r2 = r2 + 1
            goto L_0x02c5
        L_0x02d6:
            java.util.ArrayList<android.view.View> r1 = r0.mTempViews
            r2 = 1
            r0.addCustomViewsWithGravity(r1, r2)
            java.util.ArrayList<android.view.View> r1 = r0.mTempViews
            r4 = 0
            r5 = r11[r4]
            r2 = r11[r2]
            int r4 = r1.size()
            r7 = r2
            r6 = r5
            r2 = 0
            r5 = 0
        L_0x02eb:
            if (r2 >= r4) goto L_0x031e
            java.lang.Object r8 = r1.get(r2)
            android.view.View r8 = (android.view.View) r8
            android.view.ViewGroup$LayoutParams r9 = r8.getLayoutParams()
            android.support.v7.widget.Toolbar$LayoutParams r9 = (android.support.p002v7.widget.Toolbar.LayoutParams) r9
            int r13 = r9.leftMargin
            int r13 = r13 - r6
            int r6 = r9.rightMargin
            int r6 = r6 - r7
            r7 = 0
            int r9 = java.lang.Math.max(r7, r13)
            int r14 = java.lang.Math.max(r7, r6)
            int r13 = -r13
            int r13 = java.lang.Math.max(r7, r13)
            int r6 = -r6
            int r6 = java.lang.Math.max(r7, r6)
            int r8 = r8.getMeasuredWidth()
            int r8 = r8 + r9
            int r8 = r8 + r14
            int r5 = r5 + r8
            int r2 = r2 + 1
            r7 = r6
            r6 = r13
            goto L_0x02eb
        L_0x031e:
            r7 = 0
            int r4 = r16 - r17
            int r4 = r4 - r23
            int r4 = r4 / 2
            int r4 = r4 + r17
            int r1 = r5 / 2
            int r1 = r4 - r1
            int r5 = r5 + r1
            if (r1 >= r3) goto L_0x032f
            goto L_0x0336
        L_0x032f:
            if (r5 <= r10) goto L_0x0335
            int r5 = r5 - r10
            int r3 = r1 - r5
            goto L_0x0336
        L_0x0335:
            r3 = r1
        L_0x0336:
            java.util.ArrayList<android.view.View> r1 = r0.mTempViews
            int r1 = r1.size()
        L_0x033c:
            if (r7 >= r1) goto L_0x034d
            java.util.ArrayList<android.view.View> r2 = r0.mTempViews
            java.lang.Object r2 = r2.get(r7)
            android.view.View r2 = (android.view.View) r2
            int r3 = r0.layoutChildLeft(r2, r3, r11, r12)
            int r7 = r7 + 1
            goto L_0x033c
        L_0x034d:
            java.util.ArrayList<android.view.View> r0 = r0.mTempViews
            r0.clear()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.p002v7.widget.Toolbar.onLayout(boolean, int, int, int, int):void");
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x0298  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onMeasure(int r18, int r19) {
        /*
            r17 = this;
            r7 = r17
            int[] r8 = r7.mTempMargins
            boolean r0 = android.support.p002v7.widget.ViewUtils.isLayoutRtl(r17)
            r9 = 1
            r10 = 0
            if (r0 == 0) goto L_0x000f
            r11 = r9
            r12 = r10
            goto L_0x0011
        L_0x000f:
            r12 = r9
            r11 = r10
        L_0x0011:
            android.widget.ImageButton r0 = r7.mNavButtonView
            boolean r0 = r7.shouldLayout(r0)
            if (r0 == 0) goto L_0x0053
            android.widget.ImageButton r1 = r7.mNavButtonView
            r5 = 0
            int r6 = r7.mMaxButtonHeight
            r3 = 0
            r0 = r17
            r2 = r18
            r4 = r19
            r0.measureChildConstrained(r1, r2, r3, r4, r5, r6)
            android.widget.ImageButton r0 = r7.mNavButtonView
            int r0 = r0.getMeasuredWidth()
            android.widget.ImageButton r1 = r7.mNavButtonView
            int r1 = r7.getHorizontalMargins(r1)
            int r0 = r0 + r1
            android.widget.ImageButton r1 = r7.mNavButtonView
            int r1 = r1.getMeasuredHeight()
            android.widget.ImageButton r2 = r7.mNavButtonView
            int r2 = r7.getVerticalMargins(r2)
            int r1 = r1 + r2
            int r1 = java.lang.Math.max(r10, r1)
            android.widget.ImageButton r2 = r7.mNavButtonView
            int r2 = r2.getMeasuredState()
            int r2 = android.view.View.combineMeasuredStates(r10, r2)
            r13 = r1
            r14 = r2
            goto L_0x0056
        L_0x0053:
            r0 = r10
            r13 = r0
            r14 = r13
        L_0x0056:
            android.widget.ImageButton r1 = r7.mCollapseButtonView
            boolean r1 = r7.shouldLayout(r1)
            if (r1 == 0) goto L_0x0095
            android.widget.ImageButton r1 = r7.mCollapseButtonView
            r5 = 0
            int r6 = r7.mMaxButtonHeight
            r3 = 0
            r0 = r17
            r2 = r18
            r4 = r19
            r0.measureChildConstrained(r1, r2, r3, r4, r5, r6)
            android.widget.ImageButton r0 = r7.mCollapseButtonView
            int r0 = r0.getMeasuredWidth()
            android.widget.ImageButton r1 = r7.mCollapseButtonView
            int r1 = r7.getHorizontalMargins(r1)
            int r0 = r0 + r1
            android.widget.ImageButton r1 = r7.mCollapseButtonView
            int r1 = r1.getMeasuredHeight()
            android.widget.ImageButton r2 = r7.mCollapseButtonView
            int r2 = r7.getVerticalMargins(r2)
            int r1 = r1 + r2
            int r13 = java.lang.Math.max(r13, r1)
            android.widget.ImageButton r1 = r7.mCollapseButtonView
            int r1 = r1.getMeasuredState()
            int r14 = android.view.View.combineMeasuredStates(r14, r1)
        L_0x0095:
            int r1 = r17.getCurrentContentInsetStart()
            int r2 = java.lang.Math.max(r1, r0)
            int r15 = r2 + 0
            int r1 = r1 - r0
            int r0 = java.lang.Math.max(r10, r1)
            r8[r11] = r0
            android.support.v7.widget.ActionMenuView r0 = r7.mMenuView
            boolean r0 = r7.shouldLayout(r0)
            if (r0 == 0) goto L_0x00e6
            android.support.v7.widget.ActionMenuView r1 = r7.mMenuView
            r5 = 0
            int r6 = r7.mMaxButtonHeight
            r0 = r17
            r2 = r18
            r3 = r15
            r4 = r19
            r0.measureChildConstrained(r1, r2, r3, r4, r5, r6)
            android.support.v7.widget.ActionMenuView r0 = r7.mMenuView
            int r0 = r0.getMeasuredWidth()
            android.support.v7.widget.ActionMenuView r1 = r7.mMenuView
            int r1 = r7.getHorizontalMargins(r1)
            int r0 = r0 + r1
            android.support.v7.widget.ActionMenuView r1 = r7.mMenuView
            int r1 = r1.getMeasuredHeight()
            android.support.v7.widget.ActionMenuView r2 = r7.mMenuView
            int r2 = r7.getVerticalMargins(r2)
            int r1 = r1 + r2
            int r13 = java.lang.Math.max(r13, r1)
            android.support.v7.widget.ActionMenuView r1 = r7.mMenuView
            int r1 = r1.getMeasuredState()
            int r14 = android.view.View.combineMeasuredStates(r14, r1)
            goto L_0x00e7
        L_0x00e6:
            r0 = r10
        L_0x00e7:
            int r1 = r17.getCurrentContentInsetEnd()
            int r2 = java.lang.Math.max(r1, r0)
            int r11 = r2 + r15
            int r1 = r1 - r0
            int r0 = java.lang.Math.max(r10, r1)
            r8[r12] = r0
            android.view.View r0 = r7.mExpandedActionView
            boolean r0 = r7.shouldLayout(r0)
            if (r0 == 0) goto L_0x012b
            android.view.View r1 = r7.mExpandedActionView
            r5 = 0
            r0 = r17
            r2 = r18
            r3 = r11
            r4 = r19
            r6 = r8
            int r0 = r0.measureChildCollapseMargins(r1, r2, r3, r4, r5, r6)
            int r11 = r11 + r0
            android.view.View r0 = r7.mExpandedActionView
            int r0 = r0.getMeasuredHeight()
            android.view.View r1 = r7.mExpandedActionView
            int r1 = r7.getVerticalMargins(r1)
            int r0 = r0 + r1
            int r13 = java.lang.Math.max(r13, r0)
            android.view.View r0 = r7.mExpandedActionView
            int r0 = r0.getMeasuredState()
            int r14 = android.view.View.combineMeasuredStates(r14, r0)
        L_0x012b:
            android.widget.ImageView r0 = r7.mLogoView
            boolean r0 = r7.shouldLayout(r0)
            if (r0 == 0) goto L_0x015e
            android.widget.ImageView r1 = r7.mLogoView
            r5 = 0
            r0 = r17
            r2 = r18
            r3 = r11
            r4 = r19
            r6 = r8
            int r0 = r0.measureChildCollapseMargins(r1, r2, r3, r4, r5, r6)
            int r11 = r11 + r0
            android.widget.ImageView r0 = r7.mLogoView
            int r0 = r0.getMeasuredHeight()
            android.widget.ImageView r1 = r7.mLogoView
            int r1 = r7.getVerticalMargins(r1)
            int r0 = r0 + r1
            int r13 = java.lang.Math.max(r13, r0)
            android.widget.ImageView r0 = r7.mLogoView
            int r0 = r0.getMeasuredState()
            int r14 = android.view.View.combineMeasuredStates(r14, r0)
        L_0x015e:
            int r12 = r17.getChildCount()
            r15 = r13
            r13 = r11
            r11 = r10
        L_0x0165:
            if (r11 >= r12) goto L_0x01a9
            android.view.View r6 = r7.getChildAt(r11)
            android.view.ViewGroup$LayoutParams r0 = r6.getLayoutParams()
            android.support.v7.widget.Toolbar$LayoutParams r0 = (android.support.p002v7.widget.Toolbar.LayoutParams) r0
            int r0 = r0.mViewType
            if (r0 != 0) goto L_0x01a6
            boolean r0 = r7.shouldLayout(r6)
            if (r0 != 0) goto L_0x017c
            goto L_0x01a6
        L_0x017c:
            r5 = 0
            r0 = r17
            r1 = r6
            r2 = r18
            r3 = r13
            r4 = r19
            r16 = r6
            r6 = r8
            int r0 = r0.measureChildCollapseMargins(r1, r2, r3, r4, r5, r6)
            int r13 = r13 + r0
            int r0 = r16.getMeasuredHeight()
            r1 = r16
            int r2 = r7.getVerticalMargins(r1)
            int r0 = r0 + r2
            int r0 = java.lang.Math.max(r15, r0)
            int r1 = r1.getMeasuredState()
            int r1 = android.view.View.combineMeasuredStates(r14, r1)
            r15 = r0
            r14 = r1
        L_0x01a6:
            int r11 = r11 + 1
            goto L_0x0165
        L_0x01a9:
            int r0 = r7.mTitleMarginTop
            int r1 = r7.mTitleMarginBottom
            int r11 = r0 + r1
            int r0 = r7.mTitleMarginStart
            int r1 = r7.mTitleMarginEnd
            int r12 = r0 + r1
            android.widget.TextView r0 = r7.mTitleTextView
            boolean r0 = r7.shouldLayout(r0)
            if (r0 == 0) goto L_0x01f5
            android.widget.TextView r1 = r7.mTitleTextView
            int r3 = r13 + r12
            r0 = r17
            r2 = r18
            r4 = r19
            r5 = r11
            r6 = r8
            r0.measureChildCollapseMargins(r1, r2, r3, r4, r5, r6)
            android.widget.TextView r0 = r7.mTitleTextView
            int r0 = r0.getMeasuredWidth()
            android.widget.TextView r1 = r7.mTitleTextView
            int r1 = r7.getHorizontalMargins(r1)
            int r0 = r0 + r1
            android.widget.TextView r1 = r7.mTitleTextView
            int r1 = r1.getMeasuredHeight()
            android.widget.TextView r2 = r7.mTitleTextView
            int r2 = r7.getVerticalMargins(r2)
            int r1 = r1 + r2
            android.widget.TextView r2 = r7.mTitleTextView
            int r2 = r2.getMeasuredState()
            int r14 = android.view.View.combineMeasuredStates(r14, r2)
            r16 = r1
            r6 = r14
            r14 = r0
            goto L_0x01fa
        L_0x01f5:
            r16 = r10
            r6 = r14
            r14 = r16
        L_0x01fa:
            android.widget.TextView r0 = r7.mSubtitleTextView
            boolean r0 = r7.shouldLayout(r0)
            if (r0 == 0) goto L_0x0232
            android.widget.TextView r1 = r7.mSubtitleTextView
            int r3 = r13 + r12
            int r5 = r16 + r11
            r0 = r17
            r2 = r18
            r4 = r19
            r11 = r6
            r6 = r8
            int r0 = r0.measureChildCollapseMargins(r1, r2, r3, r4, r5, r6)
            int r14 = java.lang.Math.max(r14, r0)
            android.widget.TextView r0 = r7.mSubtitleTextView
            int r0 = r0.getMeasuredHeight()
            android.widget.TextView r1 = r7.mSubtitleTextView
            int r1 = r7.getVerticalMargins(r1)
            int r0 = r0 + r1
            int r16 = r0 + r16
            android.widget.TextView r0 = r7.mSubtitleTextView
            int r0 = r0.getMeasuredState()
            int r6 = android.view.View.combineMeasuredStates(r11, r0)
            goto L_0x0233
        L_0x0232:
            r11 = r6
        L_0x0233:
            r0 = r16
            int r13 = r13 + r14
            int r0 = java.lang.Math.max(r15, r0)
            int r1 = r17.getPaddingLeft()
            int r2 = r17.getPaddingRight()
            int r2 = r2 + r1
            int r2 = r2 + r13
            int r1 = r17.getPaddingTop()
            int r3 = r17.getPaddingBottom()
            int r3 = r3 + r1
            int r3 = r3 + r0
            int r0 = r17.getSuggestedMinimumWidth()
            int r0 = java.lang.Math.max(r2, r0)
            r1 = -16777216(0xffffffffff000000, float:-1.7014118E38)
            r1 = r1 & r6
            r2 = r18
            int r0 = android.view.View.resolveSizeAndState(r0, r2, r1)
            int r1 = r17.getSuggestedMinimumHeight()
            int r1 = java.lang.Math.max(r3, r1)
            int r2 = r6 << 16
            r3 = r19
            int r1 = android.view.View.resolveSizeAndState(r1, r3, r2)
            boolean r2 = r7.mCollapsible
            if (r2 != 0) goto L_0x0275
        L_0x0273:
            r9 = r10
            goto L_0x0296
        L_0x0275:
            int r2 = r17.getChildCount()
            r3 = r10
        L_0x027a:
            if (r3 >= r2) goto L_0x0296
            android.view.View r4 = r7.getChildAt(r3)
            boolean r5 = r7.shouldLayout(r4)
            if (r5 == 0) goto L_0x0293
            int r5 = r4.getMeasuredWidth()
            if (r5 <= 0) goto L_0x0293
            int r4 = r4.getMeasuredHeight()
            if (r4 <= 0) goto L_0x0293
            goto L_0x0273
        L_0x0293:
            int r3 = r3 + 1
            goto L_0x027a
        L_0x0296:
            if (r9 == 0) goto L_0x0299
            r1 = r10
        L_0x0299:
            r7.setMeasuredDimension(r0, r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.p002v7.widget.Toolbar.onMeasure(int, int):void");
    }

    /* access modifiers changed from: protected */
    public void onRestoreInstanceState(Parcelable parcelable) {
        MenuItem findItem;
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        ActionMenuView actionMenuView = this.mMenuView;
        MenuBuilder peekMenu = actionMenuView != null ? actionMenuView.peekMenu() : null;
        int i = savedState.expandedMenuItemId;
        if (!(i == 0 || this.mExpandedMenuPresenter == null || peekMenu == null || (findItem = peekMenu.findItem(i)) == null)) {
            findItem.expandActionView();
        }
        if (savedState.isOverflowOpen) {
            removeCallbacks(this.mShowOverflowMenuRunnable);
            post(this.mShowOverflowMenuRunnable);
        }
    }

    public void onRtlPropertiesChanged(int i) {
        int i2 = Build.VERSION.SDK_INT;
        super.onRtlPropertiesChanged(i);
        ensureContentInsets();
        RtlSpacingHelper rtlSpacingHelper = this.mContentInsets;
        boolean z = true;
        if (i != 1) {
            z = false;
        }
        rtlSpacingHelper.setDirection(z);
    }

    /* access modifiers changed from: protected */
    public Parcelable onSaveInstanceState() {
        MenuItemImpl menuItemImpl;
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        ExpandedActionViewMenuPresenter expandedActionViewMenuPresenter = this.mExpandedMenuPresenter;
        if (!(expandedActionViewMenuPresenter == null || (menuItemImpl = expandedActionViewMenuPresenter.mCurrentExpandedItem) == null)) {
            savedState.expandedMenuItemId = menuItemImpl.getItemId();
        }
        savedState.isOverflowOpen = isOverflowMenuShowing();
        return savedState;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            this.mEatingTouch = false;
        }
        if (!this.mEatingTouch) {
            boolean onTouchEvent = super.onTouchEvent(motionEvent);
            if (actionMasked == 0 && !onTouchEvent) {
                this.mEatingTouch = true;
            }
        }
        if (actionMasked == 1 || actionMasked == 3) {
            this.mEatingTouch = false;
        }
        return true;
    }

    /* access modifiers changed from: package-private */
    public void removeChildrenForExpandedActionView() {
        for (int childCount = getChildCount() - 1; childCount >= 0; childCount--) {
            View childAt = getChildAt(childCount);
            if (!(((LayoutParams) childAt.getLayoutParams()).mViewType == 2 || childAt == this.mMenuView)) {
                removeViewAt(childCount);
                this.mHiddenViews.add(childAt);
            }
        }
    }

    public void setCollapsible(boolean z) {
        this.mCollapsible = z;
        requestLayout();
    }

    public void setContentInsetsRelative(int i, int i2) {
        ensureContentInsets();
        this.mContentInsets.setRelative(i, i2);
    }

    public void setLogo(Drawable drawable) {
        if (drawable != null) {
            if (this.mLogoView == null) {
                this.mLogoView = new AppCompatImageView(getContext(), (AttributeSet) null);
            }
            if (!isChildOrHidden(this.mLogoView)) {
                addSystemView(this.mLogoView, true);
            }
        } else {
            ImageView imageView = this.mLogoView;
            if (imageView != null && isChildOrHidden(imageView)) {
                removeView(this.mLogoView);
                this.mHiddenViews.remove(this.mLogoView);
            }
        }
        ImageView imageView2 = this.mLogoView;
        if (imageView2 != null) {
            imageView2.setImageDrawable(drawable);
        }
    }

    public void setLogoDescription(CharSequence charSequence) {
        if (!TextUtils.isEmpty(charSequence) && this.mLogoView == null) {
            this.mLogoView = new AppCompatImageView(getContext(), (AttributeSet) null);
        }
        ImageView imageView = this.mLogoView;
        if (imageView != null) {
            imageView.setContentDescription(charSequence);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:31:0x0078  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setMenu(android.support.p002v7.view.menu.MenuBuilder r8, android.support.p002v7.widget.ActionMenuPresenter r9) {
        /*
            r7 = this;
            if (r8 != 0) goto L_0x0007
            android.support.v7.widget.ActionMenuView r0 = r7.mMenuView
            if (r0 != 0) goto L_0x0007
            return
        L_0x0007:
            r7.ensureMenuView()
            android.support.v7.widget.ActionMenuView r0 = r7.mMenuView
            android.support.v7.view.menu.MenuBuilder r0 = r0.peekMenu()
            if (r0 != r8) goto L_0x0013
            return
        L_0x0013:
            if (r0 == 0) goto L_0x001f
            android.support.v7.widget.ActionMenuPresenter r1 = r7.mOuterActionMenuPresenter
            r0.removeMenuPresenter(r1)
            android.support.v7.widget.Toolbar$ExpandedActionViewMenuPresenter r1 = r7.mExpandedMenuPresenter
            r0.removeMenuPresenter(r1)
        L_0x001f:
            android.support.v7.widget.Toolbar$ExpandedActionViewMenuPresenter r0 = r7.mExpandedMenuPresenter
            if (r0 != 0) goto L_0x002a
            android.support.v7.widget.Toolbar$ExpandedActionViewMenuPresenter r0 = new android.support.v7.widget.Toolbar$ExpandedActionViewMenuPresenter
            r0.<init>()
            r7.mExpandedMenuPresenter = r0
        L_0x002a:
            r0 = 1
            r9.setExpandedActionViewsExclusive(r0)
            if (r8 == 0) goto L_0x003d
            android.content.Context r0 = r7.mPopupContext
            r8.addMenuPresenter(r9, r0)
            android.support.v7.widget.Toolbar$ExpandedActionViewMenuPresenter r0 = r7.mExpandedMenuPresenter
            android.content.Context r1 = r7.mPopupContext
            r8.addMenuPresenter(r0, r1)
            goto L_0x00a6
        L_0x003d:
            android.content.Context r8 = r7.mPopupContext
            r1 = 0
            r9.initForMenu(r8, r1)
            android.support.v7.widget.Toolbar$ExpandedActionViewMenuPresenter r8 = r7.mExpandedMenuPresenter
            android.support.v7.view.menu.MenuBuilder r2 = r8.mMenu
            if (r2 == 0) goto L_0x0050
            android.support.v7.view.menu.MenuItemImpl r3 = r8.mCurrentExpandedItem
            if (r3 == 0) goto L_0x0050
            r2.collapseItemActionView(r3)
        L_0x0050:
            r8.mMenu = r1
            r9.updateMenuView(r0)
            android.support.v7.widget.Toolbar$ExpandedActionViewMenuPresenter r8 = r7.mExpandedMenuPresenter
            android.support.v7.view.menu.MenuItemImpl r2 = r8.mCurrentExpandedItem
            if (r2 == 0) goto L_0x00a6
            android.support.v7.view.menu.MenuBuilder r2 = r8.mMenu
            r3 = 0
            if (r2 == 0) goto L_0x0075
            int r2 = r2.size()
            r4 = r3
        L_0x0065:
            if (r4 >= r2) goto L_0x0075
            android.support.v7.view.menu.MenuBuilder r5 = r8.mMenu
            android.view.MenuItem r5 = r5.getItem(r4)
            android.support.v7.view.menu.MenuItemImpl r6 = r8.mCurrentExpandedItem
            if (r5 != r6) goto L_0x0072
            goto L_0x0076
        L_0x0072:
            int r4 = r4 + 1
            goto L_0x0065
        L_0x0075:
            r0 = r3
        L_0x0076:
            if (r0 != 0) goto L_0x00a6
            android.support.v7.view.menu.MenuItemImpl r0 = r8.mCurrentExpandedItem
            android.support.v7.widget.Toolbar r2 = android.support.p002v7.widget.Toolbar.this
            android.view.View r2 = r2.mExpandedActionView
            boolean r4 = r2 instanceof android.support.p002v7.view.CollapsibleActionView
            if (r4 == 0) goto L_0x0087
            android.support.v7.view.CollapsibleActionView r2 = (android.support.p002v7.view.CollapsibleActionView) r2
            r2.onActionViewCollapsed()
        L_0x0087:
            android.support.v7.widget.Toolbar r2 = android.support.p002v7.widget.Toolbar.this
            android.view.View r4 = r2.mExpandedActionView
            r2.removeView(r4)
            android.support.v7.widget.Toolbar r2 = android.support.p002v7.widget.Toolbar.this
            android.widget.ImageButton r4 = r2.mCollapseButtonView
            r2.removeView(r4)
            android.support.v7.widget.Toolbar r2 = android.support.p002v7.widget.Toolbar.this
            r2.mExpandedActionView = r1
            r2.addChildrenForExpandedActionView()
            r8.mCurrentExpandedItem = r1
            android.support.v7.widget.Toolbar r8 = android.support.p002v7.widget.Toolbar.this
            r8.requestLayout()
            r0.setActionViewExpanded(r3)
        L_0x00a6:
            android.support.v7.widget.ActionMenuView r8 = r7.mMenuView
            int r0 = r7.mPopupTheme
            r8.setPopupTheme(r0)
            android.support.v7.widget.ActionMenuView r8 = r7.mMenuView
            r8.setPresenter(r9)
            r7.mOuterActionMenuPresenter = r9
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.p002v7.widget.Toolbar.setMenu(android.support.v7.view.menu.MenuBuilder, android.support.v7.widget.ActionMenuPresenter):void");
    }

    public void setMenuCallbacks(MenuPresenter.Callback callback, MenuBuilder.Callback callback2) {
        this.mActionMenuPresenterCallback = callback;
        this.mMenuBuilderCallback = callback2;
        ActionMenuView actionMenuView = this.mMenuView;
        if (actionMenuView != null) {
            actionMenuView.setMenuCallbacks(callback, callback2);
        }
    }

    public void setNavigationContentDescription(int i) {
        setNavigationContentDescription(i != 0 ? getContext().getText(i) : null);
    }

    public void setNavigationIcon(int i) {
        setNavigationIcon(AppCompatResources.getDrawable(getContext(), i));
    }

    public void setNavigationOnClickListener(View.OnClickListener onClickListener) {
        ensureNavButtonView();
        this.mNavButtonView.setOnClickListener(onClickListener);
    }

    public void setOnMenuItemClickListener(OnMenuItemClickListener onMenuItemClickListener) {
        this.mOnMenuItemClickListener = onMenuItemClickListener;
    }

    public void setPopupTheme(int i) {
        if (this.mPopupTheme != i) {
            this.mPopupTheme = i;
            if (i == 0) {
                this.mPopupContext = getContext();
            } else {
                this.mPopupContext = new ContextThemeWrapper(getContext(), i);
            }
        }
    }

    public void setSubtitle(CharSequence charSequence) {
        if (!TextUtils.isEmpty(charSequence)) {
            if (this.mSubtitleTextView == null) {
                Context context = getContext();
                this.mSubtitleTextView = new AppCompatTextView(context);
                this.mSubtitleTextView.setSingleLine();
                this.mSubtitleTextView.setEllipsize(TextUtils.TruncateAt.END);
                int i = this.mSubtitleTextAppearance;
                if (i != 0) {
                    this.mSubtitleTextView.setTextAppearance(context, i);
                }
                int i2 = this.mSubtitleTextColor;
                if (i2 != 0) {
                    this.mSubtitleTextView.setTextColor(i2);
                }
            }
            if (!isChildOrHidden(this.mSubtitleTextView)) {
                addSystemView(this.mSubtitleTextView, true);
            }
        } else {
            TextView textView = this.mSubtitleTextView;
            if (textView != null && isChildOrHidden(textView)) {
                removeView(this.mSubtitleTextView);
                this.mHiddenViews.remove(this.mSubtitleTextView);
            }
        }
        TextView textView2 = this.mSubtitleTextView;
        if (textView2 != null) {
            textView2.setText(charSequence);
        }
        this.mSubtitleText = charSequence;
    }

    public void setSubtitleTextAppearance(Context context, int i) {
        this.mSubtitleTextAppearance = i;
        TextView textView = this.mSubtitleTextView;
        if (textView != null) {
            textView.setTextAppearance(context, i);
        }
    }

    public void setSubtitleTextColor(int i) {
        this.mSubtitleTextColor = i;
        TextView textView = this.mSubtitleTextView;
        if (textView != null) {
            textView.setTextColor(i);
        }
    }

    public void setTitle(int i) {
        setTitle(getContext().getText(i));
    }

    public void setTitleTextAppearance(Context context, int i) {
        this.mTitleTextAppearance = i;
        TextView textView = this.mTitleTextView;
        if (textView != null) {
            textView.setTextAppearance(context, i);
        }
    }

    public void setTitleTextColor(int i) {
        this.mTitleTextColor = i;
        TextView textView = this.mTitleTextView;
        if (textView != null) {
            textView.setTextColor(i);
        }
    }

    public boolean showOverflowMenu() {
        ActionMenuView actionMenuView = this.mMenuView;
        return actionMenuView != null && actionMenuView.showOverflowMenu();
    }

    /* renamed from: android.support.v7.widget.Toolbar$LayoutParams */
    public static class LayoutParams extends ActionBar.LayoutParams {
        int mViewType = 0;

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        public LayoutParams(int i, int i2) {
            super(i, i2);
            this.gravity = 8388627;
        }

        public LayoutParams(LayoutParams layoutParams) {
            super((ActionBar.LayoutParams) layoutParams);
            this.mViewType = layoutParams.mViewType;
        }

        public LayoutParams(ActionBar.LayoutParams layoutParams) {
            super(layoutParams);
        }

        public LayoutParams(ViewGroup.MarginLayoutParams marginLayoutParams) {
            super((ViewGroup.LayoutParams) marginLayoutParams);
            this.leftMargin = marginLayoutParams.leftMargin;
            this.topMargin = marginLayoutParams.topMargin;
            this.rightMargin = marginLayoutParams.rightMargin;
            this.bottomMargin = marginLayoutParams.bottomMargin;
        }

        public LayoutParams(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
        }
    }

    public Toolbar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.toolbarStyle);
    }

    /* access modifiers changed from: protected */
    public LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(-2, -2);
    }

    public void setNavigationContentDescription(CharSequence charSequence) {
        if (!TextUtils.isEmpty(charSequence)) {
            ensureNavButtonView();
        }
        ImageButton imageButton = this.mNavButtonView;
        if (imageButton != null) {
            imageButton.setContentDescription(charSequence);
        }
    }

    public void setNavigationIcon(Drawable drawable) {
        if (drawable != null) {
            ensureNavButtonView();
            if (!isChildOrHidden(this.mNavButtonView)) {
                addSystemView(this.mNavButtonView, true);
            }
        } else {
            ImageButton imageButton = this.mNavButtonView;
            if (imageButton != null && isChildOrHidden(imageButton)) {
                removeView(this.mNavButtonView);
                this.mHiddenViews.remove(this.mNavButtonView);
            }
        }
        ImageButton imageButton2 = this.mNavButtonView;
        if (imageButton2 != null) {
            imageButton2.setImageDrawable(drawable);
        }
    }

    public void setTitle(CharSequence charSequence) {
        if (!TextUtils.isEmpty(charSequence)) {
            if (this.mTitleTextView == null) {
                Context context = getContext();
                this.mTitleTextView = new AppCompatTextView(context);
                this.mTitleTextView.setSingleLine();
                this.mTitleTextView.setEllipsize(TextUtils.TruncateAt.END);
                int i = this.mTitleTextAppearance;
                if (i != 0) {
                    this.mTitleTextView.setTextAppearance(context, i);
                }
                int i2 = this.mTitleTextColor;
                if (i2 != 0) {
                    this.mTitleTextView.setTextColor(i2);
                }
            }
            if (!isChildOrHidden(this.mTitleTextView)) {
                addSystemView(this.mTitleTextView, true);
            }
        } else {
            TextView textView = this.mTitleTextView;
            if (textView != null && isChildOrHidden(textView)) {
                removeView(this.mTitleTextView);
                this.mHiddenViews.remove(this.mTitleTextView);
            }
        }
        TextView textView2 = this.mTitleTextView;
        if (textView2 != null) {
            textView2.setText(charSequence);
        }
        this.mTitleText = charSequence;
    }

    /* renamed from: android.support.v7.widget.Toolbar$SavedState */
    public static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.ClassLoaderCreator<SavedState>() {
            public Object createFromParcel(Parcel parcel, ClassLoader classLoader) {
                return new SavedState(parcel, classLoader);
            }

            public Object[] newArray(int i) {
                return new SavedState[i];
            }

            public Object createFromParcel(Parcel parcel) {
                return new SavedState(parcel, (ClassLoader) null);
            }
        };
        int expandedMenuItemId;
        boolean isOverflowOpen;

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.expandedMenuItemId = parcel.readInt();
            this.isOverflowOpen = parcel.readInt() != 0;
        }

        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.expandedMenuItemId);
            parcel.writeInt(this.isOverflowOpen ? 1 : 0);
        }

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }
    }

    public Toolbar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mGravity = 8388627;
        this.mTempViews = new ArrayList<>();
        this.mHiddenViews = new ArrayList<>();
        this.mTempMargins = new int[2];
        this.mMenuViewItemClickListener = new ActionMenuView.OnMenuItemClickListener() {
        };
        this.mShowOverflowMenuRunnable = new Runnable() {
            public void run() {
                Toolbar.this.showOverflowMenu();
            }
        };
        TintTypedArray obtainStyledAttributes = TintTypedArray.obtainStyledAttributes(getContext(), attributeSet, R$styleable.Toolbar, i, 0);
        this.mTitleTextAppearance = obtainStyledAttributes.getResourceId(27, 0);
        this.mSubtitleTextAppearance = obtainStyledAttributes.getResourceId(18, 0);
        this.mGravity = obtainStyledAttributes.getInteger(R$styleable.Toolbar_android_gravity, this.mGravity);
        this.mButtonGravity = obtainStyledAttributes.getInteger(2, 48);
        int dimensionPixelOffset = obtainStyledAttributes.getDimensionPixelOffset(21, 0);
        dimensionPixelOffset = obtainStyledAttributes.hasValue(26) ? obtainStyledAttributes.getDimensionPixelOffset(26, dimensionPixelOffset) : dimensionPixelOffset;
        this.mTitleMarginBottom = dimensionPixelOffset;
        this.mTitleMarginTop = dimensionPixelOffset;
        this.mTitleMarginEnd = dimensionPixelOffset;
        this.mTitleMarginStart = dimensionPixelOffset;
        int dimensionPixelOffset2 = obtainStyledAttributes.getDimensionPixelOffset(24, -1);
        if (dimensionPixelOffset2 >= 0) {
            this.mTitleMarginStart = dimensionPixelOffset2;
        }
        int dimensionPixelOffset3 = obtainStyledAttributes.getDimensionPixelOffset(23, -1);
        if (dimensionPixelOffset3 >= 0) {
            this.mTitleMarginEnd = dimensionPixelOffset3;
        }
        int dimensionPixelOffset4 = obtainStyledAttributes.getDimensionPixelOffset(25, -1);
        if (dimensionPixelOffset4 >= 0) {
            this.mTitleMarginTop = dimensionPixelOffset4;
        }
        int dimensionPixelOffset5 = obtainStyledAttributes.getDimensionPixelOffset(22, -1);
        if (dimensionPixelOffset5 >= 0) {
            this.mTitleMarginBottom = dimensionPixelOffset5;
        }
        this.mMaxButtonHeight = obtainStyledAttributes.getDimensionPixelSize(13, -1);
        int dimensionPixelOffset6 = obtainStyledAttributes.getDimensionPixelOffset(9, Integer.MIN_VALUE);
        int dimensionPixelOffset7 = obtainStyledAttributes.getDimensionPixelOffset(5, Integer.MIN_VALUE);
        int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(7, 0);
        int dimensionPixelSize2 = obtainStyledAttributes.getDimensionPixelSize(8, 0);
        ensureContentInsets();
        this.mContentInsets.setAbsolute(dimensionPixelSize, dimensionPixelSize2);
        if (!(dimensionPixelOffset6 == Integer.MIN_VALUE && dimensionPixelOffset7 == Integer.MIN_VALUE)) {
            this.mContentInsets.setRelative(dimensionPixelOffset6, dimensionPixelOffset7);
        }
        this.mContentInsetStartWithNavigation = obtainStyledAttributes.getDimensionPixelOffset(10, Integer.MIN_VALUE);
        this.mContentInsetEndWithActions = obtainStyledAttributes.getDimensionPixelOffset(6, Integer.MIN_VALUE);
        this.mCollapseIcon = obtainStyledAttributes.getDrawable(4);
        this.mCollapseDescription = obtainStyledAttributes.getText(3);
        CharSequence text = obtainStyledAttributes.getText(20);
        if (!TextUtils.isEmpty(text)) {
            setTitle(text);
        }
        CharSequence text2 = obtainStyledAttributes.getText(17);
        if (!TextUtils.isEmpty(text2)) {
            setSubtitle(text2);
        }
        this.mPopupContext = getContext();
        setPopupTheme(obtainStyledAttributes.getResourceId(16, 0));
        Drawable drawable = obtainStyledAttributes.getDrawable(15);
        if (drawable != null) {
            setNavigationIcon(drawable);
        }
        CharSequence text3 = obtainStyledAttributes.getText(14);
        if (!TextUtils.isEmpty(text3)) {
            setNavigationContentDescription(text3);
        }
        Drawable drawable2 = obtainStyledAttributes.getDrawable(11);
        if (drawable2 != null) {
            setLogo(drawable2);
        }
        CharSequence text4 = obtainStyledAttributes.getText(12);
        if (!TextUtils.isEmpty(text4)) {
            setLogoDescription(text4);
        }
        if (obtainStyledAttributes.hasValue(28)) {
            setTitleTextColor(obtainStyledAttributes.getColor(28, -1));
        }
        if (obtainStyledAttributes.hasValue(19)) {
            setSubtitleTextColor(obtainStyledAttributes.getColor(19, -1));
        }
        obtainStyledAttributes.recycle();
    }

    public LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new LayoutParams(getContext(), attributeSet);
    }

    /* access modifiers changed from: protected */
    public LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        if (layoutParams instanceof LayoutParams) {
            return new LayoutParams((LayoutParams) layoutParams);
        }
        if (layoutParams instanceof ActionBar.LayoutParams) {
            return new LayoutParams((ActionBar.LayoutParams) layoutParams);
        }
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            return new LayoutParams((ViewGroup.MarginLayoutParams) layoutParams);
        }
        return new LayoutParams(layoutParams);
    }
}
