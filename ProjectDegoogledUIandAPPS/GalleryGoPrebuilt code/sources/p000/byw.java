package p000;

import com.google.android.apps.photosgo.editor.parameters.PipelineParams;

/* renamed from: byw */
/* compiled from: PG */
final class byw implements bzy {
    /* renamed from: b */
    public final Object mo2916b(PipelineParams pipelineParams, Object obj) {
        return iol.m14227a((bzy) this, pipelineParams);
    }

    public final String toString() {
        return "Rule of thirds count";
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ Object mo2913a() {
        return 3;
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ Object mo2914a(PipelineParams pipelineParams) {
        return Integer.valueOf(pipelineParams.ruleOfThirdsCount);
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ boolean mo2915a(PipelineParams pipelineParams, Object obj) {
        pipelineParams.mo3352b();
        int intValue = ((Integer) obj).intValue();
        pipelineParams.f4836a |= pipelineParams.ruleOfThirdsCount != intValue;
        pipelineParams.ruleOfThirdsCount = intValue;
        return pipelineParams.mo3351a();
    }
}
