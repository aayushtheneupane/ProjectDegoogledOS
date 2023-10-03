package p000;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

/* renamed from: euj */
/* compiled from: PG */
public final class euj extends eqv implements Comparable {
    public static final Parcelable.Creator CREATOR = new euk();

    /* renamed from: a */
    public final int f9041a;

    /* renamed from: b */
    public final eus[] f9042b;

    /* renamed from: c */
    public final String[] f9043c;

    /* renamed from: d */
    private final Map f9044d = new TreeMap();

    public euj(int i, eus[] eusArr, String[] strArr) {
        this.f9041a = i;
        this.f9042b = eusArr;
        for (eus eus : eusArr) {
            this.f9044d.put(eus.f9068a, eus);
        }
        this.f9043c = strArr;
        if (strArr != null) {
            Arrays.sort(strArr);
        }
    }

    public final /* bridge */ /* synthetic */ int compareTo(Object obj) {
        return this.f9041a - ((euj) obj).f9041a;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof euj) {
            euj euj = (euj) obj;
            if (this.f9041a != euj.f9041a || !fej.m8701a((Object) this.f9044d, (Object) euj.f9044d) || !Arrays.equals(this.f9043c, euj.f9043c)) {
                return false;
            }
            return true;
        }
        return false;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("Configuration(");
        sb.append(this.f9041a);
        sb.append(", (");
        for (eus append : this.f9044d.values()) {
            sb.append(append);
            sb.append(", ");
        }
        sb.append("), (");
        String[] strArr = this.f9043c;
        if (strArr != null) {
            for (String append2 : strArr) {
                sb.append(append2);
                sb.append(", ");
            }
        } else {
            sb.append("null");
        }
        sb.append("))");
        return sb.toString();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int a = abj.m82a(parcel);
        abj.m114b(parcel, 2, this.f9041a);
        abj.m103a(parcel, 3, (Parcelable[]) this.f9042b, i);
        abj.m104a(parcel, 4, this.f9043c);
        abj.m92a(parcel, a);
    }
}
