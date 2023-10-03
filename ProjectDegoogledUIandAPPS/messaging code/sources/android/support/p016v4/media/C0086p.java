package android.support.p016v4.media;

import android.media.MediaDescription;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

/* renamed from: android.support.v4.media.p */
final class C0086p implements Parcelable.Creator {
    C0086p() {
    }

    public Object createFromParcel(Parcel parcel) {
        int i = Build.VERSION.SDK_INT;
        return MediaDescriptionCompat.m83b(MediaDescription.CREATOR.createFromParcel(parcel));
    }

    public Object[] newArray(int i) {
        return new MediaDescriptionCompat[i];
    }
}
