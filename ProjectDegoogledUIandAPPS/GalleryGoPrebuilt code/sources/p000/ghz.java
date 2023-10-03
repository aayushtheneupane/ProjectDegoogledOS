package p000;

/* renamed from: ghz */
/* compiled from: PG */
final class ghz implements gip {

    /* renamed from: a */
    private final /* synthetic */ gik f11398a;

    public ghz(gik gik) {
        this.f11398a = gik;
    }

    /* renamed from: a */
    public final void mo6697a(int i) {
        gik.f11413a.sendMessage(gik.f11413a.obtainMessage(1, i, 0, this.f11398a));
    }

    /* renamed from: a */
    public final void mo6696a() {
        gik.f11413a.sendMessage(gik.f11413a.obtainMessage(0, this.f11398a));
    }
}
