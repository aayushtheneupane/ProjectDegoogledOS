package com.android.settings.notification;

import android.content.ComponentName;
import android.content.Context;
import android.media.session.MediaController;
import android.media.session.MediaSession;
import android.media.session.MediaSessionManager;
import android.net.Uri;
import android.os.Looper;
import android.text.TextUtils;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.preference.PreferenceScreen;
import com.android.settings.slices.SliceBackgroundWorker;
import com.android.settingslib.volume.MediaSessions;
import com.havoc.config.center.C1715R;
import java.io.IOException;
import java.util.Objects;

public class RemoteVolumePreferenceController extends VolumeSeekBarPreferenceController {
    private static final String KEY_REMOTE_VOLUME = "remote_volume";
    static final int REMOTE_VOLUME = 100;
    MediaSession.Token mActiveToken;
    MediaSessions.Callbacks mCallbacks = new MediaSessions.Callbacks() {
        public void onRemoteUpdate(MediaSession.Token token, String str, MediaController.PlaybackInfo playbackInfo) {
            RemoteVolumePreferenceController remoteVolumePreferenceController = RemoteVolumePreferenceController.this;
            if (remoteVolumePreferenceController.mActiveToken == null) {
                remoteVolumePreferenceController.updateToken(token);
            }
            if (Objects.equals(RemoteVolumePreferenceController.this.mActiveToken, token)) {
                RemoteVolumePreferenceController remoteVolumePreferenceController2 = RemoteVolumePreferenceController.this;
                remoteVolumePreferenceController2.updatePreference(remoteVolumePreferenceController2.mPreference, remoteVolumePreferenceController2.mActiveToken, playbackInfo);
            }
        }

        public void onRemoteRemoved(MediaSession.Token token) {
            if (Objects.equals(RemoteVolumePreferenceController.this.mActiveToken, token)) {
                RemoteVolumePreferenceController.this.updateToken((MediaSession.Token) null);
                VolumeSeekBarPreference volumeSeekBarPreference = RemoteVolumePreferenceController.this.mPreference;
                if (volumeSeekBarPreference != null) {
                    volumeSeekBarPreference.setVisible(false);
                }
            }
        }

        public void onRemoteVolumeChanged(MediaSession.Token token, int i) {
            MediaController.PlaybackInfo playbackInfo;
            if (Objects.equals(RemoteVolumePreferenceController.this.mActiveToken, token) && (playbackInfo = RemoteVolumePreferenceController.this.mMediaController.getPlaybackInfo()) != null) {
                RemoteVolumePreferenceController.this.setSliderPosition(playbackInfo.getCurrentVolume());
            }
        }
    };
    MediaController mMediaController;
    private MediaSessions mMediaSessions;

    public int getAudioStream() {
        return REMOTE_VOLUME;
    }

    public int getAvailabilityStatus() {
        return 1;
    }

    public int getMuteIcon() {
        return C1715R.C1717drawable.ic_volume_remote_mute;
    }

    public String getPreferenceKey() {
        return KEY_REMOTE_VOLUME;
    }

    public boolean useDynamicSliceSummary() {
        return true;
    }

    public RemoteVolumePreferenceController(Context context) {
        super(context, KEY_REMOTE_VOLUME);
        this.mMediaSessions = new MediaSessions(context, Looper.getMainLooper(), this.mCallbacks);
        updateToken(getActiveRemoteToken(this.mContext));
    }

    public static MediaSession.Token getActiveRemoteToken(Context context) {
        for (MediaController next : ((MediaSessionManager) context.getSystemService(MediaSessionManager.class)).getActiveSessions((ComponentName) null)) {
            if (isRemote(next.getPlaybackInfo())) {
                return next.getSessionToken();
            }
        }
        return null;
    }

    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        this.mPreference.setVisible(this.mActiveToken != null);
        MediaController mediaController = this.mMediaController;
        if (mediaController != null) {
            updatePreference(this.mPreference, this.mActiveToken, mediaController.getPlaybackInfo());
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void onResume() {
        super.onResume();
        this.mMediaSessions.init();
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void onPause() {
        super.onPause();
        this.mMediaSessions.destroy();
    }

    public int getSliderPosition() {
        MediaController.PlaybackInfo playbackInfo;
        VolumeSeekBarPreference volumeSeekBarPreference = this.mPreference;
        if (volumeSeekBarPreference != null) {
            return volumeSeekBarPreference.getProgress();
        }
        MediaController mediaController = this.mMediaController;
        if (mediaController == null || (playbackInfo = mediaController.getPlaybackInfo()) == null) {
            return 0;
        }
        return playbackInfo.getCurrentVolume();
    }

    public boolean setSliderPosition(int i) {
        VolumeSeekBarPreference volumeSeekBarPreference = this.mPreference;
        if (volumeSeekBarPreference != null) {
            volumeSeekBarPreference.setProgress(i);
        }
        MediaController mediaController = this.mMediaController;
        if (mediaController == null) {
            return false;
        }
        mediaController.setVolumeTo(i, 0);
        return true;
    }

    public int getMax() {
        MediaController.PlaybackInfo playbackInfo;
        VolumeSeekBarPreference volumeSeekBarPreference = this.mPreference;
        if (volumeSeekBarPreference != null) {
            return volumeSeekBarPreference.getMax();
        }
        MediaController mediaController = this.mMediaController;
        if (mediaController == null || (playbackInfo = mediaController.getPlaybackInfo()) == null) {
            return 0;
        }
        return playbackInfo.getMaxVolume();
    }

    public int getMin() {
        VolumeSeekBarPreference volumeSeekBarPreference = this.mPreference;
        if (volumeSeekBarPreference != null) {
            return volumeSeekBarPreference.getMin();
        }
        return 0;
    }

    public boolean isSliceable() {
        return TextUtils.equals(getPreferenceKey(), KEY_REMOTE_VOLUME);
    }

    public static boolean isRemote(MediaController.PlaybackInfo playbackInfo) {
        return playbackInfo != null && playbackInfo.getPlaybackType() == 2;
    }

    public Class<? extends SliceBackgroundWorker> getBackgroundWorkerClass() {
        return RemoteVolumeSliceWorker.class;
    }

    /* access modifiers changed from: private */
    public void updatePreference(VolumeSeekBarPreference volumeSeekBarPreference, MediaSession.Token token, MediaController.PlaybackInfo playbackInfo) {
        if (volumeSeekBarPreference != null && token != null && playbackInfo != null) {
            volumeSeekBarPreference.setMax(playbackInfo.getMaxVolume());
            volumeSeekBarPreference.setVisible(true);
            setSliderPosition(playbackInfo.getCurrentVolume());
        }
    }

    /* access modifiers changed from: private */
    public void updateToken(MediaSession.Token token) {
        this.mActiveToken = token;
        if (token != null) {
            this.mMediaController = new MediaController(this.mContext, this.mActiveToken);
        } else {
            this.mMediaController = null;
        }
    }

    public static class RemoteVolumeSliceWorker extends SliceBackgroundWorker<Void> implements MediaSessions.Callbacks {
        private MediaSessions mMediaSessions;

        public RemoteVolumeSliceWorker(Context context, Uri uri) {
            super(context, uri);
            this.mMediaSessions = new MediaSessions(context, Looper.getMainLooper(), this);
        }

        /* access modifiers changed from: protected */
        public void onSlicePinned() {
            this.mMediaSessions.init();
        }

        /* access modifiers changed from: protected */
        public void onSliceUnpinned() {
            this.mMediaSessions.destroy();
        }

        public void close() throws IOException {
            this.mMediaSessions = null;
        }

        public void onRemoteUpdate(MediaSession.Token token, String str, MediaController.PlaybackInfo playbackInfo) {
            notifySliceChange();
        }

        public void onRemoteRemoved(MediaSession.Token token) {
            notifySliceChange();
        }

        public void onRemoteVolumeChanged(MediaSession.Token token, int i) {
            notifySliceChange();
        }
    }
}
