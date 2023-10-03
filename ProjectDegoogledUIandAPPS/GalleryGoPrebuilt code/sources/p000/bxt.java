package p000;

import android.animation.TypeEvaluator;

/* renamed from: bxt */
/* compiled from: PG */
final class bxt implements TypeEvaluator {
    private bxt() {
    }

    public /* synthetic */ bxt(byte[] bArr) {
    }

    public final /* bridge */ /* synthetic */ Object evaluate(float f, Object obj, Object obj2) {
        Float f2 = (Float) obj;
        return Float.valueOf(f2.floatValue() + ((((Float) obj2).floatValue() - f2.floatValue()) * f));
    }
}
