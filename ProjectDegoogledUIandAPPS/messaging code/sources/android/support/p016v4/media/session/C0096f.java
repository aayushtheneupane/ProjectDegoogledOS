package android.support.p016v4.media.session;

import android.media.MediaMetadata;
import android.media.session.MediaController;
import android.media.session.PlaybackState;
import android.os.Build;
import android.os.Bundle;
import android.support.p016v4.media.MediaMetadataCompat;
import androidx.media.AudioAttributesCompat;
import java.lang.ref.WeakReference;
import java.util.List;

/* renamed from: android.support.v4.media.session.f */
class C0096f extends MediaController.Callback {
    private final WeakReference mCallback;

    C0096f(C0098h hVar) {
        this.mCallback = new WeakReference(hVar);
    }

    public void onAudioInfoChanged(MediaController.PlaybackInfo playbackInfo) {
        C0098h hVar = (C0098h) this.mCallback.get();
        if (hVar != null) {
            hVar.mo569a(new C0102l(playbackInfo.getPlaybackType(), AudioAttributesCompat.wrap(playbackInfo.getAudioAttributes()), playbackInfo.getVolumeControl(), playbackInfo.getMaxVolume(), playbackInfo.getCurrentVolume()));
        }
    }

    public void onExtrasChanged(Bundle bundle) {
        C0107q.m130b(bundle);
        C0098h hVar = (C0098h) this.mCallback.get();
        if (hVar != null) {
            hVar.onExtrasChanged(bundle);
        }
    }

    public void onMetadataChanged(MediaMetadata mediaMetadata) {
        C0098h hVar = (C0098h) this.mCallback.get();
        if (hVar != null) {
            hVar.mo567a(MediaMetadataCompat.m85c(mediaMetadata));
        }
    }

    public void onPlaybackStateChanged(PlaybackState playbackState) {
        C0098h hVar = (C0098h) this.mCallback.get();
        if (hVar != null && hVar.f113te == null) {
            hVar.mo568a(PlaybackStateCompat.m102e(playbackState));
        }
    }

    public void onQueueChanged(List list) {
        C0098h hVar = (C0098h) this.mCallback.get();
        if (hVar != null) {
            hVar.onQueueChanged(MediaSessionCompat$QueueItem.m97d(list));
        }
    }

    public void onQueueTitleChanged(CharSequence charSequence) {
        C0098h hVar = (C0098h) this.mCallback.get();
        if (hVar != null) {
            hVar.onQueueTitleChanged(charSequence);
        }
    }

    public void onSessionDestroyed() {
        C0098h hVar = (C0098h) this.mCallback.get();
        if (hVar != null) {
            hVar.onSessionDestroyed();
        }
    }

    public void onSessionEvent(String str, Bundle bundle) {
        C0107q.m130b(bundle);
        C0098h hVar = (C0098h) this.mCallback.get();
        if (hVar != null) {
            if (hVar.f113te != null) {
                int i = Build.VERSION.SDK_INT;
            }
            hVar.onSessionEvent(str, bundle);
        }
    }
}
