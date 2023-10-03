package p000;

import p003j$.util.Optional;

/* renamed from: dif */
/* compiled from: PG */
final /* synthetic */ class dif implements hpr {

    /* renamed from: a */
    private final dih f6592a;

    /* renamed from: b */
    private final String f6593b;

    public dif(dih dih, String str) {
        this.f6592a = dih;
        this.f6593b = str;
    }

    /* renamed from: a */
    public final Object mo1484a(Object obj) {
        dih dih = this.f6592a;
        Optional optional = (Optional) obj;
        dih.f6595a.put(this.f6593b, optional);
        return optional;
    }
}
