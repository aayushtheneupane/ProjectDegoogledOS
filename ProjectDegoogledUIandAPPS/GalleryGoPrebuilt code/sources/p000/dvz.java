package p000;

/* renamed from: dvz */
/* compiled from: PG */
final class dvz {

    /* renamed from: a */
    public final hbl f7467a;

    /* renamed from: b */
    public int f7468b;

    public dvz(hbl hbl) {
        this.f7467a = hbl;
    }

    /* renamed from: a */
    static final void m6779a(dvy dvy, dik dik) {
        dwa a = dvy.mo2635n();
        if (!dwu.MEDIA.equals(a.f7472a)) {
            cwn.m5514b("GridItemViewPeer: Attempted to update special type data into a non-media item.", new Object[0]);
            return;
        }
        dxu a2 = a.f7475d.mo2635n();
        a2.f7598m = dik;
        a2.mo4548b();
        a2.mo4551e();
    }
}
