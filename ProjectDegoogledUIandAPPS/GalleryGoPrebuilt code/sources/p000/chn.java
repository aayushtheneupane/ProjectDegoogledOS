package p000;

import android.graphics.Rect;
import android.graphics.RectF;
import android.net.Uri;

/* renamed from: chn */
/* compiled from: PG */
final /* synthetic */ class chn implements icf {

    /* renamed from: a */
    private final cho f4408a;

    /* renamed from: b */
    private final cff f4409b;

    /* renamed from: c */
    private final icf f4410c;

    public chn(cho cho, cff cff, icf icf) {
        this.f4408a = cho;
        this.f4409b = cff;
        this.f4410c = icf;
    }

    /* renamed from: a */
    public final ieh mo2538a(Object obj) {
        cho cho = this.f4408a;
        cff cff = this.f4409b;
        icf icf = this.f4410c;
        cyg cyg = (cyg) obj;
        cnr cnr = cho.f4413b;
        Uri O = cyg.mo3991O();
        bdx bdx = cho.f4411e;
        int Q = cyg.mo3993Q();
        int P = cyg.mo3992P();
        RectF b = cff.mo3092b();
        float f = (float) P;
        float f2 = (float) Q;
        RectF rectF = new RectF(b.left * f, b.top * f2, b.right * f, b.bottom * f2);
        float centerX = rectF.centerX();
        float centerY = rectF.centerY();
        float max = Math.max(rectF.width(), rectF.height());
        float f3 = max / 2.0f;
        if (max > rectF.height()) {
            centerY += (max - rectF.height()) / 6.0f;
        }
        float max2 = Math.max(centerX, f3);
        float max3 = Math.max(centerY, f3);
        float min = Math.min(max2, f - f3);
        float min2 = Math.min(max3, f2 - f3);
        Rect rect = new Rect();
        icf icf2 = icf;
        new RectF(min - f3, min2 - f3, min + f3, min2 + f3).round(rect);
        if (!new Rect(0, 0, P, Q).contains(rect)) {
            RectF rectF2 = new RectF(0.0f, 0.0f, f, f2);
            float centerX2 = rectF2.centerX();
            float centerY2 = rectF2.centerY();
            float min3 = ((float) Math.min(P, Q)) / 2.0f;
            rect = new Rect();
            new RectF(centerX2 - min3, centerY2 - min3, centerX2 + min3, centerY2 + min3).round(rect);
        }
        int i = cho.f4415d;
        return cnr.mo3289a(O, bdx.mo1856a((ard) new cpf(rect, new Rect(0, 0, i, i))).mo1850a(cyg.mo3992P(), cyg.mo3993Q()), icf2);
    }
}
