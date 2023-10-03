package com.android.systemui.p006qs.tiles;

import android.content.Intent;
import android.widget.Switch;
import com.android.systemui.C1784R$string;
import com.android.systemui.Dependency;
import com.android.systemui.p006qs.QSHost;
import com.android.systemui.p006qs.SecureSetting;
import com.android.systemui.p006qs.tileimpl.QSTileImpl;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.plugins.p005qs.QSTile;
import com.android.systemui.statusbar.policy.BatteryController;
import com.android.systemui.statusbar.policy.KeyguardMonitor;

/* renamed from: com.android.systemui.qs.tiles.BatterySaverTile */
public class BatterySaverTile extends QSTileImpl<QSTile.BooleanState> implements BatteryController.BatteryStateChangeCallback {
    private final BatteryController mBatteryController;
    private boolean mCharging;
    private QSTile.Icon mIcon = QSTileImpl.ResourceIcon.get(17302791);
    private final KeyguardMonitor mKeyguard;
    private final KeyguardCallback mKeyguardCallback = new KeyguardCallback();
    private int mLevel;
    private boolean mPluggedIn;
    private boolean mPowerSave;
    private final SecureSetting mSetting;

    public int getMetricsCategory() {
        return 261;
    }

    public BatterySaverTile(QSHost qSHost, BatteryController batteryController) {
        super(qSHost);
        this.mBatteryController = batteryController;
        this.mBatteryController.observe(getLifecycle(), this);
        this.mKeyguard = (KeyguardMonitor) Dependency.get(KeyguardMonitor.class);
        this.mSetting = new SecureSetting(this.mContext, this.mHandler, "low_power_warning_acknowledged") {
            /* access modifiers changed from: protected */
            public void handleValueChanged(int i, boolean z) {
                BatterySaverTile.this.handleRefreshState((Object) null);
            }
        };
    }

    public QSTile.BooleanState newTileState() {
        return new QSTile.BooleanState();
    }

    /* access modifiers changed from: protected */
    public void handleDestroy() {
        super.handleDestroy();
        this.mSetting.setListening(false);
    }

    public void handleSetListening(boolean z) {
        this.mSetting.setListening(z);
        if (z) {
            this.mKeyguard.addCallback(this.mKeyguardCallback);
        } else {
            this.mKeyguard.removeCallback(this.mKeyguardCallback);
        }
    }

    public Intent getLongClickIntent() {
        return new Intent("android.intent.action.POWER_USAGE_SUMMARY");
    }

    /* access modifiers changed from: protected */
    public void handleClick() {
        if (((QSTile.BooleanState) getState()).state != 0) {
            if (!this.mKeyguard.isSecure() || !this.mKeyguard.isShowing()) {
                this.mBatteryController.setPowerSaveMode(!this.mPowerSave);
            } else {
                ((ActivityStarter) Dependency.get(ActivityStarter.class)).postQSRunnableDismissingKeyguard(new Runnable() {
                    public final void run() {
                        BatterySaverTile.this.lambda$handleClick$0$BatterySaverTile();
                    }
                });
            }
        }
    }

    public /* synthetic */ void lambda$handleClick$0$BatterySaverTile() {
        this.mHost.openPanels();
        this.mBatteryController.setPowerSaveMode(!this.mPowerSave);
    }

    public CharSequence getTileLabel() {
        return this.mContext.getString(C1784R$string.battery_detail_switch_title);
    }

    /* access modifiers changed from: protected */
    public void handleUpdateState(QSTile.BooleanState booleanState, Object obj) {
        int i;
        boolean z = true;
        if (this.mPluggedIn) {
            i = 0;
        } else {
            i = this.mPowerSave ? 2 : 1;
        }
        booleanState.state = i;
        booleanState.icon = this.mIcon;
        booleanState.label = this.mContext.getString(C1784R$string.battery_detail_switch_title);
        booleanState.contentDescription = booleanState.label;
        booleanState.value = this.mPowerSave;
        booleanState.expandedAccessibilityClassName = Switch.class.getName();
        if (this.mSetting.getValue() != 0) {
            z = false;
        }
        booleanState.showRippleEffect = z;
    }

    public void onBatteryLevelChanged(int i, boolean z, boolean z2) {
        this.mLevel = i;
        this.mPluggedIn = z;
        this.mCharging = z2;
        refreshState(Integer.valueOf(i));
    }

    public void onPowerSaveChanged(boolean z) {
        this.mPowerSave = z;
        refreshState((Object) null);
    }

    /* renamed from: com.android.systemui.qs.tiles.BatterySaverTile$KeyguardCallback */
    private final class KeyguardCallback implements KeyguardMonitor.Callback {
        private KeyguardCallback() {
        }

        public void onKeyguardShowingChanged() {
            BatterySaverTile.this.refreshState();
        }
    }
}
