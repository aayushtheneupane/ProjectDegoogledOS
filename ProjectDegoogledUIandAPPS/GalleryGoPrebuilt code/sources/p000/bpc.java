package p000;

/* renamed from: bpc */
/* compiled from: PG */
final /* synthetic */ class bpc implements cvl {

    /* renamed from: a */
    private final bph f3288a;

    public bpc(bph bph) {
        this.f3288a = bph;
    }

    /* renamed from: a */
    public final ieh mo2590a(Object obj) {
        bph bph = this.f3288a;
        cxi cxi = (cxi) obj;
        int i = cxi.f5909a;
        if (!((i & 16384) == 0 || (i & 8192) == 0)) {
            if (bph.f3300e.mo4123b() > ((long) cxi.f5923o) * ((long) cxi.f5924p) * 6) {
                hlj a = hnb.m11765a("Compress image");
                try {
                    ieh a2 = a.mo7548a(hnl.m11801a(bph.f3296a.f3281a.mo2656a(new bou(cxi.f5916h))).mo7610a(new bpd(bph, cxi), bph.f3304i).mo7610a(new bpe(bph, cxi), idh.f13918a).mo7610a(new bpf(bph, cxi), bph.f3304i));
                    if (a == null) {
                        return a2;
                    }
                    a.close();
                    return a2;
                } catch (Throwable th) {
                    ifn.m12935a(th, th);
                }
            }
        }
        return ife.m12820a((Object) null);
        throw th;
    }
}
