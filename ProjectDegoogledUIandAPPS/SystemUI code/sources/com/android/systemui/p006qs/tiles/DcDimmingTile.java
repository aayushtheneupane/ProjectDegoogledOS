package com.android.systemui.p006qs.tiles;

import android.content.ContentResolver;
import android.content.Intent;
import android.content.res.Resources;
import android.database.ContentObserver;
import android.hardware.display.DcDimmingManager;
import android.os.Handler;
import android.provider.Settings;
import android.text.TextUtils;
import com.android.systemui.C1776R$drawable;
import com.android.systemui.C1784R$string;
import com.android.systemui.p006qs.QSHost;
import com.android.systemui.p006qs.tileimpl.QSTileImpl;
import com.android.systemui.plugins.p005qs.QSTile;

/* renamed from: com.android.systemui.qs.tiles.DcDimmingTile */
public class DcDimmingTile extends QSTileImpl<QSTile.BooleanState> {
    private DcDimmingManager mDcDimmingManager = ((DcDimmingManager) this.mContext.getSystemService("dc_dim_service"));
    private final QSTile.Icon mIcon = QSTileImpl.ResourceIcon.get(C1776R$drawable.ic_dc_dimming_tile);

    public int getMetricsCategory() {
        return 1999;
    }

    /* access modifiers changed from: protected */
    public void handleSetListening(boolean z) {
    }

    public DcDimmingTile(QSHost qSHost) {
        super(qSHost);
        if (isAvailable()) {
            new SettingsObserver(new Handler()).observe();
        }
    }

    public boolean isAvailable() {
        DcDimmingManager dcDimmingManager = this.mDcDimmingManager;
        return dcDimmingManager != null && dcDimmingManager.isAvailable();
    }

    public QSTile.BooleanState newTileState() {
        return new QSTile.BooleanState();
    }

    /* access modifiers changed from: protected */
    public void handleClick() {
        if (((QSTile.BooleanState) getState()).state != 0) {
            this.mDcDimmingManager.setDcDimming(!((QSTile.BooleanState) this.mState).value);
        }
    }

    /* access modifiers changed from: protected */
    public void handleUpdateState(QSTile.BooleanState booleanState, Object obj) {
        CharSequence charSequence;
        int i;
        int autoMode = this.mDcDimmingManager.getAutoMode();
        boolean isDcDimmingOn = this.mDcDimmingManager.isDcDimmingOn();
        booleanState.value = isDcDimmingOn;
        booleanState.label = this.mContext.getString(C1784R$string.quick_settings_dc_dimming_label);
        booleanState.icon = this.mIcon;
        int i2 = 2;
        if (TextUtils.isEmpty(booleanState.secondaryLabel)) {
            charSequence = booleanState.label;
        } else {
            charSequence = TextUtils.concat(new CharSequence[]{booleanState.label, ", ", booleanState.secondaryLabel});
        }
        booleanState.contentDescription = charSequence;
        if (autoMode == 1) {
            Resources resources = this.mContext.getResources();
            if (isDcDimmingOn) {
                i = C1784R$string.quick_settings_dark_mode_secondary_label_until_sunrise;
            } else {
                i = C1784R$string.quick_settings_dark_mode_secondary_label_on_at_sunset;
            }
            booleanState.secondaryLabel = resources.getString(i);
        } else if (autoMode == 2) {
            booleanState.secondaryLabel = this.mContext.getResources().getString(C1784R$string.quick_settings_dc_brightness_mode);
        } else if (autoMode != 3) {
            booleanState.secondaryLabel = null;
        } else {
            booleanState.secondaryLabel = this.mContext.getResources().getString(C1784R$string.quick_settings_dc_full_auto);
        }
        if (!booleanState.value) {
            i2 = 1;
        }
        booleanState.state = i2;
        booleanState.showRippleEffect = false;
    }

    public Intent getLongClickIntent() {
        return new Intent("android.settings.DC_DIMMING_SETTINGS");
    }

    public CharSequence getTileLabel() {
        return ((QSTile.BooleanState) getState()).label;
    }

    /* renamed from: com.android.systemui.qs.tiles.DcDimmingTile$SettingsObserver */
    private class SettingsObserver extends ContentObserver {
        SettingsObserver(Handler handler) {
            super(handler);
        }

        /* access modifiers changed from: package-private */
        public void observe() {
            ContentResolver contentResolver = DcDimmingTile.this.mContext.getContentResolver();
            contentResolver.registerContentObserver(Settings.System.getUriFor("dc_dimming_auto_mode"), false, this, -1);
            contentResolver.registerContentObserver(Settings.System.getUriFor("dc_dimming_state"), false, this, -1);
        }

        public void onChange(boolean z) {
            DcDimmingTile.this.refreshState();
        }
    }
}
