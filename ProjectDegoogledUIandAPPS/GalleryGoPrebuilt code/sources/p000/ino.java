package p000;

/* renamed from: ino */
/* compiled from: PG */
public final class ino implements imp {

    /* renamed from: a */
    private final iqk f14573a;

    /* renamed from: b */
    private final iqk f14574b;

    public ino(iqk iqk, iqk iqk2) {
        this.f14573a = (iqk) m14197a(iqk, 1);
        this.f14574b = (iqk) m14197a(iqk2, 2);
    }

    /* renamed from: a */
    private static Object m14197a(Object obj, int i) {
        if (obj != null) {
            return obj;
        }
        StringBuilder sb = new StringBuilder(93);
        sb.append("@AutoFactory method argument is null but is not marked @Nullable. Argument index: ");
        sb.append(i);
        throw new NullPointerException(sb.toString());
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ imq mo8999a(String str) {
        return new inn((String) m14197a(str, 1), this.f14573a, this.f14574b);
    }
}
