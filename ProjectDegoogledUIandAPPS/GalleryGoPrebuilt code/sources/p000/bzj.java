package p000;

import android.graphics.PointF;
import com.google.android.apps.photosgo.editor.parameters.PipelineParams;

/* renamed from: bzj */
/* compiled from: PG */
public final class bzj implements bzy {

    /* renamed from: a */
    public final PointF f3934a = new PointF(0.5f, 0.5f);

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ Object mo2913a() {
        return this.f3934a;
    }

    public final String toString() {
        return "Zoom center";
    }

    /* renamed from: b */
    public static final PointF m3868b(PipelineParams pipelineParams) {
        return m3867a(pipelineParams, new PointF());
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ Object mo2914a(PipelineParams pipelineParams) {
        return m3868b(pipelineParams);
    }

    /* renamed from: a */
    private static final PointF m3867a(PipelineParams pipelineParams, PointF pointF) {
        ((PointF) ife.m12898e((Object) pointF)).set(pipelineParams.zoomCenterX, pipelineParams.zoomCenterY);
        return pointF;
    }

    /* renamed from: b */
    public final /* bridge */ /* synthetic */ Object mo2916b(PipelineParams pipelineParams, Object obj) {
        return m3867a(pipelineParams, (PointF) obj);
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ boolean mo2915a(PipelineParams pipelineParams, Object obj) {
        PointF pointF = (PointF) obj;
        pipelineParams.mo3352b();
        float f = pointF.x;
        pipelineParams.f4836a |= !ihg.m13042a(pipelineParams.zoomCenterX, f);
        pipelineParams.zoomCenterX = f;
        float f2 = pointF.y;
        pipelineParams.f4836a |= !ihg.m13042a(pipelineParams.zoomCenterY, f2);
        pipelineParams.zoomCenterY = f2;
        return pipelineParams.mo3351a();
    }
}
