package android.support.p000v4.media.session;

import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.p000v4.media.MediaMetadataCompat;
import android.support.p000v4.media.session.IMediaControllerCallback;
import java.lang.ref.WeakReference;
import java.util.List;

/* renamed from: android.support.v4.media.session.MediaControllerCompat$Callback */
public abstract class MediaControllerCompat$Callback implements IBinder.DeathRecipient {
    IMediaControllerCallback mIControllerCallback;

    /* renamed from: android.support.v4.media.session.MediaControllerCompat$Callback$StubApi21 */
    private static class StubApi21 implements MediaControllerCompatApi21$Callback {
        private final WeakReference<MediaControllerCompat$Callback> mCallback;

        StubApi21(MediaControllerCompat$Callback mediaControllerCompat$Callback) {
            this.mCallback = new WeakReference<>(mediaControllerCompat$Callback);
        }

        public void onAudioInfoChanged(int i, int i2, int i3, int i4, int i5) {
            MediaControllerCompat$Callback mediaControllerCompat$Callback = (MediaControllerCompat$Callback) this.mCallback.get();
        }

        public void onExtrasChanged(Bundle bundle) {
            MediaControllerCompat$Callback mediaControllerCompat$Callback = (MediaControllerCompat$Callback) this.mCallback.get();
        }

        public void onMetadataChanged(Object obj) {
            if (((MediaControllerCompat$Callback) this.mCallback.get()) != null) {
                MediaMetadataCompat.fromMediaMetadata(obj);
            }
        }

        public void onPlaybackStateChanged(Object obj) {
            MediaControllerCompat$Callback mediaControllerCompat$Callback = (MediaControllerCompat$Callback) this.mCallback.get();
            if (mediaControllerCompat$Callback != null && mediaControllerCompat$Callback.mIControllerCallback == null) {
                PlaybackStateCompat.fromPlaybackState(obj);
            }
        }

        public void onQueueChanged(List<?> list) {
            if (((MediaControllerCompat$Callback) this.mCallback.get()) != null) {
                MediaSessionCompat$QueueItem.fromQueueItemList(list);
            }
        }

        public void onQueueTitleChanged(CharSequence charSequence) {
            MediaControllerCompat$Callback mediaControllerCompat$Callback = (MediaControllerCompat$Callback) this.mCallback.get();
        }

        public void onSessionDestroyed() {
            MediaControllerCompat$Callback mediaControllerCompat$Callback = (MediaControllerCompat$Callback) this.mCallback.get();
        }

        public void onSessionEvent(String str, Bundle bundle) {
            MediaControllerCompat$Callback mediaControllerCompat$Callback = (MediaControllerCompat$Callback) this.mCallback.get();
            if (mediaControllerCompat$Callback != null && mediaControllerCompat$Callback.mIControllerCallback != null) {
                int i = Build.VERSION.SDK_INT;
            }
        }
    }

    /* renamed from: android.support.v4.media.session.MediaControllerCompat$Callback$StubCompat */
    private static class StubCompat extends IMediaControllerCallback.Stub {
        private final WeakReference<MediaControllerCompat$Callback> mCallback;

        StubCompat(MediaControllerCompat$Callback mediaControllerCompat$Callback) {
            this.mCallback = new WeakReference<>(mediaControllerCompat$Callback);
        }

        public void onCaptioningEnabledChanged(boolean z) throws RemoteException {
            if (((MediaControllerCompat$Callback) this.mCallback.get()) != null) {
                Boolean.valueOf(z);
            }
        }

        public void onEvent(String str, Bundle bundle) throws RemoteException {
            MediaControllerCompat$Callback mediaControllerCompat$Callback = (MediaControllerCompat$Callback) this.mCallback.get();
        }

        public void onExtrasChanged(Bundle bundle) throws RemoteException {
            MediaControllerCompat$Callback mediaControllerCompat$Callback = (MediaControllerCompat$Callback) this.mCallback.get();
        }

        public void onMetadataChanged(MediaMetadataCompat mediaMetadataCompat) throws RemoteException {
            MediaControllerCompat$Callback mediaControllerCompat$Callback = (MediaControllerCompat$Callback) this.mCallback.get();
        }

        public void onPlaybackStateChanged(PlaybackStateCompat playbackStateCompat) throws RemoteException {
            MediaControllerCompat$Callback mediaControllerCompat$Callback = (MediaControllerCompat$Callback) this.mCallback.get();
        }

        public void onQueueChanged(List<MediaSessionCompat$QueueItem> list) throws RemoteException {
            MediaControllerCompat$Callback mediaControllerCompat$Callback = (MediaControllerCompat$Callback) this.mCallback.get();
        }

        public void onQueueTitleChanged(CharSequence charSequence) throws RemoteException {
            MediaControllerCompat$Callback mediaControllerCompat$Callback = (MediaControllerCompat$Callback) this.mCallback.get();
        }

        public void onRepeatModeChanged(int i) throws RemoteException {
            if (((MediaControllerCompat$Callback) this.mCallback.get()) != null) {
                Integer.valueOf(i);
            }
        }

        public void onSessionDestroyed() throws RemoteException {
            MediaControllerCompat$Callback mediaControllerCompat$Callback = (MediaControllerCompat$Callback) this.mCallback.get();
        }

        public void onSessionReady() throws RemoteException {
            MediaControllerCompat$Callback mediaControllerCompat$Callback = (MediaControllerCompat$Callback) this.mCallback.get();
        }

        public void onShuffleModeChanged(int i) throws RemoteException {
            if (((MediaControllerCompat$Callback) this.mCallback.get()) != null) {
                Integer.valueOf(i);
            }
        }

        public void onVolumeInfoChanged(ParcelableVolumeInfo parcelableVolumeInfo) throws RemoteException {
            if (((MediaControllerCompat$Callback) this.mCallback.get()) != null && parcelableVolumeInfo != null) {
                int i = parcelableVolumeInfo.volumeType;
                int i2 = parcelableVolumeInfo.audioStream;
                int i3 = parcelableVolumeInfo.controlType;
                int i4 = parcelableVolumeInfo.maxVolume;
                int i5 = parcelableVolumeInfo.currentVolume;
            }
        }
    }

    public MediaControllerCompat$Callback() {
        int i = Build.VERSION.SDK_INT;
        new MediaControllerCompatApi21$CallbackProxy(new StubApi21(this));
    }

    public void binderDied() {
    }
}
