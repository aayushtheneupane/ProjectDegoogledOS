package p000;

import com.google.android.apps.photosgo.editor.parameters.PipelineParams;

/* renamed from: bzz */
/* compiled from: PG */
final class bzz implements bzy {
    /* renamed from: a */
    public final /* bridge */ /* synthetic */ Object mo2913a() {
        return cac.f3958a;
    }

    public final String toString() {
        return "Input quad";
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ Object mo2914a(PipelineParams pipelineParams) {
        return m3918a(pipelineParams, new cau());
    }

    /* renamed from: a */
    private static final cau m3918a(PipelineParams pipelineParams, cau cau) {
        ((cau) ife.m12898e((Object) cau)).mo2963a(pipelineParams.inputQuad);
        return cau;
    }

    /* renamed from: b */
    public final /* bridge */ /* synthetic */ Object mo2916b(PipelineParams pipelineParams, Object obj) {
        return m3918a(pipelineParams, (cau) obj);
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ boolean mo2915a(PipelineParams pipelineParams, Object obj) {
        pipelineParams.mo3352b();
        float[] fArr = ((cau) obj).f3994a;
        pipelineParams.f4836a |= !fxk.m9834b((Object) pipelineParams.inputQuad, (Object) fArr);
        System.arraycopy(fArr, 0, pipelineParams.inputQuad, 0, 8);
        return pipelineParams.mo3351a();
    }
}
