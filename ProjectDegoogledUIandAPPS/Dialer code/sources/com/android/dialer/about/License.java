package com.android.dialer.about;

import android.os.Parcel;
import android.os.Parcelable;

public final class License implements Comparable<License>, Parcelable {
    public static final Parcelable.Creator<License> CREATOR = new Parcelable.Creator<License>() {
        public Object createFromParcel(Parcel parcel) {
            return new License(parcel, (C02791) null);
        }

        public Object[] newArray(int i) {
            return new License[i];
        }
    };
    private final String libraryName;
    private final int licenseLength;
    private final long licenseOffset;

    private License(String str, long j, int i) {
        this.libraryName = str;
        this.licenseOffset = j;
        this.licenseLength = i;
    }

    static License create(String str, long j, int i) {
        return new License(str, j, i);
    }

    public int describeContents() {
        return 0;
    }

    /* access modifiers changed from: package-private */
    public String getLibraryName() {
        return this.libraryName;
    }

    /* access modifiers changed from: package-private */
    public int getLicenseLength() {
        return this.licenseLength;
    }

    /* access modifiers changed from: package-private */
    public long getLicenseOffset() {
        return this.licenseOffset;
    }

    public String toString() {
        return getLibraryName();
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.libraryName);
        parcel.writeLong(this.licenseOffset);
        parcel.writeInt(this.licenseLength);
    }

    public int compareTo(License license) {
        return this.libraryName.compareToIgnoreCase(license.getLibraryName());
    }

    /* synthetic */ License(Parcel parcel, C02791 r4) {
        this.libraryName = parcel.readString();
        this.licenseOffset = parcel.readLong();
        this.licenseLength = parcel.readInt();
    }
}
