package com.android.dialer.logging;

import com.android.dialer.logging.LoggingBindings;
import com.android.tools.p006r8.GeneratedOutlineSupport;

final class AutoValue_LoggingBindings_ContactsProviderMatchInfo extends LoggingBindings.ContactsProviderMatchInfo {
    private final boolean inputNumberHasPostdialDigits;
    private final int inputNumberLength;
    private final boolean inputNumberValid;
    private final boolean matchedContact;
    private final boolean matchedNumberHasPostdialDigits;
    private final int matchedNumberLength;

    static final class Builder extends LoggingBindings.ContactsProviderMatchInfo.Builder {
        private Boolean inputNumberHasPostdialDigits;
        private Integer inputNumberLength;
        private Boolean inputNumberValid;
        private Boolean matchedContact;
        private Boolean matchedNumberHasPostdialDigits;
        private Integer matchedNumberLength;

        Builder() {
        }

        public LoggingBindings.ContactsProviderMatchInfo build() {
            String str = "";
            if (this.matchedContact == null) {
                str = GeneratedOutlineSupport.outline8(str, " matchedContact");
            }
            if (this.inputNumberValid == null) {
                str = GeneratedOutlineSupport.outline8(str, " inputNumberValid");
            }
            if (this.inputNumberLength == null) {
                str = GeneratedOutlineSupport.outline8(str, " inputNumberLength");
            }
            if (this.matchedNumberLength == null) {
                str = GeneratedOutlineSupport.outline8(str, " matchedNumberLength");
            }
            if (this.inputNumberHasPostdialDigits == null) {
                str = GeneratedOutlineSupport.outline8(str, " inputNumberHasPostdialDigits");
            }
            if (this.matchedNumberHasPostdialDigits == null) {
                str = GeneratedOutlineSupport.outline8(str, " matchedNumberHasPostdialDigits");
            }
            if (str.isEmpty()) {
                return new AutoValue_LoggingBindings_ContactsProviderMatchInfo(this.matchedContact.booleanValue(), this.inputNumberValid.booleanValue(), this.inputNumberLength.intValue(), this.matchedNumberLength.intValue(), this.inputNumberHasPostdialDigits.booleanValue(), this.matchedNumberHasPostdialDigits.booleanValue(), (C04921) null);
            }
            throw new IllegalStateException(GeneratedOutlineSupport.outline8("Missing required properties:", str));
        }

        public LoggingBindings.ContactsProviderMatchInfo.Builder setInputNumberHasPostdialDigits(boolean z) {
            this.inputNumberHasPostdialDigits = Boolean.valueOf(z);
            return this;
        }

        public LoggingBindings.ContactsProviderMatchInfo.Builder setInputNumberLength(int i) {
            this.inputNumberLength = Integer.valueOf(i);
            return this;
        }

        public LoggingBindings.ContactsProviderMatchInfo.Builder setInputNumberValid(boolean z) {
            this.inputNumberValid = Boolean.valueOf(z);
            return this;
        }

        public LoggingBindings.ContactsProviderMatchInfo.Builder setMatchedContact(boolean z) {
            this.matchedContact = Boolean.valueOf(z);
            return this;
        }

        public LoggingBindings.ContactsProviderMatchInfo.Builder setMatchedNumberHasPostdialDigits(boolean z) {
            this.matchedNumberHasPostdialDigits = Boolean.valueOf(z);
            return this;
        }

        public LoggingBindings.ContactsProviderMatchInfo.Builder setMatchedNumberLength(int i) {
            this.matchedNumberLength = Integer.valueOf(i);
            return this;
        }
    }

    /* synthetic */ AutoValue_LoggingBindings_ContactsProviderMatchInfo(boolean z, boolean z2, int i, int i2, boolean z3, boolean z4, C04921 r7) {
        this.matchedContact = z;
        this.inputNumberValid = z2;
        this.inputNumberLength = i;
        this.matchedNumberLength = i2;
        this.inputNumberHasPostdialDigits = z3;
        this.matchedNumberHasPostdialDigits = z4;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof LoggingBindings.ContactsProviderMatchInfo)) {
            return false;
        }
        LoggingBindings.ContactsProviderMatchInfo contactsProviderMatchInfo = (LoggingBindings.ContactsProviderMatchInfo) obj;
        if (this.matchedContact == ((AutoValue_LoggingBindings_ContactsProviderMatchInfo) contactsProviderMatchInfo).matchedContact) {
            AutoValue_LoggingBindings_ContactsProviderMatchInfo autoValue_LoggingBindings_ContactsProviderMatchInfo = (AutoValue_LoggingBindings_ContactsProviderMatchInfo) contactsProviderMatchInfo;
            if (this.inputNumberValid == autoValue_LoggingBindings_ContactsProviderMatchInfo.inputNumberValid && this.inputNumberLength == autoValue_LoggingBindings_ContactsProviderMatchInfo.inputNumberLength && this.matchedNumberLength == autoValue_LoggingBindings_ContactsProviderMatchInfo.matchedNumberLength && this.inputNumberHasPostdialDigits == autoValue_LoggingBindings_ContactsProviderMatchInfo.inputNumberHasPostdialDigits && this.matchedNumberHasPostdialDigits == autoValue_LoggingBindings_ContactsProviderMatchInfo.matchedNumberHasPostdialDigits) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        int i = 1231;
        int i2 = ((((((((((this.matchedContact ? 1231 : 1237) ^ 1000003) * 1000003) ^ (this.inputNumberValid ? 1231 : 1237)) * 1000003) ^ this.inputNumberLength) * 1000003) ^ this.matchedNumberLength) * 1000003) ^ (this.inputNumberHasPostdialDigits ? 1231 : 1237)) * 1000003;
        if (!this.matchedNumberHasPostdialDigits) {
            i = 1237;
        }
        return i2 ^ i;
    }

    public String toString() {
        StringBuilder outline13 = GeneratedOutlineSupport.outline13("ContactsProviderMatchInfo{matchedContact=");
        outline13.append(this.matchedContact);
        outline13.append(", inputNumberValid=");
        outline13.append(this.inputNumberValid);
        outline13.append(", inputNumberLength=");
        outline13.append(this.inputNumberLength);
        outline13.append(", matchedNumberLength=");
        outline13.append(this.matchedNumberLength);
        outline13.append(", inputNumberHasPostdialDigits=");
        outline13.append(this.inputNumberHasPostdialDigits);
        outline13.append(", matchedNumberHasPostdialDigits=");
        outline13.append(this.matchedNumberHasPostdialDigits);
        outline13.append("}");
        return outline13.toString();
    }
}
