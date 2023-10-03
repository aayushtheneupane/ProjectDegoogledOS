package p000;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.api.Scope;

/* renamed from: elc */
/* compiled from: PG */
public final class elc implements Parcelable.Creator {
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        int c = abj.m120c(parcel);
        int i = 0;
        String str = null;
        while (parcel.dataPosition() < c) {
            int readInt = parcel.readInt();
            int b = abj.m111b(readInt);
            if (b == 1) {
                i = abj.m125e(parcel, readInt);
            } else if (b != 2) {
                abj.m122c(parcel, readInt);
            } else {
                str = abj.m127g(parcel, readInt);
            }
        }
        abj.m135o(parcel, c);
        return new Scope(i, str);
    }

    public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
        return new Scope[i];
    }
}
