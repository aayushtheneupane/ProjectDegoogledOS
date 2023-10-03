package com.android.systemui.p006qs.tiles;

import android.content.Intent;
import android.widget.Switch;
import com.android.systemui.C1776R$drawable;
import com.android.systemui.C1784R$string;
import com.android.systemui.Dependency;
import com.android.systemui.p006qs.QSHost;
import com.android.systemui.p006qs.tileimpl.QSTileImpl;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.plugins.p005qs.QSTile;
import com.android.systemui.statusbar.phone.ManagedProfileController;
import com.android.systemui.statusbar.policy.KeyguardMonitor;

/* renamed from: com.android.systemui.qs.tiles.WorkModeTile */
public class WorkModeTile extends QSTileImpl<QSTile.BooleanState> implements ManagedProfileController.Callback {
    private final QSTile.Icon mIcon = QSTileImpl.ResourceIcon.get(C1776R$drawable.stat_sys_managed_profile_status);
    private final KeyguardMonitor mKeyguard;
    private final KeyguardCallback mKeyguardCallback = new KeyguardCallback();
    private final ManagedProfileController mProfileController;

    public int getMetricsCategory() {
        return 257;
    }

    public WorkModeTile(QSHost qSHost, ManagedProfileController managedProfileController) {
        super(qSHost);
        this.mProfileController = managedProfileController;
        this.mProfileController.observe(getLifecycle(), this);
        this.mKeyguard = (KeyguardMonitor) Dependency.get(KeyguardMonitor.class);
    }

    public QSTile.BooleanState newTileState() {
        return new QSTile.BooleanState();
    }

    public void handleSetListening(boolean z) {
        if (z) {
            this.mKeyguard.addCallback(this.mKeyguardCallback);
        } else {
            this.mKeyguard.removeCallback(this.mKeyguardCallback);
        }
    }

    public Intent getLongClickIntent() {
        return new Intent("android.settings.MANAGED_PROFILE_SETTINGS");
    }

    public void handleClick() {
        if (!this.mKeyguard.isSecure() || !this.mKeyguard.isShowing()) {
            this.mProfileController.setWorkModeEnabled(!((QSTile.BooleanState) this.mState).value);
        } else {
            ((ActivityStarter) Dependency.get(ActivityStarter.class)).postQSRunnableDismissingKeyguard(new Runnable() {
                public final void run() {
                    WorkModeTile.this.lambda$handleClick$0$WorkModeTile();
                }
            });
        }
    }

    public /* synthetic */ void lambda$handleClick$0$WorkModeTile() {
        this.mHost.openPanels();
        this.mProfileController.setWorkModeEnabled(!((QSTile.BooleanState) this.mState).value);
    }

    public boolean isAvailable() {
        return this.mProfileController.hasActiveProfile();
    }

    public void onManagedProfileChanged() {
        refreshState(Boolean.valueOf(this.mProfileController.isWorkModeEnabled()));
    }

    public void onManagedProfileRemoved() {
        this.mHost.removeTile(getTileSpec());
        this.mHost.unmarkTileAsAutoAdded(getTileSpec());
    }

    public CharSequence getTileLabel() {
        return this.mContext.getString(C1784R$string.quick_settings_work_mode_label);
    }

    /* access modifiers changed from: protected */
    public void handleUpdateState(QSTile.BooleanState booleanState, Object obj) {
        if (!isAvailable()) {
            onManagedProfileRemoved();
        }
        if (booleanState.slash == null) {
            booleanState.slash = new QSTile.SlashState();
        }
        if (obj instanceof Boolean) {
            booleanState.value = ((Boolean) obj).booleanValue();
        } else {
            booleanState.value = this.mProfileController.isWorkModeEnabled();
        }
        booleanState.icon = this.mIcon;
        int i = 1;
        if (booleanState.value) {
            booleanState.slash.isSlashed = false;
            booleanState.contentDescription = this.mContext.getString(C1784R$string.accessibility_quick_settings_work_mode_on);
        } else {
            booleanState.slash.isSlashed = true;
            booleanState.contentDescription = this.mContext.getString(C1784R$string.accessibility_quick_settings_work_mode_off);
        }
        booleanState.label = this.mContext.getString(C1784R$string.quick_settings_work_mode_label);
        booleanState.expandedAccessibilityClassName = Switch.class.getName();
        if (booleanState.value) {
            i = 2;
        }
        booleanState.state = i;
    }

    /* access modifiers changed from: protected */
    public String composeChangeAnnouncement() {
        if (((QSTile.BooleanState) this.mState).value) {
            return this.mContext.getString(C1784R$string.accessibility_quick_settings_work_mode_changed_on);
        }
        return this.mContext.getString(C1784R$string.accessibility_quick_settings_work_mode_changed_off);
    }

    /* renamed from: com.android.systemui.qs.tiles.WorkModeTile$KeyguardCallback */
    private final class KeyguardCallback implements KeyguardMonitor.Callback {
        private KeyguardCallback() {
        }

        public void onKeyguardShowingChanged() {
            WorkModeTile.this.refreshState();
        }
    }
}
