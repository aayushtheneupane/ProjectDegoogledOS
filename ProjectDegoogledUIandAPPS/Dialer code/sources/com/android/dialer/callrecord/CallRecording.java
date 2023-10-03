package com.android.dialer.callrecord;

import android.os.Environment;
import android.os.Parcel;
import android.os.Parcelable;
import com.android.tools.p006r8.GeneratedOutlineSupport;
import java.io.File;

public final class CallRecording implements Parcelable {
    public static final Parcelable.Creator<CallRecording> CREATOR = new Parcelable.Creator<CallRecording>() {
        public Object createFromParcel(Parcel parcel) {
            return new CallRecording(parcel);
        }

        public Object[] newArray(int i) {
            return new CallRecording[i];
        }
    };
    public long creationTime;
    public String fileName;
    public String phoneNumber;
    public long startRecordingTime;

    public CallRecording(String str, long j, String str2, long j2) {
        this.phoneNumber = str;
        this.creationTime = j;
        this.fileName = str2;
        this.startRecordingTime = j2;
    }

    public int describeContents() {
        return 0;
    }

    public File getFile() {
        return new File(Environment.getExternalStoragePublicDirectory("CallRecordings"), this.fileName);
    }

    public String toString() {
        StringBuilder outline13 = GeneratedOutlineSupport.outline13("phoneNumber=");
        outline13.append(this.phoneNumber);
        outline13.append(", creationTime=");
        outline13.append(this.creationTime);
        outline13.append(", fileName=");
        outline13.append(this.fileName);
        outline13.append(", startRecordingTime=");
        outline13.append(this.startRecordingTime);
        return outline13.toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.phoneNumber);
        parcel.writeLong(this.creationTime);
        parcel.writeString(this.fileName);
        parcel.writeLong(this.startRecordingTime);
    }

    public CallRecording(Parcel parcel) {
        this.phoneNumber = parcel.readString();
        this.creationTime = parcel.readLong();
        this.fileName = parcel.readString();
        this.startRecordingTime = parcel.readLong();
    }
}
