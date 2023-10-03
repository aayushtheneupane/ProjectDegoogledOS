package p000;

import android.os.Parcel;
import android.os.Parcelable;

/* renamed from: fsr */
/* compiled from: PG */
public final class fsr implements Comparable, Parcelable {
    public static final Parcelable.Creator CREATOR = new fsq();

    /* renamed from: a */
    public final String f10540a;

    /* renamed from: b */
    public final long f10541b;

    /* renamed from: c */
    public final int f10542c;

    /* renamed from: d */
    public final String f10543d;

    public final int describeContents() {
        return 0;
    }

    public final String toString() {
        return this.f10540a;
    }

    public /* synthetic */ fsr(Parcel parcel) {
        this.f10540a = parcel.readString();
        this.f10541b = parcel.readLong();
        this.f10542c = parcel.readInt();
        this.f10543d = parcel.readString();
    }

    public fsr(String str, long j, int i, String str2) {
        this.f10540a = str;
        this.f10541b = j;
        this.f10542c = i;
        this.f10543d = str2;
    }

    public final /* bridge */ /* synthetic */ int compareTo(Object obj) {
        return this.f10540a.compareToIgnoreCase(((fsr) obj).f10540a);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f10540a);
        parcel.writeLong(this.f10541b);
        parcel.writeInt(this.f10542c);
        parcel.writeString(this.f10543d);
    }
}
