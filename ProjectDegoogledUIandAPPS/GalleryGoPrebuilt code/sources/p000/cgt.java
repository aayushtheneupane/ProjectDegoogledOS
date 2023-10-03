package p000;

import com.google.android.apps.photosgo.face.facenet.FaceNetDetector;
import java.io.Closeable;

/* renamed from: cgt */
/* compiled from: PG */
public final class cgt implements Closeable {

    /* renamed from: a */
    public final FaceNetDetector f4360a;

    /* renamed from: b */
    public boolean f4361b = false;

    public /* synthetic */ cgt(FaceNetDetector faceNetDetector) {
        this.f4360a = faceNetDetector;
    }

    public final void close() {
        if (!this.f4361b) {
            this.f4361b = true;
            this.f4360a.close();
        }
    }
}
