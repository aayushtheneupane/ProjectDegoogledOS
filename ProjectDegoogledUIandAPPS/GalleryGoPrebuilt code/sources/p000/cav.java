package p000;

import android.animation.TypeEvaluator;

/* renamed from: cav */
/* compiled from: PG */
public final class cav implements TypeEvaluator {

    /* renamed from: a */
    private final cau f3995a;

    public cav(cau cau) {
        this.f3995a = cau;
    }

    public final /* bridge */ /* synthetic */ Object evaluate(float f, Object obj, Object obj2) {
        float[] fArr = ((cau) obj).f3994a;
        float[] fArr2 = ((cau) obj2).f3994a;
        float[] fArr3 = this.f3995a.f3994a;
        for (int i = 0; i < 8; i++) {
            float f2 = fArr[i];
            fArr3[i] = f2 + ((fArr2[i] - f2) * f);
        }
        return this.f3995a;
    }
}
