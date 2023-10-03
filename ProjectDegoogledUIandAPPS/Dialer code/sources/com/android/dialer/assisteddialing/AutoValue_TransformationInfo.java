package com.android.dialer.assisteddialing;

import com.android.dialer.assisteddialing.TransformationInfo;
import com.android.tools.p006r8.GeneratedOutlineSupport;

final class AutoValue_TransformationInfo extends TransformationInfo {
    private final String originalNumber;
    private final String transformedNumber;
    private final int transformedNumberCountryCallingCode;
    private final String userHomeCountryCode;
    private final String userRoamingCountryCode;

    static final class Builder extends TransformationInfo.Builder {
        private String originalNumber;
        private String transformedNumber;
        private Integer transformedNumberCountryCallingCode;
        private String userHomeCountryCode;
        private String userRoamingCountryCode;

        Builder() {
        }

        public TransformationInfo build() {
            String str = "";
            if (this.originalNumber == null) {
                str = GeneratedOutlineSupport.outline8(str, " originalNumber");
            }
            if (this.transformedNumber == null) {
                str = GeneratedOutlineSupport.outline8(str, " transformedNumber");
            }
            if (this.userHomeCountryCode == null) {
                str = GeneratedOutlineSupport.outline8(str, " userHomeCountryCode");
            }
            if (this.userRoamingCountryCode == null) {
                str = GeneratedOutlineSupport.outline8(str, " userRoamingCountryCode");
            }
            if (this.transformedNumberCountryCallingCode == null) {
                str = GeneratedOutlineSupport.outline8(str, " transformedNumberCountryCallingCode");
            }
            if (str.isEmpty()) {
                return new AutoValue_TransformationInfo(this.originalNumber, this.transformedNumber, this.userHomeCountryCode, this.userRoamingCountryCode, this.transformedNumberCountryCallingCode.intValue(), (C03641) null);
            }
            throw new IllegalStateException(GeneratedOutlineSupport.outline8("Missing required properties:", str));
        }

        public TransformationInfo.Builder setOriginalNumber(String str) {
            if (str != null) {
                this.originalNumber = str;
                return this;
            }
            throw new NullPointerException("Null originalNumber");
        }

        public TransformationInfo.Builder setTransformedNumber(String str) {
            if (str != null) {
                this.transformedNumber = str;
                return this;
            }
            throw new NullPointerException("Null transformedNumber");
        }

        public TransformationInfo.Builder setTransformedNumberCountryCallingCode(int i) {
            this.transformedNumberCountryCallingCode = Integer.valueOf(i);
            return this;
        }

        public TransformationInfo.Builder setUserHomeCountryCode(String str) {
            if (str != null) {
                this.userHomeCountryCode = str;
                return this;
            }
            throw new NullPointerException("Null userHomeCountryCode");
        }

        public TransformationInfo.Builder setUserRoamingCountryCode(String str) {
            if (str != null) {
                this.userRoamingCountryCode = str;
                return this;
            }
            throw new NullPointerException("Null userRoamingCountryCode");
        }
    }

    /* synthetic */ AutoValue_TransformationInfo(String str, String str2, String str3, String str4, int i, C03641 r6) {
        this.originalNumber = str;
        this.transformedNumber = str2;
        this.userHomeCountryCode = str3;
        this.userRoamingCountryCode = str4;
        this.transformedNumberCountryCallingCode = i;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof TransformationInfo)) {
            return false;
        }
        TransformationInfo transformationInfo = (TransformationInfo) obj;
        if (this.originalNumber.equals(transformationInfo.originalNumber())) {
            AutoValue_TransformationInfo autoValue_TransformationInfo = (AutoValue_TransformationInfo) transformationInfo;
            if (this.transformedNumber.equals(autoValue_TransformationInfo.transformedNumber) && this.userHomeCountryCode.equals(autoValue_TransformationInfo.userHomeCountryCode) && this.userRoamingCountryCode.equals(transformationInfo.userRoamingCountryCode()) && this.transformedNumberCountryCallingCode == autoValue_TransformationInfo.transformedNumberCountryCallingCode) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        return this.transformedNumberCountryCallingCode ^ ((((((((this.originalNumber.hashCode() ^ 1000003) * 1000003) ^ this.transformedNumber.hashCode()) * 1000003) ^ this.userHomeCountryCode.hashCode()) * 1000003) ^ this.userRoamingCountryCode.hashCode()) * 1000003);
    }

    public String originalNumber() {
        return this.originalNumber;
    }

    public String toString() {
        StringBuilder outline13 = GeneratedOutlineSupport.outline13("TransformationInfo{originalNumber=");
        outline13.append(this.originalNumber);
        outline13.append(", transformedNumber=");
        outline13.append(this.transformedNumber);
        outline13.append(", userHomeCountryCode=");
        outline13.append(this.userHomeCountryCode);
        outline13.append(", userRoamingCountryCode=");
        outline13.append(this.userRoamingCountryCode);
        outline13.append(", transformedNumberCountryCallingCode=");
        outline13.append(this.transformedNumberCountryCallingCode);
        outline13.append("}");
        return outline13.toString();
    }

    public String transformedNumber() {
        return this.transformedNumber;
    }

    public int transformedNumberCountryCallingCode() {
        return this.transformedNumberCountryCallingCode;
    }

    public String userHomeCountryCode() {
        return this.userHomeCountryCode;
    }

    public String userRoamingCountryCode() {
        return this.userRoamingCountryCode;
    }
}
