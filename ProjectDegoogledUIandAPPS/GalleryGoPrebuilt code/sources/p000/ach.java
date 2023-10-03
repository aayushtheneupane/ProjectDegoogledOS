package p000;

import android.os.Parcel;
import android.os.Parcelable;

/* renamed from: ach */
/* compiled from: PG */
final class ach implements Parcelable.Creator {
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        return new aci(parcel);
    }

    public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
        return new aci[i];
    }
}
