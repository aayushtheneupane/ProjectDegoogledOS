package com.android.systemui.p006qs.tiles;

import android.content.Intent;
import android.database.ContentObserver;
import android.os.Handler;
import android.provider.Settings;
import android.text.TextUtils;
import com.android.systemui.C1776R$drawable;
import com.android.systemui.C1784R$string;
import com.android.systemui.p006qs.QSHost;
import com.android.systemui.p006qs.tileimpl.QSTileImpl;
import com.android.systemui.plugins.p005qs.QSTile;

/* renamed from: com.android.systemui.qs.tiles.FPSInfoTile */
public class FPSInfoTile extends QSTileImpl<QSTile.BooleanState> {
    private boolean mListening;
    private FPSInfoObserver mObserver = new FPSInfoObserver(this.mHandler);

    public Intent getLongClickIntent() {
        return null;
    }

    public int getMetricsCategory() {
        return 1999;
    }

    public void handleLongClick() {
    }

    public FPSInfoTile(QSHost qSHost) {
        super(qSHost);
    }

    public QSTile.BooleanState newTileState() {
        return new QSTile.BooleanState();
    }

    /* access modifiers changed from: protected */
    public void handleClick() {
        toggleState();
        refreshState();
    }

    /* access modifiers changed from: protected */
    public void toggleState() {
        Intent className = new Intent().setClassName("com.android.systemui", "com.android.systemui.FPSInfoService");
        if (FPSInfoEnabled()) {
            Settings.Global.putInt(this.mContext.getContentResolver(), "show_fps_overlay", 0);
            this.mContext.stopService(className);
            return;
        }
        Settings.Global.putInt(this.mContext.getContentResolver(), "show_fps_overlay", 1);
        this.mContext.startService(className);
    }

    public CharSequence getTileLabel() {
        return this.mContext.getString(C1784R$string.quick_settings_fpsinfo_label);
    }

    /* access modifiers changed from: protected */
    public void handleUpdateState(QSTile.BooleanState booleanState, Object obj) {
        booleanState.label = this.mContext.getString(C1784R$string.quick_settings_fpsinfo_label);
        booleanState.icon = QSTileImpl.ResourceIcon.get(C1776R$drawable.ic_qs_fps_info);
        if (FPSInfoEnabled()) {
            booleanState.contentDescription = this.mContext.getString(C1784R$string.accessibility_quick_settings_fpsinfo_on);
            booleanState.state = 2;
            return;
        }
        booleanState.contentDescription = this.mContext.getString(C1784R$string.accessibility_quick_settings_fpsinfo_off);
        booleanState.state = 1;
    }

    private boolean FPSInfoEnabled() {
        return Settings.Global.getInt(this.mContext.getContentResolver(), "show_fps_overlay", 0) == 1;
    }

    public boolean isAvailable() {
        return !TextUtils.isEmpty(this.mContext.getResources().getString(C1784R$string.config_fpsInfoSysNode));
    }

    public void handleSetListening(boolean z) {
        if (this.mListening != z) {
            this.mListening = z;
        }
    }

    /* renamed from: com.android.systemui.qs.tiles.FPSInfoTile$FPSInfoObserver */
    private class FPSInfoObserver extends ContentObserver {
        public FPSInfoObserver(Handler handler) {
            super(handler);
        }
    }
}
