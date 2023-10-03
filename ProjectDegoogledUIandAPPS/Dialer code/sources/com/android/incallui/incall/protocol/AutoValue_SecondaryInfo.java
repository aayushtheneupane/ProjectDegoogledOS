package com.android.incallui.incall.protocol;

import com.android.incallui.incall.protocol.SecondaryInfo;
import com.android.tools.p006r8.GeneratedOutlineSupport;

final class AutoValue_SecondaryInfo extends SecondaryInfo {
    private final boolean isConference;
    private final boolean isFullscreen;
    private final boolean isVideoCall;
    private final String label;
    private final String name;
    private final boolean nameIsNumber;
    private final String providerLabel;
    private final boolean shouldShow;

    static final class Builder extends SecondaryInfo.Builder {
        private Boolean isConference;
        private Boolean isFullscreen;
        private Boolean isVideoCall;
        private String label;
        private String name;
        private Boolean nameIsNumber;
        private String providerLabel;
        private Boolean shouldShow;

        Builder() {
        }

        public SecondaryInfo build() {
            String str = "";
            if (this.shouldShow == null) {
                str = GeneratedOutlineSupport.outline8(str, " shouldShow");
            }
            if (this.nameIsNumber == null) {
                str = GeneratedOutlineSupport.outline8(str, " nameIsNumber");
            }
            if (this.isConference == null) {
                str = GeneratedOutlineSupport.outline8(str, " isConference");
            }
            if (this.isVideoCall == null) {
                str = GeneratedOutlineSupport.outline8(str, " isVideoCall");
            }
            if (this.isFullscreen == null) {
                str = GeneratedOutlineSupport.outline8(str, " isFullscreen");
            }
            if (str.isEmpty()) {
                return new AutoValue_SecondaryInfo(this.shouldShow.booleanValue(), this.name, this.nameIsNumber.booleanValue(), this.label, this.providerLabel, this.isConference.booleanValue(), this.isVideoCall.booleanValue(), this.isFullscreen.booleanValue(), (C07221) null);
            }
            throw new IllegalStateException(GeneratedOutlineSupport.outline8("Missing required properties:", str));
        }

        public SecondaryInfo.Builder setIsConference(boolean z) {
            this.isConference = Boolean.valueOf(z);
            return this;
        }

        public SecondaryInfo.Builder setIsFullscreen(boolean z) {
            this.isFullscreen = Boolean.valueOf(z);
            return this;
        }

        public SecondaryInfo.Builder setIsVideoCall(boolean z) {
            this.isVideoCall = Boolean.valueOf(z);
            return this;
        }

        public SecondaryInfo.Builder setLabel(String str) {
            this.label = str;
            return this;
        }

        public SecondaryInfo.Builder setName(String str) {
            this.name = str;
            return this;
        }

        public SecondaryInfo.Builder setNameIsNumber(boolean z) {
            this.nameIsNumber = Boolean.valueOf(z);
            return this;
        }

        public SecondaryInfo.Builder setProviderLabel(String str) {
            this.providerLabel = str;
            return this;
        }

        public SecondaryInfo.Builder setShouldShow(boolean z) {
            this.shouldShow = Boolean.valueOf(z);
            return this;
        }
    }

    /* synthetic */ AutoValue_SecondaryInfo(boolean z, String str, boolean z2, String str2, String str3, boolean z3, boolean z4, boolean z5, C07221 r9) {
        this.shouldShow = z;
        this.name = str;
        this.nameIsNumber = z2;
        this.label = str2;
        this.providerLabel = str3;
        this.isConference = z3;
        this.isVideoCall = z4;
        this.isFullscreen = z5;
    }

    public boolean equals(Object obj) {
        String str;
        String str2;
        String str3;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof SecondaryInfo)) {
            return false;
        }
        SecondaryInfo secondaryInfo = (SecondaryInfo) obj;
        if (this.shouldShow == secondaryInfo.shouldShow() && ((str = this.name) != null ? str.equals(secondaryInfo.name()) : secondaryInfo.name() == null) && this.nameIsNumber == secondaryInfo.nameIsNumber() && ((str2 = this.label) != null ? str2.equals(secondaryInfo.label()) : secondaryInfo.label() == null) && ((str3 = this.providerLabel) != null ? str3.equals(secondaryInfo.providerLabel()) : secondaryInfo.providerLabel() == null) && this.isConference == secondaryInfo.isConference() && this.isVideoCall == secondaryInfo.isVideoCall() && this.isFullscreen == secondaryInfo.isFullscreen()) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int i = 1231;
        int i2 = ((this.shouldShow ? 1231 : 1237) ^ 1000003) * 1000003;
        String str = this.name;
        int i3 = 0;
        int hashCode = (((i2 ^ (str == null ? 0 : str.hashCode())) * 1000003) ^ (this.nameIsNumber ? 1231 : 1237)) * 1000003;
        String str2 = this.label;
        int hashCode2 = (hashCode ^ (str2 == null ? 0 : str2.hashCode())) * 1000003;
        String str3 = this.providerLabel;
        if (str3 != null) {
            i3 = str3.hashCode();
        }
        int i4 = (((((hashCode2 ^ i3) * 1000003) ^ (this.isConference ? 1231 : 1237)) * 1000003) ^ (this.isVideoCall ? 1231 : 1237)) * 1000003;
        if (!this.isFullscreen) {
            i = 1237;
        }
        return i4 ^ i;
    }

    public boolean isConference() {
        return this.isConference;
    }

    public boolean isFullscreen() {
        return this.isFullscreen;
    }

    public boolean isVideoCall() {
        return this.isVideoCall;
    }

    public String label() {
        return this.label;
    }

    public String name() {
        return this.name;
    }

    public boolean nameIsNumber() {
        return this.nameIsNumber;
    }

    public String providerLabel() {
        return this.providerLabel;
    }

    public boolean shouldShow() {
        return this.shouldShow;
    }
}
