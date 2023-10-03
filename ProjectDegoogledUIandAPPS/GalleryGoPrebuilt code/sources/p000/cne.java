package p000;

import android.os.Parcel;
import android.os.Parcelable;

/* renamed from: cne */
/* compiled from: PG */
final class cne implements Parcelable.Creator {
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        return cnf.values()[parcel.readInt()];
    }

    public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
        return new cnf[i];
    }
}
