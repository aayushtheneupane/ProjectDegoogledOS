package p000;

import android.media.MediaMetadataRetriever;
import java.io.Closeable;

/* renamed from: dgf */
/* compiled from: PG */
public final /* synthetic */ class dgf implements Closeable {

    /* renamed from: a */
    private final MediaMetadataRetriever f6498a;

    public dgf(MediaMetadataRetriever mediaMetadataRetriever) {
        this.f6498a = mediaMetadataRetriever;
    }

    public final void close() {
        this.f6498a.release();
    }
}
