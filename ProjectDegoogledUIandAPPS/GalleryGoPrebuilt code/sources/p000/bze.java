package p000;

import com.google.android.apps.photosgo.editor.parameters.PipelineParams;

/* renamed from: bze */
/* compiled from: PG */
final class bze implements bzy {
    /* renamed from: b */
    public final Object mo2916b(PipelineParams pipelineParams, Object obj) {
        return iol.m14227a((bzy) this, pipelineParams);
    }

    public final String toString() {
        return "Use sharp image";
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ Object mo2913a() {
        return false;
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ Object mo2914a(PipelineParams pipelineParams) {
        return Boolean.valueOf(pipelineParams.useSharpImage);
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ boolean mo2915a(PipelineParams pipelineParams, Object obj) {
        pipelineParams.mo3352b();
        boolean booleanValue = ((Boolean) obj).booleanValue();
        pipelineParams.f4836a |= pipelineParams.useSharpImage != booleanValue;
        pipelineParams.useSharpImage = booleanValue;
        return pipelineParams.mo3351a();
    }
}
