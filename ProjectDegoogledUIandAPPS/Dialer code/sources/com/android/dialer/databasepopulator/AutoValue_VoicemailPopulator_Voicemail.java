package com.android.dialer.databasepopulator;

import com.android.dialer.databasepopulator.VoicemailPopulator;
import com.android.tools.p006r8.GeneratedOutlineSupport;

final class AutoValue_VoicemailPopulator_Voicemail extends VoicemailPopulator.Voicemail {
    private final long durationSeconds;
    private final boolean isRead;
    private final String phoneAccountComponentName;
    private final String phoneNumber;
    private final long timeMillis;
    private final String transcription;

    static final class Builder extends VoicemailPopulator.Voicemail.Builder {
        private Long durationSeconds;
        private Boolean isRead;
        private String phoneAccountComponentName;
        private String phoneNumber;
        private Long timeMillis;
        private String transcription;

        Builder() {
        }

        public VoicemailPopulator.Voicemail build() {
            String str = "";
            if (this.phoneNumber == null) {
                str = GeneratedOutlineSupport.outline8(str, " phoneNumber");
            }
            if (this.transcription == null) {
                str = GeneratedOutlineSupport.outline8(str, " transcription");
            }
            if (this.durationSeconds == null) {
                str = GeneratedOutlineSupport.outline8(str, " durationSeconds");
            }
            if (this.timeMillis == null) {
                str = GeneratedOutlineSupport.outline8(str, " timeMillis");
            }
            if (this.isRead == null) {
                str = GeneratedOutlineSupport.outline8(str, " isRead");
            }
            if (this.phoneAccountComponentName == null) {
                str = GeneratedOutlineSupport.outline8(str, " phoneAccountComponentName");
            }
            if (str.isEmpty()) {
                return new AutoValue_VoicemailPopulator_Voicemail(this.phoneNumber, this.transcription, this.durationSeconds.longValue(), this.timeMillis.longValue(), this.isRead.booleanValue(), this.phoneAccountComponentName, (C04701) null);
            }
            throw new IllegalStateException(GeneratedOutlineSupport.outline8("Missing required properties:", str));
        }

        public VoicemailPopulator.Voicemail.Builder setDurationSeconds(long j) {
            this.durationSeconds = Long.valueOf(j);
            return this;
        }

        public VoicemailPopulator.Voicemail.Builder setIsRead(boolean z) {
            this.isRead = Boolean.valueOf(z);
            return this;
        }

        public VoicemailPopulator.Voicemail.Builder setPhoneAccountComponentName(String str) {
            if (str != null) {
                this.phoneAccountComponentName = str;
                return this;
            }
            throw new NullPointerException("Null phoneAccountComponentName");
        }

        public VoicemailPopulator.Voicemail.Builder setPhoneNumber(String str) {
            if (str != null) {
                this.phoneNumber = str;
                return this;
            }
            throw new NullPointerException("Null phoneNumber");
        }

        public VoicemailPopulator.Voicemail.Builder setTimeMillis(long j) {
            this.timeMillis = Long.valueOf(j);
            return this;
        }

        public VoicemailPopulator.Voicemail.Builder setTranscription(String str) {
            if (str != null) {
                this.transcription = str;
                return this;
            }
            throw new NullPointerException("Null transcription");
        }
    }

    /* synthetic */ AutoValue_VoicemailPopulator_Voicemail(String str, String str2, long j, long j2, boolean z, String str3, C04701 r9) {
        this.phoneNumber = str;
        this.transcription = str2;
        this.durationSeconds = j;
        this.timeMillis = j2;
        this.isRead = z;
        this.phoneAccountComponentName = str3;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof VoicemailPopulator.Voicemail)) {
            return false;
        }
        VoicemailPopulator.Voicemail voicemail = (VoicemailPopulator.Voicemail) obj;
        if (!this.phoneNumber.equals(voicemail.getPhoneNumber()) || !this.transcription.equals(voicemail.getTranscription()) || this.durationSeconds != voicemail.getDurationSeconds() || this.timeMillis != voicemail.getTimeMillis() || this.isRead != voicemail.getIsRead() || !this.phoneAccountComponentName.equals(voicemail.getPhoneAccountComponentName())) {
            return false;
        }
        return true;
    }

    public long getDurationSeconds() {
        return this.durationSeconds;
    }

    public boolean getIsRead() {
        return this.isRead;
    }

    public String getPhoneAccountComponentName() {
        return this.phoneAccountComponentName;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public long getTimeMillis() {
        return this.timeMillis;
    }

    public String getTranscription() {
        return this.transcription;
    }

    public int hashCode() {
        long j = this.durationSeconds;
        long j2 = this.timeMillis;
        return this.phoneAccountComponentName.hashCode() ^ ((((((((((this.phoneNumber.hashCode() ^ 1000003) * 1000003) ^ this.transcription.hashCode()) * 1000003) ^ ((int) (j ^ (j >>> 32)))) * 1000003) ^ ((int) (j2 ^ (j2 >>> 32)))) * 1000003) ^ (this.isRead ? 1231 : 1237)) * 1000003);
    }

    public String toString() {
        StringBuilder outline13 = GeneratedOutlineSupport.outline13("Voicemail{phoneNumber=");
        outline13.append(this.phoneNumber);
        outline13.append(", transcription=");
        outline13.append(this.transcription);
        outline13.append(", durationSeconds=");
        outline13.append(this.durationSeconds);
        outline13.append(", timeMillis=");
        outline13.append(this.timeMillis);
        outline13.append(", isRead=");
        outline13.append(this.isRead);
        outline13.append(", phoneAccountComponentName=");
        return GeneratedOutlineSupport.outline12(outline13, this.phoneAccountComponentName, "}");
    }
}
