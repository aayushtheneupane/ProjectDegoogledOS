package p000;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

/* renamed from: epn */
/* compiled from: PG */
public final class epn implements Parcelable.Creator {
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        int c = abj.m120c(parcel);
        Bundle bundle = null;
        ejt[] ejtArr = null;
        int i = 0;
        while (parcel.dataPosition() < c) {
            int readInt = parcel.readInt();
            int b = abj.m111b(readInt);
            if (b == 1) {
                bundle = abj.m129i(parcel, readInt);
            } else if (b == 2) {
                ejtArr = (ejt[]) abj.m119b(parcel, readInt, ejt.CREATOR);
            } else if (b != 3) {
                abj.m122c(parcel, readInt);
            } else {
                i = abj.m125e(parcel, readInt);
            }
        }
        abj.m135o(parcel, c);
        return new epm(bundle, ejtArr, i);
    }

    public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
        return new epm[i];
    }
}
