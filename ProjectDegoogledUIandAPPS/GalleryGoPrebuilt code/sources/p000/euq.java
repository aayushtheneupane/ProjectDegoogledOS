package p000;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.p002v7.widget.RecyclerView;

/* renamed from: euq */
/* compiled from: PG */
public final class euq implements Parcelable.Creator {
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        int c = abj.m120c(parcel);
        String str = null;
        byte[] bArr = null;
        byte[][] bArr2 = null;
        byte[][] bArr3 = null;
        byte[][] bArr4 = null;
        byte[][] bArr5 = null;
        int[] iArr = null;
        byte[][] bArr6 = null;
        int[] iArr2 = null;
        while (parcel.dataPosition() < c) {
            int readInt = parcel.readInt();
            switch (abj.m111b(readInt)) {
                case RecyclerView.SCROLL_STATE_SETTLING:
                    str = abj.m127g(parcel, readInt);
                    break;
                case 3:
                    bArr = abj.m130j(parcel, readInt);
                    break;
                case 4:
                    bArr2 = abj.m131k(parcel, readInt);
                    break;
                case 5:
                    bArr3 = abj.m131k(parcel, readInt);
                    break;
                case 6:
                    bArr4 = abj.m131k(parcel, readInt);
                    break;
                case 7:
                    bArr5 = abj.m131k(parcel, readInt);
                    break;
                case 8:
                    iArr = abj.m132l(parcel, readInt);
                    break;
                case 9:
                    bArr6 = abj.m131k(parcel, readInt);
                    break;
                case 10:
                    iArr2 = abj.m132l(parcel, readInt);
                    break;
                default:
                    abj.m122c(parcel, readInt);
                    break;
            }
        }
        abj.m135o(parcel, c);
        return new eup(str, bArr, bArr2, bArr3, bArr4, bArr5, iArr, bArr6, iArr2);
    }

    public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
        return new eup[i];
    }
}
