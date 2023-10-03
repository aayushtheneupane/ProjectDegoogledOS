package com.android.systemui.p006qs.tiles;

import android.content.Context;
import android.content.Intent;
import android.database.ContentObserver;
import android.net.NetworkUtils;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.provider.Settings;
import android.widget.Toast;
import com.android.systemui.C1776R$drawable;
import com.android.systemui.C1784R$string;
import com.android.systemui.p006qs.QSHost;
import com.android.systemui.p006qs.tileimpl.QSTileImpl;
import com.android.systemui.plugins.p005qs.QSTile;

/* renamed from: com.android.systemui.qs.tiles.AdbOverNetworkTile */
public class AdbOverNetworkTile extends QSTileImpl<QSTile.BooleanState> {
    private boolean mActive = false;
    private final QSTile.Icon mIcon = QSTileImpl.ResourceIcon.get(C1776R$drawable.ic_qs_network_adb);
    private boolean mListening;
    private ContentObserver mObserver = new ContentObserver(this.mHandler) {
        public void onChange(boolean z, Uri uri) {
            AdbOverNetworkTile.this.refreshState();
        }
    };

    public int getMetricsCategory() {
        return 1999;
    }

    public AdbOverNetworkTile(QSHost qSHost) {
        super(qSHost);
    }

    public QSTile.BooleanState newTileState() {
        return new QSTile.BooleanState();
    }

    /* access modifiers changed from: protected */
    public void handleClick() {
        if (!isAdbEnabled()) {
            Context context = this.mContext;
            Toast.makeText(context, context.getString(C1784R$string.quick_settings_network_adb_toast), 1).show();
        } else {
            Settings.Global.putInt(this.mContext.getContentResolver(), "adb_port", isAdbNetworkEnabled() ? 0 : 5555);
        }
        refreshState();
    }

    public CharSequence getTileLabel() {
        return this.mContext.getString(C1784R$string.quick_settings_network_adb_label);
    }

    public Intent getLongClickIntent() {
        return new Intent("android.settings.APPLICATION_DEVELOPMENT_SETTINGS");
    }

    /* access modifiers changed from: protected */
    public void handleUpdateState(QSTile.BooleanState booleanState, Object obj) {
        this.mActive = isAdbEnabled();
        if (booleanState.slash == null) {
            booleanState.slash = new QSTile.SlashState();
        }
        booleanState.icon = this.mIcon;
        QSTile.SlashState slashState = booleanState.slash;
        boolean z = this.mActive;
        slashState.isSlashed = !z;
        if (!z) {
            booleanState.label = this.mContext.getString(C1784R$string.quick_settings_network_adb_label);
            booleanState.state = 1;
            return;
        }
        this.mActive = isAdbNetworkEnabled();
        QSTile.SlashState slashState2 = booleanState.slash;
        boolean z2 = this.mActive;
        slashState2.isSlashed = !z2;
        if (z2) {
            WifiInfo connectionInfo = ((WifiManager) this.mContext.getSystemService("wifi")).getConnectionInfo();
            if (connectionInfo != null) {
                booleanState.label = NetworkUtils.intToInetAddress(connectionInfo.getIpAddress()).getHostAddress();
            } else {
                booleanState.label = this.mContext.getString(C1784R$string.quick_settings_network_adb_label);
            }
            booleanState.value = true;
            booleanState.state = 2;
            return;
        }
        booleanState.label = this.mContext.getString(C1784R$string.quick_settings_network_adb_label);
        booleanState.value = false;
        booleanState.state = 1;
    }

    private boolean isAdbEnabled() {
        return Settings.Global.getInt(this.mContext.getContentResolver(), "adb_enabled", 0) > 0;
    }

    private boolean isAdbNetworkEnabled() {
        return Settings.Global.getInt(this.mContext.getContentResolver(), "adb_port", 0) > 0;
    }

    public void handleSetListening(boolean z) {
        if (this.mObserver != null && this.mListening != z) {
            this.mListening = z;
            if (z) {
                this.mContext.getContentResolver().registerContentObserver(Settings.Global.getUriFor("adb_port"), false, this.mObserver);
                this.mContext.getContentResolver().registerContentObserver(Settings.Global.getUriFor("adb_enabled"), false, this.mObserver);
                return;
            }
            this.mContext.getContentResolver().unregisterContentObserver(this.mObserver);
        }
    }
}
