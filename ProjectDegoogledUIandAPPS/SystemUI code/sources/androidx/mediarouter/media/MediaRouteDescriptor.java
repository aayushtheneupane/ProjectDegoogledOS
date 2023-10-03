package androidx.mediarouter.media;

import android.content.IntentFilter;
import android.content.IntentSender;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public final class MediaRouteDescriptor {
    final Bundle mBundle;
    List<IntentFilter> mControlFilters;
    List<String> mGroupMemberIds;

    MediaRouteDescriptor(Bundle bundle) {
        this.mBundle = bundle;
    }

    public String getId() {
        return this.mBundle.getString("id");
    }

    public List<String> getGroupMemberIds() {
        ensureGroupMemberIds();
        return this.mGroupMemberIds;
    }

    /* access modifiers changed from: package-private */
    public void ensureGroupMemberIds() {
        if (this.mGroupMemberIds == null) {
            this.mGroupMemberIds = this.mBundle.getStringArrayList("groupMemberIds");
            if (this.mGroupMemberIds == null) {
                this.mGroupMemberIds = Collections.emptyList();
            }
        }
    }

    public String getName() {
        return this.mBundle.getString("name");
    }

    public String getDescription() {
        return this.mBundle.getString("status");
    }

    public Uri getIconUri() {
        String string = this.mBundle.getString("iconUri");
        if (string == null) {
            return null;
        }
        return Uri.parse(string);
    }

    public boolean isEnabled() {
        return this.mBundle.getBoolean("enabled", true);
    }

    @Deprecated
    public boolean isConnecting() {
        return this.mBundle.getBoolean("connecting", false);
    }

    public int getConnectionState() {
        return this.mBundle.getInt("connectionState", 0);
    }

    public boolean canDisconnectAndKeepPlaying() {
        return this.mBundle.getBoolean("canDisconnect", false);
    }

    public IntentSender getSettingsActivity() {
        return (IntentSender) this.mBundle.getParcelable("settingsIntent");
    }

    public List<IntentFilter> getControlFilters() {
        ensureControlFilters();
        return this.mControlFilters;
    }

    /* access modifiers changed from: package-private */
    public void ensureControlFilters() {
        if (this.mControlFilters == null) {
            this.mControlFilters = this.mBundle.getParcelableArrayList("controlFilters");
            if (this.mControlFilters == null) {
                this.mControlFilters = Collections.emptyList();
            }
        }
    }

    public int getPlaybackType() {
        return this.mBundle.getInt("playbackType", 1);
    }

    public int getPlaybackStream() {
        return this.mBundle.getInt("playbackStream", -1);
    }

    public int getDeviceType() {
        return this.mBundle.getInt("deviceType");
    }

    public int getVolume() {
        return this.mBundle.getInt("volume");
    }

    public int getVolumeMax() {
        return this.mBundle.getInt("volumeMax");
    }

    public int getVolumeHandling() {
        return this.mBundle.getInt("volumeHandling", 0);
    }

    public int getPresentationDisplayId() {
        return this.mBundle.getInt("presentationDisplayId", -1);
    }

    public Bundle getExtras() {
        return this.mBundle.getBundle("extras");
    }

    public int getMinClientVersion() {
        return this.mBundle.getInt("minClientVersion", 1);
    }

    public int getMaxClientVersion() {
        return this.mBundle.getInt("maxClientVersion", Integer.MAX_VALUE);
    }

    public boolean isValid() {
        ensureControlFilters();
        return !TextUtils.isEmpty(getId()) && !TextUtils.isEmpty(getName()) && !this.mControlFilters.contains((Object) null);
    }

    public String toString() {
        return "MediaRouteDescriptor{ " + "id=" + getId() + ", groupMemberIds=" + getGroupMemberIds() + ", name=" + getName() + ", description=" + getDescription() + ", iconUri=" + getIconUri() + ", isEnabled=" + isEnabled() + ", isConnecting=" + isConnecting() + ", connectionState=" + getConnectionState() + ", controlFilters=" + Arrays.toString(getControlFilters().toArray()) + ", playbackType=" + getPlaybackType() + ", playbackStream=" + getPlaybackStream() + ", deviceType=" + getDeviceType() + ", volume=" + getVolume() + ", volumeMax=" + getVolumeMax() + ", volumeHandling=" + getVolumeHandling() + ", presentationDisplayId=" + getPresentationDisplayId() + ", extras=" + getExtras() + ", isValid=" + isValid() + ", minClientVersion=" + getMinClientVersion() + ", maxClientVersion=" + getMaxClientVersion() + " }";
    }

    public static MediaRouteDescriptor fromBundle(Bundle bundle) {
        if (bundle != null) {
            return new MediaRouteDescriptor(bundle);
        }
        return null;
    }

    public static final class Builder {
        private final Bundle mBundle;
        private ArrayList<IntentFilter> mControlFilters;
        private ArrayList<String> mGroupMemberIds;

        public Builder(String str, String str2) {
            this.mBundle = new Bundle();
            setId(str);
            setName(str2);
        }

        public Builder(MediaRouteDescriptor mediaRouteDescriptor) {
            if (mediaRouteDescriptor != null) {
                this.mBundle = new Bundle(mediaRouteDescriptor.mBundle);
                if (!mediaRouteDescriptor.getGroupMemberIds().isEmpty()) {
                    this.mGroupMemberIds = new ArrayList<>(mediaRouteDescriptor.getGroupMemberIds());
                }
                if (!mediaRouteDescriptor.getControlFilters().isEmpty()) {
                    this.mControlFilters = new ArrayList<>(mediaRouteDescriptor.mControlFilters);
                    return;
                }
                return;
            }
            throw new IllegalArgumentException("descriptor must not be null");
        }

        public Builder setId(String str) {
            this.mBundle.putString("id", str);
            return this;
        }

        public Builder setName(String str) {
            this.mBundle.putString("name", str);
            return this;
        }

        public Builder setDescription(String str) {
            this.mBundle.putString("status", str);
            return this;
        }

        public Builder setEnabled(boolean z) {
            this.mBundle.putBoolean("enabled", z);
            return this;
        }

        @Deprecated
        public Builder setConnecting(boolean z) {
            this.mBundle.putBoolean("connecting", z);
            return this;
        }

        public Builder addControlFilter(IntentFilter intentFilter) {
            if (intentFilter != null) {
                if (this.mControlFilters == null) {
                    this.mControlFilters = new ArrayList<>();
                }
                if (!this.mControlFilters.contains(intentFilter)) {
                    this.mControlFilters.add(intentFilter);
                }
                return this;
            }
            throw new IllegalArgumentException("filter must not be null");
        }

        public Builder addControlFilters(Collection<IntentFilter> collection) {
            if (collection != null) {
                if (!collection.isEmpty()) {
                    for (IntentFilter addControlFilter : collection) {
                        addControlFilter(addControlFilter);
                    }
                }
                return this;
            }
            throw new IllegalArgumentException("filters must not be null");
        }

        public Builder setPlaybackType(int i) {
            this.mBundle.putInt("playbackType", i);
            return this;
        }

        public Builder setPlaybackStream(int i) {
            this.mBundle.putInt("playbackStream", i);
            return this;
        }

        public Builder setDeviceType(int i) {
            this.mBundle.putInt("deviceType", i);
            return this;
        }

        public Builder setVolume(int i) {
            this.mBundle.putInt("volume", i);
            return this;
        }

        public Builder setVolumeMax(int i) {
            this.mBundle.putInt("volumeMax", i);
            return this;
        }

        public Builder setVolumeHandling(int i) {
            this.mBundle.putInt("volumeHandling", i);
            return this;
        }

        public Builder setPresentationDisplayId(int i) {
            this.mBundle.putInt("presentationDisplayId", i);
            return this;
        }

        public MediaRouteDescriptor build() {
            ArrayList<IntentFilter> arrayList = this.mControlFilters;
            if (arrayList != null) {
                this.mBundle.putParcelableArrayList("controlFilters", arrayList);
            }
            ArrayList<String> arrayList2 = this.mGroupMemberIds;
            if (arrayList2 != null) {
                this.mBundle.putStringArrayList("groupMemberIds", arrayList2);
            }
            return new MediaRouteDescriptor(this.mBundle);
        }
    }
}
