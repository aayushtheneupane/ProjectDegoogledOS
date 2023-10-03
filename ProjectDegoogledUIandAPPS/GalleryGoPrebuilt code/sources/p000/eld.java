package p000;

import android.app.PendingIntent;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.api.Status;

/* renamed from: eld */
/* compiled from: PG */
public final class eld implements Parcelable.Creator {
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        int c = abj.m120c(parcel);
        String str = null;
        PendingIntent pendingIntent = null;
        int i = 0;
        int i2 = 0;
        while (parcel.dataPosition() < c) {
            int readInt = parcel.readInt();
            int b = abj.m111b(readInt);
            if (b == 1) {
                i2 = abj.m125e(parcel, readInt);
            } else if (b == 2) {
                str = abj.m127g(parcel, readInt);
            } else if (b == 3) {
                pendingIntent = (PendingIntent) abj.m84a(parcel, readInt, PendingIntent.CREATOR);
            } else if (b != 1000) {
                abj.m122c(parcel, readInt);
            } else {
                i = abj.m125e(parcel, readInt);
            }
        }
        abj.m135o(parcel, c);
        return new Status(i, i2, str, pendingIntent);
    }

    public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
        return new Status[i];
    }
}
