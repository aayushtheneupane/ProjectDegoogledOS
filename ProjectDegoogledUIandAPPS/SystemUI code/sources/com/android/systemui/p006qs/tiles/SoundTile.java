package com.android.systemui.p006qs.tiles;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.net.Uri;
import com.android.systemui.C1776R$drawable;
import com.android.systemui.C1784R$string;
import com.android.systemui.Dependency;
import com.android.systemui.p006qs.QSHost;
import com.android.systemui.p006qs.tileimpl.QSTileImpl;
import com.android.systemui.plugins.p005qs.QSTile;
import com.android.systemui.statusbar.policy.ZenModeController;

/* renamed from: com.android.systemui.qs.tiles.SoundTile */
public class SoundTile extends QSTileImpl<QSTile.BooleanState> {
    private final AudioManager mAudioManager = ((AudioManager) this.mContext.getSystemService("audio"));
    private boolean mListening = false;
    private BroadcastReceiver mReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            SoundTile.this.refreshState();
        }
    };
    private final ZenModeController mZenController = ((ZenModeController) Dependency.get(ZenModeController.class));

    public Intent getLongClickIntent() {
        return null;
    }

    public int getMetricsCategory() {
        return 1999;
    }

    public SoundTile(QSHost qSHost) {
        super(qSHost);
    }

    public QSTile.BooleanState newTileState() {
        return new QSTile.BooleanState();
    }

    public void handleSetListening(boolean z) {
        if (this.mListening != z) {
            this.mListening = z;
            if (z) {
                IntentFilter intentFilter = new IntentFilter();
                intentFilter.addAction("android.media.INTERNAL_RINGER_MODE_CHANGED_ACTION");
                this.mContext.registerReceiver(this.mReceiver, intentFilter);
                return;
            }
            this.mContext.unregisterReceiver(this.mReceiver);
        }
    }

    public void handleClick() {
        updateState();
    }

    public void handleLongClick() {
        this.mAudioManager.adjustVolume(0, 1);
    }

    private void updateState() {
        int ringerModeInternal = this.mAudioManager.getRingerModeInternal();
        if (ringerModeInternal == 0) {
            this.mZenController.setZen(0, (Uri) null, this.TAG);
            this.mAudioManager.setRingerModeInternal(2);
        } else if (ringerModeInternal == 1) {
            this.mZenController.setZen(2, (Uri) null, this.TAG);
        } else if (ringerModeInternal == 2) {
            this.mAudioManager.setRingerModeInternal(1);
        }
    }

    public CharSequence getTileLabel() {
        return this.mContext.getString(C1784R$string.quick_settings_sound_label);
    }

    /* access modifiers changed from: protected */
    public void handleUpdateState(QSTile.BooleanState booleanState, Object obj) {
        int ringerModeInternal = this.mAudioManager.getRingerModeInternal();
        if (ringerModeInternal == 0) {
            booleanState.icon = QSTileImpl.ResourceIcon.get(C1776R$drawable.ic_qs_ringer_silent);
            booleanState.label = this.mContext.getString(C1784R$string.quick_settings_sound_dnd);
            booleanState.contentDescription = this.mContext.getString(C1784R$string.quick_settings_sound_dnd);
        } else if (ringerModeInternal == 1) {
            booleanState.icon = QSTileImpl.ResourceIcon.get(C1776R$drawable.ic_qs_ringer_vibrate);
            booleanState.label = this.mContext.getString(C1784R$string.quick_settings_sound_vibrate);
            booleanState.contentDescription = this.mContext.getString(C1784R$string.quick_settings_sound_vibrate);
        } else if (ringerModeInternal == 2) {
            booleanState.icon = QSTileImpl.ResourceIcon.get(C1776R$drawable.ic_qs_ringer_audible);
            booleanState.label = this.mContext.getString(C1784R$string.quick_settings_sound_ring);
            booleanState.contentDescription = this.mContext.getString(C1784R$string.quick_settings_sound_ring);
        }
        booleanState.state = 1;
    }
}
