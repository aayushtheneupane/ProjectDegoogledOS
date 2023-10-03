package p000;

import android.os.Parcel;
import android.os.Parcelable;

/* renamed from: czc */
/* compiled from: PG */
final class czc implements Parcelable.Creator {
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        return czd.values()[parcel.readInt()];
    }

    public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
        return new czd[i];
    }
}
