package com.android.voicemail.impl;

import com.android.tools.p006r8.GeneratedOutlineSupport;
import com.android.voicemail.impl.CarrierIdentifier;

final class AutoValue_CarrierIdentifier extends CarrierIdentifier {
    private final String gid1;
    private final String mccMnc;

    static final class Builder extends CarrierIdentifier.Builder {
        private String gid1;
        private String mccMnc;

        Builder() {
        }

        public CarrierIdentifier build() {
            String str = "";
            if (this.mccMnc == null) {
                str = GeneratedOutlineSupport.outline8(str, " mccMnc");
            }
            if (this.gid1 == null) {
                str = GeneratedOutlineSupport.outline8(str, " gid1");
            }
            if (str.isEmpty()) {
                return new AutoValue_CarrierIdentifier(this.mccMnc, this.gid1, (C07661) null);
            }
            throw new IllegalStateException(GeneratedOutlineSupport.outline8("Missing required properties:", str));
        }

        public CarrierIdentifier.Builder setGid1(String str) {
            if (str != null) {
                this.gid1 = str;
                return this;
            }
            throw new NullPointerException("Null gid1");
        }

        public CarrierIdentifier.Builder setMccMnc(String str) {
            if (str != null) {
                this.mccMnc = str;
                return this;
            }
            throw new NullPointerException("Null mccMnc");
        }
    }

    /* synthetic */ AutoValue_CarrierIdentifier(String str, String str2, C07661 r3) {
        this.mccMnc = str;
        this.gid1 = str2;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof CarrierIdentifier)) {
            return false;
        }
        CarrierIdentifier carrierIdentifier = (CarrierIdentifier) obj;
        if (!this.mccMnc.equals(((AutoValue_CarrierIdentifier) carrierIdentifier).mccMnc) || !this.gid1.equals(carrierIdentifier.gid1())) {
            return false;
        }
        return true;
    }

    public String gid1() {
        return this.gid1;
    }

    public int hashCode() {
        return this.gid1.hashCode() ^ ((this.mccMnc.hashCode() ^ 1000003) * 1000003);
    }

    public String mccMnc() {
        return this.mccMnc;
    }

    public String toString() {
        StringBuilder outline13 = GeneratedOutlineSupport.outline13("CarrierIdentifier{mccMnc=");
        outline13.append(this.mccMnc);
        outline13.append(", gid1=");
        return GeneratedOutlineSupport.outline12(outline13, this.gid1, "}");
    }
}
