package com.google.android.gms.googlehelp.internal.common;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.ReflectedParcelable;

/* compiled from: PG */
public class TogglingData extends eqv implements ReflectedParcelable {
    public static final Parcelable.Creator CREATOR = new eud();

    /* renamed from: a */
    public String f5105a;

    /* renamed from: b */
    private String f5106b;

    /* renamed from: c */
    private String f5107c;

    private TogglingData() {
    }

    public TogglingData(String str, String str2, String str3) {
        this.f5106b = str;
        this.f5107c = str2;
        this.f5105a = str3;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int a = abj.m82a(parcel);
        abj.m98a(parcel, 2, this.f5106b);
        abj.m98a(parcel, 3, this.f5107c);
        abj.m98a(parcel, 4, this.f5105a);
        abj.m92a(parcel, a);
    }
}
