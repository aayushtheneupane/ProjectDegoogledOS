package p000;

import java.util.concurrent.Executor;

/* renamed from: glp */
/* compiled from: PG */
public final class glp {

    /* renamed from: a */
    public final fzx f11583a;

    /* renamed from: b */
    public final iek f11584b;

    /* renamed from: c */
    public final iqk f11585c;

    /* renamed from: d */
    public final iqk f11586d;

    public glp(fzx fzx, iek iek, iqk iqk, iqk iqk2) {
        ife.m12898e((Object) gtf.f12011a);
        this.f11583a = fzx;
        this.f11584b = iek;
        this.f11585c = iqk;
        this.f11586d = iqk2;
    }

    /* renamed from: a */
    public final ieh mo6834a(gkn gkn) {
        return ibv.m12657a(this.f11583a.mo6359a(), (hpr) new gmi(gkn), (Executor) idh.f13918a);
    }

    /* renamed from: a */
    public final ieh mo6833a() {
        return ibv.m12657a(this.f11583a.mo6359a(), gmk.f11625a, (Executor) this.f11584b);
    }

    /* renamed from: a */
    public static gkx m10479a(gnj gnj) {
        gkn a = gkn.m10445a(gnj.f11686b, gtf.f12011a);
        gle gle = gnj.f11687c;
        if (gle == null) {
            gle = gle.f11566i;
        }
        int a2 = hgh.m11441a(gnj.f11688d);
        if (a2 == 0) {
            a2 = 1;
        }
        ife.m12898e((Object) gtf.f12011a);
        return new gma(a, gle, a2);
    }
}
