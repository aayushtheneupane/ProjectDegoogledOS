package p000;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.p002v7.widget.RecyclerView;

/* renamed from: ejp */
/* compiled from: PG */
public final class ejp implements Parcelable.Creator {
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        Parcel parcel2 = parcel;
        int c = abj.m120c(parcel);
        String str = null;
        String str2 = null;
        String str3 = null;
        String str4 = null;
        Integer num = null;
        int i = 0;
        int i2 = 0;
        boolean z = true;
        boolean z2 = false;
        int i3 = 0;
        while (parcel.dataPosition() < c) {
            int readInt = parcel.readInt();
            switch (abj.m111b(readInt)) {
                case RecyclerView.SCROLL_STATE_SETTLING:
                    str = abj.m127g(parcel2, readInt);
                    break;
                case 3:
                    i = abj.m125e(parcel2, readInt);
                    break;
                case 4:
                    i2 = abj.m125e(parcel2, readInt);
                    break;
                case 5:
                    str2 = abj.m127g(parcel2, readInt);
                    break;
                case 6:
                    str3 = abj.m127g(parcel2, readInt);
                    break;
                case 7:
                    z = abj.m124d(parcel2, readInt);
                    break;
                case 8:
                    str4 = abj.m127g(parcel2, readInt);
                    break;
                case 9:
                    z2 = abj.m124d(parcel2, readInt);
                    break;
                case 10:
                    i3 = abj.m125e(parcel2, readInt);
                    break;
                case 11:
                    int b = abj.m113b(parcel2, readInt);
                    if (b == 0) {
                        num = null;
                        break;
                    } else if (b == 4) {
                        num = Integer.valueOf(parcel.readInt());
                        break;
                    } else {
                        String hexString = Integer.toHexString(b);
                        StringBuilder sb = new StringBuilder(String.valueOf(hexString).length() + 46);
                        sb.append("Expected size 4 got ");
                        sb.append(b);
                        sb.append(" (0x");
                        sb.append(hexString);
                        sb.append(")");
                        throw new eqw(sb.toString(), parcel2);
                    }
                default:
                    abj.m122c(parcel2, readInt);
                    break;
            }
        }
        abj.m135o(parcel2, c);
        return new ejo(str, i, i2, str2, str3, z, str4, z2, i3, num);
    }

    public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
        return new ejo[i];
    }
}
