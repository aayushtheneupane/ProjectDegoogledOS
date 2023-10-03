package p000;

/* renamed from: fdr */
/* compiled from: PG */
public final class fdr {

    /* renamed from: a */
    public static final fdn f9324a = new fdk((byte[]) null);

    /* renamed from: b */
    public final fdo f9325b;

    /* renamed from: c */
    public fdq f9326c;

    /* renamed from: d */
    public final iit f9327d;

    /* renamed from: e */
    private final fdn f9328e;

    /* renamed from: f */
    private int f9329f = -1;

    /* renamed from: b */
    public final boolean mo5539b() {
        return this.f9325b != null;
    }

    public /* synthetic */ fdr(iit iit, fdn fdn, fdo fdo) {
        this.f9327d = iit;
        this.f9328e = fdn;
        this.f9325b = fdo;
    }

    /* renamed from: h */
    public final int mo5547h(ffa ffa) {
        ife.m12898e((Object) ffa);
        return this.f9329f;
    }

    /* renamed from: e */
    public final fdq mo5544e(ffa ffa) {
        ife.m12898e((Object) ffa);
        return this.f9326c;
    }

    /* renamed from: d */
    public final fdh mo5543d(ffa ffa) {
        ife.m12898e((Object) ffa);
        fdh fdh = ((fdx) this.f9327d.f14318b).f9343d;
        return fdh == null ? fdh.f9308e : fdh;
    }

    /* renamed from: c */
    public final int mo5541c() {
        return this.f9326c.mo5533i();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final boolean mo5538a() {
        return (((fdx) this.f9327d.f14318b).f9340a & 1) != 0;
    }

    /* renamed from: b */
    public final boolean mo5540b(ffa ffa) {
        ife.m12898e((Object) ffa);
        return (((fdx) this.f9327d.f14318b).f9340a & 1) != 0;
    }

    /* renamed from: c */
    public final boolean mo5542c(ffa ffa) {
        ife.m12898e((Object) ffa);
        fdh fdh = ((fdx) this.f9327d.f14318b).f9343d;
        if (fdh == null) {
            fdh = fdh.f9308e;
        }
        return (fdh.f9310a & 2) != 0;
    }

    /* renamed from: f */
    public final void mo5545f(ffa ffa) {
        ife.m12898e((Object) ffa);
        fdo fdo = this.f9325b;
        if (fdo != null) {
            fdo.mo5518b(this);
        }
    }

    /* renamed from: g */
    public final void mo5546g(ffa ffa) {
        ife.m12898e((Object) ffa);
        fdo fdo = this.f9325b;
        if (fdo != null) {
            fdo.mo5519c(this);
        }
    }

    /* renamed from: a */
    public final void mo5535a(int i, ffa ffa) {
        ife.m12898e((Object) ffa);
        this.f9329f = i;
    }

    /* renamed from: a */
    public final void mo5536a(fdh fdh, ffa ffa) {
        ife.m12898e((Object) ffa);
        iit iit = this.f9327d;
        if (iit.f14319c) {
            iit.mo8751b();
            iit.f14319c = false;
        }
        fdx fdx = (fdx) iit.f14318b;
        fdx fdx2 = fdx.f9338e;
        fdh.getClass();
        fdx.f9343d = fdh;
        fdx.f9340a |= 2;
    }

    /* renamed from: a */
    public final fdx mo5534a(ffa ffa) {
        ife.m12898e((Object) ffa);
        return (fdx) this.f9327d.mo8770g();
    }

    public final String toString() {
        String str;
        fdq fdq = this.f9326c;
        if (fdq != null) {
            String valueOf = String.valueOf(fdq.getClass().getSimpleName());
            str = valueOf.length() == 0 ? new String(".") : ".".concat(valueOf);
        } else {
            str = "";
        }
        int i = ((fdx) this.f9327d.f14318b).f9341b;
        String hexString = Integer.toHexString(hashCode());
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 18 + String.valueOf(hexString).length());
        sb.append("CVE");
        sb.append(str);
        sb.append("#");
        sb.append(i);
        sb.append(" [");
        sb.append(hexString);
        sb.append("]");
        return sb.toString();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final void mo5537a(fdr fdr) {
        boolean z = false;
        ife.m12896d(fdr.f9326c == null);
        ife.m12896d(!mo5538a());
        if (this.f9328e == fdr.f9328e) {
            z = true;
        }
        ife.m12896d(z);
        boolean f = this.f9326c.mo5530f();
        if (f) {
            mo5546g(ffa.f9433a);
        }
        iit iit = this.f9327d;
        iit.f14318b = (iix) iit.f14318b.mo8790b(4);
        this.f9327d.mo8503a((iix) (fdx) fdr.f9327d.mo8770g());
        if (f) {
            mo5545f(ffa.f9433a);
        }
    }
}
