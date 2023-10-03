package p000;

import android.media.MediaMuxer;
import java.io.Closeable;

/* renamed from: dgk */
/* compiled from: PG */
final /* synthetic */ class dgk implements Closeable {

    /* renamed from: a */
    private final MediaMuxer f6504a;

    public dgk(MediaMuxer mediaMuxer) {
        this.f6504a = mediaMuxer;
    }

    public final void close() {
        this.f6504a.release();
    }
}
