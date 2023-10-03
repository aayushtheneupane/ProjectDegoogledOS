package com.android.systemui.p006qs.tiles;

import android.app.ActivityManager;
import android.content.Intent;
import android.widget.Switch;
import androidx.appcompat.R$styleable;
import com.android.systemui.C1784R$string;
import com.android.systemui.p006qs.QSHost;
import com.android.systemui.p006qs.tileimpl.QSTileImpl;
import com.android.systemui.plugins.p005qs.QSTile;
import com.android.systemui.statusbar.policy.FlashlightController;

/* renamed from: com.android.systemui.qs.tiles.FlashlightTile */
public class FlashlightTile extends QSTileImpl<QSTile.BooleanState> implements FlashlightController.FlashlightListener {
    private final FlashlightController mFlashlightController;
    private final QSTile.Icon mIcon = QSTileImpl.ResourceIcon.get(17302794);

    public int getMetricsCategory() {
        return R$styleable.AppCompatTheme_windowFixedHeightMinor;
    }

    public void handleSetListening(boolean z) {
    }

    /* access modifiers changed from: protected */
    public void handleUserSwitch(int i) {
    }

    public FlashlightTile(QSHost qSHost, FlashlightController flashlightController) {
        super(qSHost);
        this.mFlashlightController = flashlightController;
        this.mFlashlightController.observe(getLifecycle(), this);
    }

    /* access modifiers changed from: protected */
    public void handleDestroy() {
        super.handleDestroy();
    }

    public QSTile.BooleanState newTileState() {
        QSTile.BooleanState booleanState = new QSTile.BooleanState();
        booleanState.handlesLongClick = false;
        return booleanState;
    }

    public Intent getLongClickIntent() {
        return new Intent("android.media.action.STILL_IMAGE_CAMERA");
    }

    public boolean isAvailable() {
        return this.mFlashlightController.hasFlashlight();
    }

    /* access modifiers changed from: protected */
    public void handleClick() {
        if (!ActivityManager.isUserAMonkey()) {
            boolean z = !((QSTile.BooleanState) this.mState).value;
            refreshState(Boolean.valueOf(z));
            this.mFlashlightController.setFlashlight(z);
        }
    }

    public CharSequence getTileLabel() {
        return this.mContext.getString(C1784R$string.quick_settings_flashlight_label);
    }

    /* access modifiers changed from: protected */
    public void handleLongClick() {
        handleClick();
    }

    /* access modifiers changed from: protected */
    public void handleUpdateState(QSTile.BooleanState booleanState, Object obj) {
        if (booleanState.slash == null) {
            booleanState.slash = new QSTile.SlashState();
        }
        booleanState.label = this.mHost.getContext().getString(C1784R$string.quick_settings_flashlight_label);
        int i = 1;
        if (!this.mFlashlightController.isAvailable()) {
            booleanState.icon = this.mIcon;
            booleanState.slash.isSlashed = true;
            booleanState.contentDescription = this.mContext.getString(C1784R$string.accessibility_quick_settings_flashlight_unavailable);
            booleanState.state = 0;
            return;
        }
        if (obj instanceof Boolean) {
            boolean booleanValue = ((Boolean) obj).booleanValue();
            if (booleanValue != booleanState.value) {
                booleanState.value = booleanValue;
            } else {
                return;
            }
        } else {
            booleanState.value = this.mFlashlightController.isEnabled();
        }
        booleanState.icon = this.mIcon;
        booleanState.slash.isSlashed = !booleanState.value;
        booleanState.contentDescription = this.mContext.getString(C1784R$string.quick_settings_flashlight_label);
        booleanState.expandedAccessibilityClassName = Switch.class.getName();
        if (booleanState.value) {
            i = 2;
        }
        booleanState.state = i;
    }

    /* access modifiers changed from: protected */
    public String composeChangeAnnouncement() {
        if (((QSTile.BooleanState) this.mState).value) {
            return this.mContext.getString(C1784R$string.accessibility_quick_settings_flashlight_changed_on);
        }
        return this.mContext.getString(C1784R$string.accessibility_quick_settings_flashlight_changed_off);
    }

    public void onFlashlightChanged(boolean z) {
        refreshState(Boolean.valueOf(z));
    }

    public void onFlashlightError() {
        refreshState(false);
    }

    public void onFlashlightAvailabilityChanged(boolean z) {
        refreshState();
    }
}
