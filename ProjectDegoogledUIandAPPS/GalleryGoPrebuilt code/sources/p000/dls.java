package p000;

import android.os.Bundle;
import p003j$.util.Optional;

/* renamed from: dls */
/* compiled from: PG */
public class dls implements eaq {

    /* renamed from: a */
    public final cxi f6796a;

    /* renamed from: b */
    public cxi f6797b;

    /* renamed from: c */
    public final Optional f6798c;

    /* renamed from: d */
    public Bundle f6799d;

    /* renamed from: e */
    public Optional f6800e = Optional.empty();

    /* renamed from: f */
    private final cjr f6801f;

    public dls(cxi cxi, dik dik, cjr cjr) {
        this.f6796a = cxi;
        this.f6797b = cxi;
        this.f6798c = Optional.ofNullable(dik);
        this.f6801f = cjr;
    }

    /* renamed from: a */
    public void mo4230a(int i) {
    }

    /* renamed from: a */
    public void mo4233a(boolean z) {
    }

    /* renamed from: b */
    public boolean mo4235b() {
        return true;
    }

    /* renamed from: a */
    public void mo4232a(dlr dlr) {
        this.f6800e = Optional.m16285of(dlr);
        Bundle bundle = this.f6799d;
        if (bundle != null) {
            dlr.mo4222a(bundle);
            this.f6799d = null;
        }
    }

    /* renamed from: a */
    public final boolean mo4234a(eaq eaq) {
        if (!(eaq instanceof dls)) {
            return false;
        }
        dls dls = (dls) eaq;
        if (cyc.m5645a(this.f6796a, dls.f6796a)) {
            return true;
        }
        if (!this.f6801f.mo3175a() || !flw.m9195a(this.f6796a, dls.f6796a)) {
            return false;
        }
        return true;
    }

    /* renamed from: a */
    public final cxh mo4229a() {
        cxh a = cxh.m5592a(this.f6796a.f5913e);
        return a == null ? cxh.UNKNOWN_MEDIA_TYPE : a;
    }

    /* renamed from: a */
    public final void mo4231a(cxi cxi) {
        this.f6797b = cxi;
        this.f6800e.ifPresent(dlo.f6793a);
    }
}
