package p000;

import com.google.android.apps.photosgo.face.facenet.FaceNetDetector;
import java.io.Closeable;
import java.util.concurrent.Executor;

/* renamed from: cgk */
/* compiled from: PG */
final /* synthetic */ class cgk implements ice {

    /* renamed from: a */
    private final cgr f4330a;

    /* renamed from: b */
    private final cue f4331b;

    public cgk(cgr cgr, cue cue) {
        this.f4330a = cgr;
        this.f4331b = cue;
    }

    /* renamed from: a */
    public final ieh mo2539a() {
        hlj a;
        cgr cgr = this.f4330a;
        cue cue = this.f4331b;
        hlj a2 = hnb.m11765a("Face Detection job");
        try {
            cgr.f4354i.mo3869a(8);
            cji cji = cgr.f4349d.f4359a;
            cji.f4492b.mo3858a();
            try {
                a = hnb.m11765a("Create FaceNetDetector");
                long nativeCreate = FaceNetDetector.nativeCreate(cji.f4491a, "fssd_25_8bit_v2.tflite", "fssd_anchors_v2.pb");
                if (nativeCreate != 0) {
                    FaceNetDetector faceNetDetector = new FaceNetDetector(nativeCreate);
                    if (a != null) {
                        a.close();
                    }
                    cgt cgt = new cgt(faceNetDetector);
                    ieh a3 = cgr.f4353h.mo3847a(new cgl(cgr), new cgm(cgr, cgt), cue);
                    cgr.f4354i.mo3870a(a3, 9, 10);
                    cwn.m5509a(a3, "FaceDetectionJob: Failure", new Object[0]);
                    ieh a4 = a2.mo7548a(fxk.m9820a(a3, (Closeable) cgt, (Executor) cgr.f4352g));
                    if (a2 != null) {
                        a2.close();
                    }
                    return a4;
                }
                throw new RuntimeException("Could not initialize FaceNetDetector");
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
