package com.android.dialer.speeddial.database;

import com.android.dialer.speeddial.database.AutoValue_SpeedDialEntry;
import com.android.dialer.speeddial.database.AutoValue_SpeedDialEntry_Channel;
import com.google.auto.value.AutoValue;
import com.google.common.base.Optional;

@AutoValue
public abstract class SpeedDialEntry {

    public static abstract class Builder {
        public abstract SpeedDialEntry build();

        public abstract Builder setContactId(long j);

        public abstract Builder setDefaultChannel(Channel channel);

        public abstract Builder setId(Long l);

        public abstract Builder setLookupKey(String str);

        public abstract Builder setPinnedPosition(Optional<Integer> optional);
    }

    @AutoValue
    public static abstract class Channel {

        public static abstract class Builder {
            public abstract Channel build();

            public abstract Builder setLabel(String str);

            public abstract Builder setNumber(String str);

            public abstract Builder setPhoneType(int i);

            public abstract Builder setTechnology(int i);
        }

        public static Builder builder() {
            return new AutoValue_SpeedDialEntry_Channel.Builder();
        }

        public boolean isVideoTechnology() {
            return technology() == 2 || technology() == 3;
        }

        public abstract String label();

        public abstract String number();

        public abstract int phoneType();

        public abstract int technology();

        public abstract Builder toBuilder();
    }

    public static Builder builder() {
        AutoValue_SpeedDialEntry.Builder builder = new AutoValue_SpeedDialEntry.Builder();
        builder.setPinnedPosition(Optional.absent());
        return builder;
    }

    public abstract long contactId();

    public abstract Channel defaultChannel();

    /* renamed from: id */
    public abstract Long mo7325id();

    public abstract String lookupKey();

    public abstract Optional<Integer> pinnedPosition();

    public abstract Builder toBuilder();
}
