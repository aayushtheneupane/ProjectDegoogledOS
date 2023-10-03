package p000;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/* renamed from: frt */
/* compiled from: PG */
public final class frt implements Parcelable {
    public static final Parcelable.Creator CREATOR = new frs();

    /* renamed from: a */
    public final Map f10331a = new HashMap();

    public final int describeContents() {
        return 0;
    }

    public frt() {
    }

    public frt(Parcel parcel) {
        int readInt = parcel.readInt();
        for (int i = 0; i < readInt; i++) {
            this.f10331a.put(Integer.valueOf(parcel.readInt()), Integer.valueOf(parcel.readInt()));
        }
    }

    /* renamed from: a */
    public final Integer mo6080a(Integer num) {
        return (Integer) this.f10331a.get(num);
    }

    /* renamed from: a */
    public final Set mo6081a() {
        return this.f10331a.keySet();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.f10331a.size());
        for (Map.Entry entry : this.f10331a.entrySet()) {
            parcel.writeInt(((Integer) entry.getKey()).intValue());
            parcel.writeInt(((Integer) entry.getValue()).intValue());
        }
    }
}
