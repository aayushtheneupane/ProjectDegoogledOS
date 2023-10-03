package com.android.dialer.spam.status;

import com.android.dialer.spam.status.SimpleSpamStatus;
import com.android.tools.p006r8.GeneratedOutlineSupport;
import com.google.common.base.Optional;

final class AutoValue_SimpleSpamStatus extends SimpleSpamStatus {
    private final boolean spam;
    private final SpamMetadata spamMetadata;
    private final Optional<Long> timestampMillis;

    static final class Builder extends SimpleSpamStatus.Builder {
        private Boolean spam;
        private SpamMetadata spamMetadata;
        private Optional<Long> timestampMillis = Optional.absent();

        Builder() {
        }

        public SimpleSpamStatus build() {
            String str = "";
            if (this.spam == null) {
                str = GeneratedOutlineSupport.outline8(str, " spam");
            }
            if (this.spamMetadata == null) {
                str = GeneratedOutlineSupport.outline8(str, " spamMetadata");
            }
            if (str.isEmpty()) {
                return new AutoValue_SimpleSpamStatus(this.spam.booleanValue(), this.timestampMillis, this.spamMetadata, (C05641) null);
            }
            throw new IllegalStateException(GeneratedOutlineSupport.outline8("Missing required properties:", str));
        }

        public SimpleSpamStatus.Builder setSpam(boolean z) {
            this.spam = Boolean.valueOf(z);
            return this;
        }

        public SimpleSpamStatus.Builder setSpamMetadata(SpamMetadata spamMetadata2) {
            if (spamMetadata2 != null) {
                this.spamMetadata = spamMetadata2;
                return this;
            }
            throw new NullPointerException("Null spamMetadata");
        }

        /* access modifiers changed from: package-private */
        public SimpleSpamStatus.Builder setTimestampMillis(Optional<Long> optional) {
            if (optional != null) {
                this.timestampMillis = optional;
                return this;
            }
            throw new NullPointerException("Null timestampMillis");
        }
    }

    /* synthetic */ AutoValue_SimpleSpamStatus(boolean z, Optional optional, SpamMetadata spamMetadata2, C05641 r4) {
        this.spam = z;
        this.timestampMillis = optional;
        this.spamMetadata = spamMetadata2;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof SimpleSpamStatus)) {
            return false;
        }
        SimpleSpamStatus simpleSpamStatus = (SimpleSpamStatus) obj;
        if (this.spam == ((AutoValue_SimpleSpamStatus) simpleSpamStatus).spam) {
            AutoValue_SimpleSpamStatus autoValue_SimpleSpamStatus = (AutoValue_SimpleSpamStatus) simpleSpamStatus;
            if (this.timestampMillis.equals(autoValue_SimpleSpamStatus.getTimestampMillis()) && this.spamMetadata.equals(autoValue_SimpleSpamStatus.spamMetadata)) {
                return true;
            }
        }
        return false;
    }

    public Optional<Long> getTimestampMillis() {
        return this.timestampMillis;
    }

    public int hashCode() {
        return this.spamMetadata.hashCode() ^ (((((this.spam ? 1231 : 1237) ^ 1000003) * 1000003) ^ this.timestampMillis.hashCode()) * 1000003);
    }

    public boolean isSpam() {
        return this.spam;
    }

    public String toString() {
        StringBuilder outline13 = GeneratedOutlineSupport.outline13("SimpleSpamStatus{spam=");
        outline13.append(this.spam);
        outline13.append(", timestampMillis=");
        outline13.append(this.timestampMillis);
        outline13.append(", spamMetadata=");
        return GeneratedOutlineSupport.outline11(outline13, this.spamMetadata, "}");
    }
}
