package com.google.android.gms.common.data;

import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable;
import com.google.android.gms.common.internal.ReflectedParcelable;
import java.io.File;

/* compiled from: PG */
public class BitmapTeleporter extends eqv implements ReflectedParcelable {
    public static final Parcelable.Creator CREATOR = new eou();

    /* renamed from: a */
    public File f4998a;

    /* renamed from: b */
    private final int f4999b;

    /* renamed from: c */
    private ParcelFileDescriptor f5000c;

    /* renamed from: d */
    private final int f5001d;

    public BitmapTeleporter(int i, ParcelFileDescriptor parcelFileDescriptor, int i2) {
        this.f4999b = i;
        this.f5000c = parcelFileDescriptor;
        this.f5001d = i2;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        if (this.f5000c != null) {
            int a = abj.m82a(parcel);
            abj.m114b(parcel, 1, this.f4999b);
            abj.m97a(parcel, 2, (Parcelable) this.f5000c, i | 1);
            abj.m114b(parcel, 3, this.f5001d);
            abj.m92a(parcel, a);
            this.f5000c = null;
            return;
        }
        throw null;
    }
}
