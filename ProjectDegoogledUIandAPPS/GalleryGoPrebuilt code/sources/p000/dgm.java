package p000;

import android.media.MediaExtractor;
import java.io.Closeable;

/* renamed from: dgm */
/* compiled from: PG */
final /* synthetic */ class dgm implements Closeable {

    /* renamed from: a */
    private final MediaExtractor f6506a;

    public dgm(MediaExtractor mediaExtractor) {
        this.f6506a = mediaExtractor;
    }

    public final void close() {
        this.f6506a.release();
    }
}
