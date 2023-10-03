package p000;

import android.graphics.Bitmap;
import com.google.android.apps.photosgo.face.facenet.FaceNetEmbedder;

/* renamed from: chc */
/* compiled from: PG */
final /* synthetic */ class chc implements icf {

    /* renamed from: a */
    private final chf f4383a;

    /* renamed from: b */
    private final cgv f4384b;

    /* renamed from: c */
    private final cff f4385c;

    public chc(chf chf, cgv cgv, cff cff) {
        this.f4383a = chf;
        this.f4384b = cgv;
        this.f4385c = cff;
    }

    /* renamed from: a */
    public final ieh mo2538a(Object obj) {
        hlj a;
        chf chf = this.f4383a;
        cgv cgv = this.f4384b;
        cff cff = this.f4385c;
        Bitmap a2 = cns.m4631a((Bitmap) obj, Bitmap.Config.ARGB_8888);
        if (!cgv.f4364b) {
            ife.m12851a(Math.max(a2.getWidth(), a2.getHeight()) <= 1600, "Bitmap must be, at most, %spx on its long edge (it was %s,%s).", (Object) 1600, (Object) Integer.valueOf(a2.getWidth()), (Object) Integer.valueOf(a2.getHeight()));
            try {
                a = hnb.m11765a("Native compute embedding");
                gbv a3 = cjk.m4395a((gbv) iix.m13603a((iix) gbv.f10871o, cff.mo3097f()), (double) a2.getWidth(), (double) a2.getHeight());
                FaceNetEmbedder faceNetEmbedder = cgv.f4363a;
                iir iir = (iir) a3.mo8790b(5);
                iir.mo8503a((iix) a3);
                if (faceNetEmbedder.f4854b) {
                    throw new IllegalStateException("FaceNetEmbedder was already closed.");
                } else if (a2 != null) {
                    ihw a4 = ihw.m13162a(faceNetEmbedder.nativeRecognizeFace(faceNetEmbedder.f4853a, a2, ((gbv) iir.mo8770g()).mo8512ag()));
                    if (iir.f14319c) {
                        iir.mo8751b();
                        iir.f14319c = false;
                    }
                    gbv gbv = (gbv) iir.f14318b;
                    a4.getClass();
                    gbv.f10873a |= 32;
                    gbv.f10880h = a4;
                    byte[] j = gbv.f10880h.mo8625j();
                    cfe m = cff.mo3105m();
                    m.mo3126b(j);
                    m.mo3124b(a2.getWidth());
                    m.mo3119a(a2.getHeight());
                    m.mo3123a(a3.mo8512ag());
                    m.mo3125b(true);
                    cff a5 = m.mo3117a();
                    if (a != null) {
                        a.close();
                    }
                    cff = a5;
                    return chf.f4391b.f4469a.mo2656a(new cij(hso.m12033a((Object) cff)));
                } else {
                    throw new IllegalArgumentException("The bitmap is null.");
                }
            } catch (Exception e) {
                cwn.m5515b((Throwable) e, "FaceEmbedder: Failure to recognize faces.", new Object[0]);
            } catch (Throwable th) {
                ifn.m12935a(th, th);
            }
        } else {
            throw new IllegalStateException("FaceEmbedder was already closed.");
        }
        throw th;
    }
}
