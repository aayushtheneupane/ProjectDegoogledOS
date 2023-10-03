package p000;

import com.google.android.apps.photosgo.editor.parameters.PipelineParams;

/* renamed from: byx */
/* compiled from: PG */
public final class byx implements bzy {
    /* renamed from: b */
    public final Object mo2916b(PipelineParams pipelineParams, Object obj) {
        return iol.m14227a((bzy) this, pipelineParams);
    }

    public final String toString() {
        return "Crop overlay angle";
    }

    /* renamed from: b */
    public static final Float m3822b() {
        return Float.valueOf(0.0f);
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ Object mo2913a() {
        return m3822b();
    }

    /* renamed from: b */
    public static final Float m3823b(PipelineParams pipelineParams) {
        return Float.valueOf(pipelineParams.cropAngle);
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ Object mo2914a(PipelineParams pipelineParams) {
        return m3823b(pipelineParams);
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ boolean mo2915a(PipelineParams pipelineParams, Object obj) {
        pipelineParams.mo3352b();
        float floatValue = ((Float) obj).floatValue();
        pipelineParams.f4836a |= !ihg.m13042a(pipelineParams.cropAngle, floatValue);
        pipelineParams.cropAngle = floatValue;
        return pipelineParams.mo3351a();
    }
}
