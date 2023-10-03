package p000;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.apps.photosgo.R;

/* renamed from: cnf */
/* compiled from: PG */
public enum cnf implements Parcelable {
    SLIDE_UP(R.anim.fade_up_in, R.anim.fade_out_short, R.anim.fade_in, R.anim.fade_out_short),
    DEFAULT(R.anim.fade_in, R.anim.fade_out_short, R.anim.fade_in, R.anim.fade_out_short),
    NONE(R.anim.no_animation, R.anim.no_animation, R.anim.no_animation, R.anim.no_animation);
    
    public static final Parcelable.Creator CREATOR = null;

    /* renamed from: d */
    public final int f4724d;

    /* renamed from: e */
    public final int f4725e;

    /* renamed from: f */
    public final int f4726f;

    /* renamed from: g */
    public final int f4727g;

    static {
        CREATOR = new cne();
    }

    public final int describeContents() {
        return 0;
    }

    private cnf(int i, int i2, int i3, int i4) {
        this.f4724d = i;
        this.f4725e = i2;
        this.f4726f = i3;
        this.f4727g = i4;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(ordinal());
    }
}
