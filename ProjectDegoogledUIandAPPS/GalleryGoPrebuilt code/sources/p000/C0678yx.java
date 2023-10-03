package p000;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.TypedValue;

/* renamed from: yx */
/* compiled from: PG */
final class C0678yx {

    /* renamed from: a */
    public static final int[] f16424a = {-16842910};

    /* renamed from: b */
    public static final int[] f16425b = {16842908};

    /* renamed from: c */
    public static final int[] f16426c = {16842919};

    /* renamed from: d */
    public static final int[] f16427d = {16842912};

    /* renamed from: e */
    public static final int[] f16428e = new int[0];

    /* renamed from: f */
    private static final ThreadLocal f16429f = new ThreadLocal();

    /* renamed from: g */
    private static final int[] f16430g = new int[1];

    /* renamed from: c */
    public static int m16185c(Context context, int i) {
        ColorStateList b = m16184b(context, i);
        if (b != null && b.isStateful()) {
            return b.getColorForState(f16424a, b.getDefaultColor());
        }
        TypedValue typedValue = (TypedValue) f16429f.get();
        if (typedValue == null) {
            typedValue = new TypedValue();
            f16429f.set(typedValue);
        }
        context.getTheme().resolveAttribute(16842803, typedValue, true);
        float f = typedValue.getFloat();
        int a = m16183a(context, i);
        return C0238ip.m14267b(a, Math.round(((float) Color.alpha(a)) * f));
    }

    /* renamed from: a */
    public static int m16183a(Context context, int i) {
        int[] iArr = f16430g;
        iArr[0] = i;
        C0684zc a = C0684zc.m16191a(context, (AttributeSet) null, iArr);
        try {
            return a.mo10737h(0);
        } finally {
            a.mo10724a();
        }
    }

    /* renamed from: b */
    public static ColorStateList m16184b(Context context, int i) {
        int[] iArr = f16430g;
        iArr[0] = i;
        C0684zc a = C0684zc.m16191a(context, (AttributeSet) null, iArr);
        try {
            return a.mo10733e(0);
        } finally {
            a.mo10724a();
        }
    }
}
