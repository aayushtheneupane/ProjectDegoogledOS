package com.android.systemui.p006qs.tiles;

import android.content.Intent;
import com.android.internal.custom.hardware.LineageHardwareManager;
import com.android.systemui.C1776R$drawable;
import com.android.systemui.C1784R$string;
import com.android.systemui.p006qs.QSHost;
import com.android.systemui.p006qs.tileimpl.QSTileImpl;
import com.android.systemui.plugins.p005qs.QSTile;

/* renamed from: com.android.systemui.qs.tiles.ReadingModeTile */
public class ReadingModeTile extends QSTileImpl<QSTile.BooleanState> {
    private static final Intent LIVEDISPLAY_SETTINGS = new Intent("com.android.settings.LIVEDISPLAY_SETTINGS");
    private LineageHardwareManager mHardware = LineageHardwareManager.getInstance(this.mContext);
    private final QSTile.Icon mIcon = QSTileImpl.ResourceIcon.get(C1776R$drawable.ic_qs_reader);

    public int getMetricsCategory() {
        return 1999;
    }

    public void handleSetListening(boolean z) {
    }

    public ReadingModeTile(QSHost qSHost) {
        super(qSHost);
    }

    public QSTile.BooleanState newTileState() {
        return new QSTile.BooleanState();
    }

    /* access modifiers changed from: protected */
    public void handleClick() {
        this.mHardware.set(16384, !isReadingModeEnabled());
        refreshState();
    }

    public Intent getLongClickIntent() {
        return LIVEDISPLAY_SETTINGS;
    }

    public boolean isAvailable() {
        return this.mHardware.isSupported(16384);
    }

    /* access modifiers changed from: protected */
    public void handleUpdateState(QSTile.BooleanState booleanState, Object obj) {
        booleanState.value = isReadingModeEnabled();
        booleanState.icon = this.mIcon;
        if (booleanState.value) {
            booleanState.contentDescription = this.mContext.getString(C1784R$string.accessibility_quick_settings_reading_mode_on);
            booleanState.state = 2;
        } else {
            booleanState.contentDescription = this.mContext.getString(C1784R$string.accessibility_quick_settings_reading_mode_off);
            booleanState.state = 1;
        }
        booleanState.label = getTileLabel();
    }

    public CharSequence getTileLabel() {
        return this.mContext.getString(C1784R$string.quick_settings_reading_mode);
    }

    /* access modifiers changed from: protected */
    public String composeChangeAnnouncement() {
        if (((QSTile.BooleanState) this.mState).value) {
            return this.mContext.getString(C1784R$string.accessibility_quick_settings_reading_mode_changed_on);
        }
        return this.mContext.getString(C1784R$string.accessibility_quick_settings_reading_mode_changed_off);
    }

    private boolean isReadingModeEnabled() {
        return this.mHardware.get(16384);
    }
}
