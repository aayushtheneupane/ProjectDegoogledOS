package p000;

/* renamed from: dlm */
/* compiled from: PG */
public final class dlm {

    /* renamed from: a */
    public final iqk f6788a;

    /* renamed from: b */
    public final iqk f6789b;

    /* renamed from: c */
    public final iqk f6790c;

    /* renamed from: d */
    public final iqk f6791d;

    public dlm(iqk iqk, iqk iqk2, iqk iqk3, iqk iqk4) {
        this.f6788a = (iqk) m6305a(iqk, 1);
        this.f6789b = (iqk) m6305a(iqk2, 2);
        this.f6790c = (iqk) m6305a(iqk3, 3);
        this.f6791d = (iqk) m6305a(iqk4, 4);
    }

    /* renamed from: a */
    public static Object m6305a(Object obj, int i) {
        if (obj != null) {
            return obj;
        }
        StringBuilder sb = new StringBuilder(93);
        sb.append("@AutoFactory method argument is null but is not marked @Nullable. Argument index: ");
        sb.append(i);
        throw new NullPointerException(sb.toString());
    }
}
