package p000;

import android.os.Parcel;
import android.os.Parcelable;

/* renamed from: gft */
/* compiled from: PG */
public final class gft extends C0313lj {
    public static final Parcelable.Creator CREATOR = new gfs();

    /* renamed from: c */
    public boolean f11179c;

    public gft(Parcel parcel, ClassLoader classLoader) {
        super(parcel, classLoader);
        this.f11179c = parcel.readInt() != 1 ? false : true;
    }

    public gft(Parcelable parcelable) {
        super(parcelable);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeInt(this.f11179c ? 1 : 0);
    }
}
