package p000;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.util.Property;
import android.view.View;

/* renamed from: afb */
/* compiled from: PG */
public final class afb extends agk {
    public afb() {
    }

    public afb(int i) {
        this.f390n = i;
    }

    /* renamed from: a */
    public final void mo270a(afu afu) {
        agk.m448d(afu);
        afu.f355a.put("android:fade:transitionAlpha", Float.valueOf(agb.m424b(afu.f356b)));
    }

    /* renamed from: a */
    private final Animator m300a(View view, float f, float f2) {
        if (f == f2) {
            return null;
        }
        agb.m421a(view, f);
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view, agb.f366a, new float[]{f2});
        ofFloat.addListener(new afa(view));
        mo302a((afk) new aez(view));
        return ofFloat;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:1:0x0002, code lost:
        r1 = (java.lang.Float) r1.f355a.get("android:fade:transitionAlpha");
     */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static float m299a(p000.afu r1, float r2) {
        /*
            if (r1 == 0) goto L_0x0013
            java.util.Map r1 = r1.f355a
            java.lang.String r0 = "android:fade:transitionAlpha"
            java.lang.Object r1 = r1.get(r0)
            java.lang.Float r1 = (java.lang.Float) r1
            if (r1 == 0) goto L_0x0013
            float r1 = r1.floatValue()
            return r1
        L_0x0013:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.afb.m299a(afu, float):float");
    }

    /* renamed from: a */
    public final Animator mo276a(View view, afu afu) {
        float f = 0.0f;
        float a = m299a(afu, 0.0f);
        if (a != 1.0f) {
            f = a;
        }
        return m300a(view, f, 1.0f);
    }

    /* renamed from: b */
    public final Animator mo277b(View view, afu afu) {
        Property property = agb.f366a;
        return m300a(view, m299a(afu, 1.0f), 0.0f);
    }
}
