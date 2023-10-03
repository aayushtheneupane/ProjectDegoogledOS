package p000;

/* renamed from: ekn */
/* compiled from: PG */
public final class ekn {

    /* renamed from: a */
    public final String f8476a;

    /* renamed from: b */
    private final eki f8477b;

    /* renamed from: c */
    private final eov f8478c;

    public ekn(String str, eov eov, eki eki, byte[] bArr, byte[] bArr2) {
        abj.m86a((Object) eov, (Object) "Cannot construct an Api with a null ClientBuilder");
        abj.m86a((Object) eki, (Object) "Cannot construct an Api with a null ClientKey");
        this.f8476a = str;
        this.f8478c = eov;
        this.f8477b = eki;
    }

    /* renamed from: b */
    public final eov mo4941b() {
        abj.m108a(this.f8478c != null, (Object) "This API was constructed with a SimpleClientBuilder. Use getSimpleClientBuilder");
        return this.f8478c;
    }

    /* renamed from: a */
    public final eki mo4940a() {
        eki eki = this.f8477b;
        if (eki != null) {
            return eki;
        }
        throw new IllegalStateException("This API was constructed with null client keys. This should not be possible.");
    }
}
