package p000;

/* renamed from: gmi */
/* compiled from: PG */
public final /* synthetic */ class gmi implements hpr {

    /* renamed from: a */
    private final gkn f11623a;

    public gmi(gkn gkn) {
        this.f11623a = gkn;
    }

    /* renamed from: a */
    public final Object mo1484a(Object obj) {
        gng gng = (gng) obj;
        try {
            int a = this.f11623a.mo6807a();
            ijy ijy = gng.f11678c;
            Integer valueOf = Integer.valueOf(a);
            if (ijy.containsKey(valueOf)) {
                return glp.m10479a((gnj) ijy.get(valueOf));
            }
            throw new IllegalArgumentException();
        } catch (IllegalArgumentException e) {
            throw new gmc(e);
        }
    }
}
