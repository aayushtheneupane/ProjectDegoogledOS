package p000;

import android.os.Parcel;
import android.os.Parcelable;

/* renamed from: lj */
/* compiled from: PG */
public class C0313lj implements Parcelable {
    public static final Parcelable.Creator CREATOR = new C0312li();

    /* renamed from: a */
    public static final C0313lj f15200a = new C0311lh();

    /* renamed from: b */
    public final Parcelable f15201b;

    public final int describeContents() {
        return 0;
    }

    private C0313lj() {
        this.f15201b = null;
    }

    protected C0313lj(Parcel parcel, ClassLoader classLoader) {
        Parcelable readParcelable = parcel.readParcelable(classLoader);
        this.f15201b = readParcelable == null ? f15200a : readParcelable;
    }

    protected C0313lj(Parcelable parcelable) {
        if (parcelable != null) {
            this.f15201b = parcelable == f15200a ? null : parcelable;
            return;
        }
        throw new IllegalArgumentException("superState must not be null");
    }

    public /* synthetic */ C0313lj(byte[] bArr) {
        this.f15201b = null;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.f15201b, i);
    }
}
