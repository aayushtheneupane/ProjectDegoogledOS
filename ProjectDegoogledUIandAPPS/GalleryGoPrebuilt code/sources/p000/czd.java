package p000;

import android.os.Parcel;
import android.os.Parcelable;

/* renamed from: czd */
/* compiled from: PG */
public enum czd implements Parcelable {
    SUCCESS,
    CANCELLED,
    DELETION_ALREADY_RUNNING;
    
    public static final Parcelable.Creator CREATOR = null;

    static {
        CREATOR = new czc();
    }

    public final int describeContents() {
        return 0;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(ordinal());
    }
}
