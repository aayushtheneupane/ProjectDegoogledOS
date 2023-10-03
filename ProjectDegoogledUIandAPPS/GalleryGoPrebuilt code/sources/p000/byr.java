package p000;

import com.google.android.apps.photosgo.editor.parameters.PipelineParams;

/* renamed from: byr */
/* compiled from: PG */
public final class byr implements bzy {
    /* renamed from: b */
    public final Object mo2916b(PipelineParams pipelineParams, Object obj) {
        return iol.m14227a((bzy) this, pipelineParams);
    }

    public final String toString() {
        return "Rotation (radians)";
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ Object mo2913a() {
        return Float.valueOf(0.0f);
    }

    /* renamed from: b */
    public static final Float m3800b(PipelineParams pipelineParams) {
        return Float.valueOf(pipelineParams.rotateAngle);
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ Object mo2914a(PipelineParams pipelineParams) {
        return m3800b(pipelineParams);
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ boolean mo2915a(PipelineParams pipelineParams, Object obj) {
        Float valueOf = Float.valueOf(((Float) obj).floatValue() % 6.2831855f);
        if (valueOf.floatValue() < 0.0f) {
            valueOf = Float.valueOf(valueOf.floatValue() + 6.2831855f);
        }
        float floatValue = valueOf.floatValue();
        double d = (double) (floatValue + floatValue);
        Double.isNaN(d);
        double round = (double) Math.round(d / 3.141592653589793d);
        Double.isNaN(round);
        Float valueOf2 = Float.valueOf(((float) ((round * 3.141592653589793d) / 2.0d)) % 6.2831855f);
        pipelineParams.mo3352b();
        float floatValue2 = valueOf2.floatValue();
        pipelineParams.f4836a |= !ihg.m13042a(pipelineParams.rotateAngle, floatValue2);
        pipelineParams.rotateAngle = floatValue2;
        return pipelineParams.mo3351a();
    }
}
