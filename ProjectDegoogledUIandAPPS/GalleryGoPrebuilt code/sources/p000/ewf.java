package p000;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;

/* renamed from: ewf */
/* compiled from: PG */
public final class ewf implements Parcelable.Creator {
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        int c = abj.m120c(parcel);
        int i = 0;
        Intent intent = null;
        int i2 = 0;
        while (parcel.dataPosition() < c) {
            int readInt = parcel.readInt();
            int b = abj.m111b(readInt);
            if (b == 1) {
                i = abj.m125e(parcel, readInt);
            } else if (b == 2) {
                i2 = abj.m125e(parcel, readInt);
            } else if (b != 3) {
                abj.m122c(parcel, readInt);
            } else {
                intent = (Intent) abj.m84a(parcel, readInt, Intent.CREATOR);
            }
        }
        abj.m135o(parcel, c);
        return new ewe(i, i2, intent);
    }

    public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
        return new ewe[i];
    }
}
