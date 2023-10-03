package p000;

/* renamed from: fem */
/* compiled from: PG */
final class fem implements fdo {

    /* renamed from: a */
    private final /* synthetic */ fep f9373a;

    public fem(fep fep) {
        this.f9373a = fep;
    }

    /* renamed from: a */
    public final void mo5516a(fdr fdr) {
    }

    /* renamed from: d */
    public final void mo5520d(fdr fdr) {
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v0, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v1, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v2, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v8, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v3, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v4, resolved type: boolean} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* renamed from: b */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void mo5518b(p000.fdr r5) {
        /*
            r4 = this;
            ffa r0 = p000.ffa.f9433a
            fdq r0 = r5.mo5544e(r0)
            ffa r1 = p000.ffa.f9433a
            fdh r1 = r5.mo5543d(r1)
            int r2 = r1.f9310a
            r2 = r2 & 2
            r3 = 1
            if (r2 == 0) goto L_0x0032
            int r0 = r0.mo5533i()
            int r2 = r1.f9313d
            int r2 = p000.ife.m12861b((int) r2)
            if (r2 == 0) goto L_0x0020
            goto L_0x0022
        L_0x0020:
            r2 = 1
        L_0x0022:
            if (r2 == r0) goto L_0x0031
            int r1 = r1.f9313d
            int r1 = p000.ife.m12861b((int) r1)
            if (r1 != 0) goto L_0x002d
            goto L_0x002e
        L_0x002d:
            r3 = r1
        L_0x002e:
            r4.mo5517a(r5, r3, r0)
        L_0x0031:
            return
        L_0x0032:
            boolean r1 = r0 instanceof p000.fdz
            if (r1 != 0) goto L_0x0037
            goto L_0x0064
        L_0x0037:
            ffa r1 = p000.ffa.f9433a
            p000.ife.m12898e((java.lang.Object) r1)
            iit r1 = r5.f9327d
            iih r2 = p000.ffz.f9495a
            boolean r1 = r1.mo8784a(r2)
            fdz r0 = (p000.fdz) r0
            boolean r2 = r0.f9347c
            if (r2 == r1) goto L_0x0064
            fdr r2 = r0.f9345a
            if (r2 != 0) goto L_0x004f
            goto L_0x0050
        L_0x004f:
            r3 = 0
        L_0x0050:
            p000.ife.m12896d((boolean) r3)
            boolean r2 = r0.f9346b
            if (r2 == 0) goto L_0x005b
            r0.mo5554k()
        L_0x005b:
            r0.f9347c = r1
            boolean r1 = r0.f9346b
            if (r1 == 0) goto L_0x0064
            r0.mo5553j()
        L_0x0064:
            fep r0 = r4.f9373a
            java.util.Set r0 = r0.f9385c
            r0.add(r5)
            fep r5 = r4.f9373a
            r5.mo5571a()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.fem.mo5518b(fdr):void");
    }

    /* renamed from: c */
    public final void mo5519c(fdr fdr) {
        this.f9373a.f9385c.remove(fdr);
        this.f9373a.f9386d.remove(fdr);
        ife.m12898e((Object) ffa.f9433a);
        iit iit = fdr.f9327d;
        if (iit.f14319c) {
            iit.mo8751b();
            iit.f14319c = false;
        }
        fdx fdx = (fdx) iit.f14318b;
        fdx fdx2 = fdx.f9338e;
        fdx.f9343d = null;
        fdx.f9340a &= -3;
    }

    /* renamed from: a */
    public final void mo5517a(fdr fdr, int i, int i2) {
        if (!this.f9373a.f9385c.contains(fdr)) {
            int b = ife.m12861b(fdr.mo5543d(ffa.f9433a).f9313d);
            if (b == 0) {
                b = 1;
            }
            if (b != i2) {
                this.f9373a.f9386d.add(fdr);
                this.f9373a.mo5571a();
                return;
            }
            this.f9373a.f9386d.remove(fdr);
        }
    }
}
