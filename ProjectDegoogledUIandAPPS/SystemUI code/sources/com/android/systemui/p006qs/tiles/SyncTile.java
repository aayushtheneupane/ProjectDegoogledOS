package com.android.systemui.p006qs.tiles;

import android.content.ContentResolver;
import android.content.Intent;
import android.content.SyncStatusObserver;
import com.android.systemui.C1776R$drawable;
import com.android.systemui.C1784R$string;
import com.android.systemui.p006qs.QSHost;
import com.android.systemui.p006qs.tileimpl.QSTileImpl;
import com.android.systemui.plugins.p005qs.QSTile;

/* renamed from: com.android.systemui.qs.tiles.SyncTile */
public class SyncTile extends QSTileImpl<QSTile.BooleanState> {
    private boolean mListening;
    private SyncStatusObserver mSyncObserver = new SyncStatusObserver() {
        public void onStatusChanged(int i) {
            SyncTile.this.mHandler.post(new Runnable() {
                public void run() {
                    SyncTile.this.refreshState();
                }
            });
        }
    };
    private Object mSyncObserverHandle = null;

    public Intent getLongClickIntent() {
        return null;
    }

    public int getMetricsCategory() {
        return 1999;
    }

    public SyncTile(QSHost qSHost) {
        super(qSHost);
    }

    public QSTile.BooleanState newTileState() {
        return new QSTile.BooleanState();
    }

    public void handleClick() {
        ContentResolver.setMasterSyncAutomatically(!((QSTile.BooleanState) this.mState).value);
        refreshState();
    }

    public void handleLongClick() {
        ContentResolver.setMasterSyncAutomatically(!((QSTile.BooleanState) this.mState).value);
        refreshState();
    }

    public CharSequence getTileLabel() {
        return this.mContext.getString(C1784R$string.quick_settings_sync_label);
    }

    /* access modifiers changed from: protected */
    public void handleUpdateState(QSTile.BooleanState booleanState, Object obj) {
        booleanState.value = ContentResolver.getMasterSyncAutomatically();
        booleanState.label = this.mContext.getString(C1784R$string.quick_settings_sync_label);
        booleanState.icon = QSTileImpl.ResourceIcon.get(C1776R$drawable.ic_qs_sync);
        if (booleanState.value) {
            booleanState.contentDescription = this.mContext.getString(C1784R$string.accessibility_quick_settings_sync_on);
            booleanState.state = 2;
            return;
        }
        booleanState.contentDescription = this.mContext.getString(C1784R$string.accessibility_quick_settings_sync_off);
        booleanState.state = 1;
    }

    /* access modifiers changed from: protected */
    public String composeChangeAnnouncement() {
        if (((QSTile.BooleanState) this.mState).value) {
            return this.mContext.getString(C1784R$string.accessibility_quick_settings_sync_changed_on);
        }
        return this.mContext.getString(C1784R$string.accessibility_quick_settings_sync_changed_off);
    }

    public void handleSetListening(boolean z) {
        if (this.mListening != z) {
            this.mListening = z;
            if (z) {
                this.mSyncObserverHandle = ContentResolver.addStatusChangeListener(1, this.mSyncObserver);
                return;
            }
            ContentResolver.removeStatusChangeListener(this.mSyncObserverHandle);
            this.mSyncObserverHandle = null;
        }
    }
}
