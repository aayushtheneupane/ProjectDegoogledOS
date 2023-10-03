package p000;

/* renamed from: gwb */
/* compiled from: PG */
public final class gwb {

    /* renamed from: a */
    public gvx f12171a;

    /* renamed from: b */
    public hpq f12172b;

    /* renamed from: c */
    private hpr f12173c;

    /* renamed from: a */
    public final gwd mo7126a() {
        ife.m12869b((Object) this.f12173c, (Object) "No ViewBinder");
        boolean z = true;
        if (this.f12171a != null && this.f12172b == null) {
            z = false;
        }
        ife.m12876b(z, (Object) "DataDiffer was provided without a StableIdFunction or Equivalence.");
        if (this.f12172b != null && this.f12171a == null) {
            this.f12171a = new gvz();
        }
        return new gwd(this.f12173c, this.f12172b, this.f12171a);
    }

    /* renamed from: a */
    public final void mo7128a(hpr hpr) {
        this.f12172b = hpp.f13230a.mo7660a(hpr);
    }

    /* renamed from: a */
    public final void mo7127a(gwe gwe) {
        this.f12173c = ife.m12906g((Object) gwe);
    }
}
