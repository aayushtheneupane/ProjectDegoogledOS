package p000;

import com.google.android.libraries.vision.visionkit.recognition.classifier.NativeClassifier;
import java.io.Closeable;

/* renamed from: csa */
/* compiled from: PG */
public final class csa implements Closeable {

    /* renamed from: c */
    public static /* synthetic */ int f5539c;

    /* renamed from: a */
    public final gce f5540a;

    /* renamed from: b */
    public boolean f5541b = false;

    static {
        System.loadLibrary("native");
    }

    public csa(gce gce) {
        this.f5540a = gce;
    }

    public final void close() {
        if (!this.f5541b) {
            this.f5541b = true;
            gce gce = this.f5540a;
            long j = gce.f10919a;
            if (j != 0) {
                NativeClassifier.close(j);
                gce.f10919a = 0;
            }
        }
    }
}
