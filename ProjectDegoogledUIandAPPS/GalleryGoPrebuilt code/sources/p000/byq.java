package p000;

import android.graphics.RectF;
import com.google.android.apps.photosgo.editor.parameters.PipelineParams;

/* renamed from: byq */
/* compiled from: PG */
public final class byq implements bzy {

    /* renamed from: a */
    public final RectF f3914a = new RectF(0.0f, 0.0f, 1.0f, 1.0f);

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ Object mo2913a() {
        return this.f3914a;
    }

    public final String toString() {
        return "Crop rect";
    }

    /* renamed from: b */
    public static final RectF m3795b(PipelineParams pipelineParams) {
        return m3794a(pipelineParams, new RectF());
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ Object mo2914a(PipelineParams pipelineParams) {
        return m3795b(pipelineParams);
    }

    /* renamed from: a */
    private static final RectF m3794a(PipelineParams pipelineParams, RectF rectF) {
        ((RectF) ife.m12898e((Object) rectF)).set(pipelineParams.cropLeft, pipelineParams.cropTop, pipelineParams.cropRight, pipelineParams.cropBottom);
        return rectF;
    }

    /* renamed from: b */
    public final /* bridge */ /* synthetic */ Object mo2916b(PipelineParams pipelineParams, Object obj) {
        return m3794a(pipelineParams, (RectF) obj);
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ boolean mo2915a(PipelineParams pipelineParams, Object obj) {
        RectF rectF = (RectF) obj;
        pipelineParams.mo3352b();
        float f = rectF.left;
        pipelineParams.f4836a |= !ihg.m13042a(pipelineParams.cropLeft, f);
        pipelineParams.cropLeft = f;
        float f2 = rectF.top;
        pipelineParams.f4836a |= !ihg.m13042a(pipelineParams.cropTop, f2);
        pipelineParams.cropTop = f2;
        float f3 = rectF.right;
        pipelineParams.f4836a |= !ihg.m13042a(pipelineParams.cropRight, f3);
        pipelineParams.cropRight = f3;
        float f4 = rectF.bottom;
        pipelineParams.f4836a |= !ihg.m13042a(pipelineParams.cropBottom, f4);
        pipelineParams.cropBottom = f4;
        return pipelineParams.mo3351a();
    }
}
