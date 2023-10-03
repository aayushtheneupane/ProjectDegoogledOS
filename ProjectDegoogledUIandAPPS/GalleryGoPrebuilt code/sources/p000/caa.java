package p000;

import com.google.android.apps.photosgo.editor.parameters.PipelineParams;

/* renamed from: caa */
/* compiled from: PG */
final class caa implements bzy {
    /* renamed from: a */
    public final /* bridge */ /* synthetic */ Object mo2913a() {
        return cac.f3958a;
    }

    public final String toString() {
        return "Output quad";
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ Object mo2914a(PipelineParams pipelineParams) {
        return m3932a(pipelineParams, new cau());
    }

    /* renamed from: a */
    private static final cau m3932a(PipelineParams pipelineParams, cau cau) {
        ((cau) ife.m12898e((Object) cau)).mo2963a(pipelineParams.outputQuad);
        return cau;
    }

    /* renamed from: b */
    public final /* bridge */ /* synthetic */ Object mo2916b(PipelineParams pipelineParams, Object obj) {
        return m3932a(pipelineParams, (cau) obj);
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ boolean mo2915a(PipelineParams pipelineParams, Object obj) {
        pipelineParams.mo3352b();
        float[] fArr = ((cau) obj).f3994a;
        pipelineParams.f4836a |= !fxk.m9834b((Object) pipelineParams.outputQuad, (Object) fArr);
        System.arraycopy(fArr, 0, pipelineParams.outputQuad, 0, 8);
        return pipelineParams.mo3351a();
    }
}
