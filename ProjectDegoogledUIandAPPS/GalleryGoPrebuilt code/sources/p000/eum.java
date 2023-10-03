package p000;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.p002v7.widget.RecyclerView;

/* renamed from: eum */
/* compiled from: PG */
public final class eum implements Parcelable.Creator {
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        int c = abj.m120c(parcel);
        String str = null;
        String str2 = null;
        euj[] eujArr = null;
        byte[] bArr = null;
        long j = 0;
        boolean z = false;
        while (parcel.dataPosition() < c) {
            int readInt = parcel.readInt();
            switch (abj.m111b(readInt)) {
                case RecyclerView.SCROLL_STATE_SETTLING:
                    str = abj.m127g(parcel, readInt);
                    break;
                case 3:
                    str2 = abj.m127g(parcel, readInt);
                    break;
                case 4:
                    eujArr = (euj[]) abj.m119b(parcel, readInt, euj.CREATOR);
                    break;
                case 5:
                    z = abj.m124d(parcel, readInt);
                    break;
                case 6:
                    bArr = abj.m130j(parcel, readInt);
                    break;
                case 7:
                    j = abj.m126f(parcel, readInt);
                    break;
                default:
                    abj.m122c(parcel, readInt);
                    break;
            }
        }
        abj.m135o(parcel, c);
        return new eul(str, str2, eujArr, z, bArr, j);
    }

    public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
        return new eul[i];
    }
}
