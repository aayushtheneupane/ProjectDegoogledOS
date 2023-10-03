package p000;

import java.util.Comparator;

/* renamed from: hvp */
/* compiled from: PG */
public final class hvp {

    /* renamed from: a */
    public final int f13484a;

    /* renamed from: b */
    public final Comparator f13485b;

    /* renamed from: c */
    public final Object[] f13486c;

    /* renamed from: d */
    public int f13487d;

    /* renamed from: e */
    public Object f13488e;

    public hvp(Comparator comparator, int i) {
        this.f13485b = (Comparator) ife.m12869b((Object) comparator, (Object) "comparator");
        this.f13484a = i;
        ife.m12846a(i >= 0, "k must be nonnegative, was %s", i);
        this.f13486c = new Object[(i + i)];
        this.f13487d = 0;
        this.f13488e = null;
    }
}
