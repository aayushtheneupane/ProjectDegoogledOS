package p000;

import android.os.Parcel;
import android.os.Parcelable;

/* renamed from: euv */
/* compiled from: PG */
public final class euv implements Parcelable.Creator {
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        int c = abj.m120c(parcel);
        String str = null;
        String str2 = null;
        eus eus = null;
        boolean z = false;
        while (parcel.dataPosition() < c) {
            int readInt = parcel.readInt();
            int b = abj.m111b(readInt);
            if (b == 2) {
                str = abj.m127g(parcel, readInt);
            } else if (b == 3) {
                str2 = abj.m127g(parcel, readInt);
            } else if (b == 4) {
                eus = (eus) abj.m84a(parcel, readInt, eus.CREATOR);
            } else if (b != 5) {
                abj.m122c(parcel, readInt);
            } else {
                z = abj.m124d(parcel, readInt);
            }
        }
        abj.m135o(parcel, c);
        return new euu(str, str2, eus, z);
    }

    public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
        return new euu[i];
    }
}
