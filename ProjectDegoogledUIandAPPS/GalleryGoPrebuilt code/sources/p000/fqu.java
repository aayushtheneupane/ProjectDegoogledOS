package p000;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

/* renamed from: fqu */
/* compiled from: PG */
public final class fqu implements Parcelable {
    public static final Parcelable.Creator CREATOR = new fqt();

    /* renamed from: a */
    private final String f10292a;

    /* renamed from: b */
    private final boolean f10293b;

    /* renamed from: c */
    private final boolean f10294c;

    public final int describeContents() {
        return 0;
    }

    public fqu() {
        this.f10292a = null;
        this.f10293b = false;
        this.f10294c = false;
    }

    public /* synthetic */ fqu(Parcel parcel) {
        Bundle readBundle = parcel.readBundle();
        this.f10292a = readBundle.getString("account_name");
        this.f10293b = readBundle.getBoolean("original_size");
        this.f10294c = readBundle.getBoolean("use_data");
    }

    public final void writeToParcel(Parcel parcel, int i) {
        Bundle bundle = new Bundle();
        bundle.putString("account_name", this.f10292a);
        bundle.putBoolean("original_size", this.f10293b);
        bundle.putBoolean("use_data", this.f10294c);
        parcel.writeBundle(bundle);
    }
}
