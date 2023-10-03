package p000;

import android.os.Parcel;
import android.os.Parcelable;

/* renamed from: eso */
/* compiled from: PG */
public final class eso extends eqv {
    public static final Parcelable.Creator CREATOR = new esp();

    /* renamed from: a */
    public int f8948a;

    /* renamed from: b */
    private int f8949b;

    public eso() {
        this(3, 0);
    }

    public eso(int i, int i2) {
        this.f8948a = i;
        this.f8949b = i2;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int a = abj.m82a(parcel);
        abj.m114b(parcel, 2, this.f8948a);
        abj.m114b(parcel, 3, this.f8949b);
        abj.m92a(parcel, a);
    }
}
