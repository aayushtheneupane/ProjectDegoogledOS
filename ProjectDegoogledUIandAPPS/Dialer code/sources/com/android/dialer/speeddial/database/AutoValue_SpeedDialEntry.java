package com.android.dialer.speeddial.database;

import com.android.dialer.speeddial.database.SpeedDialEntry;
import com.android.tools.p006r8.GeneratedOutlineSupport;
import com.google.common.base.Optional;

final class AutoValue_SpeedDialEntry extends SpeedDialEntry {
    private final long contactId;
    private final SpeedDialEntry.Channel defaultChannel;

    /* renamed from: id */
    private final Long f37id;
    private final String lookupKey;
    private final Optional<Integer> pinnedPosition;

    /* synthetic */ AutoValue_SpeedDialEntry(Long l, Optional optional, long j, String str, SpeedDialEntry.Channel channel, C05721 r7) {
        this.f37id = l;
        this.pinnedPosition = optional;
        this.contactId = j;
        this.lookupKey = str;
        this.defaultChannel = channel;
    }

    public long contactId() {
        return this.contactId;
    }

    public SpeedDialEntry.Channel defaultChannel() {
        return this.defaultChannel;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof SpeedDialEntry)) {
            return false;
        }
        SpeedDialEntry speedDialEntry = (SpeedDialEntry) obj;
        Long l = this.f37id;
        if (l != null ? l.equals(((AutoValue_SpeedDialEntry) speedDialEntry).f37id) : ((AutoValue_SpeedDialEntry) speedDialEntry).f37id == null) {
            if (this.pinnedPosition.equals(((AutoValue_SpeedDialEntry) speedDialEntry).pinnedPosition)) {
                AutoValue_SpeedDialEntry autoValue_SpeedDialEntry = (AutoValue_SpeedDialEntry) speedDialEntry;
                if (this.contactId == autoValue_SpeedDialEntry.contactId && this.lookupKey.equals(autoValue_SpeedDialEntry.lookupKey)) {
                    SpeedDialEntry.Channel channel = this.defaultChannel;
                    if (channel == null) {
                        if (autoValue_SpeedDialEntry.defaultChannel == null) {
                            return true;
                        }
                    } else if (channel.equals(autoValue_SpeedDialEntry.defaultChannel)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public int hashCode() {
        Long l = this.f37id;
        int i = 0;
        int hashCode = l == null ? 0 : l.hashCode();
        long j = this.contactId;
        int hashCode2 = (((((((hashCode ^ 1000003) * 1000003) ^ this.pinnedPosition.hashCode()) * 1000003) ^ ((int) (j ^ (j >>> 32)))) * 1000003) ^ this.lookupKey.hashCode()) * 1000003;
        SpeedDialEntry.Channel channel = this.defaultChannel;
        if (channel != null) {
            i = channel.hashCode();
        }
        return hashCode2 ^ i;
    }

    /* renamed from: id */
    public Long mo7325id() {
        return this.f37id;
    }

    public String lookupKey() {
        return this.lookupKey;
    }

    public Optional<Integer> pinnedPosition() {
        return this.pinnedPosition;
    }

    public SpeedDialEntry.Builder toBuilder() {
        return new Builder(this, (C05721) null);
    }

    public String toString() {
        StringBuilder outline13 = GeneratedOutlineSupport.outline13("SpeedDialEntry{id=");
        outline13.append(this.f37id);
        outline13.append(", pinnedPosition=");
        outline13.append(this.pinnedPosition);
        outline13.append(", contactId=");
        outline13.append(this.contactId);
        outline13.append(", lookupKey=");
        outline13.append(this.lookupKey);
        outline13.append(", defaultChannel=");
        return GeneratedOutlineSupport.outline11(outline13, this.defaultChannel, "}");
    }

    static final class Builder extends SpeedDialEntry.Builder {
        private Long contactId;
        private SpeedDialEntry.Channel defaultChannel;

        /* renamed from: id */
        private Long f38id;
        private String lookupKey;
        private Optional<Integer> pinnedPosition = Optional.absent();

        Builder() {
        }

        public SpeedDialEntry build() {
            String str = "";
            if (this.contactId == null) {
                str = GeneratedOutlineSupport.outline8(str, " contactId");
            }
            if (this.lookupKey == null) {
                str = GeneratedOutlineSupport.outline8(str, " lookupKey");
            }
            if (str.isEmpty()) {
                return new AutoValue_SpeedDialEntry(this.f38id, this.pinnedPosition, this.contactId.longValue(), this.lookupKey, this.defaultChannel, (C05721) null);
            }
            throw new IllegalStateException(GeneratedOutlineSupport.outline8("Missing required properties:", str));
        }

        public SpeedDialEntry.Builder setContactId(long j) {
            this.contactId = Long.valueOf(j);
            return this;
        }

        public SpeedDialEntry.Builder setDefaultChannel(SpeedDialEntry.Channel channel) {
            this.defaultChannel = channel;
            return this;
        }

        public SpeedDialEntry.Builder setId(Long l) {
            this.f38id = l;
            return this;
        }

        public SpeedDialEntry.Builder setLookupKey(String str) {
            if (str != null) {
                this.lookupKey = str;
                return this;
            }
            throw new NullPointerException("Null lookupKey");
        }

        public SpeedDialEntry.Builder setPinnedPosition(Optional<Integer> optional) {
            if (optional != null) {
                this.pinnedPosition = optional;
                return this;
            }
            throw new NullPointerException("Null pinnedPosition");
        }

        /* synthetic */ Builder(SpeedDialEntry speedDialEntry, C05721 r4) {
            this.f38id = speedDialEntry.mo7325id();
            this.pinnedPosition = speedDialEntry.pinnedPosition();
            this.contactId = Long.valueOf(speedDialEntry.contactId());
            this.lookupKey = speedDialEntry.lookupKey();
            this.defaultChannel = speedDialEntry.defaultChannel();
        }
    }
}
