package com.android.voicemail.impl;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.telecom.PhoneAccountHandle;
import android.text.TextUtils;

public class Voicemail implements Parcelable {
    public static final Parcelable.Creator<Voicemail> CREATOR = new Parcelable.Creator<Voicemail>() {
        public Object createFromParcel(Parcel parcel) {
            return new Voicemail(parcel, (C07681) null);
        }

        public Object[] newArray(int i) {
            return new Voicemail[i];
        }
    };
    private final Long duration;
    private final Boolean hasContent;

    /* renamed from: id */
    private final Long f45id;
    private final Boolean isRead;
    private final String number;
    private final PhoneAccountHandle phoneAccount;
    private final String providerData;
    private final String source;
    private final Long timestamp;
    private final String transcription;
    private final Uri uri;

    public static class Builder {
        private Long builderDuration;
        private boolean builderHasContent;
        private Long builderId;
        private Boolean builderIsRead;
        private String builderNumber;
        private PhoneAccountHandle builderPhoneAccount;
        private String builderSourceData;
        private String builderSourcePackage;
        private Long builderTimestamp;
        private String builderTranscription;
        private Uri builderUri;

        /* synthetic */ Builder(C07681 r1) {
        }

        public Voicemail build() {
            Long l = this.builderId;
            this.builderId = Long.valueOf(l == null ? -1 : l.longValue());
            Long l2 = this.builderTimestamp;
            long j = 0;
            this.builderTimestamp = Long.valueOf(l2 == null ? 0 : l2.longValue());
            Long l3 = this.builderDuration;
            if (l3 != null) {
                j = l3.longValue();
            }
            this.builderDuration = Long.valueOf(j);
            Boolean bool = this.builderIsRead;
            this.builderIsRead = Boolean.valueOf(bool == null ? false : bool.booleanValue());
            return new Voicemail(this.builderTimestamp, this.builderNumber, this.builderPhoneAccount, this.builderId, this.builderDuration, this.builderSourcePackage, this.builderSourceData, this.builderUri, this.builderIsRead, Boolean.valueOf(this.builderHasContent), this.builderTranscription, (C07681) null);
        }

        public Builder setDuration(long j) {
            this.builderDuration = Long.valueOf(j);
            return this;
        }

        public Builder setId(long j) {
            this.builderId = Long.valueOf(j);
            return this;
        }

        public Builder setIsRead(boolean z) {
            this.builderIsRead = Boolean.valueOf(z);
            return this;
        }

        public Builder setNumber(String str) {
            this.builderNumber = str;
            return this;
        }

        public Builder setPhoneAccount(PhoneAccountHandle phoneAccountHandle) {
            this.builderPhoneAccount = phoneAccountHandle;
            return this;
        }

        public Builder setSourceData(String str) {
            this.builderSourceData = str;
            return this;
        }

        public Builder setSourcePackage(String str) {
            this.builderSourcePackage = str;
            return this;
        }

        public Builder setTimestamp(long j) {
            this.builderTimestamp = Long.valueOf(j);
            return this;
        }

        public Builder setTranscription(String str) {
            this.builderTranscription = str;
            return this;
        }

        public Builder setUri(Uri uri) {
            this.builderUri = uri;
            return this;
        }
    }

    /* synthetic */ Voicemail(Long l, String str, PhoneAccountHandle phoneAccountHandle, Long l2, Long l3, String str2, String str3, Uri uri2, Boolean bool, Boolean bool2, String str4, C07681 r12) {
        this.timestamp = l;
        this.number = str;
        this.phoneAccount = phoneAccountHandle;
        this.f45id = l2;
        this.duration = l3;
        this.source = str2;
        this.providerData = str3;
        this.uri = uri2;
        this.isRead = bool;
        this.hasContent = bool2;
        this.transcription = str4;
    }

    public static Builder createForInsertion(long j, String str) {
        Builder builder = new Builder((C07681) null);
        builder.setNumber(str);
        builder.setTimestamp(j);
        return builder;
    }

    public static Builder createForUpdate(long j, String str) {
        Builder builder = new Builder((C07681) null);
        builder.setId(j);
        builder.setSourceData(str);
        return builder;
    }

    private static CharSequence readCharSequence(Parcel parcel) {
        return (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
    }

    public int describeContents() {
        return 0;
    }

    public long getDuration() {
        return this.duration.longValue();
    }

    public long getId() {
        return this.f45id.longValue();
    }

    public String getNumber() {
        return this.number;
    }

    public PhoneAccountHandle getPhoneAccount() {
        return this.phoneAccount;
    }

    public String getSourceData() {
        return this.providerData;
    }

    public String getSourcePackage() {
        return this.source;
    }

    public long getTimestampMillis() {
        return this.timestamp.longValue();
    }

    public String getTranscription() {
        return this.transcription;
    }

    public Uri getUri() {
        return this.uri;
    }

    public boolean isRead() {
        return this.isRead.booleanValue();
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(this.timestamp.longValue());
        TextUtils.writeToParcel(this.number, parcel, 0);
        if (this.phoneAccount == null) {
            parcel.writeInt(0);
        } else {
            parcel.writeInt(1);
            this.phoneAccount.writeToParcel(parcel, i);
        }
        parcel.writeLong(this.f45id.longValue());
        parcel.writeLong(this.duration.longValue());
        TextUtils.writeToParcel(this.source, parcel, 0);
        TextUtils.writeToParcel(this.providerData, parcel, 0);
        if (this.uri == null) {
            parcel.writeInt(0);
        } else {
            parcel.writeInt(1);
            this.uri.writeToParcel(parcel, i);
        }
        if (this.isRead.booleanValue()) {
            parcel.writeInt(1);
        } else {
            parcel.writeInt(0);
        }
        if (this.hasContent.booleanValue()) {
            parcel.writeInt(1);
        } else {
            parcel.writeInt(0);
        }
        TextUtils.writeToParcel(this.transcription, parcel, 0);
    }

    /* synthetic */ Voicemail(Parcel parcel, C07681 r5) {
        this.timestamp = Long.valueOf(parcel.readLong());
        this.number = (String) readCharSequence(parcel);
        if (parcel.readInt() > 0) {
            this.phoneAccount = (PhoneAccountHandle) PhoneAccountHandle.CREATOR.createFromParcel(parcel);
        } else {
            this.phoneAccount = null;
        }
        this.f45id = Long.valueOf(parcel.readLong());
        this.duration = Long.valueOf(parcel.readLong());
        this.source = (String) readCharSequence(parcel);
        this.providerData = (String) readCharSequence(parcel);
        if (parcel.readInt() > 0) {
            this.uri = (Uri) Uri.CREATOR.createFromParcel(parcel);
        } else {
            this.uri = null;
        }
        boolean z = true;
        this.isRead = Boolean.valueOf(parcel.readInt() > 0);
        this.hasContent = Boolean.valueOf(parcel.readInt() <= 0 ? false : z);
        this.transcription = (String) readCharSequence(parcel);
    }
}
