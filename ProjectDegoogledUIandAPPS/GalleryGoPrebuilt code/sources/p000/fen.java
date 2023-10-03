package p000;

/* renamed from: fen */
/* compiled from: PG */
final class fen implements fdl {

    /* renamed from: a */
    private int f9374a = -1;

    /* renamed from: b */
    private final /* synthetic */ feo f9375b;

    public fen(feo feo) {
        this.f9375b = feo;
    }

    /* renamed from: a */
    public final void mo5515a(fdr fdr) {
        boolean z;
        fdh d = fdr.mo5543d(ffa.f9433a);
        ife.m12896d(fdr.mo5540b(ffa.f9433a));
        if ((d.f9310a & 2) != 0) {
            z = true;
        } else {
            z = false;
        }
        ife.m12896d(z);
        int b = ife.m12861b(d.f9313d);
        if (b == 0 || b == 1) {
            int size = this.f9375b.f9379d.size();
            this.f9375b.f9379d.add(fdr.mo5534a(ffa.f9433a));
            this.f9375b.f9380e.put(size, this.f9374a);
            int i = this.f9374a;
            this.f9374a = size;
            fdr.mo5544e(ffa.f9433a).mo5521a((fdl) this);
            this.f9374a = i;
        }
    }
}
