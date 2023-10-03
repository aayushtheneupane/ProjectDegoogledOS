package p000;

import android.os.Parcel;
import android.os.Parcelable;

/* renamed from: euz */
/* compiled from: PG */
public final class euz implements Parcelable.Creator {
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        int c = abj.m120c(parcel);
        int i = 0;
        int i2 = 0;
        while (parcel.dataPosition() < c) {
            int readInt = parcel.readInt();
            int b = abj.m111b(readInt);
            if (b == 1) {
                i = abj.m125e(parcel, readInt);
            } else if (b != 2) {
                abj.m122c(parcel, readInt);
            } else {
                i2 = abj.m125e(parcel, readInt);
            }
        }
        abj.m135o(parcel, c);
        return new euy(i, i2);
    }

    public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
        return new euy[i];
    }
}
