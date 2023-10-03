package p000;

/* renamed from: fdp */
/* compiled from: PG */
public final class fdp {

    /* renamed from: a */
    public final iih f9322a;

    /* renamed from: b */
    public final Object f9323b;

    private fdp(iih iih, Object obj) {
        boolean z = false;
        if (iih.mo8710a() >= 100000000 && iih.mo8710a() < 200000000) {
            z = true;
        }
        ife.m12890c(z);
        this.f9322a = iih;
        this.f9323b = obj;
    }

    /* renamed from: a */
    public static fdp m8624a(iih iih, Object obj) {
        return new fdp(iih, obj);
    }
}
