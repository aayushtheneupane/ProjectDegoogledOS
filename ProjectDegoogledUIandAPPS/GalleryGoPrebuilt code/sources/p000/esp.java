package p000;

import android.os.Parcel;
import android.os.Parcelable;

/* renamed from: esp */
/* compiled from: PG */
public final class esp implements Parcelable.Creator {
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        int c = abj.m120c(parcel);
        int i = 0;
        int i2 = 0;
        while (parcel.dataPosition() < c) {
            int readInt = parcel.readInt();
            int b = abj.m111b(readInt);
            if (b == 2) {
                i = abj.m125e(parcel, readInt);
            } else if (b != 3) {
                abj.m122c(parcel, readInt);
            } else {
                i2 = abj.m125e(parcel, readInt);
            }
        }
        abj.m135o(parcel, c);
        return new eso(i, i2);
    }

    public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
        return new eso[i];
    }
}
