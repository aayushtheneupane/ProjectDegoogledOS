package p000;

import android.media.MediaExtractor;
import java.io.Closeable;

/* renamed from: dgl */
/* compiled from: PG */
final /* synthetic */ class dgl implements Closeable {

    /* renamed from: a */
    private final MediaExtractor f6505a;

    public dgl(MediaExtractor mediaExtractor) {
        this.f6505a = mediaExtractor;
    }

    public final void close() {
        this.f6505a.release();
    }
}
