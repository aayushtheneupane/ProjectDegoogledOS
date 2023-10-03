package com.android.messaging.sms;

import android.os.Parcel;
import android.os.Parcelable;

public class DatabaseMessages$LocalDatabaseMessage extends C1017m implements Parcelable {
    public static final Parcelable.Creator CREATOR = new C1018n();

    /* renamed from: Ka */
    private final String f1462Ka;

    /* renamed from: WD */
    private final long f1463WD;
    private final int mProtocol;
    private final long mTimestamp;
    private final String mUri;

    public DatabaseMessages$LocalDatabaseMessage(long j, int i, String str, long j2, String str2) {
        this.f1463WD = j;
        this.mProtocol = i;
        this.mUri = str;
        this.mTimestamp = j2;
        this.f1462Ka = str2;
    }

    /* renamed from: Ue */
    public String mo6786Ue() {
        return this.f1462Ka;
    }

    public int describeContents() {
        return 0;
    }

    public long getLocalId() {
        return this.f1463WD;
    }

    public int getProtocol() {
        return this.mProtocol;
    }

    public String getUri() {
        return this.mUri;
    }

    /* renamed from: hi */
    public long mo6791hi() {
        return this.mTimestamp;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mUri);
        parcel.writeString(this.f1462Ka);
        parcel.writeLong(this.f1463WD);
        parcel.writeLong(this.mTimestamp);
        parcel.writeInt(this.mProtocol);
    }

    /* synthetic */ DatabaseMessages$LocalDatabaseMessage(Parcel parcel, C1016l lVar) {
        this.mUri = parcel.readString();
        this.f1462Ka = parcel.readString();
        this.f1463WD = parcel.readLong();
        this.mTimestamp = parcel.readLong();
        this.mProtocol = parcel.readInt();
    }
}
