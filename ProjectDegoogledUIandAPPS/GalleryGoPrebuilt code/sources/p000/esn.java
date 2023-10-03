package p000;

import android.os.Parcel;
import android.os.Parcelable;

/* renamed from: esn */
/* compiled from: PG */
public final class esn implements Parcelable.Creator {
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        int c = abj.m120c(parcel);
        String str = null;
        boolean z = false;
        while (parcel.dataPosition() < c) {
            int readInt = parcel.readInt();
            int b = abj.m111b(readInt);
            if (b == 2) {
                str = abj.m127g(parcel, readInt);
            } else if (b != 3) {
                abj.m122c(parcel, readInt);
            } else {
                z = abj.m124d(parcel, readInt);
            }
        }
        abj.m135o(parcel, c);
        return new esm(str, z);
    }

    public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
        return new esm[i];
    }
}
