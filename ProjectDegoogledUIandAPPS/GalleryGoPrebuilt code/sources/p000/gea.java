package p000;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Arrays;

/* renamed from: gea */
/* compiled from: PG */
public final class gea implements Parcelable {
    public static final Parcelable.Creator CREATOR = new gdy();

    /* renamed from: a */
    public final geu f11081a;

    /* renamed from: b */
    public final geu f11082b;

    /* renamed from: c */
    public final geu f11083c;

    /* renamed from: d */
    public final gdz f11084d;

    /* renamed from: e */
    public final int f11085e;

    /* renamed from: f */
    public final int f11086f;

    public final int describeContents() {
        return 0;
    }

    public /* synthetic */ gea(geu geu, geu geu2, geu geu3, gdz gdz) {
        this.f11081a = geu;
        this.f11082b = geu2;
        this.f11083c = geu3;
        this.f11084d = gdz;
        if (geu.compareTo(geu3) > 0) {
            throw new IllegalArgumentException("start Month cannot be after current Month");
        } else if (geu3.compareTo(geu2) <= 0) {
            this.f11086f = geu.mo6526b(geu2) + 1;
            this.f11085e = (geu2.f11132d - geu.f11132d) + 1;
        } else {
            throw new IllegalArgumentException("current Month cannot be after end Month");
        }
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof gea) {
            gea gea = (gea) obj;
            return this.f11081a.equals(gea.f11081a) && this.f11082b.equals(gea.f11082b) && this.f11083c.equals(gea.f11083c) && this.f11084d.equals(gea.f11084d);
        }
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.f11081a, this.f11082b, this.f11083c, this.f11084d});
    }

    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.f11081a, 0);
        parcel.writeParcelable(this.f11082b, 0);
        parcel.writeParcelable(this.f11083c, 0);
        parcel.writeParcelable(this.f11084d, 0);
    }
}
