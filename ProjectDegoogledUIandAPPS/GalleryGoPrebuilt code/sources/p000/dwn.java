package p000;

import android.view.View;

/* renamed from: dwn */
/* compiled from: PG */
public final class dwn implements crl, cqg {

    /* renamed from: b */
    public static final String[] f7509b = {"android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE"};

    /* renamed from: a */
    public dxy f7510a;

    /* renamed from: c */
    public final cpp f7511c;

    /* renamed from: d */
    public final cqh f7512d;

    /* renamed from: e */
    public final inw f7513e;

    /* renamed from: f */
    public final blc f7514f;

    /* renamed from: g */
    public final cqc f7515g;

    /* renamed from: h */
    public final dwh f7516h;

    /* renamed from: i */
    public final dwi f7517i;

    /* renamed from: j */
    public final cnh f7518j;

    /* renamed from: k */
    public final ebf f7519k;

    /* renamed from: l */
    public final bod f7520l;

    /* renamed from: m */
    public final bnt f7521m;

    /* renamed from: n */
    public final fee f7522n;

    /* renamed from: o */
    public final dzp f7523o;

    /* renamed from: p */
    public final bqn f7524p;

    /* renamed from: q */
    public boolean f7525q = false;

    /* renamed from: r */
    public boolean f7526r = false;

    /* renamed from: s */
    public View f7527s;

    /* renamed from: t */
    public dxg f7528t;

    /* renamed from: u */
    public boolean f7529u;

    /* renamed from: v */
    public int f7530v = 1;

    /* renamed from: w */
    private final dzb f7531w;

    /* renamed from: x */
    private final gvi f7532x;

    /* renamed from: y */
    private final dwm f7533y = new dwm(this);

    public dwn(dwh dwh, dwi dwi, cpp cpp, dzb dzb, gvi gvi, cnh cnh, inw inw, blc blc, cqh cqh, cqc cqc, egp egp, ebf ebf, bod bod, bnt bnt, fee fee, dzp dzp, bqn bqn) {
        dwh dwh2 = dwh;
        dwi dwi2 = dwi;
        this.f7516h = dwh2;
        this.f7517i = dwi2;
        this.f7511c = cpp;
        this.f7531w = dzb;
        this.f7532x = gvi;
        this.f7518j = cnh;
        this.f7513e = inw;
        this.f7514f = blc;
        this.f7512d = cqh;
        cqe cqe = dwi2.f7497d;
        cqh.mo3757a(cqe == null ? cqe.f5414d : cqe);
        this.f7515g = cqc;
        this.f7519k = ebf;
        this.f7520l = bod;
        this.f7521m = bnt;
        this.f7522n = fee;
        this.f7523o = dzp;
        dwh2.f7489a.mo64a(egp);
        this.f7524p = bqn;
        this.f7529u = false;
        dxy a = dxy.m6881a(dwi2.f7499f);
        this.f7510a = a == null ? dxy.DEFAULT_SORT_METHOD : a;
    }

    /* renamed from: a */
    public static dwh m6817a(dwi dwi) {
        dwh dwh = new dwh();
        ftr.m9611b(dwh);
        ftr.m9610a((C0147fh) dwh);
        hcl.m11210a(dwh, dwi);
        return dwh;
    }

    /* renamed from: a */
    public final void mo2621a() {
        if (!this.f7512d.mo3764d()) {
            this.f7511c.mo3732b();
            if (this.f7526r) {
                this.f7526r = false;
                dxy a = dxy.m6881a(this.f7517i.f7499f);
                if (a == null) {
                    a = dxy.DEFAULT_SORT_METHOD;
                }
                mo4526a(a);
                this.f7512d.mo3766f();
            }
        } else if (!this.f7526r) {
            cpp cpp = this.f7511c;
            cpi a2 = cpi.m5217a(this.f7517i.f7498e);
            if (a2 == null) {
                a2 = cpi.HOME;
            }
            cpp.mo3731a(a2);
        } else {
            this.f7511c.mo3731a(cpi.FIND_LARGE_FILES);
        }
    }

    /* renamed from: a */
    public final void mo4525a(cqg cqg) {
        this.f7512d.mo3758a(cqg);
    }

    /* renamed from: k */
    public final void mo2710k() {
        dxg dxg = this.f7528t;
        float computeVerticalScrollOffset = ((float) dxg.f7557b.computeVerticalScrollOffset()) / ((float) (dxg.f7557b.computeVerticalScrollRange() - dxg.f7557b.computeVerticalScrollExtent()));
        C0634xg adapter = dxg.f7557b.getAdapter();
        C0647xt layoutManager = dxg.f7557b.getLayoutManager();
        if (adapter != null && layoutManager != null) {
            dvw dvw = new dvw(dxg.f7557b.getContext(), Math.round(((float) adapter.mo220a()) * computeVerticalScrollOffset));
            dvw.f16342a = 0;
            layoutManager.mo10572a((C0663yi) dvw);
        }
    }

    /* renamed from: a */
    public final void mo4526a(dxy dxy) {
        this.f7525q = true;
        this.f7510a = dxy;
        mo4527b();
    }

    /* renamed from: c */
    public final void mo4528c() {
        this.f7512d.mo3765e();
    }

    /* renamed from: b */
    public final void mo4527b() {
        this.f7532x.mo7113a(new dza(this.f7531w, this.f7517i, this.f7510a), guy.DONT_CARE, this.f7533y);
    }
}
