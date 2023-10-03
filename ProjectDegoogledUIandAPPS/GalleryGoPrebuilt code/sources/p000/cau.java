package p000;

import android.os.Parcel;
import android.os.Parcelable;

/* renamed from: cau */
/* compiled from: PG */
public final class cau implements Parcelable {
    public static final Parcelable.Creator CREATOR = new cat();

    /* renamed from: b */
    private static final cau f3993b;

    /* renamed from: a */
    public final float[] f3994a;

    static {
        cau cau = new cau();
        f3993b = cau;
        cau.mo2963a(new float[]{-1.0f, -1.0f, -1.0f, -1.0f, -1.0f, -1.0f, -1.0f, -1.0f});
    }

    public final int describeContents() {
        return 0;
    }

    public cau() {
        this.f3994a = new float[]{0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f, 1.0f, 0.0f};
    }

    public /* synthetic */ cau(Parcel parcel) {
        this.f3994a = (float[]) ife.m12898e((Object) parcel.createFloatArray());
    }

    public final boolean equals(Object obj) {
        if (obj instanceof cau) {
            return fxk.m9834b((Object) this.f3994a, (Object) ((cau) obj).f3994a);
        }
        return false;
    }

    /* renamed from: a */
    private final float m3969a(int i) {
        return this.f3994a[iol.m14224a(i)];
    }

    /* renamed from: b */
    private final float m3970b(int i) {
        return this.f3994a[iol.m14224a(i) + 1];
    }

    public final int hashCode() {
        return fxk.m9819a((Object) this.f3994a, 17);
    }

    /* renamed from: a */
    public final void mo2963a(float[] fArr) {
        ife.m12845a(fArr.length == 8, (Object) "Quad data must have 8 elements.");
        System.arraycopy(fArr, 0, this.f3994a, 0, 8);
    }

    public final String toString() {
        float a = m3969a(1);
        float b = m3970b(1);
        float a2 = m3969a(7);
        float b2 = m3970b(7);
        float a3 = m3969a(3);
        float b3 = m3970b(3);
        float a4 = m3969a(5);
        float b4 = m3970b(5);
        StringBuilder sb = new StringBuilder(144);
        sb.append("((");
        sb.append(a);
        sb.append(", ");
        sb.append(b);
        sb.append("), (");
        sb.append(a2);
        sb.append(", ");
        sb.append(b2);
        sb.append("), (");
        sb.append(a3);
        sb.append(", ");
        sb.append(b3);
        sb.append("), (");
        sb.append(a4);
        sb.append(", ");
        sb.append(b4);
        sb.append("))");
        return sb.toString();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeFloatArray(this.f3994a);
    }
}
