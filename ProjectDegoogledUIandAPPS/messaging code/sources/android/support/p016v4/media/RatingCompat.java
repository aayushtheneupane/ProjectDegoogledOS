package android.support.p016v4.media;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;
import p026b.p027a.p030b.p031a.C0632a;

@SuppressLint({"BanParcelableUsage"})
/* renamed from: android.support.v4.media.RatingCompat */
public final class RatingCompat implements Parcelable {
    public static final Parcelable.Creator CREATOR = new C0089s();

    /* renamed from: re */
    private final int f84re;

    /* renamed from: se */
    private final float f85se;

    RatingCompat(int i, float f) {
        this.f84re = i;
        this.f85se = f;
    }

    public int describeContents() {
        return this.f84re;
    }

    public String toString() {
        String str;
        StringBuilder Pa = C0632a.m1011Pa("Rating:style=");
        Pa.append(this.f84re);
        Pa.append(" rating=");
        float f = this.f85se;
        if (f < 0.0f) {
            str = "unrated";
        } else {
            str = String.valueOf(f);
        }
        Pa.append(str);
        return Pa.toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.f84re);
        parcel.writeFloat(this.f85se);
    }
}
