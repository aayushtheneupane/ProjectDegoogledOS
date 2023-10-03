package android.support.p000v4.media.session;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import android.support.p000v4.media.MediaMetadataCompat;
import java.util.List;

/* renamed from: android.support.v4.media.session.IMediaControllerCallback */
public interface IMediaControllerCallback extends IInterface {

    /* renamed from: android.support.v4.media.session.IMediaControllerCallback$Stub */
    public static abstract class Stub extends Binder implements IMediaControllerCallback {
        public Stub() {
            attachInterface(this, "android.support.v4.media.session.IMediaControllerCallback");
        }

        public IBinder asBinder() {
            return this;
        }

        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v2, resolved type: android.os.Bundle} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v5, resolved type: android.support.v4.media.session.PlaybackStateCompat} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v8, resolved type: android.support.v4.media.MediaMetadataCompat} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v14, resolved type: android.os.Bundle} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v17, resolved type: android.support.v4.media.session.ParcelableVolumeInfo} */
        /* JADX WARNING: type inference failed for: r0v1 */
        /* JADX WARNING: type inference failed for: r0v11, types: [java.lang.CharSequence] */
        /* JADX WARNING: type inference failed for: r0v20 */
        /* JADX WARNING: type inference failed for: r0v21 */
        /* JADX WARNING: type inference failed for: r0v22 */
        /* JADX WARNING: type inference failed for: r0v23 */
        /* JADX WARNING: type inference failed for: r0v24 */
        /* JADX WARNING: type inference failed for: r0v25 */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean onTransact(int r4, android.os.Parcel r5, android.os.Parcel r6, int r7) throws android.os.RemoteException {
            /*
                r3 = this;
                r0 = 1598968902(0x5f4e5446, float:1.4867585E19)
                java.lang.String r1 = "android.support.v4.media.session.IMediaControllerCallback"
                r2 = 1
                if (r4 == r0) goto L_0x00f0
                r0 = 0
                switch(r4) {
                    case 1: goto L_0x00d4;
                    case 2: goto L_0x00cd;
                    case 3: goto L_0x00b5;
                    case 4: goto L_0x009f;
                    case 5: goto L_0x0092;
                    case 6: goto L_0x007c;
                    case 7: goto L_0x0066;
                    case 8: goto L_0x0050;
                    case 9: goto L_0x0043;
                    case 10: goto L_0x0039;
                    case 11: goto L_0x0027;
                    case 12: goto L_0x001a;
                    case 13: goto L_0x0011;
                    default: goto L_0x000c;
                }
            L_0x000c:
                boolean r3 = super.onTransact(r4, r5, r6, r7)
                return r3
            L_0x0011:
                r5.enforceInterface(r1)
                android.support.v4.media.session.MediaControllerCompat$Callback$StubCompat r3 = (android.support.p000v4.media.session.MediaControllerCompat$Callback.StubCompat) r3
                r3.onSessionReady()
                return r2
            L_0x001a:
                r5.enforceInterface(r1)
                int r4 = r5.readInt()
                android.support.v4.media.session.MediaControllerCompat$Callback$StubCompat r3 = (android.support.p000v4.media.session.MediaControllerCompat$Callback.StubCompat) r3
                r3.onShuffleModeChanged(r4)
                return r2
            L_0x0027:
                r5.enforceInterface(r1)
                int r4 = r5.readInt()
                if (r4 == 0) goto L_0x0032
                r4 = r2
                goto L_0x0033
            L_0x0032:
                r4 = 0
            L_0x0033:
                android.support.v4.media.session.MediaControllerCompat$Callback$StubCompat r3 = (android.support.p000v4.media.session.MediaControllerCompat$Callback.StubCompat) r3
                r3.onCaptioningEnabledChanged(r4)
                return r2
            L_0x0039:
                r5.enforceInterface(r1)
                int r4 = r5.readInt()
                android.support.v4.media.session.MediaControllerCompat$Callback$StubCompat r3 = (android.support.p000v4.media.session.MediaControllerCompat$Callback.StubCompat) r3
                return r2
            L_0x0043:
                r5.enforceInterface(r1)
                int r4 = r5.readInt()
                android.support.v4.media.session.MediaControllerCompat$Callback$StubCompat r3 = (android.support.p000v4.media.session.MediaControllerCompat$Callback.StubCompat) r3
                r3.onRepeatModeChanged(r4)
                return r2
            L_0x0050:
                r5.enforceInterface(r1)
                int r4 = r5.readInt()
                if (r4 == 0) goto L_0x0062
                android.os.Parcelable$Creator<android.support.v4.media.session.ParcelableVolumeInfo> r4 = android.support.p000v4.media.session.ParcelableVolumeInfo.CREATOR
                java.lang.Object r4 = r4.createFromParcel(r5)
                r0 = r4
                android.support.v4.media.session.ParcelableVolumeInfo r0 = (android.support.p000v4.media.session.ParcelableVolumeInfo) r0
            L_0x0062:
                r3.onVolumeInfoChanged(r0)
                return r2
            L_0x0066:
                r5.enforceInterface(r1)
                int r4 = r5.readInt()
                if (r4 == 0) goto L_0x0078
                android.os.Parcelable$Creator r4 = android.os.Bundle.CREATOR
                java.lang.Object r4 = r4.createFromParcel(r5)
                r0 = r4
                android.os.Bundle r0 = (android.os.Bundle) r0
            L_0x0078:
                r3.onExtrasChanged(r0)
                return r2
            L_0x007c:
                r5.enforceInterface(r1)
                int r4 = r5.readInt()
                if (r4 == 0) goto L_0x008e
                android.os.Parcelable$Creator r4 = android.text.TextUtils.CHAR_SEQUENCE_CREATOR
                java.lang.Object r4 = r4.createFromParcel(r5)
                r0 = r4
                java.lang.CharSequence r0 = (java.lang.CharSequence) r0
            L_0x008e:
                r3.onQueueTitleChanged(r0)
                return r2
            L_0x0092:
                r5.enforceInterface(r1)
                android.os.Parcelable$Creator<android.support.v4.media.session.MediaSessionCompat$QueueItem> r4 = android.support.p000v4.media.session.MediaSessionCompat$QueueItem.CREATOR
                java.util.ArrayList r4 = r5.createTypedArrayList(r4)
                r3.onQueueChanged(r4)
                return r2
            L_0x009f:
                r5.enforceInterface(r1)
                int r4 = r5.readInt()
                if (r4 == 0) goto L_0x00b1
                android.os.Parcelable$Creator<android.support.v4.media.MediaMetadataCompat> r4 = android.support.p000v4.media.MediaMetadataCompat.CREATOR
                java.lang.Object r4 = r4.createFromParcel(r5)
                r0 = r4
                android.support.v4.media.MediaMetadataCompat r0 = (android.support.p000v4.media.MediaMetadataCompat) r0
            L_0x00b1:
                r3.onMetadataChanged(r0)
                return r2
            L_0x00b5:
                r5.enforceInterface(r1)
                int r4 = r5.readInt()
                if (r4 == 0) goto L_0x00c7
                android.os.Parcelable$Creator<android.support.v4.media.session.PlaybackStateCompat> r4 = android.support.p000v4.media.session.PlaybackStateCompat.CREATOR
                java.lang.Object r4 = r4.createFromParcel(r5)
                r0 = r4
                android.support.v4.media.session.PlaybackStateCompat r0 = (android.support.p000v4.media.session.PlaybackStateCompat) r0
            L_0x00c7:
                android.support.v4.media.session.MediaControllerCompat$Callback$StubCompat r3 = (android.support.p000v4.media.session.MediaControllerCompat$Callback.StubCompat) r3
                r3.onPlaybackStateChanged(r0)
                return r2
            L_0x00cd:
                r5.enforceInterface(r1)
                r3.onSessionDestroyed()
                return r2
            L_0x00d4:
                r5.enforceInterface(r1)
                java.lang.String r4 = r5.readString()
                int r6 = r5.readInt()
                if (r6 == 0) goto L_0x00ea
                android.os.Parcelable$Creator r6 = android.os.Bundle.CREATOR
                java.lang.Object r5 = r6.createFromParcel(r5)
                r0 = r5
                android.os.Bundle r0 = (android.os.Bundle) r0
            L_0x00ea:
                android.support.v4.media.session.MediaControllerCompat$Callback$StubCompat r3 = (android.support.p000v4.media.session.MediaControllerCompat$Callback.StubCompat) r3
                r3.onEvent(r4, r0)
                return r2
            L_0x00f0:
                r6.writeString(r1)
                return r2
            */
            throw new UnsupportedOperationException("Method not decompiled: android.support.p000v4.media.session.IMediaControllerCallback.Stub.onTransact(int, android.os.Parcel, android.os.Parcel, int):boolean");
        }
    }

    void onExtrasChanged(Bundle bundle) throws RemoteException;

    void onMetadataChanged(MediaMetadataCompat mediaMetadataCompat) throws RemoteException;

    void onQueueChanged(List<MediaSessionCompat$QueueItem> list) throws RemoteException;

    void onQueueTitleChanged(CharSequence charSequence) throws RemoteException;

    void onSessionDestroyed() throws RemoteException;

    void onVolumeInfoChanged(ParcelableVolumeInfo parcelableVolumeInfo) throws RemoteException;
}
