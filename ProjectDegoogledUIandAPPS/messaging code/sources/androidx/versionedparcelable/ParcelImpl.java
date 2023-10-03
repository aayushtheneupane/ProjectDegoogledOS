package androidx.versionedparcelable;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;

@SuppressLint({"BanParcelableUsage"})
public class ParcelImpl implements Parcelable {
    public static final Parcelable.Creator CREATOR = new C0611a();
    private final C0615e mParcel;

    protected ParcelImpl(Parcel parcel) {
        this.mParcel = new C0614d(parcel).mo5316rd();
    }

    public int describeContents() {
        return 0;
    }

    /* renamed from: nd */
    public C0615e mo5292nd() {
        return this.mParcel;
    }

    public void writeToParcel(Parcel parcel, int i) {
        new C0614d(parcel).mo5303b(this.mParcel);
    }
}
