package android.support.p016v4.media.session;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;

@SuppressLint({"BanParcelableUsage"})
/* renamed from: android.support.v4.media.session.ParcelableVolumeInfo */
public class ParcelableVolumeInfo implements Parcelable {
    public static final Parcelable.Creator CREATOR = new C0108r();

    /* renamed from: Ae */
    public int f105Ae;

    /* renamed from: Be */
    public int f106Be;

    /* renamed from: Ce */
    public int f107Ce;

    /* renamed from: De */
    public int f108De;

    /* renamed from: Ee */
    public int f109Ee;

    public ParcelableVolumeInfo(Parcel parcel) {
        this.f105Ae = parcel.readInt();
        this.f107Ce = parcel.readInt();
        this.f108De = parcel.readInt();
        this.f109Ee = parcel.readInt();
        this.f106Be = parcel.readInt();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.f105Ae);
        parcel.writeInt(this.f107Ce);
        parcel.writeInt(this.f108De);
        parcel.writeInt(this.f109Ee);
        parcel.writeInt(this.f106Be);
    }
}
