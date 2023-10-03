package p000;

import android.os.Bundle;

/* renamed from: fru */
/* compiled from: PG */
public final class fru implements fwt, fwq, fwg {

    /* renamed from: a */
    public final frw f10332a;

    /* renamed from: b */
    public frt f10333b;

    /* renamed from: c */
    public final frv f10334c;

    public fru(fwc fwc, frw frw, frv frv) {
        this.f10332a = frw;
        this.f10334c = frv;
        fwc.mo6246a((fwt) this);
    }

    /* renamed from: a */
    public final void mo6072a(Bundle bundle) {
        if (bundle != null) {
            this.f10333b = (frt) bundle.getParcelable("requestcodehelper_pending_requests");
        } else {
            this.f10333b = new frt();
        }
    }

    /* renamed from: b */
    public final void mo6073b(Bundle bundle) {
        bundle.putParcelable("requestcodehelper_pending_requests", this.f10333b);
    }
}
