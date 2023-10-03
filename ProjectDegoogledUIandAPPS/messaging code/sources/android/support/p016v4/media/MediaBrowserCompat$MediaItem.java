package android.support.p016v4.media;

import android.annotation.SuppressLint;
import android.media.browse.MediaBrowser;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.List;

@SuppressLint({"BanParcelableUsage"})
/* renamed from: android.support.v4.media.MediaBrowserCompat$MediaItem */
public class MediaBrowserCompat$MediaItem implements Parcelable {
    public static final Parcelable.Creator CREATOR = new C0079i();
    private final MediaDescriptionCompat mDescription;
    private final int mFlags;

    public MediaBrowserCompat$MediaItem(MediaDescriptionCompat mediaDescriptionCompat, int i) {
        if (mediaDescriptionCompat == null) {
            throw new IllegalArgumentException("description cannot be null");
        } else if (!TextUtils.isEmpty(mediaDescriptionCompat.getMediaId())) {
            this.mFlags = i;
            this.mDescription = mediaDescriptionCompat;
        } else {
            throw new IllegalArgumentException("description must have a non-empty media id");
        }
    }

    /* renamed from: c */
    public static List m82c(List list) {
        MediaBrowserCompat$MediaItem mediaBrowserCompat$MediaItem;
        if (list == null) {
            return null;
        }
        int i = Build.VERSION.SDK_INT;
        ArrayList arrayList = new ArrayList(list.size());
        for (Object next : list) {
            if (next != null) {
                int i2 = Build.VERSION.SDK_INT;
                MediaBrowser.MediaItem mediaItem = (MediaBrowser.MediaItem) next;
                mediaBrowserCompat$MediaItem = new MediaBrowserCompat$MediaItem(MediaDescriptionCompat.m83b(mediaItem.getDescription()), mediaItem.getFlags());
            } else {
                mediaBrowserCompat$MediaItem = null;
            }
            arrayList.add(mediaBrowserCompat$MediaItem);
        }
        return arrayList;
    }

    public int describeContents() {
        return 0;
    }

    public String toString() {
        return "MediaItem{" + "mFlags=" + this.mFlags + ", mDescription=" + this.mDescription + '}';
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.mFlags);
        this.mDescription.writeToParcel(parcel, i);
    }

    MediaBrowserCompat$MediaItem(Parcel parcel) {
        this.mFlags = parcel.readInt();
        this.mDescription = (MediaDescriptionCompat) MediaDescriptionCompat.CREATOR.createFromParcel(parcel);
    }
}
