package com.android.systemui.statusbar.phone;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewTreeObserver;
import com.android.systemui.Dependency;
import com.android.systemui.bubbles.BubbleController;
import com.android.systemui.statusbar.phone.StatusBarWindowController;
import com.android.systemui.statusbar.policy.ConfigurationController;

public final class StatusBarTouchableRegionManager implements ViewTreeObserver.OnComputeInternalInsetsListener, ConfigurationController.ConfigurationListener {
    private final BubbleController mBubbleController = ((BubbleController) Dependency.get(BubbleController.class));
    private final Context mContext;
    /* access modifiers changed from: private */
    public boolean mForceCollapsedUntilLayout = false;
    private final HeadsUpManagerPhone mHeadsUpManager;
    private boolean mIsStatusBarExpanded = false;
    private boolean mShouldAdjustInsets = false;
    private final StatusBar mStatusBar;
    /* access modifiers changed from: private */
    public int mStatusBarHeight;
    private final StatusBarWindowController mStatusBarWindowController;
    /* access modifiers changed from: private */
    public final View mStatusBarWindowView;

    public StatusBarTouchableRegionManager(Context context, HeadsUpManagerPhone headsUpManagerPhone, StatusBar statusBar, View view) {
        this.mContext = context;
        this.mHeadsUpManager = headsUpManagerPhone;
        this.mStatusBar = statusBar;
        this.mStatusBarWindowView = view;
        this.mStatusBarWindowController = (StatusBarWindowController) Dependency.get(StatusBarWindowController.class);
        initResources();
        this.mBubbleController.setBubbleStateChangeListener(new BubbleController.BubbleStateChangeListener() {
            public final void onHasBubblesChanged(boolean z) {
                StatusBarTouchableRegionManager.this.lambda$new$0$StatusBarTouchableRegionManager(z);
            }
        });
        this.mStatusBarWindowController.setForcePluginOpenListener(new StatusBarWindowController.ForcePluginOpenListener() {
            public final void onChange(boolean z) {
                StatusBarTouchableRegionManager.this.lambda$new$1$StatusBarTouchableRegionManager(z);
            }
        });
        ((ConfigurationController) Dependency.get(ConfigurationController.class)).addCallback(this);
    }

    public /* synthetic */ void lambda$new$0$StatusBarTouchableRegionManager(boolean z) {
        updateTouchableRegion();
    }

    public /* synthetic */ void lambda$new$1$StatusBarTouchableRegionManager(boolean z) {
        updateTouchableRegion();
    }

    public void updateTouchableRegion() {
        View view = this.mStatusBarWindowView;
        boolean z = true;
        boolean z2 = (view == null || view.getRootWindowInsets() == null || this.mStatusBarWindowView.getRootWindowInsets().getDisplayCutout() == null) ? false : true;
        if (!this.mHeadsUpManager.hasPinnedHeadsUp() && !this.mHeadsUpManager.isHeadsUpGoingAway() && !this.mBubbleController.hasBubbles() && !this.mForceCollapsedUntilLayout && !z2 && !this.mStatusBarWindowController.getForcePluginOpen()) {
            z = false;
        }
        if (z != this.mShouldAdjustInsets) {
            if (z) {
                this.mStatusBarWindowView.getViewTreeObserver().addOnComputeInternalInsetsListener(this);
                this.mStatusBarWindowView.requestLayout();
            } else {
                this.mStatusBarWindowView.getViewTreeObserver().removeOnComputeInternalInsetsListener(this);
            }
            this.mShouldAdjustInsets = z;
        }
    }

    public void updateTouchableRegionAfterLayout() {
        this.mForceCollapsedUntilLayout = true;
        this.mStatusBarWindowView.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            public void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
                if (StatusBarTouchableRegionManager.this.mStatusBarWindowView.getHeight() <= StatusBarTouchableRegionManager.this.mStatusBarHeight) {
                    StatusBarTouchableRegionManager.this.mStatusBarWindowView.removeOnLayoutChangeListener(this);
                    boolean unused = StatusBarTouchableRegionManager.this.mForceCollapsedUntilLayout = false;
                    StatusBarTouchableRegionManager.this.updateTouchableRegion();
                }
            }
        });
    }

    public void setIsStatusBarExpanded(boolean z) {
        if (z != this.mIsStatusBarExpanded) {
            this.mIsStatusBarExpanded = z;
            if (z) {
                this.mForceCollapsedUntilLayout = false;
            }
            updateTouchableRegion();
        }
    }

    public void onComputeInternalInsets(ViewTreeObserver.InternalInsetsInfo internalInsetsInfo) {
        if (!this.mIsStatusBarExpanded && !this.mStatusBar.isBouncerShowing()) {
            this.mHeadsUpManager.updateTouchableRegion(internalInsetsInfo);
            Rect touchableRegion = this.mBubbleController.getTouchableRegion();
            if (touchableRegion != null) {
                internalInsetsInfo.touchableRegion.union(touchableRegion);
            }
        }
    }

    public void onConfigChanged(Configuration configuration) {
        initResources();
    }

    public void onDensityOrFontScaleChanged() {
        initResources();
    }

    public void onOverlayChanged() {
        initResources();
    }

    private void initResources() {
        this.mStatusBarHeight = this.mContext.getResources().getDimensionPixelSize(17105434);
    }
}
