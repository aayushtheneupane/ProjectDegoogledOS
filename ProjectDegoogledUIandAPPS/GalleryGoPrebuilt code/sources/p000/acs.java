package p000;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/* renamed from: acs */
/* compiled from: PG */
public final class acs extends acx {
    public static final Parcelable.Creator CREATOR = new acr();

    /* renamed from: a */
    public Set f190a;

    public acs(Parcel parcel) {
        super(parcel);
        int readInt = parcel.readInt();
        this.f190a = new HashSet();
        String[] strArr = new String[readInt];
        parcel.readStringArray(strArr);
        Collections.addAll(this.f190a, strArr);
    }

    public acs(Parcelable parcelable) {
        super(parcelable);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeInt(this.f190a.size());
        Set set = this.f190a;
        parcel.writeStringArray((String[]) set.toArray(new String[set.size()]));
    }
}
