package p000;

import com.google.android.apps.photosgo.editor.parameters.PipelineParams;

/* renamed from: bzi */
/* compiled from: PG */
public final class bzi implements bzy {
    /* renamed from: b */
    public final Object mo2916b(PipelineParams pipelineParams, Object obj) {
        return iol.m14227a((bzy) this, pipelineParams);
    }

    public final String toString() {
        return "Zoom scale";
    }

    /* renamed from: b */
    public static final Float m3861b() {
        return Float.valueOf(1.0f);
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ Object mo2913a() {
        return m3861b();
    }

    /* renamed from: b */
    public static final Float m3862b(PipelineParams pipelineParams) {
        return Float.valueOf(pipelineParams.zoomScale);
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ Object mo2914a(PipelineParams pipelineParams) {
        return m3862b(pipelineParams);
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ boolean mo2915a(PipelineParams pipelineParams, Object obj) {
        pipelineParams.mo3352b();
        float floatValue = ((Float) obj).floatValue();
        pipelineParams.f4836a |= !ihg.m13042a(pipelineParams.zoomScale, floatValue);
        pipelineParams.zoomScale = floatValue;
        return pipelineParams.mo3351a();
    }
}
