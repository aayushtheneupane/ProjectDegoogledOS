package androidx.fragment.app;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

@SuppressLint({"BanParcelableUsage"})
public class Fragment$SavedState implements Parcelable {
    public static final Parcelable.Creator CREATOR = new C0423i();
    final Bundle mState;

    Fragment$SavedState(Parcel parcel, ClassLoader classLoader) {
        Bundle bundle;
        this.mState = parcel.readBundle();
        if (classLoader != null && (bundle = this.mState) != null) {
            bundle.setClassLoader(classLoader);
        }
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeBundle(this.mState);
    }
}
