package com.android.dialer.enrichedcall;

import com.android.dialer.enrichedcall.EnrichedCallCapabilities;
import com.android.tools.p006r8.GeneratedOutlineSupport;

final class AutoValue_EnrichedCallCapabilities extends EnrichedCallCapabilities {
    private final boolean callComposerCapable;
    private final boolean postCallCapable;
    private final boolean temporarilyUnavailable;
    private final boolean videoShareCapable;

    static final class Builder extends EnrichedCallCapabilities.Builder {
        private Boolean callComposerCapable;
        private Boolean postCallCapable;
        private Boolean temporarilyUnavailable;
        private Boolean videoShareCapable;

        Builder() {
        }

        public EnrichedCallCapabilities build() {
            String str = "";
            if (this.callComposerCapable == null) {
                str = GeneratedOutlineSupport.outline8(str, " callComposerCapable");
            }
            if (this.postCallCapable == null) {
                str = GeneratedOutlineSupport.outline8(str, " postCallCapable");
            }
            if (this.videoShareCapable == null) {
                str = GeneratedOutlineSupport.outline8(str, " videoShareCapable");
            }
            if (this.temporarilyUnavailable == null) {
                str = GeneratedOutlineSupport.outline8(str, " temporarilyUnavailable");
            }
            if (str.isEmpty()) {
                return new AutoValue_EnrichedCallCapabilities(this.callComposerCapable.booleanValue(), this.postCallCapable.booleanValue(), this.videoShareCapable.booleanValue(), this.temporarilyUnavailable.booleanValue(), (C04811) null);
            }
            throw new IllegalStateException(GeneratedOutlineSupport.outline8("Missing required properties:", str));
        }

        public EnrichedCallCapabilities.Builder setCallComposerCapable(boolean z) {
            this.callComposerCapable = Boolean.valueOf(z);
            return this;
        }

        public EnrichedCallCapabilities.Builder setPostCallCapable(boolean z) {
            this.postCallCapable = Boolean.valueOf(z);
            return this;
        }

        public EnrichedCallCapabilities.Builder setTemporarilyUnavailable(boolean z) {
            this.temporarilyUnavailable = Boolean.valueOf(z);
            return this;
        }

        public EnrichedCallCapabilities.Builder setVideoShareCapable(boolean z) {
            this.videoShareCapable = Boolean.valueOf(z);
            return this;
        }
    }

    /* synthetic */ AutoValue_EnrichedCallCapabilities(boolean z, boolean z2, boolean z3, boolean z4, C04811 r5) {
        this.callComposerCapable = z;
        this.postCallCapable = z2;
        this.videoShareCapable = z3;
        this.temporarilyUnavailable = z4;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof EnrichedCallCapabilities)) {
            return false;
        }
        EnrichedCallCapabilities enrichedCallCapabilities = (EnrichedCallCapabilities) obj;
        if (this.callComposerCapable == ((AutoValue_EnrichedCallCapabilities) enrichedCallCapabilities).callComposerCapable && this.postCallCapable == enrichedCallCapabilities.isPostCallCapable() && this.videoShareCapable == ((AutoValue_EnrichedCallCapabilities) enrichedCallCapabilities).videoShareCapable && this.temporarilyUnavailable == enrichedCallCapabilities.isTemporarilyUnavailable()) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int i = 1231;
        int i2 = ((((((this.callComposerCapable ? 1231 : 1237) ^ 1000003) * 1000003) ^ (this.postCallCapable ? 1231 : 1237)) * 1000003) ^ (this.videoShareCapable ? 1231 : 1237)) * 1000003;
        if (!this.temporarilyUnavailable) {
            i = 1237;
        }
        return i2 ^ i;
    }

    public boolean isCallComposerCapable() {
        return this.callComposerCapable;
    }

    public boolean isPostCallCapable() {
        return this.postCallCapable;
    }

    public boolean isTemporarilyUnavailable() {
        return this.temporarilyUnavailable;
    }

    public String toString() {
        StringBuilder outline13 = GeneratedOutlineSupport.outline13("EnrichedCallCapabilities{callComposerCapable=");
        outline13.append(this.callComposerCapable);
        outline13.append(", postCallCapable=");
        outline13.append(this.postCallCapable);
        outline13.append(", videoShareCapable=");
        outline13.append(this.videoShareCapable);
        outline13.append(", temporarilyUnavailable=");
        outline13.append(this.temporarilyUnavailable);
        outline13.append("}");
        return outline13.toString();
    }
}
