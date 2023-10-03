package p000;

import android.os.Bundle;

/* renamed from: fvv */
/* compiled from: PG */
public final class fvv extends fwc {

    /* renamed from: a */
    private fwb f10628a;

    /* renamed from: b */
    private fwb f10629b;

    /* renamed from: c */
    private fwb f10630c;

    /* renamed from: d */
    private fwb f10631d;

    /* renamed from: f */
    private fwb f10632f;

    /* renamed from: a */
    public final void mo6221a(Bundle bundle) {
        int i = fwv.f10647a;
        this.f10629b = mo6244a((fwb) new fvr(this, bundle));
    }

    /* renamed from: f */
    public final void mo6227f() {
        int i = fwv.f10647a;
        this.f10628a = mo6244a((fwb) new fvq());
    }

    /* renamed from: b */
    public final void mo6213b() {
        super.mo6213b();
        fwb fwb = this.f10629b;
        if (fwb != null) {
            mo6247b(fwb);
            this.f10629b = null;
        }
        fwb fwb2 = this.f10631d;
        if (fwb2 != null) {
            mo6247b(fwb2);
            this.f10631d = null;
        }
        fwb fwb3 = this.f10632f;
        if (fwb3 != null) {
            mo6247b(fwb3);
            this.f10632f = null;
        }
    }

    /* renamed from: c */
    public final void mo6223c() {
        int i = fwv.f10647a;
        fwb fwb = this.f10630c;
        if (fwb != null) {
            mo6247b(fwb);
            this.f10630c = null;
        }
        for (int i2 = 0; i2 < this.f10642e.size(); i2++) {
            fwt fwt = (fwt) this.f10642e.get(i2);
            fxk.m9821a((Object) fwt);
            if (fwt instanceof fvl) {
                ((fvl) fwt).mo6216a();
            }
        }
    }

    /* renamed from: d */
    public final void mo6225d() {
        int i = fwv.f10647a;
        fwb fwb = this.f10628a;
        if (fwb != null) {
            mo6247b(fwb);
            this.f10628a = null;
        }
        for (int i2 = 0; i2 < this.f10642e.size(); i2++) {
            fwt fwt = (fwt) this.f10642e.get(i2);
            fxk.m9821a((Object) fwt);
            if (fwt instanceof fvm) {
                ((fvm) fwt).mo6217a();
            }
        }
    }

    /* renamed from: e */
    public final void mo6226e() {
        int i = fwv.f10647a;
        if (this.f10632f == null) {
            this.f10632f = mo6244a((fwb) new fvu());
        }
    }

    /* renamed from: b */
    public final void mo6222b(Bundle bundle) {
        int i = fwv.f10647a;
        this.f10631d = mo6244a((fwb) new fvs(this, bundle));
    }

    /* renamed from: c */
    public final void mo6224c(Bundle bundle) {
        int i = fwv.f10647a;
        this.f10630c = mo6244a((fwb) new fvt(this, bundle));
    }
}
