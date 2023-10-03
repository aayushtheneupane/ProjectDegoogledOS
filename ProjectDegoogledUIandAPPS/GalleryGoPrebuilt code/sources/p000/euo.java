package p000;

import android.os.Parcel;
import android.os.Parcelable;

/* renamed from: euo */
/* compiled from: PG */
public final class euo implements Parcelable.Creator {
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        int c = abj.m120c(parcel);
        byte[] bArr = null;
        while (parcel.dataPosition() < c) {
            int readInt = parcel.readInt();
            if (abj.m111b(readInt) != 2) {
                abj.m122c(parcel, readInt);
            } else {
                bArr = abj.m130j(parcel, readInt);
            }
        }
        abj.m135o(parcel, c);
        return new eun(bArr);
    }

    public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
        return new eun[i];
    }
}
