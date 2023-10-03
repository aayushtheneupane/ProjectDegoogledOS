package p000;

import android.os.Parcel;
import android.os.Parcelable;

/* renamed from: euy */
/* compiled from: PG */
public final class euy extends eqv implements Comparable {
    public static final Parcelable.Creator CREATOR = new euz();

    /* renamed from: a */
    private final int f9081a;

    /* renamed from: b */
    private final int f9082b;

    public final int hashCode() {
        return (this.f9081a * 31) + this.f9082b;
    }

    public euy(int i, int i2) {
        this.f9081a = i;
        this.f9082b = i2;
    }

    /* access modifiers changed from: private */
    /* renamed from: a */
    public final int compareTo(euy euy) {
        int i = this.f9081a;
        int i2 = euy.f9081a;
        if (i < i2) {
            return -1;
        }
        if (i > i2) {
            return 1;
        }
        int i3 = this.f9082b;
        int i4 = euy.f9082b;
        if (i3 < i4) {
            return -1;
        }
        if (i3 <= i4) {
            return 0;
        }
        return 1;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof euy) || compareTo((euy) obj) != 0) {
            return false;
        }
        return true;
    }

    public final String toString() {
        return "GenericDimension" + "(" + this.f9081a + ", " + this.f9082b + ")";
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int a = abj.m82a(parcel);
        abj.m114b(parcel, 1, this.f9081a);
        abj.m114b(parcel, 2, this.f9082b);
        abj.m92a(parcel, a);
    }
}
