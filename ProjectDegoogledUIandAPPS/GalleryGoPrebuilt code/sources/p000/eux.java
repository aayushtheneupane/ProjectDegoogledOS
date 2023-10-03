package p000;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;

/* renamed from: eux */
/* compiled from: PG */
public final class eux implements Parcelable.Creator {
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        int c = abj.m120c(parcel);
        ArrayList arrayList = null;
        while (parcel.dataPosition() < c) {
            int readInt = parcel.readInt();
            if (abj.m111b(readInt) != 2) {
                abj.m122c(parcel, readInt);
            } else {
                arrayList = abj.m121c(parcel, readInt, euu.CREATOR);
            }
        }
        abj.m135o(parcel, c);
        return new euw(arrayList);
    }

    public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
        return new euw[i];
    }
}
