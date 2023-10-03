package p000;

import com.google.android.apps.photosgo.editor.parameters.PipelineParams;

/* renamed from: byt */
/* compiled from: PG */
final class byt implements bzy {
    /* renamed from: a */
    public final /* bridge */ /* synthetic */ Object mo2913a() {
        return byh.f3903a;
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ Object mo2914a(PipelineParams pipelineParams) {
        return pipelineParams.f4837b;
    }

    /* renamed from: b */
    public final Object mo2916b(PipelineParams pipelineParams, Object obj) {
        return iol.m14227a((bzy) this, pipelineParams);
    }

    public final String toString() {
        return "Aspect ratio";
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ boolean mo2915a(PipelineParams pipelineParams, Object obj) {
        byh byh = (byh) obj;
        pipelineParams.mo3352b();
        pipelineParams.f4836a |= !fxk.m9828a((Object) byh, (Object) pipelineParams.f4837b);
        pipelineParams.f4837b = byh;
        return pipelineParams.mo3351a();
    }
}
