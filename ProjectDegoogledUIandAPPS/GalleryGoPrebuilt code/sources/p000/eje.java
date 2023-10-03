package p000;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Arrays;

/* renamed from: eje */
/* compiled from: PG */
public final class eje extends eqv {
    public static final Parcelable.Creator CREATOR = new ejf();

    /* renamed from: a */
    public byte[] f8413a;

    /* renamed from: b */
    public ins f8414b;

    /* renamed from: c */
    public final eiz f8415c;

    /* renamed from: d */
    private final ejo f8416d;

    /* renamed from: e */
    private final int[] f8417e;

    /* renamed from: f */
    private final String[] f8418f;

    /* renamed from: g */
    private final int[] f8419g;

    /* renamed from: h */
    private final byte[][] f8420h;

    /* renamed from: i */
    private final eup[] f8421i;

    /* renamed from: j */
    private final boolean f8422j;

    /* renamed from: k */
    private final euy[] f8423k;

    public eje(ejo ejo, ins ins, eiz eiz, boolean z) {
        this.f8416d = ejo;
        this.f8414b = ins;
        this.f8415c = eiz;
        this.f8417e = null;
        this.f8418f = null;
        this.f8419g = null;
        this.f8420h = null;
        this.f8421i = null;
        this.f8422j = z;
        this.f8423k = null;
    }

    public eje(ejo ejo, byte[] bArr, int[] iArr, String[] strArr, int[] iArr2, byte[][] bArr2, boolean z, eup[] eupArr, euy[] euyArr) {
        this.f8416d = ejo;
        this.f8413a = bArr;
        this.f8417e = iArr;
        this.f8418f = strArr;
        this.f8414b = null;
        this.f8415c = null;
        this.f8419g = iArr2;
        this.f8420h = bArr2;
        this.f8421i = eupArr;
        this.f8422j = z;
        this.f8423k = euyArr;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof eje) {
            eje eje = (eje) obj;
            return C0652xy.m16068a((Object) this.f8416d, (Object) eje.f8416d) && Arrays.equals(this.f8413a, eje.f8413a) && Arrays.equals(this.f8417e, eje.f8417e) && Arrays.equals(this.f8418f, eje.f8418f) && C0652xy.m16068a((Object) this.f8414b, (Object) eje.f8414b) && C0652xy.m16068a((Object) this.f8415c, (Object) eje.f8415c) && C0652xy.m16068a((Object) null, (Object) null) && Arrays.equals(this.f8419g, eje.f8419g) && Arrays.deepEquals(this.f8420h, eje.f8420h) && Arrays.equals(this.f8421i, eje.f8421i) && this.f8422j == eje.f8422j && Arrays.equals(this.f8423k, eje.f8423k);
        }
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.f8416d, this.f8413a, this.f8417e, this.f8418f, this.f8414b, this.f8415c, null, this.f8419g, this.f8420h, this.f8421i, Boolean.valueOf(this.f8422j), this.f8423k});
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("LogEventParcelable[");
        sb.append(this.f8416d);
        sb.append(", LogEventBytes: ");
        byte[] bArr = this.f8413a;
        sb.append(bArr != null ? new String(bArr) : null);
        sb.append(", TestCodes: ");
        sb.append(Arrays.toString(this.f8417e));
        sb.append(", MendelPackages: ");
        sb.append(Arrays.toString(this.f8418f));
        sb.append(", LogEvent: ");
        sb.append(this.f8414b);
        sb.append(", ExtensionProducer: ");
        sb.append(this.f8415c);
        sb.append(", VeProducer: ");
        sb.append((Object) null);
        sb.append(", ExperimentIDs: ");
        sb.append(Arrays.toString(this.f8419g));
        sb.append(", ExperimentTokens: ");
        sb.append(Arrays.deepToString(this.f8420h));
        sb.append(", ExperimentTokensParcelables: ");
        sb.append(Arrays.toString(this.f8421i));
        sb.append(", AddPhenotypeExperimentTokens: ");
        sb.append(this.f8422j);
        sb.append("GenericDimensions: ");
        sb.append(Arrays.toString(this.f8423k));
        sb.append("]");
        return sb.toString();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int a = abj.m82a(parcel);
        abj.m97a(parcel, 2, (Parcelable) this.f8416d, i);
        abj.m101a(parcel, 3, this.f8413a);
        abj.m102a(parcel, 4, this.f8417e);
        abj.m104a(parcel, 5, this.f8418f);
        abj.m102a(parcel, 6, this.f8419g);
        abj.m105a(parcel, 7, this.f8420h);
        abj.m100a(parcel, 8, this.f8422j);
        abj.m103a(parcel, 9, (Parcelable[]) this.f8421i, i);
        abj.m103a(parcel, 10, (Parcelable[]) this.f8423k, i);
        abj.m92a(parcel, a);
    }
}
