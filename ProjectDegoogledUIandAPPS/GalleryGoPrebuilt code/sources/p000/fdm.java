package p000;

/* renamed from: fdm */
/* compiled from: PG */
public final class fdm {

    /* renamed from: a */
    public final iih f9320a;

    /* renamed from: b */
    public final Object f9321b;

    private fdm(iih iih, Object obj) {
        boolean z = false;
        if (iih.mo8710a() >= 200000000 && iih.mo8710a() < 300000000) {
            z = true;
        }
        ife.m12890c(z);
        this.f9320a = iih;
        this.f9321b = obj;
    }

    /* renamed from: a */
    public static fdm m8618a(iih iih, Object obj) {
        return new fdm(iih, obj);
    }
}
