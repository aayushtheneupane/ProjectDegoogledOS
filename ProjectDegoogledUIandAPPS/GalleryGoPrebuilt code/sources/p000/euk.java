package p000;

import android.os.Parcel;
import android.os.Parcelable;

/* renamed from: euk */
/* compiled from: PG */
public final class euk implements Parcelable.Creator {
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        int c = abj.m120c(parcel);
        eus[] eusArr = null;
        String[] strArr = null;
        int i = 0;
        while (parcel.dataPosition() < c) {
            int readInt = parcel.readInt();
            int b = abj.m111b(readInt);
            if (b == 2) {
                i = abj.m125e(parcel, readInt);
            } else if (b == 3) {
                eusArr = (eus[]) abj.m119b(parcel, readInt, eus.CREATOR);
            } else if (b != 4) {
                abj.m122c(parcel, readInt);
            } else {
                strArr = abj.m133m(parcel, readInt);
            }
        }
        abj.m135o(parcel, c);
        return new euj(i, eusArr, strArr);
    }

    public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
        return new euj[i];
    }
}
