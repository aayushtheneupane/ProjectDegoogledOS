package p000;

import android.animation.TimeInterpolator;
import android.view.ScaleGestureDetector;
import com.google.android.apps.photosgo.editor.parameters.PipelineParams;

/* renamed from: bxj */
/* compiled from: PG */
final class bxj extends ScaleGestureDetector.SimpleOnScaleGestureListener {

    /* renamed from: a */
    private final /* synthetic */ bxl f3830a;

    public bxj(bxl bxl) {
        this.f3830a = bxl;
    }

    public final boolean onScale(ScaleGestureDetector scaleGestureDetector) {
        bxl bxl = this.f3830a;
        TimeInterpolator timeInterpolator = bxl.f3832a;
        if (bxl.f3845m == null || bxl.f3846n != -2) {
            return false;
        }
        float scaleFactor = scaleGestureDetector.getScaleFactor();
        float f = bxl.f3834b.x;
        float f2 = bxl.f3834b.y;
        PipelineParams pipelineParams = ((byc) bxl.f3841i).f3888c;
        float f3 = pipelineParams.cropLeft;
        float f4 = pipelineParams.cropTop;
        float f5 = pipelineParams.cropRight;
        PipelineParams magicPinch = bxl.f3842j.magicPinch(pipelineParams, ((f3 - f) / scaleFactor) + f, ((f4 - f2) / scaleFactor) + f2, f + ((f5 - f) / scaleFactor), ((pipelineParams.cropBottom - f2) / scaleFactor) + f2);
        if (magicPinch == null) {
            magicPinch = new PipelineParams();
        }
        bxl.mo2881a(magicPinch);
        bxl.f3839g.mo2884a();
        return true;
    }

    public final boolean onScaleBegin(ScaleGestureDetector scaleGestureDetector) {
        bxl bxl = this.f3830a;
        TimeInterpolator timeInterpolator = bxl.f3832a;
        bxl.f3846n = -2;
        bxl.f3834b.set(ihg.m13012a(bxl.f3835c.x, this.f3830a.f3836d), ihg.m13043b(this.f3830a.f3835c.y, this.f3830a.f3836d));
        return true;
    }

    public final void onScaleEnd(ScaleGestureDetector scaleGestureDetector) {
        bxl bxl = this.f3830a;
        TimeInterpolator timeInterpolator = bxl.f3832a;
        bxl.f3846n = -1;
    }
}
