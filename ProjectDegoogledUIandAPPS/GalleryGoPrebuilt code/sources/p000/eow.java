package p000;

import android.database.CursorWindow;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.data.DataHolder;

/* renamed from: eow */
/* compiled from: PG */
public final class eow implements Parcelable.Creator {
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        int c = abj.m120c(parcel);
        int i = 0;
        String[] strArr = null;
        CursorWindow[] cursorWindowArr = null;
        Bundle bundle = null;
        int i2 = 0;
        int i3 = 0;
        while (parcel.dataPosition() < c) {
            int readInt = parcel.readInt();
            int b = abj.m111b(readInt);
            if (b == 1) {
                strArr = abj.m133m(parcel, readInt);
            } else if (b == 2) {
                cursorWindowArr = (CursorWindow[]) abj.m119b(parcel, readInt, CursorWindow.CREATOR);
            } else if (b == 3) {
                i3 = abj.m125e(parcel, readInt);
            } else if (b == 4) {
                bundle = abj.m129i(parcel, readInt);
            } else if (b != 1000) {
                abj.m122c(parcel, readInt);
            } else {
                i2 = abj.m125e(parcel, readInt);
            }
        }
        abj.m135o(parcel, c);
        DataHolder dataHolder = new DataHolder(i2, strArr, cursorWindowArr, i3, bundle);
        dataHolder.f5003b = new Bundle();
        int i4 = 0;
        while (true) {
            String[] strArr2 = dataHolder.f5002a;
            if (i4 >= strArr2.length) {
                break;
            }
            dataHolder.f5003b.putInt(strArr2[i4], i4);
            i4++;
        }
        dataHolder.f5005d = new int[dataHolder.f5004c.length];
        int i5 = 0;
        while (true) {
            CursorWindow[] cursorWindowArr2 = dataHolder.f5004c;
            if (i >= cursorWindowArr2.length) {
                return dataHolder;
            }
            dataHolder.f5005d[i] = i5;
            i5 += dataHolder.f5004c[i].getNumRows() - (i5 - cursorWindowArr2[i].getStartPosition());
            i++;
        }
    }

    public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
        return new DataHolder[i];
    }
}
