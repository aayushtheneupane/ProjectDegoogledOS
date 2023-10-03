package com.google.android.apps.photosgo.editor;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

/* compiled from: PG */
public final class PresetThumbnail implements Parcelable {
    public static final Parcelable.Creator CREATOR = new bwz();

    /* renamed from: a */
    public final Bitmap f4832a;

    /* renamed from: b */
    public final car f4833b;

    /* renamed from: c */
    private final int f4834c;

    private PresetThumbnail(Bitmap bitmap, int i, int i2) {
        this.f4832a = bitmap;
        this.f4833b = car.m3967a(i);
        this.f4834c = i2;
    }

    public /* synthetic */ PresetThumbnail(Parcel parcel) {
        this.f4832a = (Bitmap) parcel.readParcelable(Bitmap.class.getClassLoader());
        this.f4833b = car.m3967a(parcel.readInt());
        this.f4834c = parcel.readInt();
    }

    public final int describeContents() {
        return this.f4832a.describeContents();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.f4832a, i);
        parcel.writeInt(this.f4833b.ordinal());
        parcel.writeInt(this.f4834c);
    }
}
