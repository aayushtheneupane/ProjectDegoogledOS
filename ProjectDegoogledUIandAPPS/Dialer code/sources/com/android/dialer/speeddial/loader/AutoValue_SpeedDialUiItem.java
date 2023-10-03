package com.android.dialer.speeddial.loader;

import com.android.dialer.speeddial.database.SpeedDialEntry;
import com.android.dialer.speeddial.loader.SpeedDialUiItem;
import com.android.tools.p006r8.GeneratedOutlineSupport;
import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;

final class AutoValue_SpeedDialUiItem extends SpeedDialUiItem {
    private final ImmutableList<SpeedDialEntry.Channel> channels;
    private final long contactId;
    private final SpeedDialEntry.Channel defaultChannel;
    private final boolean isStarred;
    private final String lookupKey;
    private final String name;
    private final long photoId;
    private final String photoUri;
    private final Optional<Integer> pinnedPosition;
    private final Long speedDialEntryId;

    /* synthetic */ AutoValue_SpeedDialUiItem(Long l, Optional optional, String str, long j, String str2, boolean z, long j2, String str3, ImmutableList immutableList, SpeedDialEntry.Channel channel, C05741 r13) {
        this.speedDialEntryId = l;
        this.pinnedPosition = optional;
        this.name = str;
        this.contactId = j;
        this.lookupKey = str2;
        this.isStarred = z;
        this.photoId = j2;
        this.photoUri = str3;
        this.channels = immutableList;
        this.defaultChannel = channel;
    }

    public ImmutableList<SpeedDialEntry.Channel> channels() {
        return this.channels;
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
        if (!(obj instanceof SpeedDialUiItem)) {
            return false;
        }
        SpeedDialUiItem speedDialUiItem = (SpeedDialUiItem) obj;
        Long l = this.speedDialEntryId;
        if (l != null ? l.equals(((AutoValue_SpeedDialUiItem) speedDialUiItem).speedDialEntryId) : ((AutoValue_SpeedDialUiItem) speedDialUiItem).speedDialEntryId == null) {
            if (this.pinnedPosition.equals(((AutoValue_SpeedDialUiItem) speedDialUiItem).pinnedPosition)) {
                AutoValue_SpeedDialUiItem autoValue_SpeedDialUiItem = (AutoValue_SpeedDialUiItem) speedDialUiItem;
                if (this.name.equals(autoValue_SpeedDialUiItem.name) && this.contactId == autoValue_SpeedDialUiItem.contactId && this.lookupKey.equals(autoValue_SpeedDialUiItem.lookupKey) && this.isStarred == autoValue_SpeedDialUiItem.isStarred && this.photoId == autoValue_SpeedDialUiItem.photoId && this.photoUri.equals(autoValue_SpeedDialUiItem.photoUri) && this.channels.equals(autoValue_SpeedDialUiItem.channels)) {
                    SpeedDialEntry.Channel channel = this.defaultChannel;
                    if (channel == null) {
                        if (autoValue_SpeedDialUiItem.defaultChannel == null) {
                            return true;
                        }
                    } else if (channel.equals(autoValue_SpeedDialUiItem.defaultChannel)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public int hashCode() {
        Long l = this.speedDialEntryId;
        int i = 0;
        int hashCode = l == null ? 0 : l.hashCode();
        long j = this.contactId;
        int hashCode2 = (((((((((hashCode ^ 1000003) * 1000003) ^ this.pinnedPosition.hashCode()) * 1000003) ^ this.name.hashCode()) * 1000003) ^ ((int) (j ^ (j >>> 32)))) * 1000003) ^ this.lookupKey.hashCode()) * 1000003;
        int i2 = this.isStarred ? 1231 : 1237;
        long j2 = this.photoId;
        int hashCode3 = (((((((hashCode2 ^ i2) * 1000003) ^ ((int) (j2 ^ (j2 >>> 32)))) * 1000003) ^ this.photoUri.hashCode()) * 1000003) ^ this.channels.hashCode()) * 1000003;
        SpeedDialEntry.Channel channel = this.defaultChannel;
        if (channel != null) {
            i = channel.hashCode();
        }
        return hashCode3 ^ i;
    }

    public boolean isStarred() {
        return this.isStarred;
    }

    public String lookupKey() {
        return this.lookupKey;
    }

    public String name() {
        return this.name;
    }

    public long photoId() {
        return this.photoId;
    }

    public String photoUri() {
        return this.photoUri;
    }

    public Optional<Integer> pinnedPosition() {
        return this.pinnedPosition;
    }

    public Long speedDialEntryId() {
        return this.speedDialEntryId;
    }

    public SpeedDialUiItem.Builder toBuilder() {
        return new Builder(this, (C05741) null);
    }

    public String toString() {
        StringBuilder outline13 = GeneratedOutlineSupport.outline13("SpeedDialUiItem{speedDialEntryId=");
        outline13.append(this.speedDialEntryId);
        outline13.append(", pinnedPosition=");
        outline13.append(this.pinnedPosition);
        outline13.append(", name=");
        outline13.append(this.name);
        outline13.append(", contactId=");
        outline13.append(this.contactId);
        outline13.append(", lookupKey=");
        outline13.append(this.lookupKey);
        outline13.append(", isStarred=");
        outline13.append(this.isStarred);
        outline13.append(", photoId=");
        outline13.append(this.photoId);
        outline13.append(", photoUri=");
        outline13.append(this.photoUri);
        outline13.append(", channels=");
        outline13.append(this.channels);
        outline13.append(", defaultChannel=");
        return GeneratedOutlineSupport.outline11(outline13, this.defaultChannel, "}");
    }

    static final class Builder extends SpeedDialUiItem.Builder {
        private ImmutableList<SpeedDialEntry.Channel> channels;
        private Long contactId;
        private SpeedDialEntry.Channel defaultChannel;
        private Boolean isStarred;
        private String lookupKey;
        private String name;
        private Long photoId;
        private String photoUri;
        private Optional<Integer> pinnedPosition = Optional.absent();
        private Long speedDialEntryId;

        Builder() {
        }

        public SpeedDialUiItem build() {
            String str = "";
            if (this.name == null) {
                str = GeneratedOutlineSupport.outline8(str, " name");
            }
            if (this.contactId == null) {
                str = GeneratedOutlineSupport.outline8(str, " contactId");
            }
            if (this.lookupKey == null) {
                str = GeneratedOutlineSupport.outline8(str, " lookupKey");
            }
            if (this.isStarred == null) {
                str = GeneratedOutlineSupport.outline8(str, " isStarred");
            }
            if (this.photoId == null) {
                str = GeneratedOutlineSupport.outline8(str, " photoId");
            }
            if (this.photoUri == null) {
                str = GeneratedOutlineSupport.outline8(str, " photoUri");
            }
            if (this.channels == null) {
                str = GeneratedOutlineSupport.outline8(str, " channels");
            }
            if (str.isEmpty()) {
                return new AutoValue_SpeedDialUiItem(this.speedDialEntryId, this.pinnedPosition, this.name, this.contactId.longValue(), this.lookupKey, this.isStarred.booleanValue(), this.photoId.longValue(), this.photoUri, this.channels, this.defaultChannel, (C05741) null);
            }
            throw new IllegalStateException(GeneratedOutlineSupport.outline8("Missing required properties:", str));
        }

        public SpeedDialUiItem.Builder setChannels(ImmutableList<SpeedDialEntry.Channel> immutableList) {
            if (immutableList != null) {
                this.channels = immutableList;
                return this;
            }
            throw new NullPointerException("Null channels");
        }

        public SpeedDialUiItem.Builder setContactId(long j) {
            this.contactId = Long.valueOf(j);
            return this;
        }

        public SpeedDialUiItem.Builder setDefaultChannel(SpeedDialEntry.Channel channel) {
            this.defaultChannel = channel;
            return this;
        }

        public SpeedDialUiItem.Builder setIsStarred(boolean z) {
            this.isStarred = Boolean.valueOf(z);
            return this;
        }

        public SpeedDialUiItem.Builder setLookupKey(String str) {
            if (str != null) {
                this.lookupKey = str;
                return this;
            }
            throw new NullPointerException("Null lookupKey");
        }

        public SpeedDialUiItem.Builder setName(String str) {
            if (str != null) {
                this.name = str;
                return this;
            }
            throw new NullPointerException("Null name");
        }

        public SpeedDialUiItem.Builder setPhotoId(long j) {
            this.photoId = Long.valueOf(j);
            return this;
        }

        public SpeedDialUiItem.Builder setPhotoUri(String str) {
            if (str != null) {
                this.photoUri = str;
                return this;
            }
            throw new NullPointerException("Null photoUri");
        }

        public SpeedDialUiItem.Builder setPinnedPosition(Optional<Integer> optional) {
            if (optional != null) {
                this.pinnedPosition = optional;
                return this;
            }
            throw new NullPointerException("Null pinnedPosition");
        }

        public SpeedDialUiItem.Builder setSpeedDialEntryId(Long l) {
            this.speedDialEntryId = l;
            return this;
        }

        /* synthetic */ Builder(SpeedDialUiItem speedDialUiItem, C05741 r4) {
            this.speedDialEntryId = speedDialUiItem.speedDialEntryId();
            this.pinnedPosition = speedDialUiItem.pinnedPosition();
            this.name = speedDialUiItem.name();
            this.contactId = Long.valueOf(speedDialUiItem.contactId());
            this.lookupKey = speedDialUiItem.lookupKey();
            this.isStarred = Boolean.valueOf(speedDialUiItem.isStarred());
            this.photoId = Long.valueOf(speedDialUiItem.photoId());
            this.photoUri = speedDialUiItem.photoUri();
            this.channels = speedDialUiItem.channels();
            this.defaultChannel = speedDialUiItem.defaultChannel();
        }
    }
}
