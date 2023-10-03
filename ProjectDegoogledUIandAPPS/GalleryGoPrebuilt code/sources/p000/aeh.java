package p000;

import android.os.Parcel;
import android.os.Parcelable;

/* renamed from: aeh */
/* compiled from: PG */
public final class aeh extends acx {
    public static final Parcelable.Creator CREATOR = new aeg();

    /* renamed from: a */
    public boolean f272a;

    public aeh(Parcel parcel) {
        super(parcel);
        this.f272a = parcel.readInt() != 1 ? false : true;
    }

    public aeh(Parcelable parcelable) {
        super(parcelable);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeInt(this.f272a ? 1 : 0);
    }
}
