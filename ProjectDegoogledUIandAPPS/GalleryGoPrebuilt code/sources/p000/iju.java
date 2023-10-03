package p000;

/* renamed from: iju */
/* compiled from: PG */
final class iju implements ikd {

    /* renamed from: a */
    private final ikd[] f14360a;

    public iju(ikd... ikdArr) {
        this.f14360a = ikdArr;
    }

    /* renamed from: a */
    public final boolean mo8741a(Class cls) {
        ikd[] ikdArr = this.f14360a;
        for (int i = 0; i < 2; i++) {
            if (ikdArr[i].mo8741a(cls)) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: b */
    public final ikc mo8742b(Class cls) {
        ikd[] ikdArr = this.f14360a;
        for (int i = 0; i < 2; i++) {
            ikd ikd = ikdArr[i];
            if (ikd.mo8741a(cls)) {
                return ikd.mo8742b(cls);
            }
        }
        String valueOf = String.valueOf(cls.getName());
        throw new UnsupportedOperationException(valueOf.length() == 0 ? new String("No factory is available for message type: ") : "No factory is available for message type: ".concat(valueOf));
    }
}
