package p000;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;

/* renamed from: eqt */
/* compiled from: PG */
public final class eqt implements Parcelable.Creator {
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        int c = abj.m120c(parcel);
        IBinder iBinder = null;
        ejq ejq = null;
        int i = 0;
        boolean z = false;
        boolean z2 = false;
        while (parcel.dataPosition() < c) {
            int readInt = parcel.readInt();
            int b = abj.m111b(readInt);
            if (b == 1) {
                i = abj.m125e(parcel, readInt);
            } else if (b == 2) {
                iBinder = abj.m128h(parcel, readInt);
            } else if (b == 3) {
                ejq = (ejq) abj.m84a(parcel, readInt, ejq.CREATOR);
            } else if (b == 4) {
                z = abj.m124d(parcel, readInt);
            } else if (b != 5) {
                abj.m122c(parcel, readInt);
            } else {
                z2 = abj.m124d(parcel, readInt);
            }
        }
        abj.m135o(parcel, c);
        return new eqs(i, iBinder, ejq, z, z2);
    }

    public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
        return new eqs[i];
    }
}
