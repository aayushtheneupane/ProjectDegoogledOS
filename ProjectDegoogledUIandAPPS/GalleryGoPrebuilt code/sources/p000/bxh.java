package p000;

import android.graphics.RectF;
import com.google.android.apps.photosgo.editor.parameters.PipelineParams;

/* renamed from: bxh */
/* compiled from: PG */
final /* synthetic */ class bxh implements bxp {

    /* renamed from: a */
    private final bxl f3828a;

    public bxh(bxl bxl) {
        this.f3828a = bxl;
    }

    /* renamed from: a */
    public final void mo2791a() {
        bxl bxl = this.f3828a;
        bzy bzy = byu.f3915a;
        byh byh = ((byc) bxl.f3841i).f3888c.f4837b;
        byh byh2 = bxl.f3847o;
        if (!byh.equals(byh2) && !byh.mo2906a().equals(byh2)) {
            bxl.f3847o = byh;
            if (byh.f3904b) {
                float b = ((byc) bxl.f3841i).f3888c.f4837b.mo2907b();
                if (b == 0.0f) {
                    bxl.f3837e.set(((byq) byu.f3916b).f3914a);
                } else if (b <= 1.0f) {
                    float f = b / 2.0f;
                    bxl.f3837e.set(0.5f - f, 0.0f, f + 0.5f, 1.0f);
                } else {
                    float f2 = 1.0f / (b + b);
                    bxl.f3837e.set(0.0f, 0.5f - f2, 1.0f, f2 + 0.5f);
                }
            } else {
                bxl.f3837e.set(((byq) byu.f3916b).f3914a);
            }
            if (bxl.f3845m != null && !byh.equals(byh.f3903a)) {
                float b2 = byh.mo2907b();
                RectF b3 = byq.m3795b(((byc) bxl.f3841i).f3888c);
                bxl.f3842j.changeToDesiredCropRect(byr.m3800b(((byc) bxl.f3841i).f3888c).floatValue(), bys.m3805b(((byc) bxl.f3841i).f3888c).floatValue(), b2, b3.left, b3.top, b3.right, b3.bottom, b3);
                PipelineParams pipelineParams = ((byc) bxl.f3841i).f3888c;
                PipelineParams a = PipelineParams.m4770a(pipelineParams);
                byu.f3916b.mo2915a(a, b3);
                PipelineParams fitAndRotateRect = bxl.f3842j.fitAndRotateRect(pipelineParams, a, 1.0f);
                if (fitAndRotateRect == null) {
                    fitAndRotateRect = new PipelineParams();
                }
                bxl.mo2881a(fitAndRotateRect);
                bxo bxo = bxl.f3843k;
                bxo.f3860a = bxl.f3832a;
                bxo.f3861b = bxl.f3840h;
                bxo.mo2883a();
            }
        }
    }
}
