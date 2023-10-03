package p000;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.PointF;
import android.graphics.Rect;
import android.util.Property;
import android.view.View;
import android.view.ViewGroup;
import java.util.Map;

/* renamed from: aey */
/* compiled from: PG */
public final class aey extends afl {

    /* renamed from: n */
    private static final String[] f290n = {"android:changeBounds:bounds", "android:changeBounds:clip", "android:changeBounds:parent", "android:changeBounds:windowX", "android:changeBounds:windowY"};

    /* renamed from: o */
    private static final Property f291o = new aeq(PointF.class, "topLeft");

    /* renamed from: p */
    private static final Property f292p = new aer(PointF.class, "bottomRight");

    /* renamed from: q */
    private static final Property f293q = new aes(PointF.class, "bottomRight");

    /* renamed from: r */
    private static final Property f294r = new aet(PointF.class, "topLeft");

    /* renamed from: s */
    private static final Property f295s = new aeu(PointF.class, "position");

    static {
        new aep(PointF.class, "boundsOrigin");
    }

    /* renamed from: a */
    public final String[] mo271a() {
        return f290n;
    }

    /* renamed from: b */
    public final void mo272b(afu afu) {
        m293d(afu);
    }

    /* renamed from: a */
    public final void mo270a(afu afu) {
        m293d(afu);
    }

    /* renamed from: d */
    private static final void m293d(afu afu) {
        View view = afu.f356b;
        if (C0340mj.m14732w(view) || view.getWidth() != 0 || view.getHeight() != 0) {
            afu.f355a.put("android:changeBounds:bounds", new Rect(view.getLeft(), view.getTop(), view.getRight(), view.getBottom()));
            afu.f355a.put("android:changeBounds:parent", afu.f356b.getParent());
        }
    }

    /* renamed from: a */
    public final Animator mo269a(ViewGroup viewGroup, afu afu, afu afu2) {
        int i;
        Animator animator;
        afu afu3 = afu;
        afu afu4 = afu2;
        if (afu3 == null || afu4 == null) {
            return null;
        }
        Map map = afu3.f355a;
        Map map2 = afu4.f355a;
        ViewGroup viewGroup2 = (ViewGroup) map.get("android:changeBounds:parent");
        ViewGroup viewGroup3 = (ViewGroup) map2.get("android:changeBounds:parent");
        if (viewGroup2 == null || viewGroup3 == null) {
            return null;
        }
        View view = afu4.f356b;
        Rect rect = (Rect) afu3.f355a.get("android:changeBounds:bounds");
        Rect rect2 = (Rect) afu4.f355a.get("android:changeBounds:bounds");
        int i2 = rect.left;
        int i3 = rect2.left;
        int i4 = rect.top;
        int i5 = rect2.top;
        int i6 = rect.right;
        int i7 = rect2.right;
        int i8 = rect.bottom;
        int i9 = rect2.bottom;
        int i10 = i6 - i2;
        int i11 = i8 - i4;
        int i12 = i7 - i3;
        int i13 = i9 - i5;
        Rect rect3 = (Rect) afu3.f355a.get("android:changeBounds:clip");
        Rect rect4 = (Rect) afu4.f355a.get("android:changeBounds:clip");
        if ((i10 == 0 || i11 == 0) && (i12 == 0 || i13 == 0)) {
            i = 0;
        } else {
            if (i2 == i3 && i4 == i5) {
                i = 0;
            } else {
                i = 1;
            }
            if (!(i6 == i7 && i8 == i9)) {
                i++;
            }
        }
        if ((rect3 != null && !rect3.equals(rect4)) || (rect3 == null && rect4 != null)) {
            i++;
        }
        int i14 = i;
        if (i14 > 0) {
            agb.m423a(view, i2, i4, i6, i8);
            if (i14 != 2) {
                if (i2 == i3 && i4 == i5) {
                    animator = abj.m83a((Object) view, f293q, flw.m9186a((float) i6, (float) i8, (float) i7, (float) i9));
                } else {
                    animator = abj.m83a((Object) view, f294r, flw.m9186a((float) i2, (float) i4, (float) i3, (float) i5));
                }
            } else if (i10 == i12 && i11 == i13) {
                animator = abj.m83a((Object) view, f295s, flw.m9186a((float) i2, (float) i4, (float) i3, (float) i5));
            } else {
                aex aex = new aex(view);
                ObjectAnimator a = abj.m83a((Object) aex, f291o, flw.m9186a((float) i2, (float) i4, (float) i3, (float) i5));
                ObjectAnimator a2 = abj.m83a((Object) aex, f292p, flw.m9186a((float) i6, (float) i8, (float) i7, (float) i9));
                AnimatorSet animatorSet = new AnimatorSet();
                animatorSet.playTogether(new Animator[]{a, a2});
                animatorSet.addListener(new aev(aex));
                animator = animatorSet;
            }
            if (view.getParent() instanceof ViewGroup) {
                ViewGroup viewGroup4 = (ViewGroup) view.getParent();
                afy.m414a(viewGroup4, true);
                mo302a((afk) new aew(viewGroup4));
            }
            return animator;
        }
        return null;
    }
}
