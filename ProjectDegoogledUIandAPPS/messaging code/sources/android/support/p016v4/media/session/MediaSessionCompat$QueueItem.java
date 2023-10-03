package android.support.p016v4.media.session;

import android.annotation.SuppressLint;
import android.media.session.MediaSession;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.p016v4.media.MediaDescriptionCompat;
import java.util.ArrayList;
import java.util.List;
import p026b.p027a.p030b.p031a.C0632a;

@SuppressLint({"BanParcelableUsage"})
/* renamed from: android.support.v4.media.session.MediaSessionCompat$QueueItem */
public final class MediaSessionCompat$QueueItem implements Parcelable {
    public static final Parcelable.Creator CREATOR = new C0104n();
    private final MediaDescriptionCompat mDescription;
    private final long mId;

    private MediaSessionCompat$QueueItem(MediaSession.QueueItem queueItem, MediaDescriptionCompat mediaDescriptionCompat, long j) {
        if (mediaDescriptionCompat == null) {
            throw new IllegalArgumentException("Description cannot be null.");
        } else if (j != -1) {
            this.mDescription = mediaDescriptionCompat;
            this.mId = j;
        } else {
            throw new IllegalArgumentException("Id cannot be QueueItem.UNKNOWN_ID");
        }
    }

    /* renamed from: d */
    public static List m97d(List list) {
        MediaSessionCompat$QueueItem mediaSessionCompat$QueueItem;
        if (list == null) {
            return null;
        }
        int i = Build.VERSION.SDK_INT;
        ArrayList arrayList = new ArrayList();
        for (Object next : list) {
            if (next != null) {
                int i2 = Build.VERSION.SDK_INT;
                MediaSession.QueueItem queueItem = (MediaSession.QueueItem) next;
                mediaSessionCompat$QueueItem = new MediaSessionCompat$QueueItem(queueItem, MediaDescriptionCompat.m83b(queueItem.getDescription()), queueItem.getQueueId());
            } else {
                mediaSessionCompat$QueueItem = null;
            }
            arrayList.add(mediaSessionCompat$QueueItem);
        }
        return arrayList;
    }

    public int describeContents() {
        return 0;
    }

    public String toString() {
        StringBuilder Pa = C0632a.m1011Pa("MediaSession.QueueItem {Description=");
        Pa.append(this.mDescription);
        Pa.append(", Id=");
        Pa.append(this.mId);
        Pa.append(" }");
        return Pa.toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        this.mDescription.writeToParcel(parcel, i);
        parcel.writeLong(this.mId);
    }

    MediaSessionCompat$QueueItem(Parcel parcel) {
        this.mDescription = (MediaDescriptionCompat) MediaDescriptionCompat.CREATOR.createFromParcel(parcel);
        this.mId = parcel.readLong();
    }
}
