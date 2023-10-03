package p000;

import android.os.Parcel;
import android.os.Parcelable;

/* renamed from: zk */
/* compiled from: PG */
public final class C0692zk extends C0313lj {
    public static final Parcelable.Creator CREATOR = new C0691zj();

    /* renamed from: c */
    public int f16445c;

    /* renamed from: d */
    public boolean f16446d;

    public C0692zk(Parcel parcel, ClassLoader classLoader) {
        super(parcel, classLoader);
        this.f16445c = parcel.readInt();
        this.f16446d = parcel.readInt() != 0;
    }

    public C0692zk(Parcelable parcelable) {
        super(parcelable);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeInt(this.f16445c);
        parcel.writeInt(this.f16446d ? 1 : 0);
    }
}
