package android.support.p000v4.media.session;

import android.media.AudioAttributes;
import android.media.MediaMetadata;
import android.media.session.MediaController;
import android.media.session.MediaSession;
import android.media.session.PlaybackState;
import android.os.Bundle;
import android.support.p000v4.media.session.MediaControllerCompat$Callback;
import android.support.p000v4.media.session.MediaControllerCompatApi21$Callback;
import java.util.List;

/* renamed from: android.support.v4.media.session.MediaControllerCompatApi21$CallbackProxy */
class MediaControllerCompatApi21$CallbackProxy<T extends MediaControllerCompatApi21$Callback> extends MediaController.Callback {
    protected final T mCallback;

    public MediaControllerCompatApi21$CallbackProxy(T t) {
        this.mCallback = t;
    }

    public void onAudioInfoChanged(MediaController.PlaybackInfo playbackInfo) {
        int i;
        int i2;
        T t = this.mCallback;
        int playbackType = playbackInfo.getPlaybackType();
        AudioAttributes audioAttributes = playbackInfo.getAudioAttributes();
        if ((audioAttributes.getFlags() & 1) != 1) {
            if ((audioAttributes.getFlags() & 4) != 4) {
                i = 3;
                switch (audioAttributes.getUsage()) {
                    case 2:
                        i2 = 0;
                        break;
                    case 3:
                        i2 = 8;
                        break;
                    case 4:
                        i = 4;
                        break;
                    case 5:
                    case 7:
                    case 8:
                    case 9:
                    case 10:
                        i2 = 5;
                        break;
                    case 6:
                        i2 = 2;
                        break;
                    case 13:
                        i = 1;
                        break;
                }
            } else {
                i2 = 6;
            }
        } else {
            i2 = 7;
        }
        i = i2;
        ((MediaControllerCompat$Callback.StubApi21) t).onAudioInfoChanged(playbackType, i, playbackInfo.getVolumeControl(), playbackInfo.getMaxVolume(), playbackInfo.getCurrentVolume());
    }

    public void onExtrasChanged(Bundle bundle) {
        ((MediaControllerCompat$Callback.StubApi21) this.mCallback).onExtrasChanged(bundle);
    }

    public void onMetadataChanged(MediaMetadata mediaMetadata) {
        ((MediaControllerCompat$Callback.StubApi21) this.mCallback).onMetadataChanged(mediaMetadata);
    }

    public void onPlaybackStateChanged(PlaybackState playbackState) {
        ((MediaControllerCompat$Callback.StubApi21) this.mCallback).onPlaybackStateChanged(playbackState);
    }

    public void onQueueChanged(List<MediaSession.QueueItem> list) {
        ((MediaControllerCompat$Callback.StubApi21) this.mCallback).onQueueChanged(list);
    }

    public void onQueueTitleChanged(CharSequence charSequence) {
        ((MediaControllerCompat$Callback.StubApi21) this.mCallback).onQueueTitleChanged(charSequence);
    }

    public void onSessionDestroyed() {
        ((MediaControllerCompat$Callback.StubApi21) this.mCallback).onSessionDestroyed();
    }

    public void onSessionEvent(String str, Bundle bundle) {
        ((MediaControllerCompat$Callback.StubApi21) this.mCallback).onSessionEvent(str, bundle);
    }
}
