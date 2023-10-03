package com.android.systemui.statusbar.phone;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Pair;
import android.view.Display;
import android.view.DisplayCutout;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowInsets;
import android.view.accessibility.AccessibilityEvent;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import com.android.systemui.C1773R$bool;
import com.android.systemui.C1775R$dimen;
import com.android.systemui.C1777R$id;
import com.android.systemui.Dependency;
import com.android.systemui.ScreenDecorations;
import com.android.systemui.SysUiServiceProvider;
import com.android.systemui.plugins.DarkIconDispatcher;
import com.android.systemui.statusbar.CommandQueue;
import com.android.systemui.util.leak.RotationUtils;
import java.util.Objects;

public class PhoneStatusBarView extends PanelBar {
    StatusBar mBar;
    private int mBasePaddingBottom;
    private int mBasePaddingLeft;
    private int mBasePaddingRight;
    private int mBasePaddingTop;
    private DarkIconDispatcher.DarkReceiver mBattery;
    private View mCenterIconSpace;
    private final CommandQueue mCommandQueue;
    private int mCutoutSideNudge = 0;
    private View mCutoutSpace;
    private DisplayCutout mDisplayCutout;
    private boolean mHeadsUpVisible;
    private Runnable mHideExpandedRunnable = new Runnable() {
        public void run() {
            PhoneStatusBarView phoneStatusBarView = PhoneStatusBarView.this;
            if (phoneStatusBarView.mPanelFraction == 0.0f) {
                phoneStatusBarView.mBar.makeExpandedInvisible();
            }
        }
    };
    boolean mIsFullyOpenedPanel = false;
    private int mLastOrientation;
    private float mMinFraction;
    private int mRotationOrientation;
    private ScrimController mScrimController;
    private boolean mShowNotchView;
    private ViewGroup mStatusBarContents;
    private int mStatusBarHeight;

    public PhoneStatusBarView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mCommandQueue = (CommandQueue) SysUiServiceProvider.getComponent(context, CommandQueue.class);
    }

    public void setBar(StatusBar statusBar) {
        this.mBar = statusBar;
    }

    public void setScrimController(ScrimController scrimController) {
        this.mScrimController = scrimController;
    }

    public void swiftStatusBarItems(int i, int i2) {
        ViewGroup viewGroup = this.mStatusBarContents;
        if (viewGroup != null) {
            viewGroup.setPaddingRelative(this.mBasePaddingLeft + i, this.mBasePaddingTop + i2, this.mBasePaddingRight + i, this.mBasePaddingBottom - i2);
            invalidate();
        }
    }

    public void onFinishInflate() {
        this.mBattery = (DarkIconDispatcher.DarkReceiver) findViewById(C1777R$id.battery);
        this.mCutoutSpace = findViewById(C1777R$id.cutout_space_view);
        this.mCenterIconSpace = findViewById(C1777R$id.centered_icon_area);
        this.mStatusBarContents = (ViewGroup) findViewById(C1777R$id.status_bar_contents);
        this.mBasePaddingLeft = this.mStatusBarContents.getPaddingStart();
        this.mBasePaddingTop = this.mStatusBarContents.getPaddingTop();
        this.mBasePaddingRight = this.mStatusBarContents.getPaddingEnd();
        this.mBasePaddingBottom = this.mStatusBarContents.getPaddingBottom();
        updateResources();
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        ((DarkIconDispatcher) Dependency.get(DarkIconDispatcher.class)).addDarkReceiver(this.mBattery);
        if (updateOrientationAndCutout(getResources().getConfiguration().orientation)) {
            updateLayoutForCutout();
        }
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        ((DarkIconDispatcher) Dependency.get(DarkIconDispatcher.class)).removeDarkReceiver(this.mBattery);
        this.mDisplayCutout = null;
    }

    /* access modifiers changed from: protected */
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        updateResources();
        if (updateOrientationAndCutout(configuration.orientation)) {
            updateLayoutForCutout();
            requestLayout();
        }
    }

    public WindowInsets onApplyWindowInsets(WindowInsets windowInsets) {
        if (updateOrientationAndCutout(this.mLastOrientation)) {
            updateLayoutForCutout();
            requestLayout();
        }
        return super.onApplyWindowInsets(windowInsets);
    }

    private boolean updateOrientationAndCutout(int i) {
        boolean z = false;
        if (i != Integer.MIN_VALUE) {
            if (this.mLastOrientation != i) {
                this.mLastOrientation = i;
                z = true;
            }
            this.mRotationOrientation = RotationUtils.getExactRotation(this.mContext);
        }
        if (Objects.equals(getRootWindowInsets().getDisplayCutout(), this.mDisplayCutout)) {
            return z;
        }
        this.mDisplayCutout = getRootWindowInsets().getDisplayCutout();
        return true;
    }

    public boolean panelEnabled() {
        return this.mCommandQueue.panelsEnabled();
    }

    public boolean onRequestSendAccessibilityEventInternal(View view, AccessibilityEvent accessibilityEvent) {
        if (!super.onRequestSendAccessibilityEventInternal(view, accessibilityEvent)) {
            return false;
        }
        AccessibilityEvent obtain = AccessibilityEvent.obtain();
        onInitializeAccessibilityEvent(obtain);
        dispatchPopulateAccessibilityEvent(obtain);
        accessibilityEvent.appendRecord(obtain);
        return true;
    }

    public void onPanelPeeked() {
        super.onPanelPeeked();
        this.mBar.makeExpandedVisible(false);
    }

    public void onPanelCollapsed() {
        super.onPanelCollapsed();
        post(this.mHideExpandedRunnable);
        this.mIsFullyOpenedPanel = false;
    }

    public void removePendingHideExpandedRunnables() {
        removeCallbacks(this.mHideExpandedRunnable);
    }

    public void onPanelFullyOpened() {
        super.onPanelFullyOpened();
        if (!this.mIsFullyOpenedPanel) {
            this.mPanel.sendAccessibilityEvent(32);
        }
        this.mIsFullyOpenedPanel = true;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        return this.mBar.interceptTouchEvent(motionEvent) || super.onTouchEvent(motionEvent);
    }

    public void onTrackingStarted() {
        super.onTrackingStarted();
        this.mBar.onTrackingStarted();
        this.mScrimController.onTrackingStarted();
        removePendingHideExpandedRunnables();
    }

    public void onClosingFinished() {
        super.onClosingFinished();
        this.mBar.onClosingFinished();
    }

    public void onTrackingStopped(boolean z) {
        super.onTrackingStopped(z);
        this.mBar.onTrackingStopped(z);
    }

    public void onExpandingFinished() {
        super.onExpandingFinished();
        this.mScrimController.onExpandingFinished();
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        return this.mBar.interceptTouchEvent(motionEvent) || super.onInterceptTouchEvent(motionEvent);
    }

    public void panelScrimMinFractionChanged(float f) {
        if (Float.isNaN(f)) {
            throw new IllegalArgumentException("minFraction cannot be NaN");
        } else if (this.mMinFraction != f) {
            this.mMinFraction = f;
            updateScrimFraction();
        }
    }

    public void panelExpansionChanged(float f, boolean z) {
        super.panelExpansionChanged(f, z);
        updateScrimFraction();
        if ((f == 0.0f || f == 1.0f) && this.mBar.getNavigationBarView() != null) {
            this.mBar.getNavigationBarView().onStatusBarPanelStateChanged();
        }
    }

    private void updateScrimFraction() {
        float f = this.mPanelFraction;
        float f2 = this.mMinFraction;
        if (f2 < 1.0f) {
            f = Math.max((f - f2) / (1.0f - f2), 0.0f);
        }
        this.mScrimController.setPanelExpansion(f);
    }

    public void updateResources() {
        this.mCutoutSideNudge = getResources().getDimensionPixelSize(C1775R$dimen.display_cutout_margin_consumption);
        boolean z = true;
        if (getLayoutDirection() != 1) {
            z = false;
        }
        int dimensionPixelSize = getResources().getDimensionPixelSize(C1775R$dimen.status_bar_padding_top);
        int dimensionPixelSize2 = getResources().getDimensionPixelSize(C1775R$dimen.status_bar_padding_start);
        int dimensionPixelSize3 = getResources().getDimensionPixelSize(C1775R$dimen.status_bar_padding_end);
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        this.mStatusBarHeight = getResources().getDimensionPixelSize(C1775R$dimen.status_bar_height);
        layoutParams.height = this.mStatusBarHeight;
        View findViewById = findViewById(C1777R$id.status_bar_contents);
        int i = z ? dimensionPixelSize3 : dimensionPixelSize2;
        if (z) {
            dimensionPixelSize3 = dimensionPixelSize2;
        }
        findViewById.setPadding(i, dimensionPixelSize, dimensionPixelSize3, 0);
        findViewById(C1777R$id.notification_lights_out).setPadding(0, dimensionPixelSize2, 0, 0);
        setLayoutParams(layoutParams);
    }

    private void updateLayoutForCutout() {
        Pair<Integer, Integer> cornerCutoutMargins = cornerCutoutMargins(this.mDisplayCutout, getDisplay(), this.mRotationOrientation, this.mStatusBarHeight);
        updateCutoutLocation(cornerCutoutMargins);
        updateSafeInsets(cornerCutoutMargins);
    }

    private void updateCutoutLocation(Pair<Integer, Integer> pair) {
        if (this.mCutoutSpace != null) {
            DisplayCutout displayCutout = this.mDisplayCutout;
            if (displayCutout == null || displayCutout.isEmpty() || this.mLastOrientation != 1 || pair != null) {
                this.mCenterIconSpace.setVisibility(0);
                this.mCutoutSpace.setVisibility(8);
                return;
            }
            this.mCenterIconSpace.setVisibility(8);
            this.mShowNotchView = getResources().getBoolean(C1773R$bool.hide_view_behind_notch);
            if (!this.mShowNotchView) {
                this.mCutoutSpace.setVisibility(0);
            } else {
                this.mCutoutSpace.setVisibility(8);
            }
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.mCutoutSpace.getLayoutParams();
            Rect rect = new Rect();
            ScreenDecorations.DisplayCutoutView.boundsFromDirection(this.mDisplayCutout, 48, rect);
            int i = rect.left;
            int i2 = this.mCutoutSideNudge;
            rect.left = i + i2;
            rect.right -= i2;
            layoutParams.width = rect.width();
            layoutParams.height = rect.height();
        }
    }

    private void updateSafeInsets(Pair<Integer, Integer> pair) {
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) getLayoutParams();
        DisplayCutout displayCutout = this.mDisplayCutout;
        if (displayCutout == null || displayCutout.isEmpty() || pair == null) {
            layoutParams.leftMargin = 0;
            layoutParams.rightMargin = 0;
            return;
        }
        layoutParams.leftMargin = ((Integer) pair.first).intValue();
        layoutParams.rightMargin = ((Integer) pair.second).intValue();
        WindowInsets rootWindowInsets = getRootWindowInsets();
        int systemWindowInsetLeft = rootWindowInsets.getSystemWindowInsetLeft();
        int systemWindowInsetRight = rootWindowInsets.getSystemWindowInsetRight();
        if (layoutParams.leftMargin <= systemWindowInsetLeft) {
            layoutParams.leftMargin = 0;
        }
        if (layoutParams.rightMargin <= systemWindowInsetRight) {
            layoutParams.rightMargin = 0;
        }
    }

    public static Pair<Integer, Integer> cornerCutoutMargins(DisplayCutout displayCutout, Display display) {
        return cornerCutoutMargins(displayCutout, display, 0, -1);
    }

    private static Pair<Integer, Integer> cornerCutoutMargins(DisplayCutout displayCutout, Display display, int i, int i2) {
        if (displayCutout == null) {
            return null;
        }
        Point point = new Point();
        display.getRealSize(point);
        if (i != 0) {
            return new Pair<>(Integer.valueOf(displayCutout.getSafeInsetLeft()), Integer.valueOf(displayCutout.getSafeInsetRight()));
        }
        Rect rect = new Rect();
        ScreenDecorations.DisplayCutoutView.boundsFromDirection(displayCutout, 48, rect);
        if (i2 >= 0 && rect.top > i2) {
            return null;
        }
        if (rect.left <= 0) {
            return new Pair<>(Integer.valueOf(rect.right), 0);
        }
        if (rect.right >= point.x) {
            return new Pair<>(0, Integer.valueOf(point.x - rect.left));
        }
        return null;
    }

    public void setHeadsUpVisible(boolean z) {
        this.mHeadsUpVisible = z;
        updateVisibility();
    }

    /* access modifiers changed from: protected */
    public boolean shouldPanelBeVisible() {
        return this.mHeadsUpVisible || super.shouldPanelBeVisible();
    }
}
