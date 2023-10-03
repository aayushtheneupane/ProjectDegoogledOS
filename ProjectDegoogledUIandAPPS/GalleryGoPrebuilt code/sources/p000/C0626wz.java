package p000;

import android.graphics.PointF;
import android.support.p002v7.widget.RecyclerView;
import android.view.View;

/* renamed from: wz */
/* compiled from: PG */
public class C0626wz extends C0650xw {

    /* renamed from: c */
    private C0624wx f16280c;

    /* renamed from: d */
    private C0624wx f16281d;

    /* renamed from: a */
    public final int[] mo10485a(C0647xt xtVar, View view) {
        int[] iArr = new int[2];
        if (xtVar.mo2620i()) {
            iArr[0] = m15840a(view, m15843d(xtVar));
        } else {
            iArr[0] = 0;
        }
        if (!xtVar.mo10477j()) {
            iArr[1] = 0;
        } else {
            iArr[1] = m15840a(view, m15842c(xtVar));
        }
        return iArr;
    }

    /* renamed from: b */
    public C0663yi mo4651b(C0647xt xtVar) {
        if (xtVar instanceof C0662yh) {
            return new C0625wy(this, this.f16316a.getContext());
        }
        return null;
    }

    /* renamed from: a */
    private static final int m15840a(View view, C0624wx wxVar) {
        return (wxVar.mo10519d(view) + (wxVar.mo10512a(view) / 2)) - (wxVar.mo10516c() + (wxVar.mo10518d() / 2));
    }

    /* renamed from: a */
    private static final View m15841a(C0647xt xtVar, C0624wx wxVar) {
        int r = xtVar.mo10585r();
        View view = null;
        if (r != 0) {
            int c = wxVar.mo10516c() + (wxVar.mo10518d() / 2);
            int i = Integer.MAX_VALUE;
            int i2 = 0;
            while (i2 < r) {
                View h = xtVar.mo10582h(i2);
                int abs = Math.abs((wxVar.mo10519d(h) + (wxVar.mo10512a(h) / 2)) - c);
                int i3 = abs < i ? abs : i;
                if (abs < i) {
                    view = h;
                }
                i2++;
                i = i3;
            }
        }
        return view;
    }

    /* renamed from: a */
    public final View mo10484a(C0647xt xtVar) {
        if (xtVar.mo10477j()) {
            return m15841a(xtVar, m15842c(xtVar));
        }
        if (xtVar.mo2620i()) {
            return m15841a(xtVar, m15843d(xtVar));
        }
        return null;
    }

    /* renamed from: a */
    public final int mo10483a(C0647xt xtVar, int i, int i2) {
        C0624wx wxVar;
        PointF c;
        int x = xtVar.mo10591x();
        if (x == 0) {
            return -1;
        }
        View view = null;
        if (xtVar.mo10477j()) {
            wxVar = m15842c(xtVar);
        } else {
            wxVar = xtVar.mo2620i() ? m15843d(xtVar) : null;
        }
        if (wxVar == null) {
            return -1;
        }
        int r = xtVar.mo10585r();
        boolean z = false;
        View view2 = null;
        int i3 = RecyclerView.UNDEFINED_DURATION;
        int i4 = Integer.MAX_VALUE;
        for (int i5 = 0; i5 < r; i5++) {
            View h = xtVar.mo10582h(i5);
            if (h != null) {
                int a = m15840a(h, wxVar);
                if (a <= 0 && a > i3) {
                    view2 = h;
                    i3 = a;
                }
                if (a >= 0 && a < i4) {
                    view = h;
                    i4 = a;
                }
            }
        }
        int i6 = 1;
        boolean z2 = xtVar.mo2620i() ? i > 0 : i2 > 0;
        if (z2 && view != null) {
            return C0647xt.m15975i(view);
        }
        if (!z2 && view2 != null) {
            return C0647xt.m15975i(view2);
        }
        if (z2) {
            view = view2;
        }
        if (view != null) {
            int i7 = C0647xt.m15975i(view);
            int x2 = xtVar.mo10591x();
            if ((xtVar instanceof C0662yh) && (c = ((C0662yh) xtVar).mo10468c(x2 - 1)) != null && (c.x < 0.0f || c.y < 0.0f)) {
                z = true;
            }
            if (z == z2) {
                i6 = -1;
            }
            int i8 = i7 + i6;
            if (i8 < 0 || i8 >= x) {
                return -1;
            }
            return i8;
        }
        return -1;
    }

    /* renamed from: d */
    private final C0624wx m15843d(C0647xt xtVar) {
        C0624wx wxVar = this.f16281d;
        if (wxVar == null || wxVar.f16276a != xtVar) {
            this.f16281d = C0624wx.m15820a(xtVar);
        }
        return this.f16281d;
    }

    /* renamed from: c */
    private final C0624wx m15842c(C0647xt xtVar) {
        C0624wx wxVar = this.f16280c;
        if (wxVar == null || wxVar.f16276a != xtVar) {
            this.f16280c = C0624wx.m15821b(xtVar);
        }
        return this.f16280c;
    }
}
