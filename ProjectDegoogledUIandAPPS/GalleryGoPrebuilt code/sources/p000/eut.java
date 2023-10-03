package p000;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.p002v7.widget.RecyclerView;

/* renamed from: eut */
/* compiled from: PG */
public final class eut implements Parcelable.Creator {
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        Parcel parcel2 = parcel;
        int c = abj.m120c(parcel);
        String str = null;
        String str2 = null;
        byte[] bArr = null;
        long j = 0;
        double d = 0.0d;
        boolean z = false;
        int i = 0;
        int i2 = 0;
        while (parcel.dataPosition() < c) {
            int readInt = parcel.readInt();
            switch (abj.m111b(readInt)) {
                case RecyclerView.SCROLL_STATE_SETTLING:
                    str = abj.m127g(parcel2, readInt);
                    break;
                case 3:
                    j = abj.m126f(parcel2, readInt);
                    break;
                case 4:
                    z = abj.m124d(parcel2, readInt);
                    break;
                case 5:
                    abj.m123c(parcel2, readInt, 8);
                    d = parcel.readDouble();
                    break;
                case 6:
                    str2 = abj.m127g(parcel2, readInt);
                    break;
                case 7:
                    bArr = abj.m130j(parcel2, readInt);
                    break;
                case 8:
                    i = abj.m125e(parcel2, readInt);
                    break;
                case 9:
                    i2 = abj.m125e(parcel2, readInt);
                    break;
                default:
                    abj.m122c(parcel2, readInt);
                    break;
            }
        }
        abj.m135o(parcel2, c);
        return new eus(str, j, z, d, str2, bArr, i, i2);
    }

    public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
        return new eus[i];
    }
}
