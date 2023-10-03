package p000;

import android.support.p002v7.widget.RecyclerView;

/* renamed from: cup */
/* compiled from: PG */
public enum cup implements iiz {
    UNKNOWN_JOB_TYPE(0),
    THUMBNAIL_JOB(1),
    IMAGE_LABELING_JOB(2),
    FACE_DETECTION_JOB(3),
    FACE_EMBEDDING_JOB(4),
    FACE_CLUSTERING_JOB(5),
    FACE_THUMBNAILING_JOB(6),
    FACE_CLUSTERING_WIPEOUT_JOB(7),
    IMAGE_COMPRESSION_JOB(8),
    CLIENT_SNAPSHOTTER_JOB(9);
    

    /* renamed from: i */
    public final int f5689i;

    /* renamed from: a */
    public static cup m5454a(int i) {
        switch (i) {
            case 0:
                return UNKNOWN_JOB_TYPE;
            case 1:
                return THUMBNAIL_JOB;
            case RecyclerView.SCROLL_STATE_SETTLING:
                return IMAGE_LABELING_JOB;
            case 3:
                return FACE_DETECTION_JOB;
            case 4:
                return FACE_EMBEDDING_JOB;
            case 5:
                return FACE_CLUSTERING_JOB;
            case 6:
                return FACE_THUMBNAILING_JOB;
            case 7:
                return FACE_CLUSTERING_WIPEOUT_JOB;
            case 8:
                return IMAGE_COMPRESSION_JOB;
            case 9:
                return CLIENT_SNAPSHOTTER_JOB;
            default:
                return null;
        }
    }

    /* renamed from: a */
    public static ijb m5455a() {
        return cuo.f5677a;
    }

    public final int getNumber() {
        return this.f5689i;
    }

    public final String toString() {
        return Integer.toString(this.f5689i);
    }

    private cup(int i) {
        this.f5689i = i;
    }
}
