package p000;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;

/* renamed from: bin */
/* compiled from: PG */
public final class bin {
    static {
        bin.class.getClassLoader();
    }

    private bin() {
    }

    /* renamed from: a */
    public static boolean m2621a(Parcel parcel) {
        return parcel.readInt() != 0;
    }

    /* renamed from: a */
    public static Parcelable m2617a(Parcel parcel, Parcelable.Creator creator) {
        if (parcel.readInt() != 0) {
            return (Parcelable) creator.createFromParcel(parcel);
        }
        return null;
    }

    /* renamed from: a */
    public static void m2620a(Parcel parcel, boolean z) {
        parcel.writeInt(z ? 1 : 0);
    }

    /* renamed from: a */
    public static void m2619a(Parcel parcel, Parcelable parcelable) {
        if (parcelable != null) {
            parcel.writeInt(1);
            parcelable.writeToParcel(parcel, 0);
            return;
        }
        parcel.writeInt(0);
    }

    /* renamed from: a */
    public static void m2618a(Parcel parcel, IInterface iInterface) {
        if (iInterface != null) {
            parcel.writeStrongBinder(iInterface.asBinder());
        } else {
            parcel.writeStrongBinder((IBinder) null);
        }
    }
}
