package p000;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

/* renamed from: cbd */
/* compiled from: PG */
public abstract class cbd implements Parcelable {
    public static final Parcelable.Creator CREATOR = new cbb();

    /* renamed from: a */
    public abstract Bitmap mo2972a();

    /* renamed from: b */
    public abstract car mo2973b();

    /* renamed from: c */
    public abstract boolean mo2974c();

    /* renamed from: d */
    public abstract cbc mo2975d();

    public final int describeContents() {
        return 0;
    }

    /* renamed from: e */
    public static cbc m3991e() {
        return new cbc((byte[]) null);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(mo2972a(), 0);
        parcel.writeString(mo2973b().name());
        parcel.writeInt(mo2974c() ? 1 : 0);
    }
}
