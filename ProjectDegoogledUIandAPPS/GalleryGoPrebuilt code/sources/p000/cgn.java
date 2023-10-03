package p000;

import android.graphics.Bitmap;
import android.graphics.RectF;
import com.google.android.apps.photosgo.face.facenet.FaceNetDetector;
import java.util.Iterator;
import java.util.concurrent.Executor;

/* renamed from: cgn */
/* compiled from: PG */
final /* synthetic */ class cgn implements icf {

    /* renamed from: a */
    private final cgr f4335a;

    /* renamed from: b */
    private final cgt f4336b;

    /* renamed from: c */
    private final cyg f4337c;

    public cgn(cgr cgr, cgt cgt, cyg cyg) {
        this.f4335a = cgr;
        this.f4336b = cgt;
        this.f4337c = cyg;
    }

    /* renamed from: a */
    public final ieh mo2538a(Object obj) {
        boolean z;
        hso hso;
        Throwable th;
        cgr cgr = this.f4335a;
        cgt cgt = this.f4336b;
        cyg cyg = this.f4337c;
        long longValue = ((Long) cyg.mo3907a().get()).longValue();
        Bitmap a = cns.m4631a((Bitmap) obj, Bitmap.Config.ARGB_8888);
        if (!cgt.f4361b) {
            if (Math.max(a.getWidth(), a.getHeight()) <= 800) {
                z = true;
            } else {
                z = false;
            }
            ife.m12851a(z, "Bitmap must be, at most, %spx on its long edge (it was %s,%s).", (Object) 800, (Object) Integer.valueOf(a.getWidth()), (Object) Integer.valueOf(a.getHeight()));
            double d = 1.0d;
            try {
                hlj a2 = hnb.m11765a("Detect faces");
                try {
                    FaceNetDetector faceNetDetector = cgt.f4360a;
                    if (faceNetDetector.f4852b) {
                        throw new IllegalStateException("FaceNetDetector was already closed.");
                    } else if (a != null) {
                        byte[] nativeDetectFaces = faceNetDetector.nativeDetectFaces(faceNetDetector.f4851a, a);
                        gbw gbw = (gbw) iix.m13605a((iix) gbw.f10888b, nativeDetectFaces, iij.m13502b());
                        hsj j = hso.m12048j();
                        Iterator it = gbw.f10890a.iterator();
                        while (it.hasNext()) {
                            gbv gbv = (gbv) it.next();
                            double width = (double) a.getWidth();
                            Double.isNaN(width);
                            double d2 = d / width;
                            double height = (double) a.getHeight();
                            Double.isNaN(height);
                            gbv a3 = cjk.m4395a(gbv, d2, 1.0d / height);
                            gbs gbs = a3.f10874b;
                            if (gbs == null) {
                                gbs = gbs.f10849f;
                            }
                            cfe o = cff.m4256o();
                            o.mo3120a(longValue);
                            o.mo3119a(a.getHeight());
                            o.mo3124b(a.getWidth());
                            Iterator it2 = it;
                            o.f4246b = new RectF(gbs.f10852b, gbs.f10853c, gbs.f10854d, gbs.f10855e);
                            o.mo3123a(a3.mo8512ag());
                            gbs gbs2 = gbv.f10874b;
                            if (gbs2 == null) {
                                gbs2 = gbs.f10849f;
                            }
                            double log = Math.log((double) Math.abs(gbs2.f10854d - gbs2.f10852b)) * Math.log((double) Math.abs(gbs2.f10855e - gbs2.f10853c));
                            double d3 = (double) gbv.f10876d;
                            Double.isNaN(d3);
                            o.mo3118a(log * d3);
                            j.mo7908c(o.mo3117a());
                            it = it2;
                            d = 1.0d;
                        }
                        hso = j.mo7905a();
                        if (a2 != null) {
                            a2.close();
                        }
                        Object[] objArr = {Integer.valueOf(hso.size()), cyg.mo3923o()};
                        hsj j2 = hso.m12048j();
                        hvs i = hso.listIterator();
                        while (i.hasNext()) {
                            cff cff = (cff) i.next();
                            double max = (double) Math.max(cyg.mo3913f(), cyg.mo3911e());
                            Double.isNaN(max);
                            double min = Math.min(1.0d, 1600.0d / max);
                            int f = cyg.mo3913f();
                            int e = cyg.mo3911e();
                            float width2 = cff.mo3092b().width();
                            double height2 = (double) cff.mo3092b().height();
                            double d4 = (double) e;
                            Double.isNaN(d4);
                            Double.isNaN(height2);
                            double d5 = height2 * d4 * min;
                            double d6 = (double) width2;
                            double d7 = (double) f;
                            Double.isNaN(d7);
                            Double.isNaN(d6);
                            if (d6 * d7 * min >= 80.0d && d5 >= 80.0d) {
                                j2.mo7908c(cff);
                            }
                        }
                        return cgr.f4348c.mo7394a().mo6898b((icf) new cgp(cgr, cyg, j2), (Executor) cgr.f4352g).mo6894a();
                    } else {
                        throw new IllegalArgumentException("The bitmap is null.");
                    }
                } catch (ijh e2) {
                    throw new RuntimeException("Parsing returned Faces proto failed", e2);
                } catch (Throwable th2) {
                    th = th2;
                    if (a2 != null) {
                        a2.close();
                    }
                    throw th;
                }
            } catch (Exception e3) {
                cwn.m5515b((Throwable) e3, "FaceDetector: Failed to detect faces.", new Object[0]);
                hso = hso.m12047f();
            } catch (Throwable th3) {
                ifn.m12935a(th, th3);
            }
        } else {
            throw new IllegalStateException("FaceDetector was already closed.");
        }
    }
}
