package com.android.systemui.p006qs.tiles;

import android.content.Intent;
import android.media.AudioManager;
import android.media.RemoteController;
import android.media.session.MediaSessionLegacyHelper;
import android.os.Handler;
import android.os.SystemClock;
import android.view.KeyEvent;
import android.view.ViewConfiguration;
import com.android.systemui.C1776R$drawable;
import com.android.systemui.C1784R$string;
import com.android.systemui.p006qs.QSHost;
import com.android.systemui.p006qs.tileimpl.QSTileImpl;
import com.android.systemui.plugins.p005qs.QSTile;

/* renamed from: com.android.systemui.qs.tiles.MusicTile */
public class MusicTile extends QSTileImpl<QSTile.BooleanState> {
    private final boolean DBG = false;
    private final String TAG = "MusicTile";
    final Runnable checkDouble = new Runnable() {
        public void run() {
            int unused = MusicTile.this.mTaps = 0;
            MusicTile.this.sendMediaButtonClick(85);
        }
    };
    /* access modifiers changed from: private */
    public boolean mActive = false;
    private final AudioManager mAudioManager = ((AudioManager) this.mContext.getSystemService("audio"));
    /* access modifiers changed from: private */
    public boolean mClientIdLost = true;
    private Handler mHandler = new Handler();
    /* access modifiers changed from: private */
    public Metadata mMetadata = new Metadata();
    private RemoteController.OnClientUpdateListener mRCClientUpdateListener = new RemoteController.OnClientUpdateListener() {
        private String mCurrentTrack = null;

        public void onClientTransportControlUpdate(int i) {
        }

        public void onClientChange(boolean z) {
            if (z) {
                MusicTile.this.mMetadata.clear();
                this.mCurrentTrack = null;
                boolean unused = MusicTile.this.mActive = false;
                boolean unused2 = MusicTile.this.mClientIdLost = true;
                MusicTile.this.refreshState();
            }
        }

        public void onClientPlaybackStateUpdate(int i, long j, long j2, float f) {
            boolean unused = MusicTile.this.mClientIdLost = false;
            MusicTile.this.playbackStateUpdate(i);
        }

        public void onClientPlaybackStateUpdate(int i) {
            boolean unused = MusicTile.this.mClientIdLost = false;
            MusicTile.this.playbackStateUpdate(i);
        }

        public void onClientMetadataUpdate(RemoteController.MetadataEditor metadataEditor) {
            String unused = MusicTile.this.mMetadata.trackTitle = metadataEditor.getString(7, MusicTile.this.mMetadata.trackTitle);
            boolean unused2 = MusicTile.this.mClientIdLost = false;
            if (MusicTile.this.mMetadata.trackTitle != null && !MusicTile.this.mMetadata.trackTitle.equals(this.mCurrentTrack)) {
                this.mCurrentTrack = MusicTile.this.mMetadata.trackTitle;
                MusicTile.this.refreshState();
            }
        }
    };
    private RemoteController mRemoteController = new RemoteController(this.mContext, this.mRCClientUpdateListener);
    /* access modifiers changed from: private */
    public int mTaps = 0;

    public Intent getLongClickIntent() {
        return null;
    }

    public int getMetricsCategory() {
        return 1999;
    }

    public void handleSetListening(boolean z) {
    }

    public MusicTile(QSHost qSHost) {
        super(qSHost);
        this.mAudioManager.registerRemoteController(this.mRemoteController);
    }

    public QSTile.BooleanState newTileState() {
        return new QSTile.BooleanState();
    }

    public void handleClick() {
        if (this.mActive) {
            checkDoubleClick();
        } else {
            sendMediaButtonClick(85);
        }
        refreshState();
    }

    public void handleLongClick() {
        sendMediaButtonClick(87);
        refreshState();
    }

    public CharSequence getTileLabel() {
        return this.mContext.getString(C1784R$string.quick_settings_music_label);
    }

    /* access modifiers changed from: protected */
    public void handleUpdateState(QSTile.BooleanState booleanState, Object obj) {
        if (this.mActive) {
            booleanState.icon = QSTileImpl.ResourceIcon.get(C1776R$drawable.ic_qs_media_pause);
            booleanState.label = this.mMetadata.trackTitle != null ? this.mMetadata.trackTitle : this.mContext.getString(C1784R$string.quick_settings_music_pause);
        } else {
            booleanState.icon = QSTileImpl.ResourceIcon.get(C1776R$drawable.ic_qs_media_play);
            booleanState.label = this.mContext.getString(C1784R$string.quick_settings_music_play);
        }
        booleanState.state = this.mActive ? 2 : 1;
    }

    /* access modifiers changed from: private */
    public void playbackStateUpdate(int i) {
        boolean z = i == 3;
        if (z != this.mActive) {
            this.mActive = z;
            refreshState();
        }
    }

    private void checkDoubleClick() {
        this.mHandler.removeCallbacks(this.checkDouble);
        int i = this.mTaps;
        if (i > 0) {
            Intent intent = new Intent("android.intent.action.MAIN");
            intent.addCategory("android.intent.category.APP_MUSIC");
            intent.setFlags(268435456);
            this.mTaps = 0;
            return;
        }
        this.mTaps = i + 1;
        this.mHandler.postDelayed(this.checkDouble, (long) ViewConfiguration.getDoubleTapTimeout());
    }

    /* access modifiers changed from: private */
    public void sendMediaButtonClick(int i) {
        if (!this.mClientIdLost) {
            this.mRemoteController.sendMediaKeyEvent(new KeyEvent(0, i));
            this.mRemoteController.sendMediaKeyEvent(new KeyEvent(1, i));
            return;
        }
        long uptimeMillis = SystemClock.uptimeMillis();
        KeyEvent keyEvent = new KeyEvent(uptimeMillis, uptimeMillis, 0, i, 0);
        MediaSessionLegacyHelper.getHelper(this.mContext).sendMediaButtonEvent(keyEvent, true);
        MediaSessionLegacyHelper.getHelper(this.mContext).sendMediaButtonEvent(KeyEvent.changeAction(keyEvent, 1), true);
    }

    /* renamed from: com.android.systemui.qs.tiles.MusicTile$Metadata */
    class Metadata {
        /* access modifiers changed from: private */
        public String trackTitle;

        Metadata() {
        }

        public void clear() {
            this.trackTitle = null;
        }
    }
}
