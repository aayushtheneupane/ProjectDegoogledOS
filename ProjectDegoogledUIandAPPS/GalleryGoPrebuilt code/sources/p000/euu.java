package p000;

import android.os.Parcel;
import android.os.Parcelable;

/* renamed from: euu */
/* compiled from: PG */
public final class euu extends eqv {
    public static final Parcelable.Creator CREATOR = new euv();

    /* renamed from: a */
    private final String f9076a;

    /* renamed from: b */
    private final String f9077b;

    /* renamed from: c */
    private final eus f9078c;

    /* renamed from: d */
    private final boolean f9079d;

    public euu(String str, String str2, eus eus, boolean z) {
        this.f9076a = str;
        this.f9077b = str2;
        this.f9078c = eus;
        this.f9079d = z;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof euu) {
            euu euu = (euu) obj;
            return fej.m8701a((Object) this.f9076a, (Object) euu.f9076a) && fej.m8701a((Object) this.f9077b, (Object) euu.f9077b) && fej.m8701a((Object) this.f9078c, (Object) euu.f9078c) && this.f9079d == euu.f9079d;
        }
    }

    public final String toString() {
        return mo5286a(new StringBuilder());
    }

    /* renamed from: a */
    public final String mo5286a(StringBuilder sb) {
        sb.append("FlagOverride(");
        sb.append(this.f9076a);
        sb.append(", ");
        sb.append(this.f9077b);
        sb.append(", ");
        this.f9078c.mo5279a(sb);
        sb.append(", ");
        sb.append(this.f9079d);
        sb.append(")");
        return sb.toString();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int a = abj.m82a(parcel);
        abj.m98a(parcel, 2, this.f9076a);
        abj.m98a(parcel, 3, this.f9077b);
        abj.m97a(parcel, 4, (Parcelable) this.f9078c, i);
        abj.m100a(parcel, 5, this.f9079d);
        abj.m92a(parcel, a);
    }
}
