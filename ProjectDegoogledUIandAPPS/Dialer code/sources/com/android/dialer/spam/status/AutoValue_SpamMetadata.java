package com.android.dialer.spam.status;

import com.android.dialer.spam.status.SpamMetadata;
import com.android.tools.p006r8.GeneratedOutlineSupport;
import com.google.common.base.Optional;

final class AutoValue_SpamMetadata extends SpamMetadata {
    private final Optional<Object> globalSpamListStatus;
    private final Optional<Object> userSpamListStatus;

    static final class Builder extends SpamMetadata.Builder {
        private Optional<Object> globalSpamListStatus = Optional.absent();
        private Optional<Object> userSpamListStatus = Optional.absent();

        Builder() {
        }

        public SpamMetadata build() {
            return new AutoValue_SpamMetadata(this.globalSpamListStatus, this.userSpamListStatus, (C05651) null);
        }
    }

    /* synthetic */ AutoValue_SpamMetadata(Optional optional, Optional optional2, C05651 r3) {
        this.globalSpamListStatus = optional;
        this.userSpamListStatus = optional2;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof SpamMetadata)) {
            return false;
        }
        SpamMetadata spamMetadata = (SpamMetadata) obj;
        if (!this.globalSpamListStatus.equals(((AutoValue_SpamMetadata) spamMetadata).globalSpamListStatus) || !this.userSpamListStatus.equals(((AutoValue_SpamMetadata) spamMetadata).userSpamListStatus)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return this.userSpamListStatus.hashCode() ^ ((this.globalSpamListStatus.hashCode() ^ 1000003) * 1000003);
    }

    public String toString() {
        StringBuilder outline13 = GeneratedOutlineSupport.outline13("SpamMetadata{globalSpamListStatus=");
        outline13.append(this.globalSpamListStatus);
        outline13.append(", userSpamListStatus=");
        return GeneratedOutlineSupport.outline11(outline13, this.userSpamListStatus, "}");
    }
}
