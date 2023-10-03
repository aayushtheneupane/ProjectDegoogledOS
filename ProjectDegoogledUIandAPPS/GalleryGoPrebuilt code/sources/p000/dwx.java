package p000;

import android.content.Context;
import android.view.View;
import com.google.android.apps.photosgo.R;

/* renamed from: dwx */
/* compiled from: PG */
public final class dwx extends C0598vy {

    /* renamed from: w */
    private final dvz f7545w;

    /* renamed from: x */
    private final hbl f7546x;

    public dwx(Context context, int i, dvz dvz, hbl hbl) {
        super(i, (byte[]) null);
        this.f7545w = dvz;
        this.f7546x = hbl;
    }

    /* renamed from: b */
    public final boolean mo4532b() {
        return false;
    }

    /* renamed from: a */
    public final void mo4531a(int i, int i2, C0664yj yjVar, C0646xs xsVar) {
        int signum = Integer.signum(i2);
        if (signum != 0 && mo10585r() > 0) {
            View h = mo10582h(signum >= 0 ? mo10585r() - 1 : 0);
            if (h != null) {
                int i3 = m15975i(h);
                int a = signum >= 0 ? yjVar.mo10626a() - 1 : 0;
                for (int i4 = 0; i4 < this.f16173a && i3 != a; i4++) {
                    i3 += signum;
                    xsVar.mo10410a(i3, Math.abs(i2) + 1 + i4);
                }
            }
        }
    }

    /* renamed from: c */
    public final void mo4533c(C0656yb ybVar, C0664yj yjVar) {
        int i = this.f16310u;
        int s = mo10586s();
        int u = mo10588u();
        int dimensionPixelSize = this.f7546x.getResources().getDimensionPixelSize(R.dimen.photogrid_item_margin);
        int i2 = this.f16173a;
        int i3 = (((i - s) - u) - (dimensionPixelSize * (i2 - 1))) / i2;
        this.f7545w.f7468b = i3;
        int r = mo10585r();
        for (int i4 = 0; i4 < r; i4++) {
            dvy dvy = (dvy) mo10582h(i4);
            if (dvy != null) {
                dvy.mo2635n().mo4518a(i3);
            }
        }
        super.mo4533c(ybVar, yjVar);
    }
}
