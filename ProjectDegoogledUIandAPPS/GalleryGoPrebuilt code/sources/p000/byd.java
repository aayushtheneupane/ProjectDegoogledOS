package p000;

import android.animation.TypeEvaluator;
import android.graphics.RectF;

/* renamed from: byd */
/* compiled from: PG */
public final class byd implements TypeEvaluator {

    /* renamed from: a */
    private final RectF f3901a;

    public byd(RectF rectF) {
        this.f3901a = rectF;
    }

    public final /* bridge */ /* synthetic */ Object evaluate(float f, Object obj, Object obj2) {
        RectF rectF = (RectF) obj;
        RectF rectF2 = (RectF) obj2;
        this.f3901a.set(rectF.left + ((rectF2.left - rectF.left) * f), rectF.top + ((rectF2.top - rectF.top) * f), rectF.right + ((rectF2.right - rectF.right) * f), rectF.bottom + ((rectF2.bottom - rectF.bottom) * f));
        return this.f3901a;
    }
}
