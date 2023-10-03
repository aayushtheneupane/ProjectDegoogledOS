package p000;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.p002v7.widget.RecyclerView;

/* renamed from: ejf */
/* compiled from: PG */
public final class ejf implements Parcelable.Creator {
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        int c = abj.m120c(parcel);
        ejo ejo = null;
        byte[] bArr = null;
        int[] iArr = null;
        String[] strArr = null;
        int[] iArr2 = null;
        byte[][] bArr2 = null;
        eup[] eupArr = null;
        euy[] euyArr = null;
        boolean z = true;
        while (parcel.dataPosition() < c) {
            int readInt = parcel.readInt();
            switch (abj.m111b(readInt)) {
                case RecyclerView.SCROLL_STATE_SETTLING:
                    ejo = (ejo) abj.m84a(parcel, readInt, ejo.CREATOR);
                    break;
                case 3:
                    bArr = abj.m130j(parcel, readInt);
                    break;
                case 4:
                    iArr = abj.m132l(parcel, readInt);
                    break;
                case 5:
                    strArr = abj.m133m(parcel, readInt);
                    break;
                case 6:
                    iArr2 = abj.m132l(parcel, readInt);
                    break;
                case 7:
                    bArr2 = abj.m131k(parcel, readInt);
                    break;
                case 8:
                    z = abj.m124d(parcel, readInt);
                    break;
                case 9:
                    eupArr = (eup[]) abj.m119b(parcel, readInt, eup.CREATOR);
                    break;
                case 10:
                    euyArr = (euy[]) abj.m119b(parcel, readInt, euy.CREATOR);
                    break;
                default:
                    abj.m122c(parcel, readInt);
                    break;
            }
        }
        abj.m135o(parcel, c);
        return new eje(ejo, bArr, iArr, strArr, iArr2, bArr2, z, eupArr, euyArr);
    }

    public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
        return new eje[i];
    }
}
