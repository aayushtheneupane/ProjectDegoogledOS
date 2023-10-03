package android.support.p000v4.media.session;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.ResultReceiver;

/* renamed from: android.support.v4.media.session.MediaSessionCompat$ResultReceiverWrapper */
public final class MediaSessionCompat$ResultReceiverWrapper implements Parcelable {
    public static final Parcelable.Creator<MediaSessionCompat$ResultReceiverWrapper> CREATOR = new Parcelable.Creator<MediaSessionCompat$ResultReceiverWrapper>() {
        public Object createFromParcel(Parcel parcel) {
            return new MediaSessionCompat$ResultReceiverWrapper(parcel);
        }

        public Object[] newArray(int i) {
            return new MediaSessionCompat$ResultReceiverWrapper[i];
        }
    };
    private ResultReceiver mResultReceiver;

    MediaSessionCompat$ResultReceiverWrapper(Parcel parcel) {
        this.mResultReceiver = (ResultReceiver) ResultReceiver.CREATOR.createFromParcel(parcel);
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        this.mResultReceiver.writeToParcel(parcel, i);
    }
}
