package p000;

import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable;
import com.google.android.gms.common.data.BitmapTeleporter;

/* renamed from: eou */
/* compiled from: PG */
public final class eou implements Parcelable.Creator {
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        int c = abj.m120c(parcel);
        int i = 0;
        ParcelFileDescriptor parcelFileDescriptor = null;
        int i2 = 0;
        while (parcel.dataPosition() < c) {
            int readInt = parcel.readInt();
            int b = abj.m111b(readInt);
            if (b == 1) {
                i = abj.m125e(parcel, readInt);
            } else if (b == 2) {
                parcelFileDescriptor = (ParcelFileDescriptor) abj.m84a(parcel, readInt, ParcelFileDescriptor.CREATOR);
            } else if (b != 3) {
                abj.m122c(parcel, readInt);
            } else {
                i2 = abj.m125e(parcel, readInt);
            }
        }
        abj.m135o(parcel, c);
        return new BitmapTeleporter(i, parcelFileDescriptor, i2);
    }

    public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
        return new BitmapTeleporter[i];
    }
}
