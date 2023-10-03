package p000;

/* renamed from: gqa */
/* compiled from: PG */
final class gqa implements ice {

    /* renamed from: a */
    private final /* synthetic */ ice f11825a;

    /* renamed from: b */
    private final /* synthetic */ ieh f11826b;

    public gqa(ice ice, ieh ieh) {
        this.f11825a = ice;
        this.f11826b = ieh;
    }

    /* renamed from: a */
    public final ieh mo2539a() {
        return this.f11825a.mo2539a();
    }

    public final String toString() {
        String valueOf = String.valueOf(this.f11825a);
        String valueOf2 = String.valueOf(this.f11826b);
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 10 + String.valueOf(valueOf2).length());
        sb.append(valueOf);
        sb.append(", input=[");
        sb.append(valueOf2);
        sb.append("]");
        return sb.toString();
    }
}
