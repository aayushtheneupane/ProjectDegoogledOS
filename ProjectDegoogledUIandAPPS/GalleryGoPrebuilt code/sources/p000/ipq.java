package p000;

/* renamed from: ipq */
/* compiled from: PG */
final class ipq extends ipk {

    /* renamed from: a */
    private final ipk f14629a;

    public ipq(ipk ipk) {
        this.f14629a = ipk;
    }

    /* renamed from: a */
    public final ipl mo7603a(Object obj) {
        try {
            ipl a = this.f14629a.mo7603a(obj);
            return a != null ? new ipr(a) : ipl.f14624a;
        } catch (RuntimeException e) {
            ips.m14312a(e, this.f14629a, obj);
            return ipl.f14624a;
        }
    }
}
