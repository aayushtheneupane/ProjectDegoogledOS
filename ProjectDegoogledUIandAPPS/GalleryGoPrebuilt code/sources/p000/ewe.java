package p000;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.api.Status;

/* renamed from: ewe */
/* compiled from: PG */
public final class ewe extends eqv implements ela {
    public static final Parcelable.Creator CREATOR = new ewf();

    /* renamed from: a */
    private final int f9129a;

    /* renamed from: b */
    private int f9130b;

    /* renamed from: c */
    private Intent f9131c;

    /* renamed from: a */
    public final Status mo3498a() {
        return this.f9130b == 0 ? Status.f4972a : Status.f4976e;
    }

    public ewe() {
        this(2, 0, (Intent) null);
    }

    public ewe(int i, int i2, Intent intent) {
        this.f9129a = i;
        this.f9130b = i2;
        this.f9131c = intent;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int a = abj.m82a(parcel);
        abj.m114b(parcel, 1, this.f9129a);
        abj.m114b(parcel, 2, this.f9130b);
        abj.m97a(parcel, 3, (Parcelable) this.f9131c, i);
        abj.m92a(parcel, a);
    }
}
