package p000;

import android.graphics.PointF;
import android.support.p002v7.widget.RecyclerView;
import android.view.View;

/* renamed from: wh */
/* compiled from: PG */
public final class C0608wh extends C0650xw {

    /* renamed from: c */
    private C0624wx f16233c;

    /* renamed from: d */
    private C0624wx f16234d;

    /* renamed from: a */
    public final int[] mo10485a(C0647xt xtVar, View view) {
        int[] iArr = new int[2];
        if (xtVar.mo2620i()) {
            iArr[0] = m15762a(view, m15766d(xtVar));
        } else {
            iArr[0] = 0;
        }
        if (!xtVar.mo10477j()) {
            iArr[1] = 0;
        } else {
            iArr[1] = m15762a(view, m15765c(xtVar));
        }
        return iArr;
    }

    /* renamed from: a */
    private static final int m15762a(View view, C0624wx wxVar) {
        return (wxVar.mo10519d(view) + (wxVar.mo10512a(view) / 2)) - (wxVar.mo10516c() + (wxVar.mo10518d() / 2));
    }

    /* renamed from: a */
    private final int m15763a(C0647xt xtVar, C0624wx wxVar, int i, int i2) {
        int max;
        this.f16317b.fling(0, 0, i, i2, RecyclerView.UNDEFINED_DURATION, Integer.MAX_VALUE, RecyclerView.UNDEFINED_DURATION, Integer.MAX_VALUE);
        int[] iArr = {this.f16317b.getFinalX(), this.f16317b.getFinalY()};
        int r = xtVar.mo10585r();
        float f = 1.0f;
        if (r != 0) {
            int i3 = Integer.MAX_VALUE;
            int i4 = RecyclerView.UNDEFINED_DURATION;
            View view = null;
            View view2 = null;
            for (int i5 = 0; i5 < r; i5++) {
                View h = xtVar.mo10582h(i5);
                int i6 = C0647xt.m15975i(h);
                if (i6 != -1) {
                    int i7 = i6 < i3 ? i6 : i3;
                    if (i6 < i3) {
                        view = h;
                    }
                    if (i6 > i4) {
                        view2 = h;
                        i4 = i6;
                    }
                    i3 = i7;
                }
            }
            if (!(view == null || view2 == null || (max = Math.max(wxVar.mo10517c(view), wxVar.mo10517c(view2)) - Math.min(wxVar.mo10519d(view), wxVar.mo10519d(view2))) == 0)) {
                f = ((float) max) / ((float) ((i4 - i3) + 1));
            }
        }
        if (f <= 0.0f) {
            return 0;
        }
        return Math.round(((float) (Math.abs(iArr[0]) <= Math.abs(iArr[1]) ? iArr[1] : iArr[0])) / f);
    }

    /* renamed from: a */
    private static final View m15764a(C0647xt xtVar, C0624wx wxVar) {
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
            return m15764a(xtVar, m15765c(xtVar));
        }
        if (xtVar.mo2620i()) {
            return m15764a(xtVar, m15766d(xtVar));
        }
        return null;
    }

    /* renamed from: a */
    public final int mo10483a(C0647xt xtVar, int i, int i2) {
        int x;
        View a;
        int i3;
        int i4;
        PointF c;
        int i5;
        int i6;
        if (!(xtVar instanceof C0662yh) || (x = xtVar.mo10591x()) == 0 || (a = mo10484a(xtVar)) == null || (i3 = C0647xt.m15975i(a)) == -1 || (c = ((C0662yh) xtVar).mo10468c(i4)) == null) {
            return -1;
        }
        int i7 = 0;
        if (xtVar.mo2620i()) {
            i5 = m15763a(xtVar, m15766d(xtVar), i, 0);
            if (c.x < 0.0f) {
                i5 = -i5;
            }
        } else {
            i5 = 0;
        }
        if (xtVar.mo10477j()) {
            i6 = m15763a(xtVar, m15765c(xtVar), 0, i2);
            if (c.y < 0.0f) {
                i6 = -i6;
            }
        } else {
            i6 = 0;
        }
        if (xtVar.mo10477j()) {
            i5 = i6;
        }
        if (i5 == 0) {
            return -1;
        }
        int i8 = i3 + i5;
        if (i8 >= 0) {
            i7 = i8;
        }
        return i7 >= x ? x - 1 : i7;
    }

    /* renamed from: d */
    private final C0624wx m15766d(C0647xt xtVar) {
        C0624wx wxVar = this.f16234d;
        if (wxVar == null || wxVar.f16276a != xtVar) {
            this.f16234d = C0624wx.m15820a(xtVar);
        }
        return this.f16234d;
    }

    /* renamed from: c */
    private final C0624wx m15765c(C0647xt xtVar) {
        C0624wx wxVar = this.f16233c;
        if (wxVar == null || wxVar.f16276a != xtVar) {
            this.f16233c = C0624wx.m15821b(xtVar);
        }
        return this.f16233c;
    }
}
