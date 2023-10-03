package p000;

import android.os.Parcel;
import android.os.Parcelable;

/* renamed from: ejd */
/* compiled from: PG */
public final class ejd implements Parcelable.Creator {
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        int c = abj.m120c(parcel);
        long j = 0;
        long j2 = 0;
        boolean z = false;
        while (parcel.dataPosition() < c) {
            int readInt = parcel.readInt();
            int b = abj.m111b(readInt);
            if (b == 1) {
                z = abj.m124d(parcel, readInt);
            } else if (b == 2) {
                j2 = abj.m126f(parcel, readInt);
            } else if (b != 3) {
                abj.m122c(parcel, readInt);
            } else {
                j = abj.m126f(parcel, readInt);
            }
        }
        abj.m135o(parcel, c);
        return new ejc(z, j, j2);
    }

    public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
        return new ejc[i];
    }
}
