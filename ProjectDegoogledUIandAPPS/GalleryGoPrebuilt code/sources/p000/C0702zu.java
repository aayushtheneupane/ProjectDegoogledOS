package p000;

/* renamed from: zu */
/* compiled from: PG */
public final class C0702zu {

    /* renamed from: a */
    public final C0309lf f16478a = new C0309lf();

    /* renamed from: b */
    public final C0296kt f16479b = new C0296kt();

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public final void mo10756b(C0667ym ymVar) {
        C0700zs zsVar = (C0700zs) this.f16478a.get(ymVar);
        if (zsVar == null) {
            zsVar = C0700zs.m16263a();
            this.f16478a.put(ymVar, zsVar);
        }
        zsVar.f16475a |= 1;
    }

    /* renamed from: a */
    public final void mo10753a(long j, C0667ym ymVar) {
        this.f16479b.mo9231a(j, ymVar);
    }

    /* renamed from: b */
    public final void mo10757b(C0667ym ymVar, C0640xm xmVar) {
        C0700zs zsVar = (C0700zs) this.f16478a.get(ymVar);
        if (zsVar == null) {
            zsVar = C0700zs.m16263a();
            this.f16478a.put(ymVar, zsVar);
        }
        zsVar.f16477c = xmVar;
        zsVar.f16475a |= 8;
    }

    /* renamed from: a */
    public final void mo10754a(C0667ym ymVar, C0640xm xmVar) {
        C0700zs zsVar = (C0700zs) this.f16478a.get(ymVar);
        if (zsVar == null) {
            zsVar = C0700zs.m16263a();
            this.f16478a.put(ymVar, zsVar);
        }
        zsVar.f16476b = xmVar;
        zsVar.f16475a |= 4;
    }

    /* renamed from: a */
    public final void mo10752a() {
        this.f16478a.clear();
        this.f16479b.mo9234c();
    }

    /* renamed from: a */
    public final boolean mo10755a(C0667ym ymVar) {
        C0700zs zsVar = (C0700zs) this.f16478a.get(ymVar);
        return (zsVar == null || (zsVar.f16475a & 1) == 0) ? false : true;
    }

    /* renamed from: a */
    public final C0640xm mo10751a(C0667ym ymVar, int i) {
        C0700zs zsVar;
        C0640xm xmVar;
        int a = this.f16478a.mo9317a((Object) ymVar);
        if (a >= 0 && (zsVar = (C0700zs) this.f16478a.mo9321c(a)) != null) {
            int i2 = zsVar.f16475a;
            if ((i2 & i) != 0) {
                int i3 = (i ^ -1) & i2;
                zsVar.f16475a = i3;
                if (i == 4) {
                    xmVar = zsVar.f16476b;
                } else if (i == 8) {
                    xmVar = zsVar.f16477c;
                } else {
                    throw new IllegalArgumentException("Must provide flag PRE or POST");
                }
                if ((i3 & 12) == 0) {
                    this.f16478a.mo1935d(a);
                    C0700zs.m16264a(zsVar);
                }
                return xmVar;
            }
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: c */
    public final void mo10758c(C0667ym ymVar) {
        C0700zs zsVar = (C0700zs) this.f16478a.get(ymVar);
        if (zsVar != null) {
            zsVar.f16475a &= -2;
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: d */
    public final void mo10759d(C0667ym ymVar) {
        int b = this.f16479b.mo9232b() - 1;
        while (true) {
            if (b < 0) {
                break;
            } else if (ymVar == this.f16479b.mo9233b(b)) {
                C0296kt ktVar = this.f16479b;
                if (ktVar.f15156d[b] != C0296kt.f15153a) {
                    ktVar.f15156d[b] = C0296kt.f15153a;
                    ktVar.f15154b = true;
                }
            } else {
                b--;
            }
        }
        C0700zs zsVar = (C0700zs) this.f16478a.remove(ymVar);
        if (zsVar != null) {
            C0700zs.m16264a(zsVar);
        }
    }
}
