package p000;

import android.os.Parcel;
import android.os.Parcelable;

/* renamed from: ewo */
/* compiled from: PG */
public final class ewo implements Parcelable.Creator {
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        int c = abj.m120c(parcel);
        int i = 0;
        eqq eqq = null;
        while (parcel.dataPosition() < c) {
            int readInt = parcel.readInt();
            int b = abj.m111b(readInt);
            if (b == 1) {
                i = abj.m125e(parcel, readInt);
            } else if (b != 2) {
                abj.m122c(parcel, readInt);
            } else {
                eqq = (eqq) abj.m84a(parcel, readInt, eqq.CREATOR);
            }
        }
        abj.m135o(parcel, c);
        return new ewn(i, eqq);
    }

    public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
        return new ewn[i];
    }
}
