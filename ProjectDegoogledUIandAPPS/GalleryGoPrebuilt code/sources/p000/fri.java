package p000;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;

/* renamed from: fri */
/* compiled from: PG */
public final class fri implements Parcelable {
    public static final Parcelable.Creator CREATOR = new frh();

    /* renamed from: a */
    public final int f10317a;

    /* renamed from: b */
    public final int f10318b;

    /* renamed from: c */
    public final Intent f10319c;

    public final int describeContents() {
        return 0;
    }

    public fri(int i, int i2, Intent intent) {
        this.f10317a = i;
        this.f10318b = i2;
        this.f10319c = intent;
    }

    public /* synthetic */ fri(Parcel parcel) {
        this.f10317a = parcel.readInt();
        this.f10318b = parcel.readInt();
        this.f10319c = (Intent) parcel.readParcelable(Intent.class.getClassLoader());
    }

    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.f10317a);
        parcel.writeInt(this.f10318b);
        parcel.writeParcelable(this.f10319c, i);
    }
}
