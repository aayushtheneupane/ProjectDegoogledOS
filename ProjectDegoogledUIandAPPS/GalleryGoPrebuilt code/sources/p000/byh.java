package p000;

import android.os.Parcel;
import android.os.Parcelable;

/* renamed from: byh */
/* compiled from: PG */
public final class byh implements Parcelable {
    public static final Parcelable.Creator CREATOR = new byg();

    /* renamed from: a */
    public static final byh f3903a = new byh(0.0f);

    /* renamed from: b */
    public final boolean f3904b;

    /* renamed from: c */
    private final float f3905c;

    /* renamed from: d */
    private final boolean f3906d;

    /* renamed from: e */
    private final boolean f3907e;

    static {
        new byh(-1.0f);
        new byh(1.0f);
        new byh(1.7777778f);
        new byh(1.3333334f);
        new byh(1.5f);
    }

    /* renamed from: b */
    public final float mo2907b() {
        float f = this.f3905c;
        if (f == 0.0f) {
            return 0.0f;
        }
        if (f <= 0.0f) {
            f = 1.0f;
        }
        return this.f3906d != this.f3907e ? 1.0f / f : f;
    }

    public final int describeContents() {
        return 0;
    }

    private byh(float f) {
        this(f, false, false, false);
    }

    private byh(float f, boolean z, boolean z2, boolean z3) {
        this.f3905c = f;
        this.f3906d = z;
        this.f3907e = z2;
        this.f3904b = z3;
    }

    public /* synthetic */ byh(Parcel parcel) {
        this.f3905c = parcel.readFloat();
        boolean z = true;
        this.f3906d = parcel.readByte() != 0;
        this.f3907e = parcel.readByte() != 0;
        this.f3904b = parcel.readByte() == 0 ? false : z;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof byh) {
            byh byh = (byh) obj;
            float f = this.f3905c;
            if (f == 0.0f && byh.f3905c == 0.0f) {
                return true;
            }
            if (!fxk.m9828a((Object) Float.valueOf(f), (Object) Float.valueOf(byh.f3905c)) || !fxk.m9828a((Object) Boolean.valueOf(this.f3906d), (Object) Boolean.valueOf(byh.f3906d)) || !fxk.m9828a((Object) Boolean.valueOf(this.f3907e), (Object) Boolean.valueOf(byh.f3907e)) || !fxk.m9828a((Object) Boolean.valueOf(this.f3904b), (Object) Boolean.valueOf(byh.f3904b))) {
                return false;
            }
            return true;
        }
        return false;
    }

    public final int hashCode() {
        float f = this.f3905c;
        boolean z = this.f3906d;
        boolean z2 = this.f3907e;
        boolean z3 = this.f3904b;
        return fxk.m9817a(Float.floatToIntBits(f), fxk.m9817a(z ? 1 : 0, fxk.m9817a(z2 ? 1 : 0, fxk.m9817a(z3 ? 1 : 0, 17))));
    }

    /* renamed from: a */
    public static byh m3762a(float f) {
        ife.m12890c(f > 0.0f);
        return new byh(f, false, false, true);
    }

    /* renamed from: a */
    public final byh mo2906a() {
        return !this.f3904b ? new byh(this.f3905c, this.f3906d, !this.f3907e, false) : this;
    }

    public final String toString() {
        float f = this.f3905c;
        boolean z = this.f3906d;
        boolean z2 = this.f3907e;
        boolean z3 = this.f3904b;
        StringBuilder sb = new StringBuilder(77);
        sb.append("(ratio: ");
        sb.append(f);
        sb.append(", isInverted: ");
        sb.append(z);
        sb.append(", isRotated: ");
        sb.append(z2);
        sb.append(", isExact: ");
        sb.append(z3);
        sb.append(")");
        return sb.toString();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeFloat(this.f3905c);
        parcel.writeByte(this.f3906d ? (byte) 1 : 0);
        parcel.writeByte(this.f3907e ? (byte) 1 : 0);
        parcel.writeByte(this.f3904b ? (byte) 1 : 0);
    }
}
