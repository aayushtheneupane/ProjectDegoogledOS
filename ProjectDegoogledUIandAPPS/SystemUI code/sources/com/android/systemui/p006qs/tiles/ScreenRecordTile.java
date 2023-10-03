package com.android.systemui.p006qs.tiles;

import android.content.Intent;
import com.android.internal.util.ScreenRecordHelper;
import com.android.systemui.C1776R$drawable;
import com.android.systemui.C1784R$string;
import com.android.systemui.p006qs.QSHost;
import com.android.systemui.p006qs.tileimpl.QSTileImpl;
import com.android.systemui.plugins.p005qs.QSTile;

/* renamed from: com.android.systemui.qs.tiles.ScreenRecordTile */
public class ScreenRecordTile extends QSTileImpl<QSTile.BooleanState> {
    private boolean mListening;
    private ScreenRecordHelper mScreenRecordHelper;

    public Intent getLongClickIntent() {
        return null;
    }

    public int getMetricsCategory() {
        return 1999;
    }

    public void handleLongClick() {
    }

    public ScreenRecordTile(QSHost qSHost) {
        super(qSHost);
    }

    public QSTile.BooleanState newTileState() {
        return new QSTile.BooleanState();
    }

    public CharSequence getTileLabel() {
        return this.mContext.getString(C1784R$string.screenrecorder_title_tile);
    }

    /* access modifiers changed from: protected */
    public void handleClick() {
        this.mScreenRecordHelper = new ScreenRecordHelper(this.mContext);
        this.mScreenRecordHelper.launchRecordPrompt();
        this.mHost.collapsePanels();
    }

    /* access modifiers changed from: protected */
    public void handleUpdateState(QSTile.BooleanState booleanState, Object obj) {
        booleanState.icon = QSTileImpl.ResourceIcon.get(C1776R$drawable.ic_screenrecord);
        booleanState.label = this.mContext.getString(C1784R$string.screenrecorder_title_tile);
        booleanState.state = 1;
    }

    public void handleSetListening(boolean z) {
        if (this.mListening != z) {
            this.mListening = z;
        }
    }
}
