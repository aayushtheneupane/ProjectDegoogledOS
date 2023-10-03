package p000;

import android.os.Parcel;
import android.os.Parcelable;

/* renamed from: adn */
/* compiled from: PG */
public final class adn extends acx {
    public static final Parcelable.Creator CREATOR = new adm();

    /* renamed from: a */
    public final int f222a;

    public adn(Parcel parcel) {
        super(parcel);
        this.f222a = parcel.readInt();
    }

    public adn(Parcelable parcelable, int i) {
        super(parcelable);
        this.f222a = i;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeInt(this.f222a);
    }
}
