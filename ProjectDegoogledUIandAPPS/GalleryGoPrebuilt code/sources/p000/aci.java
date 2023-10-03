package p000;

import android.os.Parcel;
import android.os.Parcelable;

/* renamed from: aci */
/* compiled from: PG */
public final class aci extends acx {
    public static final Parcelable.Creator CREATOR = new ach();

    /* renamed from: a */
    public String f179a;

    public aci(Parcel parcel) {
        super(parcel);
        this.f179a = parcel.readString();
    }

    public aci(Parcelable parcelable) {
        super(parcelable);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeString(this.f179a);
    }
}
