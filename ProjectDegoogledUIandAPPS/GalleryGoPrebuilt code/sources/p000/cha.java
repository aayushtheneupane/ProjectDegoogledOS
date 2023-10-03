package p000;

import android.graphics.Bitmap;
import android.net.Uri;
import java.util.concurrent.Executor;

/* renamed from: cha */
/* compiled from: PG */
final /* synthetic */ class cha implements icf {

    /* renamed from: a */
    private final chf f4377a;

    /* renamed from: b */
    private final cgv f4378b;

    /* renamed from: c */
    private final che f4379c;

    /* renamed from: d */
    private final cff f4380d;

    public cha(chf chf, cgv cgv, che che, cff cff) {
        this.f4377a = chf;
        this.f4378b = cgv;
        this.f4379c = che;
        this.f4380d = cff;
    }

    /* renamed from: a */
    public final ieh mo2538a(Object obj) {
        ieh ieh;
        Bitmap bitmap;
        chf chf = this.f4377a;
        cgv cgv = this.f4378b;
        che che = this.f4379c;
        cff cff = this.f4380d;
        Uri O = ((cyg) obj).mo3991O();
        if (O.equals(che.f4388a) && (bitmap = che.f4389b) != null) {
            ieh = ife.m12820a((Object) bitmap);
        } else {
            hlj a = hnb.m11765a("Load Scaled Image");
            try {
                ieh = a.mo7548a(chf.f4393d.mo3289a(O, chf.f4394e.mo3298b(1600, 1600), new chd(che, O)));
                if (a != null) {
                    a.close();
                }
            } catch (Throwable th) {
                ifn.m12935a(th, th);
            }
        }
        return gte.m10771a(ieh, (icf) new chc(chf, cgv, cff), (Executor) chf.f4395f);
        throw th;
    }
}
