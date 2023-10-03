package p000;

import android.os.Parcel;
import android.os.Parcelable;

/* renamed from: ewq */
/* compiled from: PG */
public final class ewq implements Parcelable.Creator {
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        int c = abj.m120c(parcel);
        ejq ejq = null;
        eqs eqs = null;
        int i = 0;
        while (parcel.dataPosition() < c) {
            int readInt = parcel.readInt();
            int b = abj.m111b(readInt);
            if (b == 1) {
                i = abj.m125e(parcel, readInt);
            } else if (b == 2) {
                ejq = (ejq) abj.m84a(parcel, readInt, ejq.CREATOR);
            } else if (b != 3) {
                abj.m122c(parcel, readInt);
            } else {
                eqs = (eqs) abj.m84a(parcel, readInt, eqs.CREATOR);
            }
        }
        abj.m135o(parcel, c);
        return new ewp(i, ejq, eqs);
    }

    public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
        return new ewp[i];
    }
}
