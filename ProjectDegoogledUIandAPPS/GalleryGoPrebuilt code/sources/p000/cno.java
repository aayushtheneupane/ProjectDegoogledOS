package p000;

import android.os.Parcel;
import android.os.Parcelable;

/* renamed from: cno */
/* compiled from: PG */
public final class cno implements Parcelable {
    public static final Parcelable.Creator CREATOR = new cnn();

    /* renamed from: a */
    public String f4738a;

    /* renamed from: b */
    public cnf f4739b;

    public final int describeContents() {
        return 0;
    }

    public cno(Parcel parcel) {
        this.f4738a = "";
        this.f4739b = cnf.NONE;
        String readString = parcel.readString();
        cnf cnf = (cnf) parcel.readParcelable(cnf.class.getClassLoader());
        if (cnf != null && readString != null) {
            this.f4738a = readString;
            this.f4739b = cnf;
        }
    }

    public cno(String str, cnf cnf) {
        this.f4738a = str;
        this.f4739b = cnf;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f4738a);
        parcel.writeParcelable(this.f4739b, 0);
    }
}
