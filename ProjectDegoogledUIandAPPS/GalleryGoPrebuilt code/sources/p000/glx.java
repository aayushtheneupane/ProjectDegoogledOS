package p000;

import java.util.Set;
import java.util.concurrent.Executor;

/* renamed from: glx */
/* compiled from: PG */
public final class glx {

    /* renamed from: a */
    public final glp f11594a;

    /* renamed from: b */
    public final fzx f11595b;

    /* renamed from: c */
    public final exm f11596c;

    /* renamed from: d */
    public final iqk f11597d;

    /* renamed from: e */
    public final Executor f11598e;

    /* renamed from: f */
    private final iqk f11599f;

    /* renamed from: g */
    private final ido f11600g = ido.m12729a();

    public glx(iqk iqk, glp glp, fzx fzx, exm exm, iqk iqk2, Executor executor) {
        this.f11599f = iqk;
        this.f11594a = glp;
        this.f11595b = fzx;
        this.f11596c = exm;
        this.f11597d = iqk2;
        this.f11598e = executor;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public final ieh mo6837b() {
        htm j = hto.m12130j();
        j.mo7995b((Iterable) (Set) ((ioi) this.f11599f).f14599a);
        return this.f11600g.mo8417a(hmq.m11743a((ice) new glr(this, j.mo7993a())), idh.f13918a);
    }

    /* renamed from: a */
    public final ieh mo6836a() {
        return this.f11595b.mo6360a(new glt(this), idh.f13918a);
    }
}
