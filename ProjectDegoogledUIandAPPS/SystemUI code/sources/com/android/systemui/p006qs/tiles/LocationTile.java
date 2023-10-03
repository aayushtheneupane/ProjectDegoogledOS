package com.android.systemui.p006qs.tiles;

import android.content.Intent;
import android.widget.Switch;
import androidx.appcompat.R$styleable;
import androidx.lifecycle.LifecycleOwner;
import com.android.systemui.C1776R$drawable;
import com.android.systemui.C1784R$string;
import com.android.systemui.p006qs.QSHost;
import com.android.systemui.p006qs.tileimpl.QSTileImpl;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.plugins.p005qs.QSTile;
import com.android.systemui.statusbar.policy.KeyguardMonitor;
import com.android.systemui.statusbar.policy.LocationController;

/* renamed from: com.android.systemui.qs.tiles.LocationTile */
public class LocationTile extends QSTileImpl<QSTile.BooleanState> {
    private final ActivityStarter mActivityStarter;
    private final Callback mCallback = new Callback();
    private final LocationController mController;
    private final QSTile.Icon mIcon = QSTileImpl.ResourceIcon.get(C1776R$drawable.ic_location);
    private final KeyguardMonitor mKeyguard;

    public int getMetricsCategory() {
        return R$styleable.AppCompatTheme_windowMinWidthMajor;
    }

    public void handleSetListening(boolean z) {
    }

    public LocationTile(QSHost qSHost, LocationController locationController, KeyguardMonitor keyguardMonitor, ActivityStarter activityStarter) {
        super(qSHost);
        this.mController = locationController;
        this.mKeyguard = keyguardMonitor;
        this.mActivityStarter = activityStarter;
        this.mController.observe((LifecycleOwner) this, this.mCallback);
        this.mKeyguard.observe((LifecycleOwner) this, this.mCallback);
    }

    public QSTile.BooleanState newTileState() {
        return new QSTile.BooleanState();
    }

    public Intent getLongClickIntent() {
        return new Intent("android.settings.LOCATION_SOURCE_SETTINGS");
    }

    /* access modifiers changed from: protected */
    public void handleClick() {
        if (!this.mKeyguard.isSecure() || !this.mKeyguard.isShowing()) {
            this.mController.setLocationEnabled(!((QSTile.BooleanState) this.mState).value);
            return;
        }
        this.mActivityStarter.postQSRunnableDismissingKeyguard(new Runnable() {
            public final void run() {
                LocationTile.this.lambda$handleClick$0$LocationTile();
            }
        });
    }

    public /* synthetic */ void lambda$handleClick$0$LocationTile() {
        boolean z = ((QSTile.BooleanState) this.mState).value;
        this.mHost.openPanels();
        this.mController.setLocationEnabled(!z);
    }

    public CharSequence getTileLabel() {
        return this.mContext.getString(C1784R$string.quick_settings_location_label);
    }

    /* access modifiers changed from: protected */
    public void handleUpdateState(QSTile.BooleanState booleanState, Object obj) {
        if (booleanState.slash == null) {
            booleanState.slash = new QSTile.SlashState();
        }
        boolean isLocationEnabled = this.mController.isLocationEnabled();
        booleanState.value = isLocationEnabled;
        checkIfRestrictionEnforcedByAdminOnly(booleanState, "no_share_location");
        if (!booleanState.disabledByPolicy) {
            checkIfRestrictionEnforcedByAdminOnly(booleanState, "no_config_location");
        }
        booleanState.icon = this.mIcon;
        int i = 1;
        booleanState.slash.isSlashed = !booleanState.value;
        if (isLocationEnabled) {
            booleanState.label = this.mContext.getString(C1784R$string.quick_settings_location_label);
            booleanState.contentDescription = this.mContext.getString(C1784R$string.accessibility_quick_settings_location_on);
        } else {
            booleanState.label = this.mContext.getString(C1784R$string.quick_settings_location_label);
            booleanState.contentDescription = this.mContext.getString(C1784R$string.accessibility_quick_settings_location_off);
        }
        if (booleanState.value) {
            i = 2;
        }
        booleanState.state = i;
        booleanState.expandedAccessibilityClassName = Switch.class.getName();
    }

    /* access modifiers changed from: protected */
    public String composeChangeAnnouncement() {
        if (((QSTile.BooleanState) this.mState).value) {
            return this.mContext.getString(C1784R$string.accessibility_quick_settings_location_changed_on);
        }
        return this.mContext.getString(C1784R$string.accessibility_quick_settings_location_changed_off);
    }

    /* renamed from: com.android.systemui.qs.tiles.LocationTile$Callback */
    private final class Callback implements LocationController.LocationChangeCallback, KeyguardMonitor.Callback {
        private Callback() {
        }

        public void onLocationSettingsChanged(boolean z) {
            LocationTile.this.refreshState();
        }

        public void onKeyguardShowingChanged() {
            LocationTile.this.refreshState();
        }
    }
}
