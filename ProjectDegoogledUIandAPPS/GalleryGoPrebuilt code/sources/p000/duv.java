package p000;

/* renamed from: duv */
/* compiled from: PG */
final class duv extends C0597vx {

    /* renamed from: a */
    private final /* synthetic */ duw f7430a;

    public /* synthetic */ duv(duw duw) {
        this.f7430a = duw;
    }

    /* renamed from: a */
    public final int mo2711a(int i) {
        int a = ((dul) this.f7430a.f7432b.get(i)).mo4432a();
        int i2 = a - 1;
        if (a != 0) {
            if (i2 != 0) {
                if (i2 == 1) {
                    return this.f7430a.f7433c;
                }
                if (i2 != 2) {
                    int a2 = ((dul) this.f7430a.f7432b.get(i)).mo4432a();
                    String a3 = dvg.m6743a(a2);
                    if (a2 != 0) {
                        throw new IllegalArgumentException(a3.length() == 0 ? new String("Unknown kind for people grid items: ") : "Unknown kind for people grid items: ".concat(a3));
                    }
                    throw null;
                }
            }
            return 1;
        }
        throw null;
    }
}
