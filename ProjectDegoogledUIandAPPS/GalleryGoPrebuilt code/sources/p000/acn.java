package p000;

import android.os.Parcel;
import android.os.Parcelable;

/* renamed from: acn */
/* compiled from: PG */
public final class acn extends acx {
    public static final Parcelable.Creator CREATOR = new acm();

    /* renamed from: a */
    public String f184a;

    public acn(Parcel parcel) {
        super(parcel);
        this.f184a = parcel.readString();
    }

    public acn(Parcelable parcelable) {
        super(parcelable);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeString(this.f184a);
    }
}
