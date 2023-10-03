package p000;

import android.os.Parcel;
import android.os.Parcelable;

/* renamed from: eju */
/* compiled from: PG */
public final class eju implements Parcelable.Creator {
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        int c = abj.m120c(parcel);
        String str = null;
        int i = 0;
        long j = -1;
        while (parcel.dataPosition() < c) {
            int readInt = parcel.readInt();
            int b = abj.m111b(readInt);
            if (b == 1) {
                str = abj.m127g(parcel, readInt);
            } else if (b == 2) {
                i = abj.m125e(parcel, readInt);
            } else if (b != 3) {
                abj.m122c(parcel, readInt);
            } else {
                j = abj.m126f(parcel, readInt);
            }
        }
        abj.m135o(parcel, c);
        return new ejt(str, i, j);
    }

    public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
        return new ejt[i];
    }
}
