package com.android.systemui.p006qs.tiles;

import android.content.Intent;
import android.database.ContentObserver;
import android.os.Handler;
import android.provider.Settings;
import com.android.systemui.C1776R$drawable;
import com.android.systemui.C1784R$string;
import com.android.systemui.p006qs.QSHost;
import com.android.systemui.p006qs.tileimpl.QSTileImpl;
import com.android.systemui.plugins.p005qs.QSTile;

/* renamed from: com.android.systemui.qs.tiles.CPUInfoTile */
public class CPUInfoTile extends QSTileImpl<QSTile.BooleanState> {
    private boolean mListening;
    private CPUInfoObserver mObserver = new CPUInfoObserver(this.mHandler);

    public Intent getLongClickIntent() {
        return null;
    }

    public int getMetricsCategory() {
        return 1999;
    }

    public void handleLongClick() {
    }

    public CPUInfoTile(QSHost qSHost) {
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
        Intent className = new Intent().setClassName("com.android.systemui", "com.android.systemui.CPUInfoService");
        if (CPUInfoEnabled()) {
            Settings.Global.putInt(this.mContext.getContentResolver(), "show_cpu_overlay", 0);
            this.mContext.stopService(className);
            return;
        }
        Settings.Global.putInt(this.mContext.getContentResolver(), "show_cpu_overlay", 1);
        this.mContext.startService(className);
    }

    public CharSequence getTileLabel() {
        return this.mContext.getString(C1784R$string.quick_settings_cpuinfo_label);
    }

    /* access modifiers changed from: protected */
    public void handleUpdateState(QSTile.BooleanState booleanState, Object obj) {
        booleanState.label = this.mContext.getString(C1784R$string.quick_settings_cpuinfo_label);
        booleanState.icon = QSTileImpl.ResourceIcon.get(C1776R$drawable.ic_qs_cpuinfo);
        if (CPUInfoEnabled()) {
            booleanState.contentDescription = this.mContext.getString(C1784R$string.accessibility_quick_settings_cpuinfo_on);
            booleanState.state = 2;
            return;
        }
        booleanState.contentDescription = this.mContext.getString(C1784R$string.accessibility_quick_settings_cpuinfo_off);
        booleanState.state = 1;
    }

    private boolean CPUInfoEnabled() {
        return Settings.Global.getInt(this.mContext.getContentResolver(), "show_cpu_overlay", 0) == 1;
    }

    public void handleSetListening(boolean z) {
        if (this.mListening != z) {
            this.mListening = z;
        }
    }

    /* renamed from: com.android.systemui.qs.tiles.CPUInfoTile$CPUInfoObserver */
    private class CPUInfoObserver extends ContentObserver {
        public CPUInfoObserver(Handler handler) {
            super(handler);
        }
    }
}
