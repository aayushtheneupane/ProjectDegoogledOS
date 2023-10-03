package p000;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

/* renamed from: fzx */
/* compiled from: PG */
public final class fzx {

    /* renamed from: a */
    public final String f10761a;

    /* renamed from: b */
    public final fzy f10762b;

    /* renamed from: c */
    public final grf f10763c;

    /* renamed from: d */
    public final Object f10764d = new Object();

    /* renamed from: e */
    public List f10765e = new ArrayList();

    /* renamed from: f */
    private final ieh f10766f;

    /* renamed from: g */
    private final grf f10767g = new grf(new fzu(this), idh.f13918a);

    public fzx(fzy fzy, ieh ieh) {
        this.f10762b = fzy;
        this.f10766f = ieh;
        this.f10761a = ((fzg) fzy).f10722a;
        this.f10763c = new grf(new fyz((fzg) this.f10762b), idh.f13918a);
        mo6361a(new fzn(this));
    }

    /* renamed from: a */
    public final void mo6361a(icf icf) {
        synchronized (this.f10764d) {
            this.f10765e.add(icf);
        }
    }

    /* renamed from: a */
    public final ieh mo6359a() {
        hlj hlj;
        List list = hnb.f13076a;
        if (!this.f10767g.mo6950b()) {
            String valueOf = String.valueOf(this.f10761a);
            hlj = hnb.m11766a(valueOf.length() == 0 ? new String("Get ") : "Get ".concat(valueOf), hnf.f13084a);
        } else {
            hlj = null;
        }
        try {
            ieh a = ibv.m12658a(this.f10767g.mo6948a(), hmq.m11744a((icf) new fzo(this)), (Executor) idh.f13918a);
            if (hlj != null) {
                a = hlj.mo7548a(a);
            }
            ife.m12817a(this.f10766f);
            new fzs(a);
            if (hlj != null) {
                hlj.close();
            }
            return a;
        } catch (Throwable th) {
            if (hlj != null) {
                hlj.close();
            }
            throw th;
        }
    }

    /* renamed from: a */
    public final ieh mo6360a(hpr hpr, Executor executor) {
        icf a = hmq.m11744a((icf) new fzq(hpr));
        List list = hnb.f13076a;
        String valueOf = String.valueOf(this.f10761a);
        hlj a2 = hnb.m11766a(valueOf.length() == 0 ? new String("Update ") : "Update ".concat(valueOf), hnf.f13084a);
        try {
            ieh a3 = a2.mo7548a(ibv.m12658a(this.f10767g.mo6948a(), hmq.m11744a((icf) new fzp(this, a, executor)), (Executor) idh.f13918a));
            ife.m12817a(this.f10766f);
            if (a2 != null) {
                a2.close();
            }
            return a3;
        } catch (Throwable th) {
            ifn.m12935a(th, th);
        }
        throw th;
    }
}
