package p000;

/* renamed from: aqh */
/* compiled from: PG */
final class aqh implements bee {

    /* renamed from: a */
    private final aba f1452a;

    public aqh(aba aba) {
        this.f1452a = aba;
    }

    /* renamed from: a */
    public final boolean mo1486a(atu atu) {
        aba aba = this.f1452a;
        Throwable th = atu;
        if (atu == null) {
            th = new RuntimeException("Unknown error");
        }
        aba.mo68a(th);
        return true;
    }

    /* renamed from: a */
    public final boolean mo1487a(Object obj) {
        try {
            this.f1452a.mo66a((Object) new aqi(obj));
            return true;
        } catch (Throwable th) {
            this.f1452a.mo68a(th);
            return true;
        }
    }
}
