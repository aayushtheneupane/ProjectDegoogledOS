package com.android.dialer.enrichedcall;

import com.android.dialer.enrichedcall.AutoValue_EnrichedCallCapabilities;
import com.google.auto.value.AutoValue;

@AutoValue
public abstract class EnrichedCallCapabilities {
    public static final EnrichedCallCapabilities NO_CAPABILITIES;

    public static abstract class Builder {
        public abstract EnrichedCallCapabilities build();

        public abstract Builder setCallComposerCapable(boolean z);

        public abstract Builder setPostCallCapable(boolean z);

        public abstract Builder setTemporarilyUnavailable(boolean z);

        public abstract Builder setVideoShareCapable(boolean z);
    }

    static {
        AutoValue_EnrichedCallCapabilities.Builder builder = new AutoValue_EnrichedCallCapabilities.Builder();
        builder.setCallComposerCapable(false);
        builder.setPostCallCapable(false);
        builder.setVideoShareCapable(false);
        builder.setTemporarilyUnavailable(false);
        NO_CAPABILITIES = builder.build();
        AutoValue_EnrichedCallCapabilities.Builder builder2 = new AutoValue_EnrichedCallCapabilities.Builder();
        builder2.setCallComposerCapable(false);
        builder2.setPostCallCapable(false);
        builder2.setVideoShareCapable(false);
        builder2.setTemporarilyUnavailable(false);
        builder2.setCallComposerCapable(true);
        builder2.setPostCallCapable(true);
        builder2.setVideoShareCapable(true);
        builder2.build();
    }

    public abstract boolean isCallComposerCapable();

    public abstract boolean isPostCallCapable();

    public abstract boolean isTemporarilyUnavailable();
}
