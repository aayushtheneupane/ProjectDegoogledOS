package p000;

import android.graphics.PointF;
import com.google.android.apps.photosgo.editor.parameters.PipelineParams;

/* renamed from: bzf */
/* compiled from: PG */
final class bzf implements bzy {

    /* renamed from: a */
    private final PointF f3925a = new PointF(0.5f, 0.5f);

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ Object mo2913a() {
        return this.f3925a;
    }

    public final String toString() {
        return "Focus ring center";
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ Object mo2914a(PipelineParams pipelineParams) {
        return m3852a(pipelineParams, new PointF());
    }

    /* renamed from: a */
    private static final PointF m3852a(PipelineParams pipelineParams, PointF pointF) {
        ((PointF) ife.m12898e((Object) pointF)).set(pipelineParams.depthFocusRingCenterX, pipelineParams.depthFocusRingCenterY);
        return pointF;
    }

    /* renamed from: b */
    public final /* bridge */ /* synthetic */ Object mo2916b(PipelineParams pipelineParams, Object obj) {
        return m3852a(pipelineParams, (PointF) obj);
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ boolean mo2915a(PipelineParams pipelineParams, Object obj) {
        PointF pointF = (PointF) obj;
        pipelineParams.mo3352b();
        float f = pointF.x;
        pipelineParams.f4836a |= !ihg.m13042a(pipelineParams.depthFocusRingCenterX, f);
        pipelineParams.depthFocusRingCenterX = f;
        float f2 = pointF.y;
        pipelineParams.f4836a |= !ihg.m13042a(pipelineParams.depthFocusRingCenterY, f2);
        pipelineParams.depthFocusRingCenterY = f2;
        return pipelineParams.mo3351a();
    }
}
