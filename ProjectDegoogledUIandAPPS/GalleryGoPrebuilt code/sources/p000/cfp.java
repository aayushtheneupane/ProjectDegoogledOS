package p000;

/* renamed from: cfp */
/* compiled from: PG */
final /* synthetic */ class cfp implements icf {

    /* renamed from: a */
    private final cfu f4276a;

    /* renamed from: b */
    private final cue f4277b;

    public cfp(cfu cfu, cue cue) {
        this.f4276a = cfu;
        this.f4277b = cue;
    }

    /* renamed from: a */
    public final ieh mo2538a(Object obj) {
        cfu cfu = this.f4276a;
        cue cue = this.f4277b;
        hso hso = (hso) obj;
        if (hso != null && !hso.isEmpty()) {
            return cue.mo3143a(new cfn(cfu, hso, cue), cfu.f4285d);
        }
        return ife.m12820a((Object) null);
    }
}
