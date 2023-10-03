package p000;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Arrays;

/* renamed from: ejc */
/* compiled from: PG */
public final class ejc extends eqv {
    public static final Parcelable.Creator CREATOR = new ejd();

    /* renamed from: a */
    private final boolean f8410a;

    /* renamed from: b */
    private final long f8411b;

    /* renamed from: c */
    private final long f8412c;

    public ejc(boolean z, long j, long j2) {
        this.f8410a = z;
        this.f8411b = j;
        this.f8412c = j2;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof ejc) {
            ejc ejc = (ejc) obj;
            return this.f8410a == ejc.f8410a && this.f8411b == ejc.f8411b && this.f8412c == ejc.f8412c;
        }
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{Boolean.valueOf(this.f8410a), Long.valueOf(this.f8411b), Long.valueOf(this.f8412c)});
    }

    public final String toString() {
        return "CollectForDebugParcelable[" + "skipPersistentStorage: " + this.f8410a + ",collectForDebugStartTimeMillis: " + this.f8411b + ",collectForDebugExpiryTimeMillis: " + this.f8412c + "]";
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int a = abj.m82a(parcel);
        abj.m100a(parcel, 1, this.f8410a);
        abj.m94a(parcel, 2, this.f8412c);
        abj.m94a(parcel, 3, this.f8411b);
        abj.m92a(parcel, a);
    }
}
