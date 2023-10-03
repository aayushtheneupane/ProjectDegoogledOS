package p000;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;

/* renamed from: eub */
/* compiled from: PG */
public final class eub implements Parcelable.Creator {
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        int c = abj.m120c(parcel);
        String str = null;
        Intent intent = null;
        int i = 0;
        while (parcel.dataPosition() < c) {
            int readInt = parcel.readInt();
            int b = abj.m111b(readInt);
            if (b == 2) {
                i = abj.m125e(parcel, readInt);
            } else if (b == 3) {
                str = abj.m127g(parcel, readInt);
            } else if (b != 4) {
                abj.m122c(parcel, readInt);
            } else {
                intent = (Intent) abj.m84a(parcel, readInt, Intent.CREATOR);
            }
        }
        abj.m135o(parcel, c);
        return new eua(i, str, intent);
    }

    public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
        return new eua[i];
    }
}
