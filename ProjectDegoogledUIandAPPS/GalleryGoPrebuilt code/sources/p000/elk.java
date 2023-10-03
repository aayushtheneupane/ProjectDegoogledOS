package p000;

/* renamed from: elk */
/* compiled from: PG */
public final class elk extends elh {

    /* renamed from: b */
    private final enz f8510b;

    public elk(enz enz, exe exe) {
        super(exe);
        this.f8510b = enz;
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ void mo4963a(emg emg, boolean z) {
    }

    /* renamed from: d */
    public final void mo4966d(enl enl) {
        if (((eod) enl.f8651d.remove(this.f8510b)) == null) {
            exb exb = this.f8506a.f9167a;
            synchronized (exb.f9158a) {
                if (!exb.f9160c) {
                    exb.f9160c = true;
                    exb.f9162e = false;
                    exb.f9159b.mo5362a(exb);
                    return;
                }
                return;
            }
        }
        throw null;
    }

    /* renamed from: a */
    public final ejt[] mo4960a(enl enl) {
        if (((eod) enl.f8651d.get(this.f8510b)) == null) {
            return null;
        }
        throw null;
    }

    /* renamed from: b */
    public final boolean mo4961b(enl enl) {
        if (((eod) enl.f8651d.get(this.f8510b)) == null) {
            return false;
        }
        throw null;
    }
}
