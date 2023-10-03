package com.android.systemui.p006qs.tiles;

import android.content.Intent;
import com.android.internal.util.havoc.ActionUtils;
import com.android.systemui.C1776R$drawable;
import com.android.systemui.C1784R$string;
import com.android.systemui.p006qs.QSHost;
import com.android.systemui.p006qs.tileimpl.QSTileImpl;
import com.android.systemui.plugins.p005qs.QSTile;

/* renamed from: com.android.systemui.qs.tiles.ScreenshotTile */
public class ScreenshotTile extends QSTileImpl<QSTile.BooleanState> {
    private boolean mRegion = false;

    public Intent getLongClickIntent() {
        return null;
    }

    public int getMetricsCategory() {
        return 1999;
    }

    public void handleSetListening(boolean z) {
    }

    public ScreenshotTile(QSHost qSHost) {
        super(qSHost);
    }

    public QSTile.BooleanState newTileState() {
        return new QSTile.BooleanState();
    }

    public void handleClick() {
        this.mRegion = !this.mRegion;
        refreshState();
    }

    public void handleLongClick() {
        this.mHost.collapsePanels();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException unused) {
        }
        ActionUtils.takeScreenshot(!this.mRegion);
    }

    public CharSequence getTileLabel() {
        return this.mContext.getString(C1784R$string.quick_settings_screenshot_label);
    }

    /* access modifiers changed from: protected */
    public void handleUpdateState(QSTile.BooleanState booleanState, Object obj) {
        booleanState.label = this.mContext.getString(C1784R$string.quick_settings_screenshot_label);
        booleanState.icon = QSTileImpl.ResourceIcon.get(C1776R$drawable.ic_qs_screenshot);
        booleanState.state = 1;
        if (this.mRegion) {
            booleanState.secondaryLabel = this.mContext.getString(C1784R$string.quick_settings_region_screenshot_label);
            booleanState.contentDescription = this.mContext.getString(C1784R$string.quick_settings_region_screenshot_label);
            return;
        }
        booleanState.secondaryLabel = this.mContext.getString(C1784R$string.quick_settings_full_screenshot_label);
        booleanState.contentDescription = this.mContext.getString(C1784R$string.quick_settings_full_screenshot_label);
    }
}
