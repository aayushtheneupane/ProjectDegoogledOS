package p000;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;

/* renamed from: gf */
/* compiled from: PG */
final class C0173gf implements Parcelable {
    public static final Parcelable.Creator CREATOR = new C0172ge();

    /* renamed from: a */
    public ArrayList f11148a;

    /* renamed from: b */
    public ArrayList f11149b;

    /* renamed from: c */
    public C0135ew[] f11150c;

    /* renamed from: d */
    public int f11151d;

    /* renamed from: e */
    public String f11152e = null;

    public final int describeContents() {
        return 0;
    }

    public C0173gf() {
    }

    public C0173gf(Parcel parcel) {
        this.f11148a = parcel.createTypedArrayList(C0177gj.CREATOR);
        this.f11149b = parcel.createStringArrayList();
        this.f11150c = (C0135ew[]) parcel.createTypedArray(C0135ew.CREATOR);
        this.f11151d = parcel.readInt();
        this.f11152e = parcel.readString();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeTypedList(this.f11148a);
        parcel.writeStringList(this.f11149b);
        parcel.writeTypedArray(this.f11150c, i);
        parcel.writeInt(this.f11151d);
        parcel.writeString(this.f11152e);
    }
}
