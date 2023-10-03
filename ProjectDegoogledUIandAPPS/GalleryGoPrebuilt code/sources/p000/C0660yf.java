package p000;

import android.os.Parcel;
import android.os.Parcelable;

/* renamed from: yf */
/* compiled from: PG */
public final class C0660yf extends C0313lj {
    public static final Parcelable.Creator CREATOR = new C0659ye();

    /* renamed from: c */
    public Parcelable f16334c;

    public C0660yf(Parcel parcel, ClassLoader classLoader) {
        super(parcel, classLoader);
        this.f16334c = parcel.readParcelable(classLoader == null ? C0647xt.class.getClassLoader() : classLoader);
    }

    public C0660yf(Parcelable parcelable) {
        super(parcelable);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeParcelable(this.f16334c, 0);
    }
}
