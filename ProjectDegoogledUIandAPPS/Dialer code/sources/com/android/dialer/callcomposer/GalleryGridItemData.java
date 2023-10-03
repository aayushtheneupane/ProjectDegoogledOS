package com.android.dialer.callcomposer;

import android.database.Cursor;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.android.dialer.common.Assert;
import java.io.File;
import java.util.Objects;

public final class GalleryGridItemData implements Parcelable {
    public static final Parcelable.Creator<GalleryGridItemData> CREATOR = new Parcelable.Creator<GalleryGridItemData>() {
        public Object createFromParcel(Parcel parcel) {
            return new GalleryGridItemData(parcel, (C03971) null);
        }

        public Object[] newArray(int i) {
            return new GalleryGridItemData[i];
        }
    };
    public static final String[] IMAGE_PROJECTION = {"_id", "_data", "mime_type", "date_modified"};
    private long dateModifiedSeconds;
    private String filePath;
    private String mimeType;

    public GalleryGridItemData() {
    }

    public void bind(Cursor cursor) {
        String string = cursor.getString(2);
        Assert.isNotNull(string);
        this.mimeType = string;
        String string2 = cursor.getString(3);
        Assert.isNotNull(string2);
        String str = string2;
        this.dateModifiedSeconds = !TextUtils.isEmpty(str) ? Long.parseLong(str) : -1;
        String string3 = cursor.getString(1);
        Assert.isNotNull(string3);
        this.filePath = string3;
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (obj instanceof GalleryGridItemData) {
            GalleryGridItemData galleryGridItemData = (GalleryGridItemData) obj;
            return Objects.equals(this.mimeType, galleryGridItemData.mimeType) && Objects.equals(this.filePath, galleryGridItemData.filePath) && galleryGridItemData.dateModifiedSeconds == this.dateModifiedSeconds;
        }
    }

    public long getDateModifiedSeconds() {
        return this.dateModifiedSeconds;
    }

    public String getFilePath() {
        return this.filePath;
    }

    public Uri getFileUri() {
        if (TextUtils.isEmpty(this.filePath)) {
            return null;
        }
        return Uri.fromFile(new File(this.filePath));
    }

    public String getMimeType() {
        return this.mimeType;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.filePath, this.mimeType, Long.valueOf(this.dateModifiedSeconds)});
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.filePath);
        parcel.writeString(this.mimeType);
        parcel.writeLong(this.dateModifiedSeconds);
    }

    public GalleryGridItemData(GalleryGridItemData galleryGridItemData) {
        String filePath2 = galleryGridItemData.getFilePath();
        Assert.isNotNull(filePath2);
        this.filePath = filePath2;
        String mimeType2 = galleryGridItemData.getMimeType();
        Assert.isNotNull(mimeType2);
        this.mimeType = mimeType2;
        Long valueOf = Long.valueOf(galleryGridItemData.getDateModifiedSeconds());
        Assert.isNotNull(valueOf);
        this.dateModifiedSeconds = valueOf.longValue();
    }

    public GalleryGridItemData(Cursor cursor) {
        bind(cursor);
    }

    /* synthetic */ GalleryGridItemData(Parcel parcel, C03971 r2) {
        this.filePath = parcel.readString();
        this.mimeType = parcel.readString();
        this.dateModifiedSeconds = parcel.readLong();
    }
}
