package p000;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Arrays;

/* renamed from: ejo */
/* compiled from: PG */
public final class ejo extends eqv {
    public static final Parcelable.Creator CREATOR = new ejp();

    /* renamed from: a */
    private final String f8432a;

    /* renamed from: b */
    private final int f8433b;

    /* renamed from: c */
    private final int f8434c;

    /* renamed from: d */
    private final String f8435d;

    /* renamed from: e */
    private final String f8436e;

    /* renamed from: f */
    private final boolean f8437f;

    /* renamed from: g */
    private final String f8438g;

    /* renamed from: h */
    private final boolean f8439h;

    /* renamed from: i */
    private final int f8440i;

    /* renamed from: j */
    private final Integer f8441j;

    public ejo(String str, int i, int i2, String str2, String str3, boolean z, int i3) {
        this.f8432a = (String) abj.m85a((Object) str);
        this.f8433b = i;
        this.f8434c = i2;
        this.f8438g = str2;
        this.f8435d = str3;
        this.f8436e = null;
        this.f8437f = !z;
        this.f8439h = z;
        int i4 = i3 - 1;
        if (i3 != 0) {
            this.f8440i = i4;
            this.f8441j = null;
            return;
        }
        throw null;
    }

    public ejo(String str, int i, int i2, String str2, String str3, boolean z, String str4, boolean z2, int i3, Integer num) {
        this.f8432a = str;
        this.f8433b = i;
        this.f8434c = i2;
        this.f8435d = str2;
        this.f8436e = str3;
        this.f8437f = z;
        this.f8438g = str4;
        this.f8439h = z2;
        this.f8440i = i3;
        this.f8441j = num;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof ejo) {
            ejo ejo = (ejo) obj;
            return C0652xy.m16068a((Object) this.f8432a, (Object) ejo.f8432a) && this.f8433b == ejo.f8433b && this.f8434c == ejo.f8434c && C0652xy.m16068a((Object) this.f8438g, (Object) ejo.f8438g) && C0652xy.m16068a((Object) this.f8435d, (Object) ejo.f8435d) && C0652xy.m16068a((Object) this.f8436e, (Object) ejo.f8436e) && this.f8437f == ejo.f8437f && this.f8439h == ejo.f8439h && this.f8440i == ejo.f8440i && this.f8441j == ejo.f8441j;
        }
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.f8432a, Integer.valueOf(this.f8433b), Integer.valueOf(this.f8434c), this.f8438g, this.f8435d, this.f8436e, Boolean.valueOf(this.f8437f), Boolean.valueOf(this.f8439h), Integer.valueOf(this.f8440i), this.f8441j});
    }

    public final String toString() {
        return "PlayLoggerContext[package=" + this.f8432a + ",packageVersionCode=" + this.f8433b + ",logSource=" + this.f8434c + ",logSourceName=" + this.f8438g + ",uploadAccount=" + this.f8435d + ",loggingId=" + this.f8436e + ",logAndroidId=" + this.f8437f + ",isAnonymous=" + this.f8439h + ",qosTier=" + this.f8440i + ",appMobilespecId=" + this.f8441j + "]";
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int a = abj.m82a(parcel);
        abj.m98a(parcel, 2, this.f8432a);
        abj.m114b(parcel, 3, this.f8433b);
        abj.m114b(parcel, 4, this.f8434c);
        abj.m98a(parcel, 5, this.f8435d);
        abj.m98a(parcel, 6, this.f8436e);
        abj.m100a(parcel, 7, this.f8437f);
        abj.m98a(parcel, 8, this.f8438g);
        abj.m100a(parcel, 9, this.f8439h);
        abj.m114b(parcel, 10, this.f8440i);
        Integer num = this.f8441j;
        if (num != null) {
            abj.m93a(parcel, 11, 4);
            parcel.writeInt(num.intValue());
        }
        abj.m92a(parcel, a);
    }
}
