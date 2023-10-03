package p000;

import com.google.android.apps.photosgo.editor.parameters.PipelineParams;

/* renamed from: bzp */
/* compiled from: PG */
final class bzp implements bzy {
    /* renamed from: b */
    public final Object mo2916b(PipelineParams pipelineParams, Object obj) {
        return iol.m14227a((bzy) this, pipelineParams);
    }

    public final String toString() {
        return "Highlights";
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ Object mo2913a() {
        return Float.valueOf(0.0f);
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ Object mo2914a(PipelineParams pipelineParams) {
        return Float.valueOf(pipelineParams.highlights);
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ boolean mo2915a(PipelineParams pipelineParams, Object obj) {
        pipelineParams.mo3352b();
        float floatValue = ((Float) obj).floatValue();
        pipelineParams.f4836a |= !ihg.m13042a(pipelineParams.highlights, floatValue);
        pipelineParams.highlights = floatValue;
        return pipelineParams.mo3351a();
    }
}
