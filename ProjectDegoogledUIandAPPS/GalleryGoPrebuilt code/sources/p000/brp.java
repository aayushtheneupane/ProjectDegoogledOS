package p000;

import android.view.View;

/* renamed from: brp */
/* compiled from: PG */
public final class brp implements crl {

    /* renamed from: a */
    public static final String[] f3441a = {"android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE"};

    /* renamed from: b */
    public final brn f3442b;

    /* renamed from: c */
    public final brk f3443c;

    /* renamed from: d */
    public final inw f3444d;

    /* renamed from: e */
    public final blc f3445e;

    /* renamed from: f */
    public final btr f3446f;

    /* renamed from: g */
    public final fee f3447g;

    /* renamed from: h */
    public bru f3448h;

    /* renamed from: i */
    public View f3449i;

    /* renamed from: j */
    private final btg f3450j;

    /* renamed from: k */
    private final gvi f3451k;

    /* renamed from: l */
    private final gvc f3452l = new bro(this);

    public brp(brn brn, brk brk, inw inw, blc blc, btg btg, btr btr, gvi gvi, fee fee) {
        this.f3442b = brn;
        this.f3443c = brk;
        this.f3444d = inw;
        this.f3445e = blc;
        this.f3450j = btg;
        this.f3446f = btr;
        this.f3451k = gvi;
        this.f3447g = fee;
    }

    /* renamed from: a */
    public static brk m3494a(brn brn) {
        brk brk = new brk();
        ftr.m9611b(brk);
        ftr.m9610a((C0147fh) brk);
        hcl.m11210a(brk, brn);
        return brk;
    }

    /* renamed from: k */
    public final void mo2710k() {
        this.f3448h.f3457b.smoothScrollToPosition(0);
    }

    /* renamed from: b */
    public final void mo2709b() {
        this.f3451k.mo7113a(new btf(this.f3450j, this.f3442b), guy.DONT_CARE, this.f3452l);
    }
}
