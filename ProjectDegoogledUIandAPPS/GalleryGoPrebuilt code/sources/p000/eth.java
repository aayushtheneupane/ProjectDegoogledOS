package p000;

import android.os.Parcel;
import android.os.Parcelable;

/* renamed from: eth */
/* compiled from: PG */
public final class eth implements Parcelable.Creator {
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        int c = abj.m120c(parcel);
        String str = null;
        String str2 = null;
        String str3 = null;
        String str4 = null;
        while (parcel.dataPosition() < c) {
            int readInt = parcel.readInt();
            int b = abj.m111b(readInt);
            if (b == 2) {
                str = abj.m127g(parcel, readInt);
            } else if (b == 3) {
                str2 = abj.m127g(parcel, readInt);
            } else if (b == 4) {
                str4 = abj.m127g(parcel, readInt);
            } else if (b != 5) {
                abj.m122c(parcel, readInt);
            } else {
                str3 = abj.m127g(parcel, readInt);
            }
        }
        abj.m135o(parcel, c);
        return new etg(str, str2, str3, str4);
    }

    public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
        return new etg[i];
    }
}
