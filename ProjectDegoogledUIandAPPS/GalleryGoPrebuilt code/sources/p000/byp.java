package p000;

import com.google.android.apps.photosgo.editor.parameters.PipelineParams;

/* renamed from: byp */
/* compiled from: PG */
public final class byp implements bzy {
    /* renamed from: b */
    public final Object mo2916b(PipelineParams pipelineParams, Object obj) {
        return iol.m14227a((bzy) this, pipelineParams);
    }

    public final String toString() {
        return "Apply crop";
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ Object mo2913a() {
        return true;
    }

    /* renamed from: b */
    public static final Boolean m3789b(PipelineParams pipelineParams) {
        return Boolean.valueOf(pipelineParams.applyCrop);
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ Object mo2914a(PipelineParams pipelineParams) {
        return m3789b(pipelineParams);
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ boolean mo2915a(PipelineParams pipelineParams, Object obj) {
        pipelineParams.mo3352b();
        boolean booleanValue = ((Boolean) obj).booleanValue();
        pipelineParams.f4836a |= pipelineParams.applyCrop != booleanValue;
        pipelineParams.applyCrop = booleanValue;
        return pipelineParams.mo3351a();
    }
}
