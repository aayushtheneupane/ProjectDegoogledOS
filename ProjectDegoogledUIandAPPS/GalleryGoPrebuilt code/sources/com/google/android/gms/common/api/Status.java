package com.google.android.gms.common.api;

import android.app.PendingIntent;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.ReflectedParcelable;
import java.util.Arrays;

/* compiled from: PG */
public final class Status extends eqv implements ReflectedParcelable, ela {
    public static final Parcelable.Creator CREATOR = new eld();

    /* renamed from: a */
    public static final Status f4972a = new Status(0);

    /* renamed from: b */
    public static final Status f4973b = new Status(14);

    /* renamed from: c */
    public static final Status f4974c = new Status(8);

    /* renamed from: d */
    public static final Status f4975d = new Status(15);

    /* renamed from: e */
    public static final Status f4976e = new Status(16);

    /* renamed from: f */
    public final int f4977f;

    /* renamed from: g */
    public final String f4978g;

    /* renamed from: h */
    public final PendingIntent f4979h;

    /* renamed from: i */
    private final int f4980i;

    static {
        new Status(17);
        new Status(18);
    }

    /* renamed from: a */
    public final Status mo3498a() {
        return this;
    }

    /* renamed from: b */
    public final boolean mo3499b() {
        return this.f4977f <= 0;
    }

    public Status(int i) {
        this(i, (String) null);
    }

    public Status(int i, int i2, String str, PendingIntent pendingIntent) {
        this.f4980i = i;
        this.f4977f = i2;
        this.f4978g = str;
        this.f4979h = pendingIntent;
    }

    public Status(int i, String str) {
        this(1, i, str, (PendingIntent) null);
    }

    public Status(int i, String str, byte[] bArr) {
        this(1, i, str, (PendingIntent) null);
    }

    public final boolean equals(Object obj) {
        if (obj instanceof Status) {
            Status status = (Status) obj;
            if (this.f4980i != status.f4980i || this.f4977f != status.f4977f || !C0652xy.m16068a((Object) this.f4978g, (Object) status.f4978g) || !C0652xy.m16068a((Object) this.f4979h, (Object) status.f4979h)) {
                return false;
            }
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(this.f4980i), Integer.valueOf(this.f4977f), this.f4978g, this.f4979h});
    }

    public final String toString() {
        eqo a = C0652xy.m16063a((Object) this);
        String str = this.f4978g;
        if (str == null) {
            str = eod.m7920a(this.f4977f);
        }
        a.mo5163a("statusCode", str);
        a.mo5163a("resolution", this.f4979h);
        return a.toString();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int a = abj.m82a(parcel);
        abj.m114b(parcel, 1, this.f4977f);
        abj.m98a(parcel, 2, this.f4978g);
        abj.m97a(parcel, 3, (Parcelable) this.f4979h, i);
        abj.m114b(parcel, 1000, this.f4980i);
        abj.m92a(parcel, a);
    }
}
