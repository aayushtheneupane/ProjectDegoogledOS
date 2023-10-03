package com.android.dialer.spam.status;

import com.android.dialer.spam.status.AutoValue_SimpleSpamStatus;
import com.android.dialer.spam.status.AutoValue_SpamMetadata;
import com.google.auto.value.AutoValue;
import com.google.common.base.Optional;

@AutoValue
public abstract class SimpleSpamStatus implements SpamStatus {

    public static abstract class Builder {
        public abstract SimpleSpamStatus build();

        public abstract Builder setSpam(boolean z);

        public abstract Builder setSpamMetadata(SpamMetadata spamMetadata);

        /* access modifiers changed from: package-private */
        public abstract Builder setTimestampMillis(Optional<Long> optional);

        public Builder setTimestampMillis(Long l) {
            setTimestampMillis((Optional<Long>) Optional.fromNullable(l));
            return this;
        }
    }

    public static SimpleSpamStatus notSpam() {
        AutoValue_SimpleSpamStatus.Builder builder = new AutoValue_SimpleSpamStatus.Builder();
        builder.setSpam(false);
        builder.setTimestampMillis((Long) null);
        builder.setSpamMetadata(new AutoValue_SpamMetadata.Builder().build());
        return builder.build();
    }
}
