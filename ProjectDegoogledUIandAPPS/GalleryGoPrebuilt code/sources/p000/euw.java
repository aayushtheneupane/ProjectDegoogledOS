package p000;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.List;

/* renamed from: euw */
/* compiled from: PG */
public final class euw extends eqv {
    public static final Parcelable.Creator CREATOR = new eux();

    /* renamed from: a */
    private final List f9080a;

    public euw(List list) {
        this.f9080a = list;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof euw) {
            return this.f9080a.equals(((euw) obj).f9080a);
        }
        return false;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("FlagOverrides(");
        List list = this.f9080a;
        int size = list.size();
        boolean z = true;
        int i = 0;
        while (i < size) {
            euu euu = (euu) list.get(i);
            if (!z) {
                sb.append(", ");
            }
            euu.mo5286a(sb);
            i++;
            z = false;
        }
        sb.append(")");
        return sb.toString();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int a = abj.m82a(parcel);
        abj.m115b(parcel, 2, this.f9080a);
        abj.m92a(parcel, a);
    }
}
