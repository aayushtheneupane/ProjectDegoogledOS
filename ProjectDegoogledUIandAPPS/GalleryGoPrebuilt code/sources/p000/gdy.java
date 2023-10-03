package p000;

import android.os.Parcel;
import android.os.Parcelable;

/* renamed from: gdy */
/* compiled from: PG */
final class gdy implements Parcelable.Creator {
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        return new gea((geu) parcel.readParcelable(geu.class.getClassLoader()), (geu) parcel.readParcelable(geu.class.getClassLoader()), (geu) parcel.readParcelable(geu.class.getClassLoader()), (gdz) parcel.readParcelable(gdz.class.getClassLoader()));
    }

    public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
        return new gea[i];
    }
}
