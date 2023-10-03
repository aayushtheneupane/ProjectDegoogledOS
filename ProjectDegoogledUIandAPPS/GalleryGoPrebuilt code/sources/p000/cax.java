package p000;

import android.graphics.PointF;
import com.google.android.apps.photosgo.editor.parameters.PipelineParams;

/* renamed from: cax */
/* compiled from: PG */
final class cax implements bzy {

    /* renamed from: a */
    private final PointF f3996a = new PointF(0.5f, 0.5f);

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ Object mo2913a() {
        return this.f3996a;
    }

    public final String toString() {
        return "Vignette center";
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ Object mo2914a(PipelineParams pipelineParams) {
        return m3976a(pipelineParams, new PointF());
    }

    /* renamed from: a */
    private static final PointF m3976a(PipelineParams pipelineParams, PointF pointF) {
        ((PointF) ife.m12898e((Object) pointF)).set(pipelineParams.vignetteCenterX, pipelineParams.vignetteCenterY);
        return pointF;
    }

    /* renamed from: b */
    public final /* bridge */ /* synthetic */ Object mo2916b(PipelineParams pipelineParams, Object obj) {
        return m3976a(pipelineParams, (PointF) obj);
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ boolean mo2915a(PipelineParams pipelineParams, Object obj) {
        PointF pointF = (PointF) obj;
        pipelineParams.mo3352b();
        float f = pointF.x;
        pipelineParams.f4836a |= !ihg.m13042a(pipelineParams.vignetteCenterX, f);
        pipelineParams.vignetteCenterX = f;
        float f2 = pointF.y;
        pipelineParams.f4836a |= !ihg.m13042a(pipelineParams.vignetteCenterY, f2);
        pipelineParams.vignetteCenterY = f2;
        return pipelineParams.mo3351a();
    }
}
