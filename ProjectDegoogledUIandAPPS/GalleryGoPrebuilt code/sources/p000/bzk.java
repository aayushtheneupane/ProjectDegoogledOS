package p000;

import android.graphics.RectF;
import com.google.android.apps.photosgo.editor.parameters.PipelineParams;

/* renamed from: bzk */
/* compiled from: PG */
final class bzk implements bzy {

    /* renamed from: a */
    private final RectF f3935a = new RectF();

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ Object mo2913a() {
        return this.f3935a;
    }

    public final String toString() {
        return "Margins";
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ Object mo2914a(PipelineParams pipelineParams) {
        return m3873a(pipelineParams, new RectF());
    }

    /* renamed from: a */
    private static final RectF m3873a(PipelineParams pipelineParams, RectF rectF) {
        ((RectF) ife.m12898e((Object) rectF)).set(pipelineParams.marginLeft, pipelineParams.marginTop, pipelineParams.marginRight, pipelineParams.marginBottom);
        return rectF;
    }

    /* renamed from: b */
    public final /* bridge */ /* synthetic */ Object mo2916b(PipelineParams pipelineParams, Object obj) {
        return m3873a(pipelineParams, (RectF) obj);
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ boolean mo2915a(PipelineParams pipelineParams, Object obj) {
        RectF rectF = (RectF) obj;
        pipelineParams.mo3352b();
        float f = rectF.left;
        pipelineParams.f4836a |= !ihg.m13042a(pipelineParams.marginLeft, f);
        pipelineParams.marginLeft = f;
        float f2 = rectF.top;
        pipelineParams.f4836a |= !ihg.m13042a(pipelineParams.marginTop, f2);
        pipelineParams.marginTop = f2;
        float f3 = rectF.right;
        pipelineParams.f4836a |= !ihg.m13042a(pipelineParams.marginRight, f3);
        pipelineParams.marginRight = f3;
        float f4 = rectF.bottom;
        pipelineParams.f4836a |= !ihg.m13042a(pipelineParams.marginBottom, f4);
        pipelineParams.marginBottom = f4;
        return pipelineParams.mo3351a();
    }
}
