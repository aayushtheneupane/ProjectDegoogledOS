package p000;

/* renamed from: hth */
/* compiled from: PG */
public final class hth extends hse {

    /* renamed from: a */
    public hut f13377a;

    /* renamed from: b */
    public boolean f13378b;

    /* renamed from: c */
    public boolean f13379c;

    public hth() {
        this(4);
    }

    public hth(int i) {
        this.f13378b = false;
        this.f13379c = false;
        this.f13377a = new hut(i);
    }

    /* renamed from: a */
    public final void mo7874b(Object obj) {
        mo7983a(obj, 1);
    }

    /* JADX WARNING: type inference failed for: r4v0, types: [huo, java.lang.Iterable] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* renamed from: b */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void mo7984b(java.lang.Iterable r4) {
        /*
            r3 = this;
            boolean r0 = r4 instanceof p000.huo
            if (r0 == 0) goto L_0x0069
            boolean r0 = r4 instanceof p000.hve
            if (r0 == 0) goto L_0x000e
            r0 = r4
            hve r0 = (p000.hve) r0
            hut r0 = r0.f13462b
            goto L_0x0010
        L_0x000e:
            r0 = 0
        L_0x0010:
            if (r0 != 0) goto L_0x0045
            java.util.Set r0 = r4.mo7796f()
            hut r1 = r3.f13377a
            int r2 = r1.f13431c
            int r0 = r0.size()
            int r0 = java.lang.Math.max(r2, r0)
            r1.mo8111e(r0)
            java.util.Set r4 = r4.mo7796f()
            java.util.Iterator r4 = r4.iterator()
        L_0x002d:
            boolean r0 = r4.hasNext()
            if (r0 == 0) goto L_0x0068
            java.lang.Object r0 = r4.next()
            hun r0 = (p000.hun) r0
            java.lang.Object r1 = r0.mo8079a()
            int r0 = r0.mo8080b()
            r3.mo7983a(r1, r0)
            goto L_0x002d
        L_0x0045:
            hut r4 = r3.f13377a
            int r1 = r4.f13431c
            int r2 = r0.f13431c
            int r1 = java.lang.Math.max(r1, r2)
            r4.mo8111e(r1)
            int r4 = r0.mo8100a()
        L_0x0056:
            if (r4 < 0) goto L_0x0068
            java.lang.Object r1 = r0.mo8107b((int) r4)
            int r2 = r0.mo8109c(r4)
            r3.mo7983a(r1, r2)
            int r4 = r0.mo8101a((int) r4)
            goto L_0x0056
        L_0x0068:
            return
        L_0x0069:
            super.mo7872a(r4)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.hth.mo7984b(java.lang.Iterable):void");
    }

    /* renamed from: a */
    public final void mo7983a(Object obj, int i) {
        if (i != 0) {
            if (this.f13378b) {
                this.f13377a = new hut(this.f13377a);
                this.f13379c = false;
            }
            this.f13378b = false;
            ife.m12898e(obj);
            hut hut = this.f13377a;
            hut.mo8108b(obj, i + hut.mo8106b(obj));
        }
    }

    /* renamed from: a */
    public final htk mo7981a() {
        hut hut = this.f13377a;
        if (hut.f13431c == 0) {
            return hve.f13461a;
        }
        if (this.f13379c) {
            this.f13377a = new hut(hut);
            this.f13379c = false;
        }
        this.f13378b = true;
        return new hve(this.f13377a);
    }
}
