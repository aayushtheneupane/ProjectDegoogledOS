package android.support.p000v4.media.session;

import android.os.Bundle;
import android.os.RemoteException;
import android.os.ResultReceiver;
import android.support.p000v4.app.BundleCompat;
import android.support.p000v4.media.MediaMetadataCompat;
import android.support.p000v4.media.SessionToken2;
import android.support.p000v4.media.session.IMediaSession;
import android.support.p000v4.media.session.MediaControllerCompat$Callback;
import android.util.Log;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.List;

/* renamed from: android.support.v4.media.session.MediaControllerCompat$MediaControllerImplApi21 */
class MediaControllerCompat$MediaControllerImplApi21 implements MediaControllerCompat$MediaControllerImpl {
    private HashMap<MediaControllerCompat$Callback, ExtraCallback> mCallbackMap;
    private final List<MediaControllerCompat$Callback> mPendingCallbacks;
    /* access modifiers changed from: private */
    public final MediaSessionCompat$Token mSessionToken;

    /* renamed from: android.support.v4.media.session.MediaControllerCompat$MediaControllerImplApi21$ExtraBinderRequestResultReceiver */
    private static class ExtraBinderRequestResultReceiver extends ResultReceiver {
        private WeakReference<MediaControllerCompat$MediaControllerImplApi21> mMediaControllerImpl;

        /* access modifiers changed from: protected */
        public void onReceiveResult(int i, Bundle bundle) {
            MediaControllerCompat$MediaControllerImplApi21 mediaControllerCompat$MediaControllerImplApi21 = (MediaControllerCompat$MediaControllerImplApi21) this.mMediaControllerImpl.get();
            if (mediaControllerCompat$MediaControllerImplApi21 != null && bundle != null) {
                mediaControllerCompat$MediaControllerImplApi21.mSessionToken.setExtraBinder(IMediaSession.Stub.asInterface(BundleCompat.getBinder(bundle, "android.support.v4.media.session.EXTRA_BINDER")));
                mediaControllerCompat$MediaControllerImplApi21.mSessionToken.setSessionToken2(SessionToken2.fromBundle(bundle.getBundle("android.support.v4.media.session.SESSION_TOKEN2")));
                mediaControllerCompat$MediaControllerImplApi21.processPendingCallbacks();
            }
        }
    }

    /* renamed from: android.support.v4.media.session.MediaControllerCompat$MediaControllerImplApi21$ExtraCallback */
    private static class ExtraCallback extends MediaControllerCompat$Callback.StubCompat {
        ExtraCallback(MediaControllerCompat$Callback mediaControllerCompat$Callback) {
            super(mediaControllerCompat$Callback);
        }

        public void onExtrasChanged(Bundle bundle) throws RemoteException {
            throw new AssertionError();
        }

        public void onMetadataChanged(MediaMetadataCompat mediaMetadataCompat) throws RemoteException {
            throw new AssertionError();
        }

        public void onQueueChanged(List<MediaSessionCompat$QueueItem> list) throws RemoteException {
            throw new AssertionError();
        }

        public void onQueueTitleChanged(CharSequence charSequence) throws RemoteException {
            throw new AssertionError();
        }

        public void onSessionDestroyed() throws RemoteException {
            throw new AssertionError();
        }

        public void onVolumeInfoChanged(ParcelableVolumeInfo parcelableVolumeInfo) throws RemoteException {
            throw new AssertionError();
        }
    }

    /* access modifiers changed from: private */
    public void processPendingCallbacks() {
        if (this.mSessionToken.getExtraBinder() != null) {
            synchronized (this.mPendingCallbacks) {
                for (MediaControllerCompat$Callback next : this.mPendingCallbacks) {
                    ExtraCallback extraCallback = new ExtraCallback(next);
                    this.mCallbackMap.put(next, extraCallback);
                    next.mIControllerCallback = extraCallback;
                    try {
                        ((IMediaSession.Stub.Proxy) this.mSessionToken.getExtraBinder()).registerCallbackListener(extraCallback);
                    } catch (RemoteException e) {
                        Log.e("MediaControllerCompat", "Dead object in registerCallback.", e);
                    }
                }
                this.mPendingCallbacks.clear();
            }
        }
    }
}
