package p000;

import android.os.Parcel;
import android.os.Parcelable;

/* renamed from: gcn */
/* compiled from: PG */
public final class gcn extends C0313lj {
    public static final Parcelable.Creator CREATOR = new C0170gcm();

    /* renamed from: c */
    public int f10946c;

    /* renamed from: d */
    public float f10947d;

    /* renamed from: e */
    public boolean f10948e;

    public gcn(Parcel parcel, ClassLoader classLoader) {
        super(parcel, classLoader);
        this.f10946c = parcel.readInt();
        this.f10947d = parcel.readFloat();
        this.f10948e = parcel.readByte() != 0;
    }

    public gcn(Parcelable parcelable) {
        super(parcelable);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeInt(this.f10946c);
        parcel.writeFloat(this.f10947d);
        parcel.writeByte(this.f10948e ? (byte) 1 : 0);
    }
}
