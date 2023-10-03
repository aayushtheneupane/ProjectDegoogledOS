package android.support.p016v4.media.session;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.versionedparcelable.C0615e;

/* renamed from: android.support.v4.media.session.p */
final class C0106p implements Parcelable.Creator {
    C0106p() {
    }

    public Object createFromParcel(Parcel parcel) {
        int i = Build.VERSION.SDK_INT;
        return new MediaSessionCompat$Token(parcel.readParcelable((ClassLoader) null), (C0095e) null, (C0615e) null);
    }

    public Object[] newArray(int i) {
        return new MediaSessionCompat$Token[i];
    }
}
