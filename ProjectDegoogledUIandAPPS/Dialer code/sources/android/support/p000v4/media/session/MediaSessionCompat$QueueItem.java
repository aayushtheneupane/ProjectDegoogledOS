package android.support.p000v4.media.session;

import android.media.session.MediaSession;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.p000v4.media.MediaDescriptionCompat;
import com.android.tools.p006r8.GeneratedOutlineSupport;
import java.util.ArrayList;
import java.util.List;

/* renamed from: android.support.v4.media.session.MediaSessionCompat$QueueItem */
public final class MediaSessionCompat$QueueItem implements Parcelable {
    public static final Parcelable.Creator<MediaSessionCompat$QueueItem> CREATOR = new Parcelable.Creator<MediaSessionCompat$QueueItem>() {
        public Object createFromParcel(Parcel parcel) {
            return new MediaSessionCompat$QueueItem(parcel);
        }

        public Object[] newArray(int i) {
            return new MediaSessionCompat$QueueItem[i];
        }
    };
    private final MediaDescriptionCompat mDescription;
    private final long mId;

    private MediaSessionCompat$QueueItem(Object obj, MediaDescriptionCompat mediaDescriptionCompat, long j) {
        if (mediaDescriptionCompat == null) {
            throw new IllegalArgumentException("Description cannot be null.");
        } else if (j != -1) {
            this.mDescription = mediaDescriptionCompat;
            this.mId = j;
        } else {
            throw new IllegalArgumentException("Id cannot be QueueItem.UNKNOWN_ID");
        }
    }

    public static List<MediaSessionCompat$QueueItem> fromQueueItemList(List<?> list) {
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
                mediaSessionCompat$QueueItem = new MediaSessionCompat$QueueItem(next, MediaDescriptionCompat.fromMediaDescription(queueItem.getDescription()), queueItem.getQueueId());
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
        StringBuilder outline13 = GeneratedOutlineSupport.outline13("MediaSession.QueueItem {Description=");
        outline13.append(this.mDescription);
        outline13.append(", Id=");
        outline13.append(this.mId);
        outline13.append(" }");
        return outline13.toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        this.mDescription.writeToParcel(parcel, i);
        parcel.writeLong(this.mId);
    }

    MediaSessionCompat$QueueItem(Parcel parcel) {
        this.mDescription = MediaDescriptionCompat.CREATOR.createFromParcel(parcel);
        this.mId = parcel.readLong();
    }
}
