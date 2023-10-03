package p000;

/* renamed from: dtw */
/* compiled from: PG */
public final class dtw extends dls {

    /* renamed from: f */
    private int f7368f = 1;

    public dtw(cxi cxi, dik dik, cjr cjr) {
        super(cxi, dik, cjr);
    }

    /* renamed from: a */
    public final void mo4232a(dlr dlr) {
        super.mo4232a(dlr);
        dlr.mo4221a(this.f7368f);
    }

    /* renamed from: a */
    public final void mo4233a(boolean z) {
        this.f6800e.ifPresent(new dtv(z));
    }

    /* renamed from: a */
    public final void mo4230a(int i) {
        this.f6800e.ifPresent(new dtu(i));
        this.f7368f = i;
    }
}
