package p000;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Arrays;

/* renamed from: ejt */
/* compiled from: PG */
public final class ejt extends eqv {
    public static final Parcelable.Creator CREATOR = new eju();

    /* renamed from: a */
    public final String f8449a;
    @Deprecated

    /* renamed from: b */
    private final int f8450b;

    /* renamed from: c */
    private final long f8451c;

    /* renamed from: a */
    public final long mo4904a() {
        long j = this.f8451c;
        return j == -1 ? (long) this.f8450b : j;
    }

    public ejt(String str, int i, long j) {
        this.f8449a = str;
        this.f8450b = i;
        this.f8451c = j;
    }

    public ejt(String str) {
        this.f8449a = str;
        this.f8451c = 1;
        this.f8450b = -1;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof ejt) {
            ejt ejt = (ejt) obj;
            String str = this.f8449a;
            if (((str == null || !str.equals(ejt.f8449a)) && (this.f8449a != null || ejt.f8449a != null)) || mo4904a() != ejt.mo4904a()) {
                return false;
            }
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.f8449a, Long.valueOf(mo4904a())});
    }

    public final String toString() {
        eqo a = C0652xy.m16063a((Object) this);
        a.mo5163a("name", this.f8449a);
        a.mo5163a("version", Long.valueOf(mo4904a()));
        return a.toString();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int a = abj.m82a(parcel);
        abj.m98a(parcel, 1, this.f8449a);
        abj.m114b(parcel, 2, this.f8450b);
        abj.m94a(parcel, 3, mo4904a());
        abj.m92a(parcel, a);
    }
}
