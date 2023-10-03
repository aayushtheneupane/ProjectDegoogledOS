package com.android.dialer.multimedia;

import android.location.Location;
import android.net.Uri;
import android.text.TextUtils;
import com.android.dialer.common.LogUtil;
import com.android.dialer.multimedia.AutoValue_MultimediaData;
import com.google.auto.value.AutoValue;

@AutoValue
public abstract class MultimediaData {

    public static abstract class Builder {
        public abstract MultimediaData build();

        public Builder setImage(Uri uri, String str) {
            setImageUri(uri);
            setImageContentType(str);
            return this;
        }

        /* access modifiers changed from: package-private */
        public abstract Builder setImageContentType(String str);

        /* access modifiers changed from: package-private */
        public abstract Builder setImageUri(Uri uri);
    }

    static {
        builder().build();
    }

    public static Builder builder() {
        AutoValue_MultimediaData.Builder builder = new AutoValue_MultimediaData.Builder();
        builder.setImportant(false);
        return builder;
    }

    public abstract String getImageContentType();

    public abstract Uri getImageUri();

    public abstract Location getLocation();

    public abstract String getText();

    public boolean hasData() {
        return hasImageData() || !TextUtils.isEmpty(getText()) || getLocation() != null;
    }

    public boolean hasImageData() {
        return (getImageUri() == null || getImageContentType() == null) ? false : true;
    }

    public abstract boolean isImportant();

    public String toString() {
        return String.format("MultimediaData{subject: %s, location: %s, imageUrl: %s, imageContentType: %s, important: %b}", new Object[]{LogUtil.sanitizePii(getText()), LogUtil.sanitizePii(getLocation()), LogUtil.sanitizePii(getImageUri()), getImageContentType(), Boolean.valueOf(isImportant())});
    }
}
