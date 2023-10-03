package p000;

import android.os.Parcel;
import android.os.Parcelable;

/* renamed from: gkn */
/* compiled from: PG */
public abstract class gkn implements Parcelable {
    public static final Parcelable.Creator CREATOR = new gkm();

    /* renamed from: a */
    public abstract int mo6807a();

    public final int describeContents() {
        return 0;
    }

    /* renamed from: a */
    public static gkn m10445a(int i, gtf gtf) {
        boolean z;
        ife.m12898e((Object) gtf);
        if (i >= -1) {
            z = true;
        } else {
            z = false;
        }
        ife.m12876b(z, (Object) "Invalid AccountId");
        return new gkp(i);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(mo6807a());
    }
}
