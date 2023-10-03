package p000;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* renamed from: frq */
/* compiled from: PG */
public final class frq implements Parcelable {
    public static final Parcelable.Creator CREATOR = new frp();

    /* renamed from: a */
    public final Map f10329a = new HashMap();

    /* renamed from: b */
    private final Class f10330b;

    public final int describeContents() {
        return 0;
    }

    public frq(Parcel parcel) {
        try {
            Class<?> cls = Class.forName(parcel.readString());
            this.f10330b = cls;
            ClassLoader classLoader = cls.getClassLoader();
            int readInt = parcel.readInt();
            for (int i = 0; i < readInt; i++) {
                int readInt2 = parcel.readInt();
                int readInt3 = parcel.readInt();
                ArrayList arrayList = new ArrayList(readInt3);
                for (int i2 = 0; i2 < readInt3; i2++) {
                    arrayList.add(parcel.readParcelable(classLoader));
                }
                this.f10329a.put(Integer.valueOf(readInt2), arrayList);
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public frq(Class cls) {
        this.f10330b = cls;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f10330b.getName());
        parcel.writeInt(this.f10329a.size());
        for (Integer num : this.f10329a.keySet()) {
            parcel.writeInt(num.intValue());
            List<Parcelable> list = (List) this.f10329a.get(num);
            parcel.writeInt(list.size());
            for (Parcelable writeParcelable : list) {
                parcel.writeParcelable(writeParcelable, i);
            }
        }
    }
}
