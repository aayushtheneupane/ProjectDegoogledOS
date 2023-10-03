package p000;

import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable;

/* renamed from: esl */
/* compiled from: PG */
public final class esl implements Parcelable.Creator {
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        int c = abj.m120c(parcel);
        ParcelFileDescriptor parcelFileDescriptor = null;
        String str = null;
        String str2 = null;
        while (parcel.dataPosition() < c) {
            int readInt = parcel.readInt();
            int b = abj.m111b(readInt);
            if (b == 2) {
                parcelFileDescriptor = (ParcelFileDescriptor) abj.m84a(parcel, readInt, ParcelFileDescriptor.CREATOR);
            } else if (b == 3) {
                str = abj.m127g(parcel, readInt);
            } else if (b != 4) {
                abj.m122c(parcel, readInt);
            } else {
                str2 = abj.m127g(parcel, readInt);
            }
        }
        abj.m135o(parcel, c);
        return new esk(parcelFileDescriptor, str, str2);
    }

    public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
        return new esk[i];
    }
}
