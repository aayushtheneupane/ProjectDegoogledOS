package p000;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

/* renamed from: gdj */
/* compiled from: PG */
public final class gdj extends C0313lj {
    public static final Parcelable.Creator CREATOR = new gdi();

    /* renamed from: c */
    public final int f11020c;

    /* renamed from: d */
    public final int f11021d;

    /* renamed from: e */
    public final boolean f11022e;

    /* renamed from: f */
    public final boolean f11023f;

    /* renamed from: g */
    public final boolean f11024g;

    public gdj(Parcel parcel, ClassLoader classLoader) {
        super(parcel, classLoader);
        this.f11020c = parcel.readInt();
        this.f11021d = parcel.readInt();
        boolean z = false;
        this.f11022e = parcel.readInt() == 1;
        this.f11023f = parcel.readInt() == 1;
        this.f11024g = parcel.readInt() == 1 ? true : z;
    }

    public gdj(Parcelable parcelable, BottomSheetBehavior bottomSheetBehavior) {
        super(parcelable);
        this.f11020c = bottomSheetBehavior.f5175j;
        this.f11021d = bottomSheetBehavior.f5167b;
        this.f11022e = bottomSheetBehavior.f5166a;
        this.f11023f = bottomSheetBehavior.f5173h;
        this.f11024g = bottomSheetBehavior.f5174i;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeInt(this.f11020c);
        parcel.writeInt(this.f11021d);
        parcel.writeInt(this.f11022e ? 1 : 0);
        parcel.writeInt(this.f11023f ? 1 : 0);
        parcel.writeInt(this.f11024g ? 1 : 0);
    }
}
