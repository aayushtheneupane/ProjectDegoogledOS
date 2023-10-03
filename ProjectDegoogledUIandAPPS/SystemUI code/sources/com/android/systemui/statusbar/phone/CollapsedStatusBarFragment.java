package com.android.systemui.statusbar.phone;

import android.app.Fragment;
import android.content.ContentResolver;
import android.database.ContentObserver;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.ImageSwitcher;
import android.widget.LinearLayout;
import com.android.systemui.BatteryMeterView;
import com.android.systemui.C1773R$bool;
import com.android.systemui.C1775R$dimen;
import com.android.systemui.C1777R$id;
import com.android.systemui.C1779R$layout;
import com.android.systemui.Dependency;
import com.android.systemui.Interpolators;
import com.android.systemui.SysUiServiceProvider;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.statusbar.CommandQueue;
import com.android.systemui.statusbar.phone.StatusBarIconController;
import com.android.systemui.statusbar.policy.Clock;
import com.android.systemui.statusbar.policy.EncryptionHelper;
import com.android.systemui.statusbar.policy.KeyguardMonitor;
import com.android.systemui.statusbar.policy.NetworkController;

public class CollapsedStatusBarFragment extends Fragment implements CommandQueue.Callbacks, StatusBarStateController.StateListener {
    private View[] mBatteryBars = new View[2];
    private BatteryMeterView mBatteryMeterView;
    private BatteryMeterView.BatteryMeterViewCallbacks mBatteryMeterViewCallback = new BatteryMeterView.BatteryMeterViewCallbacks() {
        public void onHiddenBattery(boolean z) {
            CollapsedStatusBarFragment.this.mStatusIcons.setPadding(CollapsedStatusBarFragment.this.mStatusIcons.getPaddingLeft(), CollapsedStatusBarFragment.this.mStatusIcons.getPaddingTop(), z ? 0 : CollapsedStatusBarFragment.this.mSignalClusterEndPadding, CollapsedStatusBarFragment.this.mStatusIcons.getPaddingBottom());
        }
    };
    private LinearLayout mCenterClockLayout;
    private View mCenteredIconArea;
    private int mClockStyle;
    private View mClockView;
    /* access modifiers changed from: private */
    public CommandQueue mCommandQueue;
    /* access modifiers changed from: private */
    public ContentResolver mContentResolver;
    private View mCustomCarrierLabel;
    private LinearLayout mCustomIconArea;
    private StatusBarIconController.DarkIconManager mDarkIconManager;
    private int mDisabled1;
    private final Handler mHandler = new Handler();
    private boolean mHasCarrierLabel;
    private View mHavocLogoRight;
    private KeyguardMonitor mKeyguardMonitor;
    private NetworkController mNetworkController;
    private View mNotificationIconAreaInner;
    private View mOperatorNameFrame;
    private View mRightClock;
    private SettingsObserver mSettingsObserver = new SettingsObserver(this.mHandler);
    private boolean mShowClock = true;
    private NetworkController.SignalCallback mSignalCallback = new NetworkController.SignalCallback() {
        public void setIsAirplaneMode(NetworkController.IconState iconState) {
            CollapsedStatusBarFragment.this.mCommandQueue.recomputeDisableFlags(CollapsedStatusBarFragment.this.getContext().getDisplayId(), true);
        }
    };
    /* access modifiers changed from: private */
    public int mSignalClusterEndPadding = 0;
    private PhoneStatusBarView mStatusBar;
    private StatusBar mStatusBarComponent;
    private StatusBarStateController mStatusBarStateController;
    /* access modifiers changed from: private */
    public StatusIconContainer mStatusIcons;
    private LinearLayout mSystemIconArea;
    private boolean mTickerEnabled;
    private View mTickerViewFromStub;

    public void onStateChanged(int i) {
    }

    private class SettingsObserver extends ContentObserver {
        SettingsObserver(Handler handler) {
            super(handler);
        }

        /* access modifiers changed from: package-private */
        public void observe() {
            CollapsedStatusBarFragment.this.mContentResolver.registerContentObserver(Settings.System.getUriFor("status_bar_clock"), false, this, -1);
            CollapsedStatusBarFragment.this.mContentResolver.registerContentObserver(Settings.System.getUriFor("statusbar_clock_style"), false, this, -1);
            CollapsedStatusBarFragment.this.mContentResolver.registerContentObserver(Settings.System.getUriFor("carrier_label_enabled"), false, this, -1);
            CollapsedStatusBarFragment.this.mContentResolver.registerContentObserver(Settings.System.getUriFor("carrier_label_location"), false, this, -1);
            CollapsedStatusBarFragment.this.mContentResolver.registerContentObserver(Settings.System.getUriFor("status_bar_show_ticker"), false, this, -1);
        }

        public void onChange(boolean z) {
            CollapsedStatusBarFragment.this.updateSettings(true);
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mContentResolver = getContext().getContentResolver();
        this.mKeyguardMonitor = (KeyguardMonitor) Dependency.get(KeyguardMonitor.class);
        this.mNetworkController = (NetworkController) Dependency.get(NetworkController.class);
        this.mStatusBarStateController = (StatusBarStateController) Dependency.get(StatusBarStateController.class);
        this.mStatusBarComponent = (StatusBar) SysUiServiceProvider.getComponent(getContext(), StatusBar.class);
        this.mCommandQueue = (CommandQueue) SysUiServiceProvider.getComponent(getContext(), CommandQueue.class);
        this.mSettingsObserver.observe();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(C1779R$layout.status_bar, viewGroup, false);
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.mStatusBar = (PhoneStatusBarView) view;
        if (bundle != null && bundle.containsKey("panel_state")) {
            this.mStatusBar.restoreHierarchyState(bundle.getSparseParcelableArray("panel_state"));
        }
        this.mDarkIconManager = new StatusBarIconController.DarkIconManager((LinearLayout) view.findViewById(C1777R$id.statusIcons));
        this.mDarkIconManager.setShouldLog(true);
        ((StatusBarIconController) Dependency.get(StatusBarIconController.class)).addIconGroup(this.mDarkIconManager);
        this.mSystemIconArea = (LinearLayout) this.mStatusBar.findViewById(C1777R$id.system_icon_area);
        this.mCustomIconArea = (LinearLayout) this.mStatusBar.findViewById(C1777R$id.left_icon_area);
        this.mSignalClusterEndPadding = getResources().getDimensionPixelSize(C1775R$dimen.signal_cluster_battery_padding);
        this.mStatusIcons = (StatusIconContainer) this.mStatusBar.findViewById(C1777R$id.statusIcons);
        int i = Settings.System.getInt(getContext().getContentResolver(), "status_bar_battery_style", 0);
        StatusIconContainer statusIconContainer = this.mStatusIcons;
        statusIconContainer.setPadding(statusIconContainer.getPaddingLeft(), this.mStatusIcons.getPaddingTop(), i == 5 ? 0 : this.mSignalClusterEndPadding, this.mStatusIcons.getPaddingBottom());
        this.mBatteryMeterView = (BatteryMeterView) this.mStatusBar.findViewById(C1777R$id.battery);
        this.mBatteryMeterView.addCallback(this.mBatteryMeterViewCallback);
        this.mClockView = this.mStatusBar.findViewById(C1777R$id.clock);
        this.mCenterClockLayout = (LinearLayout) this.mStatusBar.findViewById(C1777R$id.center_clock_layout);
        this.mRightClock = this.mStatusBar.findViewById(C1777R$id.right_clock);
        this.mCustomCarrierLabel = this.mStatusBar.findViewById(C1777R$id.statusbar_carrier_text);
        this.mHavocLogoRight = this.mStatusBar.findViewById(C1777R$id.havoc_logo_right);
        this.mBatteryBars[0] = this.mStatusBar.findViewById(C1777R$id.battery_bar);
        this.mBatteryBars[1] = this.mStatusBar.findViewById(C1777R$id.battery_bar_1);
        showSystemIconArea(false);
        initEmergencyCryptkeeperText();
        animateHide(this.mClockView, false, false);
        initOperatorName();
        this.mSettingsObserver.observe();
        updateSettings(false);
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        SparseArray sparseArray = new SparseArray();
        this.mStatusBar.saveHierarchyState(sparseArray);
        bundle.putSparseParcelableArray("panel_state", sparseArray);
    }

    public void onResume() {
        super.onResume();
        this.mCommandQueue.addCallback((CommandQueue.Callbacks) this);
        this.mStatusBarStateController.addCallback(this);
    }

    public void onPause() {
        super.onPause();
        this.mCommandQueue.removeCallback((CommandQueue.Callbacks) this);
        this.mStatusBarStateController.removeCallback(this);
    }

    public void onDestroyView() {
        super.onDestroyView();
        ((StatusBarIconController) Dependency.get(StatusBarIconController.class)).removeIconGroup(this.mDarkIconManager);
        if (this.mNetworkController.hasEmergencyCryptKeeperText()) {
            this.mNetworkController.removeCallback(this.mSignalCallback);
        }
        BatteryMeterView batteryMeterView = this.mBatteryMeterView;
        if (batteryMeterView != null) {
            batteryMeterView.removeCallback(this.mBatteryMeterViewCallback);
        }
    }

    public void initNotificationIconArea(NotificationIconAreaController notificationIconAreaController) {
        ViewGroup viewGroup = (ViewGroup) this.mStatusBar.findViewById(C1777R$id.notification_icon_area);
        this.mNotificationIconAreaInner = notificationIconAreaController.getNotificationInnerAreaView();
        if (this.mNotificationIconAreaInner.getParent() != null) {
            ((ViewGroup) this.mNotificationIconAreaInner.getParent()).removeView(this.mNotificationIconAreaInner);
        }
        viewGroup.addView(this.mNotificationIconAreaInner);
        ViewGroup viewGroup2 = (ViewGroup) this.mStatusBar.findViewById(C1777R$id.centered_icon_area);
        this.mCenteredIconArea = notificationIconAreaController.getCenteredNotificationAreaView();
        if (this.mCenteredIconArea.getParent() != null) {
            ((ViewGroup) this.mCenteredIconArea.getParent()).removeView(this.mCenteredIconArea);
        }
        viewGroup2.addView(this.mCenteredIconArea);
        showNotificationIconArea(false);
    }

    public void disable(int i, int i2, int i3, boolean z) {
        if (i == getContext().getDisplayId()) {
            int adjustDisableFlags = adjustDisableFlags(i2);
            int i4 = this.mDisabled1 ^ adjustDisableFlags;
            this.mDisabled1 = adjustDisableFlags;
            if ((i4 & 1048576) != 0) {
                if ((1048576 & adjustDisableFlags) != 0) {
                    hideSystemIconArea(z);
                    hideOperatorName(z);
                } else {
                    showSystemIconArea(z);
                    showOperatorName(z);
                }
            }
            if ((i4 & 131072) == 0) {
                return;
            }
            if ((adjustDisableFlags & 131072) != 0) {
                hideNotificationIconArea(z);
                hideCarrierName(z);
                animateHide(this.mClockView, z, this.mClockStyle == 0);
                return;
            }
            showNotificationIconArea(z);
            updateClockStyle(z);
            showCarrierName(z);
        }
    }

    /* access modifiers changed from: protected */
    public int adjustDisableFlags(int i) {
        boolean headsUpShouldBeVisible = this.mStatusBarComponent.headsUpShouldBeVisible();
        if (headsUpShouldBeVisible) {
            i |= 8388608;
        }
        if (!this.mKeyguardMonitor.isLaunchTransitionFadingAway() && !this.mKeyguardMonitor.isKeyguardFadingAway() && shouldHideNotificationIcons() && (this.mStatusBarStateController.getState() != 1 || !headsUpShouldBeVisible)) {
            i = i | 131072 | 1048576 | 8388608;
        }
        NetworkController networkController = this.mNetworkController;
        if (networkController != null && EncryptionHelper.IS_DATA_ENCRYPTED) {
            if (networkController.hasEmergencyCryptKeeperText()) {
                i |= 131072;
            }
            if (!this.mNetworkController.isRadioOn()) {
                i |= 1048576;
            }
        }
        return (!this.mStatusBarStateController.isDozing() || !this.mStatusBarComponent.getPanel().hasCustomClock()) ? i : i | 9437184;
    }

    private boolean shouldHideNotificationIcons() {
        if ((this.mStatusBar.isClosed() || !this.mStatusBarComponent.hideStatusBarIconsWhenExpanded()) && !this.mStatusBarComponent.hideStatusBarIconsForBouncer()) {
            return false;
        }
        return true;
    }

    public void hideSystemIconArea(boolean z) {
        animateHide(this.mCenterClockLayout, z, true);
        if (this.mClockStyle == 2) {
            animateHide(this.mRightClock, z, true);
        }
        animateHide(this.mSystemIconArea, z, true);
        animateHide(this.mHavocLogoRight, z, true);
        for (View animateHide : this.mBatteryBars) {
            animateHide(animateHide, z, true);
        }
    }

    public void showSystemIconArea(boolean z) {
        animateShow(this.mCenterClockLayout, z);
        if (this.mClockStyle == 2) {
            animateShow(this.mRightClock, z);
        }
        animateShow(this.mSystemIconArea, z);
        animateShow(this.mHavocLogoRight, z);
        for (View animateShow : this.mBatteryBars) {
            animateShow(animateShow, z);
        }
    }

    public void hideNotificationIconArea(boolean z) {
        animateHide(this.mNotificationIconAreaInner, z, true);
        animateHide(this.mCenteredIconArea, z, true);
        animateHide(this.mCenterClockLayout, z, true);
        animateHide(this.mCustomIconArea, z, true);
    }

    public void showNotificationIconArea(boolean z) {
        animateShow(this.mNotificationIconAreaInner, z);
        animateShow(this.mCenteredIconArea, z);
        animateShow(this.mCenterClockLayout, z);
        animateShow(this.mCustomIconArea, z);
    }

    public void hideOperatorName(boolean z) {
        View view = this.mOperatorNameFrame;
        if (view != null) {
            animateHide(view, z, true);
        }
    }

    public void showOperatorName(boolean z) {
        View view = this.mOperatorNameFrame;
        if (view != null) {
            animateShow(view, z);
        }
    }

    public void hideCarrierName(boolean z) {
        View view = this.mCustomCarrierLabel;
        if (view != null) {
            animateHide(view, z, this.mHasCarrierLabel);
        }
    }

    public void showCarrierName(boolean z) {
        if (this.mCustomCarrierLabel != null) {
            setCarrierLabel(z);
        }
    }

    private void animateHide(View view, boolean z, boolean z2) {
        view.animate().cancel();
        if (!z) {
            view.setAlpha(0.0f);
            view.setVisibility(z2 ? 4 : 8);
            return;
        }
        view.animate().alpha(0.0f).setDuration(160).setStartDelay(0).setInterpolator(Interpolators.ALPHA_OUT).withEndAction(new Runnable(view, z2) {
            private final /* synthetic */ View f$0;
            private final /* synthetic */ boolean f$1;

            {
                this.f$0 = r1;
                this.f$1 = r2;
            }

            public final void run() {
                CollapsedStatusBarFragment.lambda$animateHide$0(this.f$0, this.f$1);
            }
        });
    }

    static /* synthetic */ void lambda$animateHide$0(View view, boolean z) {
        view.setVisibility(z ? 4 : 8);
    }

    private void animateShow(View view, boolean z) {
        if (!(view instanceof Clock) || ((Clock) view).isClockVisible()) {
            view.animate().cancel();
            view.setVisibility(0);
            if (!z) {
                view.setAlpha(1.0f);
                return;
            }
            view.animate().alpha(1.0f).setDuration(320).setInterpolator(Interpolators.ALPHA_IN).setStartDelay(50).withEndAction((Runnable) null);
            if (this.mKeyguardMonitor.isKeyguardFadingAway()) {
                view.animate().setDuration(this.mKeyguardMonitor.getKeyguardFadingAwayDuration()).setInterpolator(Interpolators.LINEAR_OUT_SLOW_IN).setStartDelay(this.mKeyguardMonitor.getKeyguardFadingAwayDelay()).start();
            }
        }
    }

    private void initEmergencyCryptkeeperText() {
        View findViewById = this.mStatusBar.findViewById(C1777R$id.emergency_cryptkeeper_text);
        if (this.mNetworkController.hasEmergencyCryptKeeperText()) {
            if (findViewById != null) {
                ((ViewStub) findViewById).inflate();
            }
            this.mNetworkController.addCallback(this.mSignalCallback);
        } else if (findViewById != null) {
            ((ViewGroup) findViewById.getParent()).removeView(findViewById);
        }
    }

    private void initOperatorName() {
        if (getResources().getBoolean(C1773R$bool.config_showOperatorNameInStatusBar)) {
            this.mOperatorNameFrame = ((ViewStub) this.mStatusBar.findViewById(C1777R$id.operator_name)).inflate();
        }
    }

    public void onDozingChanged(boolean z) {
        int displayId = getContext().getDisplayId();
        int i = this.mDisabled1;
        disable(displayId, i, i, false);
    }

    public void updateSettings(boolean z) {
        boolean z2 = true;
        this.mHasCarrierLabel = (Settings.System.getIntForUser(getContext().getContentResolver(), "carrier_label_enabled", 1, -2) == 1) && Settings.System.getIntForUser(getContext().getContentResolver(), "carrier_label_location", 0, -2) != 0;
        this.mShowClock = Settings.System.getIntForUser(this.mContentResolver, "status_bar_clock", 1, -2) == 1;
        if (!this.mShowClock) {
            this.mClockStyle = 1;
        } else {
            this.mClockStyle = Settings.System.getIntForUser(this.mContentResolver, "statusbar_clock_style", 0, -2);
        }
        if (Settings.System.getIntForUser(this.mContentResolver, "status_bar_show_ticker", 0, -2) != 1) {
            z2 = false;
        }
        this.mTickerEnabled = z2;
        updateClockStyle(z);
        setCarrierLabel(z);
        initTickerView();
    }

    private void updateClockStyle(boolean z) {
        int i = this.mClockStyle;
        if (i == 1 || i == 2) {
            animateHide(this.mClockView, z, false);
        } else if (((Clock) this.mClockView).isClockVisible()) {
            animateShow(this.mClockView, z);
        }
    }

    private void setCarrierLabel(boolean z) {
        if (this.mHasCarrierLabel) {
            animateShow(this.mCustomCarrierLabel, z);
        } else {
            animateHide(this.mCustomCarrierLabel, z, false);
        }
    }

    private void initTickerView() {
        if (this.mTickerEnabled) {
            View findViewById = this.mStatusBar.findViewById(C1777R$id.ticker_stub);
            if (this.mTickerViewFromStub == null && findViewById != null) {
                this.mTickerViewFromStub = ((ViewStub) findViewById).inflate();
            }
            this.mStatusBarComponent.createTicker(getContext(), this.mStatusBar, (TickerView) this.mStatusBar.findViewById(C1777R$id.tickerText), (ImageSwitcher) this.mStatusBar.findViewById(C1777R$id.tickerIcon), this.mTickerViewFromStub);
            return;
        }
        this.mStatusBarComponent.disableTicker();
    }
}
