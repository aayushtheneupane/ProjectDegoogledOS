package p000;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Base64;
import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

/* renamed from: eul */
/* compiled from: PG */
public final class eul extends eqv {
    public static final Parcelable.Creator CREATOR = new eum();

    /* renamed from: a */
    public final String f9045a;

    /* renamed from: b */
    public final byte[] f9046b;

    /* renamed from: c */
    public final String f9047c;

    /* renamed from: d */
    public final euj[] f9048d;

    /* renamed from: e */
    private final Map f9049e = new TreeMap();

    /* renamed from: f */
    private final boolean f9050f;

    /* renamed from: g */
    private final long f9051g;

    public eul(String str, String str2, euj[] eujArr, boolean z, byte[] bArr, long j) {
        this.f9045a = str;
        this.f9047c = str2;
        this.f9048d = eujArr;
        this.f9050f = z;
        this.f9046b = bArr;
        this.f9051g = j;
        for (euj euj : eujArr) {
            this.f9049e.put(Integer.valueOf(euj.f9041a), euj);
        }
    }

    public final boolean equals(Object obj) {
        if (obj instanceof eul) {
            eul eul = (eul) obj;
            if (!fej.m8701a((Object) this.f9045a, (Object) eul.f9045a) || !fej.m8701a((Object) this.f9047c, (Object) eul.f9047c) || !this.f9049e.equals(eul.f9049e) || this.f9050f != eul.f9050f || !Arrays.equals(this.f9046b, eul.f9046b) || this.f9051g != eul.f9051g) {
                return false;
            }
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.f9045a, this.f9047c, this.f9049e, Boolean.valueOf(this.f9050f), this.f9046b, Long.valueOf(this.f9051g)});
    }

    public final String toString() {
        String str;
        StringBuilder sb = new StringBuilder("Configurations('");
        sb.append(this.f9045a);
        sb.append("', '");
        sb.append(this.f9047c);
        sb.append("', (");
        for (euj append : this.f9049e.values()) {
            sb.append(append);
            sb.append(", ");
        }
        sb.append("), ");
        sb.append(this.f9050f);
        sb.append(", ");
        byte[] bArr = this.f9046b;
        if (bArr != null) {
            str = Base64.encodeToString(bArr, 3);
        } else {
            str = "null";
        }
        sb.append(str);
        sb.append(", ");
        sb.append(this.f9051g);
        sb.append(')');
        return sb.toString();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int a = abj.m82a(parcel);
        abj.m98a(parcel, 2, this.f9045a);
        abj.m98a(parcel, 3, this.f9047c);
        abj.m103a(parcel, 4, (Parcelable[]) this.f9048d, i);
        abj.m100a(parcel, 5, this.f9050f);
        abj.m101a(parcel, 6, this.f9046b);
        abj.m94a(parcel, 7, this.f9051g);
        abj.m92a(parcel, a);
    }
}
