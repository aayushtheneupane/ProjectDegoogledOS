package p000;

import android.animation.PointFEvaluator;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.graphics.PointF;
import android.graphics.RectF;
import com.google.android.apps.photosgo.editor.parameters.PipelineParams;
import java.util.HashSet;
import java.util.Set;

/* renamed from: bxu */
/* compiled from: PG */
final class bxu extends ValueAnimator implements ValueAnimator.AnimatorUpdateListener, bye {

    /* renamed from: f */
    public static /* synthetic */ int f3864f;

    /* renamed from: g */
    private static final hsu f3865g;

    /* renamed from: h */
    private static final hsu f3866h;

    /* renamed from: i */
    private static final hsu f3867i;

    /* renamed from: a */
    public final PipelineParams f3868a;

    /* renamed from: b */
    public final Set f3869b;

    /* renamed from: c */
    public final bxm f3870c;

    /* renamed from: d */
    public final Runnable f3871d;

    /* renamed from: e */
    public final bye f3872e;

    /* renamed from: j */
    private float f3873j;

    static {
        hsq g = hsu.m12070g();
        g.mo7932a(Float.class, new bxt((byte[]) null));
        g.mo7932a(Integer.class, new bxs((byte[]) null));
        g.mo7932a(Boolean.class, new bxs((byte[]) null));
        g.mo7932a(RectF.class, new byd(new RectF()));
        g.mo7932a(PointF.class, new PointFEvaluator(new PointF()));
        g.mo7932a(cau.class, new cav(new cau()));
        g.mo7932a(car.class, new bxs((byte[]) null));
        g.mo7932a(byh.class, new bxs((byte[]) null));
        f3865g = g.mo7930a();
        hsq g2 = hsu.m12070g();
        g2.mo7932a(PointF.class, new PointF());
        g2.mo7932a(RectF.class, new RectF());
        g2.mo7932a(cau.class, new cau());
        f3866h = g2.mo7930a();
        hsq g3 = hsu.m12070g();
        g3.mo7932a(PointF.class, new PointF());
        g3.mo7932a(RectF.class, new RectF());
        g3.mo7932a(cau.class, new cau());
        f3867i = g3.mo7930a();
    }

    public bxu(PipelineParams pipelineParams, Set set, bxm bxm, Runnable runnable, bye bye) {
        this.f3868a = PipelineParams.m4770a(pipelineParams);
        this.f3869b = new HashSet(set);
        this.f3870c = bxm;
        this.f3871d = runnable;
        this.f3872e = bye;
        if (mo2892a()) {
            addListener(new bxr(this));
        }
        addUpdateListener(this);
        setFloatValues(new float[]{0.0f, 1.0f});
    }

    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
        if (!this.f3869b.isEmpty()) {
            float animatedFraction = valueAnimator.getAnimatedFraction();
            float f = this.f3873j;
            float f2 = 1.0f;
            if (f != 1.0f) {
                f2 = (animatedFraction - f) / (1.0f - f);
            }
            this.f3873j = animatedFraction;
            if (f2 != 0.0f) {
                for (bzy bzy : this.f3869b) {
                    if (!bzy.equals(byu.f3917c)) {
                        Class<?> cls = ife.m12898e(bzy.mo2913a()).getClass();
                        bzy.mo2915a(((byc) this.f3870c).f3888c, ((TypeEvaluator) ife.m12898e((Object) (TypeEvaluator) f3865g.get(cls))).evaluate(f2, bzy.mo2916b(((byc) this.f3870c).f3888c, f3866h.get(cls)), bzy.mo2916b(this.f3868a, f3867i.get(cls))));
                    }
                }
                this.f3871d.run();
            }
        }
    }

    /* renamed from: a */
    public final boolean mo2892a() {
        return this.f3869b.contains(byu.f3917c);
    }
}
