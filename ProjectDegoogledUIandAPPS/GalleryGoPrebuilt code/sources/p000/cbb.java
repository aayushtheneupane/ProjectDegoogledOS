package p000;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

/* renamed from: cbb */
/* compiled from: PG */
final class cbb implements Parcelable.Creator {
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        Bitmap bitmap = (Bitmap) Bitmap.CREATOR.createFromParcel(parcel);
        car a = car.m3968a((String) ife.m12898e((Object) parcel.readString()));
        boolean z = parcel.readInt() != 0;
        cbc e = cbd.m3991e();
        e.mo2983a(bitmap);
        e.mo2984a(a);
        e.mo2985a(z);
        return e.mo2982a();
    }

    public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
        return new cbd[i];
    }
}
