package p000;

import com.google.android.apps.photosgo.editor.parameters.PipelineParams;

/* renamed from: cap */
/* compiled from: PG */
public final class cap implements bzy {
    /* renamed from: a */
    public final /* bridge */ /* synthetic */ Object mo2913a() {
        return car.ORIGINAL;
    }

    /* renamed from: b */
    public final Object mo2916b(PipelineParams pipelineParams, Object obj) {
        return iol.m14227a((bzy) this, pipelineParams);
    }

    public final String toString() {
        return "Preset";
    }

    /* renamed from: b */
    public static final car m3957b(PipelineParams pipelineParams) {
        return car.m3967a(pipelineParams.look);
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ Object mo2914a(PipelineParams pipelineParams) {
        return m3957b(pipelineParams);
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ boolean mo2915a(PipelineParams pipelineParams, Object obj) {
        pipelineParams.mo3352b();
        int i = ((car) obj).f3990p;
        pipelineParams.f4836a |= pipelineParams.look != i;
        pipelineParams.look = i;
        return pipelineParams.mo3351a();
    }
}
