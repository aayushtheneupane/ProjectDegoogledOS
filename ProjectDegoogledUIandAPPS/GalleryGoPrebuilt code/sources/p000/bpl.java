package p000;

/* renamed from: bpl */
/* compiled from: PG */
final /* synthetic */ class bpl implements ice {

    /* renamed from: a */
    private final bpm f3308a;

    /* renamed from: b */
    private final ieh f3309b;

    public bpl(bpm bpm, ieh ieh) {
        this.f3308a = bpm;
        this.f3309b = ieh;
    }

    /* renamed from: a */
    public final ieh mo2539a() {
        bpm bpm = this.f3308a;
        ieh ieh = this.f3309b;
        synchronized (bpm.f3310a) {
            bpm.f3311b = bpm.f3312c;
            bpm.f3312c = null;
        }
        return ieh;
    }
}
