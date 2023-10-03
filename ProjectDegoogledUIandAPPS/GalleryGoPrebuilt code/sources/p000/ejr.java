package p000;

import android.app.PendingIntent;
import android.os.Parcel;
import android.os.Parcelable;

/* renamed from: ejr */
/* compiled from: PG */
public final class ejr implements Parcelable.Creator {
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        int c = abj.m120c(parcel);
        PendingIntent pendingIntent = null;
        String str = null;
        int i = 0;
        int i2 = 0;
        while (parcel.dataPosition() < c) {
            int readInt = parcel.readInt();
            int b = abj.m111b(readInt);
            if (b == 1) {
                i = abj.m125e(parcel, readInt);
            } else if (b == 2) {
                i2 = abj.m125e(parcel, readInt);
            } else if (b == 3) {
                pendingIntent = (PendingIntent) abj.m84a(parcel, readInt, PendingIntent.CREATOR);
            } else if (b != 4) {
                abj.m122c(parcel, readInt);
            } else {
                str = abj.m127g(parcel, readInt);
            }
        }
        abj.m135o(parcel, c);
        return new ejq(i, i2, pendingIntent, str);
    }

    public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
        return new ejq[i];
    }
}
