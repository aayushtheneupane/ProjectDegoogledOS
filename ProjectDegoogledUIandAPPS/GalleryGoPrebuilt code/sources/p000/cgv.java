package p000;

import com.google.android.apps.photosgo.face.facenet.FaceNetEmbedder;
import java.io.Closeable;

/* renamed from: cgv */
/* compiled from: PG */
public final class cgv implements Closeable {

    /* renamed from: a */
    public final FaceNetEmbedder f4363a;

    /* renamed from: b */
    public boolean f4364b = false;

    public /* synthetic */ cgv(FaceNetEmbedder faceNetEmbedder) {
        this.f4363a = faceNetEmbedder;
    }

    public final void close() {
        if (!this.f4364b) {
            this.f4364b = true;
            this.f4363a.close();
        }
    }
}
