package com.android.incallui.incall.protocol;

import android.os.Parcel;
import android.os.Parcelable;
import com.android.dialer.common.LogUtil;
import com.android.incallui.incall.protocol.AutoValue_SecondaryInfo;
import com.google.auto.value.AutoValue;
import java.util.Locale;

@AutoValue
public abstract class SecondaryInfo implements Parcelable {
    public static final Parcelable.Creator<SecondaryInfo> CREATOR = new Parcelable.Creator<SecondaryInfo>() {
        public Object createFromParcel(Parcel parcel) {
            Builder builder = SecondaryInfo.builder();
            boolean z = true;
            builder.setShouldShow(parcel.readByte() != 0);
            builder.setName(parcel.readString());
            builder.setNameIsNumber(parcel.readByte() != 0);
            builder.setLabel(parcel.readString());
            builder.setProviderLabel(parcel.readString());
            builder.setIsConference(parcel.readByte() != 0);
            builder.setIsVideoCall(parcel.readByte() != 0);
            if (parcel.readByte() == 0) {
                z = false;
            }
            builder.setIsFullscreen(z);
            return builder.build();
        }

        public Object[] newArray(int i) {
            return new SecondaryInfo[i];
        }
    };

    public static abstract class Builder {
        public abstract SecondaryInfo build();

        public abstract Builder setIsConference(boolean z);

        public abstract Builder setIsFullscreen(boolean z);

        public abstract Builder setIsVideoCall(boolean z);

        public abstract Builder setLabel(String str);

        public abstract Builder setName(String str);

        public abstract Builder setNameIsNumber(boolean z);

        public abstract Builder setProviderLabel(String str);

        public abstract Builder setShouldShow(boolean z);
    }

    public static Builder builder() {
        AutoValue_SecondaryInfo.Builder builder = new AutoValue_SecondaryInfo.Builder();
        builder.setShouldShow(false);
        builder.setNameIsNumber(false);
        builder.setIsConference(false);
        builder.setIsVideoCall(false);
        builder.setIsFullscreen(false);
        return builder;
    }

    public int describeContents() {
        return 0;
    }

    public abstract boolean isConference();

    public abstract boolean isFullscreen();

    public abstract boolean isVideoCall();

    public abstract String label();

    public abstract String name();

    public abstract boolean nameIsNumber();

    public abstract String providerLabel();

    public abstract boolean shouldShow();

    public String toString() {
        return String.format(Locale.US, "SecondaryInfo, show: %b, name: %s, label: %s, providerLabel: %s", new Object[]{Boolean.valueOf(shouldShow()), LogUtil.sanitizePii(name()), label(), providerLabel()});
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeByte(shouldShow() ? (byte) 1 : 0);
        parcel.writeString(name());
        parcel.writeByte(nameIsNumber() ? (byte) 1 : 0);
        parcel.writeString(label());
        parcel.writeString(providerLabel());
        parcel.writeByte(isConference() ? (byte) 1 : 0);
        parcel.writeByte(isVideoCall() ? (byte) 1 : 0);
        parcel.writeByte(isFullscreen() ? (byte) 1 : 0);
    }
}
