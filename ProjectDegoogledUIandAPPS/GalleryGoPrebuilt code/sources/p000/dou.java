package p000;

/* renamed from: dou */
/* compiled from: PG */
public final class dou {

    /* renamed from: a */
    public final iqk f6963a;

    /* renamed from: b */
    public final iqk f6964b;

    /* renamed from: c */
    public final iqk f6965c;

    /* renamed from: d */
    public final iqk f6966d;

    public dou(iqk iqk, iqk iqk2, iqk iqk3, iqk iqk4) {
        this.f6963a = (iqk) m6420a(iqk, 1);
        this.f6964b = (iqk) m6420a(iqk2, 2);
        this.f6965c = (iqk) m6420a(iqk3, 3);
        this.f6966d = (iqk) m6420a(iqk4, 4);
    }

    /* renamed from: a */
    public static Object m6420a(Object obj, int i) {
        if (obj != null) {
            return obj;
        }
        StringBuilder sb = new StringBuilder(93);
        sb.append("@AutoFactory method argument is null but is not marked @Nullable. Argument index: ");
        sb.append(i);
        throw new NullPointerException(sb.toString());
    }
}
