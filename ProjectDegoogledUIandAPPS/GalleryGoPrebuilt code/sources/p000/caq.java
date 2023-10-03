package p000;

import com.google.android.apps.photosgo.editor.parameters.PipelineParams;

/* renamed from: caq */
/* compiled from: PG */
public final class caq implements bzy {
    /* renamed from: b */
    public final Object mo2916b(PipelineParams pipelineParams, Object obj) {
        return iol.m14227a((bzy) this, pipelineParams);
    }

    public final String toString() {
        return "Preset strength";
    }

    /* renamed from: b */
    public static final Float m3962b() {
        return Float.valueOf(0.5f);
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ Object mo2913a() {
        return m3962b();
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ Object mo2914a(PipelineParams pipelineParams) {
        return Float.valueOf(pipelineParams.lookIntensity);
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ boolean mo2915a(PipelineParams pipelineParams, Object obj) {
        pipelineParams.mo3352b();
        float floatValue = ((Float) obj).floatValue();
        pipelineParams.f4836a |= !ihg.m13042a(pipelineParams.lookIntensity, floatValue);
        pipelineParams.lookIntensity = floatValue;
        return pipelineParams.mo3351a();
    }
}
