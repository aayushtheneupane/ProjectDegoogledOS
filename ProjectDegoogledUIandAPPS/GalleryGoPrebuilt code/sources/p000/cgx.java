package p000;

import com.google.android.apps.photosgo.face.facenet.FaceNetEmbedder;
import java.io.Closeable;
import java.util.concurrent.Executor;

/* renamed from: cgx */
/* compiled from: PG */
final /* synthetic */ class cgx implements ice {

    /* renamed from: a */
    private final chf f4366a;

    /* renamed from: b */
    private final cue f4367b;

    public cgx(chf chf, cue cue) {
        this.f4366a = chf;
        this.f4367b = cue;
    }

    /* renamed from: a */
    public final ieh mo2539a() {
        hlj a;
        chf chf = this.f4366a;
        cue cue = this.f4367b;
        hlj a2 = hnb.m11765a("Face Embedding job");
        try {
            chf.f4397h.mo3869a(11);
            cjj cjj = chf.f4392c.f4362a;
            cjj.f4494b.mo3858a();
            try {
                a = hnb.m11765a("Create FaceNetEmbedder");
                long nativeCreate = FaceNetEmbedder.nativeCreate(cjj.f4493a, "facenet_mobile_v1_8bits_tflite");
                if (nativeCreate != 0) {
                    FaceNetEmbedder faceNetEmbedder = new FaceNetEmbedder(nativeCreate);
                    if (a != null) {
                        a.close();
                    }
                    cgv cgv = new cgv(faceNetEmbedder);
                    ieh a3 = chf.f4396g.mo3847a(new cgy(chf), new cgz(chf, cgv, new che((byte[]) null)), cue);
                    chf.f4397h.mo3870a(a3, 12, 13);
                    cwn.m5509a(a3, "FaceEmbeddingJob: job failed", new Object[0]);
                    ieh a4 = a2.mo7548a(fxk.m9820a(a3, (Closeable) cgv, (Executor) chf.f4395f));
                    if (a2 != null) {
                        a2.close();
                    }
                    return a4;
                }
                throw new RuntimeException("Could not initialize FaceNetEmbedder");
            } catch (Exception e) {
                throw new RuntimeException(e);
            } catch (Throwable th) {
                ifn.m12935a(th, th);
            }
            throw th;
        } catch (Throwable th2) {
            if (a2 != null) {
                try {
                    a2.close();
                } catch (Throwable th3) {
                    ifn.m12935a(th2, th3);
                }
            }
            throw th2;
        }
    }
}
