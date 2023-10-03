package p000;

import java.io.Closeable;
import java.io.IOException;
import java.util.concurrent.Executor;

/* renamed from: csd */
/* compiled from: PG */
final /* synthetic */ class csd implements ice {

    /* renamed from: a */
    private final csi f5550a;

    /* renamed from: b */
    private final cue f5551b;

    public csd(csi csi, cue cue) {
        this.f5550a = csi;
        this.f5551b = cue;
    }

    /* renamed from: a */
    public final ieh mo2539a() {
        hlj a;
        csi csi = this.f5550a;
        cue cue = this.f5551b;
        hlj a2 = hnb.m11765a("Image labeling job");
        try {
            csi.f5567h.mo3869a(2);
            crz crz = csi.f5562c;
            try {
                a = hnb.m11765a("Create Native Image Classifier");
                iir g = gch.f10929f.mo8793g();
                iir g2 = gcg.f10924d.mo8793g();
                iir a3 = crz.m5340a(crz.f5531a, crz.f5532b);
                if (g2.f14319c) {
                    g2.mo8751b();
                    g2.f14319c = false;
                }
                gcg gcg = (gcg) g2.f14318b;
                gcf gcf = (gcf) a3.mo8770g();
                gcf.getClass();
                gcg.f10927b = gcf;
                gcg.f10926a |= 1;
                iir a4 = crz.m5340a(crz.f5531a, crz.f5533c);
                if (g2.f14319c) {
                    g2.mo8751b();
                    g2.f14319c = false;
                }
                gcg gcg2 = (gcg) g2.f14318b;
                gcf gcf2 = (gcf) a4.mo8770g();
                gcf2.getClass();
                gcg2.f10928c = gcf2;
                gcg2.f10926a = 2 | gcg2.f10926a;
                if (g.f14319c) {
                    g.mo8751b();
                    g.f14319c = false;
                }
                gch gch = (gch) g.f14318b;
                gcg gcg3 = (gcg) g2.mo8770g();
                gcg3.getClass();
                gch.f10933c = gcg3;
                gch.f10932b = 6;
                if (g.f14319c) {
                    g.mo8751b();
                    g.f14319c = false;
                }
                gch gch2 = (gch) g.f14318b;
                gch2.f10931a |= 4;
                gch2.f10934d = 100;
                float floatValue = crz.f5534d.floatValue();
                if (g.f14319c) {
                    g.mo8751b();
                    g.f14319c = false;
                }
                gch gch3 = (gch) g.f14318b;
                gch3.f10931a |= 8;
                gch3.f10935e = floatValue;
                csa csa = new csa(new gce((gch) g.mo8770g()));
                if (a != null) {
                    a.close();
                }
                ieh a5 = csi.f5566g.mo3847a(new cse(csi), new csf(csi, csa), cue);
                csi.f5567h.mo3870a(a5, 3, 4);
                cwn.m5509a(a5, "ImageLabelingJob: Job failed", new Object[0]);
                ieh a6 = a2.mo7548a(fxk.m9820a(csi.f5568i.mo2553a(a5, csi.f5565f), (Closeable) csa, (Executor) csi.f5565f));
                if (a2 != null) {
                    a2.close();
                }
                return a6;
            } catch (IOException e) {
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
