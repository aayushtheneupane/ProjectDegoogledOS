package com.android.voicemail.impl;

import com.android.tools.p006r8.GeneratedOutlineSupport;
import com.android.voicemail.impl.CarrierIdentifierMatcher;
import com.google.common.base.Optional;

final class AutoValue_CarrierIdentifierMatcher extends CarrierIdentifierMatcher {
    private final Optional<String> gid1;
    private final String mccMnc;

    static final class Builder extends CarrierIdentifierMatcher.Builder {
        private Optional<String> gid1 = Optional.absent();
        private String mccMnc;

        Builder() {
        }

        public CarrierIdentifierMatcher build() {
            String str = "";
            if (this.mccMnc == null) {
                str = GeneratedOutlineSupport.outline8(str, " mccMnc");
            }
            if (str.isEmpty()) {
                return new AutoValue_CarrierIdentifierMatcher(this.mccMnc, this.gid1, (C07671) null);
            }
            throw new IllegalStateException(GeneratedOutlineSupport.outline8("Missing required properties:", str));
        }

        public CarrierIdentifierMatcher.Builder setGid1(String str) {
            if (str != null) {
                this.gid1 = Optional.m67of(str);
                return this;
            }
            throw new NullPointerException("Null gid1");
        }

        public CarrierIdentifierMatcher.Builder setMccMnc(String str) {
            if (str != null) {
                this.mccMnc = str;
                return this;
            }
            throw new NullPointerException("Null mccMnc");
        }
    }

    /* synthetic */ AutoValue_CarrierIdentifierMatcher(String str, Optional optional, C07671 r3) {
        this.mccMnc = str;
        this.gid1 = optional;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof CarrierIdentifierMatcher)) {
            return false;
        }
        CarrierIdentifierMatcher carrierIdentifierMatcher = (CarrierIdentifierMatcher) obj;
        if (!this.mccMnc.equals(((AutoValue_CarrierIdentifierMatcher) carrierIdentifierMatcher).mccMnc) || !this.gid1.equals(((AutoValue_CarrierIdentifierMatcher) carrierIdentifierMatcher).gid1)) {
            return false;
        }
        return true;
    }

    public Optional<String> gid1() {
        return this.gid1;
    }

    public int hashCode() {
        return this.gid1.hashCode() ^ ((this.mccMnc.hashCode() ^ 1000003) * 1000003);
    }

    public String mccMnc() {
        return this.mccMnc;
    }

    public String toString() {
        StringBuilder outline13 = GeneratedOutlineSupport.outline13("CarrierIdentifierMatcher{mccMnc=");
        outline13.append(this.mccMnc);
        outline13.append(", gid1=");
        return GeneratedOutlineSupport.outline11(outline13, this.gid1, "}");
    }
}
