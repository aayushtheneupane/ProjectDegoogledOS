package com.android.dialer.multimedia;

import android.location.Location;
import android.net.Uri;
import com.android.dialer.multimedia.MultimediaData;
import com.android.tools.p006r8.GeneratedOutlineSupport;

final class AutoValue_MultimediaData extends MultimediaData {
    private final String imageContentType;
    private final Uri imageUri;
    private final boolean important;
    private final Location location;
    private final String text;

    static final class Builder extends MultimediaData.Builder {
        private String imageContentType;
        private Uri imageUri;
        private Boolean important;
        private Location location;
        private String text;

        Builder() {
        }

        public MultimediaData build() {
            String str = "";
            if (this.important == null) {
                str = GeneratedOutlineSupport.outline8(str, " important");
            }
            if (str.isEmpty()) {
                return new AutoValue_MultimediaData(this.text, this.location, this.imageUri, this.imageContentType, this.important.booleanValue(), (C05041) null);
            }
            throw new IllegalStateException(GeneratedOutlineSupport.outline8("Missing required properties:", str));
        }

        /* access modifiers changed from: package-private */
        public MultimediaData.Builder setImageContentType(String str) {
            this.imageContentType = str;
            return this;
        }

        /* access modifiers changed from: package-private */
        public MultimediaData.Builder setImageUri(Uri uri) {
            this.imageUri = uri;
            return this;
        }

        public MultimediaData.Builder setImportant(boolean z) {
            this.important = Boolean.valueOf(z);
            return this;
        }
    }

    /* synthetic */ AutoValue_MultimediaData(String str, Location location2, Uri uri, String str2, boolean z, C05041 r6) {
        this.text = str;
        this.location = location2;
        this.imageUri = uri;
        this.imageContentType = str2;
        this.important = z;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof MultimediaData)) {
            return false;
        }
        String str = this.text;
        if (str != null ? str.equals(((AutoValue_MultimediaData) obj).text) : ((AutoValue_MultimediaData) obj).text == null) {
            Location location2 = this.location;
            if (location2 != null ? location2.equals(((AutoValue_MultimediaData) obj).location) : ((AutoValue_MultimediaData) obj).location == null) {
                Uri uri = this.imageUri;
                if (uri != null ? uri.equals(((AutoValue_MultimediaData) obj).imageUri) : ((AutoValue_MultimediaData) obj).imageUri == null) {
                    String str2 = this.imageContentType;
                    if (str2 != null ? str2.equals(((AutoValue_MultimediaData) obj).imageContentType) : ((AutoValue_MultimediaData) obj).imageContentType == null) {
                        if (this.important == ((AutoValue_MultimediaData) obj).important) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public String getImageContentType() {
        return this.imageContentType;
    }

    public Uri getImageUri() {
        return this.imageUri;
    }

    public Location getLocation() {
        return this.location;
    }

    public String getText() {
        return this.text;
    }

    public int hashCode() {
        String str = this.text;
        int i = 0;
        int hashCode = ((str == null ? 0 : str.hashCode()) ^ 1000003) * 1000003;
        Location location2 = this.location;
        int hashCode2 = (hashCode ^ (location2 == null ? 0 : location2.hashCode())) * 1000003;
        Uri uri = this.imageUri;
        int hashCode3 = (hashCode2 ^ (uri == null ? 0 : uri.hashCode())) * 1000003;
        String str2 = this.imageContentType;
        if (str2 != null) {
            i = str2.hashCode();
        }
        return (this.important ? 1231 : 1237) ^ ((hashCode3 ^ i) * 1000003);
    }

    public boolean isImportant() {
        return this.important;
    }
}
