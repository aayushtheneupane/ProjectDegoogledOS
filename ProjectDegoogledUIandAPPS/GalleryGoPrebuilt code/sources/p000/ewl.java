package p000;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;

/* renamed from: ewl */
/* compiled from: PG */
public final class ewl implements Parcelable.Creator {
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        int c = abj.m120c(parcel);
        ArrayList arrayList = null;
        String str = null;
        while (parcel.dataPosition() < c) {
            int readInt = parcel.readInt();
            int b = abj.m111b(readInt);
            if (b == 1) {
                arrayList = abj.m134n(parcel, readInt);
            } else if (b != 2) {
                abj.m122c(parcel, readInt);
            } else {
                str = abj.m127g(parcel, readInt);
            }
        }
        abj.m135o(parcel, c);
        return new ewk(arrayList, str);
    }

    public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
        return new ewk[i];
    }
}
