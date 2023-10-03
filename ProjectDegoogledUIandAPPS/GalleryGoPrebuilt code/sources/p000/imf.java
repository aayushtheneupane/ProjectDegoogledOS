package p000;

import android.os.Parcel;
import android.os.Parcelable;

/* renamed from: imf */
/* compiled from: PG */
final class imf implements Parcelable.Creator {
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        byte[] bArr = new byte[parcel.readInt()];
        parcel.readByteArray(bArr);
        return new img(bArr, (ikf) null);
    }

    public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
        return new img[i];
    }
}
