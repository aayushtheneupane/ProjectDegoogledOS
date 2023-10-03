package p000;

import android.os.Parcel;
import android.os.Parcelable;

/* renamed from: aed */
/* compiled from: PG */
public final class aed extends acx {
    public static final Parcelable.Creator CREATOR = new aec();

    /* renamed from: a */
    public int f267a;

    /* renamed from: b */
    public int f268b;

    /* renamed from: c */
    public int f269c;

    public aed(Parcel parcel) {
        super(parcel);
        this.f267a = parcel.readInt();
        this.f268b = parcel.readInt();
        this.f269c = parcel.readInt();
    }

    public aed(Parcelable parcelable) {
        super(parcelable);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeInt(this.f267a);
        parcel.writeInt(this.f268b);
        parcel.writeInt(this.f269c);
    }
}
