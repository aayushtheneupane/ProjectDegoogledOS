package com.google.android.gms.common.api;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.google.android.gms.common.internal.ReflectedParcelable;

/* compiled from: PG */
public final class Scope extends eqv implements ReflectedParcelable {
    public static final Parcelable.Creator CREATOR = new elc();

    /* renamed from: a */
    private final int f4970a;

    /* renamed from: b */
    private final String f4971b;

    public final String toString() {
        return this.f4971b;
    }

    public Scope(int i, String str) {
        if (!TextUtils.isEmpty(str)) {
            this.f4970a = i;
            this.f4971b = str;
            return;
        }
        throw new IllegalArgumentException("scopeUri must not be null or empty");
    }

    public Scope(String str) {
        this(1, str);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Scope) {
            return this.f4971b.equals(((Scope) obj).f4971b);
        }
        return false;
    }

    public final int hashCode() {
        return this.f4971b.hashCode();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int a = abj.m82a(parcel);
        abj.m114b(parcel, 1, this.f4970a);
        abj.m98a(parcel, 2, this.f4971b);
        abj.m92a(parcel, a);
    }
}
